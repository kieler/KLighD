/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016-2025 by
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
package de.cau.cs.kieler.kgraph.text.ui.contentassist;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import de.cau.cs.kieler.kgraph.text.grandom.Configuration;
import de.cau.cs.kieler.kgraph.text.grandom.Form;
import de.cau.cs.kieler.kgraph.text.grandom.RandGraph;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
public class GRandomProposalProvider extends AbstractGRandomProposalProvider {

    private static final ArrayList<Pair<Function1<Keyword, Boolean>, Function1<Configuration, Boolean>>> notTogether = CollectionLiterals.<Pair<Function1<Keyword, Boolean>, Function1<Configuration, Boolean>>>newArrayList(
      Pair.<Function1<Keyword, Boolean>, Function1<Configuration, Boolean>>of(new Function1<Keyword, Boolean>() {
        public Boolean apply(final Keyword k) {
          String _value = k.getValue();
          return Boolean.valueOf(Objects.equal(_value, "maxWidth"));
        }
      }, new Function1<Configuration, Boolean>() {
        public Boolean apply(final Configuration c) {
          Form _form = c.getForm();
          return Boolean.valueOf((!Objects.equal(_form, Form.TREES)));
        }
      }), 
      Pair.<Function1<Keyword, Boolean>, Function1<Configuration, Boolean>>of(new Function1<Keyword, Boolean>() {
        public Boolean apply(final Keyword k) {
          String _value = k.getValue();
          return Boolean.valueOf(Objects.equal(_value, "maxDegree"));
        }
      }, new Function1<Configuration, Boolean>() {
        public Boolean apply(final Configuration c) {
          Form _form = c.getForm();
          return Boolean.valueOf((!Objects.equal(_form, Form.TREES)));
        }
      }), 
      Pair.<Function1<Keyword, Boolean>, Function1<Configuration, Boolean>>of(new Function1<Keyword, Boolean>() {
        public Boolean apply(final Keyword k) {
          String _value = k.getValue();
          return Boolean.valueOf(Objects.equal(_value, "partitionFraction"));
        }
      }, new Function1<Configuration, Boolean>() {
        public Boolean apply(final Configuration c) {
          Form _form = c.getForm();
          return Boolean.valueOf((!Objects.equal(_form, Form.BIPARTITE)));
        }
      }));


    private static final ImmutableMap<String, String> documentation = ImmutableMap.<String, String>builder()
            .put("nodes", GRandomProposalProvider.<Integer>d(GRandomProposalProvider.o(GRandomProposalProvider.n("nodes")), Integer.valueOf(30)))
            .put("edges", GRandomProposalProvider.<String>d(GRandomProposalProvider.n("edges"), "total"))
            .put("density", GRandomProposalProvider.o("Fraction of number of nodes squared."))
            .put("relative", GRandomProposalProvider.o("Relative to number of nodes."))
            .put("outgoing", GRandomProposalProvider.o("Number of outgoing edges per node."))
            .put("total", GRandomProposalProvider.<Integer>d(GRandomProposalProvider.o("Total number of edges in graph."), Integer.valueOf(20)))
            .put("labels", "Add labels.")
            .put("self loops", "Allow edges with equal source and target.")
            .put("filename", GRandomProposalProvider.<String>d("Name + index.", "\"random\""))
            .put("format", GRandomProposalProvider.<String>d("Textual or xml.", "kgt"))
            .put("hierarchy", GRandomProposalProvider.e(""))
            .put("maxDegree", "Maximum Degree (trees)")
            .put("maxWidth", "Maximum Width (trees)")
            .put("partitionFraction", "Minimal fraction of nodes in second partition set (bipartite).")
            .put("seed", "Random seed.")
            .put("size", GRandomProposalProvider.<String>d(GRandomProposalProvider.o("size"), "node: width, height 30, port: width, height: 4"))
            .put("ports", GRandomProposalProvider.o("ports"))
            .put("constraint", GRandomProposalProvider.<String>d(GRandomProposalProvider.o("set port constraints"), "free"))
            .put("incoming", GRandomProposalProvider.rel(GRandomProposalProvider.n("incoming ports")))
            .put("north", GRandomProposalProvider.rel(GRandomProposalProvider.n("north ports")))
            .put("east", GRandomProposalProvider.rel(GRandomProposalProvider.n("east ports")))
            .put("south", GRandomProposalProvider.rel(GRandomProposalProvider.n("south ports")))
            .put("west", GRandomProposalProvider.rel(GRandomProposalProvider.n("west ports")))
            .put("re-use", "Fraction of edges with same source or target port.")
            .put("+/-", "Gaussian distribution: Mean +/- Standard deviation.")
            .put("to", "Equal distribution: Min to max value.")
            .build();
    

    public static <T extends Object> String d(final String s, final T defaultVal) {
      return (((s + " (Default: ") + defaultVal) + ")");
    }

    public static String rel(final String s) {
      return (s + ". Relative to other given numbers.");
    }

    public static String n(final String s) {
      return ("Number of " + s);
    }

    public static String e(final String s) {
      return (s + "Experimental. Heare bee dragonns");
    }

    public static String o(final String s) {
      return (s + " With extra options in own block, i.e. {...}.");
    }

    public StyledString getKeywordDisplayString(final Keyword keyword) {
      final String value = keyword.getValue();
      final StyledString styled = new StyledString(value);
      final String doc = GRandomProposalProvider.documentation.get(value);
      if ((doc != null)) {
        StyledString _styledString = new StyledString((": " + doc), StyledString.COUNTER_STYLER);
        styled.append(_styledString);
      }
      return styled;
    }

    public void completeKeyword(final Keyword keyword, final ContentAssistContext contentAssistContext, final ICompletionProposalAcceptor acceptor) {
      EObject _rootModel = contentAssistContext.getRootModel();
      final Configuration conf = this.findConfig(((RandGraph) _rootModel), contentAssistContext.getCurrentModel());
      for (final Pair<Function1<Keyword, Boolean>, Function1<Configuration, Boolean>> rule : GRandomProposalProvider.notTogether) {
        if (((rule.getKey().apply(keyword)).booleanValue() && (rule.getValue().apply(conf)).booleanValue())) {
          return;
        }
      }
      super.completeKeyword(keyword, contentAssistContext, acceptor);
    }

    public Configuration findConfig(final RandGraph root, final EObject node) {
      Object _xifexpression = null;
      if (((root != null) && (root.getConfigs() != null))) {
        EList<Configuration> _configs = root.getConfigs();
        for (final Configuration c : _configs) {
          boolean _in = this.in(node, c);
          if (_in) {
            return c;
          }
        }
      }
      return ((Configuration)_xifexpression);
    }

    public boolean in(final EObject key, final EObject tree) {
      Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(tree.eContainer().eAllContents());
      for (final EObject v : _iterable) {
        boolean _equals = Objects.equal(key, v);
        if (_equals) {
          return true;
        }
      }
      return false;
    }
  }
