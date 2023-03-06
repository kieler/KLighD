/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.filtering.parser;

import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import de.cau.cs.kieler.klighd.filtering.AndConnective;
import de.cau.cs.kieler.klighd.filtering.FalseConnective;
import de.cau.cs.kieler.klighd.filtering.GreaterEqualsConnective;
import de.cau.cs.kieler.klighd.filtering.GreaterThanConnective;
import de.cau.cs.kieler.klighd.filtering.LessEqualsConnective;
import de.cau.cs.kieler.klighd.filtering.LessThanConnective;
import de.cau.cs.kieler.klighd.filtering.LogicEqualConnective;
import de.cau.cs.kieler.klighd.filtering.NegationConnective;
import de.cau.cs.kieler.klighd.filtering.NumericAdditionConnective;
import de.cau.cs.kieler.klighd.filtering.NumericConstantConnective;
import de.cau.cs.kieler.klighd.filtering.NumericDivisionConnective;
import de.cau.cs.kieler.klighd.filtering.NumericEqualConnective;
import de.cau.cs.kieler.klighd.filtering.NumericMultiplicationConnective;
import de.cau.cs.kieler.klighd.filtering.NumericNotEqualConnective;
import de.cau.cs.kieler.klighd.filtering.NumericResult;
import de.cau.cs.kieler.klighd.filtering.NumericSubtractionConnective;
import de.cau.cs.kieler.klighd.filtering.OrConnective;
import de.cau.cs.kieler.klighd.filtering.SemanticFilterRule;
import de.cau.cs.kieler.klighd.filtering.SemanticFilterTag;
import de.cau.cs.kieler.klighd.filtering.TrueConnective;

/**
 * Provides a parser for the semantic filter language. The syntax is of the form
 * <rule> <operator> <rule> for all binary operators. The most basic rule is a tag
 * #<tag> that evaluates to true if an element has that tag and false otherwise. 
 * Tags may also contain numeric values in which case they  are written as $<tag>.
 * Constant values can be expressed as true, false or a number. Brackets may be used 
 * to override precedences. The full list of available operators is given in the table 
 * below.
 * 
 * | Operator       |  Syntax                |  Input          | Output  | Precedence |
 * |----------------|------------------------|-----------------|---------|------------|
 * | And            | \<expr\> && \<expr\>   | boolean         | boolean | 4          |
 * | Or             | \<expr\> \|\| \<expr\> | boolean         | boolean | 3          |
 * | Not            | ! \<expr\>             | boolean         | boolean | 6          |
 * | Addition       | \<expr\> + \<expr\>    | numeric         | numeric | 9          |
 * | Subtraction    | \<expr\> - \<expr\>    | numeric         | numeric | 9          |
 * | Multiplication | \<expr\> * \<expr\>    | numeric         | numeric | 10         |
 * | Division       | \<expr\> / \<expr\>    | numeric         | numeric | 10         |
 * | GreaterEquals  | \<expr\> >= \<expr\>   | numeric         | boolean | 8          |
 * | GreaterThan    | \<expr\> > \<expr\>    | numeric         | boolean | 8          |
 * | LessEquals     | \<expr\> <= \<expr\>   | numeric         | boolean | 8          |
 * | LessThan       | \<expr\> < \<expr\>    | numeric         | boolean | 8          |
 * | Equals         | \<expr\> = \<expr\>    | numeric/boolean | boolean | 7          |
 * | NotEqual       | \<expr\> != \<expr\>   | numeric/boolean | boolean | 7          |

 * @author mka
 *
 */
public class SemanticFilterRuleParser {
    
    private abstract class Node {
        public String token;
        // current return type of AST
        // -1: unknown
        // 0: boolean
        // 1: numeric
        public int type;
        public Node(String token, int type) {
            this.token = token;
            this.type = type;
        }
    }
    
    private class OperatorNode extends Node {
        private List<Node> children;
        
        public List<Node> getChildren() { return children; }
        
        public OperatorNode(String token, int type) {
            super(token, type);
            this.children = new ArrayList<>();
        }
        public OperatorNode(String token, int type, List<Node> children) {
            super(token, type);
            this.children = children;
        }
        
        public void addChild(Node child) {
            this.children.add(child);
        }
    }
    
    private class OperandNode extends Node {

        public OperandNode(String token, int type) {
            super(token, type);
        }
    }
    
    public class InvalidSyntaxException extends Exception {

        public InvalidSyntaxException(String message) {
            super(message);
        }
    }
    
    private SemanticFilterRule convertAST(Node ast) throws InvalidSyntaxException {
        try {
            SemanticFilterRule result;
            switch(ast.token) {
            case "*":
                result = new NumericMultiplicationConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case "/":
                result = new NumericDivisionConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case "+":
                result = new NumericAdditionConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case "-":
                result = new NumericSubtractionConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            // NUMERIC-TO-BOOLEAN
            case "=":
                if (ast.type == 0) {
                    result = new LogicEqualConnective(
                            convertAST(((OperatorNode)ast).getChildren().get(0)),
                            convertAST(((OperatorNode)ast).getChildren().get(1)));
                } else {
                    result = new NumericEqualConnective(
                            (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                            (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                }
                break;
            case "!=":
                result = new NumericNotEqualConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case ">=":
                result = new GreaterEqualsConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case ">":
                result = new GreaterThanConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case "<=":
                result = new LessEqualsConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case "<":
                result = new LessThanConnective(
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(0)),
                        (NumericResult) convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            // BOOLEAN-TO-BOOLEAN
            case "||":
                result = new OrConnective(
                        convertAST(((OperatorNode)ast).getChildren().get(0)),
                        convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case "&&":
                result = new AndConnective(
                        convertAST(((OperatorNode)ast).getChildren().get(0)),
                        convertAST(((OperatorNode)ast).getChildren().get(1)));
                break;
            case "!":
                result = new NegationConnective(
                        convertAST(((OperatorNode)ast).getChildren().get(0)));
                break;
            case "true":
                result = new TrueConnective();
                break;
            case "false":
                result = new FalseConnective();
            default:
                // variable or number
                if (ast.token.charAt(0) == '$' || ast.token.charAt(0) == '#') {
                    result = new SemanticFilterTag(ast.token.substring(1));
                } else {
                    result = new NumericConstantConnective(Double.valueOf(ast.token));
                }
            }
            return result;
        } catch (Exception e) {
            throw new InvalidSyntaxException("Syntax error.");
        }
    }
    
    private static List<String> OPERATORS = new ArrayList<>(Arrays.asList(
            "*", "/", "+", "-", "=", "!=", ">=", ">", "<=", "<", "||", "&&", "!"));
    private static Map<String, Integer> PRECEDENCES = Map.ofEntries(
            entry("*", 10),
            entry("/", 10),
            entry("+", 9),
            entry("-", 9),
            entry("=", 7),
            entry("!=", 7),
            entry(">=", 8),
            entry(">", 8),
            entry("<=", 8),
            entry("<", 8),
            entry("||", 3),
            entry("&&", 4),
            entry("!", 6));
    /**
     * Parses a semantic filter rule expression and creates the abstract syntax tree i.e., {@link SemanticFilterRule}.
     * @param ruleString expression
     * @return AST
     * @throws InvalidSyntaxException 
     */
    public SemanticFilterRule parse(String ruleString) throws InvalidSyntaxException {
        
        // Tokenize string
        StringTokenizer tokenizer = new StringTokenizer(ruleString);
        Stack<OperatorNode> operatorStack = new Stack<>();
        Stack<Node> outputStack = new Stack<>();
        
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            
            if (OPERATORS.contains(token)) {

                while (!operatorStack.isEmpty() && !operatorStack.peek().token.equals("(") 
                        && (PRECEDENCES.get(operatorStack.peek().token) > PRECEDENCES.get(token) 
                                || PRECEDENCES.get(operatorStack.peek().token) == PRECEDENCES.get(token) 
                                && !token.equals("!"))) {
                    popOperator(operatorStack, outputStack);
                }
                operatorStack.push(new OperatorNode(token,-1));
                
            } else if (token.equals("(")) {
                operatorStack.push(new OperatorNode(token, -1));
            } else if (token.equals(")")) {
                if (operatorStack.isEmpty()) {
                    throw new InvalidSyntaxException("Mismatched parentheses.");
                }
                while (!operatorStack.peek().token.equals("(")) {
                    if (operatorStack.isEmpty()) {
                        throw new InvalidSyntaxException("Mismatched parentheses.");
                    }
                    popOperator(operatorStack, outputStack);
                }
                // discard final (
                operatorStack.pop();
            } else {
                // Operand
                if (token.startsWith("#") || token.equals("true") || token.equals("false")) {
                    outputStack.push(new OperandNode(token, 0));
                } else {
                    // number or variable starting with $
                    outputStack.push(new OperandNode(token, 1));
                }
            }
        }
        // pop remaining operators
        while (!operatorStack.isEmpty()) {
            popOperator(operatorStack, outputStack);
        }
        
        return convertAST(outputStack.pop());
    }
    
    private void popOperator(Stack<OperatorNode> operatorStack, Stack<Node> outputStack) throws InvalidSyntaxException {
        // remove operator from operator stack and create new node on output stack
        OperatorNode operator = operatorStack.pop();
        if (operator.token.equals("(") || operator.token.equals(")")) {
            throw new InvalidSyntaxException("Mismatched parentheses.");
        }
        if (operator.token.equals("!")) {
            Node operand = outputStack.pop();
            operator.addChild(operand);
            operator.type = 0;
            outputStack.push(operator);
        } else {
            // all other operators are binary
            Node operand1 = outputStack.pop();
            Node operand2 = outputStack.pop();
            // reverse order to get correct final order
            operator.addChild(operand2);
            operator.addChild(operand1);
            if (operand1.type != operand2.type) {
                throw new InvalidSyntaxException("Mixed use of boolean and numeric types in operator " + operator.token + ".");
            }
            operator.type = operand1.type;
            outputStack.push(operator);
        }
    }

}
