package de.cau.cs.kieler.kgraph.text.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKGraphParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_BOOLEAN", "RULE_DEGREES", "RULE_ALPHA", "RULE_FSIZE", "RULE_NATURAL", "RULE_PERCENT", "RULE_RED", "RULE_GREEN", "RULE_BLUE", "RULE_ID", "RULE_TFLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'kgraph'", "'knode'", "'{'", "'}'", "'kedge'", "'('", "':'", "'->'", "')'", "'klabel'", "'kport'", "'='", "'['", "']'", "'pos'", "'x'", "'y'", "'size'", "'width'", "'height'", "'properties'", "'insets'", "'top'", "'bottom'", "'left'", "'right'", "'points'", "';'", "','", "'styles'", "'actions'", "'krendering'", "'*'", "'kchildArea'", "'ktext'", "'krectangle'", "'kroundedRectangle'", "'kellipse'", "'karc'", "'kcustomRendering'", "'kimage'", "'clipShape'", "'junction'", "'kpolyline'", "'kpolygon'", "'kroundedPolyline'", "'kspline'", "'modifier'", "'null'", "'propagate'", "'selection'", "'foreground'", "'background'", "'bold'", "'italic'", "'fontName'", "'scale'", "'fontSize'", "'underline'", "'hAlign'", "'vAlign'", "'invisible'", "'lineCap'", "'lineJoin'", "'lineStyle'", "'dashOffset'", "'dashPattern'", "'lineWidth'", "'rotation'", "'anchor'", "'shadow'", "'reference'", "'krenderingLibrary'", "'kstylesTemplate'", "'grid'", "'topLeftAnchor'", "'bottomRightAnchor'", "'columns'", "'areaData'", "'pointData'", "'referencePoint'", "'minimalWidth'", "'minimalHeight'", "'horizontalAlignment'", "'verticalAlignment'", "'horizontalMargin'", "'verticalMargin'", "'gridData'", "'minCellWidth'", "'minCellHeight'", "'flexibleWidth'", "'flexibleHeight'", "'decoratorData'", "'xoffset'", "'yoffset'", "'relativePos'", "'absolutePos'", "'rotateWithLine'", "'=>'", "'+'", "'.'", "'open'", "'chord'", "'pie'", "'center'", "'none'", "'single'", "'double'", "'error'", "'squiggle'", "'link'", "'solid'", "'dash'", "'dot'", "'dash-dot'", "'dash-dot-dot'", "'custom'", "'flat'", "'round'", "'square'", "'miter'", "'bevel'", "'singleClick'", "'doubleClick'", "'singleOrMultiClick'", "'middleSingleClick'", "'middleDoubleClick'", "'middleSingleOrMultiClick'"
    };
    public static final int T__144=144;
    public static final int RULE_FSIZE=8;
    public static final int T__143=143;
    public static final int T__146=146;
    public static final int T__50=50;
    public static final int T__145=145;
    public static final int T__140=140;
    public static final int T__142=142;
    public static final int T__141=141;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int RULE_PERCENT=10;
    public static final int T__51=51;
    public static final int T__137=137;
    public static final int T__52=52;
    public static final int T__136=136;
    public static final int T__53=53;
    public static final int T__139=139;
    public static final int T__54=54;
    public static final int T__138=138;
    public static final int T__133=133;
    public static final int T__132=132;
    public static final int T__60=60;
    public static final int T__135=135;
    public static final int T__61=61;
    public static final int T__134=134;
    public static final int RULE_ID=14;
    public static final int T__131=131;
    public static final int T__130=130;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=16;
    public static final int T__67=67;
    public static final int T__129=129;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__126=126;
    public static final int T__63=63;
    public static final int T__125=125;
    public static final int T__64=64;
    public static final int T__128=128;
    public static final int T__65=65;
    public static final int T__127=127;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_DEGREES=6;
    public static final int T__32=32;
    public static final int RULE_TFLOAT=15;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int RULE_BOOLEAN=5;
    public static final int T__19=19;
    public static final int RULE_RED=11;
    public static final int T__99=99;
    public static final int T__95=95;
    public static final int RULE_BLUE=13;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int RULE_GREEN=12;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_NATURAL=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__122=122;
    public static final int T__70=70;
    public static final int T__121=121;
    public static final int RULE_ALPHA=7;
    public static final int T__71=71;
    public static final int T__124=124;
    public static final int T__72=72;
    public static final int T__123=123;
    public static final int T__120=120;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=17;
    public static final int T__77=77;
    public static final int T__119=119;
    public static final int T__78=78;
    public static final int T__118=118;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int T__115=115;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__114=114;
    public static final int T__75=75;
    public static final int T__117=117;
    public static final int T__76=76;
    public static final int T__116=116;
    public static final int T__80=80;
    public static final int T__111=111;
    public static final int T__81=81;
    public static final int T__110=110;
    public static final int T__82=82;
    public static final int T__113=113;
    public static final int T__83=83;
    public static final int T__112=112;
    public static final int RULE_WS=18;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__109=109;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

    // delegates
    // delegators


        public InternalKGraphParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalKGraphParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalKGraphParser.tokenNames; }
    public String getGrammarFileName() { return "InternalKGraph.g"; }



     	private KGraphGrammarAccess grammarAccess;

        public InternalKGraphParser(TokenStream input, KGraphGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "ParentKNode";
       	}

       	@Override
       	protected KGraphGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleParentKNode"
    // InternalKGraph.g:65:1: entryRuleParentKNode returns [EObject current=null] : iv_ruleParentKNode= ruleParentKNode EOF ;
    public final EObject entryRuleParentKNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParentKNode = null;


        try {
            // InternalKGraph.g:65:52: (iv_ruleParentKNode= ruleParentKNode EOF )
            // InternalKGraph.g:66:2: iv_ruleParentKNode= ruleParentKNode EOF
            {
             newCompositeNode(grammarAccess.getParentKNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParentKNode=ruleParentKNode();

            state._fsp--;

             current =iv_ruleParentKNode; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParentKNode"


    // $ANTLR start "ruleParentKNode"
    // InternalKGraph.g:72:1: ruleParentKNode returns [EObject current=null] : ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* ) ;
    public final EObject ruleParentKNode() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_data_2_0 = null;

        EObject lv_data_3_0 = null;

        EObject lv_labels_4_0 = null;

        EObject lv_children_5_0 = null;

        EObject lv_ports_6_0 = null;

        EObject lv_outgoingEdges_7_0 = null;

        EObject lv_data_8_0 = null;

        EObject lv_data_9_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:78:2: ( ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* ) )
            // InternalKGraph.g:79:2: ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* )
            {
            // InternalKGraph.g:79:2: ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* )
            // InternalKGraph.g:80:3: () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )*
            {
            // InternalKGraph.g:80:3: ()
            // InternalKGraph.g:81:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParentKNodeAccess().getKNodeAction_0(),
            					current);
            			

            }

            // InternalKGraph.g:87:3: (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==19) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalKGraph.g:88:4: otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )?
                    {
                    otherlv_1=(Token)match(input,19,FOLLOW_3); 

                    				newLeafNode(otherlv_1, grammarAccess.getParentKNodeAccess().getKgraphKeyword_1_0());
                    			
                    // InternalKGraph.g:92:4: ( (lv_data_2_0= ruleKIdentifier ) )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==RULE_ID) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // InternalKGraph.g:93:5: (lv_data_2_0= ruleKIdentifier )
                            {
                            // InternalKGraph.g:93:5: (lv_data_2_0= ruleKIdentifier )
                            // InternalKGraph.g:94:6: lv_data_2_0= ruleKIdentifier
                            {

                            						newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKIdentifierParserRuleCall_1_1_0());
                            					
                            pushFollow(FOLLOW_3);
                            lv_data_2_0=ruleKIdentifier();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getParentKNodeRule());
                            						}
                            						add(
                            							current,
                            							"data",
                            							lv_data_2_0,
                            							"de.cau.cs.kieler.kgraph.text.KGraph.KIdentifier");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // InternalKGraph.g:112:3: ( (lv_data_3_0= ruleKNodeLayout ) )
            // InternalKGraph.g:113:4: (lv_data_3_0= ruleKNodeLayout )
            {
            // InternalKGraph.g:113:4: (lv_data_3_0= ruleKNodeLayout )
            // InternalKGraph.g:114:5: lv_data_3_0= ruleKNodeLayout
            {

            					newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKNodeLayoutParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_data_3_0=ruleKNodeLayout();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParentKNodeRule());
            					}
            					add(
            						current,
            						"data",
            						lv_data_3_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.KNodeLayout");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalKGraph.g:131:3: ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )*
            loop3:
            do {
                int alt3=7;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    alt3=1;
                    }
                    break;
                case 20:
                    {
                    alt3=2;
                    }
                    break;
                case 29:
                    {
                    alt3=3;
                    }
                    break;
                case 23:
                    {
                    alt3=4;
                    }
                    break;
                case 50:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 62:
                case 63:
                case 64:
                case 65:
                    {
                    alt3=5;
                    }
                    break;
                case 91:
                    {
                    alt3=6;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // InternalKGraph.g:132:4: ( (lv_labels_4_0= ruleKLabel ) )
            	    {
            	    // InternalKGraph.g:132:4: ( (lv_labels_4_0= ruleKLabel ) )
            	    // InternalKGraph.g:133:5: (lv_labels_4_0= ruleKLabel )
            	    {
            	    // InternalKGraph.g:133:5: (lv_labels_4_0= ruleKLabel )
            	    // InternalKGraph.g:134:6: lv_labels_4_0= ruleKLabel
            	    {

            	    						newCompositeNode(grammarAccess.getParentKNodeAccess().getLabelsKLabelParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_labels_4_0=ruleKLabel();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParentKNodeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"labels",
            	    							lv_labels_4_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KLabel");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:152:4: ( (lv_children_5_0= ruleKNode ) )
            	    {
            	    // InternalKGraph.g:152:4: ( (lv_children_5_0= ruleKNode ) )
            	    // InternalKGraph.g:153:5: (lv_children_5_0= ruleKNode )
            	    {
            	    // InternalKGraph.g:153:5: (lv_children_5_0= ruleKNode )
            	    // InternalKGraph.g:154:6: lv_children_5_0= ruleKNode
            	    {

            	    						newCompositeNode(grammarAccess.getParentKNodeAccess().getChildrenKNodeParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_children_5_0=ruleKNode();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParentKNodeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"children",
            	    							lv_children_5_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KNode");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:172:4: ( (lv_ports_6_0= ruleKPort ) )
            	    {
            	    // InternalKGraph.g:172:4: ( (lv_ports_6_0= ruleKPort ) )
            	    // InternalKGraph.g:173:5: (lv_ports_6_0= ruleKPort )
            	    {
            	    // InternalKGraph.g:173:5: (lv_ports_6_0= ruleKPort )
            	    // InternalKGraph.g:174:6: lv_ports_6_0= ruleKPort
            	    {

            	    						newCompositeNode(grammarAccess.getParentKNodeAccess().getPortsKPortParserRuleCall_3_2_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_ports_6_0=ruleKPort();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParentKNodeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"ports",
            	    							lv_ports_6_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KPort");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalKGraph.g:192:4: ( (lv_outgoingEdges_7_0= ruleKEdge ) )
            	    {
            	    // InternalKGraph.g:192:4: ( (lv_outgoingEdges_7_0= ruleKEdge ) )
            	    // InternalKGraph.g:193:5: (lv_outgoingEdges_7_0= ruleKEdge )
            	    {
            	    // InternalKGraph.g:193:5: (lv_outgoingEdges_7_0= ruleKEdge )
            	    // InternalKGraph.g:194:6: lv_outgoingEdges_7_0= ruleKEdge
            	    {

            	    						newCompositeNode(grammarAccess.getParentKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_3_3_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_outgoingEdges_7_0=ruleKEdge();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParentKNodeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"outgoingEdges",
            	    							lv_outgoingEdges_7_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KEdge");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalKGraph.g:212:4: ( (lv_data_8_0= ruleKRendering ) )
            	    {
            	    // InternalKGraph.g:212:4: ( (lv_data_8_0= ruleKRendering ) )
            	    // InternalKGraph.g:213:5: (lv_data_8_0= ruleKRendering )
            	    {
            	    // InternalKGraph.g:213:5: (lv_data_8_0= ruleKRendering )
            	    // InternalKGraph.g:214:6: lv_data_8_0= ruleKRendering
            	    {

            	    						newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKRenderingParserRuleCall_3_4_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_data_8_0=ruleKRendering();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParentKNodeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"data",
            	    							lv_data_8_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalKGraph.g:232:4: ( (lv_data_9_0= ruleKRenderingLibrary ) )
            	    {
            	    // InternalKGraph.g:232:4: ( (lv_data_9_0= ruleKRenderingLibrary ) )
            	    // InternalKGraph.g:233:5: (lv_data_9_0= ruleKRenderingLibrary )
            	    {
            	    // InternalKGraph.g:233:5: (lv_data_9_0= ruleKRenderingLibrary )
            	    // InternalKGraph.g:234:6: lv_data_9_0= ruleKRenderingLibrary
            	    {

            	    						newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKRenderingLibraryParserRuleCall_3_5_0());
            	    					
            	    pushFollow(FOLLOW_4);
            	    lv_data_9_0=ruleKRenderingLibrary();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParentKNodeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"data",
            	    							lv_data_9_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KRenderingLibrary");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParentKNode"


    // $ANTLR start "entryRuleKNode"
    // InternalKGraph.g:256:1: entryRuleKNode returns [EObject current=null] : iv_ruleKNode= ruleKNode EOF ;
    public final EObject entryRuleKNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKNode = null;


        try {
            // InternalKGraph.g:256:46: (iv_ruleKNode= ruleKNode EOF )
            // InternalKGraph.g:257:2: iv_ruleKNode= ruleKNode EOF
            {
             newCompositeNode(grammarAccess.getKNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKNode=ruleKNode();

            state._fsp--;

             current =iv_ruleKNode; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKNode"


    // $ANTLR start "ruleKNode"
    // InternalKGraph.g:263:1: ruleKNode returns [EObject current=null] : ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) ) ;
    public final EObject ruleKNode() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_11=null;
        EObject lv_data_2_0 = null;

        EObject lv_data_4_0 = null;

        EObject lv_labels_5_0 = null;

        EObject lv_children_6_0 = null;

        EObject lv_ports_7_0 = null;

        EObject lv_outgoingEdges_8_0 = null;

        EObject lv_data_9_0 = null;

        EObject lv_data_10_0 = null;

        EObject lv_data_12_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:269:2: ( ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) ) )
            // InternalKGraph.g:270:2: ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) )
            {
            // InternalKGraph.g:270:2: ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) )
            // InternalKGraph.g:271:3: () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) )
            {
            // InternalKGraph.g:271:3: ()
            // InternalKGraph.g:272:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKNodeAccess().getKNodeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,20,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getKNodeAccess().getKnodeKeyword_1());
            		
            // InternalKGraph.g:282:3: ( (lv_data_2_0= ruleKIdentifier ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalKGraph.g:283:4: (lv_data_2_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:283:4: (lv_data_2_0= ruleKIdentifier )
                    // InternalKGraph.g:284:5: lv_data_2_0= ruleKIdentifier
                    {

                    					newCompositeNode(grammarAccess.getKNodeAccess().getDataKIdentifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_5);
                    lv_data_2_0=ruleKIdentifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKNodeRule());
                    					}
                    					add(
                    						current,
                    						"data",
                    						lv_data_2_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.KIdentifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:301:3: ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==21) ) {
                alt6=1;
            }
            else if ( (LA6_0==EOF||LA6_0==20||(LA6_0>=22 && LA6_0<=23)||(LA6_0>=28 && LA6_0<=29)||LA6_0==50||(LA6_0>=52 && LA6_0<=59)||(LA6_0>=62 && LA6_0<=65)||LA6_0==91) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalKGraph.g:302:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' )
                    {
                    // InternalKGraph.g:302:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' )
                    // InternalKGraph.g:303:5: otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}'
                    {
                    otherlv_3=(Token)match(input,21,FOLLOW_3); 

                    					newLeafNode(otherlv_3, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_3_0_0());
                    				
                    // InternalKGraph.g:307:5: ( (lv_data_4_0= ruleKNodeLayout ) )
                    // InternalKGraph.g:308:6: (lv_data_4_0= ruleKNodeLayout )
                    {
                    // InternalKGraph.g:308:6: (lv_data_4_0= ruleKNodeLayout )
                    // InternalKGraph.g:309:7: lv_data_4_0= ruleKNodeLayout
                    {

                    							newCompositeNode(grammarAccess.getKNodeAccess().getDataKNodeLayoutParserRuleCall_3_0_1_0());
                    						
                    pushFollow(FOLLOW_6);
                    lv_data_4_0=ruleKNodeLayout();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKNodeRule());
                    							}
                    							add(
                    								current,
                    								"data",
                    								lv_data_4_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.KNodeLayout");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalKGraph.g:326:5: ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )*
                    loop5:
                    do {
                        int alt5=7;
                        switch ( input.LA(1) ) {
                        case 28:
                            {
                            alt5=1;
                            }
                            break;
                        case 20:
                            {
                            alt5=2;
                            }
                            break;
                        case 29:
                            {
                            alt5=3;
                            }
                            break;
                        case 23:
                            {
                            alt5=4;
                            }
                            break;
                        case 50:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                        case 59:
                        case 62:
                        case 63:
                        case 64:
                        case 65:
                            {
                            alt5=5;
                            }
                            break;
                        case 91:
                            {
                            alt5=6;
                            }
                            break;

                        }

                        switch (alt5) {
                    	case 1 :
                    	    // InternalKGraph.g:327:6: ( (lv_labels_5_0= ruleKLabel ) )
                    	    {
                    	    // InternalKGraph.g:327:6: ( (lv_labels_5_0= ruleKLabel ) )
                    	    // InternalKGraph.g:328:7: (lv_labels_5_0= ruleKLabel )
                    	    {
                    	    // InternalKGraph.g:328:7: (lv_labels_5_0= ruleKLabel )
                    	    // InternalKGraph.g:329:8: lv_labels_5_0= ruleKLabel
                    	    {

                    	    								newCompositeNode(grammarAccess.getKNodeAccess().getLabelsKLabelParserRuleCall_3_0_2_0_0());
                    	    							
                    	    pushFollow(FOLLOW_6);
                    	    lv_labels_5_0=ruleKLabel();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"labels",
                    	    									lv_labels_5_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KLabel");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalKGraph.g:347:6: ( (lv_children_6_0= ruleKNode ) )
                    	    {
                    	    // InternalKGraph.g:347:6: ( (lv_children_6_0= ruleKNode ) )
                    	    // InternalKGraph.g:348:7: (lv_children_6_0= ruleKNode )
                    	    {
                    	    // InternalKGraph.g:348:7: (lv_children_6_0= ruleKNode )
                    	    // InternalKGraph.g:349:8: lv_children_6_0= ruleKNode
                    	    {

                    	    								newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_3_0_2_1_0());
                    	    							
                    	    pushFollow(FOLLOW_6);
                    	    lv_children_6_0=ruleKNode();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"children",
                    	    									lv_children_6_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KNode");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalKGraph.g:367:6: ( (lv_ports_7_0= ruleKPort ) )
                    	    {
                    	    // InternalKGraph.g:367:6: ( (lv_ports_7_0= ruleKPort ) )
                    	    // InternalKGraph.g:368:7: (lv_ports_7_0= ruleKPort )
                    	    {
                    	    // InternalKGraph.g:368:7: (lv_ports_7_0= ruleKPort )
                    	    // InternalKGraph.g:369:8: lv_ports_7_0= ruleKPort
                    	    {

                    	    								newCompositeNode(grammarAccess.getKNodeAccess().getPortsKPortParserRuleCall_3_0_2_2_0());
                    	    							
                    	    pushFollow(FOLLOW_6);
                    	    lv_ports_7_0=ruleKPort();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"ports",
                    	    									lv_ports_7_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KPort");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalKGraph.g:387:6: ( (lv_outgoingEdges_8_0= ruleKEdge ) )
                    	    {
                    	    // InternalKGraph.g:387:6: ( (lv_outgoingEdges_8_0= ruleKEdge ) )
                    	    // InternalKGraph.g:388:7: (lv_outgoingEdges_8_0= ruleKEdge )
                    	    {
                    	    // InternalKGraph.g:388:7: (lv_outgoingEdges_8_0= ruleKEdge )
                    	    // InternalKGraph.g:389:8: lv_outgoingEdges_8_0= ruleKEdge
                    	    {

                    	    								newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_3_0_2_3_0());
                    	    							
                    	    pushFollow(FOLLOW_6);
                    	    lv_outgoingEdges_8_0=ruleKEdge();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"outgoingEdges",
                    	    									lv_outgoingEdges_8_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KEdge");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 5 :
                    	    // InternalKGraph.g:407:6: ( (lv_data_9_0= ruleKRendering ) )
                    	    {
                    	    // InternalKGraph.g:407:6: ( (lv_data_9_0= ruleKRendering ) )
                    	    // InternalKGraph.g:408:7: (lv_data_9_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:408:7: (lv_data_9_0= ruleKRendering )
                    	    // InternalKGraph.g:409:8: lv_data_9_0= ruleKRendering
                    	    {

                    	    								newCompositeNode(grammarAccess.getKNodeAccess().getDataKRenderingParserRuleCall_3_0_2_4_0());
                    	    							
                    	    pushFollow(FOLLOW_6);
                    	    lv_data_9_0=ruleKRendering();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"data",
                    	    									lv_data_9_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 6 :
                    	    // InternalKGraph.g:427:6: ( (lv_data_10_0= ruleKRenderingLibrary ) )
                    	    {
                    	    // InternalKGraph.g:427:6: ( (lv_data_10_0= ruleKRenderingLibrary ) )
                    	    // InternalKGraph.g:428:7: (lv_data_10_0= ruleKRenderingLibrary )
                    	    {
                    	    // InternalKGraph.g:428:7: (lv_data_10_0= ruleKRenderingLibrary )
                    	    // InternalKGraph.g:429:8: lv_data_10_0= ruleKRenderingLibrary
                    	    {

                    	    								newCompositeNode(grammarAccess.getKNodeAccess().getDataKRenderingLibraryParserRuleCall_3_0_2_5_0());
                    	    							
                    	    pushFollow(FOLLOW_6);
                    	    lv_data_10_0=ruleKRenderingLibrary();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"data",
                    	    									lv_data_10_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KRenderingLibrary");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,22,FOLLOW_2); 

                    					newLeafNode(otherlv_11, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_3_0_3());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:453:4: ( (lv_data_12_0= ruleEmptyKNodeLayout ) )
                    {
                    // InternalKGraph.g:453:4: ( (lv_data_12_0= ruleEmptyKNodeLayout ) )
                    // InternalKGraph.g:454:5: (lv_data_12_0= ruleEmptyKNodeLayout )
                    {
                    // InternalKGraph.g:454:5: (lv_data_12_0= ruleEmptyKNodeLayout )
                    // InternalKGraph.g:455:6: lv_data_12_0= ruleEmptyKNodeLayout
                    {

                    						newCompositeNode(grammarAccess.getKNodeAccess().getDataEmptyKNodeLayoutParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_data_12_0=ruleEmptyKNodeLayout();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKNodeRule());
                    						}
                    						add(
                    							current,
                    							"data",
                    							lv_data_12_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKNodeLayout");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKNode"


    // $ANTLR start "entryRuleKEdge"
    // InternalKGraph.g:477:1: entryRuleKEdge returns [EObject current=null] : iv_ruleKEdge= ruleKEdge EOF ;
    public final EObject entryRuleKEdge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdge = null;


        try {
            // InternalKGraph.g:477:46: (iv_ruleKEdge= ruleKEdge EOF )
            // InternalKGraph.g:478:2: iv_ruleKEdge= ruleKEdge EOF
            {
             newCompositeNode(grammarAccess.getKEdgeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKEdge=ruleKEdge();

            state._fsp--;

             current =iv_ruleKEdge; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKEdge"


    // $ANTLR start "ruleKEdge"
    // InternalKGraph.g:484:1: ruleKEdge returns [EObject current=null] : (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) ) ;
    public final EObject ruleKEdge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_14=null;
        EObject lv_data_1_0 = null;

        EObject lv_data_11_0 = null;

        EObject lv_labels_12_0 = null;

        EObject lv_data_13_0 = null;

        EObject lv_data_15_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:490:2: ( (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) ) )
            // InternalKGraph.g:491:2: (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) )
            {
            // InternalKGraph.g:491:2: (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) )
            // InternalKGraph.g:492:3: otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getKEdgeAccess().getKedgeKeyword_0());
            		
            // InternalKGraph.g:496:3: ( (lv_data_1_0= ruleKIdentifier ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalKGraph.g:497:4: (lv_data_1_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:497:4: (lv_data_1_0= ruleKIdentifier )
                    // InternalKGraph.g:498:5: lv_data_1_0= ruleKIdentifier
                    {

                    					newCompositeNode(grammarAccess.getKEdgeAccess().getDataKIdentifierParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_8);
                    lv_data_1_0=ruleKIdentifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    					}
                    					add(
                    						current,
                    						"data",
                    						lv_data_1_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.KIdentifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,24,FOLLOW_9); 

            			newLeafNode(otherlv_2, grammarAccess.getKEdgeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalKGraph.g:519:3: (otherlv_3= ':' ( ( ruleQualifiedID ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==25) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalKGraph.g:520:4: otherlv_3= ':' ( ( ruleQualifiedID ) )
                    {
                    otherlv_3=(Token)match(input,25,FOLLOW_10); 

                    				newLeafNode(otherlv_3, grammarAccess.getKEdgeAccess().getColonKeyword_3_0());
                    			
                    // InternalKGraph.g:524:4: ( ( ruleQualifiedID ) )
                    // InternalKGraph.g:525:5: ( ruleQualifiedID )
                    {
                    // InternalKGraph.g:525:5: ( ruleQualifiedID )
                    // InternalKGraph.g:526:6: ruleQualifiedID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKEdgeRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getKEdgeAccess().getSourcePortKPortCrossReference_3_1_0());
                    					
                    pushFollow(FOLLOW_11);
                    ruleQualifiedID();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,26,FOLLOW_10); 

            			newLeafNode(otherlv_5, grammarAccess.getKEdgeAccess().getHyphenMinusGreaterThanSignKeyword_4());
            		
            // InternalKGraph.g:545:3: ( ( ruleQualifiedID ) )
            // InternalKGraph.g:546:4: ( ruleQualifiedID )
            {
            // InternalKGraph.g:546:4: ( ruleQualifiedID )
            // InternalKGraph.g:547:5: ruleQualifiedID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKEdgeRule());
            					}
            				

            					newCompositeNode(grammarAccess.getKEdgeAccess().getTargetKNodeCrossReference_5_0());
            				
            pushFollow(FOLLOW_12);
            ruleQualifiedID();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalKGraph.g:561:3: (otherlv_7= ':' ( ( ruleQualifiedID ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==25) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalKGraph.g:562:4: otherlv_7= ':' ( ( ruleQualifiedID ) )
                    {
                    otherlv_7=(Token)match(input,25,FOLLOW_10); 

                    				newLeafNode(otherlv_7, grammarAccess.getKEdgeAccess().getColonKeyword_6_0());
                    			
                    // InternalKGraph.g:566:4: ( ( ruleQualifiedID ) )
                    // InternalKGraph.g:567:5: ( ruleQualifiedID )
                    {
                    // InternalKGraph.g:567:5: ( ruleQualifiedID )
                    // InternalKGraph.g:568:6: ruleQualifiedID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKEdgeRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getKEdgeAccess().getTargetPortKPortCrossReference_6_1_0());
                    					
                    pushFollow(FOLLOW_13);
                    ruleQualifiedID();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_9=(Token)match(input,27,FOLLOW_14); 

            			newLeafNode(otherlv_9, grammarAccess.getKEdgeAccess().getRightParenthesisKeyword_7());
            		
            // InternalKGraph.g:587:3: ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==21) ) {
                alt11=1;
            }
            else if ( (LA11_0==EOF||LA11_0==20||(LA11_0>=22 && LA11_0<=23)||(LA11_0>=28 && LA11_0<=29)||LA11_0==39||LA11_0==50||(LA11_0>=52 && LA11_0<=59)||(LA11_0>=62 && LA11_0<=65)||LA11_0==91) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalKGraph.g:588:4: (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' )
                    {
                    // InternalKGraph.g:588:4: (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' )
                    // InternalKGraph.g:589:5: otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}'
                    {
                    otherlv_10=(Token)match(input,21,FOLLOW_15); 

                    					newLeafNode(otherlv_10, grammarAccess.getKEdgeAccess().getLeftCurlyBracketKeyword_8_0_0());
                    				
                    // InternalKGraph.g:593:5: ( (lv_data_11_0= ruleKEdgeLayout ) )
                    // InternalKGraph.g:594:6: (lv_data_11_0= ruleKEdgeLayout )
                    {
                    // InternalKGraph.g:594:6: (lv_data_11_0= ruleKEdgeLayout )
                    // InternalKGraph.g:595:7: lv_data_11_0= ruleKEdgeLayout
                    {

                    							newCompositeNode(grammarAccess.getKEdgeAccess().getDataKEdgeLayoutParserRuleCall_8_0_1_0());
                    						
                    pushFollow(FOLLOW_16);
                    lv_data_11_0=ruleKEdgeLayout();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    							}
                    							add(
                    								current,
                    								"data",
                    								lv_data_11_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.KEdgeLayout");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalKGraph.g:612:5: ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==28) ) {
                            alt10=1;
                        }
                        else if ( (LA10_0==50||(LA10_0>=52 && LA10_0<=59)||(LA10_0>=62 && LA10_0<=65)) ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalKGraph.g:613:6: ( (lv_labels_12_0= ruleKLabel ) )
                    	    {
                    	    // InternalKGraph.g:613:6: ( (lv_labels_12_0= ruleKLabel ) )
                    	    // InternalKGraph.g:614:7: (lv_labels_12_0= ruleKLabel )
                    	    {
                    	    // InternalKGraph.g:614:7: (lv_labels_12_0= ruleKLabel )
                    	    // InternalKGraph.g:615:8: lv_labels_12_0= ruleKLabel
                    	    {

                    	    								newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_8_0_2_0_0());
                    	    							
                    	    pushFollow(FOLLOW_16);
                    	    lv_labels_12_0=ruleKLabel();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"labels",
                    	    									lv_labels_12_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KLabel");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalKGraph.g:633:6: ( (lv_data_13_0= ruleKRendering ) )
                    	    {
                    	    // InternalKGraph.g:633:6: ( (lv_data_13_0= ruleKRendering ) )
                    	    // InternalKGraph.g:634:7: (lv_data_13_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:634:7: (lv_data_13_0= ruleKRendering )
                    	    // InternalKGraph.g:635:8: lv_data_13_0= ruleKRendering
                    	    {

                    	    								newCompositeNode(grammarAccess.getKEdgeAccess().getDataKRenderingParserRuleCall_8_0_2_1_0());
                    	    							
                    	    pushFollow(FOLLOW_16);
                    	    lv_data_13_0=ruleKRendering();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"data",
                    	    									lv_data_13_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,22,FOLLOW_2); 

                    					newLeafNode(otherlv_14, grammarAccess.getKEdgeAccess().getRightCurlyBracketKeyword_8_0_3());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:659:4: ( (lv_data_15_0= ruleEmptyKEdgeLayout ) )
                    {
                    // InternalKGraph.g:659:4: ( (lv_data_15_0= ruleEmptyKEdgeLayout ) )
                    // InternalKGraph.g:660:5: (lv_data_15_0= ruleEmptyKEdgeLayout )
                    {
                    // InternalKGraph.g:660:5: (lv_data_15_0= ruleEmptyKEdgeLayout )
                    // InternalKGraph.g:661:6: lv_data_15_0= ruleEmptyKEdgeLayout
                    {

                    						newCompositeNode(grammarAccess.getKEdgeAccess().getDataEmptyKEdgeLayoutParserRuleCall_8_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_data_15_0=ruleEmptyKEdgeLayout();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    						}
                    						add(
                    							current,
                    							"data",
                    							lv_data_15_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKEdgeLayout");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKEdge"


    // $ANTLR start "entryRuleKLabel"
    // InternalKGraph.g:683:1: entryRuleKLabel returns [EObject current=null] : iv_ruleKLabel= ruleKLabel EOF ;
    public final EObject entryRuleKLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLabel = null;


        try {
            // InternalKGraph.g:683:47: (iv_ruleKLabel= ruleKLabel EOF )
            // InternalKGraph.g:684:2: iv_ruleKLabel= ruleKLabel EOF
            {
             newCompositeNode(grammarAccess.getKLabelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKLabel=ruleKLabel();

            state._fsp--;

             current =iv_ruleKLabel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLabel"


    // $ANTLR start "ruleKLabel"
    // InternalKGraph.g:690:1: ruleKLabel returns [EObject current=null] : ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) ;
    public final EObject ruleKLabel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_text_3_0=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        EObject lv_data_2_0 = null;

        EObject lv_data_5_0 = null;

        EObject lv_data_6_0 = null;

        EObject lv_data_8_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:696:2: ( ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) )
            // InternalKGraph.g:697:2: ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            {
            // InternalKGraph.g:697:2: ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            // InternalKGraph.g:698:3: () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
            {
            // InternalKGraph.g:698:3: ()
            // InternalKGraph.g:699:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKLabelAccess().getKLabelAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,28,FOLLOW_17); 

            			newLeafNode(otherlv_1, grammarAccess.getKLabelAccess().getKlabelKeyword_1());
            		
            // InternalKGraph.g:709:3: ( (lv_data_2_0= ruleKIdentifier ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalKGraph.g:710:4: (lv_data_2_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:710:4: (lv_data_2_0= ruleKIdentifier )
                    // InternalKGraph.g:711:5: lv_data_2_0= ruleKIdentifier
                    {

                    					newCompositeNode(grammarAccess.getKLabelAccess().getDataKIdentifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_18);
                    lv_data_2_0=ruleKIdentifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKLabelRule());
                    					}
                    					add(
                    						current,
                    						"data",
                    						lv_data_2_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.KIdentifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:728:3: ( (lv_text_3_0= RULE_STRING ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalKGraph.g:729:4: (lv_text_3_0= RULE_STRING )
                    {
                    // InternalKGraph.g:729:4: (lv_text_3_0= RULE_STRING )
                    // InternalKGraph.g:730:5: lv_text_3_0= RULE_STRING
                    {
                    lv_text_3_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

                    					newLeafNode(lv_text_3_0, grammarAccess.getKLabelAccess().getTextSTRINGTerminalRuleCall_3_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLabelRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"text",
                    						lv_text_3_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.STRING");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:746:3: ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==21) ) {
                alt15=1;
            }
            else if ( (LA15_0==EOF||LA15_0==20||(LA15_0>=22 && LA15_0<=23)||(LA15_0>=28 && LA15_0<=29)||LA15_0==50||(LA15_0>=52 && LA15_0<=59)||(LA15_0>=62 && LA15_0<=65)||LA15_0==91) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // InternalKGraph.g:747:4: (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' )
                    {
                    // InternalKGraph.g:747:4: (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' )
                    // InternalKGraph.g:748:5: otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,21,FOLLOW_19); 

                    					newLeafNode(otherlv_4, grammarAccess.getKLabelAccess().getLeftCurlyBracketKeyword_4_0_0());
                    				
                    // InternalKGraph.g:752:5: ( (lv_data_5_0= ruleKShapeLayout ) )
                    // InternalKGraph.g:753:6: (lv_data_5_0= ruleKShapeLayout )
                    {
                    // InternalKGraph.g:753:6: (lv_data_5_0= ruleKShapeLayout )
                    // InternalKGraph.g:754:7: lv_data_5_0= ruleKShapeLayout
                    {

                    							newCompositeNode(grammarAccess.getKLabelAccess().getDataKShapeLayoutParserRuleCall_4_0_1_0());
                    						
                    pushFollow(FOLLOW_20);
                    lv_data_5_0=ruleKShapeLayout();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKLabelRule());
                    							}
                    							add(
                    								current,
                    								"data",
                    								lv_data_5_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.KShapeLayout");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalKGraph.g:771:5: ( (lv_data_6_0= ruleKRendering ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==50||(LA14_0>=52 && LA14_0<=59)||(LA14_0>=62 && LA14_0<=65)) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalKGraph.g:772:6: (lv_data_6_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:772:6: (lv_data_6_0= ruleKRendering )
                    	    // InternalKGraph.g:773:7: lv_data_6_0= ruleKRendering
                    	    {

                    	    							newCompositeNode(grammarAccess.getKLabelAccess().getDataKRenderingParserRuleCall_4_0_2_0());
                    	    						
                    	    pushFollow(FOLLOW_20);
                    	    lv_data_6_0=ruleKRendering();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getKLabelRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"data",
                    	    								lv_data_6_0,
                    	    								"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,22,FOLLOW_2); 

                    					newLeafNode(otherlv_7, grammarAccess.getKLabelAccess().getRightCurlyBracketKeyword_4_0_3());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:796:4: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    {
                    // InternalKGraph.g:796:4: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    // InternalKGraph.g:797:5: (lv_data_8_0= ruleEmptyKShapeLayout )
                    {
                    // InternalKGraph.g:797:5: (lv_data_8_0= ruleEmptyKShapeLayout )
                    // InternalKGraph.g:798:6: lv_data_8_0= ruleEmptyKShapeLayout
                    {

                    						newCompositeNode(grammarAccess.getKLabelAccess().getDataEmptyKShapeLayoutParserRuleCall_4_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_data_8_0=ruleEmptyKShapeLayout();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKLabelRule());
                    						}
                    						add(
                    							current,
                    							"data",
                    							lv_data_8_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKShapeLayout");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKLabel"


    // $ANTLR start "entryRuleKPort"
    // InternalKGraph.g:820:1: entryRuleKPort returns [EObject current=null] : iv_ruleKPort= ruleKPort EOF ;
    public final EObject entryRuleKPort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPort = null;


        try {
            // InternalKGraph.g:820:46: (iv_ruleKPort= ruleKPort EOF )
            // InternalKGraph.g:821:2: iv_ruleKPort= ruleKPort EOF
            {
             newCompositeNode(grammarAccess.getKPortRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPort=ruleKPort();

            state._fsp--;

             current =iv_ruleKPort; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPort"


    // $ANTLR start "ruleKPort"
    // InternalKGraph.g:827:1: ruleKPort returns [EObject current=null] : ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) ;
    public final EObject ruleKPort() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_7=null;
        EObject lv_data_2_0 = null;

        EObject lv_data_4_0 = null;

        EObject lv_labels_5_0 = null;

        EObject lv_data_6_0 = null;

        EObject lv_data_8_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:833:2: ( ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) )
            // InternalKGraph.g:834:2: ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            {
            // InternalKGraph.g:834:2: ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            // InternalKGraph.g:835:3: () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
            {
            // InternalKGraph.g:835:3: ()
            // InternalKGraph.g:836:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKPortAccess().getKPortAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,29,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getKPortAccess().getKportKeyword_1());
            		
            // InternalKGraph.g:846:3: ( (lv_data_2_0= ruleKIdentifier ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalKGraph.g:847:4: (lv_data_2_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:847:4: (lv_data_2_0= ruleKIdentifier )
                    // InternalKGraph.g:848:5: lv_data_2_0= ruleKIdentifier
                    {

                    					newCompositeNode(grammarAccess.getKPortAccess().getDataKIdentifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_14);
                    lv_data_2_0=ruleKIdentifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKPortRule());
                    					}
                    					add(
                    						current,
                    						"data",
                    						lv_data_2_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.KIdentifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:865:3: ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==21) ) {
                alt18=1;
            }
            else if ( (LA18_0==EOF||LA18_0==20||(LA18_0>=22 && LA18_0<=23)||(LA18_0>=28 && LA18_0<=29)||LA18_0==50||(LA18_0>=52 && LA18_0<=59)||(LA18_0>=62 && LA18_0<=65)||LA18_0==91) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalKGraph.g:866:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' )
                    {
                    // InternalKGraph.g:866:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' )
                    // InternalKGraph.g:867:5: otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}'
                    {
                    otherlv_3=(Token)match(input,21,FOLLOW_21); 

                    					newLeafNode(otherlv_3, grammarAccess.getKPortAccess().getLeftCurlyBracketKeyword_3_0_0());
                    				
                    // InternalKGraph.g:871:5: ( (lv_data_4_0= ruleKShapeLayout ) )
                    // InternalKGraph.g:872:6: (lv_data_4_0= ruleKShapeLayout )
                    {
                    // InternalKGraph.g:872:6: (lv_data_4_0= ruleKShapeLayout )
                    // InternalKGraph.g:873:7: lv_data_4_0= ruleKShapeLayout
                    {

                    							newCompositeNode(grammarAccess.getKPortAccess().getDataKShapeLayoutParserRuleCall_3_0_1_0());
                    						
                    pushFollow(FOLLOW_16);
                    lv_data_4_0=ruleKShapeLayout();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKPortRule());
                    							}
                    							add(
                    								current,
                    								"data",
                    								lv_data_4_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.KShapeLayout");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalKGraph.g:890:5: ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )*
                    loop17:
                    do {
                        int alt17=3;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==28) ) {
                            alt17=1;
                        }
                        else if ( (LA17_0==50||(LA17_0>=52 && LA17_0<=59)||(LA17_0>=62 && LA17_0<=65)) ) {
                            alt17=2;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalKGraph.g:891:6: ( (lv_labels_5_0= ruleKLabel ) )
                    	    {
                    	    // InternalKGraph.g:891:6: ( (lv_labels_5_0= ruleKLabel ) )
                    	    // InternalKGraph.g:892:7: (lv_labels_5_0= ruleKLabel )
                    	    {
                    	    // InternalKGraph.g:892:7: (lv_labels_5_0= ruleKLabel )
                    	    // InternalKGraph.g:893:8: lv_labels_5_0= ruleKLabel
                    	    {

                    	    								newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_3_0_2_0_0());
                    	    							
                    	    pushFollow(FOLLOW_16);
                    	    lv_labels_5_0=ruleKLabel();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKPortRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"labels",
                    	    									lv_labels_5_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KLabel");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalKGraph.g:911:6: ( (lv_data_6_0= ruleKRendering ) )
                    	    {
                    	    // InternalKGraph.g:911:6: ( (lv_data_6_0= ruleKRendering ) )
                    	    // InternalKGraph.g:912:7: (lv_data_6_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:912:7: (lv_data_6_0= ruleKRendering )
                    	    // InternalKGraph.g:913:8: lv_data_6_0= ruleKRendering
                    	    {

                    	    								newCompositeNode(grammarAccess.getKPortAccess().getDataKRenderingParserRuleCall_3_0_2_1_0());
                    	    							
                    	    pushFollow(FOLLOW_16);
                    	    lv_data_6_0=ruleKRendering();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getKPortRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"data",
                    	    									lv_data_6_0,
                    	    									"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,22,FOLLOW_2); 

                    					newLeafNode(otherlv_7, grammarAccess.getKPortAccess().getRightCurlyBracketKeyword_3_0_3());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:937:4: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    {
                    // InternalKGraph.g:937:4: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    // InternalKGraph.g:938:5: (lv_data_8_0= ruleEmptyKShapeLayout )
                    {
                    // InternalKGraph.g:938:5: (lv_data_8_0= ruleEmptyKShapeLayout )
                    // InternalKGraph.g:939:6: lv_data_8_0= ruleEmptyKShapeLayout
                    {

                    						newCompositeNode(grammarAccess.getKPortAccess().getDataEmptyKShapeLayoutParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_data_8_0=ruleEmptyKShapeLayout();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKPortRule());
                    						}
                    						add(
                    							current,
                    							"data",
                    							lv_data_8_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKShapeLayout");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPort"


    // $ANTLR start "entryRuleProperty"
    // InternalKGraph.g:961:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // InternalKGraph.g:961:49: (iv_ruleProperty= ruleProperty EOF )
            // InternalKGraph.g:962:2: iv_ruleProperty= ruleProperty EOF
            {
             newCompositeNode(grammarAccess.getPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProperty=ruleProperty();

            state._fsp--;

             current =iv_ruleProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalKGraph.g:968:1: ruleProperty returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:974:2: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // InternalKGraph.g:975:2: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // InternalKGraph.g:975:2: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) )
            // InternalKGraph.g:976:3: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // InternalKGraph.g:976:3: ( (lv_key_0_0= ruleQualifiedID ) )
            // InternalKGraph.g:977:4: (lv_key_0_0= ruleQualifiedID )
            {
            // InternalKGraph.g:977:4: (lv_key_0_0= ruleQualifiedID )
            // InternalKGraph.g:978:5: lv_key_0_0= ruleQualifiedID
            {

            					newCompositeNode(grammarAccess.getPropertyAccess().getKeyQualifiedIDParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_22);
            lv_key_0_0=ruleQualifiedID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPropertyRule());
            					}
            					set(
            						current,
            						"key",
            						lv_key_0_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,30,FOLLOW_23); 

            			newLeafNode(otherlv_1, grammarAccess.getPropertyAccess().getEqualsSignKeyword_1());
            		
            // InternalKGraph.g:999:3: ( (lv_value_2_0= rulePropertyValue ) )
            // InternalKGraph.g:1000:4: (lv_value_2_0= rulePropertyValue )
            {
            // InternalKGraph.g:1000:4: (lv_value_2_0= rulePropertyValue )
            // InternalKGraph.g:1001:5: lv_value_2_0= rulePropertyValue
            {

            					newCompositeNode(grammarAccess.getPropertyAccess().getValuePropertyValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=rulePropertyValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPropertyRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.PropertyValue");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRuleKIdentifier"
    // InternalKGraph.g:1022:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // InternalKGraph.g:1022:52: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // InternalKGraph.g:1023:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKIdentifier"


    // $ANTLR start "ruleKIdentifier"
    // InternalKGraph.g:1029:1: ruleKIdentifier returns [EObject current=null] : ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        EObject lv_persistentEntries_2_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:1035:2: ( ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? ) )
            // InternalKGraph.g:1036:2: ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? )
            {
            // InternalKGraph.g:1036:2: ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? )
            // InternalKGraph.g:1037:3: ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )?
            {
            // InternalKGraph.g:1037:3: ( (lv_id_0_0= ruleQualifiedID ) )
            // InternalKGraph.g:1038:4: (lv_id_0_0= ruleQualifiedID )
            {
            // InternalKGraph.g:1038:4: (lv_id_0_0= ruleQualifiedID )
            // InternalKGraph.g:1039:5: lv_id_0_0= ruleQualifiedID
            {

            					newCompositeNode(grammarAccess.getKIdentifierAccess().getIdQualifiedIDParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_24);
            lv_id_0_0=ruleQualifiedID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKIdentifierRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_0_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalKGraph.g:1056:3: (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==31) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalKGraph.g:1057:4: otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']'
                    {
                    otherlv_1=(Token)match(input,31,FOLLOW_25); 

                    				newLeafNode(otherlv_1, grammarAccess.getKIdentifierAccess().getLeftSquareBracketKeyword_1_0());
                    			
                    // InternalKGraph.g:1061:4: ( (lv_persistentEntries_2_0= ruleProperty ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==RULE_ID) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalKGraph.g:1062:5: (lv_persistentEntries_2_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:1062:5: (lv_persistentEntries_2_0= ruleProperty )
                    	    // InternalKGraph.g:1063:6: lv_persistentEntries_2_0= ruleProperty
                    	    {

                    	    						newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPropertyParserRuleCall_1_1_0());
                    	    					
                    	    pushFollow(FOLLOW_25);
                    	    lv_persistentEntries_2_0=ruleProperty();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKIdentifierRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"persistentEntries",
                    	    							lv_persistentEntries_2_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.Property");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);

                    otherlv_3=(Token)match(input,32,FOLLOW_2); 

                    				newLeafNode(otherlv_3, grammarAccess.getKIdentifierAccess().getRightSquareBracketKeyword_1_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKIdentifier"


    // $ANTLR start "entryRuleKNodeLayout"
    // InternalKGraph.g:1089:1: entryRuleKNodeLayout returns [EObject current=null] : iv_ruleKNodeLayout= ruleKNodeLayout EOF ;
    public final EObject entryRuleKNodeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKNodeLayout = null;


        try {
            // InternalKGraph.g:1089:52: (iv_ruleKNodeLayout= ruleKNodeLayout EOF )
            // InternalKGraph.g:1090:2: iv_ruleKNodeLayout= ruleKNodeLayout EOF
            {
             newCompositeNode(grammarAccess.getKNodeLayoutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKNodeLayout=ruleKNodeLayout();

            state._fsp--;

             current =iv_ruleKNodeLayout; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKNodeLayout"


    // $ANTLR start "ruleKNodeLayout"
    // InternalKGraph.g:1096:1: ruleKNodeLayout returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) ) ;
    public final EObject ruleKNodeLayout() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        AntlrDatatypeRuleToken lv_xpos_6_0 = null;

        AntlrDatatypeRuleToken lv_ypos_9_0 = null;

        AntlrDatatypeRuleToken lv_width_15_0 = null;

        AntlrDatatypeRuleToken lv_height_18_0 = null;

        EObject lv_persistentEntries_21_0 = null;

        EObject lv_insets_24_0 = null;

        EObject lv_insets_25_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:1102:2: ( ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) ) )
            // InternalKGraph.g:1103:2: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) )
            {
            // InternalKGraph.g:1103:2: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) )
            // InternalKGraph.g:1104:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) )
            {
            // InternalKGraph.g:1104:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) )
            // InternalKGraph.g:1105:4: ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) )
            {
            // InternalKGraph.g:1105:4: ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) )
            // InternalKGraph.g:1106:5: ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0());
            				
            // InternalKGraph.g:1109:5: ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* )
            // InternalKGraph.g:1110:6: ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )*
            {
            // InternalKGraph.g:1110:6: ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )*
            loop24:
            do {
                int alt24=4;
                int LA24_0 = input.LA(1);

                if ( LA24_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 0) ) {
                    alt24=1;
                }
                else if ( LA24_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 1) ) {
                    alt24=2;
                }
                else if ( LA24_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 2) ) {
                    alt24=3;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalKGraph.g:1111:4: ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1111:4: ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1112:5: {...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalKGraph.g:1112:108: ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1113:6: ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 0);
            	    					
            	    // InternalKGraph.g:1116:9: ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1116:10: {...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    }
            	    // InternalKGraph.g:1116:19: (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1116:20: otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_1=(Token)match(input,33,FOLLOW_26); 

            	    									newLeafNode(otherlv_1, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0());
            	    								
            	    otherlv_2=(Token)match(input,25,FOLLOW_27); 

            	    									newLeafNode(otherlv_2, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1());
            	    								
            	    // InternalKGraph.g:1124:9: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1125:10: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1125:10: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1126:11: ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    										  getUnorderedGroupHelper().enter(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2());
            	    										
            	    // InternalKGraph.g:1129:11: ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1130:12: ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1130:12: ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )*
            	    loop21:
            	    do {
            	        int alt21=3;
            	        int LA21_0 = input.LA(1);

            	        if ( LA21_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 0) ) {
            	            alt21=1;
            	        }
            	        else if ( LA21_0 == 35 && getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 1) ) {
            	            alt21=2;
            	        }


            	        switch (alt21) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1131:10: ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1131:10: ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1132:11: {...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1132:118: ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1133:12: ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 0);
            	    	    											
            	    	    // InternalKGraph.g:1136:15: ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1136:16: {...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1136:25: (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1136:26: otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_4=(Token)match(input,34,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_4, grammarAccess.getKNodeLayoutAccess().getXKeyword_0_0_2_0_0());
            	    	    														
            	    	    otherlv_5=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_5, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_0_2_0_1());
            	    	    														
            	    	    // InternalKGraph.g:1144:15: ( (lv_xpos_6_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1145:16: (lv_xpos_6_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1145:16: (lv_xpos_6_0= ruleFloat )
            	    	    // InternalKGraph.g:1146:17: lv_xpos_6_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKNodeLayoutAccess().getXposFloatParserRuleCall_0_0_2_0_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_27);
            	    	    lv_xpos_6_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKNodeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"xpos",
            	    	    																		lv_xpos_6_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // InternalKGraph.g:1169:10: ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1169:10: ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1170:11: {...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1170:118: ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1171:12: ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 1);
            	    	    											
            	    	    // InternalKGraph.g:1174:15: ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1174:16: {...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1174:25: (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1174:26: otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_7=(Token)match(input,35,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_7, grammarAccess.getKNodeLayoutAccess().getYKeyword_0_0_2_1_0());
            	    	    														
            	    	    otherlv_8=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_8, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_0_2_1_1());
            	    	    														
            	    	    // InternalKGraph.g:1182:15: ( (lv_ypos_9_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1183:16: (lv_ypos_9_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1183:16: (lv_ypos_9_0= ruleFloat )
            	    	    // InternalKGraph.g:1184:17: lv_ypos_9_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKNodeLayoutAccess().getYposFloatParserRuleCall_0_0_2_1_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_27);
            	    	    lv_ypos_9_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKNodeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"ypos",
            	    	    																		lv_ypos_9_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop21;
            	        }
            	    } while (true);


            	    }


            	    }

            	     
            	    										  getUnorderedGroupHelper().leave(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2());
            	    										

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:1220:4: ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1220:4: ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1221:5: {...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalKGraph.g:1221:108: ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1222:6: ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 1);
            	    					
            	    // InternalKGraph.g:1225:9: ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1225:10: {...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    }
            	    // InternalKGraph.g:1225:19: (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1225:20: otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_10=(Token)match(input,36,FOLLOW_26); 

            	    									newLeafNode(otherlv_10, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0());
            	    								
            	    otherlv_11=(Token)match(input,25,FOLLOW_29); 

            	    									newLeafNode(otherlv_11, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1());
            	    								
            	    // InternalKGraph.g:1233:9: ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1234:10: ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1234:10: ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1235:11: ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    										  getUnorderedGroupHelper().enter(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2());
            	    										
            	    // InternalKGraph.g:1238:11: ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1239:12: ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1239:12: ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )*
            	    loop22:
            	    do {
            	        int alt22=3;
            	        int LA22_0 = input.LA(1);

            	        if ( LA22_0 == 37 && getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 0) ) {
            	            alt22=1;
            	        }
            	        else if ( LA22_0 == 38 && getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 1) ) {
            	            alt22=2;
            	        }


            	        switch (alt22) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1240:10: ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1240:10: ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1241:11: {...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1241:118: ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1242:12: ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 0);
            	    	    											
            	    	    // InternalKGraph.g:1245:15: ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1245:16: {...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1245:25: (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1245:26: otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_13=(Token)match(input,37,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_13, grammarAccess.getKNodeLayoutAccess().getWidthKeyword_0_1_2_0_0());
            	    	    														
            	    	    otherlv_14=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_14, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_1_2_0_1());
            	    	    														
            	    	    // InternalKGraph.g:1253:15: ( (lv_width_15_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1254:16: (lv_width_15_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1254:16: (lv_width_15_0= ruleFloat )
            	    	    // InternalKGraph.g:1255:17: lv_width_15_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKNodeLayoutAccess().getWidthFloatParserRuleCall_0_1_2_0_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_29);
            	    	    lv_width_15_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKNodeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"width",
            	    	    																		lv_width_15_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // InternalKGraph.g:1278:10: ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1278:10: ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1279:11: {...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1279:118: ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1280:12: ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 1);
            	    	    											
            	    	    // InternalKGraph.g:1283:15: ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1283:16: {...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1283:25: (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1283:26: otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_16=(Token)match(input,38,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_16, grammarAccess.getKNodeLayoutAccess().getHeightKeyword_0_1_2_1_0());
            	    	    														
            	    	    otherlv_17=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_17, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_1_2_1_1());
            	    	    														
            	    	    // InternalKGraph.g:1291:15: ( (lv_height_18_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1292:16: (lv_height_18_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1292:16: (lv_height_18_0= ruleFloat )
            	    	    // InternalKGraph.g:1293:17: lv_height_18_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKNodeLayoutAccess().getHeightFloatParserRuleCall_0_1_2_1_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_29);
            	    	    lv_height_18_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKNodeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"height",
            	    	    																		lv_height_18_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop22;
            	        }
            	    } while (true);


            	    }


            	    }

            	     
            	    										  getUnorderedGroupHelper().leave(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2());
            	    										

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:1329:4: ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) )
            	    {
            	    // InternalKGraph.g:1329:4: ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) )
            	    // InternalKGraph.g:1330:5: {...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 2)");
            	    }
            	    // InternalKGraph.g:1330:108: ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) )
            	    // InternalKGraph.g:1331:6: ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 2);
            	    					
            	    // InternalKGraph.g:1334:9: ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) )
            	    // InternalKGraph.g:1334:10: {...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    }
            	    // InternalKGraph.g:1334:19: (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* )
            	    // InternalKGraph.g:1334:20: otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )*
            	    {
            	    otherlv_19=(Token)match(input,39,FOLLOW_26); 

            	    									newLeafNode(otherlv_19, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0());
            	    								
            	    otherlv_20=(Token)match(input,25,FOLLOW_3); 

            	    									newLeafNode(otherlv_20, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1());
            	    								
            	    // InternalKGraph.g:1342:9: ( (lv_persistentEntries_21_0= ruleProperty ) )*
            	    loop23:
            	    do {
            	        int alt23=2;
            	        int LA23_0 = input.LA(1);

            	        if ( (LA23_0==RULE_ID) ) {
            	            alt23=1;
            	        }


            	        switch (alt23) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1343:10: (lv_persistentEntries_21_0= ruleProperty )
            	    	    {
            	    	    // InternalKGraph.g:1343:10: (lv_persistentEntries_21_0= ruleProperty )
            	    	    // InternalKGraph.g:1344:11: lv_persistentEntries_21_0= ruleProperty
            	    	    {

            	    	    											newCompositeNode(grammarAccess.getKNodeLayoutAccess().getPersistentEntriesPropertyParserRuleCall_0_2_2_0());
            	    	    										
            	    	    pushFollow(FOLLOW_3);
            	    	    lv_persistentEntries_21_0=ruleProperty();

            	    	    state._fsp--;


            	    	    											if (current==null) {
            	    	    												current = createModelElementForParent(grammarAccess.getKNodeLayoutRule());
            	    	    											}
            	    	    											add(
            	    	    												current,
            	    	    												"persistentEntries",
            	    	    												lv_persistentEntries_21_0,
            	    	    												"de.cau.cs.kieler.kgraph.text.KGraph.Property");
            	    	    											afterParserOrEnumRuleCall();
            	    	    										

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop23;
            	        }
            	    } while (true);


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0());
            				

            }

            // InternalKGraph.g:1374:3: ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==40) ) {
                alt25=1;
            }
            else if ( (LA25_0==EOF||LA25_0==20||(LA25_0>=22 && LA25_0<=23)||(LA25_0>=28 && LA25_0<=29)||LA25_0==50||(LA25_0>=52 && LA25_0<=59)||(LA25_0>=62 && LA25_0<=65)||LA25_0==91) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalKGraph.g:1375:4: (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) )
                    {
                    // InternalKGraph.g:1375:4: (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) )
                    // InternalKGraph.g:1376:5: otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) )
                    {
                    otherlv_22=(Token)match(input,40,FOLLOW_26); 

                    					newLeafNode(otherlv_22, grammarAccess.getKNodeLayoutAccess().getInsetsKeyword_1_0_0());
                    				
                    otherlv_23=(Token)match(input,25,FOLLOW_30); 

                    					newLeafNode(otherlv_23, grammarAccess.getKNodeLayoutAccess().getColonKeyword_1_0_1());
                    				
                    // InternalKGraph.g:1384:5: ( (lv_insets_24_0= ruleKInsets ) )
                    // InternalKGraph.g:1385:6: (lv_insets_24_0= ruleKInsets )
                    {
                    // InternalKGraph.g:1385:6: (lv_insets_24_0= ruleKInsets )
                    // InternalKGraph.g:1386:7: lv_insets_24_0= ruleKInsets
                    {

                    							newCompositeNode(grammarAccess.getKNodeLayoutAccess().getInsetsKInsetsParserRuleCall_1_0_2_0());
                    						
                    pushFollow(FOLLOW_2);
                    lv_insets_24_0=ruleKInsets();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKNodeLayoutRule());
                    							}
                    							set(
                    								current,
                    								"insets",
                    								lv_insets_24_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.KInsets");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:1405:4: ( (lv_insets_25_0= ruleEmptyKInsets ) )
                    {
                    // InternalKGraph.g:1405:4: ( (lv_insets_25_0= ruleEmptyKInsets ) )
                    // InternalKGraph.g:1406:5: (lv_insets_25_0= ruleEmptyKInsets )
                    {
                    // InternalKGraph.g:1406:5: (lv_insets_25_0= ruleEmptyKInsets )
                    // InternalKGraph.g:1407:6: lv_insets_25_0= ruleEmptyKInsets
                    {

                    						newCompositeNode(grammarAccess.getKNodeLayoutAccess().getInsetsEmptyKInsetsParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_insets_25_0=ruleEmptyKInsets();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKNodeLayoutRule());
                    						}
                    						set(
                    							current,
                    							"insets",
                    							lv_insets_25_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKInsets");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKNodeLayout"


    // $ANTLR start "entryRuleEmptyKNodeLayout"
    // InternalKGraph.g:1429:1: entryRuleEmptyKNodeLayout returns [EObject current=null] : iv_ruleEmptyKNodeLayout= ruleEmptyKNodeLayout EOF ;
    public final EObject entryRuleEmptyKNodeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKNodeLayout = null;


        try {
            // InternalKGraph.g:1429:57: (iv_ruleEmptyKNodeLayout= ruleEmptyKNodeLayout EOF )
            // InternalKGraph.g:1430:2: iv_ruleEmptyKNodeLayout= ruleEmptyKNodeLayout EOF
            {
             newCompositeNode(grammarAccess.getEmptyKNodeLayoutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEmptyKNodeLayout=ruleEmptyKNodeLayout();

            state._fsp--;

             current =iv_ruleEmptyKNodeLayout; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEmptyKNodeLayout"


    // $ANTLR start "ruleEmptyKNodeLayout"
    // InternalKGraph.g:1436:1: ruleEmptyKNodeLayout returns [EObject current=null] : ( (lv_insets_0_0= ruleEmptyKInsets ) ) ;
    public final EObject ruleEmptyKNodeLayout() throws RecognitionException {
        EObject current = null;

        EObject lv_insets_0_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:1442:2: ( ( (lv_insets_0_0= ruleEmptyKInsets ) ) )
            // InternalKGraph.g:1443:2: ( (lv_insets_0_0= ruleEmptyKInsets ) )
            {
            // InternalKGraph.g:1443:2: ( (lv_insets_0_0= ruleEmptyKInsets ) )
            // InternalKGraph.g:1444:3: (lv_insets_0_0= ruleEmptyKInsets )
            {
            // InternalKGraph.g:1444:3: (lv_insets_0_0= ruleEmptyKInsets )
            // InternalKGraph.g:1445:4: lv_insets_0_0= ruleEmptyKInsets
            {

            				newCompositeNode(grammarAccess.getEmptyKNodeLayoutAccess().getInsetsEmptyKInsetsParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_insets_0_0=ruleEmptyKInsets();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getEmptyKNodeLayoutRule());
            				}
            				set(
            					current,
            					"insets",
            					lv_insets_0_0,
            					"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKInsets");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEmptyKNodeLayout"


    // $ANTLR start "entryRuleKInsets"
    // InternalKGraph.g:1465:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // InternalKGraph.g:1465:48: (iv_ruleKInsets= ruleKInsets EOF )
            // InternalKGraph.g:1466:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKInsets"


    // $ANTLR start "ruleKInsets"
    // InternalKGraph.g:1472:1: ruleKInsets returns [EObject current=null] : ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ;
    public final EObject ruleKInsets() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        AntlrDatatypeRuleToken lv_top_4_0 = null;

        AntlrDatatypeRuleToken lv_bottom_7_0 = null;

        AntlrDatatypeRuleToken lv_left_10_0 = null;

        AntlrDatatypeRuleToken lv_right_13_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:1478:2: ( ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:1479:2: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:1479:2: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:1480:3: () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:1480:3: ()
            // InternalKGraph.g:1481:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
            					current);
            			

            }

            // InternalKGraph.g:1487:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:1488:4: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:1488:4: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:1489:5: ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKInsetsAccess().getUnorderedGroup_1());
            				
            // InternalKGraph.g:1492:5: ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* )
            // InternalKGraph.g:1493:6: ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:1493:6: ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )*
            loop26:
            do {
                int alt26=5;
                int LA26_0 = input.LA(1);

                if ( LA26_0 == 41 && getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 0) ) {
                    alt26=1;
                }
                else if ( LA26_0 == 42 && getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 1) ) {
                    alt26=2;
                }
                else if ( LA26_0 == 43 && getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 2) ) {
                    alt26=3;
                }
                else if ( LA26_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 3) ) {
                    alt26=4;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalKGraph.g:1494:4: ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1494:4: ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1495:5: {...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 0)");
            	    }
            	    // InternalKGraph.g:1495:104: ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1496:6: ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 0);
            	    					
            	    // InternalKGraph.g:1499:9: ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1499:10: {...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1499:19: (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1499:20: otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) )
            	    {
            	    otherlv_2=(Token)match(input,41,FOLLOW_22); 

            	    									newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getTopKeyword_1_0_0());
            	    								
            	    otherlv_3=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_0_1());
            	    								
            	    // InternalKGraph.g:1507:9: ( (lv_top_4_0= ruleFloat ) )
            	    // InternalKGraph.g:1508:10: (lv_top_4_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1508:10: (lv_top_4_0= ruleFloat )
            	    // InternalKGraph.g:1509:11: lv_top_4_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKInsetsAccess().getTopFloatParserRuleCall_1_0_2_0());
            	    										
            	    pushFollow(FOLLOW_31);
            	    lv_top_4_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKInsetsRule());
            	    											}
            	    											set(
            	    												current,
            	    												"top",
            	    												lv_top_4_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKInsetsAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:1532:4: ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1532:4: ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1533:5: {...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 1)");
            	    }
            	    // InternalKGraph.g:1533:104: ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1534:6: ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 1);
            	    					
            	    // InternalKGraph.g:1537:9: ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1537:10: {...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1537:19: (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1537:20: otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) )
            	    {
            	    otherlv_5=(Token)match(input,42,FOLLOW_22); 

            	    									newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_1_1_0());
            	    								
            	    otherlv_6=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_6, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_1_1());
            	    								
            	    // InternalKGraph.g:1545:9: ( (lv_bottom_7_0= ruleFloat ) )
            	    // InternalKGraph.g:1546:10: (lv_bottom_7_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1546:10: (lv_bottom_7_0= ruleFloat )
            	    // InternalKGraph.g:1547:11: lv_bottom_7_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKInsetsAccess().getBottomFloatParserRuleCall_1_1_2_0());
            	    										
            	    pushFollow(FOLLOW_31);
            	    lv_bottom_7_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKInsetsRule());
            	    											}
            	    											set(
            	    												current,
            	    												"bottom",
            	    												lv_bottom_7_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKInsetsAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:1570:4: ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1570:4: ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1571:5: {...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 2)");
            	    }
            	    // InternalKGraph.g:1571:104: ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1572:6: ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 2);
            	    					
            	    // InternalKGraph.g:1575:9: ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1575:10: {...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1575:19: (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1575:20: otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) )
            	    {
            	    otherlv_8=(Token)match(input,43,FOLLOW_22); 

            	    									newLeafNode(otherlv_8, grammarAccess.getKInsetsAccess().getLeftKeyword_1_2_0());
            	    								
            	    otherlv_9=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_2_1());
            	    								
            	    // InternalKGraph.g:1583:9: ( (lv_left_10_0= ruleFloat ) )
            	    // InternalKGraph.g:1584:10: (lv_left_10_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1584:10: (lv_left_10_0= ruleFloat )
            	    // InternalKGraph.g:1585:11: lv_left_10_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKInsetsAccess().getLeftFloatParserRuleCall_1_2_2_0());
            	    										
            	    pushFollow(FOLLOW_31);
            	    lv_left_10_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKInsetsRule());
            	    											}
            	    											set(
            	    												current,
            	    												"left",
            	    												lv_left_10_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKInsetsAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalKGraph.g:1608:4: ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1608:4: ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1609:5: {...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 3)");
            	    }
            	    // InternalKGraph.g:1609:104: ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1610:6: ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 3);
            	    					
            	    // InternalKGraph.g:1613:9: ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1613:10: {...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1613:19: (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1613:20: otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) )
            	    {
            	    otherlv_11=(Token)match(input,44,FOLLOW_22); 

            	    									newLeafNode(otherlv_11, grammarAccess.getKInsetsAccess().getRightKeyword_1_3_0());
            	    								
            	    otherlv_12=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_12, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_3_1());
            	    								
            	    // InternalKGraph.g:1621:9: ( (lv_right_13_0= ruleFloat ) )
            	    // InternalKGraph.g:1622:10: (lv_right_13_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1622:10: (lv_right_13_0= ruleFloat )
            	    // InternalKGraph.g:1623:11: lv_right_13_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKInsetsAccess().getRightFloatParserRuleCall_1_3_2_0());
            	    										
            	    pushFollow(FOLLOW_31);
            	    lv_right_13_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKInsetsRule());
            	    											}
            	    											set(
            	    												current,
            	    												"right",
            	    												lv_right_13_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKInsetsAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKInsetsAccess().getUnorderedGroup_1());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKInsets"


    // $ANTLR start "entryRuleEmptyKInsets"
    // InternalKGraph.g:1657:1: entryRuleEmptyKInsets returns [EObject current=null] : iv_ruleEmptyKInsets= ruleEmptyKInsets EOF ;
    public final EObject entryRuleEmptyKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKInsets = null;


        try {
            // InternalKGraph.g:1657:53: (iv_ruleEmptyKInsets= ruleEmptyKInsets EOF )
            // InternalKGraph.g:1658:2: iv_ruleEmptyKInsets= ruleEmptyKInsets EOF
            {
             newCompositeNode(grammarAccess.getEmptyKInsetsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEmptyKInsets=ruleEmptyKInsets();

            state._fsp--;

             current =iv_ruleEmptyKInsets; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEmptyKInsets"


    // $ANTLR start "ruleEmptyKInsets"
    // InternalKGraph.g:1664:1: ruleEmptyKInsets returns [EObject current=null] : () ;
    public final EObject ruleEmptyKInsets() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalKGraph.g:1670:2: ( () )
            // InternalKGraph.g:1671:2: ()
            {
            // InternalKGraph.g:1671:2: ()
            // InternalKGraph.g:1672:3: 
            {

            			current = forceCreateModelElement(
            				grammarAccess.getEmptyKInsetsAccess().getKInsetsAction(),
            				current);
            		

            }


            }


            	leaveRule();

        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEmptyKInsets"


    // $ANTLR start "entryRuleKShapeLayout"
    // InternalKGraph.g:1681:1: entryRuleKShapeLayout returns [EObject current=null] : iv_ruleKShapeLayout= ruleKShapeLayout EOF ;
    public final EObject entryRuleKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShapeLayout = null;


        try {
            // InternalKGraph.g:1681:53: (iv_ruleKShapeLayout= ruleKShapeLayout EOF )
            // InternalKGraph.g:1682:2: iv_ruleKShapeLayout= ruleKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getKShapeLayoutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKShapeLayout=ruleKShapeLayout();

            state._fsp--;

             current =iv_ruleKShapeLayout; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKShapeLayout"


    // $ANTLR start "ruleKShapeLayout"
    // InternalKGraph.g:1688:1: ruleKShapeLayout returns [EObject current=null] : ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ) ;
    public final EObject ruleKShapeLayout() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        AntlrDatatypeRuleToken lv_xpos_7_0 = null;

        AntlrDatatypeRuleToken lv_ypos_10_0 = null;

        AntlrDatatypeRuleToken lv_width_16_0 = null;

        AntlrDatatypeRuleToken lv_height_19_0 = null;

        EObject lv_persistentEntries_22_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:1694:2: ( ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:1695:2: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:1695:2: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:1696:3: () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:1696:3: ()
            // InternalKGraph.g:1697:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0(),
            					current);
            			

            }

            // InternalKGraph.g:1703:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) )
            // InternalKGraph.g:1704:4: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) )
            {
            // InternalKGraph.g:1704:4: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) )
            // InternalKGraph.g:1705:5: ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1());
            				
            // InternalKGraph.g:1708:5: ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* )
            // InternalKGraph.g:1709:6: ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )*
            {
            // InternalKGraph.g:1709:6: ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )*
            loop30:
            do {
                int alt30=4;
                int LA30_0 = input.LA(1);

                if ( LA30_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 0) ) {
                    alt30=1;
                }
                else if ( LA30_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 1) ) {
                    alt30=2;
                }
                else if ( LA30_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 2) ) {
                    alt30=3;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalKGraph.g:1710:4: ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1710:4: ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1711:5: {...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 0)");
            	    }
            	    // InternalKGraph.g:1711:109: ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1712:6: ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 0);
            	    					
            	    // InternalKGraph.g:1715:9: ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1715:10: {...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    }
            	    // InternalKGraph.g:1715:19: (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1715:20: otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_2=(Token)match(input,33,FOLLOW_26); 

            	    									newLeafNode(otherlv_2, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0());
            	    								
            	    otherlv_3=(Token)match(input,25,FOLLOW_32); 

            	    									newLeafNode(otherlv_3, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1());
            	    								
            	    // InternalKGraph.g:1723:9: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1724:10: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1724:10: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1725:11: ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    										  getUnorderedGroupHelper().enter(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2());
            	    										
            	    // InternalKGraph.g:1728:11: ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1729:12: ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1729:12: ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )*
            	    loop27:
            	    do {
            	        int alt27=3;
            	        int LA27_0 = input.LA(1);

            	        if ( LA27_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 0) ) {
            	            alt27=1;
            	        }
            	        else if ( LA27_0 == 35 && getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 1) ) {
            	            alt27=2;
            	        }


            	        switch (alt27) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1730:10: ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1730:10: ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1731:11: {...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1731:119: ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1732:12: ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 0);
            	    	    											
            	    	    // InternalKGraph.g:1735:15: ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1735:16: {...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1735:25: (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1735:26: otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_5=(Token)match(input,34,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_5, grammarAccess.getKShapeLayoutAccess().getXKeyword_1_0_2_0_0());
            	    	    														
            	    	    otherlv_6=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_6, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_0_2_0_1());
            	    	    														
            	    	    // InternalKGraph.g:1743:15: ( (lv_xpos_7_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1744:16: (lv_xpos_7_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1744:16: (lv_xpos_7_0= ruleFloat )
            	    	    // InternalKGraph.g:1745:17: lv_xpos_7_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKShapeLayoutAccess().getXposFloatParserRuleCall_1_0_2_0_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_32);
            	    	    lv_xpos_7_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"xpos",
            	    	    																		lv_xpos_7_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // InternalKGraph.g:1768:10: ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1768:10: ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1769:11: {...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1769:119: ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1770:12: ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 1);
            	    	    											
            	    	    // InternalKGraph.g:1773:15: ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1773:16: {...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1773:25: (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1773:26: otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_8=(Token)match(input,35,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_8, grammarAccess.getKShapeLayoutAccess().getYKeyword_1_0_2_1_0());
            	    	    														
            	    	    otherlv_9=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_9, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_0_2_1_1());
            	    	    														
            	    	    // InternalKGraph.g:1781:15: ( (lv_ypos_10_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1782:16: (lv_ypos_10_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1782:16: (lv_ypos_10_0= ruleFloat )
            	    	    // InternalKGraph.g:1783:17: lv_ypos_10_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKShapeLayoutAccess().getYposFloatParserRuleCall_1_0_2_1_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_32);
            	    	    lv_ypos_10_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"ypos",
            	    	    																		lv_ypos_10_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop27;
            	        }
            	    } while (true);


            	    }


            	    }

            	     
            	    										  getUnorderedGroupHelper().leave(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2());
            	    										

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:1819:4: ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1819:4: ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1820:5: {...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 1)");
            	    }
            	    // InternalKGraph.g:1820:109: ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1821:6: ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 1);
            	    					
            	    // InternalKGraph.g:1824:9: ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1824:10: {...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    }
            	    // InternalKGraph.g:1824:19: (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1824:20: otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_11=(Token)match(input,36,FOLLOW_26); 

            	    									newLeafNode(otherlv_11, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0());
            	    								
            	    otherlv_12=(Token)match(input,25,FOLLOW_33); 

            	    									newLeafNode(otherlv_12, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1());
            	    								
            	    // InternalKGraph.g:1832:9: ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1833:10: ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1833:10: ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1834:11: ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    										  getUnorderedGroupHelper().enter(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2());
            	    										
            	    // InternalKGraph.g:1837:11: ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1838:12: ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1838:12: ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )*
            	    loop28:
            	    do {
            	        int alt28=3;
            	        int LA28_0 = input.LA(1);

            	        if ( LA28_0 == 37 && getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 0) ) {
            	            alt28=1;
            	        }
            	        else if ( LA28_0 == 38 && getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 1) ) {
            	            alt28=2;
            	        }


            	        switch (alt28) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1839:10: ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1839:10: ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1840:11: {...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1840:119: ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1841:12: ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 0);
            	    	    											
            	    	    // InternalKGraph.g:1844:15: ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1844:16: {...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1844:25: (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1844:26: otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_14=(Token)match(input,37,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_14, grammarAccess.getKShapeLayoutAccess().getWidthKeyword_1_1_2_0_0());
            	    	    														
            	    	    otherlv_15=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_15, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_1_2_0_1());
            	    	    														
            	    	    // InternalKGraph.g:1852:15: ( (lv_width_16_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1853:16: (lv_width_16_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1853:16: (lv_width_16_0= ruleFloat )
            	    	    // InternalKGraph.g:1854:17: lv_width_16_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKShapeLayoutAccess().getWidthFloatParserRuleCall_1_1_2_0_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_33);
            	    	    lv_width_16_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"width",
            	    	    																		lv_width_16_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // InternalKGraph.g:1877:10: ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1877:10: ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1878:11: {...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1878:119: ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1879:12: ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) )
            	    	    {

            	    	    												getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 1);
            	    	    											
            	    	    // InternalKGraph.g:1882:15: ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1882:16: {...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1882:25: (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1882:26: otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_17=(Token)match(input,38,FOLLOW_22); 

            	    	    															newLeafNode(otherlv_17, grammarAccess.getKShapeLayoutAccess().getHeightKeyword_1_1_2_1_0());
            	    	    														
            	    	    otherlv_18=(Token)match(input,30,FOLLOW_28); 

            	    	    															newLeafNode(otherlv_18, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_1_2_1_1());
            	    	    														
            	    	    // InternalKGraph.g:1890:15: ( (lv_height_19_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1891:16: (lv_height_19_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1891:16: (lv_height_19_0= ruleFloat )
            	    	    // InternalKGraph.g:1892:17: lv_height_19_0= ruleFloat
            	    	    {

            	    	    																	newCompositeNode(grammarAccess.getKShapeLayoutAccess().getHeightFloatParserRuleCall_1_1_2_1_2_0());
            	    	    																
            	    	    pushFollow(FOLLOW_33);
            	    	    lv_height_19_0=ruleFloat();

            	    	    state._fsp--;


            	    	    																	if (current==null) {
            	    	    																		current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
            	    	    																	}
            	    	    																	set(
            	    	    																		current,
            	    	    																		"height",
            	    	    																		lv_height_19_0,
            	    	    																		"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    	    																	afterParserOrEnumRuleCall();
            	    	    																

            	    	    }


            	    	    }


            	    	    }


            	    	    }

            	    	     
            	    	    												getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2());
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop28;
            	        }
            	    } while (true);


            	    }


            	    }

            	     
            	    										  getUnorderedGroupHelper().leave(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2());
            	    										

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:1928:4: ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) )
            	    {
            	    // InternalKGraph.g:1928:4: ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) )
            	    // InternalKGraph.g:1929:5: {...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 2)");
            	    }
            	    // InternalKGraph.g:1929:109: ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) )
            	    // InternalKGraph.g:1930:6: ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 2);
            	    					
            	    // InternalKGraph.g:1933:9: ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) )
            	    // InternalKGraph.g:1933:10: {...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    }
            	    // InternalKGraph.g:1933:19: (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* )
            	    // InternalKGraph.g:1933:20: otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )*
            	    {
            	    otherlv_20=(Token)match(input,39,FOLLOW_26); 

            	    									newLeafNode(otherlv_20, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0());
            	    								
            	    otherlv_21=(Token)match(input,25,FOLLOW_34); 

            	    									newLeafNode(otherlv_21, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1());
            	    								
            	    // InternalKGraph.g:1941:9: ( (lv_persistentEntries_22_0= ruleProperty ) )*
            	    loop29:
            	    do {
            	        int alt29=2;
            	        int LA29_0 = input.LA(1);

            	        if ( (LA29_0==RULE_ID) ) {
            	            alt29=1;
            	        }


            	        switch (alt29) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1942:10: (lv_persistentEntries_22_0= ruleProperty )
            	    	    {
            	    	    // InternalKGraph.g:1942:10: (lv_persistentEntries_22_0= ruleProperty )
            	    	    // InternalKGraph.g:1943:11: lv_persistentEntries_22_0= ruleProperty
            	    	    {

            	    	    											newCompositeNode(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPropertyParserRuleCall_1_2_2_0());
            	    	    										
            	    	    pushFollow(FOLLOW_34);
            	    	    lv_persistentEntries_22_0=ruleProperty();

            	    	    state._fsp--;


            	    	    											if (current==null) {
            	    	    												current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
            	    	    											}
            	    	    											add(
            	    	    												current,
            	    	    												"persistentEntries",
            	    	    												lv_persistentEntries_22_0,
            	    	    												"de.cau.cs.kieler.kgraph.text.KGraph.Property");
            	    	    											afterParserOrEnumRuleCall();
            	    	    										

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop29;
            	        }
            	    } while (true);


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKShapeLayout"


    // $ANTLR start "entryRuleEmptyKShapeLayout"
    // InternalKGraph.g:1977:1: entryRuleEmptyKShapeLayout returns [EObject current=null] : iv_ruleEmptyKShapeLayout= ruleEmptyKShapeLayout EOF ;
    public final EObject entryRuleEmptyKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKShapeLayout = null;


        try {
            // InternalKGraph.g:1977:58: (iv_ruleEmptyKShapeLayout= ruleEmptyKShapeLayout EOF )
            // InternalKGraph.g:1978:2: iv_ruleEmptyKShapeLayout= ruleEmptyKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getEmptyKShapeLayoutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEmptyKShapeLayout=ruleEmptyKShapeLayout();

            state._fsp--;

             current =iv_ruleEmptyKShapeLayout; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEmptyKShapeLayout"


    // $ANTLR start "ruleEmptyKShapeLayout"
    // InternalKGraph.g:1984:1: ruleEmptyKShapeLayout returns [EObject current=null] : () ;
    public final EObject ruleEmptyKShapeLayout() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalKGraph.g:1990:2: ( () )
            // InternalKGraph.g:1991:2: ()
            {
            // InternalKGraph.g:1991:2: ()
            // InternalKGraph.g:1992:3: 
            {

            			current = forceCreateModelElement(
            				grammarAccess.getEmptyKShapeLayoutAccess().getKShapeLayoutAction(),
            				current);
            		

            }


            }


            	leaveRule();

        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEmptyKShapeLayout"


    // $ANTLR start "entryRuleKEdgeLayout"
    // InternalKGraph.g:2001:1: entryRuleKEdgeLayout returns [EObject current=null] : iv_ruleKEdgeLayout= ruleKEdgeLayout EOF ;
    public final EObject entryRuleKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdgeLayout = null;


        try {
            // InternalKGraph.g:2001:52: (iv_ruleKEdgeLayout= ruleKEdgeLayout EOF )
            // InternalKGraph.g:2002:2: iv_ruleKEdgeLayout= ruleKEdgeLayout EOF
            {
             newCompositeNode(grammarAccess.getKEdgeLayoutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKEdgeLayout=ruleKEdgeLayout();

            state._fsp--;

             current =iv_ruleKEdgeLayout; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKEdgeLayout"


    // $ANTLR start "ruleKEdgeLayout"
    // InternalKGraph.g:2008:1: ruleKEdgeLayout returns [EObject current=null] : ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? ) ;
    public final EObject ruleKEdgeLayout() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        EObject lv_sourcePoint_2_0 = null;

        EObject lv_bendPoints_4_0 = null;

        EObject lv_targetPoint_6_0 = null;

        EObject lv_targetPoint_7_0 = null;

        EObject lv_sourcePoint_8_0 = null;

        EObject lv_targetPoint_9_0 = null;

        EObject lv_sourcePoint_10_0 = null;

        EObject lv_targetPoint_11_0 = null;

        EObject lv_persistentEntries_14_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:2014:2: ( ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? ) )
            // InternalKGraph.g:2015:2: ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? )
            {
            // InternalKGraph.g:2015:2: ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? )
            // InternalKGraph.g:2016:3: ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )?
            {
            // InternalKGraph.g:2016:3: ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==45) ) {
                alt34=1;
            }
            else if ( (LA34_0==EOF||LA34_0==20||(LA34_0>=22 && LA34_0<=23)||(LA34_0>=28 && LA34_0<=29)||LA34_0==39||LA34_0==50||(LA34_0>=52 && LA34_0<=59)||(LA34_0>=62 && LA34_0<=65)||LA34_0==91) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // InternalKGraph.g:2017:4: (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) )
                    {
                    // InternalKGraph.g:2017:4: (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) )
                    // InternalKGraph.g:2018:5: otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) )
                    {
                    otherlv_0=(Token)match(input,45,FOLLOW_26); 

                    					newLeafNode(otherlv_0, grammarAccess.getKEdgeLayoutAccess().getPointsKeyword_0_0_0());
                    				
                    otherlv_1=(Token)match(input,25,FOLLOW_35); 

                    					newLeafNode(otherlv_1, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_0_0_1());
                    				
                    // InternalKGraph.g:2026:5: ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) )
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==RULE_NATURAL||LA33_0==RULE_TFLOAT) ) {
                        alt33=1;
                    }
                    else if ( (LA33_0==EOF||LA33_0==20||(LA33_0>=22 && LA33_0<=23)||(LA33_0>=28 && LA33_0<=29)||LA33_0==39||LA33_0==50||(LA33_0>=52 && LA33_0<=59)||(LA33_0>=62 && LA33_0<=65)||LA33_0==91) ) {
                        alt33=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 0, input);

                        throw nvae;
                    }
                    switch (alt33) {
                        case 1 :
                            // InternalKGraph.g:2027:6: ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) )
                            {
                            // InternalKGraph.g:2027:6: ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) )
                            // InternalKGraph.g:2028:7: ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) )
                            {
                            // InternalKGraph.g:2028:7: ( (lv_sourcePoint_2_0= ruleKPoint ) )
                            // InternalKGraph.g:2029:8: (lv_sourcePoint_2_0= ruleKPoint )
                            {
                            // InternalKGraph.g:2029:8: (lv_sourcePoint_2_0= ruleKPoint )
                            // InternalKGraph.g:2030:9: lv_sourcePoint_2_0= ruleKPoint
                            {

                            									newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointKPointParserRuleCall_0_0_2_0_0_0());
                            								
                            pushFollow(FOLLOW_36);
                            lv_sourcePoint_2_0=ruleKPoint();

                            state._fsp--;


                            									if (current==null) {
                            										current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                            									}
                            									set(
                            										current,
                            										"sourcePoint",
                            										lv_sourcePoint_2_0,
                            										"de.cau.cs.kieler.kgraph.text.KGraph.KPoint");
                            									afterParserOrEnumRuleCall();
                            								

                            }


                            }

                            // InternalKGraph.g:2047:7: ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) )
                            int alt32=2;
                            int LA32_0 = input.LA(1);

                            if ( (LA32_0==46) ) {
                                alt32=1;
                            }
                            else if ( (LA32_0==EOF||LA32_0==20||(LA32_0>=22 && LA32_0<=23)||(LA32_0>=28 && LA32_0<=29)||LA32_0==39||LA32_0==50||(LA32_0>=52 && LA32_0<=59)||(LA32_0>=62 && LA32_0<=65)||LA32_0==91) ) {
                                alt32=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 32, 0, input);

                                throw nvae;
                            }
                            switch (alt32) {
                                case 1 :
                                    // InternalKGraph.g:2048:8: (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) )
                                    {
                                    // InternalKGraph.g:2048:8: (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) )
                                    // InternalKGraph.g:2049:9: otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) )
                                    {
                                    otherlv_3=(Token)match(input,46,FOLLOW_28); 

                                    									newLeafNode(otherlv_3, grammarAccess.getKEdgeLayoutAccess().getSemicolonKeyword_0_0_2_0_1_0_0());
                                    								
                                    // InternalKGraph.g:2053:9: ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )*
                                    loop31:
                                    do {
                                        int alt31=2;
                                        int LA31_0 = input.LA(1);

                                        if ( (LA31_0==RULE_TFLOAT) ) {
                                            int LA31_1 = input.LA(2);

                                            if ( (LA31_1==47) ) {
                                                int LA31_3 = input.LA(3);

                                                if ( (LA31_3==RULE_TFLOAT) ) {
                                                    int LA31_4 = input.LA(4);

                                                    if ( (LA31_4==46) ) {
                                                        alt31=1;
                                                    }


                                                }
                                                else if ( (LA31_3==RULE_NATURAL) ) {
                                                    int LA31_5 = input.LA(4);

                                                    if ( (LA31_5==46) ) {
                                                        alt31=1;
                                                    }


                                                }


                                            }


                                        }
                                        else if ( (LA31_0==RULE_NATURAL) ) {
                                            int LA31_2 = input.LA(2);

                                            if ( (LA31_2==47) ) {
                                                int LA31_3 = input.LA(3);

                                                if ( (LA31_3==RULE_TFLOAT) ) {
                                                    int LA31_4 = input.LA(4);

                                                    if ( (LA31_4==46) ) {
                                                        alt31=1;
                                                    }


                                                }
                                                else if ( (LA31_3==RULE_NATURAL) ) {
                                                    int LA31_5 = input.LA(4);

                                                    if ( (LA31_5==46) ) {
                                                        alt31=1;
                                                    }


                                                }


                                            }


                                        }


                                        switch (alt31) {
                                    	case 1 :
                                    	    // InternalKGraph.g:2054:10: ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';'
                                    	    {
                                    	    // InternalKGraph.g:2054:10: ( (lv_bendPoints_4_0= ruleKPoint ) )
                                    	    // InternalKGraph.g:2055:11: (lv_bendPoints_4_0= ruleKPoint )
                                    	    {
                                    	    // InternalKGraph.g:2055:11: (lv_bendPoints_4_0= ruleKPoint )
                                    	    // InternalKGraph.g:2056:12: lv_bendPoints_4_0= ruleKPoint
                                    	    {

                                    	    												newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_0_0_2_0_1_0_1_0_0());
                                    	    											
                                    	    pushFollow(FOLLOW_37);
                                    	    lv_bendPoints_4_0=ruleKPoint();

                                    	    state._fsp--;


                                    	    												if (current==null) {
                                    	    													current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                                    	    												}
                                    	    												add(
                                    	    													current,
                                    	    													"bendPoints",
                                    	    													lv_bendPoints_4_0,
                                    	    													"de.cau.cs.kieler.kgraph.text.KGraph.KPoint");
                                    	    												afterParserOrEnumRuleCall();
                                    	    											

                                    	    }


                                    	    }

                                    	    otherlv_5=(Token)match(input,46,FOLLOW_28); 

                                    	    										newLeafNode(otherlv_5, grammarAccess.getKEdgeLayoutAccess().getSemicolonKeyword_0_0_2_0_1_0_1_1());
                                    	    									

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop31;
                                        }
                                    } while (true);

                                    // InternalKGraph.g:2078:9: ( (lv_targetPoint_6_0= ruleKPoint ) )
                                    // InternalKGraph.g:2079:10: (lv_targetPoint_6_0= ruleKPoint )
                                    {
                                    // InternalKGraph.g:2079:10: (lv_targetPoint_6_0= ruleKPoint )
                                    // InternalKGraph.g:2080:11: lv_targetPoint_6_0= ruleKPoint
                                    {

                                    											newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointKPointParserRuleCall_0_0_2_0_1_0_2_0());
                                    										
                                    pushFollow(FOLLOW_38);
                                    lv_targetPoint_6_0=ruleKPoint();

                                    state._fsp--;


                                    											if (current==null) {
                                    												current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                                    											}
                                    											set(
                                    												current,
                                    												"targetPoint",
                                    												lv_targetPoint_6_0,
                                    												"de.cau.cs.kieler.kgraph.text.KGraph.KPoint");
                                    											afterParserOrEnumRuleCall();
                                    										

                                    }


                                    }


                                    }


                                    }
                                    break;
                                case 2 :
                                    // InternalKGraph.g:2099:8: ( (lv_targetPoint_7_0= ruleEmptyKPoint ) )
                                    {
                                    // InternalKGraph.g:2099:8: ( (lv_targetPoint_7_0= ruleEmptyKPoint ) )
                                    // InternalKGraph.g:2100:9: (lv_targetPoint_7_0= ruleEmptyKPoint )
                                    {
                                    // InternalKGraph.g:2100:9: (lv_targetPoint_7_0= ruleEmptyKPoint )
                                    // InternalKGraph.g:2101:10: lv_targetPoint_7_0= ruleEmptyKPoint
                                    {

                                    										newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_0_0_2_0_1_1_0());
                                    									
                                    pushFollow(FOLLOW_38);
                                    lv_targetPoint_7_0=ruleEmptyKPoint();

                                    state._fsp--;


                                    										if (current==null) {
                                    											current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                                    										}
                                    										set(
                                    											current,
                                    											"targetPoint",
                                    											lv_targetPoint_7_0,
                                    											"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKPoint");
                                    										afterParserOrEnumRuleCall();
                                    									

                                    }


                                    }


                                    }
                                    break;

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalKGraph.g:2121:6: ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) )
                            {
                            // InternalKGraph.g:2121:6: ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) )
                            // InternalKGraph.g:2122:7: ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) )
                            {
                            // InternalKGraph.g:2122:7: ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) )
                            // InternalKGraph.g:2123:8: (lv_sourcePoint_8_0= ruleEmptyKPoint )
                            {
                            // InternalKGraph.g:2123:8: (lv_sourcePoint_8_0= ruleEmptyKPoint )
                            // InternalKGraph.g:2124:9: lv_sourcePoint_8_0= ruleEmptyKPoint
                            {

                            									newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointEmptyKPointParserRuleCall_0_0_2_1_0_0());
                            								
                            pushFollow(FOLLOW_39);
                            lv_sourcePoint_8_0=ruleEmptyKPoint();

                            state._fsp--;


                            									if (current==null) {
                            										current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                            									}
                            									set(
                            										current,
                            										"sourcePoint",
                            										lv_sourcePoint_8_0,
                            										"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKPoint");
                            									afterParserOrEnumRuleCall();
                            								

                            }


                            }

                            // InternalKGraph.g:2141:7: ( (lv_targetPoint_9_0= ruleEmptyKPoint ) )
                            // InternalKGraph.g:2142:8: (lv_targetPoint_9_0= ruleEmptyKPoint )
                            {
                            // InternalKGraph.g:2142:8: (lv_targetPoint_9_0= ruleEmptyKPoint )
                            // InternalKGraph.g:2143:9: lv_targetPoint_9_0= ruleEmptyKPoint
                            {

                            									newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_0_0_2_1_1_0());
                            								
                            pushFollow(FOLLOW_38);
                            lv_targetPoint_9_0=ruleEmptyKPoint();

                            state._fsp--;


                            									if (current==null) {
                            										current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                            									}
                            									set(
                            										current,
                            										"targetPoint",
                            										lv_targetPoint_9_0,
                            										"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKPoint");
                            									afterParserOrEnumRuleCall();
                            								

                            }


                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:2164:4: ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) )
                    {
                    // InternalKGraph.g:2164:4: ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) )
                    // InternalKGraph.g:2165:5: ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) )
                    {
                    // InternalKGraph.g:2165:5: ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) )
                    // InternalKGraph.g:2166:6: (lv_sourcePoint_10_0= ruleEmptyKPoint )
                    {
                    // InternalKGraph.g:2166:6: (lv_sourcePoint_10_0= ruleEmptyKPoint )
                    // InternalKGraph.g:2167:7: lv_sourcePoint_10_0= ruleEmptyKPoint
                    {

                    							newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointEmptyKPointParserRuleCall_0_1_0_0());
                    						
                    pushFollow(FOLLOW_39);
                    lv_sourcePoint_10_0=ruleEmptyKPoint();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    							}
                    							set(
                    								current,
                    								"sourcePoint",
                    								lv_sourcePoint_10_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKPoint");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalKGraph.g:2184:5: ( (lv_targetPoint_11_0= ruleEmptyKPoint ) )
                    // InternalKGraph.g:2185:6: (lv_targetPoint_11_0= ruleEmptyKPoint )
                    {
                    // InternalKGraph.g:2185:6: (lv_targetPoint_11_0= ruleEmptyKPoint )
                    // InternalKGraph.g:2186:7: lv_targetPoint_11_0= ruleEmptyKPoint
                    {

                    							newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_0_1_1_0());
                    						
                    pushFollow(FOLLOW_38);
                    lv_targetPoint_11_0=ruleEmptyKPoint();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    							}
                    							set(
                    								current,
                    								"targetPoint",
                    								lv_targetPoint_11_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKPoint");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }


                    }


                    }
                    break;

            }

            // InternalKGraph.g:2205:3: (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==39) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalKGraph.g:2206:4: otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )*
                    {
                    otherlv_12=(Token)match(input,39,FOLLOW_26); 

                    				newLeafNode(otherlv_12, grammarAccess.getKEdgeLayoutAccess().getPropertiesKeyword_1_0());
                    			
                    otherlv_13=(Token)match(input,25,FOLLOW_40); 

                    				newLeafNode(otherlv_13, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_1_1());
                    			
                    // InternalKGraph.g:2214:4: ( (lv_persistentEntries_14_0= ruleProperty ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==RULE_ID) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // InternalKGraph.g:2215:5: (lv_persistentEntries_14_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:2215:5: (lv_persistentEntries_14_0= ruleProperty )
                    	    // InternalKGraph.g:2216:6: lv_persistentEntries_14_0= ruleProperty
                    	    {

                    	    						newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getPersistentEntriesPropertyParserRuleCall_1_2_0());
                    	    					
                    	    pushFollow(FOLLOW_40);
                    	    lv_persistentEntries_14_0=ruleProperty();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"persistentEntries",
                    	    							lv_persistentEntries_14_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.Property");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKEdgeLayout"


    // $ANTLR start "entryRuleEmptyKEdgeLayout"
    // InternalKGraph.g:2238:1: entryRuleEmptyKEdgeLayout returns [EObject current=null] : iv_ruleEmptyKEdgeLayout= ruleEmptyKEdgeLayout EOF ;
    public final EObject entryRuleEmptyKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKEdgeLayout = null;


        try {
            // InternalKGraph.g:2238:57: (iv_ruleEmptyKEdgeLayout= ruleEmptyKEdgeLayout EOF )
            // InternalKGraph.g:2239:2: iv_ruleEmptyKEdgeLayout= ruleEmptyKEdgeLayout EOF
            {
             newCompositeNode(grammarAccess.getEmptyKEdgeLayoutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEmptyKEdgeLayout=ruleEmptyKEdgeLayout();

            state._fsp--;

             current =iv_ruleEmptyKEdgeLayout; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEmptyKEdgeLayout"


    // $ANTLR start "ruleEmptyKEdgeLayout"
    // InternalKGraph.g:2245:1: ruleEmptyKEdgeLayout returns [EObject current=null] : ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) ) ;
    public final EObject ruleEmptyKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject lv_sourcePoint_0_0 = null;

        EObject lv_targetPoint_1_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:2251:2: ( ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) ) )
            // InternalKGraph.g:2252:2: ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) )
            {
            // InternalKGraph.g:2252:2: ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) )
            // InternalKGraph.g:2253:3: ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) )
            {
            // InternalKGraph.g:2253:3: ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) )
            // InternalKGraph.g:2254:4: (lv_sourcePoint_0_0= ruleEmptyKPoint )
            {
            // InternalKGraph.g:2254:4: (lv_sourcePoint_0_0= ruleEmptyKPoint )
            // InternalKGraph.g:2255:5: lv_sourcePoint_0_0= ruleEmptyKPoint
            {

            					newCompositeNode(grammarAccess.getEmptyKEdgeLayoutAccess().getSourcePointEmptyKPointParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_1);
            lv_sourcePoint_0_0=ruleEmptyKPoint();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEmptyKEdgeLayoutRule());
            					}
            					set(
            						current,
            						"sourcePoint",
            						lv_sourcePoint_0_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKPoint");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalKGraph.g:2272:3: ( (lv_targetPoint_1_0= ruleEmptyKPoint ) )
            // InternalKGraph.g:2273:4: (lv_targetPoint_1_0= ruleEmptyKPoint )
            {
            // InternalKGraph.g:2273:4: (lv_targetPoint_1_0= ruleEmptyKPoint )
            // InternalKGraph.g:2274:5: lv_targetPoint_1_0= ruleEmptyKPoint
            {

            					newCompositeNode(grammarAccess.getEmptyKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_targetPoint_1_0=ruleEmptyKPoint();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEmptyKEdgeLayoutRule());
            					}
            					set(
            						current,
            						"targetPoint",
            						lv_targetPoint_1_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.EmptyKPoint");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEmptyKEdgeLayout"


    // $ANTLR start "entryRuleKPoint"
    // InternalKGraph.g:2295:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // InternalKGraph.g:2295:47: (iv_ruleKPoint= ruleKPoint EOF )
            // InternalKGraph.g:2296:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPoint"


    // $ANTLR start "ruleKPoint"
    // InternalKGraph.g:2302:1: ruleKPoint returns [EObject current=null] : ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_x_0_0 = null;

        AntlrDatatypeRuleToken lv_y_2_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:2308:2: ( ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) ) )
            // InternalKGraph.g:2309:2: ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) )
            {
            // InternalKGraph.g:2309:2: ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) )
            // InternalKGraph.g:2310:3: ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) )
            {
            // InternalKGraph.g:2310:3: ( (lv_x_0_0= ruleFloat ) )
            // InternalKGraph.g:2311:4: (lv_x_0_0= ruleFloat )
            {
            // InternalKGraph.g:2311:4: (lv_x_0_0= ruleFloat )
            // InternalKGraph.g:2312:5: lv_x_0_0= ruleFloat
            {

            					newCompositeNode(grammarAccess.getKPointAccess().getXFloatParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_41);
            lv_x_0_0=ruleFloat();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKPointRule());
            					}
            					set(
            						current,
            						"x",
            						lv_x_0_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,47,FOLLOW_28); 

            			newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getCommaKeyword_1());
            		
            // InternalKGraph.g:2333:3: ( (lv_y_2_0= ruleFloat ) )
            // InternalKGraph.g:2334:4: (lv_y_2_0= ruleFloat )
            {
            // InternalKGraph.g:2334:4: (lv_y_2_0= ruleFloat )
            // InternalKGraph.g:2335:5: lv_y_2_0= ruleFloat
            {

            					newCompositeNode(grammarAccess.getKPointAccess().getYFloatParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_y_2_0=ruleFloat();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKPointRule());
            					}
            					set(
            						current,
            						"y",
            						lv_y_2_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPoint"


    // $ANTLR start "entryRuleEmptyKPoint"
    // InternalKGraph.g:2356:1: entryRuleEmptyKPoint returns [EObject current=null] : iv_ruleEmptyKPoint= ruleEmptyKPoint EOF ;
    public final EObject entryRuleEmptyKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKPoint = null;


        try {
            // InternalKGraph.g:2356:52: (iv_ruleEmptyKPoint= ruleEmptyKPoint EOF )
            // InternalKGraph.g:2357:2: iv_ruleEmptyKPoint= ruleEmptyKPoint EOF
            {
             newCompositeNode(grammarAccess.getEmptyKPointRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEmptyKPoint=ruleEmptyKPoint();

            state._fsp--;

             current =iv_ruleEmptyKPoint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEmptyKPoint"


    // $ANTLR start "ruleEmptyKPoint"
    // InternalKGraph.g:2363:1: ruleEmptyKPoint returns [EObject current=null] : () ;
    public final EObject ruleEmptyKPoint() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalKGraph.g:2369:2: ( () )
            // InternalKGraph.g:2370:2: ()
            {
            // InternalKGraph.g:2370:2: ()
            // InternalKGraph.g:2371:3: 
            {

            			current = forceCreateModelElement(
            				grammarAccess.getEmptyKPointAccess().getKPointAction(),
            				current);
            		

            }


            }


            	leaveRule();

        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEmptyKPoint"


    // $ANTLR start "entryRuleKRendering"
    // InternalKGraph.g:2380:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // InternalKGraph.g:2380:51: (iv_ruleKRendering= ruleKRendering EOF )
            // InternalKGraph.g:2381:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRendering"


    // $ANTLR start "ruleKRendering"
    // InternalKGraph.g:2387:1: ruleKRendering returns [EObject current=null] : (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline ) ;
    public final EObject ruleKRendering() throws RecognitionException {
        EObject current = null;

        EObject this_KSimpleRendering_0 = null;

        EObject this_KContainerRendering_1 = null;

        EObject this_KPolyline_2 = null;



        	enterRule();

        try {
            // InternalKGraph.g:2393:2: ( (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline ) )
            // InternalKGraph.g:2394:2: (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline )
            {
            // InternalKGraph.g:2394:2: (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline )
            int alt37=3;
            switch ( input.LA(1) ) {
            case 50:
            case 52:
            case 53:
                {
                alt37=1;
                }
                break;
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                {
                alt37=2;
                }
                break;
            case 62:
            case 63:
            case 64:
            case 65:
                {
                alt37=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // InternalKGraph.g:2395:3: this_KSimpleRendering_0= ruleKSimpleRendering
                    {

                    			newCompositeNode(grammarAccess.getKRenderingAccess().getKSimpleRenderingParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_KSimpleRendering_0=ruleKSimpleRendering();

                    state._fsp--;


                    			current = this_KSimpleRendering_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:2404:3: this_KContainerRendering_1= ruleKContainerRendering
                    {

                    			newCompositeNode(grammarAccess.getKRenderingAccess().getKContainerRenderingParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_KContainerRendering_1=ruleKContainerRendering();

                    state._fsp--;


                    			current = this_KContainerRendering_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:2413:3: this_KPolyline_2= ruleKPolyline
                    {

                    			newCompositeNode(grammarAccess.getKRenderingAccess().getKPolylineParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_KPolyline_2=ruleKPolyline();

                    state._fsp--;


                    			current = this_KPolyline_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRendering"


    // $ANTLR start "entryRuleKSimpleRendering"
    // InternalKGraph.g:2425:1: entryRuleKSimpleRendering returns [EObject current=null] : iv_ruleKSimpleRendering= ruleKSimpleRendering EOF ;
    public final EObject entryRuleKSimpleRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSimpleRendering = null;


        try {
            // InternalKGraph.g:2425:57: (iv_ruleKSimpleRendering= ruleKSimpleRendering EOF )
            // InternalKGraph.g:2426:2: iv_ruleKSimpleRendering= ruleKSimpleRendering EOF
            {
             newCompositeNode(grammarAccess.getKSimpleRenderingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKSimpleRendering=ruleKSimpleRendering();

            state._fsp--;

             current =iv_ruleKSimpleRendering; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKSimpleRendering"


    // $ANTLR start "ruleKSimpleRendering"
    // InternalKGraph.g:2432:1: ruleKSimpleRendering returns [EObject current=null] : ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? ) ;
    public final EObject ruleKSimpleRendering() throws RecognitionException {
        EObject current = null;

        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_16=null;
        EObject this_KRenderingRef_0 = null;

        EObject this_KChildArea_1 = null;

        EObject this_KText_2 = null;

        AntlrDatatypeRuleToken lv_id_3_0 = null;

        EObject lv_persistentEntries_5_0 = null;

        EObject lv_styles_11_0 = null;

        EObject lv_actions_14_0 = null;

        EObject lv_placementData_15_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:2438:2: ( ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? ) )
            // InternalKGraph.g:2439:2: ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? )
            {
            // InternalKGraph.g:2439:2: ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? )
            // InternalKGraph.g:2440:3: (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )?
            {
            // InternalKGraph.g:2440:3: (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText )
            int alt38=3;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt38=1;
                }
                break;
            case 52:
                {
                alt38=2;
                }
                break;
            case 53:
                {
                alt38=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // InternalKGraph.g:2441:4: this_KRenderingRef_0= ruleKRenderingRef
                    {

                    				newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getKRenderingRefParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_42);
                    this_KRenderingRef_0=ruleKRenderingRef();

                    state._fsp--;


                    				current = this_KRenderingRef_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:2450:4: this_KChildArea_1= ruleKChildArea
                    {

                    				newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getKChildAreaParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_42);
                    this_KChildArea_1=ruleKChildArea();

                    state._fsp--;


                    				current = this_KChildArea_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:2459:4: this_KText_2= ruleKText
                    {

                    				newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getKTextParserRuleCall_0_2());
                    			
                    pushFollow(FOLLOW_42);
                    this_KText_2=ruleKText();

                    state._fsp--;


                    				current = this_KText_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalKGraph.g:2468:3: ( (lv_id_3_0= ruleQualifiedID ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==RULE_ID) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalKGraph.g:2469:4: (lv_id_3_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:2469:4: (lv_id_3_0= ruleQualifiedID )
                    // InternalKGraph.g:2470:5: lv_id_3_0= ruleQualifiedID
                    {

                    					newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getIdQualifiedIDParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_43);
                    lv_id_3_0=ruleQualifiedID();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKSimpleRenderingRule());
                    					}
                    					set(
                    						current,
                    						"id",
                    						lv_id_3_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:2487:3: (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==31) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalKGraph.g:2488:4: otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']'
                    {
                    otherlv_4=(Token)match(input,31,FOLLOW_25); 

                    				newLeafNode(otherlv_4, grammarAccess.getKSimpleRenderingAccess().getLeftSquareBracketKeyword_2_0());
                    			
                    // InternalKGraph.g:2492:4: ( (lv_persistentEntries_5_0= ruleProperty ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==RULE_ID) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // InternalKGraph.g:2493:5: (lv_persistentEntries_5_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:2493:5: (lv_persistentEntries_5_0= ruleProperty )
                    	    // InternalKGraph.g:2494:6: lv_persistentEntries_5_0= ruleProperty
                    	    {

                    	    						newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_25);
                    	    lv_persistentEntries_5_0=ruleProperty();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKSimpleRenderingRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"persistentEntries",
                    	    							lv_persistentEntries_5_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.Property");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,32,FOLLOW_44); 

                    				newLeafNode(otherlv_6, grammarAccess.getKSimpleRenderingAccess().getRightSquareBracketKeyword_2_2());
                    			

                    }
                    break;

            }

            // InternalKGraph.g:2516:3: (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==21) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalKGraph.g:2517:4: otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}'
                    {
                    otherlv_7=(Token)match(input,21,FOLLOW_45); 

                    				newLeafNode(otherlv_7, grammarAccess.getKSimpleRenderingAccess().getLeftCurlyBracketKeyword_3_0());
                    			
                    // InternalKGraph.g:2521:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) )
                    // InternalKGraph.g:2522:5: ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) )
                    {
                    // InternalKGraph.g:2522:5: ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) )
                    // InternalKGraph.g:2523:6: ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1());
                    					
                    // InternalKGraph.g:2526:6: ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* )
                    // InternalKGraph.g:2527:7: ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )*
                    {
                    // InternalKGraph.g:2527:7: ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )*
                    loop44:
                    do {
                        int alt44=4;
                        int LA44_0 = input.LA(1);

                        if ( LA44_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 0) ) {
                            alt44=1;
                        }
                        else if ( LA44_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 1) ) {
                            alt44=2;
                        }
                        else if ( ( LA44_0 >= 97 && LA44_0 <= 98 || LA44_0 == 106 || LA44_0 == 111 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 2) ) {
                            alt44=3;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // InternalKGraph.g:2528:5: ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2528:5: ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) )
                    	    // InternalKGraph.g:2529:6: {...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 0)");
                    	    }
                    	    // InternalKGraph.g:2529:116: ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) )
                    	    // InternalKGraph.g:2530:7: ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 0);
                    	    						
                    	    // InternalKGraph.g:2533:10: ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) )
                    	    // InternalKGraph.g:2533:11: {...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2533:20: (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* )
                    	    // InternalKGraph.g:2533:21: otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )*
                    	    {
                    	    otherlv_9=(Token)match(input,48,FOLLOW_26); 

                    	    										newLeafNode(otherlv_9, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0());
                    	    									
                    	    otherlv_10=(Token)match(input,25,FOLLOW_46); 

                    	    										newLeafNode(otherlv_10, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1());
                    	    									
                    	    // InternalKGraph.g:2541:10: ( (lv_styles_11_0= ruleKStyle ) )*
                    	    loop42:
                    	    do {
                    	        int alt42=2;
                    	        int LA42_0 = input.LA(1);

                    	        if ( ((LA42_0>=68 && LA42_0<=83)||(LA42_0>=86 && LA42_0<=87)||(LA42_0>=89 && LA42_0<=90)) ) {
                    	            alt42=1;
                    	        }


                    	        switch (alt42) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2542:11: (lv_styles_11_0= ruleKStyle )
                    	    	    {
                    	    	    // InternalKGraph.g:2542:11: (lv_styles_11_0= ruleKStyle )
                    	    	    // InternalKGraph.g:2543:12: lv_styles_11_0= ruleKStyle
                    	    	    {

                    	    	    												newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getStylesKStyleParserRuleCall_3_1_0_2_0());
                    	    	    											
                    	    	    pushFollow(FOLLOW_46);
                    	    	    lv_styles_11_0=ruleKStyle();

                    	    	    state._fsp--;


                    	    	    												if (current==null) {
                    	    	    													current = createModelElementForParent(grammarAccess.getKSimpleRenderingRule());
                    	    	    												}
                    	    	    												add(
                    	    	    													current,
                    	    	    													"styles",
                    	    	    													lv_styles_11_0,
                    	    	    													"de.cau.cs.kieler.kgraph.text.KGraph.KStyle");
                    	    	    												afterParserOrEnumRuleCall();
                    	    	    											

                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop42;
                    	        }
                    	    } while (true);


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalKGraph.g:2566:5: ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2566:5: ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) )
                    	    // InternalKGraph.g:2567:6: {...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 1)");
                    	    }
                    	    // InternalKGraph.g:2567:116: ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) )
                    	    // InternalKGraph.g:2568:7: ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 1);
                    	    						
                    	    // InternalKGraph.g:2571:10: ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) )
                    	    // InternalKGraph.g:2571:11: {...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2571:20: (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* )
                    	    // InternalKGraph.g:2571:21: otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )*
                    	    {
                    	    otherlv_12=(Token)match(input,49,FOLLOW_26); 

                    	    										newLeafNode(otherlv_12, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0());
                    	    									
                    	    otherlv_13=(Token)match(input,25,FOLLOW_47); 

                    	    										newLeafNode(otherlv_13, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1());
                    	    									
                    	    // InternalKGraph.g:2579:10: ( (lv_actions_14_0= ruleKAction ) )*
                    	    loop43:
                    	    do {
                    	        int alt43=2;
                    	        int LA43_0 = input.LA(1);

                    	        if ( ((LA43_0>=141 && LA43_0<=146)) ) {
                    	            alt43=1;
                    	        }


                    	        switch (alt43) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2580:11: (lv_actions_14_0= ruleKAction )
                    	    	    {
                    	    	    // InternalKGraph.g:2580:11: (lv_actions_14_0= ruleKAction )
                    	    	    // InternalKGraph.g:2581:12: lv_actions_14_0= ruleKAction
                    	    	    {

                    	    	    												newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getActionsKActionParserRuleCall_3_1_1_2_0());
                    	    	    											
                    	    	    pushFollow(FOLLOW_47);
                    	    	    lv_actions_14_0=ruleKAction();

                    	    	    state._fsp--;


                    	    	    												if (current==null) {
                    	    	    													current = createModelElementForParent(grammarAccess.getKSimpleRenderingRule());
                    	    	    												}
                    	    	    												add(
                    	    	    													current,
                    	    	    													"actions",
                    	    	    													lv_actions_14_0,
                    	    	    													"de.cau.cs.kieler.kgraph.text.KGraph.KAction");
                    	    	    												afterParserOrEnumRuleCall();
                    	    	    											

                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop43;
                    	        }
                    	    } while (true);


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalKGraph.g:2604:5: ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2604:5: ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) )
                    	    // InternalKGraph.g:2605:6: {...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 2)");
                    	    }
                    	    // InternalKGraph.g:2605:116: ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) )
                    	    // InternalKGraph.g:2606:7: ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 2);
                    	    						
                    	    // InternalKGraph.g:2609:10: ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) )
                    	    // InternalKGraph.g:2609:11: {...}? => ( (lv_placementData_15_0= ruleKPlacementData ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2609:20: ( (lv_placementData_15_0= ruleKPlacementData ) )
                    	    // InternalKGraph.g:2609:21: (lv_placementData_15_0= ruleKPlacementData )
                    	    {
                    	    // InternalKGraph.g:2609:21: (lv_placementData_15_0= ruleKPlacementData )
                    	    // InternalKGraph.g:2610:11: lv_placementData_15_0= ruleKPlacementData
                    	    {

                    	    											newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_2_0());
                    	    										
                    	    pushFollow(FOLLOW_45);
                    	    lv_placementData_15_0=ruleKPlacementData();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getKSimpleRenderingRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"placementData",
                    	    												lv_placementData_15_0,
                    	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPlacementData");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1());
                    					

                    }

                    otherlv_16=(Token)match(input,22,FOLLOW_2); 

                    				newLeafNode(otherlv_16, grammarAccess.getKSimpleRenderingAccess().getRightCurlyBracketKeyword_3_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKSimpleRendering"


    // $ANTLR start "entryRuleKContainerRendering"
    // InternalKGraph.g:2648:1: entryRuleKContainerRendering returns [EObject current=null] : iv_ruleKContainerRendering= ruleKContainerRendering EOF ;
    public final EObject entryRuleKContainerRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKContainerRendering = null;


        try {
            // InternalKGraph.g:2648:60: (iv_ruleKContainerRendering= ruleKContainerRendering EOF )
            // InternalKGraph.g:2649:2: iv_ruleKContainerRendering= ruleKContainerRendering EOF
            {
             newCompositeNode(grammarAccess.getKContainerRenderingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKContainerRendering=ruleKContainerRendering();

            state._fsp--;

             current =iv_ruleKContainerRendering; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKContainerRendering"


    // $ANTLR start "ruleKContainerRendering"
    // InternalKGraph.g:2655:1: ruleKContainerRendering returns [EObject current=null] : ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? ) ;
    public final EObject ruleKContainerRendering() throws RecognitionException {
        EObject current = null;

        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_21=null;
        EObject this_KRectangle_0 = null;

        EObject this_KRoundedRectangle_1 = null;

        EObject this_KEllipse_2 = null;

        EObject this_KArc_3 = null;

        EObject this_KCustomRendering_4 = null;

        EObject this_KImage_5 = null;

        AntlrDatatypeRuleToken lv_id_6_0 = null;

        EObject lv_persistentEntries_8_0 = null;

        EObject lv_styles_14_0 = null;

        EObject lv_actions_17_0 = null;

        EObject lv_placementData_18_0 = null;

        EObject lv_childPlacement_19_0 = null;

        EObject lv_children_20_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:2661:2: ( ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? ) )
            // InternalKGraph.g:2662:2: ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? )
            {
            // InternalKGraph.g:2662:2: ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? )
            // InternalKGraph.g:2663:3: (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )?
            {
            // InternalKGraph.g:2663:3: (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage )
            int alt46=6;
            switch ( input.LA(1) ) {
            case 54:
                {
                alt46=1;
                }
                break;
            case 55:
                {
                alt46=2;
                }
                break;
            case 56:
                {
                alt46=3;
                }
                break;
            case 57:
                {
                alt46=4;
                }
                break;
            case 58:
                {
                alt46=5;
                }
                break;
            case 59:
                {
                alt46=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // InternalKGraph.g:2664:4: this_KRectangle_0= ruleKRectangle
                    {

                    				newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKRectangleParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_42);
                    this_KRectangle_0=ruleKRectangle();

                    state._fsp--;


                    				current = this_KRectangle_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:2673:4: this_KRoundedRectangle_1= ruleKRoundedRectangle
                    {

                    				newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKRoundedRectangleParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_42);
                    this_KRoundedRectangle_1=ruleKRoundedRectangle();

                    state._fsp--;


                    				current = this_KRoundedRectangle_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:2682:4: this_KEllipse_2= ruleKEllipse
                    {

                    				newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKEllipseParserRuleCall_0_2());
                    			
                    pushFollow(FOLLOW_42);
                    this_KEllipse_2=ruleKEllipse();

                    state._fsp--;


                    				current = this_KEllipse_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:2691:4: this_KArc_3= ruleKArc
                    {

                    				newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKArcParserRuleCall_0_3());
                    			
                    pushFollow(FOLLOW_42);
                    this_KArc_3=ruleKArc();

                    state._fsp--;


                    				current = this_KArc_3;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 5 :
                    // InternalKGraph.g:2700:4: this_KCustomRendering_4= ruleKCustomRendering
                    {

                    				newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKCustomRenderingParserRuleCall_0_4());
                    			
                    pushFollow(FOLLOW_42);
                    this_KCustomRendering_4=ruleKCustomRendering();

                    state._fsp--;


                    				current = this_KCustomRendering_4;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 6 :
                    // InternalKGraph.g:2709:4: this_KImage_5= ruleKImage
                    {

                    				newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKImageParserRuleCall_0_5());
                    			
                    pushFollow(FOLLOW_42);
                    this_KImage_5=ruleKImage();

                    state._fsp--;


                    				current = this_KImage_5;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalKGraph.g:2718:3: ( (lv_id_6_0= ruleQualifiedID ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==RULE_ID) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalKGraph.g:2719:4: (lv_id_6_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:2719:4: (lv_id_6_0= ruleQualifiedID )
                    // InternalKGraph.g:2720:5: lv_id_6_0= ruleQualifiedID
                    {

                    					newCompositeNode(grammarAccess.getKContainerRenderingAccess().getIdQualifiedIDParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_43);
                    lv_id_6_0=ruleQualifiedID();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKContainerRenderingRule());
                    					}
                    					set(
                    						current,
                    						"id",
                    						lv_id_6_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:2737:3: (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==31) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalKGraph.g:2738:4: otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']'
                    {
                    otherlv_7=(Token)match(input,31,FOLLOW_25); 

                    				newLeafNode(otherlv_7, grammarAccess.getKContainerRenderingAccess().getLeftSquareBracketKeyword_2_0());
                    			
                    // InternalKGraph.g:2742:4: ( (lv_persistentEntries_8_0= ruleProperty ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==RULE_ID) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // InternalKGraph.g:2743:5: (lv_persistentEntries_8_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:2743:5: (lv_persistentEntries_8_0= ruleProperty )
                    	    // InternalKGraph.g:2744:6: lv_persistentEntries_8_0= ruleProperty
                    	    {

                    	    						newCompositeNode(grammarAccess.getKContainerRenderingAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_25);
                    	    lv_persistentEntries_8_0=ruleProperty();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKContainerRenderingRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"persistentEntries",
                    	    							lv_persistentEntries_8_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.Property");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,32,FOLLOW_44); 

                    				newLeafNode(otherlv_9, grammarAccess.getKContainerRenderingAccess().getRightSquareBracketKeyword_2_2());
                    			

                    }
                    break;

            }

            // InternalKGraph.g:2766:3: (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==21) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalKGraph.g:2767:4: otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}'
                    {
                    otherlv_10=(Token)match(input,21,FOLLOW_48); 

                    				newLeafNode(otherlv_10, grammarAccess.getKContainerRenderingAccess().getLeftCurlyBracketKeyword_3_0());
                    			
                    // InternalKGraph.g:2771:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) )
                    // InternalKGraph.g:2772:5: ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) )
                    {
                    // InternalKGraph.g:2772:5: ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) )
                    // InternalKGraph.g:2773:6: ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1());
                    					
                    // InternalKGraph.g:2776:6: ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* )
                    // InternalKGraph.g:2777:7: ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )*
                    {
                    // InternalKGraph.g:2777:7: ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )*
                    loop52:
                    do {
                        int alt52=5;
                        int LA52_0 = input.LA(1);

                        if ( LA52_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 0) ) {
                            alt52=1;
                        }
                        else if ( LA52_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 1) ) {
                            alt52=2;
                        }
                        else if ( ( LA52_0 >= 97 && LA52_0 <= 98 || LA52_0 == 106 || LA52_0 == 111 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 2) ) {
                            alt52=3;
                        }
                        else if ( LA52_0 == 93 && getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 3) ) {
                            alt52=4;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // InternalKGraph.g:2778:5: ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2778:5: ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) )
                    	    // InternalKGraph.g:2779:6: {...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 0)");
                    	    }
                    	    // InternalKGraph.g:2779:119: ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) )
                    	    // InternalKGraph.g:2780:7: ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 0);
                    	    						
                    	    // InternalKGraph.g:2783:10: ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) )
                    	    // InternalKGraph.g:2783:11: {...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2783:20: (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* )
                    	    // InternalKGraph.g:2783:21: otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )*
                    	    {
                    	    otherlv_12=(Token)match(input,48,FOLLOW_26); 

                    	    										newLeafNode(otherlv_12, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0());
                    	    									
                    	    otherlv_13=(Token)match(input,25,FOLLOW_49); 

                    	    										newLeafNode(otherlv_13, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1());
                    	    									
                    	    // InternalKGraph.g:2791:10: ( (lv_styles_14_0= ruleKStyle ) )*
                    	    loop50:
                    	    do {
                    	        int alt50=2;
                    	        int LA50_0 = input.LA(1);

                    	        if ( ((LA50_0>=68 && LA50_0<=83)||(LA50_0>=86 && LA50_0<=87)||(LA50_0>=89 && LA50_0<=90)) ) {
                    	            alt50=1;
                    	        }


                    	        switch (alt50) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2792:11: (lv_styles_14_0= ruleKStyle )
                    	    	    {
                    	    	    // InternalKGraph.g:2792:11: (lv_styles_14_0= ruleKStyle )
                    	    	    // InternalKGraph.g:2793:12: lv_styles_14_0= ruleKStyle
                    	    	    {

                    	    	    												newCompositeNode(grammarAccess.getKContainerRenderingAccess().getStylesKStyleParserRuleCall_3_1_0_2_0());
                    	    	    											
                    	    	    pushFollow(FOLLOW_49);
                    	    	    lv_styles_14_0=ruleKStyle();

                    	    	    state._fsp--;


                    	    	    												if (current==null) {
                    	    	    													current = createModelElementForParent(grammarAccess.getKContainerRenderingRule());
                    	    	    												}
                    	    	    												add(
                    	    	    													current,
                    	    	    													"styles",
                    	    	    													lv_styles_14_0,
                    	    	    													"de.cau.cs.kieler.kgraph.text.KGraph.KStyle");
                    	    	    												afterParserOrEnumRuleCall();
                    	    	    											

                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop50;
                    	        }
                    	    } while (true);


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalKGraph.g:2816:5: ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2816:5: ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) )
                    	    // InternalKGraph.g:2817:6: {...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 1)");
                    	    }
                    	    // InternalKGraph.g:2817:119: ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) )
                    	    // InternalKGraph.g:2818:7: ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 1);
                    	    						
                    	    // InternalKGraph.g:2821:10: ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) )
                    	    // InternalKGraph.g:2821:11: {...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2821:20: (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* )
                    	    // InternalKGraph.g:2821:21: otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )*
                    	    {
                    	    otherlv_15=(Token)match(input,49,FOLLOW_26); 

                    	    										newLeafNode(otherlv_15, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0());
                    	    									
                    	    otherlv_16=(Token)match(input,25,FOLLOW_50); 

                    	    										newLeafNode(otherlv_16, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1());
                    	    									
                    	    // InternalKGraph.g:2829:10: ( (lv_actions_17_0= ruleKAction ) )*
                    	    loop51:
                    	    do {
                    	        int alt51=2;
                    	        int LA51_0 = input.LA(1);

                    	        if ( ((LA51_0>=141 && LA51_0<=146)) ) {
                    	            alt51=1;
                    	        }


                    	        switch (alt51) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2830:11: (lv_actions_17_0= ruleKAction )
                    	    	    {
                    	    	    // InternalKGraph.g:2830:11: (lv_actions_17_0= ruleKAction )
                    	    	    // InternalKGraph.g:2831:12: lv_actions_17_0= ruleKAction
                    	    	    {

                    	    	    												newCompositeNode(grammarAccess.getKContainerRenderingAccess().getActionsKActionParserRuleCall_3_1_1_2_0());
                    	    	    											
                    	    	    pushFollow(FOLLOW_50);
                    	    	    lv_actions_17_0=ruleKAction();

                    	    	    state._fsp--;


                    	    	    												if (current==null) {
                    	    	    													current = createModelElementForParent(grammarAccess.getKContainerRenderingRule());
                    	    	    												}
                    	    	    												add(
                    	    	    													current,
                    	    	    													"actions",
                    	    	    													lv_actions_17_0,
                    	    	    													"de.cau.cs.kieler.kgraph.text.KGraph.KAction");
                    	    	    												afterParserOrEnumRuleCall();
                    	    	    											

                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop51;
                    	        }
                    	    } while (true);


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalKGraph.g:2854:5: ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2854:5: ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) )
                    	    // InternalKGraph.g:2855:6: {...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 2)");
                    	    }
                    	    // InternalKGraph.g:2855:119: ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) )
                    	    // InternalKGraph.g:2856:7: ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 2);
                    	    						
                    	    // InternalKGraph.g:2859:10: ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) )
                    	    // InternalKGraph.g:2859:11: {...}? => ( (lv_placementData_18_0= ruleKPlacementData ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2859:20: ( (lv_placementData_18_0= ruleKPlacementData ) )
                    	    // InternalKGraph.g:2859:21: (lv_placementData_18_0= ruleKPlacementData )
                    	    {
                    	    // InternalKGraph.g:2859:21: (lv_placementData_18_0= ruleKPlacementData )
                    	    // InternalKGraph.g:2860:11: lv_placementData_18_0= ruleKPlacementData
                    	    {

                    	    											newCompositeNode(grammarAccess.getKContainerRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_2_0());
                    	    										
                    	    pushFollow(FOLLOW_48);
                    	    lv_placementData_18_0=ruleKPlacementData();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getKContainerRenderingRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"placementData",
                    	    												lv_placementData_18_0,
                    	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPlacementData");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalKGraph.g:2882:5: ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2882:5: ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) )
                    	    // InternalKGraph.g:2883:6: {...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 3) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 3)");
                    	    }
                    	    // InternalKGraph.g:2883:119: ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) )
                    	    // InternalKGraph.g:2884:7: ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 3);
                    	    						
                    	    // InternalKGraph.g:2887:10: ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) )
                    	    // InternalKGraph.g:2887:11: {...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2887:20: ( (lv_childPlacement_19_0= ruleKPlacement ) )
                    	    // InternalKGraph.g:2887:21: (lv_childPlacement_19_0= ruleKPlacement )
                    	    {
                    	    // InternalKGraph.g:2887:21: (lv_childPlacement_19_0= ruleKPlacement )
                    	    // InternalKGraph.g:2888:11: lv_childPlacement_19_0= ruleKPlacement
                    	    {

                    	    											newCompositeNode(grammarAccess.getKContainerRenderingAccess().getChildPlacementKPlacementParserRuleCall_3_1_3_0());
                    	    										
                    	    pushFollow(FOLLOW_48);
                    	    lv_childPlacement_19_0=ruleKPlacement();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getKContainerRenderingRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"childPlacement",
                    	    												lv_childPlacement_19_0,
                    	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPlacement");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1());
                    					

                    }

                    // InternalKGraph.g:2917:4: ( (lv_children_20_0= ruleKRendering ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==50||(LA53_0>=52 && LA53_0<=59)||(LA53_0>=62 && LA53_0<=65)) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // InternalKGraph.g:2918:5: (lv_children_20_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:2918:5: (lv_children_20_0= ruleKRendering )
                    	    // InternalKGraph.g:2919:6: lv_children_20_0= ruleKRendering
                    	    {

                    	    						newCompositeNode(grammarAccess.getKContainerRenderingAccess().getChildrenKRenderingParserRuleCall_3_2_0());
                    	    					
                    	    pushFollow(FOLLOW_20);
                    	    lv_children_20_0=ruleKRendering();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKContainerRenderingRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"children",
                    	    							lv_children_20_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop53;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,22,FOLLOW_2); 

                    				newLeafNode(otherlv_21, grammarAccess.getKContainerRenderingAccess().getRightCurlyBracketKeyword_3_3());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKContainerRendering"


    // $ANTLR start "entryRuleKRenderingRef"
    // InternalKGraph.g:2945:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // InternalKGraph.g:2945:54: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // InternalKGraph.g:2946:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRenderingRef"


    // $ANTLR start "ruleKRenderingRef"
    // InternalKGraph.g:2952:1: ruleKRenderingRef returns [EObject current=null] : (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) ) ;
    public final EObject ruleKRenderingRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:2958:2: ( (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) ) )
            // InternalKGraph.g:2959:2: (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) )
            {
            // InternalKGraph.g:2959:2: (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) )
            // InternalKGraph.g:2960:3: otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,50,FOLLOW_51); 

            			newLeafNode(otherlv_0, grammarAccess.getKRenderingRefAccess().getKrenderingKeyword_0());
            		
            otherlv_1=(Token)match(input,51,FOLLOW_10); 

            			newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getAsteriskKeyword_1());
            		
            // InternalKGraph.g:2968:3: ( ( ruleQualifiedID ) )
            // InternalKGraph.g:2969:4: ( ruleQualifiedID )
            {
            // InternalKGraph.g:2969:4: ( ruleQualifiedID )
            // InternalKGraph.g:2970:5: ruleQualifiedID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKRenderingRefRule());
            					}
            				

            					newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_2_0());
            				
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRenderingRef"


    // $ANTLR start "entryRuleKChildArea"
    // InternalKGraph.g:2988:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // InternalKGraph.g:2988:51: (iv_ruleKChildArea= ruleKChildArea EOF )
            // InternalKGraph.g:2989:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKChildArea"


    // $ANTLR start "ruleKChildArea"
    // InternalKGraph.g:2995:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'kchildArea' ) ;
    public final EObject ruleKChildArea() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:3001:2: ( ( () otherlv_1= 'kchildArea' ) )
            // InternalKGraph.g:3002:2: ( () otherlv_1= 'kchildArea' )
            {
            // InternalKGraph.g:3002:2: ( () otherlv_1= 'kchildArea' )
            // InternalKGraph.g:3003:3: () otherlv_1= 'kchildArea'
            {
            // InternalKGraph.g:3003:3: ()
            // InternalKGraph.g:3004:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,52,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getKchildAreaKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKChildArea"


    // $ANTLR start "entryRuleKText"
    // InternalKGraph.g:3018:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // InternalKGraph.g:3018:46: (iv_ruleKText= ruleKText EOF )
            // InternalKGraph.g:3019:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKText"


    // $ANTLR start "ruleKText"
    // InternalKGraph.g:3025:1: ruleKText returns [EObject current=null] : ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? ) ;
    public final EObject ruleKText() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_text_3_0=null;
        Token otherlv_4=null;
        Token lv_cursorSelectable_5_0=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalKGraph.g:3031:2: ( ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? ) )
            // InternalKGraph.g:3032:2: ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? )
            {
            // InternalKGraph.g:3032:2: ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? )
            // InternalKGraph.g:3033:3: () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )?
            {
            // InternalKGraph.g:3033:3: ()
            // InternalKGraph.g:3034:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKTextAccess().getKTextAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,53,FOLLOW_52); 

            			newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getKtextKeyword_1());
            		
            // InternalKGraph.g:3044:3: (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==24) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalKGraph.g:3045:4: otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,24,FOLLOW_53); 

                    				newLeafNode(otherlv_2, grammarAccess.getKTextAccess().getLeftParenthesisKeyword_2_0());
                    			
                    // InternalKGraph.g:3049:4: ( (lv_text_3_0= RULE_STRING ) )
                    // InternalKGraph.g:3050:5: (lv_text_3_0= RULE_STRING )
                    {
                    // InternalKGraph.g:3050:5: (lv_text_3_0= RULE_STRING )
                    // InternalKGraph.g:3051:6: lv_text_3_0= RULE_STRING
                    {
                    lv_text_3_0=(Token)match(input,RULE_STRING,FOLLOW_54); 

                    						newLeafNode(lv_text_3_0, grammarAccess.getKTextAccess().getTextSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKTextRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"text",
                    							lv_text_3_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.STRING");
                    					

                    }


                    }

                    // InternalKGraph.g:3067:4: (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==47) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // InternalKGraph.g:3068:5: otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) )
                            {
                            otherlv_4=(Token)match(input,47,FOLLOW_55); 

                            					newLeafNode(otherlv_4, grammarAccess.getKTextAccess().getCommaKeyword_2_2_0());
                            				
                            // InternalKGraph.g:3072:5: ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) )
                            // InternalKGraph.g:3073:6: (lv_cursorSelectable_5_0= RULE_BOOLEAN )
                            {
                            // InternalKGraph.g:3073:6: (lv_cursorSelectable_5_0= RULE_BOOLEAN )
                            // InternalKGraph.g:3074:7: lv_cursorSelectable_5_0= RULE_BOOLEAN
                            {
                            lv_cursorSelectable_5_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_13); 

                            							newLeafNode(lv_cursorSelectable_5_0, grammarAccess.getKTextAccess().getCursorSelectableBOOLEANTerminalRuleCall_2_2_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getKTextRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"cursorSelectable",
                            								lv_cursorSelectable_5_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.BOOLEAN");
                            						

                            }


                            }


                            }
                            break;

                    }

                    otherlv_6=(Token)match(input,27,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getRightParenthesisKeyword_2_3());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKText"


    // $ANTLR start "entryRuleKRectangle"
    // InternalKGraph.g:3100:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // InternalKGraph.g:3100:51: (iv_ruleKRectangle= ruleKRectangle EOF )
            // InternalKGraph.g:3101:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRectangle"


    // $ANTLR start "ruleKRectangle"
    // InternalKGraph.g:3107:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'krectangle' ) ;
    public final EObject ruleKRectangle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:3113:2: ( ( () otherlv_1= 'krectangle' ) )
            // InternalKGraph.g:3114:2: ( () otherlv_1= 'krectangle' )
            {
            // InternalKGraph.g:3114:2: ( () otherlv_1= 'krectangle' )
            // InternalKGraph.g:3115:3: () otherlv_1= 'krectangle'
            {
            // InternalKGraph.g:3115:3: ()
            // InternalKGraph.g:3116:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,54,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getKrectangleKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRectangle"


    // $ANTLR start "entryRuleKRoundedRectangle"
    // InternalKGraph.g:3130:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // InternalKGraph.g:3130:58: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // InternalKGraph.g:3131:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRoundedRectangle"


    // $ANTLR start "ruleKRoundedRectangle"
    // InternalKGraph.g:3137:1: ruleKRoundedRectangle returns [EObject current=null] : ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? ) ;
    public final EObject ruleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_cornerWidth_3_0 = null;

        AntlrDatatypeRuleToken lv_cornerHeight_5_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:3143:2: ( ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? ) )
            // InternalKGraph.g:3144:2: ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? )
            {
            // InternalKGraph.g:3144:2: ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? )
            // InternalKGraph.g:3145:3: () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )?
            {
            // InternalKGraph.g:3145:3: ()
            // InternalKGraph.g:3146:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,55,FOLLOW_52); 

            			newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getKroundedRectangleKeyword_1());
            		
            // InternalKGraph.g:3156:3: (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==24) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalKGraph.g:3157:4: otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,24,FOLLOW_28); 

                    				newLeafNode(otherlv_2, grammarAccess.getKRoundedRectangleAccess().getLeftParenthesisKeyword_2_0());
                    			
                    // InternalKGraph.g:3161:4: ( (lv_cornerWidth_3_0= ruleFloat ) )
                    // InternalKGraph.g:3162:5: (lv_cornerWidth_3_0= ruleFloat )
                    {
                    // InternalKGraph.g:3162:5: (lv_cornerWidth_3_0= ruleFloat )
                    // InternalKGraph.g:3163:6: lv_cornerWidth_3_0= ruleFloat
                    {

                    						newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthFloatParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_41);
                    lv_cornerWidth_3_0=ruleFloat();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    						}
                    						set(
                    							current,
                    							"cornerWidth",
                    							lv_cornerWidth_3_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_4=(Token)match(input,47,FOLLOW_28); 

                    				newLeafNode(otherlv_4, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_2_2());
                    			
                    // InternalKGraph.g:3184:4: ( (lv_cornerHeight_5_0= ruleFloat ) )
                    // InternalKGraph.g:3185:5: (lv_cornerHeight_5_0= ruleFloat )
                    {
                    // InternalKGraph.g:3185:5: (lv_cornerHeight_5_0= ruleFloat )
                    // InternalKGraph.g:3186:6: lv_cornerHeight_5_0= ruleFloat
                    {

                    						newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightFloatParserRuleCall_2_3_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_cornerHeight_5_0=ruleFloat();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    						}
                    						set(
                    							current,
                    							"cornerHeight",
                    							lv_cornerHeight_5_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_6=(Token)match(input,27,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getRightParenthesisKeyword_2_4());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRoundedRectangle"


    // $ANTLR start "entryRuleKEllipse"
    // InternalKGraph.g:3212:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // InternalKGraph.g:3212:49: (iv_ruleKEllipse= ruleKEllipse EOF )
            // InternalKGraph.g:3213:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKEllipse"


    // $ANTLR start "ruleKEllipse"
    // InternalKGraph.g:3219:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'kellipse' ) ;
    public final EObject ruleKEllipse() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:3225:2: ( ( () otherlv_1= 'kellipse' ) )
            // InternalKGraph.g:3226:2: ( () otherlv_1= 'kellipse' )
            {
            // InternalKGraph.g:3226:2: ( () otherlv_1= 'kellipse' )
            // InternalKGraph.g:3227:3: () otherlv_1= 'kellipse'
            {
            // InternalKGraph.g:3227:3: ()
            // InternalKGraph.g:3228:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,56,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getKellipseKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKEllipse"


    // $ANTLR start "entryRuleKArc"
    // InternalKGraph.g:3242:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // InternalKGraph.g:3242:45: (iv_ruleKArc= ruleKArc EOF )
            // InternalKGraph.g:3243:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKArc"


    // $ANTLR start "ruleKArc"
    // InternalKGraph.g:3249:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) ) ;
    public final EObject ruleKArc() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_startAngle_3_0=null;
        Token otherlv_4=null;
        Token lv_arcAngle_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_arcType_7_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:3255:2: ( ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) ) )
            // InternalKGraph.g:3256:2: ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) )
            {
            // InternalKGraph.g:3256:2: ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) )
            // InternalKGraph.g:3257:3: () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' )
            {
            // InternalKGraph.g:3257:3: ()
            // InternalKGraph.g:3258:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKArcAccess().getKArcAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,57,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getKarcKeyword_1());
            		
            // InternalKGraph.g:3268:3: (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' )
            // InternalKGraph.g:3269:4: otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')'
            {
            otherlv_2=(Token)match(input,24,FOLLOW_56); 

            				newLeafNode(otherlv_2, grammarAccess.getKArcAccess().getLeftParenthesisKeyword_2_0());
            			
            // InternalKGraph.g:3273:4: ( (lv_startAngle_3_0= RULE_DEGREES ) )
            // InternalKGraph.g:3274:5: (lv_startAngle_3_0= RULE_DEGREES )
            {
            // InternalKGraph.g:3274:5: (lv_startAngle_3_0= RULE_DEGREES )
            // InternalKGraph.g:3275:6: lv_startAngle_3_0= RULE_DEGREES
            {
            lv_startAngle_3_0=(Token)match(input,RULE_DEGREES,FOLLOW_41); 

            						newLeafNode(lv_startAngle_3_0, grammarAccess.getKArcAccess().getStartAngleDEGREESTerminalRuleCall_2_1_0());
            					

            						if (current==null) {
            							current = createModelElement(grammarAccess.getKArcRule());
            						}
            						setWithLastConsumed(
            							current,
            							"startAngle",
            							lv_startAngle_3_0,
            							"de.cau.cs.kieler.kgraph.text.KGraph.DEGREES");
            					

            }


            }

            otherlv_4=(Token)match(input,47,FOLLOW_56); 

            				newLeafNode(otherlv_4, grammarAccess.getKArcAccess().getCommaKeyword_2_2());
            			
            // InternalKGraph.g:3295:4: ( (lv_arcAngle_5_0= RULE_DEGREES ) )
            // InternalKGraph.g:3296:5: (lv_arcAngle_5_0= RULE_DEGREES )
            {
            // InternalKGraph.g:3296:5: (lv_arcAngle_5_0= RULE_DEGREES )
            // InternalKGraph.g:3297:6: lv_arcAngle_5_0= RULE_DEGREES
            {
            lv_arcAngle_5_0=(Token)match(input,RULE_DEGREES,FOLLOW_54); 

            						newLeafNode(lv_arcAngle_5_0, grammarAccess.getKArcAccess().getArcAngleDEGREESTerminalRuleCall_2_3_0());
            					

            						if (current==null) {
            							current = createModelElement(grammarAccess.getKArcRule());
            						}
            						setWithLastConsumed(
            							current,
            							"arcAngle",
            							lv_arcAngle_5_0,
            							"de.cau.cs.kieler.kgraph.text.KGraph.DEGREES");
            					

            }


            }

            // InternalKGraph.g:3313:4: (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==47) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalKGraph.g:3314:5: otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) )
                    {
                    otherlv_6=(Token)match(input,47,FOLLOW_57); 

                    					newLeafNode(otherlv_6, grammarAccess.getKArcAccess().getCommaKeyword_2_4_0());
                    				
                    // InternalKGraph.g:3318:5: ( (lv_arcType_7_0= ruleArc ) )
                    // InternalKGraph.g:3319:6: (lv_arcType_7_0= ruleArc )
                    {
                    // InternalKGraph.g:3319:6: (lv_arcType_7_0= ruleArc )
                    // InternalKGraph.g:3320:7: lv_arcType_7_0= ruleArc
                    {

                    							newCompositeNode(grammarAccess.getKArcAccess().getArcTypeArcEnumRuleCall_2_4_1_0());
                    						
                    pushFollow(FOLLOW_13);
                    lv_arcType_7_0=ruleArc();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKArcRule());
                    							}
                    							set(
                    								current,
                    								"arcType",
                    								lv_arcType_7_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.Arc");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,27,FOLLOW_2); 

            				newLeafNode(otherlv_8, grammarAccess.getKArcAccess().getRightParenthesisKeyword_2_5());
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKArc"


    // $ANTLR start "entryRuleKCustomRendering"
    // InternalKGraph.g:3347:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // InternalKGraph.g:3347:57: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // InternalKGraph.g:3348:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKCustomRendering"


    // $ANTLR start "ruleKCustomRendering"
    // InternalKGraph.g:3354:1: ruleKCustomRendering returns [EObject current=null] : ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? ) ;
    public final EObject ruleKCustomRendering() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_bundleName_3_0 = null;

        AntlrDatatypeRuleToken lv_className_5_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:3360:2: ( ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? ) )
            // InternalKGraph.g:3361:2: ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? )
            {
            // InternalKGraph.g:3361:2: ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? )
            // InternalKGraph.g:3362:3: () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )?
            {
            // InternalKGraph.g:3362:3: ()
            // InternalKGraph.g:3363:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKCustomRenderingAccess().getKCustomRenderingAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,58,FOLLOW_52); 

            			newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getKcustomRenderingKeyword_1());
            		
            // InternalKGraph.g:3373:3: (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==24) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalKGraph.g:3374:4: otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,24,FOLLOW_10); 

                    				newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getLeftParenthesisKeyword_2_0());
                    			
                    // InternalKGraph.g:3378:4: ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )?
                    int alt59=2;
                    alt59 = dfa59.predict(input);
                    switch (alt59) {
                        case 1 :
                            // InternalKGraph.g:3379:5: ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':'
                            {
                            // InternalKGraph.g:3379:5: ( (lv_bundleName_3_0= ruleQualifiedID ) )
                            // InternalKGraph.g:3380:6: (lv_bundleName_3_0= ruleQualifiedID )
                            {
                            // InternalKGraph.g:3380:6: (lv_bundleName_3_0= ruleQualifiedID )
                            // InternalKGraph.g:3381:7: lv_bundleName_3_0= ruleQualifiedID
                            {

                            							newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameQualifiedIDParserRuleCall_2_1_0_0());
                            						
                            pushFollow(FOLLOW_26);
                            lv_bundleName_3_0=ruleQualifiedID();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                            							}
                            							set(
                            								current,
                            								"bundleName",
                            								lv_bundleName_3_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }

                            otherlv_4=(Token)match(input,25,FOLLOW_10); 

                            					newLeafNode(otherlv_4, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_1_1());
                            				

                            }
                            break;

                    }

                    // InternalKGraph.g:3403:4: ( (lv_className_5_0= ruleQualifiedID ) )
                    // InternalKGraph.g:3404:5: (lv_className_5_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:3404:5: (lv_className_5_0= ruleQualifiedID )
                    // InternalKGraph.g:3405:6: lv_className_5_0= ruleQualifiedID
                    {

                    						newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameQualifiedIDParserRuleCall_2_2_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_className_5_0=ruleQualifiedID();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    						}
                    						set(
                    							current,
                    							"className",
                    							lv_className_5_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_6=(Token)match(input,27,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getKCustomRenderingAccess().getRightParenthesisKeyword_2_3());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKCustomRendering"


    // $ANTLR start "entryRuleKImage"
    // InternalKGraph.g:3431:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // InternalKGraph.g:3431:47: (iv_ruleKImage= ruleKImage EOF )
            // InternalKGraph.g:3432:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKImage"


    // $ANTLR start "ruleKImage"
    // InternalKGraph.g:3438:1: ruleKImage returns [EObject current=null] : ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? ) ;
    public final EObject ruleKImage() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_imagePath_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_bundleName_3_0 = null;

        EObject lv_clipShape_7_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:3444:2: ( ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? ) )
            // InternalKGraph.g:3445:2: ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? )
            {
            // InternalKGraph.g:3445:2: ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? )
            // InternalKGraph.g:3446:3: () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )?
            {
            // InternalKGraph.g:3446:3: ()
            // InternalKGraph.g:3447:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKImageAccess().getKImageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,59,FOLLOW_52); 

            			newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getKimageKeyword_1());
            		
            // InternalKGraph.g:3457:3: (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==24) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalKGraph.g:3458:4: otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')'
                    {
                    otherlv_2=(Token)match(input,24,FOLLOW_58); 

                    				newLeafNode(otherlv_2, grammarAccess.getKImageAccess().getLeftParenthesisKeyword_2_0());
                    			
                    // InternalKGraph.g:3462:4: ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==RULE_ID) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // InternalKGraph.g:3463:5: ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':'
                            {
                            // InternalKGraph.g:3463:5: ( (lv_bundleName_3_0= ruleQualifiedID ) )
                            // InternalKGraph.g:3464:6: (lv_bundleName_3_0= ruleQualifiedID )
                            {
                            // InternalKGraph.g:3464:6: (lv_bundleName_3_0= ruleQualifiedID )
                            // InternalKGraph.g:3465:7: lv_bundleName_3_0= ruleQualifiedID
                            {

                            							newCompositeNode(grammarAccess.getKImageAccess().getBundleNameQualifiedIDParserRuleCall_2_1_0_0());
                            						
                            pushFollow(FOLLOW_26);
                            lv_bundleName_3_0=ruleQualifiedID();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getKImageRule());
                            							}
                            							set(
                            								current,
                            								"bundleName",
                            								lv_bundleName_3_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }

                            otherlv_4=(Token)match(input,25,FOLLOW_53); 

                            					newLeafNode(otherlv_4, grammarAccess.getKImageAccess().getColonKeyword_2_1_1());
                            				

                            }
                            break;

                    }

                    // InternalKGraph.g:3487:4: ( (lv_imagePath_5_0= RULE_STRING ) )
                    // InternalKGraph.g:3488:5: (lv_imagePath_5_0= RULE_STRING )
                    {
                    // InternalKGraph.g:3488:5: (lv_imagePath_5_0= RULE_STRING )
                    // InternalKGraph.g:3489:6: lv_imagePath_5_0= RULE_STRING
                    {
                    lv_imagePath_5_0=(Token)match(input,RULE_STRING,FOLLOW_59); 

                    						newLeafNode(lv_imagePath_5_0, grammarAccess.getKImageAccess().getImagePathSTRINGTerminalRuleCall_2_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKImageRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"imagePath",
                    							lv_imagePath_5_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.STRING");
                    					

                    }


                    }

                    // InternalKGraph.g:3505:4: (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==60) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // InternalKGraph.g:3506:5: otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) )
                            {
                            otherlv_6=(Token)match(input,60,FOLLOW_60); 

                            					newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getClipShapeKeyword_2_3_0());
                            				
                            // InternalKGraph.g:3510:5: ( (lv_clipShape_7_0= ruleKRendering ) )
                            // InternalKGraph.g:3511:6: (lv_clipShape_7_0= ruleKRendering )
                            {
                            // InternalKGraph.g:3511:6: (lv_clipShape_7_0= ruleKRendering )
                            // InternalKGraph.g:3512:7: lv_clipShape_7_0= ruleKRendering
                            {

                            							newCompositeNode(grammarAccess.getKImageAccess().getClipShapeKRenderingParserRuleCall_2_3_1_0());
                            						
                            pushFollow(FOLLOW_13);
                            lv_clipShape_7_0=ruleKRendering();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getKImageRule());
                            							}
                            							set(
                            								current,
                            								"clipShape",
                            								lv_clipShape_7_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }

                    otherlv_8=(Token)match(input,27,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getKImageAccess().getRightParenthesisKeyword_2_4());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKImage"


    // $ANTLR start "entryRuleKPolyline"
    // InternalKGraph.g:3539:1: entryRuleKPolyline returns [EObject current=null] : iv_ruleKPolyline= ruleKPolyline EOF ;
    public final EObject entryRuleKPolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline = null;


        try {
            // InternalKGraph.g:3539:50: (iv_ruleKPolyline= ruleKPolyline EOF )
            // InternalKGraph.g:3540:2: iv_ruleKPolyline= ruleKPolyline EOF
            {
             newCompositeNode(grammarAccess.getKPolylineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPolyline=ruleKPolyline();

            state._fsp--;

             current =iv_ruleKPolyline; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPolyline"


    // $ANTLR start "ruleKPolyline"
    // InternalKGraph.g:3546:1: ruleKPolyline returns [EObject current=null] : ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? ) ;
    public final EObject ruleKPolyline() throws RecognitionException {
        EObject current = null;

        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_23=null;
        Token otherlv_26=null;
        EObject this_KSimplePolyline_0 = null;

        EObject this_KPolygon_1 = null;

        EObject this_KRoundedBendsPolyline_2 = null;

        EObject this_KSpline_3 = null;

        AntlrDatatypeRuleToken lv_id_4_0 = null;

        EObject lv_persistentEntries_6_0 = null;

        EObject lv_points_12_0 = null;

        EObject lv_points_14_0 = null;

        EObject lv_styles_17_0 = null;

        EObject lv_actions_20_0 = null;

        EObject lv_placementData_21_0 = null;

        EObject lv_childPlacement_22_0 = null;

        EObject lv_junctionPointRendering_24_0 = null;

        EObject lv_children_25_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:3552:2: ( ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? ) )
            // InternalKGraph.g:3553:2: ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? )
            {
            // InternalKGraph.g:3553:2: ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? )
            // InternalKGraph.g:3554:3: (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )?
            {
            // InternalKGraph.g:3554:3: (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline )
            int alt64=4;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt64=1;
                }
                break;
            case 63:
                {
                alt64=2;
                }
                break;
            case 64:
                {
                alt64=3;
                }
                break;
            case 65:
                {
                alt64=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // InternalKGraph.g:3555:4: this_KSimplePolyline_0= ruleKSimplePolyline
                    {

                    				newCompositeNode(grammarAccess.getKPolylineAccess().getKSimplePolylineParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_42);
                    this_KSimplePolyline_0=ruleKSimplePolyline();

                    state._fsp--;


                    				current = this_KSimplePolyline_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:3564:4: this_KPolygon_1= ruleKPolygon
                    {

                    				newCompositeNode(grammarAccess.getKPolylineAccess().getKPolygonParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_42);
                    this_KPolygon_1=ruleKPolygon();

                    state._fsp--;


                    				current = this_KPolygon_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:3573:4: this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline
                    {

                    				newCompositeNode(grammarAccess.getKPolylineAccess().getKRoundedBendsPolylineParserRuleCall_0_2());
                    			
                    pushFollow(FOLLOW_42);
                    this_KRoundedBendsPolyline_2=ruleKRoundedBendsPolyline();

                    state._fsp--;


                    				current = this_KRoundedBendsPolyline_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:3582:4: this_KSpline_3= ruleKSpline
                    {

                    				newCompositeNode(grammarAccess.getKPolylineAccess().getKSplineParserRuleCall_0_3());
                    			
                    pushFollow(FOLLOW_42);
                    this_KSpline_3=ruleKSpline();

                    state._fsp--;


                    				current = this_KSpline_3;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalKGraph.g:3591:3: ( (lv_id_4_0= ruleQualifiedID ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==RULE_ID) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalKGraph.g:3592:4: (lv_id_4_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:3592:4: (lv_id_4_0= ruleQualifiedID )
                    // InternalKGraph.g:3593:5: lv_id_4_0= ruleQualifiedID
                    {

                    					newCompositeNode(grammarAccess.getKPolylineAccess().getIdQualifiedIDParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_43);
                    lv_id_4_0=ruleQualifiedID();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    					}
                    					set(
                    						current,
                    						"id",
                    						lv_id_4_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:3610:3: (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==31) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalKGraph.g:3611:4: otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']'
                    {
                    otherlv_5=(Token)match(input,31,FOLLOW_25); 

                    				newLeafNode(otherlv_5, grammarAccess.getKPolylineAccess().getLeftSquareBracketKeyword_2_0());
                    			
                    // InternalKGraph.g:3615:4: ( (lv_persistentEntries_6_0= ruleProperty ) )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==RULE_ID) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // InternalKGraph.g:3616:5: (lv_persistentEntries_6_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:3616:5: (lv_persistentEntries_6_0= ruleProperty )
                    	    // InternalKGraph.g:3617:6: lv_persistentEntries_6_0= ruleProperty
                    	    {

                    	    						newCompositeNode(grammarAccess.getKPolylineAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_25);
                    	    lv_persistentEntries_6_0=ruleProperty();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"persistentEntries",
                    	    							lv_persistentEntries_6_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.Property");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,32,FOLLOW_44); 

                    				newLeafNode(otherlv_7, grammarAccess.getKPolylineAccess().getRightSquareBracketKeyword_2_2());
                    			

                    }
                    break;

            }

            // InternalKGraph.g:3639:3: (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==21) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalKGraph.g:3640:4: otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}'
                    {
                    otherlv_8=(Token)match(input,21,FOLLOW_61); 

                    				newLeafNode(otherlv_8, grammarAccess.getKPolylineAccess().getLeftCurlyBracketKeyword_3_0());
                    			
                    // InternalKGraph.g:3644:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) )
                    // InternalKGraph.g:3645:5: ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) )
                    {
                    // InternalKGraph.g:3645:5: ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) )
                    // InternalKGraph.g:3646:6: ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    					
                    // InternalKGraph.g:3649:6: ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* )
                    // InternalKGraph.g:3650:7: ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )*
                    {
                    // InternalKGraph.g:3650:7: ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )*
                    loop72:
                    do {
                        int alt72=6;
                        int LA72_0 = input.LA(1);

                        if ( LA72_0 == 45 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 0) ) {
                            alt72=1;
                        }
                        else if ( LA72_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 1) ) {
                            alt72=2;
                        }
                        else if ( LA72_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 2) ) {
                            alt72=3;
                        }
                        else if ( ( LA72_0 >= 97 && LA72_0 <= 98 || LA72_0 == 106 || LA72_0 == 111 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 3) ) {
                            alt72=4;
                        }
                        else if ( LA72_0 == 93 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 4) ) {
                            alt72=5;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // InternalKGraph.g:3651:5: ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3651:5: ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) )
                    	    // InternalKGraph.g:3652:6: {...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 0)");
                    	    }
                    	    // InternalKGraph.g:3652:109: ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) )
                    	    // InternalKGraph.g:3653:7: ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 0);
                    	    						
                    	    // InternalKGraph.g:3656:10: ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) )
                    	    // InternalKGraph.g:3656:11: {...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3656:20: (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? )
                    	    // InternalKGraph.g:3656:21: otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )?
                    	    {
                    	    otherlv_10=(Token)match(input,45,FOLLOW_26); 

                    	    										newLeafNode(otherlv_10, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0());
                    	    									
                    	    otherlv_11=(Token)match(input,25,FOLLOW_62); 

                    	    										newLeafNode(otherlv_11, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1());
                    	    									
                    	    // InternalKGraph.g:3664:10: ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )?
                    	    int alt69=2;
                    	    int LA69_0 = input.LA(1);

                    	    if ( ((LA69_0>=43 && LA69_0<=44)) ) {
                    	        alt69=1;
                    	    }
                    	    switch (alt69) {
                    	        case 1 :
                    	            // InternalKGraph.g:3665:11: ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )*
                    	            {
                    	            // InternalKGraph.g:3665:11: ( (lv_points_12_0= ruleKPosition ) )
                    	            // InternalKGraph.g:3666:12: (lv_points_12_0= ruleKPosition )
                    	            {
                    	            // InternalKGraph.g:3666:12: (lv_points_12_0= ruleKPosition )
                    	            // InternalKGraph.g:3667:13: lv_points_12_0= ruleKPosition
                    	            {

                    	            													newCompositeNode(grammarAccess.getKPolylineAccess().getPointsKPositionParserRuleCall_3_1_0_2_0_0());
                    	            												
                    	            pushFollow(FOLLOW_63);
                    	            lv_points_12_0=ruleKPosition();

                    	            state._fsp--;


                    	            													if (current==null) {
                    	            														current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	            													}
                    	            													add(
                    	            														current,
                    	            														"points",
                    	            														lv_points_12_0,
                    	            														"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
                    	            													afterParserOrEnumRuleCall();
                    	            												

                    	            }


                    	            }

                    	            // InternalKGraph.g:3684:11: (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )*
                    	            loop68:
                    	            do {
                    	                int alt68=2;
                    	                int LA68_0 = input.LA(1);

                    	                if ( (LA68_0==46) ) {
                    	                    alt68=1;
                    	                }


                    	                switch (alt68) {
                    	            	case 1 :
                    	            	    // InternalKGraph.g:3685:12: otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) )
                    	            	    {
                    	            	    otherlv_13=(Token)match(input,46,FOLLOW_64); 

                    	            	    												newLeafNode(otherlv_13, grammarAccess.getKPolylineAccess().getSemicolonKeyword_3_1_0_2_1_0());
                    	            	    											
                    	            	    // InternalKGraph.g:3689:12: ( (lv_points_14_0= ruleKPosition ) )
                    	            	    // InternalKGraph.g:3690:13: (lv_points_14_0= ruleKPosition )
                    	            	    {
                    	            	    // InternalKGraph.g:3690:13: (lv_points_14_0= ruleKPosition )
                    	            	    // InternalKGraph.g:3691:14: lv_points_14_0= ruleKPosition
                    	            	    {

                    	            	    														newCompositeNode(grammarAccess.getKPolylineAccess().getPointsKPositionParserRuleCall_3_1_0_2_1_1_0());
                    	            	    													
                    	            	    pushFollow(FOLLOW_63);
                    	            	    lv_points_14_0=ruleKPosition();

                    	            	    state._fsp--;


                    	            	    														if (current==null) {
                    	            	    															current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	            	    														}
                    	            	    														add(
                    	            	    															current,
                    	            	    															"points",
                    	            	    															lv_points_14_0,
                    	            	    															"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
                    	            	    														afterParserOrEnumRuleCall();
                    	            	    													

                    	            	    }


                    	            	    }


                    	            	    }
                    	            	    break;

                    	            	default :
                    	            	    break loop68;
                    	                }
                    	            } while (true);


                    	            }
                    	            break;

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalKGraph.g:3716:5: ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3716:5: ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) )
                    	    // InternalKGraph.g:3717:6: {...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 1)");
                    	    }
                    	    // InternalKGraph.g:3717:109: ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) )
                    	    // InternalKGraph.g:3718:7: ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 1);
                    	    						
                    	    // InternalKGraph.g:3721:10: ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) )
                    	    // InternalKGraph.g:3721:11: {...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3721:20: (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* )
                    	    // InternalKGraph.g:3721:21: otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )*
                    	    {
                    	    otherlv_15=(Token)match(input,48,FOLLOW_26); 

                    	    										newLeafNode(otherlv_15, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0());
                    	    									
                    	    otherlv_16=(Token)match(input,25,FOLLOW_65); 

                    	    										newLeafNode(otherlv_16, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1());
                    	    									
                    	    // InternalKGraph.g:3729:10: ( (lv_styles_17_0= ruleKStyle ) )*
                    	    loop70:
                    	    do {
                    	        int alt70=2;
                    	        int LA70_0 = input.LA(1);

                    	        if ( ((LA70_0>=68 && LA70_0<=83)||(LA70_0>=86 && LA70_0<=87)||(LA70_0>=89 && LA70_0<=90)) ) {
                    	            alt70=1;
                    	        }


                    	        switch (alt70) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:3730:11: (lv_styles_17_0= ruleKStyle )
                    	    	    {
                    	    	    // InternalKGraph.g:3730:11: (lv_styles_17_0= ruleKStyle )
                    	    	    // InternalKGraph.g:3731:12: lv_styles_17_0= ruleKStyle
                    	    	    {

                    	    	    												newCompositeNode(grammarAccess.getKPolylineAccess().getStylesKStyleParserRuleCall_3_1_1_2_0());
                    	    	    											
                    	    	    pushFollow(FOLLOW_65);
                    	    	    lv_styles_17_0=ruleKStyle();

                    	    	    state._fsp--;


                    	    	    												if (current==null) {
                    	    	    													current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	    	    												}
                    	    	    												add(
                    	    	    													current,
                    	    	    													"styles",
                    	    	    													lv_styles_17_0,
                    	    	    													"de.cau.cs.kieler.kgraph.text.KGraph.KStyle");
                    	    	    												afterParserOrEnumRuleCall();
                    	    	    											

                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop70;
                    	        }
                    	    } while (true);


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalKGraph.g:3754:5: ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3754:5: ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) )
                    	    // InternalKGraph.g:3755:6: {...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 2)");
                    	    }
                    	    // InternalKGraph.g:3755:109: ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) )
                    	    // InternalKGraph.g:3756:7: ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 2);
                    	    						
                    	    // InternalKGraph.g:3759:10: ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) )
                    	    // InternalKGraph.g:3759:11: {...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3759:20: (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* )
                    	    // InternalKGraph.g:3759:21: otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )*
                    	    {
                    	    otherlv_18=(Token)match(input,49,FOLLOW_26); 

                    	    										newLeafNode(otherlv_18, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0());
                    	    									
                    	    otherlv_19=(Token)match(input,25,FOLLOW_66); 

                    	    										newLeafNode(otherlv_19, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1());
                    	    									
                    	    // InternalKGraph.g:3767:10: ( (lv_actions_20_0= ruleKAction ) )*
                    	    loop71:
                    	    do {
                    	        int alt71=2;
                    	        int LA71_0 = input.LA(1);

                    	        if ( ((LA71_0>=141 && LA71_0<=146)) ) {
                    	            alt71=1;
                    	        }


                    	        switch (alt71) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:3768:11: (lv_actions_20_0= ruleKAction )
                    	    	    {
                    	    	    // InternalKGraph.g:3768:11: (lv_actions_20_0= ruleKAction )
                    	    	    // InternalKGraph.g:3769:12: lv_actions_20_0= ruleKAction
                    	    	    {

                    	    	    												newCompositeNode(grammarAccess.getKPolylineAccess().getActionsKActionParserRuleCall_3_1_2_2_0());
                    	    	    											
                    	    	    pushFollow(FOLLOW_66);
                    	    	    lv_actions_20_0=ruleKAction();

                    	    	    state._fsp--;


                    	    	    												if (current==null) {
                    	    	    													current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	    	    												}
                    	    	    												add(
                    	    	    													current,
                    	    	    													"actions",
                    	    	    													lv_actions_20_0,
                    	    	    													"de.cau.cs.kieler.kgraph.text.KGraph.KAction");
                    	    	    												afterParserOrEnumRuleCall();
                    	    	    											

                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop71;
                    	        }
                    	    } while (true);


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalKGraph.g:3792:5: ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3792:5: ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) )
                    	    // InternalKGraph.g:3793:6: {...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 3) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 3)");
                    	    }
                    	    // InternalKGraph.g:3793:109: ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) )
                    	    // InternalKGraph.g:3794:7: ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 3);
                    	    						
                    	    // InternalKGraph.g:3797:10: ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) )
                    	    // InternalKGraph.g:3797:11: {...}? => ( (lv_placementData_21_0= ruleKPlacementData ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3797:20: ( (lv_placementData_21_0= ruleKPlacementData ) )
                    	    // InternalKGraph.g:3797:21: (lv_placementData_21_0= ruleKPlacementData )
                    	    {
                    	    // InternalKGraph.g:3797:21: (lv_placementData_21_0= ruleKPlacementData )
                    	    // InternalKGraph.g:3798:11: lv_placementData_21_0= ruleKPlacementData
                    	    {

                    	    											newCompositeNode(grammarAccess.getKPolylineAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_3_0());
                    	    										
                    	    pushFollow(FOLLOW_61);
                    	    lv_placementData_21_0=ruleKPlacementData();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"placementData",
                    	    												lv_placementData_21_0,
                    	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPlacementData");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 5 :
                    	    // InternalKGraph.g:3820:5: ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3820:5: ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) )
                    	    // InternalKGraph.g:3821:6: {...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 4) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 4)");
                    	    }
                    	    // InternalKGraph.g:3821:109: ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) )
                    	    // InternalKGraph.g:3822:7: ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 4);
                    	    						
                    	    // InternalKGraph.g:3825:10: ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) )
                    	    // InternalKGraph.g:3825:11: {...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3825:20: ( (lv_childPlacement_22_0= ruleKPlacement ) )
                    	    // InternalKGraph.g:3825:21: (lv_childPlacement_22_0= ruleKPlacement )
                    	    {
                    	    // InternalKGraph.g:3825:21: (lv_childPlacement_22_0= ruleKPlacement )
                    	    // InternalKGraph.g:3826:11: lv_childPlacement_22_0= ruleKPlacement
                    	    {

                    	    											newCompositeNode(grammarAccess.getKPolylineAccess().getChildPlacementKPlacementParserRuleCall_3_1_4_0());
                    	    										
                    	    pushFollow(FOLLOW_61);
                    	    lv_childPlacement_22_0=ruleKPlacement();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"childPlacement",
                    	    												lv_childPlacement_22_0,
                    	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPlacement");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop72;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    					

                    }

                    // InternalKGraph.g:3855:4: (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==61) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // InternalKGraph.g:3856:5: otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) )
                            {
                            otherlv_23=(Token)match(input,61,FOLLOW_60); 

                            					newLeafNode(otherlv_23, grammarAccess.getKPolylineAccess().getJunctionKeyword_3_2_0());
                            				
                            // InternalKGraph.g:3860:5: ( (lv_junctionPointRendering_24_0= ruleKRendering ) )
                            // InternalKGraph.g:3861:6: (lv_junctionPointRendering_24_0= ruleKRendering )
                            {
                            // InternalKGraph.g:3861:6: (lv_junctionPointRendering_24_0= ruleKRendering )
                            // InternalKGraph.g:3862:7: lv_junctionPointRendering_24_0= ruleKRendering
                            {

                            							newCompositeNode(grammarAccess.getKPolylineAccess().getJunctionPointRenderingKRenderingParserRuleCall_3_2_1_0());
                            						
                            pushFollow(FOLLOW_20);
                            lv_junctionPointRendering_24_0=ruleKRendering();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getKPolylineRule());
                            							}
                            							set(
                            								current,
                            								"junctionPointRendering",
                            								lv_junctionPointRendering_24_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalKGraph.g:3880:4: ( (lv_children_25_0= ruleKRendering ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==50||(LA74_0>=52 && LA74_0<=59)||(LA74_0>=62 && LA74_0<=65)) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // InternalKGraph.g:3881:5: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:3881:5: (lv_children_25_0= ruleKRendering )
                    	    // InternalKGraph.g:3882:6: lv_children_25_0= ruleKRendering
                    	    {

                    	    						newCompositeNode(grammarAccess.getKPolylineAccess().getChildrenKRenderingParserRuleCall_3_3_0());
                    	    					
                    	    pushFollow(FOLLOW_20);
                    	    lv_children_25_0=ruleKRendering();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKPolylineRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"children",
                    	    							lv_children_25_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop74;
                        }
                    } while (true);

                    otherlv_26=(Token)match(input,22,FOLLOW_2); 

                    				newLeafNode(otherlv_26, grammarAccess.getKPolylineAccess().getRightCurlyBracketKeyword_3_4());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPolyline"


    // $ANTLR start "entryRuleKSimplePolyline"
    // InternalKGraph.g:3908:1: entryRuleKSimplePolyline returns [EObject current=null] : iv_ruleKSimplePolyline= ruleKSimplePolyline EOF ;
    public final EObject entryRuleKSimplePolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSimplePolyline = null;


        try {
            // InternalKGraph.g:3908:56: (iv_ruleKSimplePolyline= ruleKSimplePolyline EOF )
            // InternalKGraph.g:3909:2: iv_ruleKSimplePolyline= ruleKSimplePolyline EOF
            {
             newCompositeNode(grammarAccess.getKSimplePolylineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKSimplePolyline=ruleKSimplePolyline();

            state._fsp--;

             current =iv_ruleKSimplePolyline; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKSimplePolyline"


    // $ANTLR start "ruleKSimplePolyline"
    // InternalKGraph.g:3915:1: ruleKSimplePolyline returns [EObject current=null] : ( () otherlv_1= 'kpolyline' ) ;
    public final EObject ruleKSimplePolyline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:3921:2: ( ( () otherlv_1= 'kpolyline' ) )
            // InternalKGraph.g:3922:2: ( () otherlv_1= 'kpolyline' )
            {
            // InternalKGraph.g:3922:2: ( () otherlv_1= 'kpolyline' )
            // InternalKGraph.g:3923:3: () otherlv_1= 'kpolyline'
            {
            // InternalKGraph.g:3923:3: ()
            // InternalKGraph.g:3924:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKSimplePolylineAccess().getKPolylineAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,62,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKSimplePolylineAccess().getKpolylineKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKSimplePolyline"


    // $ANTLR start "entryRuleKPolygon"
    // InternalKGraph.g:3938:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // InternalKGraph.g:3938:49: (iv_ruleKPolygon= ruleKPolygon EOF )
            // InternalKGraph.g:3939:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPolygon"


    // $ANTLR start "ruleKPolygon"
    // InternalKGraph.g:3945:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'kpolygon' ) ;
    public final EObject ruleKPolygon() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:3951:2: ( ( () otherlv_1= 'kpolygon' ) )
            // InternalKGraph.g:3952:2: ( () otherlv_1= 'kpolygon' )
            {
            // InternalKGraph.g:3952:2: ( () otherlv_1= 'kpolygon' )
            // InternalKGraph.g:3953:3: () otherlv_1= 'kpolygon'
            {
            // InternalKGraph.g:3953:3: ()
            // InternalKGraph.g:3954:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,63,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getKpolygonKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPolygon"


    // $ANTLR start "entryRuleKRoundedBendsPolyline"
    // InternalKGraph.g:3968:1: entryRuleKRoundedBendsPolyline returns [EObject current=null] : iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF ;
    public final EObject entryRuleKRoundedBendsPolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedBendsPolyline = null;


        try {
            // InternalKGraph.g:3968:62: (iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF )
            // InternalKGraph.g:3969:2: iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF
            {
             newCompositeNode(grammarAccess.getKRoundedBendsPolylineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRoundedBendsPolyline=ruleKRoundedBendsPolyline();

            state._fsp--;

             current =iv_ruleKRoundedBendsPolyline; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRoundedBendsPolyline"


    // $ANTLR start "ruleKRoundedBendsPolyline"
    // InternalKGraph.g:3975:1: ruleKRoundedBendsPolyline returns [EObject current=null] : ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? ) ;
    public final EObject ruleKRoundedBendsPolyline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_bendRadius_3_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:3981:2: ( ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? ) )
            // InternalKGraph.g:3982:2: ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? )
            {
            // InternalKGraph.g:3982:2: ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? )
            // InternalKGraph.g:3983:3: () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )?
            {
            // InternalKGraph.g:3983:3: ()
            // InternalKGraph.g:3984:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKRoundedBendsPolylineAccess().getKRoundedBendsPolylineAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,64,FOLLOW_52); 

            			newLeafNode(otherlv_1, grammarAccess.getKRoundedBendsPolylineAccess().getKroundedPolylineKeyword_1());
            		
            // InternalKGraph.g:3994:3: (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==24) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalKGraph.g:3995:4: otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')'
                    {
                    otherlv_2=(Token)match(input,24,FOLLOW_28); 

                    				newLeafNode(otherlv_2, grammarAccess.getKRoundedBendsPolylineAccess().getLeftParenthesisKeyword_2_0());
                    			
                    // InternalKGraph.g:3999:4: ( (lv_bendRadius_3_0= ruleFloat ) )
                    // InternalKGraph.g:4000:5: (lv_bendRadius_3_0= ruleFloat )
                    {
                    // InternalKGraph.g:4000:5: (lv_bendRadius_3_0= ruleFloat )
                    // InternalKGraph.g:4001:6: lv_bendRadius_3_0= ruleFloat
                    {

                    						newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getBendRadiusFloatParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_bendRadius_3_0=ruleFloat();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                    						}
                    						set(
                    							current,
                    							"bendRadius",
                    							lv_bendRadius_3_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_4=(Token)match(input,27,FOLLOW_2); 

                    				newLeafNode(otherlv_4, grammarAccess.getKRoundedBendsPolylineAccess().getRightParenthesisKeyword_2_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRoundedBendsPolyline"


    // $ANTLR start "entryRuleKSpline"
    // InternalKGraph.g:4027:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // InternalKGraph.g:4027:48: (iv_ruleKSpline= ruleKSpline EOF )
            // InternalKGraph.g:4028:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKSpline"


    // $ANTLR start "ruleKSpline"
    // InternalKGraph.g:4034:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'kspline' ) ;
    public final EObject ruleKSpline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:4040:2: ( ( () otherlv_1= 'kspline' ) )
            // InternalKGraph.g:4041:2: ( () otherlv_1= 'kspline' )
            {
            // InternalKGraph.g:4041:2: ( () otherlv_1= 'kspline' )
            // InternalKGraph.g:4042:3: () otherlv_1= 'kspline'
            {
            // InternalKGraph.g:4042:3: ()
            // InternalKGraph.g:4043:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKSplineAccess().getKSplineAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,65,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getKsplineKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKSpline"


    // $ANTLR start "entryRuleKStyle"
    // InternalKGraph.g:4057:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // InternalKGraph.g:4057:47: (iv_ruleKStyle= ruleKStyle EOF )
            // InternalKGraph.g:4058:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKStyle"


    // $ANTLR start "ruleKStyle"
    // InternalKGraph.g:4064:1: ruleKStyle returns [EObject current=null] : ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? ) ;
    public final EObject ruleKStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_16=null;
        EObject this_KColoring_0 = null;

        EObject this_KFontBold_1 = null;

        EObject this_KFontItalic_2 = null;

        EObject this_KFontName_3 = null;

        EObject this_KFontSize_4 = null;

        EObject this_KTextUnderline_5 = null;

        EObject this_KHorizontalAlignment_6 = null;

        EObject this_KVerticalAlignment_7 = null;

        EObject this_KInvisibility_8 = null;

        EObject this_KLineCap_9 = null;

        EObject this_KLineJoin_10 = null;

        EObject this_KLineStyle_11 = null;

        EObject this_KLineWidth_12 = null;

        EObject this_KRotation_13 = null;

        EObject this_KShadow_14 = null;

        EObject this_KStyleRef_15 = null;

        AntlrDatatypeRuleToken lv_modifierId_17_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:4070:2: ( ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? ) )
            // InternalKGraph.g:4071:2: ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? )
            {
            // InternalKGraph.g:4071:2: ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? )
            // InternalKGraph.g:4072:3: (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )?
            {
            // InternalKGraph.g:4072:3: (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef )
            int alt77=16;
            alt77 = dfa77.predict(input);
            switch (alt77) {
                case 1 :
                    // InternalKGraph.g:4073:4: this_KColoring_0= ruleKColoring
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKColoringParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_67);
                    this_KColoring_0=ruleKColoring();

                    state._fsp--;


                    				current = this_KColoring_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:4082:4: this_KFontBold_1= ruleKFontBold
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKFontBoldParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_67);
                    this_KFontBold_1=ruleKFontBold();

                    state._fsp--;


                    				current = this_KFontBold_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:4091:4: this_KFontItalic_2= ruleKFontItalic
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKFontItalicParserRuleCall_0_2());
                    			
                    pushFollow(FOLLOW_67);
                    this_KFontItalic_2=ruleKFontItalic();

                    state._fsp--;


                    				current = this_KFontItalic_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:4100:4: this_KFontName_3= ruleKFontName
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKFontNameParserRuleCall_0_3());
                    			
                    pushFollow(FOLLOW_67);
                    this_KFontName_3=ruleKFontName();

                    state._fsp--;


                    				current = this_KFontName_3;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 5 :
                    // InternalKGraph.g:4109:4: this_KFontSize_4= ruleKFontSize
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKFontSizeParserRuleCall_0_4());
                    			
                    pushFollow(FOLLOW_67);
                    this_KFontSize_4=ruleKFontSize();

                    state._fsp--;


                    				current = this_KFontSize_4;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 6 :
                    // InternalKGraph.g:4118:4: this_KTextUnderline_5= ruleKTextUnderline
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKTextUnderlineParserRuleCall_0_5());
                    			
                    pushFollow(FOLLOW_67);
                    this_KTextUnderline_5=ruleKTextUnderline();

                    state._fsp--;


                    				current = this_KTextUnderline_5;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 7 :
                    // InternalKGraph.g:4127:4: this_KHorizontalAlignment_6= ruleKHorizontalAlignment
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_0_6());
                    			
                    pushFollow(FOLLOW_67);
                    this_KHorizontalAlignment_6=ruleKHorizontalAlignment();

                    state._fsp--;


                    				current = this_KHorizontalAlignment_6;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 8 :
                    // InternalKGraph.g:4136:4: this_KVerticalAlignment_7= ruleKVerticalAlignment
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_0_7());
                    			
                    pushFollow(FOLLOW_67);
                    this_KVerticalAlignment_7=ruleKVerticalAlignment();

                    state._fsp--;


                    				current = this_KVerticalAlignment_7;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 9 :
                    // InternalKGraph.g:4145:4: this_KInvisibility_8= ruleKInvisibility
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKInvisibilityParserRuleCall_0_8());
                    			
                    pushFollow(FOLLOW_67);
                    this_KInvisibility_8=ruleKInvisibility();

                    state._fsp--;


                    				current = this_KInvisibility_8;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 10 :
                    // InternalKGraph.g:4154:4: this_KLineCap_9= ruleKLineCap
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKLineCapParserRuleCall_0_9());
                    			
                    pushFollow(FOLLOW_67);
                    this_KLineCap_9=ruleKLineCap();

                    state._fsp--;


                    				current = this_KLineCap_9;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 11 :
                    // InternalKGraph.g:4163:4: this_KLineJoin_10= ruleKLineJoin
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKLineJoinParserRuleCall_0_10());
                    			
                    pushFollow(FOLLOW_67);
                    this_KLineJoin_10=ruleKLineJoin();

                    state._fsp--;


                    				current = this_KLineJoin_10;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 12 :
                    // InternalKGraph.g:4172:4: this_KLineStyle_11= ruleKLineStyle
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_0_11());
                    			
                    pushFollow(FOLLOW_67);
                    this_KLineStyle_11=ruleKLineStyle();

                    state._fsp--;


                    				current = this_KLineStyle_11;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 13 :
                    // InternalKGraph.g:4181:4: this_KLineWidth_12= ruleKLineWidth
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_0_12());
                    			
                    pushFollow(FOLLOW_67);
                    this_KLineWidth_12=ruleKLineWidth();

                    state._fsp--;


                    				current = this_KLineWidth_12;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 14 :
                    // InternalKGraph.g:4190:4: this_KRotation_13= ruleKRotation
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKRotationParserRuleCall_0_13());
                    			
                    pushFollow(FOLLOW_67);
                    this_KRotation_13=ruleKRotation();

                    state._fsp--;


                    				current = this_KRotation_13;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 15 :
                    // InternalKGraph.g:4199:4: this_KShadow_14= ruleKShadow
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKShadowParserRuleCall_0_14());
                    			
                    pushFollow(FOLLOW_67);
                    this_KShadow_14=ruleKShadow();

                    state._fsp--;


                    				current = this_KShadow_14;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 16 :
                    // InternalKGraph.g:4208:4: this_KStyleRef_15= ruleKStyleRef
                    {

                    				newCompositeNode(grammarAccess.getKStyleAccess().getKStyleRefParserRuleCall_0_15());
                    			
                    pushFollow(FOLLOW_67);
                    this_KStyleRef_15=ruleKStyleRef();

                    state._fsp--;


                    				current = this_KStyleRef_15;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalKGraph.g:4217:3: (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==66) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalKGraph.g:4218:4: otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) )
                    {
                    otherlv_16=(Token)match(input,66,FOLLOW_10); 

                    				newLeafNode(otherlv_16, grammarAccess.getKStyleAccess().getModifierKeyword_1_0());
                    			
                    // InternalKGraph.g:4222:4: ( (lv_modifierId_17_0= ruleQualifiedID ) )
                    // InternalKGraph.g:4223:5: (lv_modifierId_17_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:4223:5: (lv_modifierId_17_0= ruleQualifiedID )
                    // InternalKGraph.g:4224:6: lv_modifierId_17_0= ruleQualifiedID
                    {

                    						newCompositeNode(grammarAccess.getKStyleAccess().getModifierIdQualifiedIDParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_modifierId_17_0=ruleQualifiedID();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKStyleRule());
                    						}
                    						set(
                    							current,
                    							"modifierId",
                    							lv_modifierId_17_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKStyle"


    // $ANTLR start "entryRuleKColoring"
    // InternalKGraph.g:4246:1: entryRuleKColoring returns [EObject current=null] : iv_ruleKColoring= ruleKColoring EOF ;
    public final EObject entryRuleKColoring() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKColoring = null;


        try {
            // InternalKGraph.g:4246:50: (iv_ruleKColoring= ruleKColoring EOF )
            // InternalKGraph.g:4247:2: iv_ruleKColoring= ruleKColoring EOF
            {
             newCompositeNode(grammarAccess.getKColoringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKColoring=ruleKColoring();

            state._fsp--;

             current =iv_ruleKColoring; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKColoring"


    // $ANTLR start "ruleKColoring"
    // InternalKGraph.g:4253:1: ruleKColoring returns [EObject current=null] : ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) ) ;
    public final EObject ruleKColoring() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token lv_alpha_6_0=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token lv_targetAlpha_10_0=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject this_KForeground_0 = null;

        EObject this_KBackground_1 = null;

        EObject lv_color_4_0 = null;

        EObject lv_targetColor_8_0 = null;

        AntlrDatatypeRuleToken lv_gradientAngle_12_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:4259:2: ( ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) ) )
            // InternalKGraph.g:4260:2: ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) )
            {
            // InternalKGraph.g:4260:2: ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) )
            // InternalKGraph.g:4261:3: (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) )
            {
            // InternalKGraph.g:4261:3: (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground )
            int alt79=2;
            switch ( input.LA(1) ) {
            case 68:
                {
                switch ( input.LA(2) ) {
                case 69:
                    {
                    int LA79_2 = input.LA(3);

                    if ( (LA79_2==71) ) {
                        alt79=2;
                    }
                    else if ( (LA79_2==70) ) {
                        alt79=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 79, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 71:
                    {
                    alt79=2;
                    }
                    break;
                case 70:
                    {
                    alt79=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 79, 1, input);

                    throw nvae;
                }

                }
                break;
            case 69:
                {
                int LA79_2 = input.LA(2);

                if ( (LA79_2==71) ) {
                    alt79=2;
                }
                else if ( (LA79_2==70) ) {
                    alt79=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 79, 2, input);

                    throw nvae;
                }
                }
                break;
            case 70:
                {
                alt79=1;
                }
                break;
            case 71:
                {
                alt79=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }

            switch (alt79) {
                case 1 :
                    // InternalKGraph.g:4262:4: this_KForeground_0= ruleKForeground
                    {

                    				newCompositeNode(grammarAccess.getKColoringAccess().getKForegroundParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_22);
                    this_KForeground_0=ruleKForeground();

                    state._fsp--;


                    				current = this_KForeground_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:4271:4: this_KBackground_1= ruleKBackground
                    {

                    				newCompositeNode(grammarAccess.getKColoringAccess().getKBackgroundParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_22);
                    this_KBackground_1=ruleKBackground();

                    state._fsp--;


                    				current = this_KBackground_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            otherlv_2=(Token)match(input,30,FOLLOW_68); 

            			newLeafNode(otherlv_2, grammarAccess.getKColoringAccess().getEqualsSignKeyword_1());
            		
            // InternalKGraph.g:4284:3: (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==67) ) {
                alt84=1;
            }
            else if ( ((LA84_0>=RULE_RED && LA84_0<=RULE_BLUE)) ) {
                alt84=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // InternalKGraph.g:4285:4: otherlv_3= 'null'
                    {
                    otherlv_3=(Token)match(input,67,FOLLOW_2); 

                    				newLeafNode(otherlv_3, grammarAccess.getKColoringAccess().getNullKeyword_2_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:4290:4: ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? )
                    {
                    // InternalKGraph.g:4290:4: ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? )
                    // InternalKGraph.g:4291:5: ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )?
                    {
                    // InternalKGraph.g:4291:5: ( (lv_color_4_0= ruleKColor ) )
                    // InternalKGraph.g:4292:6: (lv_color_4_0= ruleKColor )
                    {
                    // InternalKGraph.g:4292:6: (lv_color_4_0= ruleKColor )
                    // InternalKGraph.g:4293:7: lv_color_4_0= ruleKColor
                    {

                    							newCompositeNode(grammarAccess.getKColoringAccess().getColorKColorParserRuleCall_2_1_0_0());
                    						
                    pushFollow(FOLLOW_69);
                    lv_color_4_0=ruleKColor();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKColoringRule());
                    							}
                    							set(
                    								current,
                    								"color",
                    								lv_color_4_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.KColor");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalKGraph.g:4310:5: (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==47) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // InternalKGraph.g:4311:6: otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) )
                            {
                            otherlv_5=(Token)match(input,47,FOLLOW_70); 

                            						newLeafNode(otherlv_5, grammarAccess.getKColoringAccess().getCommaKeyword_2_1_1_0());
                            					
                            // InternalKGraph.g:4315:6: ( (lv_alpha_6_0= RULE_ALPHA ) )
                            // InternalKGraph.g:4316:7: (lv_alpha_6_0= RULE_ALPHA )
                            {
                            // InternalKGraph.g:4316:7: (lv_alpha_6_0= RULE_ALPHA )
                            // InternalKGraph.g:4317:8: lv_alpha_6_0= RULE_ALPHA
                            {
                            lv_alpha_6_0=(Token)match(input,RULE_ALPHA,FOLLOW_71); 

                            								newLeafNode(lv_alpha_6_0, grammarAccess.getKColoringAccess().getAlphaALPHATerminalRuleCall_2_1_1_1_0());
                            							

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getKColoringRule());
                            								}
                            								setWithLastConsumed(
                            									current,
                            									"alpha",
                            									lv_alpha_6_0,
                            									"de.cau.cs.kieler.kgraph.text.KGraph.ALPHA");
                            							

                            }


                            }


                            }
                            break;

                    }

                    // InternalKGraph.g:4334:5: (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==26) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // InternalKGraph.g:4335:6: otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )?
                            {
                            otherlv_7=(Token)match(input,26,FOLLOW_68); 

                            						newLeafNode(otherlv_7, grammarAccess.getKColoringAccess().getHyphenMinusGreaterThanSignKeyword_2_1_2_0());
                            					
                            // InternalKGraph.g:4339:6: ( (lv_targetColor_8_0= ruleKColor ) )
                            // InternalKGraph.g:4340:7: (lv_targetColor_8_0= ruleKColor )
                            {
                            // InternalKGraph.g:4340:7: (lv_targetColor_8_0= ruleKColor )
                            // InternalKGraph.g:4341:8: lv_targetColor_8_0= ruleKColor
                            {

                            								newCompositeNode(grammarAccess.getKColoringAccess().getTargetColorKColorParserRuleCall_2_1_2_1_0());
                            							
                            pushFollow(FOLLOW_72);
                            lv_targetColor_8_0=ruleKColor();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getKColoringRule());
                            								}
                            								set(
                            									current,
                            									"targetColor",
                            									lv_targetColor_8_0,
                            									"de.cau.cs.kieler.kgraph.text.KGraph.KColor");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }

                            // InternalKGraph.g:4358:6: (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )?
                            int alt81=2;
                            int LA81_0 = input.LA(1);

                            if ( (LA81_0==47) ) {
                                alt81=1;
                            }
                            switch (alt81) {
                                case 1 :
                                    // InternalKGraph.g:4359:7: otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) )
                                    {
                                    otherlv_9=(Token)match(input,47,FOLLOW_70); 

                                    							newLeafNode(otherlv_9, grammarAccess.getKColoringAccess().getCommaKeyword_2_1_2_2_0());
                                    						
                                    // InternalKGraph.g:4363:7: ( (lv_targetAlpha_10_0= RULE_ALPHA ) )
                                    // InternalKGraph.g:4364:8: (lv_targetAlpha_10_0= RULE_ALPHA )
                                    {
                                    // InternalKGraph.g:4364:8: (lv_targetAlpha_10_0= RULE_ALPHA )
                                    // InternalKGraph.g:4365:9: lv_targetAlpha_10_0= RULE_ALPHA
                                    {
                                    lv_targetAlpha_10_0=(Token)match(input,RULE_ALPHA,FOLLOW_52); 

                                    									newLeafNode(lv_targetAlpha_10_0, grammarAccess.getKColoringAccess().getTargetAlphaALPHATerminalRuleCall_2_1_2_2_1_0());
                                    								

                                    									if (current==null) {
                                    										current = createModelElement(grammarAccess.getKColoringRule());
                                    									}
                                    									setWithLastConsumed(
                                    										current,
                                    										"targetAlpha",
                                    										lv_targetAlpha_10_0,
                                    										"de.cau.cs.kieler.kgraph.text.KGraph.ALPHA");
                                    								

                                    }


                                    }


                                    }
                                    break;

                            }

                            // InternalKGraph.g:4382:6: (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )?
                            int alt82=2;
                            int LA82_0 = input.LA(1);

                            if ( (LA82_0==24) ) {
                                alt82=1;
                            }
                            switch (alt82) {
                                case 1 :
                                    // InternalKGraph.g:4383:7: otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')'
                                    {
                                    otherlv_11=(Token)match(input,24,FOLLOW_28); 

                                    							newLeafNode(otherlv_11, grammarAccess.getKColoringAccess().getLeftParenthesisKeyword_2_1_2_3_0());
                                    						
                                    // InternalKGraph.g:4387:7: ( (lv_gradientAngle_12_0= ruleFloat ) )
                                    // InternalKGraph.g:4388:8: (lv_gradientAngle_12_0= ruleFloat )
                                    {
                                    // InternalKGraph.g:4388:8: (lv_gradientAngle_12_0= ruleFloat )
                                    // InternalKGraph.g:4389:9: lv_gradientAngle_12_0= ruleFloat
                                    {

                                    									newCompositeNode(grammarAccess.getKColoringAccess().getGradientAngleFloatParserRuleCall_2_1_2_3_1_0());
                                    								
                                    pushFollow(FOLLOW_13);
                                    lv_gradientAngle_12_0=ruleFloat();

                                    state._fsp--;


                                    									if (current==null) {
                                    										current = createModelElementForParent(grammarAccess.getKColoringRule());
                                    									}
                                    									set(
                                    										current,
                                    										"gradientAngle",
                                    										lv_gradientAngle_12_0,
                                    										"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                                    									afterParserOrEnumRuleCall();
                                    								

                                    }


                                    }

                                    otherlv_13=(Token)match(input,27,FOLLOW_2); 

                                    							newLeafNode(otherlv_13, grammarAccess.getKColoringAccess().getRightParenthesisKeyword_2_1_2_3_2());
                                    						

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKColoring"


    // $ANTLR start "entryRuleKForeground"
    // InternalKGraph.g:4418:1: entryRuleKForeground returns [EObject current=null] : iv_ruleKForeground= ruleKForeground EOF ;
    public final EObject entryRuleKForeground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForeground = null;


        try {
            // InternalKGraph.g:4418:52: (iv_ruleKForeground= ruleKForeground EOF )
            // InternalKGraph.g:4419:2: iv_ruleKForeground= ruleKForeground EOF
            {
             newCompositeNode(grammarAccess.getKForegroundRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKForeground=ruleKForeground();

            state._fsp--;

             current =iv_ruleKForeground; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKForeground"


    // $ANTLR start "ruleKForeground"
    // InternalKGraph.g:4425:1: ruleKForeground returns [EObject current=null] : ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' ) ;
    public final EObject ruleKForeground() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_1_0=null;
        Token lv_selection_2_0=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalKGraph.g:4431:2: ( ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' ) )
            // InternalKGraph.g:4432:2: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' )
            {
            // InternalKGraph.g:4432:2: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' )
            // InternalKGraph.g:4433:3: () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground'
            {
            // InternalKGraph.g:4433:3: ()
            // InternalKGraph.g:4434:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKForegroundAccess().getKForegroundAction_0(),
            					current);
            			

            }

            // InternalKGraph.g:4440:3: ( (lv_propagateToChildren_1_0= 'propagate' ) )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==68) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalKGraph.g:4441:4: (lv_propagateToChildren_1_0= 'propagate' )
                    {
                    // InternalKGraph.g:4441:4: (lv_propagateToChildren_1_0= 'propagate' )
                    // InternalKGraph.g:4442:5: lv_propagateToChildren_1_0= 'propagate'
                    {
                    lv_propagateToChildren_1_0=(Token)match(input,68,FOLLOW_73); 

                    					newLeafNode(lv_propagateToChildren_1_0, grammarAccess.getKForegroundAccess().getPropagateToChildrenPropagateKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKForegroundRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4454:3: ( (lv_selection_2_0= 'selection' ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==69) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalKGraph.g:4455:4: (lv_selection_2_0= 'selection' )
                    {
                    // InternalKGraph.g:4455:4: (lv_selection_2_0= 'selection' )
                    // InternalKGraph.g:4456:5: lv_selection_2_0= 'selection'
                    {
                    lv_selection_2_0=(Token)match(input,69,FOLLOW_74); 

                    					newLeafNode(lv_selection_2_0, grammarAccess.getKForegroundAccess().getSelectionSelectionKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKForegroundRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,70,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getKForegroundAccess().getForegroundKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKForeground"


    // $ANTLR start "entryRuleKBackground"
    // InternalKGraph.g:4476:1: entryRuleKBackground returns [EObject current=null] : iv_ruleKBackground= ruleKBackground EOF ;
    public final EObject entryRuleKBackground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackground = null;


        try {
            // InternalKGraph.g:4476:52: (iv_ruleKBackground= ruleKBackground EOF )
            // InternalKGraph.g:4477:2: iv_ruleKBackground= ruleKBackground EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKBackground=ruleKBackground();

            state._fsp--;

             current =iv_ruleKBackground; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKBackground"


    // $ANTLR start "ruleKBackground"
    // InternalKGraph.g:4483:1: ruleKBackground returns [EObject current=null] : ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' ) ;
    public final EObject ruleKBackground() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_1_0=null;
        Token lv_selection_2_0=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalKGraph.g:4489:2: ( ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' ) )
            // InternalKGraph.g:4490:2: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' )
            {
            // InternalKGraph.g:4490:2: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' )
            // InternalKGraph.g:4491:3: () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background'
            {
            // InternalKGraph.g:4491:3: ()
            // InternalKGraph.g:4492:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKBackgroundAccess().getKBackgroundAction_0(),
            					current);
            			

            }

            // InternalKGraph.g:4498:3: ( (lv_propagateToChildren_1_0= 'propagate' ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==68) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalKGraph.g:4499:4: (lv_propagateToChildren_1_0= 'propagate' )
                    {
                    // InternalKGraph.g:4499:4: (lv_propagateToChildren_1_0= 'propagate' )
                    // InternalKGraph.g:4500:5: lv_propagateToChildren_1_0= 'propagate'
                    {
                    lv_propagateToChildren_1_0=(Token)match(input,68,FOLLOW_75); 

                    					newLeafNode(lv_propagateToChildren_1_0, grammarAccess.getKBackgroundAccess().getPropagateToChildrenPropagateKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKBackgroundRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4512:3: ( (lv_selection_2_0= 'selection' ) )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==69) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalKGraph.g:4513:4: (lv_selection_2_0= 'selection' )
                    {
                    // InternalKGraph.g:4513:4: (lv_selection_2_0= 'selection' )
                    // InternalKGraph.g:4514:5: lv_selection_2_0= 'selection'
                    {
                    lv_selection_2_0=(Token)match(input,69,FOLLOW_76); 

                    					newLeafNode(lv_selection_2_0, grammarAccess.getKBackgroundAccess().getSelectionSelectionKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKBackgroundRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,71,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getKBackgroundAccess().getBackgroundKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKBackground"


    // $ANTLR start "entryRuleKFontBold"
    // InternalKGraph.g:4534:1: entryRuleKFontBold returns [EObject current=null] : iv_ruleKFontBold= ruleKFontBold EOF ;
    public final EObject entryRuleKFontBold() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontBold = null;


        try {
            // InternalKGraph.g:4534:50: (iv_ruleKFontBold= ruleKFontBold EOF )
            // InternalKGraph.g:4535:2: iv_ruleKFontBold= ruleKFontBold EOF
            {
             newCompositeNode(grammarAccess.getKFontBoldRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKFontBold=ruleKFontBold();

            state._fsp--;

             current =iv_ruleKFontBold; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKFontBold"


    // $ANTLR start "ruleKFontBold"
    // InternalKGraph.g:4541:1: ruleKFontBold returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleKFontBold() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_bold_4_0=null;


        	enterRule();

        try {
            // InternalKGraph.g:4547:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) ) )
            // InternalKGraph.g:4548:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) )
            {
            // InternalKGraph.g:4548:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) )
            // InternalKGraph.g:4549:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) )
            {
            // InternalKGraph.g:4549:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==68) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // InternalKGraph.g:4550:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4550:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4551:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_77); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontBoldAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontBoldRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4563:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==69) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // InternalKGraph.g:4564:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4564:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4565:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_78); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKFontBoldAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontBoldRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,72,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKFontBoldAccess().getBoldKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_55); 

            			newLeafNode(otherlv_3, grammarAccess.getKFontBoldAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:4585:3: ( (lv_bold_4_0= RULE_BOOLEAN ) )
            // InternalKGraph.g:4586:4: (lv_bold_4_0= RULE_BOOLEAN )
            {
            // InternalKGraph.g:4586:4: (lv_bold_4_0= RULE_BOOLEAN )
            // InternalKGraph.g:4587:5: lv_bold_4_0= RULE_BOOLEAN
            {
            lv_bold_4_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_2); 

            					newLeafNode(lv_bold_4_0, grammarAccess.getKFontBoldAccess().getBoldBOOLEANTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKFontBoldRule());
            					}
            					setWithLastConsumed(
            						current,
            						"bold",
            						lv_bold_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.BOOLEAN");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKFontBold"


    // $ANTLR start "entryRuleKFontItalic"
    // InternalKGraph.g:4607:1: entryRuleKFontItalic returns [EObject current=null] : iv_ruleKFontItalic= ruleKFontItalic EOF ;
    public final EObject entryRuleKFontItalic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontItalic = null;


        try {
            // InternalKGraph.g:4607:52: (iv_ruleKFontItalic= ruleKFontItalic EOF )
            // InternalKGraph.g:4608:2: iv_ruleKFontItalic= ruleKFontItalic EOF
            {
             newCompositeNode(grammarAccess.getKFontItalicRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKFontItalic=ruleKFontItalic();

            state._fsp--;

             current =iv_ruleKFontItalic; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKFontItalic"


    // $ANTLR start "ruleKFontItalic"
    // InternalKGraph.g:4614:1: ruleKFontItalic returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleKFontItalic() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_italic_4_0=null;


        	enterRule();

        try {
            // InternalKGraph.g:4620:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) ) )
            // InternalKGraph.g:4621:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) )
            {
            // InternalKGraph.g:4621:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) )
            // InternalKGraph.g:4622:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) )
            {
            // InternalKGraph.g:4622:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==68) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // InternalKGraph.g:4623:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4623:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4624:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_79); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontItalicAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontItalicRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4636:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==69) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalKGraph.g:4637:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4637:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4638:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_80); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKFontItalicAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontItalicRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,73,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKFontItalicAccess().getItalicKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_55); 

            			newLeafNode(otherlv_3, grammarAccess.getKFontItalicAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:4658:3: ( (lv_italic_4_0= RULE_BOOLEAN ) )
            // InternalKGraph.g:4659:4: (lv_italic_4_0= RULE_BOOLEAN )
            {
            // InternalKGraph.g:4659:4: (lv_italic_4_0= RULE_BOOLEAN )
            // InternalKGraph.g:4660:5: lv_italic_4_0= RULE_BOOLEAN
            {
            lv_italic_4_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_2); 

            					newLeafNode(lv_italic_4_0, grammarAccess.getKFontItalicAccess().getItalicBOOLEANTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKFontItalicRule());
            					}
            					setWithLastConsumed(
            						current,
            						"italic",
            						lv_italic_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.BOOLEAN");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKFontItalic"


    // $ANTLR start "entryRuleKFontName"
    // InternalKGraph.g:4680:1: entryRuleKFontName returns [EObject current=null] : iv_ruleKFontName= ruleKFontName EOF ;
    public final EObject entryRuleKFontName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontName = null;


        try {
            // InternalKGraph.g:4680:50: (iv_ruleKFontName= ruleKFontName EOF )
            // InternalKGraph.g:4681:2: iv_ruleKFontName= ruleKFontName EOF
            {
             newCompositeNode(grammarAccess.getKFontNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKFontName=ruleKFontName();

            state._fsp--;

             current =iv_ruleKFontName; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKFontName"


    // $ANTLR start "ruleKFontName"
    // InternalKGraph.g:4687:1: ruleKFontName returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) ) ;
    public final EObject ruleKFontName() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_name_4_0=null;


        	enterRule();

        try {
            // InternalKGraph.g:4693:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) ) )
            // InternalKGraph.g:4694:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) )
            {
            // InternalKGraph.g:4694:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) )
            // InternalKGraph.g:4695:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) )
            {
            // InternalKGraph.g:4695:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==68) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // InternalKGraph.g:4696:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4696:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4697:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_81); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontNameAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontNameRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4709:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==69) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalKGraph.g:4710:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4710:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4711:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_82); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKFontNameAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontNameRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,74,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKFontNameAccess().getFontNameKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_53); 

            			newLeafNode(otherlv_3, grammarAccess.getKFontNameAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:4731:3: ( (lv_name_4_0= RULE_STRING ) )
            // InternalKGraph.g:4732:4: (lv_name_4_0= RULE_STRING )
            {
            // InternalKGraph.g:4732:4: (lv_name_4_0= RULE_STRING )
            // InternalKGraph.g:4733:5: lv_name_4_0= RULE_STRING
            {
            lv_name_4_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_name_4_0, grammarAccess.getKFontNameAccess().getNameSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKFontNameRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKFontName"


    // $ANTLR start "entryRuleKFontSize"
    // InternalKGraph.g:4753:1: entryRuleKFontSize returns [EObject current=null] : iv_ruleKFontSize= ruleKFontSize EOF ;
    public final EObject entryRuleKFontSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontSize = null;


        try {
            // InternalKGraph.g:4753:50: (iv_ruleKFontSize= ruleKFontSize EOF )
            // InternalKGraph.g:4754:2: iv_ruleKFontSize= ruleKFontSize EOF
            {
             newCompositeNode(grammarAccess.getKFontSizeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKFontSize=ruleKFontSize();

            state._fsp--;

             current =iv_ruleKFontSize; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKFontSize"


    // $ANTLR start "ruleKFontSize"
    // InternalKGraph.g:4760:1: ruleKFontSize returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) ) ;
    public final EObject ruleKFontSize() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token lv_scaleWithZoom_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_size_5_0=null;


        	enterRule();

        try {
            // InternalKGraph.g:4766:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) ) )
            // InternalKGraph.g:4767:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) )
            {
            // InternalKGraph.g:4767:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) )
            // InternalKGraph.g:4768:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) )
            {
            // InternalKGraph.g:4768:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==68) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalKGraph.g:4769:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4769:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4770:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_83); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontSizeAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontSizeRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4782:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==69) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalKGraph.g:4783:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4783:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4784:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_84); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKFontSizeAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontSizeRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4796:3: ( (lv_scaleWithZoom_2_0= 'scale' ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==75) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalKGraph.g:4797:4: (lv_scaleWithZoom_2_0= 'scale' )
                    {
                    // InternalKGraph.g:4797:4: (lv_scaleWithZoom_2_0= 'scale' )
                    // InternalKGraph.g:4798:5: lv_scaleWithZoom_2_0= 'scale'
                    {
                    lv_scaleWithZoom_2_0=(Token)match(input,75,FOLLOW_85); 

                    					newLeafNode(lv_scaleWithZoom_2_0, grammarAccess.getKFontSizeAccess().getScaleWithZoomScaleKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKFontSizeRule());
                    					}
                    					setWithLastConsumed(current, "scaleWithZoom", true, "scale");
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,76,FOLLOW_22); 

            			newLeafNode(otherlv_3, grammarAccess.getKFontSizeAccess().getFontSizeKeyword_3());
            		
            otherlv_4=(Token)match(input,30,FOLLOW_86); 

            			newLeafNode(otherlv_4, grammarAccess.getKFontSizeAccess().getEqualsSignKeyword_4());
            		
            // InternalKGraph.g:4818:3: ( (lv_size_5_0= RULE_FSIZE ) )
            // InternalKGraph.g:4819:4: (lv_size_5_0= RULE_FSIZE )
            {
            // InternalKGraph.g:4819:4: (lv_size_5_0= RULE_FSIZE )
            // InternalKGraph.g:4820:5: lv_size_5_0= RULE_FSIZE
            {
            lv_size_5_0=(Token)match(input,RULE_FSIZE,FOLLOW_2); 

            					newLeafNode(lv_size_5_0, grammarAccess.getKFontSizeAccess().getSizeFSIZETerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKFontSizeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"size",
            						lv_size_5_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.FSIZE");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKFontSize"


    // $ANTLR start "entryRuleKTextUnderline"
    // InternalKGraph.g:4840:1: entryRuleKTextUnderline returns [EObject current=null] : iv_ruleKTextUnderline= ruleKTextUnderline EOF ;
    public final EObject entryRuleKTextUnderline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTextUnderline = null;


        try {
            // InternalKGraph.g:4840:55: (iv_ruleKTextUnderline= ruleKTextUnderline EOF )
            // InternalKGraph.g:4841:2: iv_ruleKTextUnderline= ruleKTextUnderline EOF
            {
             newCompositeNode(grammarAccess.getKTextUnderlineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKTextUnderline=ruleKTextUnderline();

            state._fsp--;

             current =iv_ruleKTextUnderline; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKTextUnderline"


    // $ANTLR start "ruleKTextUnderline"
    // InternalKGraph.g:4847:1: ruleKTextUnderline returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) ) ;
    public final EObject ruleKTextUnderline() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_underline_4_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:4853:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) ) )
            // InternalKGraph.g:4854:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) )
            {
            // InternalKGraph.g:4854:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) )
            // InternalKGraph.g:4855:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) )
            {
            // InternalKGraph.g:4855:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==68) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // InternalKGraph.g:4856:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4856:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4857:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_87); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKTextUnderlineAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKTextUnderlineRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4869:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==69) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // InternalKGraph.g:4870:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4870:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4871:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_88); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKTextUnderlineAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKTextUnderlineRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,77,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKTextUnderlineAccess().getUnderlineKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_89); 

            			newLeafNode(otherlv_3, grammarAccess.getKTextUnderlineAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:4891:3: ( (lv_underline_4_0= ruleUnderline ) )
            // InternalKGraph.g:4892:4: (lv_underline_4_0= ruleUnderline )
            {
            // InternalKGraph.g:4892:4: (lv_underline_4_0= ruleUnderline )
            // InternalKGraph.g:4893:5: lv_underline_4_0= ruleUnderline
            {

            					newCompositeNode(grammarAccess.getKTextUnderlineAccess().getUnderlineUnderlineEnumRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_underline_4_0=ruleUnderline();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKTextUnderlineRule());
            					}
            					set(
            						current,
            						"underline",
            						lv_underline_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.Underline");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKTextUnderline"


    // $ANTLR start "entryRuleKHorizontalAlignment"
    // InternalKGraph.g:4914:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // InternalKGraph.g:4914:61: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // InternalKGraph.g:4915:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKHorizontalAlignment"


    // $ANTLR start "ruleKHorizontalAlignment"
    // InternalKGraph.g:4921:1: ruleKHorizontalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_horizontalAlignment_4_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:4927:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) ) )
            // InternalKGraph.g:4928:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) )
            {
            // InternalKGraph.g:4928:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) )
            // InternalKGraph.g:4929:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) )
            {
            // InternalKGraph.g:4929:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==68) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // InternalKGraph.g:4930:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4930:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4931:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_90); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKHorizontalAlignmentAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4943:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==69) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // InternalKGraph.g:4944:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4944:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4945:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_91); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKHorizontalAlignmentAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,78,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKHorizontalAlignmentAccess().getHAlignKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_92); 

            			newLeafNode(otherlv_3, grammarAccess.getKHorizontalAlignmentAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:4965:3: ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) )
            // InternalKGraph.g:4966:4: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            {
            // InternalKGraph.g:4966:4: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            // InternalKGraph.g:4967:5: lv_horizontalAlignment_4_0= ruleHorizontalAlignment
            {

            					newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_horizontalAlignment_4_0=ruleHorizontalAlignment();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKHorizontalAlignmentRule());
            					}
            					set(
            						current,
            						"horizontalAlignment",
            						lv_horizontalAlignment_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.HorizontalAlignment");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKHorizontalAlignment"


    // $ANTLR start "entryRuleKVerticalAlignment"
    // InternalKGraph.g:4988:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // InternalKGraph.g:4988:59: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // InternalKGraph.g:4989:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKVerticalAlignment"


    // $ANTLR start "ruleKVerticalAlignment"
    // InternalKGraph.g:4995:1: ruleKVerticalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_verticalAlignment_4_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5001:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) ) )
            // InternalKGraph.g:5002:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) )
            {
            // InternalKGraph.g:5002:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) )
            // InternalKGraph.g:5003:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) )
            {
            // InternalKGraph.g:5003:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==68) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // InternalKGraph.g:5004:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5004:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5005:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_93); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKVerticalAlignmentAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5017:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==69) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalKGraph.g:5018:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5018:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5019:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_94); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKVerticalAlignmentAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,79,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKVerticalAlignmentAccess().getVAlignKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_95); 

            			newLeafNode(otherlv_3, grammarAccess.getKVerticalAlignmentAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:5039:3: ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) )
            // InternalKGraph.g:5040:4: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            {
            // InternalKGraph.g:5040:4: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            // InternalKGraph.g:5041:5: lv_verticalAlignment_4_0= ruleVerticalAlignment
            {

            					newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_verticalAlignment_4_0=ruleVerticalAlignment();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKVerticalAlignmentRule());
            					}
            					set(
            						current,
            						"verticalAlignment",
            						lv_verticalAlignment_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.VerticalAlignment");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKVerticalAlignment"


    // $ANTLR start "entryRuleKInvisibility"
    // InternalKGraph.g:5062:1: entryRuleKInvisibility returns [EObject current=null] : iv_ruleKInvisibility= ruleKInvisibility EOF ;
    public final EObject entryRuleKInvisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInvisibility = null;


        try {
            // InternalKGraph.g:5062:54: (iv_ruleKInvisibility= ruleKInvisibility EOF )
            // InternalKGraph.g:5063:2: iv_ruleKInvisibility= ruleKInvisibility EOF
            {
             newCompositeNode(grammarAccess.getKInvisibilityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKInvisibility=ruleKInvisibility();

            state._fsp--;

             current =iv_ruleKInvisibility; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKInvisibility"


    // $ANTLR start "ruleKInvisibility"
    // InternalKGraph.g:5069:1: ruleKInvisibility returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleKInvisibility() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_invisible_4_0=null;


        	enterRule();

        try {
            // InternalKGraph.g:5075:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) ) )
            // InternalKGraph.g:5076:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) )
            {
            // InternalKGraph.g:5076:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) )
            // InternalKGraph.g:5077:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) )
            {
            // InternalKGraph.g:5077:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==68) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // InternalKGraph.g:5078:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5078:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5079:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_96); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKInvisibilityAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKInvisibilityRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5091:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==69) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalKGraph.g:5092:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5092:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5093:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_97); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKInvisibilityAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKInvisibilityRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,80,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKInvisibilityAccess().getInvisibleKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_55); 

            			newLeafNode(otherlv_3, grammarAccess.getKInvisibilityAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:5113:3: ( (lv_invisible_4_0= RULE_BOOLEAN ) )
            // InternalKGraph.g:5114:4: (lv_invisible_4_0= RULE_BOOLEAN )
            {
            // InternalKGraph.g:5114:4: (lv_invisible_4_0= RULE_BOOLEAN )
            // InternalKGraph.g:5115:5: lv_invisible_4_0= RULE_BOOLEAN
            {
            lv_invisible_4_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_2); 

            					newLeafNode(lv_invisible_4_0, grammarAccess.getKInvisibilityAccess().getInvisibleBOOLEANTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKInvisibilityRule());
            					}
            					setWithLastConsumed(
            						current,
            						"invisible",
            						lv_invisible_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.BOOLEAN");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKInvisibility"


    // $ANTLR start "entryRuleKLineCap"
    // InternalKGraph.g:5135:1: entryRuleKLineCap returns [EObject current=null] : iv_ruleKLineCap= ruleKLineCap EOF ;
    public final EObject entryRuleKLineCap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineCap = null;


        try {
            // InternalKGraph.g:5135:49: (iv_ruleKLineCap= ruleKLineCap EOF )
            // InternalKGraph.g:5136:2: iv_ruleKLineCap= ruleKLineCap EOF
            {
             newCompositeNode(grammarAccess.getKLineCapRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKLineCap=ruleKLineCap();

            state._fsp--;

             current =iv_ruleKLineCap; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLineCap"


    // $ANTLR start "ruleKLineCap"
    // InternalKGraph.g:5142:1: ruleKLineCap returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) ) ;
    public final EObject ruleKLineCap() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_lineCap_4_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5148:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) ) )
            // InternalKGraph.g:5149:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) )
            {
            // InternalKGraph.g:5149:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) )
            // InternalKGraph.g:5150:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) )
            {
            // InternalKGraph.g:5150:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==68) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // InternalKGraph.g:5151:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5151:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5152:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_98); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineCapAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineCapRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5164:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==69) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // InternalKGraph.g:5165:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5165:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5166:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_99); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKLineCapAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineCapRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,81,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKLineCapAccess().getLineCapKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_100); 

            			newLeafNode(otherlv_3, grammarAccess.getKLineCapAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:5186:3: ( (lv_lineCap_4_0= ruleLineCap ) )
            // InternalKGraph.g:5187:4: (lv_lineCap_4_0= ruleLineCap )
            {
            // InternalKGraph.g:5187:4: (lv_lineCap_4_0= ruleLineCap )
            // InternalKGraph.g:5188:5: lv_lineCap_4_0= ruleLineCap
            {

            					newCompositeNode(grammarAccess.getKLineCapAccess().getLineCapLineCapEnumRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_lineCap_4_0=ruleLineCap();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKLineCapRule());
            					}
            					set(
            						current,
            						"lineCap",
            						lv_lineCap_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.LineCap");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKLineCap"


    // $ANTLR start "entryRuleKLineJoin"
    // InternalKGraph.g:5209:1: entryRuleKLineJoin returns [EObject current=null] : iv_ruleKLineJoin= ruleKLineJoin EOF ;
    public final EObject entryRuleKLineJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineJoin = null;


        try {
            // InternalKGraph.g:5209:50: (iv_ruleKLineJoin= ruleKLineJoin EOF )
            // InternalKGraph.g:5210:2: iv_ruleKLineJoin= ruleKLineJoin EOF
            {
             newCompositeNode(grammarAccess.getKLineJoinRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKLineJoin=ruleKLineJoin();

            state._fsp--;

             current =iv_ruleKLineJoin; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLineJoin"


    // $ANTLR start "ruleKLineJoin"
    // InternalKGraph.g:5216:1: ruleKLineJoin returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) ) ;
    public final EObject ruleKLineJoin() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_lineJoin_4_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5222:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) ) )
            // InternalKGraph.g:5223:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) )
            {
            // InternalKGraph.g:5223:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) )
            // InternalKGraph.g:5224:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) )
            {
            // InternalKGraph.g:5224:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==68) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // InternalKGraph.g:5225:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5225:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5226:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_101); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineJoinAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineJoinRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5238:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==69) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // InternalKGraph.g:5239:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5239:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5240:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_102); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKLineJoinAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineJoinRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,82,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKLineJoinAccess().getLineJoinKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_103); 

            			newLeafNode(otherlv_3, grammarAccess.getKLineJoinAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:5260:3: ( (lv_lineJoin_4_0= ruleLineJoin ) )
            // InternalKGraph.g:5261:4: (lv_lineJoin_4_0= ruleLineJoin )
            {
            // InternalKGraph.g:5261:4: (lv_lineJoin_4_0= ruleLineJoin )
            // InternalKGraph.g:5262:5: lv_lineJoin_4_0= ruleLineJoin
            {

            					newCompositeNode(grammarAccess.getKLineJoinAccess().getLineJoinLineJoinEnumRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_lineJoin_4_0=ruleLineJoin();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKLineJoinRule());
            					}
            					set(
            						current,
            						"lineJoin",
            						lv_lineJoin_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.LineJoin");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKLineJoin"


    // $ANTLR start "entryRuleKLineStyle"
    // InternalKGraph.g:5283:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // InternalKGraph.g:5283:51: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // InternalKGraph.g:5284:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLineStyle"


    // $ANTLR start "ruleKLineStyle"
    // InternalKGraph.g:5290:1: ruleKLineStyle returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Enumerator lv_lineStyle_4_0 = null;

        AntlrDatatypeRuleToken lv_dashOffset_7_0 = null;

        AntlrDatatypeRuleToken lv_dashPattern_10_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5296:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? ) )
            // InternalKGraph.g:5297:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? )
            {
            // InternalKGraph.g:5297:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? )
            // InternalKGraph.g:5298:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )?
            {
            // InternalKGraph.g:5298:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==68) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // InternalKGraph.g:5299:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5299:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5300:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_104); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineStyleAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineStyleRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5312:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==69) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // InternalKGraph.g:5313:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5313:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5314:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_105); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKLineStyleAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineStyleRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,83,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_106); 

            			newLeafNode(otherlv_3, grammarAccess.getKLineStyleAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:5334:3: ( (lv_lineStyle_4_0= ruleLineStyle ) )
            // InternalKGraph.g:5335:4: (lv_lineStyle_4_0= ruleLineStyle )
            {
            // InternalKGraph.g:5335:4: (lv_lineStyle_4_0= ruleLineStyle )
            // InternalKGraph.g:5336:5: lv_lineStyle_4_0= ruleLineStyle
            {

            					newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_4_0());
            				
            pushFollow(FOLLOW_107);
            lv_lineStyle_4_0=ruleLineStyle();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKLineStyleRule());
            					}
            					set(
            						current,
            						"lineStyle",
            						lv_lineStyle_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.LineStyle");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalKGraph.g:5353:3: (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==84) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // InternalKGraph.g:5354:4: otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) )
                    {
                    otherlv_5=(Token)match(input,84,FOLLOW_22); 

                    				newLeafNode(otherlv_5, grammarAccess.getKLineStyleAccess().getDashOffsetKeyword_5_0());
                    			
                    otherlv_6=(Token)match(input,30,FOLLOW_28); 

                    				newLeafNode(otherlv_6, grammarAccess.getKLineStyleAccess().getEqualsSignKeyword_5_1());
                    			
                    // InternalKGraph.g:5362:4: ( (lv_dashOffset_7_0= ruleFloat ) )
                    // InternalKGraph.g:5363:5: (lv_dashOffset_7_0= ruleFloat )
                    {
                    // InternalKGraph.g:5363:5: (lv_dashOffset_7_0= ruleFloat )
                    // InternalKGraph.g:5364:6: lv_dashOffset_7_0= ruleFloat
                    {

                    						newCompositeNode(grammarAccess.getKLineStyleAccess().getDashOffsetFloatParserRuleCall_5_2_0());
                    					
                    pushFollow(FOLLOW_108);
                    lv_dashOffset_7_0=ruleFloat();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKLineStyleRule());
                    						}
                    						set(
                    							current,
                    							"dashOffset",
                    							lv_dashOffset_7_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalKGraph.g:5382:3: (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==85) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // InternalKGraph.g:5383:4: otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )*
                    {
                    otherlv_8=(Token)match(input,85,FOLLOW_22); 

                    				newLeafNode(otherlv_8, grammarAccess.getKLineStyleAccess().getDashPatternKeyword_6_0());
                    			
                    otherlv_9=(Token)match(input,30,FOLLOW_109); 

                    				newLeafNode(otherlv_9, grammarAccess.getKLineStyleAccess().getEqualsSignKeyword_6_1());
                    			
                    // InternalKGraph.g:5391:4: ( (lv_dashPattern_10_0= ruleFloat ) )*
                    loop113:
                    do {
                        int alt113=2;
                        int LA113_0 = input.LA(1);

                        if ( (LA113_0==RULE_NATURAL||LA113_0==RULE_TFLOAT) ) {
                            alt113=1;
                        }


                        switch (alt113) {
                    	case 1 :
                    	    // InternalKGraph.g:5392:5: (lv_dashPattern_10_0= ruleFloat )
                    	    {
                    	    // InternalKGraph.g:5392:5: (lv_dashPattern_10_0= ruleFloat )
                    	    // InternalKGraph.g:5393:6: lv_dashPattern_10_0= ruleFloat
                    	    {

                    	    						newCompositeNode(grammarAccess.getKLineStyleAccess().getDashPatternFloatParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_109);
                    	    lv_dashPattern_10_0=ruleFloat();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKLineStyleRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"dashPattern",
                    	    							lv_dashPattern_10_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop113;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKLineStyle"


    // $ANTLR start "entryRuleKLineWidth"
    // InternalKGraph.g:5415:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // InternalKGraph.g:5415:51: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // InternalKGraph.g:5416:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLineWidth"


    // $ANTLR start "ruleKLineWidth"
    // InternalKGraph.g:5422:1: ruleKLineWidth returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_lineWidth_4_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5428:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) ) )
            // InternalKGraph.g:5429:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) )
            {
            // InternalKGraph.g:5429:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) )
            // InternalKGraph.g:5430:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) )
            {
            // InternalKGraph.g:5430:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==68) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // InternalKGraph.g:5431:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5431:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5432:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_110); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineWidthAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineWidthRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5444:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==69) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // InternalKGraph.g:5445:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5445:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5446:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_111); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKLineWidthAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKLineWidthRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,86,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_28); 

            			newLeafNode(otherlv_3, grammarAccess.getKLineWidthAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:5466:3: ( (lv_lineWidth_4_0= ruleFloat ) )
            // InternalKGraph.g:5467:4: (lv_lineWidth_4_0= ruleFloat )
            {
            // InternalKGraph.g:5467:4: (lv_lineWidth_4_0= ruleFloat )
            // InternalKGraph.g:5468:5: lv_lineWidth_4_0= ruleFloat
            {

            					newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthFloatParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_lineWidth_4_0=ruleFloat();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKLineWidthRule());
            					}
            					set(
            						current,
            						"lineWidth",
            						lv_lineWidth_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKLineWidth"


    // $ANTLR start "entryRuleKRotation"
    // InternalKGraph.g:5489:1: entryRuleKRotation returns [EObject current=null] : iv_ruleKRotation= ruleKRotation EOF ;
    public final EObject entryRuleKRotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRotation = null;


        try {
            // InternalKGraph.g:5489:50: (iv_ruleKRotation= ruleKRotation EOF )
            // InternalKGraph.g:5490:2: iv_ruleKRotation= ruleKRotation EOF
            {
             newCompositeNode(grammarAccess.getKRotationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRotation=ruleKRotation();

            state._fsp--;

             current =iv_ruleKRotation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRotation"


    // $ANTLR start "ruleKRotation"
    // InternalKGraph.g:5496:1: ruleKRotation returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? ) ;
    public final EObject ruleKRotation() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_rotation_4_0 = null;

        EObject lv_rotationAnchor_8_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5502:2: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? ) )
            // InternalKGraph.g:5503:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? )
            {
            // InternalKGraph.g:5503:2: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? )
            // InternalKGraph.g:5504:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )?
            {
            // InternalKGraph.g:5504:3: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==68) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // InternalKGraph.g:5505:4: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5505:4: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5506:5: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FOLLOW_112); 

                    					newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKRotationAccess().getPropagateToChildrenPropagateKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKRotationRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5518:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==69) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // InternalKGraph.g:5519:4: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5519:4: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5520:5: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FOLLOW_113); 

                    					newLeafNode(lv_selection_1_0, grammarAccess.getKRotationAccess().getSelectionSelectionKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKRotationRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,87,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getKRotationAccess().getRotationKeyword_2());
            		
            otherlv_3=(Token)match(input,30,FOLLOW_28); 

            			newLeafNode(otherlv_3, grammarAccess.getKRotationAccess().getEqualsSignKeyword_3());
            		
            // InternalKGraph.g:5540:3: ( (lv_rotation_4_0= ruleFloat ) )
            // InternalKGraph.g:5541:4: (lv_rotation_4_0= ruleFloat )
            {
            // InternalKGraph.g:5541:4: (lv_rotation_4_0= ruleFloat )
            // InternalKGraph.g:5542:5: lv_rotation_4_0= ruleFloat
            {

            					newCompositeNode(grammarAccess.getKRotationAccess().getRotationFloatParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_52);
            lv_rotation_4_0=ruleFloat();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKRotationRule());
            					}
            					set(
            						current,
            						"rotation",
            						lv_rotation_4_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalKGraph.g:5559:3: (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==24) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // InternalKGraph.g:5560:4: otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')'
                    {
                    otherlv_5=(Token)match(input,24,FOLLOW_114); 

                    				newLeafNode(otherlv_5, grammarAccess.getKRotationAccess().getLeftParenthesisKeyword_5_0());
                    			
                    otherlv_6=(Token)match(input,88,FOLLOW_22); 

                    				newLeafNode(otherlv_6, grammarAccess.getKRotationAccess().getAnchorKeyword_5_1());
                    			
                    otherlv_7=(Token)match(input,30,FOLLOW_64); 

                    				newLeafNode(otherlv_7, grammarAccess.getKRotationAccess().getEqualsSignKeyword_5_2());
                    			
                    // InternalKGraph.g:5572:4: ( (lv_rotationAnchor_8_0= ruleKPosition ) )
                    // InternalKGraph.g:5573:5: (lv_rotationAnchor_8_0= ruleKPosition )
                    {
                    // InternalKGraph.g:5573:5: (lv_rotationAnchor_8_0= ruleKPosition )
                    // InternalKGraph.g:5574:6: lv_rotationAnchor_8_0= ruleKPosition
                    {

                    						newCompositeNode(grammarAccess.getKRotationAccess().getRotationAnchorKPositionParserRuleCall_5_3_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_rotationAnchor_8_0=ruleKPosition();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKRotationRule());
                    						}
                    						set(
                    							current,
                    							"rotationAnchor",
                    							lv_rotationAnchor_8_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_9=(Token)match(input,27,FOLLOW_2); 

                    				newLeafNode(otherlv_9, grammarAccess.getKRotationAccess().getRightParenthesisKeyword_5_4());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRotation"


    // $ANTLR start "entryRuleKShadow"
    // InternalKGraph.g:5600:1: entryRuleKShadow returns [EObject current=null] : iv_ruleKShadow= ruleKShadow EOF ;
    public final EObject entryRuleKShadow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShadow = null;


        try {
            // InternalKGraph.g:5600:48: (iv_ruleKShadow= ruleKShadow EOF )
            // InternalKGraph.g:5601:2: iv_ruleKShadow= ruleKShadow EOF
            {
             newCompositeNode(grammarAccess.getKShadowRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKShadow=ruleKShadow();

            state._fsp--;

             current =iv_ruleKShadow; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKShadow"


    // $ANTLR start "ruleKShadow"
    // InternalKGraph.g:5607:1: ruleKShadow returns [EObject current=null] : ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? ) ;
    public final EObject ruleKShadow() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_1_0=null;
        Token lv_selection_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_color_5_0 = null;

        AntlrDatatypeRuleToken lv_xOffset_7_0 = null;

        AntlrDatatypeRuleToken lv_yOffset_9_0 = null;

        AntlrDatatypeRuleToken lv_blur_11_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5613:2: ( ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? ) )
            // InternalKGraph.g:5614:2: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? )
            {
            // InternalKGraph.g:5614:2: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? )
            // InternalKGraph.g:5615:3: () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )?
            {
            // InternalKGraph.g:5615:3: ()
            // InternalKGraph.g:5616:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKShadowAccess().getKShadowAction_0(),
            					current);
            			

            }

            // InternalKGraph.g:5622:3: ( (lv_propagateToChildren_1_0= 'propagate' ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==68) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // InternalKGraph.g:5623:4: (lv_propagateToChildren_1_0= 'propagate' )
                    {
                    // InternalKGraph.g:5623:4: (lv_propagateToChildren_1_0= 'propagate' )
                    // InternalKGraph.g:5624:5: lv_propagateToChildren_1_0= 'propagate'
                    {
                    lv_propagateToChildren_1_0=(Token)match(input,68,FOLLOW_115); 

                    					newLeafNode(lv_propagateToChildren_1_0, grammarAccess.getKShadowAccess().getPropagateToChildrenPropagateKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKShadowRule());
                    					}
                    					setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5636:3: ( (lv_selection_2_0= 'selection' ) )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==69) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // InternalKGraph.g:5637:4: (lv_selection_2_0= 'selection' )
                    {
                    // InternalKGraph.g:5637:4: (lv_selection_2_0= 'selection' )
                    // InternalKGraph.g:5638:5: lv_selection_2_0= 'selection'
                    {
                    lv_selection_2_0=(Token)match(input,69,FOLLOW_116); 

                    					newLeafNode(lv_selection_2_0, grammarAccess.getKShadowAccess().getSelectionSelectionKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKShadowRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,89,FOLLOW_22); 

            			newLeafNode(otherlv_3, grammarAccess.getKShadowAccess().getShadowKeyword_3());
            		
            otherlv_4=(Token)match(input,30,FOLLOW_117); 

            			newLeafNode(otherlv_4, grammarAccess.getKShadowAccess().getEqualsSignKeyword_4());
            		
            // InternalKGraph.g:5658:3: ( (lv_color_5_0= ruleKColor ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( ((LA122_0>=RULE_RED && LA122_0<=RULE_BLUE)) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // InternalKGraph.g:5659:4: (lv_color_5_0= ruleKColor )
                    {
                    // InternalKGraph.g:5659:4: (lv_color_5_0= ruleKColor )
                    // InternalKGraph.g:5660:5: lv_color_5_0= ruleKColor
                    {

                    					newCompositeNode(grammarAccess.getKShadowAccess().getColorKColorParserRuleCall_5_0());
                    				
                    pushFollow(FOLLOW_52);
                    lv_color_5_0=ruleKColor();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKShadowRule());
                    					}
                    					set(
                    						current,
                    						"color",
                    						lv_color_5_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.KColor");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5677:3: (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==24) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // InternalKGraph.g:5678:4: otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')'
                    {
                    otherlv_6=(Token)match(input,24,FOLLOW_28); 

                    				newLeafNode(otherlv_6, grammarAccess.getKShadowAccess().getLeftParenthesisKeyword_6_0());
                    			
                    // InternalKGraph.g:5682:4: ( (lv_xOffset_7_0= ruleFloat ) )
                    // InternalKGraph.g:5683:5: (lv_xOffset_7_0= ruleFloat )
                    {
                    // InternalKGraph.g:5683:5: (lv_xOffset_7_0= ruleFloat )
                    // InternalKGraph.g:5684:6: lv_xOffset_7_0= ruleFloat
                    {

                    						newCompositeNode(grammarAccess.getKShadowAccess().getXOffsetFloatParserRuleCall_6_1_0());
                    					
                    pushFollow(FOLLOW_41);
                    lv_xOffset_7_0=ruleFloat();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKShadowRule());
                    						}
                    						set(
                    							current,
                    							"xOffset",
                    							lv_xOffset_7_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_8=(Token)match(input,47,FOLLOW_28); 

                    				newLeafNode(otherlv_8, grammarAccess.getKShadowAccess().getCommaKeyword_6_2());
                    			
                    // InternalKGraph.g:5705:4: ( (lv_yOffset_9_0= ruleFloat ) )
                    // InternalKGraph.g:5706:5: (lv_yOffset_9_0= ruleFloat )
                    {
                    // InternalKGraph.g:5706:5: (lv_yOffset_9_0= ruleFloat )
                    // InternalKGraph.g:5707:6: lv_yOffset_9_0= ruleFloat
                    {

                    						newCompositeNode(grammarAccess.getKShadowAccess().getYOffsetFloatParserRuleCall_6_3_0());
                    					
                    pushFollow(FOLLOW_54);
                    lv_yOffset_9_0=ruleFloat();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKShadowRule());
                    						}
                    						set(
                    							current,
                    							"yOffset",
                    							lv_yOffset_9_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalKGraph.g:5724:4: (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==47) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // InternalKGraph.g:5725:5: otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) )
                            {
                            otherlv_10=(Token)match(input,47,FOLLOW_28); 

                            					newLeafNode(otherlv_10, grammarAccess.getKShadowAccess().getCommaKeyword_6_4_0());
                            				
                            // InternalKGraph.g:5729:5: ( (lv_blur_11_0= ruleFloat ) )
                            // InternalKGraph.g:5730:6: (lv_blur_11_0= ruleFloat )
                            {
                            // InternalKGraph.g:5730:6: (lv_blur_11_0= ruleFloat )
                            // InternalKGraph.g:5731:7: lv_blur_11_0= ruleFloat
                            {

                            							newCompositeNode(grammarAccess.getKShadowAccess().getBlurFloatParserRuleCall_6_4_1_0());
                            						
                            pushFollow(FOLLOW_13);
                            lv_blur_11_0=ruleFloat();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getKShadowRule());
                            							}
                            							set(
                            								current,
                            								"blur",
                            								lv_blur_11_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }

                    otherlv_12=(Token)match(input,27,FOLLOW_2); 

                    				newLeafNode(otherlv_12, grammarAccess.getKShadowAccess().getRightParenthesisKeyword_6_5());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKShadow"


    // $ANTLR start "entryRuleKStyleRef"
    // InternalKGraph.g:5758:1: entryRuleKStyleRef returns [EObject current=null] : iv_ruleKStyleRef= ruleKStyleRef EOF ;
    public final EObject entryRuleKStyleRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyleRef = null;


        try {
            // InternalKGraph.g:5758:50: (iv_ruleKStyleRef= ruleKStyleRef EOF )
            // InternalKGraph.g:5759:2: iv_ruleKStyleRef= ruleKStyleRef EOF
            {
             newCompositeNode(grammarAccess.getKStyleRefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKStyleRef=ruleKStyleRef();

            state._fsp--;

             current =iv_ruleKStyleRef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKStyleRef"


    // $ANTLR start "ruleKStyleRef"
    // InternalKGraph.g:5765:1: ruleKStyleRef returns [EObject current=null] : ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) ) ;
    public final EObject ruleKStyleRef() throws RecognitionException {
        EObject current = null;

        Token lv_selection_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalKGraph.g:5771:2: ( ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) ) )
            // InternalKGraph.g:5772:2: ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) )
            {
            // InternalKGraph.g:5772:2: ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) )
            // InternalKGraph.g:5773:3: ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) )
            {
            // InternalKGraph.g:5773:3: ( (lv_selection_0_0= 'selection' ) )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==69) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // InternalKGraph.g:5774:4: (lv_selection_0_0= 'selection' )
                    {
                    // InternalKGraph.g:5774:4: (lv_selection_0_0= 'selection' )
                    // InternalKGraph.g:5775:5: lv_selection_0_0= 'selection'
                    {
                    lv_selection_0_0=(Token)match(input,69,FOLLOW_118); 

                    					newLeafNode(lv_selection_0_0, grammarAccess.getKStyleRefAccess().getSelectionSelectionKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKStyleRefRule());
                    					}
                    					setWithLastConsumed(current, "selection", true, "selection");
                    				

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,90,FOLLOW_22); 

            			newLeafNode(otherlv_1, grammarAccess.getKStyleRefAccess().getReferenceKeyword_1());
            		
            otherlv_2=(Token)match(input,30,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getKStyleRefAccess().getEqualsSignKeyword_2());
            		
            // InternalKGraph.g:5795:3: ( ( ruleQualifiedID ) )
            // InternalKGraph.g:5796:4: ( ruleQualifiedID )
            {
            // InternalKGraph.g:5796:4: ( ruleQualifiedID )
            // InternalKGraph.g:5797:5: ruleQualifiedID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKStyleRefRule());
            					}
            				

            					newCompositeNode(grammarAccess.getKStyleRefAccess().getStyleHolderKStyleHolderCrossReference_3_0());
            				
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKStyleRef"


    // $ANTLR start "entryRuleKRenderingLibrary"
    // InternalKGraph.g:5815:1: entryRuleKRenderingLibrary returns [EObject current=null] : iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF ;
    public final EObject entryRuleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingLibrary = null;


        try {
            // InternalKGraph.g:5815:58: (iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF )
            // InternalKGraph.g:5816:2: iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF
            {
             newCompositeNode(grammarAccess.getKRenderingLibraryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRenderingLibrary=ruleKRenderingLibrary();

            state._fsp--;

             current =iv_ruleKRenderingLibrary; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRenderingLibrary"


    // $ANTLR start "ruleKRenderingLibrary"
    // InternalKGraph.g:5822:1: ruleKRenderingLibrary returns [EObject current=null] : ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' ) ;
    public final EObject ruleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_8=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_renderings_6_0 = null;

        EObject lv_renderings_7_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5828:2: ( ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' ) )
            // InternalKGraph.g:5829:2: ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' )
            {
            // InternalKGraph.g:5829:2: ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' )
            // InternalKGraph.g:5830:3: () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}'
            {
            // InternalKGraph.g:5830:3: ()
            // InternalKGraph.g:5831:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKRenderingLibraryAccess().getKRenderingLibraryAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,91,FOLLOW_119); 

            			newLeafNode(otherlv_1, grammarAccess.getKRenderingLibraryAccess().getKrenderingLibraryKeyword_1());
            		
            // InternalKGraph.g:5841:3: (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==31) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // InternalKGraph.g:5842:4: otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']'
                    {
                    otherlv_2=(Token)match(input,31,FOLLOW_25); 

                    				newLeafNode(otherlv_2, grammarAccess.getKRenderingLibraryAccess().getLeftSquareBracketKeyword_2_0());
                    			
                    // InternalKGraph.g:5846:4: ( (lv_persistentEntries_3_0= ruleProperty ) )*
                    loop126:
                    do {
                        int alt126=2;
                        int LA126_0 = input.LA(1);

                        if ( (LA126_0==RULE_ID) ) {
                            alt126=1;
                        }


                        switch (alt126) {
                    	case 1 :
                    	    // InternalKGraph.g:5847:5: (lv_persistentEntries_3_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:5847:5: (lv_persistentEntries_3_0= ruleProperty )
                    	    // InternalKGraph.g:5848:6: lv_persistentEntries_3_0= ruleProperty
                    	    {

                    	    						newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_25);
                    	    lv_persistentEntries_3_0=ruleProperty();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getKRenderingLibraryRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"persistentEntries",
                    	    							lv_persistentEntries_3_0,
                    	    							"de.cau.cs.kieler.kgraph.text.KGraph.Property");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop126;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,32,FOLLOW_14); 

                    				newLeafNode(otherlv_4, grammarAccess.getKRenderingLibraryAccess().getRightSquareBracketKeyword_2_2());
                    			

                    }
                    break;

            }

            otherlv_5=(Token)match(input,21,FOLLOW_120); 

            			newLeafNode(otherlv_5, grammarAccess.getKRenderingLibraryAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalKGraph.g:5874:3: ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )*
            loop128:
            do {
                int alt128=3;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==50||(LA128_0>=52 && LA128_0<=59)||(LA128_0>=62 && LA128_0<=65)) ) {
                    alt128=1;
                }
                else if ( (LA128_0==92) ) {
                    alt128=2;
                }


                switch (alt128) {
            	case 1 :
            	    // InternalKGraph.g:5875:4: ( (lv_renderings_6_0= ruleKRendering ) )
            	    {
            	    // InternalKGraph.g:5875:4: ( (lv_renderings_6_0= ruleKRendering ) )
            	    // InternalKGraph.g:5876:5: (lv_renderings_6_0= ruleKRendering )
            	    {
            	    // InternalKGraph.g:5876:5: (lv_renderings_6_0= ruleKRendering )
            	    // InternalKGraph.g:5877:6: lv_renderings_6_0= ruleKRendering
            	    {

            	    						newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_4_0_0());
            	    					
            	    pushFollow(FOLLOW_120);
            	    lv_renderings_6_0=ruleKRendering();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getKRenderingLibraryRule());
            	    						}
            	    						add(
            	    							current,
            	    							"renderings",
            	    							lv_renderings_6_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KRendering");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:5895:4: ( (lv_renderings_7_0= ruleKStyleHolder ) )
            	    {
            	    // InternalKGraph.g:5895:4: ( (lv_renderings_7_0= ruleKStyleHolder ) )
            	    // InternalKGraph.g:5896:5: (lv_renderings_7_0= ruleKStyleHolder )
            	    {
            	    // InternalKGraph.g:5896:5: (lv_renderings_7_0= ruleKStyleHolder )
            	    // InternalKGraph.g:5897:6: lv_renderings_7_0= ruleKStyleHolder
            	    {

            	    						newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKStyleHolderParserRuleCall_4_1_0());
            	    					
            	    pushFollow(FOLLOW_120);
            	    lv_renderings_7_0=ruleKStyleHolder();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getKRenderingLibraryRule());
            	    						}
            	    						add(
            	    							current,
            	    							"renderings",
            	    							lv_renderings_7_0,
            	    							"de.cau.cs.kieler.kgraph.text.KGraph.KStyleHolder");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop128;
                }
            } while (true);

            otherlv_8=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getKRenderingLibraryAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRenderingLibrary"


    // $ANTLR start "entryRuleKStyleHolder"
    // InternalKGraph.g:5923:1: entryRuleKStyleHolder returns [EObject current=null] : iv_ruleKStyleHolder= ruleKStyleHolder EOF ;
    public final EObject entryRuleKStyleHolder() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyleHolder = null;


        try {
            // InternalKGraph.g:5923:53: (iv_ruleKStyleHolder= ruleKStyleHolder EOF )
            // InternalKGraph.g:5924:2: iv_ruleKStyleHolder= ruleKStyleHolder EOF
            {
             newCompositeNode(grammarAccess.getKStyleHolderRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKStyleHolder=ruleKStyleHolder();

            state._fsp--;

             current =iv_ruleKStyleHolder; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKStyleHolder"


    // $ANTLR start "ruleKStyleHolder"
    // InternalKGraph.g:5930:1: ruleKStyleHolder returns [EObject current=null] : ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' ) ;
    public final EObject ruleKStyleHolder() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_id_2_0 = null;

        EObject lv_styles_4_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:5936:2: ( ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' ) )
            // InternalKGraph.g:5937:2: ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' )
            {
            // InternalKGraph.g:5937:2: ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' )
            // InternalKGraph.g:5938:3: () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}'
            {
            // InternalKGraph.g:5938:3: ()
            // InternalKGraph.g:5939:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKStyleHolderAccess().getKStyleHolderAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,92,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getKStyleHolderAccess().getKstylesTemplateKeyword_1());
            		
            // InternalKGraph.g:5949:3: ( (lv_id_2_0= ruleQualifiedID ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==RULE_ID) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // InternalKGraph.g:5950:4: (lv_id_2_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:5950:4: (lv_id_2_0= ruleQualifiedID )
                    // InternalKGraph.g:5951:5: lv_id_2_0= ruleQualifiedID
                    {

                    					newCompositeNode(grammarAccess.getKStyleHolderAccess().getIdQualifiedIDParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_14);
                    lv_id_2_0=ruleQualifiedID();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKStyleHolderRule());
                    					}
                    					set(
                    						current,
                    						"id",
                    						lv_id_2_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,21,FOLLOW_121); 

            			newLeafNode(otherlv_3, grammarAccess.getKStyleHolderAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalKGraph.g:5972:3: ( (lv_styles_4_0= ruleKStyle ) )*
            loop130:
            do {
                int alt130=2;
                int LA130_0 = input.LA(1);

                if ( ((LA130_0>=68 && LA130_0<=83)||(LA130_0>=86 && LA130_0<=87)||(LA130_0>=89 && LA130_0<=90)) ) {
                    alt130=1;
                }


                switch (alt130) {
            	case 1 :
            	    // InternalKGraph.g:5973:4: (lv_styles_4_0= ruleKStyle )
            	    {
            	    // InternalKGraph.g:5973:4: (lv_styles_4_0= ruleKStyle )
            	    // InternalKGraph.g:5974:5: lv_styles_4_0= ruleKStyle
            	    {

            	    					newCompositeNode(grammarAccess.getKStyleHolderAccess().getStylesKStyleParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_121);
            	    lv_styles_4_0=ruleKStyle();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getKStyleHolderRule());
            	    					}
            	    					add(
            	    						current,
            	    						"styles",
            	    						lv_styles_4_0,
            	    						"de.cau.cs.kieler.kgraph.text.KGraph.KStyle");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop130;
                }
            } while (true);

            otherlv_5=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getKStyleHolderAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKStyleHolder"


    // $ANTLR start "entryRuleKPlacement"
    // InternalKGraph.g:5999:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // InternalKGraph.g:5999:51: (iv_ruleKPlacement= ruleKPlacement EOF )
            // InternalKGraph.g:6000:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPlacement"


    // $ANTLR start "ruleKPlacement"
    // InternalKGraph.g:6006:1: ruleKPlacement returns [EObject current=null] : this_KGridPlacement_0= ruleKGridPlacement ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:6012:2: (this_KGridPlacement_0= ruleKGridPlacement )
            // InternalKGraph.g:6013:2: this_KGridPlacement_0= ruleKGridPlacement
            {

            		newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_KGridPlacement_0=ruleKGridPlacement();

            state._fsp--;


            		current = this_KGridPlacement_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPlacement"


    // $ANTLR start "entryRuleKGridPlacement"
    // InternalKGraph.g:6024:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // InternalKGraph.g:6024:55: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // InternalKGraph.g:6025:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKGridPlacement"


    // $ANTLR start "ruleKGridPlacement"
    // InternalKGraph.g:6031:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_numColumns_12_0=null;
        EObject lv_topLeft_6_0 = null;

        EObject lv_bottomRight_9_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:6037:2: ( ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6038:2: ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6038:2: ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6039:3: () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6039:3: ()
            // InternalKGraph.g:6040:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,93,FOLLOW_26); 

            			newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getGridKeyword_1());
            		
            otherlv_2=(Token)match(input,25,FOLLOW_122); 

            			newLeafNode(otherlv_2, grammarAccess.getKGridPlacementAccess().getColonKeyword_2());
            		
            // InternalKGraph.g:6054:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6055:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6055:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6056:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3());
            				
            // InternalKGraph.g:6059:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* )
            // InternalKGraph.g:6060:6: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6060:6: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )*
            loop131:
            do {
                int alt131=4;
                int LA131_0 = input.LA(1);

                if ( LA131_0 == 94 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 0) ) {
                    alt131=1;
                }
                else if ( LA131_0 == 95 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 1) ) {
                    alt131=2;
                }
                else if ( LA131_0 == 96 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 2) ) {
                    alt131=3;
                }


                switch (alt131) {
            	case 1 :
            	    // InternalKGraph.g:6061:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6061:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6062:5: {...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6062:111: ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6063:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalKGraph.g:6066:9: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6066:10: {...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "true");
            	    }
            	    // InternalKGraph.g:6066:19: (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6066:20: otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,94,FOLLOW_22); 

            	    									newLeafNode(otherlv_4, grammarAccess.getKGridPlacementAccess().getTopLeftAnchorKeyword_3_0_0());
            	    								
            	    otherlv_5=(Token)match(input,30,FOLLOW_64); 

            	    									newLeafNode(otherlv_5, grammarAccess.getKGridPlacementAccess().getEqualsSignKeyword_3_0_1());
            	    								
            	    // InternalKGraph.g:6074:9: ( (lv_topLeft_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6075:10: (lv_topLeft_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6075:10: (lv_topLeft_6_0= ruleKPosition )
            	    // InternalKGraph.g:6076:11: lv_topLeft_6_0= ruleKPosition
            	    {

            	    											newCompositeNode(grammarAccess.getKGridPlacementAccess().getTopLeftKPositionParserRuleCall_3_0_2_0());
            	    										
            	    pushFollow(FOLLOW_122);
            	    lv_topLeft_6_0=ruleKPosition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKGridPlacementRule());
            	    											}
            	    											set(
            	    												current,
            	    												"topLeft",
            	    												lv_topLeft_6_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:6099:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6099:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6100:5: {...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6100:111: ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6101:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalKGraph.g:6104:9: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6104:10: {...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "true");
            	    }
            	    // InternalKGraph.g:6104:19: (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6104:20: otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    {
            	    otherlv_7=(Token)match(input,95,FOLLOW_22); 

            	    									newLeafNode(otherlv_7, grammarAccess.getKGridPlacementAccess().getBottomRightAnchorKeyword_3_1_0());
            	    								
            	    otherlv_8=(Token)match(input,30,FOLLOW_64); 

            	    									newLeafNode(otherlv_8, grammarAccess.getKGridPlacementAccess().getEqualsSignKeyword_3_1_1());
            	    								
            	    // InternalKGraph.g:6112:9: ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    // InternalKGraph.g:6113:10: (lv_bottomRight_9_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6113:10: (lv_bottomRight_9_0= ruleKPosition )
            	    // InternalKGraph.g:6114:11: lv_bottomRight_9_0= ruleKPosition
            	    {

            	    											newCompositeNode(grammarAccess.getKGridPlacementAccess().getBottomRightKPositionParserRuleCall_3_1_2_0());
            	    										
            	    pushFollow(FOLLOW_122);
            	    lv_bottomRight_9_0=ruleKPosition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKGridPlacementRule());
            	    											}
            	    											set(
            	    												current,
            	    												"bottomRight",
            	    												lv_bottomRight_9_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:6137:4: ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6137:4: ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) )
            	    // InternalKGraph.g:6138:5: {...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:6138:111: ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) )
            	    // InternalKGraph.g:6139:6: ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalKGraph.g:6142:9: ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) )
            	    // InternalKGraph.g:6142:10: {...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "true");
            	    }
            	    // InternalKGraph.g:6142:19: (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) )
            	    // InternalKGraph.g:6142:20: otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) )
            	    {
            	    otherlv_10=(Token)match(input,96,FOLLOW_22); 

            	    									newLeafNode(otherlv_10, grammarAccess.getKGridPlacementAccess().getColumnsKeyword_3_2_0());
            	    								
            	    otherlv_11=(Token)match(input,30,FOLLOW_123); 

            	    									newLeafNode(otherlv_11, grammarAccess.getKGridPlacementAccess().getEqualsSignKeyword_3_2_1());
            	    								
            	    // InternalKGraph.g:6150:9: ( (lv_numColumns_12_0= RULE_NATURAL ) )
            	    // InternalKGraph.g:6151:10: (lv_numColumns_12_0= RULE_NATURAL )
            	    {
            	    // InternalKGraph.g:6151:10: (lv_numColumns_12_0= RULE_NATURAL )
            	    // InternalKGraph.g:6152:11: lv_numColumns_12_0= RULE_NATURAL
            	    {
            	    lv_numColumns_12_0=(Token)match(input,RULE_NATURAL,FOLLOW_122); 

            	    											newLeafNode(lv_numColumns_12_0, grammarAccess.getKGridPlacementAccess().getNumColumnsNATURALTerminalRuleCall_3_2_2_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getKGridPlacementRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"numColumns",
            	    												lv_numColumns_12_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.NATURAL");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop131;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKGridPlacement"


    // $ANTLR start "entryRuleKPlacementData"
    // InternalKGraph.g:6185:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // InternalKGraph.g:6185:55: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // InternalKGraph.g:6186:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPlacementData"


    // $ANTLR start "ruleKPlacementData"
    // InternalKGraph.g:6192:1: ruleKPlacementData returns [EObject current=null] : (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KAreaPlacementData_0 = null;

        EObject this_KPointPlacementData_1 = null;

        EObject this_KGridPlacementData_2 = null;

        EObject this_KDecoratorPlacementData_3 = null;



        	enterRule();

        try {
            // InternalKGraph.g:6198:2: ( (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData ) )
            // InternalKGraph.g:6199:2: (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData )
            {
            // InternalKGraph.g:6199:2: (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData )
            int alt132=4;
            switch ( input.LA(1) ) {
            case 97:
                {
                alt132=1;
                }
                break;
            case 98:
                {
                alt132=2;
                }
                break;
            case 106:
                {
                alt132=3;
                }
                break;
            case 111:
                {
                alt132=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 132, 0, input);

                throw nvae;
            }

            switch (alt132) {
                case 1 :
                    // InternalKGraph.g:6200:3: this_KAreaPlacementData_0= ruleKAreaPlacementData
                    {

                    			newCompositeNode(grammarAccess.getKPlacementDataAccess().getKAreaPlacementDataParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_KAreaPlacementData_0=ruleKAreaPlacementData();

                    state._fsp--;


                    			current = this_KAreaPlacementData_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:6209:3: this_KPointPlacementData_1= ruleKPointPlacementData
                    {

                    			newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPointPlacementDataParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_KPointPlacementData_1=ruleKPointPlacementData();

                    state._fsp--;


                    			current = this_KPointPlacementData_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:6218:3: this_KGridPlacementData_2= ruleKGridPlacementData
                    {

                    			newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_KGridPlacementData_2=ruleKGridPlacementData();

                    state._fsp--;


                    			current = this_KGridPlacementData_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:6227:3: this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData
                    {

                    			newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_KDecoratorPlacementData_3=ruleKDecoratorPlacementData();

                    state._fsp--;


                    			current = this_KDecoratorPlacementData_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPlacementData"


    // $ANTLR start "entryRuleKAreaPlacementData"
    // InternalKGraph.g:6239:1: entryRuleKAreaPlacementData returns [EObject current=null] : iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF ;
    public final EObject entryRuleKAreaPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKAreaPlacementData = null;


        try {
            // InternalKGraph.g:6239:59: (iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF )
            // InternalKGraph.g:6240:2: iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKAreaPlacementDataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKAreaPlacementData=ruleKAreaPlacementData();

            state._fsp--;

             current =iv_ruleKAreaPlacementData; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKAreaPlacementData"


    // $ANTLR start "ruleKAreaPlacementData"
    // InternalKGraph.g:6246:1: ruleKAreaPlacementData returns [EObject current=null] : ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) ) ;
    public final EObject ruleKAreaPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_topLeft_6_0 = null;

        EObject lv_bottomRight_9_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:6252:2: ( ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6253:2: ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6253:2: ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6254:3: () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6254:3: ()
            // InternalKGraph.g:6255:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKAreaPlacementDataAccess().getKAreaPlacementDataAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,97,FOLLOW_26); 

            			newLeafNode(otherlv_1, grammarAccess.getKAreaPlacementDataAccess().getAreaDataKeyword_1());
            		
            otherlv_2=(Token)match(input,25,FOLLOW_124); 

            			newLeafNode(otherlv_2, grammarAccess.getKAreaPlacementDataAccess().getColonKeyword_2());
            		
            // InternalKGraph.g:6269:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6270:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6270:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6271:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3());
            				
            // InternalKGraph.g:6274:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* )
            // InternalKGraph.g:6275:6: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6275:6: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )*
            loop133:
            do {
                int alt133=3;
                int LA133_0 = input.LA(1);

                if ( LA133_0 == 94 && getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
                    alt133=1;
                }
                else if ( LA133_0 == 95 && getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
                    alt133=2;
                }


                switch (alt133) {
            	case 1 :
            	    // InternalKGraph.g:6276:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6276:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6277:5: {...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6277:115: ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6278:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalKGraph.g:6281:9: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6281:10: {...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6281:19: (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6281:20: otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,94,FOLLOW_22); 

            	    									newLeafNode(otherlv_4, grammarAccess.getKAreaPlacementDataAccess().getTopLeftAnchorKeyword_3_0_0());
            	    								
            	    otherlv_5=(Token)match(input,30,FOLLOW_64); 

            	    									newLeafNode(otherlv_5, grammarAccess.getKAreaPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	    								
            	    // InternalKGraph.g:6289:9: ( (lv_topLeft_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6290:10: (lv_topLeft_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6290:10: (lv_topLeft_6_0= ruleKPosition )
            	    // InternalKGraph.g:6291:11: lv_topLeft_6_0= ruleKPosition
            	    {

            	    											newCompositeNode(grammarAccess.getKAreaPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0_2_0());
            	    										
            	    pushFollow(FOLLOW_124);
            	    lv_topLeft_6_0=ruleKPosition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKAreaPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"topLeft",
            	    												lv_topLeft_6_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:6314:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6314:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6315:5: {...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6315:115: ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6316:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalKGraph.g:6319:9: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6319:10: {...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6319:19: (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6319:20: otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    {
            	    otherlv_7=(Token)match(input,95,FOLLOW_22); 

            	    									newLeafNode(otherlv_7, grammarAccess.getKAreaPlacementDataAccess().getBottomRightAnchorKeyword_3_1_0());
            	    								
            	    otherlv_8=(Token)match(input,30,FOLLOW_64); 

            	    									newLeafNode(otherlv_8, grammarAccess.getKAreaPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	    								
            	    // InternalKGraph.g:6327:9: ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    // InternalKGraph.g:6328:10: (lv_bottomRight_9_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6328:10: (lv_bottomRight_9_0= ruleKPosition )
            	    // InternalKGraph.g:6329:11: lv_bottomRight_9_0= ruleKPosition
            	    {

            	    											newCompositeNode(grammarAccess.getKAreaPlacementDataAccess().getBottomRightKPositionParserRuleCall_3_1_2_0());
            	    										
            	    pushFollow(FOLLOW_124);
            	    lv_bottomRight_9_0=ruleKPosition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKAreaPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"bottomRight",
            	    												lv_bottomRight_9_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop133;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKAreaPlacementData"


    // $ANTLR start "entryRuleKPointPlacementData"
    // InternalKGraph.g:6363:1: entryRuleKPointPlacementData returns [EObject current=null] : iv_ruleKPointPlacementData= ruleKPointPlacementData EOF ;
    public final EObject entryRuleKPointPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPointPlacementData = null;


        try {
            // InternalKGraph.g:6363:60: (iv_ruleKPointPlacementData= ruleKPointPlacementData EOF )
            // InternalKGraph.g:6364:2: iv_ruleKPointPlacementData= ruleKPointPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPointPlacementDataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPointPlacementData=ruleKPointPlacementData();

            state._fsp--;

             current =iv_ruleKPointPlacementData; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPointPlacementData"


    // $ANTLR start "ruleKPointPlacementData"
    // InternalKGraph.g:6370:1: ruleKPointPlacementData returns [EObject current=null] : ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ;
    public final EObject ruleKPointPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        EObject lv_referencePoint_6_0 = null;

        AntlrDatatypeRuleToken lv_minWidth_9_0 = null;

        AntlrDatatypeRuleToken lv_minHeight_12_0 = null;

        Enumerator lv_horizontalAlignment_15_0 = null;

        Enumerator lv_verticalAlignment_18_0 = null;

        AntlrDatatypeRuleToken lv_horizontalMargin_21_0 = null;

        AntlrDatatypeRuleToken lv_verticalMargin_24_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:6376:2: ( ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6377:2: ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6377:2: ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6378:3: () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6378:3: ()
            // InternalKGraph.g:6379:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKPointPlacementDataAccess().getKPointPlacementDataAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,98,FOLLOW_26); 

            			newLeafNode(otherlv_1, grammarAccess.getKPointPlacementDataAccess().getPointDataKeyword_1());
            		
            otherlv_2=(Token)match(input,25,FOLLOW_125); 

            			newLeafNode(otherlv_2, grammarAccess.getKPointPlacementDataAccess().getColonKeyword_2());
            		
            // InternalKGraph.g:6393:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6394:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6394:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6395:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            				
            // InternalKGraph.g:6398:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* )
            // InternalKGraph.g:6399:6: ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6399:6: ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )*
            loop134:
            do {
                int alt134=8;
                int LA134_0 = input.LA(1);

                if ( LA134_0 == 99 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
                    alt134=1;
                }
                else if ( LA134_0 == 100 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
                    alt134=2;
                }
                else if ( LA134_0 == 101 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
                    alt134=3;
                }
                else if ( LA134_0 == 102 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
                    alt134=4;
                }
                else if ( LA134_0 == 103 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
                    alt134=5;
                }
                else if ( LA134_0 == 104 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
                    alt134=6;
                }
                else if ( LA134_0 == 105 && getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 6) ) {
                    alt134=7;
                }


                switch (alt134) {
            	case 1 :
            	    // InternalKGraph.g:6400:4: ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6400:4: ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6401:5: {...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6401:116: ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6402:6: ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalKGraph.g:6405:9: ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6405:10: {...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6405:19: (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6405:20: otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,99,FOLLOW_22); 

            	    									newLeafNode(otherlv_4, grammarAccess.getKPointPlacementDataAccess().getReferencePointKeyword_3_0_0());
            	    								
            	    otherlv_5=(Token)match(input,30,FOLLOW_64); 

            	    									newLeafNode(otherlv_5, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	    								
            	    // InternalKGraph.g:6413:9: ( (lv_referencePoint_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6414:10: (lv_referencePoint_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6414:10: (lv_referencePoint_6_0= ruleKPosition )
            	    // InternalKGraph.g:6415:11: lv_referencePoint_6_0= ruleKPosition
            	    {

            	    											newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getReferencePointKPositionParserRuleCall_3_0_2_0());
            	    										
            	    pushFollow(FOLLOW_125);
            	    lv_referencePoint_6_0=ruleKPosition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"referencePoint",
            	    												lv_referencePoint_6_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:6438:4: ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6438:4: ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6439:5: {...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6439:116: ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6440:6: ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalKGraph.g:6443:9: ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6443:10: {...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6443:19: (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6443:20: otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) )
            	    {
            	    otherlv_7=(Token)match(input,100,FOLLOW_22); 

            	    									newLeafNode(otherlv_7, grammarAccess.getKPointPlacementDataAccess().getMinimalWidthKeyword_3_1_0());
            	    								
            	    otherlv_8=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_8, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	    								
            	    // InternalKGraph.g:6451:9: ( (lv_minWidth_9_0= ruleFloat ) )
            	    // InternalKGraph.g:6452:10: (lv_minWidth_9_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6452:10: (lv_minWidth_9_0= ruleFloat )
            	    // InternalKGraph.g:6453:11: lv_minWidth_9_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getMinWidthFloatParserRuleCall_3_1_2_0());
            	    										
            	    pushFollow(FOLLOW_125);
            	    lv_minWidth_9_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"minWidth",
            	    												lv_minWidth_9_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:6476:4: ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6476:4: ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6477:5: {...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:6477:116: ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6478:6: ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalKGraph.g:6481:9: ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6481:10: {...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6481:19: (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6481:20: otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) )
            	    {
            	    otherlv_10=(Token)match(input,101,FOLLOW_22); 

            	    									newLeafNode(otherlv_10, grammarAccess.getKPointPlacementDataAccess().getMinimalHeightKeyword_3_2_0());
            	    								
            	    otherlv_11=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_11, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_2_1());
            	    								
            	    // InternalKGraph.g:6489:9: ( (lv_minHeight_12_0= ruleFloat ) )
            	    // InternalKGraph.g:6490:10: (lv_minHeight_12_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6490:10: (lv_minHeight_12_0= ruleFloat )
            	    // InternalKGraph.g:6491:11: lv_minHeight_12_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getMinHeightFloatParserRuleCall_3_2_2_0());
            	    										
            	    pushFollow(FOLLOW_125);
            	    lv_minHeight_12_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"minHeight",
            	    												lv_minHeight_12_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalKGraph.g:6514:4: ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6514:4: ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) )
            	    // InternalKGraph.g:6515:5: {...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalKGraph.g:6515:116: ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) )
            	    // InternalKGraph.g:6516:6: ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 3);
            	    					
            	    // InternalKGraph.g:6519:9: ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) )
            	    // InternalKGraph.g:6519:10: {...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6519:19: (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) )
            	    // InternalKGraph.g:6519:20: otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) )
            	    {
            	    otherlv_13=(Token)match(input,102,FOLLOW_22); 

            	    									newLeafNode(otherlv_13, grammarAccess.getKPointPlacementDataAccess().getHorizontalAlignmentKeyword_3_3_0());
            	    								
            	    otherlv_14=(Token)match(input,30,FOLLOW_92); 

            	    									newLeafNode(otherlv_14, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_3_1());
            	    								
            	    // InternalKGraph.g:6527:9: ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) )
            	    // InternalKGraph.g:6528:10: (lv_horizontalAlignment_15_0= ruleHorizontalAlignment )
            	    {
            	    // InternalKGraph.g:6528:10: (lv_horizontalAlignment_15_0= ruleHorizontalAlignment )
            	    // InternalKGraph.g:6529:11: lv_horizontalAlignment_15_0= ruleHorizontalAlignment
            	    {

            	    											newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_3_3_2_0());
            	    										
            	    pushFollow(FOLLOW_125);
            	    lv_horizontalAlignment_15_0=ruleHorizontalAlignment();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"horizontalAlignment",
            	    												lv_horizontalAlignment_15_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.HorizontalAlignment");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalKGraph.g:6552:4: ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6552:4: ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) )
            	    // InternalKGraph.g:6553:5: {...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalKGraph.g:6553:116: ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) )
            	    // InternalKGraph.g:6554:6: ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 4);
            	    					
            	    // InternalKGraph.g:6557:9: ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) )
            	    // InternalKGraph.g:6557:10: {...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6557:19: (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) )
            	    // InternalKGraph.g:6557:20: otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) )
            	    {
            	    otherlv_16=(Token)match(input,103,FOLLOW_22); 

            	    									newLeafNode(otherlv_16, grammarAccess.getKPointPlacementDataAccess().getVerticalAlignmentKeyword_3_4_0());
            	    								
            	    otherlv_17=(Token)match(input,30,FOLLOW_95); 

            	    									newLeafNode(otherlv_17, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_4_1());
            	    								
            	    // InternalKGraph.g:6565:9: ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) )
            	    // InternalKGraph.g:6566:10: (lv_verticalAlignment_18_0= ruleVerticalAlignment )
            	    {
            	    // InternalKGraph.g:6566:10: (lv_verticalAlignment_18_0= ruleVerticalAlignment )
            	    // InternalKGraph.g:6567:11: lv_verticalAlignment_18_0= ruleVerticalAlignment
            	    {

            	    											newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_3_4_2_0());
            	    										
            	    pushFollow(FOLLOW_125);
            	    lv_verticalAlignment_18_0=ruleVerticalAlignment();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verticalAlignment",
            	    												lv_verticalAlignment_18_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.VerticalAlignment");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalKGraph.g:6590:4: ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6590:4: ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6591:5: {...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 5)");
            	    }
            	    // InternalKGraph.g:6591:116: ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6592:6: ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 5);
            	    					
            	    // InternalKGraph.g:6595:9: ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6595:10: {...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6595:19: (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6595:20: otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) )
            	    {
            	    otherlv_19=(Token)match(input,104,FOLLOW_22); 

            	    									newLeafNode(otherlv_19, grammarAccess.getKPointPlacementDataAccess().getHorizontalMarginKeyword_3_5_0());
            	    								
            	    otherlv_20=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_20, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_5_1());
            	    								
            	    // InternalKGraph.g:6603:9: ( (lv_horizontalMargin_21_0= ruleFloat ) )
            	    // InternalKGraph.g:6604:10: (lv_horizontalMargin_21_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6604:10: (lv_horizontalMargin_21_0= ruleFloat )
            	    // InternalKGraph.g:6605:11: lv_horizontalMargin_21_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getHorizontalMarginFloatParserRuleCall_3_5_2_0());
            	    										
            	    pushFollow(FOLLOW_125);
            	    lv_horizontalMargin_21_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"horizontalMargin",
            	    												lv_horizontalMargin_21_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalKGraph.g:6628:4: ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6628:4: ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6629:5: {...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 6)");
            	    }
            	    // InternalKGraph.g:6629:116: ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6630:6: ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 6);
            	    					
            	    // InternalKGraph.g:6633:9: ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6633:10: {...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6633:19: (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6633:20: otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) )
            	    {
            	    otherlv_22=(Token)match(input,105,FOLLOW_22); 

            	    									newLeafNode(otherlv_22, grammarAccess.getKPointPlacementDataAccess().getVerticalMarginKeyword_3_6_0());
            	    								
            	    otherlv_23=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_23, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_6_1());
            	    								
            	    // InternalKGraph.g:6641:9: ( (lv_verticalMargin_24_0= ruleFloat ) )
            	    // InternalKGraph.g:6642:10: (lv_verticalMargin_24_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6642:10: (lv_verticalMargin_24_0= ruleFloat )
            	    // InternalKGraph.g:6643:11: lv_verticalMargin_24_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getVerticalMarginFloatParserRuleCall_3_6_2_0());
            	    										
            	    pushFollow(FOLLOW_125);
            	    lv_verticalMargin_24_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verticalMargin",
            	    												lv_verticalMargin_24_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPointPlacementData"


    // $ANTLR start "entryRuleKGridPlacementData"
    // InternalKGraph.g:6677:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // InternalKGraph.g:6677:59: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // InternalKGraph.g:6678:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKGridPlacementData"


    // $ANTLR start "ruleKGridPlacementData"
    // InternalKGraph.g:6684:1: ruleKGridPlacementData returns [EObject current=null] : ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) ;
    public final EObject ruleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token lv_flexibleWidth_18_0=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token lv_flexibleHeight_21_0=null;
        EObject lv_topLeft_6_0 = null;

        EObject lv_bottomRight_9_0 = null;

        AntlrDatatypeRuleToken lv_minCellWidth_12_0 = null;

        AntlrDatatypeRuleToken lv_minCellHeight_15_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:6690:2: ( ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6691:2: ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6691:2: ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6692:3: () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6692:3: ()
            // InternalKGraph.g:6693:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,106,FOLLOW_26); 

            			newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getGridDataKeyword_1());
            		
            otherlv_2=(Token)match(input,25,FOLLOW_126); 

            			newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getColonKeyword_2());
            		
            // InternalKGraph.g:6707:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6708:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6708:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6709:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            				
            // InternalKGraph.g:6712:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            // InternalKGraph.g:6713:6: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6713:6: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )*
            loop135:
            do {
                int alt135=7;
                int LA135_0 = input.LA(1);

                if ( LA135_0 == 94 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
                    alt135=1;
                }
                else if ( LA135_0 == 95 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
                    alt135=2;
                }
                else if ( LA135_0 == 107 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
                    alt135=3;
                }
                else if ( LA135_0 == 108 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
                    alt135=4;
                }
                else if ( LA135_0 == 109 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
                    alt135=5;
                }
                else if ( LA135_0 == 110 && getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
                    alt135=6;
                }


                switch (alt135) {
            	case 1 :
            	    // InternalKGraph.g:6714:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6714:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6715:5: {...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6715:115: ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6716:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalKGraph.g:6719:9: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6719:10: {...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6719:19: (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6719:20: otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,94,FOLLOW_22); 

            	    									newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getTopLeftAnchorKeyword_3_0_0());
            	    								
            	    otherlv_5=(Token)match(input,30,FOLLOW_64); 

            	    									newLeafNode(otherlv_5, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	    								
            	    // InternalKGraph.g:6727:9: ( (lv_topLeft_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6728:10: (lv_topLeft_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6728:10: (lv_topLeft_6_0= ruleKPosition )
            	    // InternalKGraph.g:6729:11: lv_topLeft_6_0= ruleKPosition
            	    {

            	    											newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0_2_0());
            	    										
            	    pushFollow(FOLLOW_126);
            	    lv_topLeft_6_0=ruleKPosition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"topLeft",
            	    												lv_topLeft_6_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:6752:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6752:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6753:5: {...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6753:115: ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6754:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalKGraph.g:6757:9: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6757:10: {...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6757:19: (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6757:20: otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    {
            	    otherlv_7=(Token)match(input,95,FOLLOW_22); 

            	    									newLeafNode(otherlv_7, grammarAccess.getKGridPlacementDataAccess().getBottomRightAnchorKeyword_3_1_0());
            	    								
            	    otherlv_8=(Token)match(input,30,FOLLOW_64); 

            	    									newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	    								
            	    // InternalKGraph.g:6765:9: ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    // InternalKGraph.g:6766:10: (lv_bottomRight_9_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6766:10: (lv_bottomRight_9_0= ruleKPosition )
            	    // InternalKGraph.g:6767:11: lv_bottomRight_9_0= ruleKPosition
            	    {

            	    											newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getBottomRightKPositionParserRuleCall_3_1_2_0());
            	    										
            	    pushFollow(FOLLOW_126);
            	    lv_bottomRight_9_0=ruleKPosition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"bottomRight",
            	    												lv_bottomRight_9_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.KPosition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:6790:4: ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6790:4: ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6791:5: {...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:6791:115: ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6792:6: ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalKGraph.g:6795:9: ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6795:10: {...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6795:19: (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6795:20: otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) )
            	    {
            	    otherlv_10=(Token)match(input,107,FOLLOW_22); 

            	    									newLeafNode(otherlv_10, grammarAccess.getKGridPlacementDataAccess().getMinCellWidthKeyword_3_2_0());
            	    								
            	    otherlv_11=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_11, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_2_1());
            	    								
            	    // InternalKGraph.g:6803:9: ( (lv_minCellWidth_12_0= ruleFloat ) )
            	    // InternalKGraph.g:6804:10: (lv_minCellWidth_12_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6804:10: (lv_minCellWidth_12_0= ruleFloat )
            	    // InternalKGraph.g:6805:11: lv_minCellWidth_12_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMinCellWidthFloatParserRuleCall_3_2_2_0());
            	    										
            	    pushFollow(FOLLOW_126);
            	    lv_minCellWidth_12_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"minCellWidth",
            	    												lv_minCellWidth_12_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalKGraph.g:6828:4: ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6828:4: ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6829:5: {...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalKGraph.g:6829:115: ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6830:6: ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 3);
            	    					
            	    // InternalKGraph.g:6833:9: ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6833:10: {...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6833:19: (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6833:20: otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) )
            	    {
            	    otherlv_13=(Token)match(input,108,FOLLOW_22); 

            	    									newLeafNode(otherlv_13, grammarAccess.getKGridPlacementDataAccess().getMinCellHeightKeyword_3_3_0());
            	    								
            	    otherlv_14=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_14, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_3_1());
            	    								
            	    // InternalKGraph.g:6841:9: ( (lv_minCellHeight_15_0= ruleFloat ) )
            	    // InternalKGraph.g:6842:10: (lv_minCellHeight_15_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6842:10: (lv_minCellHeight_15_0= ruleFloat )
            	    // InternalKGraph.g:6843:11: lv_minCellHeight_15_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMinCellHeightFloatParserRuleCall_3_3_2_0());
            	    										
            	    pushFollow(FOLLOW_126);
            	    lv_minCellHeight_15_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"minCellHeight",
            	    												lv_minCellHeight_15_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalKGraph.g:6866:4: ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6866:4: ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) )
            	    // InternalKGraph.g:6867:5: {...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalKGraph.g:6867:115: ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) )
            	    // InternalKGraph.g:6868:6: ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 4);
            	    					
            	    // InternalKGraph.g:6871:9: ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) )
            	    // InternalKGraph.g:6871:10: {...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6871:19: (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) )
            	    // InternalKGraph.g:6871:20: otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) )
            	    {
            	    otherlv_16=(Token)match(input,109,FOLLOW_22); 

            	    									newLeafNode(otherlv_16, grammarAccess.getKGridPlacementDataAccess().getFlexibleWidthKeyword_3_4_0());
            	    								
            	    otherlv_17=(Token)match(input,30,FOLLOW_55); 

            	    									newLeafNode(otherlv_17, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_4_1());
            	    								
            	    // InternalKGraph.g:6879:9: ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) )
            	    // InternalKGraph.g:6880:10: (lv_flexibleWidth_18_0= RULE_BOOLEAN )
            	    {
            	    // InternalKGraph.g:6880:10: (lv_flexibleWidth_18_0= RULE_BOOLEAN )
            	    // InternalKGraph.g:6881:11: lv_flexibleWidth_18_0= RULE_BOOLEAN
            	    {
            	    lv_flexibleWidth_18_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_126); 

            	    											newLeafNode(lv_flexibleWidth_18_0, grammarAccess.getKGridPlacementDataAccess().getFlexibleWidthBOOLEANTerminalRuleCall_3_4_2_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getKGridPlacementDataRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"flexibleWidth",
            	    												lv_flexibleWidth_18_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.BOOLEAN");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalKGraph.g:6903:4: ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6903:4: ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) )
            	    // InternalKGraph.g:6904:5: {...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 5)");
            	    }
            	    // InternalKGraph.g:6904:115: ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) )
            	    // InternalKGraph.g:6905:6: ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 5);
            	    					
            	    // InternalKGraph.g:6908:9: ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) )
            	    // InternalKGraph.g:6908:10: {...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6908:19: (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) )
            	    // InternalKGraph.g:6908:20: otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) )
            	    {
            	    otherlv_19=(Token)match(input,110,FOLLOW_22); 

            	    									newLeafNode(otherlv_19, grammarAccess.getKGridPlacementDataAccess().getFlexibleHeightKeyword_3_5_0());
            	    								
            	    otherlv_20=(Token)match(input,30,FOLLOW_55); 

            	    									newLeafNode(otherlv_20, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_5_1());
            	    								
            	    // InternalKGraph.g:6916:9: ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) )
            	    // InternalKGraph.g:6917:10: (lv_flexibleHeight_21_0= RULE_BOOLEAN )
            	    {
            	    // InternalKGraph.g:6917:10: (lv_flexibleHeight_21_0= RULE_BOOLEAN )
            	    // InternalKGraph.g:6918:11: lv_flexibleHeight_21_0= RULE_BOOLEAN
            	    {
            	    lv_flexibleHeight_21_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_126); 

            	    											newLeafNode(lv_flexibleHeight_21_0, grammarAccess.getKGridPlacementDataAccess().getFlexibleHeightBOOLEANTerminalRuleCall_3_5_2_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getKGridPlacementDataRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"flexibleHeight",
            	    												lv_flexibleHeight_21_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.BOOLEAN");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop135;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKGridPlacementData"


    // $ANTLR start "entryRuleKDecoratorPlacementData"
    // InternalKGraph.g:6951:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // InternalKGraph.g:6951:64: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // InternalKGraph.g:6952:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKDecoratorPlacementData"


    // $ANTLR start "ruleKDecoratorPlacementData"
    // InternalKGraph.g:6958:1: ruleKDecoratorPlacementData returns [EObject current=null] : ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) ;
    public final EObject ruleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token lv_rotateWithLine_24_0=null;
        AntlrDatatypeRuleToken lv_xOffset_6_0 = null;

        AntlrDatatypeRuleToken lv_yOffset_9_0 = null;

        AntlrDatatypeRuleToken lv_width_12_0 = null;

        AntlrDatatypeRuleToken lv_height_15_0 = null;

        AntlrDatatypeRuleToken lv_relative_18_0 = null;

        AntlrDatatypeRuleToken lv_absolute_21_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:6964:2: ( ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6965:2: ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6965:2: ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6966:3: () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6966:3: ()
            // InternalKGraph.g:6967:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKDecoratorPlacementDataAccess().getKDecoratorPlacementDataAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,111,FOLLOW_26); 

            			newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getDecoratorDataKeyword_1());
            		
            otherlv_2=(Token)match(input,25,FOLLOW_127); 

            			newLeafNode(otherlv_2, grammarAccess.getKDecoratorPlacementDataAccess().getColonKeyword_2());
            		
            // InternalKGraph.g:6981:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6982:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6982:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6983:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            				
            // InternalKGraph.g:6986:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            // InternalKGraph.g:6987:6: ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6987:6: ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )*
            loop136:
            do {
                int alt136=8;
                int LA136_0 = input.LA(1);

                if ( LA136_0 == 112 && getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
                    alt136=1;
                }
                else if ( LA136_0 == 113 && getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
                    alt136=2;
                }
                else if ( LA136_0 == 37 && getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
                    alt136=3;
                }
                else if ( LA136_0 == 38 && getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
                    alt136=4;
                }
                else if ( LA136_0 == 114 && getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
                    alt136=5;
                }
                else if ( LA136_0 == 115 && getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
                    alt136=6;
                }
                else if ( LA136_0 == 116 && getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 6) ) {
                    alt136=7;
                }


                switch (alt136) {
            	case 1 :
            	    // InternalKGraph.g:6988:4: ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6988:4: ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6989:5: {...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6989:120: ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6990:6: ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalKGraph.g:6993:9: ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6993:10: {...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6993:19: (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6993:20: otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) )
            	    {
            	    otherlv_4=(Token)match(input,112,FOLLOW_22); 

            	    									newLeafNode(otherlv_4, grammarAccess.getKDecoratorPlacementDataAccess().getXoffsetKeyword_3_0_0());
            	    								
            	    otherlv_5=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	    								
            	    // InternalKGraph.g:7001:9: ( (lv_xOffset_6_0= ruleFloat ) )
            	    // InternalKGraph.g:7002:10: (lv_xOffset_6_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7002:10: (lv_xOffset_6_0= ruleFloat )
            	    // InternalKGraph.g:7003:11: lv_xOffset_6_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetFloatParserRuleCall_3_0_2_0());
            	    										
            	    pushFollow(FOLLOW_127);
            	    lv_xOffset_6_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"xOffset",
            	    												lv_xOffset_6_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalKGraph.g:7026:4: ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7026:4: ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7027:5: {...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:7027:120: ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7028:6: ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalKGraph.g:7031:9: ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7031:10: {...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7031:19: (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7031:20: otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) )
            	    {
            	    otherlv_7=(Token)match(input,113,FOLLOW_22); 

            	    									newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYoffsetKeyword_3_1_0());
            	    								
            	    otherlv_8=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_8, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	    								
            	    // InternalKGraph.g:7039:9: ( (lv_yOffset_9_0= ruleFloat ) )
            	    // InternalKGraph.g:7040:10: (lv_yOffset_9_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7040:10: (lv_yOffset_9_0= ruleFloat )
            	    // InternalKGraph.g:7041:11: lv_yOffset_9_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetFloatParserRuleCall_3_1_2_0());
            	    										
            	    pushFollow(FOLLOW_127);
            	    lv_yOffset_9_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"yOffset",
            	    												lv_yOffset_9_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalKGraph.g:7064:4: ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7064:4: ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7065:5: {...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:7065:120: ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7066:6: ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalKGraph.g:7069:9: ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7069:10: {...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7069:19: (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7069:20: otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) )
            	    {
            	    otherlv_10=(Token)match(input,37,FOLLOW_22); 

            	    									newLeafNode(otherlv_10, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_3_2_0());
            	    								
            	    otherlv_11=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_2_1());
            	    								
            	    // InternalKGraph.g:7077:9: ( (lv_width_12_0= ruleFloat ) )
            	    // InternalKGraph.g:7078:10: (lv_width_12_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7078:10: (lv_width_12_0= ruleFloat )
            	    // InternalKGraph.g:7079:11: lv_width_12_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthFloatParserRuleCall_3_2_2_0());
            	    										
            	    pushFollow(FOLLOW_127);
            	    lv_width_12_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"width",
            	    												lv_width_12_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalKGraph.g:7102:4: ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7102:4: ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7103:5: {...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalKGraph.g:7103:120: ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7104:6: ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 3);
            	    					
            	    // InternalKGraph.g:7107:9: ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7107:10: {...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7107:19: (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7107:20: otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) )
            	    {
            	    otherlv_13=(Token)match(input,38,FOLLOW_22); 

            	    									newLeafNode(otherlv_13, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_3_3_0());
            	    								
            	    otherlv_14=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_14, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_3_1());
            	    								
            	    // InternalKGraph.g:7115:9: ( (lv_height_15_0= ruleFloat ) )
            	    // InternalKGraph.g:7116:10: (lv_height_15_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7116:10: (lv_height_15_0= ruleFloat )
            	    // InternalKGraph.g:7117:11: lv_height_15_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightFloatParserRuleCall_3_3_2_0());
            	    										
            	    pushFollow(FOLLOW_127);
            	    lv_height_15_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"height",
            	    												lv_height_15_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalKGraph.g:7140:4: ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7140:4: ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7141:5: {...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalKGraph.g:7141:120: ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7142:6: ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 4);
            	    					
            	    // InternalKGraph.g:7145:9: ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7145:10: {...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7145:19: (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7145:20: otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) )
            	    {
            	    otherlv_16=(Token)match(input,114,FOLLOW_22); 

            	    									newLeafNode(otherlv_16, grammarAccess.getKDecoratorPlacementDataAccess().getRelativePosKeyword_3_4_0());
            	    								
            	    otherlv_17=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_17, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_4_1());
            	    								
            	    // InternalKGraph.g:7153:9: ( (lv_relative_18_0= ruleFloat ) )
            	    // InternalKGraph.g:7154:10: (lv_relative_18_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7154:10: (lv_relative_18_0= ruleFloat )
            	    // InternalKGraph.g:7155:11: lv_relative_18_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getRelativeFloatParserRuleCall_3_4_2_0());
            	    										
            	    pushFollow(FOLLOW_127);
            	    lv_relative_18_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"relative",
            	    												lv_relative_18_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalKGraph.g:7178:4: ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7178:4: ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7179:5: {...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 5)");
            	    }
            	    // InternalKGraph.g:7179:120: ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7180:6: ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 5);
            	    					
            	    // InternalKGraph.g:7183:9: ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7183:10: {...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7183:19: (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7183:20: otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) )
            	    {
            	    otherlv_19=(Token)match(input,115,FOLLOW_22); 

            	    									newLeafNode(otherlv_19, grammarAccess.getKDecoratorPlacementDataAccess().getAbsolutePosKeyword_3_5_0());
            	    								
            	    otherlv_20=(Token)match(input,30,FOLLOW_28); 

            	    									newLeafNode(otherlv_20, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_5_1());
            	    								
            	    // InternalKGraph.g:7191:9: ( (lv_absolute_21_0= ruleFloat ) )
            	    // InternalKGraph.g:7192:10: (lv_absolute_21_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7192:10: (lv_absolute_21_0= ruleFloat )
            	    // InternalKGraph.g:7193:11: lv_absolute_21_0= ruleFloat
            	    {

            	    											newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getAbsoluteFloatParserRuleCall_3_5_2_0());
            	    										
            	    pushFollow(FOLLOW_127);
            	    lv_absolute_21_0=ruleFloat();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	    											}
            	    											set(
            	    												current,
            	    												"absolute",
            	    												lv_absolute_21_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.Float");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalKGraph.g:7216:4: ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7216:4: ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) )
            	    // InternalKGraph.g:7217:5: {...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 6)");
            	    }
            	    // InternalKGraph.g:7217:120: ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) )
            	    // InternalKGraph.g:7218:6: ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 6);
            	    					
            	    // InternalKGraph.g:7221:9: ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) )
            	    // InternalKGraph.g:7221:10: {...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7221:19: (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) )
            	    // InternalKGraph.g:7221:20: otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) )
            	    {
            	    otherlv_22=(Token)match(input,116,FOLLOW_22); 

            	    									newLeafNode(otherlv_22, grammarAccess.getKDecoratorPlacementDataAccess().getRotateWithLineKeyword_3_6_0());
            	    								
            	    otherlv_23=(Token)match(input,30,FOLLOW_55); 

            	    									newLeafNode(otherlv_23, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_6_1());
            	    								
            	    // InternalKGraph.g:7229:9: ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) )
            	    // InternalKGraph.g:7230:10: (lv_rotateWithLine_24_0= RULE_BOOLEAN )
            	    {
            	    // InternalKGraph.g:7230:10: (lv_rotateWithLine_24_0= RULE_BOOLEAN )
            	    // InternalKGraph.g:7231:11: lv_rotateWithLine_24_0= RULE_BOOLEAN
            	    {
            	    lv_rotateWithLine_24_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_127); 

            	    											newLeafNode(lv_rotateWithLine_24_0, grammarAccess.getKDecoratorPlacementDataAccess().getRotateWithLineBOOLEANTerminalRuleCall_3_6_2_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"rotateWithLine",
            	    												lv_rotateWithLine_24_0,
            	    												"de.cau.cs.kieler.kgraph.text.KGraph.BOOLEAN");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop136;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKDecoratorPlacementData"


    // $ANTLR start "entryRuleKAction"
    // InternalKGraph.g:7264:1: entryRuleKAction returns [EObject current=null] : iv_ruleKAction= ruleKAction EOF ;
    public final EObject entryRuleKAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKAction = null;


        try {
            // InternalKGraph.g:7264:48: (iv_ruleKAction= ruleKAction EOF )
            // InternalKGraph.g:7265:2: iv_ruleKAction= ruleKAction EOF
            {
             newCompositeNode(grammarAccess.getKActionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKAction=ruleKAction();

            state._fsp--;

             current =iv_ruleKAction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKAction"


    // $ANTLR start "ruleKAction"
    // InternalKGraph.g:7271:1: ruleKAction returns [EObject current=null] : ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleKAction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Enumerator lv_trigger_0_0 = null;

        AntlrDatatypeRuleToken lv_actionId_2_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:7277:2: ( ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) ) )
            // InternalKGraph.g:7278:2: ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) )
            {
            // InternalKGraph.g:7278:2: ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) )
            // InternalKGraph.g:7279:3: ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) )
            {
            // InternalKGraph.g:7279:3: ( (lv_trigger_0_0= ruleTrigger ) )
            // InternalKGraph.g:7280:4: (lv_trigger_0_0= ruleTrigger )
            {
            // InternalKGraph.g:7280:4: (lv_trigger_0_0= ruleTrigger )
            // InternalKGraph.g:7281:5: lv_trigger_0_0= ruleTrigger
            {

            					newCompositeNode(grammarAccess.getKActionAccess().getTriggerTriggerEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_128);
            lv_trigger_0_0=ruleTrigger();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKActionRule());
            					}
            					set(
            						current,
            						"trigger",
            						lv_trigger_0_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.Trigger");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,117,FOLLOW_10); 

            			newLeafNode(otherlv_1, grammarAccess.getKActionAccess().getEqualsSignGreaterThanSignKeyword_1());
            		
            // InternalKGraph.g:7302:3: ( (lv_actionId_2_0= ruleQualifiedID ) )
            // InternalKGraph.g:7303:4: (lv_actionId_2_0= ruleQualifiedID )
            {
            // InternalKGraph.g:7303:4: (lv_actionId_2_0= ruleQualifiedID )
            // InternalKGraph.g:7304:5: lv_actionId_2_0= ruleQualifiedID
            {

            					newCompositeNode(grammarAccess.getKActionAccess().getActionIdQualifiedIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_actionId_2_0=ruleQualifiedID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKActionRule());
            					}
            					set(
            						current,
            						"actionId",
            						lv_actionId_2_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.QualifiedID");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKAction"


    // $ANTLR start "entryRuleKPosition"
    // InternalKGraph.g:7325:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // InternalKGraph.g:7325:50: (iv_ruleKPosition= ruleKPosition EOF )
            // InternalKGraph.g:7326:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKPosition"


    // $ANTLR start "ruleKPosition"
    // InternalKGraph.g:7332:1: ruleKPosition returns [EObject current=null] : ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_x_0_0 = null;

        EObject lv_y_2_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:7338:2: ( ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) ) )
            // InternalKGraph.g:7339:2: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) )
            {
            // InternalKGraph.g:7339:2: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) )
            // InternalKGraph.g:7340:3: ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) )
            {
            // InternalKGraph.g:7340:3: ( (lv_x_0_0= ruleKXPosition ) )
            // InternalKGraph.g:7341:4: (lv_x_0_0= ruleKXPosition )
            {
            // InternalKGraph.g:7341:4: (lv_x_0_0= ruleKXPosition )
            // InternalKGraph.g:7342:5: lv_x_0_0= ruleKXPosition
            {

            					newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_41);
            lv_x_0_0=ruleKXPosition();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKPositionRule());
            					}
            					set(
            						current,
            						"x",
            						lv_x_0_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.KXPosition");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,47,FOLLOW_129); 

            			newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getCommaKeyword_1());
            		
            // InternalKGraph.g:7363:3: ( (lv_y_2_0= ruleKYPosition ) )
            // InternalKGraph.g:7364:4: (lv_y_2_0= ruleKYPosition )
            {
            // InternalKGraph.g:7364:4: (lv_y_2_0= ruleKYPosition )
            // InternalKGraph.g:7365:5: lv_y_2_0= ruleKYPosition
            {

            					newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_y_2_0=ruleKYPosition();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getKPositionRule());
            					}
            					set(
            						current,
            						"y",
            						lv_y_2_0,
            						"de.cau.cs.kieler.kgraph.text.KGraph.KYPosition");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKPosition"


    // $ANTLR start "entryRuleKXPosition"
    // InternalKGraph.g:7386:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // InternalKGraph.g:7386:51: (iv_ruleKXPosition= ruleKXPosition EOF )
            // InternalKGraph.g:7387:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKXPosition"


    // $ANTLR start "ruleKXPosition"
    // InternalKGraph.g:7393:1: ruleKXPosition returns [EObject current=null] : ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        Token lv_relative_2_0=null;
        Token lv_relative_3_0=null;
        Token otherlv_4=null;
        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;

        AntlrDatatypeRuleToken lv_absolute_5_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:7399:2: ( ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) )
            // InternalKGraph.g:7400:2: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            {
            // InternalKGraph.g:7400:2: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            // InternalKGraph.g:7401:3: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
            {
            // InternalKGraph.g:7401:3: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==43) ) {
                alt137=1;
            }
            else if ( (LA137_0==44) ) {
                alt137=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 137, 0, input);

                throw nvae;
            }
            switch (alt137) {
                case 1 :
                    // InternalKGraph.g:7402:4: this_KLeftPosition_0= ruleKLeftPosition
                    {

                    				newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_130);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;


                    				current = this_KLeftPosition_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7411:4: this_KRightPosition_1= ruleKRightPosition
                    {

                    				newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_130);
                    this_KRightPosition_1=ruleKRightPosition();

                    state._fsp--;


                    				current = this_KRightPosition_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalKGraph.g:7420:3: ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
            int alt139=3;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==RULE_PERCENT) ) {
                int LA139_1 = input.LA(2);

                if ( (LA139_1==EOF||LA139_1==47) ) {
                    alt139=1;
                }
                else if ( (LA139_1==118) ) {
                    alt139=2;
                }
            }
            else if ( (LA139_0==RULE_NATURAL||LA139_0==RULE_TFLOAT) ) {
                alt139=2;
            }
            switch (alt139) {
                case 1 :
                    // InternalKGraph.g:7421:4: ( (lv_relative_2_0= RULE_PERCENT ) )
                    {
                    // InternalKGraph.g:7421:4: ( (lv_relative_2_0= RULE_PERCENT ) )
                    // InternalKGraph.g:7422:5: (lv_relative_2_0= RULE_PERCENT )
                    {
                    // InternalKGraph.g:7422:5: (lv_relative_2_0= RULE_PERCENT )
                    // InternalKGraph.g:7423:6: lv_relative_2_0= RULE_PERCENT
                    {
                    lv_relative_2_0=(Token)match(input,RULE_PERCENT,FOLLOW_2); 

                    						newLeafNode(lv_relative_2_0, grammarAccess.getKXPositionAccess().getRelativePERCENTTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKXPositionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"relative",
                    							lv_relative_2_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.PERCENT");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7440:4: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    {
                    // InternalKGraph.g:7440:4: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    // InternalKGraph.g:7441:5: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) )
                    {
                    // InternalKGraph.g:7441:5: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )?
                    int alt138=2;
                    int LA138_0 = input.LA(1);

                    if ( (LA138_0==RULE_PERCENT) ) {
                        alt138=1;
                    }
                    switch (alt138) {
                        case 1 :
                            // InternalKGraph.g:7442:6: ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+'
                            {
                            // InternalKGraph.g:7442:6: ( (lv_relative_3_0= RULE_PERCENT ) )
                            // InternalKGraph.g:7443:7: (lv_relative_3_0= RULE_PERCENT )
                            {
                            // InternalKGraph.g:7443:7: (lv_relative_3_0= RULE_PERCENT )
                            // InternalKGraph.g:7444:8: lv_relative_3_0= RULE_PERCENT
                            {
                            lv_relative_3_0=(Token)match(input,RULE_PERCENT,FOLLOW_131); 

                            								newLeafNode(lv_relative_3_0, grammarAccess.getKXPositionAccess().getRelativePERCENTTerminalRuleCall_1_1_0_0_0());
                            							

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getKXPositionRule());
                            								}
                            								setWithLastConsumed(
                            									current,
                            									"relative",
                            									lv_relative_3_0,
                            									"de.cau.cs.kieler.kgraph.text.KGraph.PERCENT");
                            							

                            }


                            }

                            otherlv_4=(Token)match(input,118,FOLLOW_28); 

                            						newLeafNode(otherlv_4, grammarAccess.getKXPositionAccess().getPlusSignKeyword_1_1_0_1());
                            					

                            }
                            break;

                    }

                    // InternalKGraph.g:7465:5: ( (lv_absolute_5_0= ruleFloat ) )
                    // InternalKGraph.g:7466:6: (lv_absolute_5_0= ruleFloat )
                    {
                    // InternalKGraph.g:7466:6: (lv_absolute_5_0= ruleFloat )
                    // InternalKGraph.g:7467:7: lv_absolute_5_0= ruleFloat
                    {

                    							newCompositeNode(grammarAccess.getKXPositionAccess().getAbsoluteFloatParserRuleCall_1_1_1_0());
                    						
                    pushFollow(FOLLOW_2);
                    lv_absolute_5_0=ruleFloat();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKXPositionRule());
                    							}
                    							set(
                    								current,
                    								"absolute",
                    								lv_absolute_5_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKXPosition"


    // $ANTLR start "entryRuleKLeftPosition"
    // InternalKGraph.g:7490:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // InternalKGraph.g:7490:54: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // InternalKGraph.g:7491:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLeftPosition"


    // $ANTLR start "ruleKLeftPosition"
    // InternalKGraph.g:7497:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'left' ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:7503:2: ( ( () otherlv_1= 'left' ) )
            // InternalKGraph.g:7504:2: ( () otherlv_1= 'left' )
            {
            // InternalKGraph.g:7504:2: ( () otherlv_1= 'left' )
            // InternalKGraph.g:7505:3: () otherlv_1= 'left'
            {
            // InternalKGraph.g:7505:3: ()
            // InternalKGraph.g:7506:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,43,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getLeftKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKLeftPosition"


    // $ANTLR start "entryRuleKRightPosition"
    // InternalKGraph.g:7520:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // InternalKGraph.g:7520:55: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // InternalKGraph.g:7521:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKRightPosition"


    // $ANTLR start "ruleKRightPosition"
    // InternalKGraph.g:7527:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'right' ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:7533:2: ( ( () otherlv_1= 'right' ) )
            // InternalKGraph.g:7534:2: ( () otherlv_1= 'right' )
            {
            // InternalKGraph.g:7534:2: ( () otherlv_1= 'right' )
            // InternalKGraph.g:7535:3: () otherlv_1= 'right'
            {
            // InternalKGraph.g:7535:3: ()
            // InternalKGraph.g:7536:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,44,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getRightKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKRightPosition"


    // $ANTLR start "entryRuleKYPosition"
    // InternalKGraph.g:7550:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // InternalKGraph.g:7550:51: (iv_ruleKYPosition= ruleKYPosition EOF )
            // InternalKGraph.g:7551:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKYPosition"


    // $ANTLR start "ruleKYPosition"
    // InternalKGraph.g:7557:1: ruleKYPosition returns [EObject current=null] : ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        Token lv_relative_2_0=null;
        Token lv_relative_3_0=null;
        Token otherlv_4=null;
        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;

        AntlrDatatypeRuleToken lv_absolute_5_0 = null;



        	enterRule();

        try {
            // InternalKGraph.g:7563:2: ( ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) )
            // InternalKGraph.g:7564:2: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            {
            // InternalKGraph.g:7564:2: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            // InternalKGraph.g:7565:3: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
            {
            // InternalKGraph.g:7565:3: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==41) ) {
                alt140=1;
            }
            else if ( (LA140_0==42) ) {
                alt140=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 140, 0, input);

                throw nvae;
            }
            switch (alt140) {
                case 1 :
                    // InternalKGraph.g:7566:4: this_KTopPosition_0= ruleKTopPosition
                    {

                    				newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_130);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;


                    				current = this_KTopPosition_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7575:4: this_KBottomPosition_1= ruleKBottomPosition
                    {

                    				newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_130);
                    this_KBottomPosition_1=ruleKBottomPosition();

                    state._fsp--;


                    				current = this_KBottomPosition_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalKGraph.g:7584:3: ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
            int alt142=3;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==RULE_PERCENT) ) {
                int LA142_1 = input.LA(2);

                if ( (LA142_1==118) ) {
                    alt142=2;
                }
                else if ( (LA142_1==EOF||LA142_1==22||LA142_1==27||(LA142_1>=45 && LA142_1<=46)||(LA142_1>=48 && LA142_1<=50)||(LA142_1>=52 && LA142_1<=59)||(LA142_1>=61 && LA142_1<=65)||(LA142_1>=93 && LA142_1<=111)) ) {
                    alt142=1;
                }
            }
            else if ( (LA142_0==RULE_NATURAL||LA142_0==RULE_TFLOAT) ) {
                alt142=2;
            }
            switch (alt142) {
                case 1 :
                    // InternalKGraph.g:7585:4: ( (lv_relative_2_0= RULE_PERCENT ) )
                    {
                    // InternalKGraph.g:7585:4: ( (lv_relative_2_0= RULE_PERCENT ) )
                    // InternalKGraph.g:7586:5: (lv_relative_2_0= RULE_PERCENT )
                    {
                    // InternalKGraph.g:7586:5: (lv_relative_2_0= RULE_PERCENT )
                    // InternalKGraph.g:7587:6: lv_relative_2_0= RULE_PERCENT
                    {
                    lv_relative_2_0=(Token)match(input,RULE_PERCENT,FOLLOW_2); 

                    						newLeafNode(lv_relative_2_0, grammarAccess.getKYPositionAccess().getRelativePERCENTTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKYPositionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"relative",
                    							lv_relative_2_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.PERCENT");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7604:4: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    {
                    // InternalKGraph.g:7604:4: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    // InternalKGraph.g:7605:5: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) )
                    {
                    // InternalKGraph.g:7605:5: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )?
                    int alt141=2;
                    int LA141_0 = input.LA(1);

                    if ( (LA141_0==RULE_PERCENT) ) {
                        alt141=1;
                    }
                    switch (alt141) {
                        case 1 :
                            // InternalKGraph.g:7606:6: ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+'
                            {
                            // InternalKGraph.g:7606:6: ( (lv_relative_3_0= RULE_PERCENT ) )
                            // InternalKGraph.g:7607:7: (lv_relative_3_0= RULE_PERCENT )
                            {
                            // InternalKGraph.g:7607:7: (lv_relative_3_0= RULE_PERCENT )
                            // InternalKGraph.g:7608:8: lv_relative_3_0= RULE_PERCENT
                            {
                            lv_relative_3_0=(Token)match(input,RULE_PERCENT,FOLLOW_131); 

                            								newLeafNode(lv_relative_3_0, grammarAccess.getKYPositionAccess().getRelativePERCENTTerminalRuleCall_1_1_0_0_0());
                            							

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getKYPositionRule());
                            								}
                            								setWithLastConsumed(
                            									current,
                            									"relative",
                            									lv_relative_3_0,
                            									"de.cau.cs.kieler.kgraph.text.KGraph.PERCENT");
                            							

                            }


                            }

                            otherlv_4=(Token)match(input,118,FOLLOW_28); 

                            						newLeafNode(otherlv_4, grammarAccess.getKYPositionAccess().getPlusSignKeyword_1_1_0_1());
                            					

                            }
                            break;

                    }

                    // InternalKGraph.g:7629:5: ( (lv_absolute_5_0= ruleFloat ) )
                    // InternalKGraph.g:7630:6: (lv_absolute_5_0= ruleFloat )
                    {
                    // InternalKGraph.g:7630:6: (lv_absolute_5_0= ruleFloat )
                    // InternalKGraph.g:7631:7: lv_absolute_5_0= ruleFloat
                    {

                    							newCompositeNode(grammarAccess.getKYPositionAccess().getAbsoluteFloatParserRuleCall_1_1_1_0());
                    						
                    pushFollow(FOLLOW_2);
                    lv_absolute_5_0=ruleFloat();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getKYPositionRule());
                    							}
                    							set(
                    								current,
                    								"absolute",
                    								lv_absolute_5_0,
                    								"de.cau.cs.kieler.kgraph.text.KGraph.Float");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKYPosition"


    // $ANTLR start "entryRuleKTopPosition"
    // InternalKGraph.g:7654:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // InternalKGraph.g:7654:53: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // InternalKGraph.g:7655:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKTopPosition"


    // $ANTLR start "ruleKTopPosition"
    // InternalKGraph.g:7661:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'top' ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:7667:2: ( ( () otherlv_1= 'top' ) )
            // InternalKGraph.g:7668:2: ( () otherlv_1= 'top' )
            {
            // InternalKGraph.g:7668:2: ( () otherlv_1= 'top' )
            // InternalKGraph.g:7669:3: () otherlv_1= 'top'
            {
            // InternalKGraph.g:7669:3: ()
            // InternalKGraph.g:7670:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,41,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getTopKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKTopPosition"


    // $ANTLR start "entryRuleKBottomPosition"
    // InternalKGraph.g:7684:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // InternalKGraph.g:7684:56: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // InternalKGraph.g:7685:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKBottomPosition"


    // $ANTLR start "ruleKBottomPosition"
    // InternalKGraph.g:7691:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'bottom' ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:7697:2: ( ( () otherlv_1= 'bottom' ) )
            // InternalKGraph.g:7698:2: ( () otherlv_1= 'bottom' )
            {
            // InternalKGraph.g:7698:2: ( () otherlv_1= 'bottom' )
            // InternalKGraph.g:7699:3: () otherlv_1= 'bottom'
            {
            // InternalKGraph.g:7699:3: ()
            // InternalKGraph.g:7700:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,42,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getBottomKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKBottomPosition"


    // $ANTLR start "entryRuleKColor"
    // InternalKGraph.g:7714:1: entryRuleKColor returns [EObject current=null] : iv_ruleKColor= ruleKColor EOF ;
    public final EObject entryRuleKColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKColor = null;


        try {
            // InternalKGraph.g:7714:47: (iv_ruleKColor= ruleKColor EOF )
            // InternalKGraph.g:7715:2: iv_ruleKColor= ruleKColor EOF
            {
             newCompositeNode(grammarAccess.getKColorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKColor=ruleKColor();

            state._fsp--;

             current =iv_ruleKColor; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKColor"


    // $ANTLR start "ruleKColor"
    // InternalKGraph.g:7721:1: ruleKColor returns [EObject current=null] : ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) ) ;
    public final EObject ruleKColor() throws RecognitionException {
        EObject current = null;

        Token lv_red_0_0=null;
        Token otherlv_1=null;
        Token lv_green_2_0=null;
        Token otherlv_3=null;
        Token lv_blue_4_0=null;
        Token lv_green_5_0=null;
        Token otherlv_6=null;
        Token lv_blue_7_0=null;
        Token lv_blue_8_0=null;


        	enterRule();

        try {
            // InternalKGraph.g:7727:2: ( ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) ) )
            // InternalKGraph.g:7728:2: ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) )
            {
            // InternalKGraph.g:7728:2: ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) )
            int alt146=3;
            switch ( input.LA(1) ) {
            case RULE_RED:
                {
                alt146=1;
                }
                break;
            case RULE_GREEN:
                {
                alt146=2;
                }
                break;
            case RULE_BLUE:
                {
                alt146=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 146, 0, input);

                throw nvae;
            }

            switch (alt146) {
                case 1 :
                    // InternalKGraph.g:7729:3: ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? )
                    {
                    // InternalKGraph.g:7729:3: ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? )
                    // InternalKGraph.g:7730:4: ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )?
                    {
                    // InternalKGraph.g:7730:4: ( (lv_red_0_0= RULE_RED ) )
                    // InternalKGraph.g:7731:5: (lv_red_0_0= RULE_RED )
                    {
                    // InternalKGraph.g:7731:5: (lv_red_0_0= RULE_RED )
                    // InternalKGraph.g:7732:6: lv_red_0_0= RULE_RED
                    {
                    lv_red_0_0=(Token)match(input,RULE_RED,FOLLOW_132); 

                    						newLeafNode(lv_red_0_0, grammarAccess.getKColorAccess().getRedREDTerminalRuleCall_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKColorRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"red",
                    							lv_red_0_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.RED");
                    					

                    }


                    }

                    // InternalKGraph.g:7748:4: (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )?
                    int alt143=2;
                    int LA143_0 = input.LA(1);

                    if ( (LA143_0==47) ) {
                        int LA143_1 = input.LA(2);

                        if ( (LA143_1==RULE_GREEN) ) {
                            alt143=1;
                        }
                    }
                    switch (alt143) {
                        case 1 :
                            // InternalKGraph.g:7749:5: otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) )
                            {
                            otherlv_1=(Token)match(input,47,FOLLOW_133); 

                            					newLeafNode(otherlv_1, grammarAccess.getKColorAccess().getCommaKeyword_0_1_0());
                            				
                            // InternalKGraph.g:7753:5: ( (lv_green_2_0= RULE_GREEN ) )
                            // InternalKGraph.g:7754:6: (lv_green_2_0= RULE_GREEN )
                            {
                            // InternalKGraph.g:7754:6: (lv_green_2_0= RULE_GREEN )
                            // InternalKGraph.g:7755:7: lv_green_2_0= RULE_GREEN
                            {
                            lv_green_2_0=(Token)match(input,RULE_GREEN,FOLLOW_132); 

                            							newLeafNode(lv_green_2_0, grammarAccess.getKColorAccess().getGreenGREENTerminalRuleCall_0_1_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getKColorRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"green",
                            								lv_green_2_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.GREEN");
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalKGraph.g:7772:4: (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )?
                    int alt144=2;
                    int LA144_0 = input.LA(1);

                    if ( (LA144_0==47) ) {
                        int LA144_1 = input.LA(2);

                        if ( (LA144_1==RULE_BLUE) ) {
                            alt144=1;
                        }
                    }
                    switch (alt144) {
                        case 1 :
                            // InternalKGraph.g:7773:5: otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) )
                            {
                            otherlv_3=(Token)match(input,47,FOLLOW_134); 

                            					newLeafNode(otherlv_3, grammarAccess.getKColorAccess().getCommaKeyword_0_2_0());
                            				
                            // InternalKGraph.g:7777:5: ( (lv_blue_4_0= RULE_BLUE ) )
                            // InternalKGraph.g:7778:6: (lv_blue_4_0= RULE_BLUE )
                            {
                            // InternalKGraph.g:7778:6: (lv_blue_4_0= RULE_BLUE )
                            // InternalKGraph.g:7779:7: lv_blue_4_0= RULE_BLUE
                            {
                            lv_blue_4_0=(Token)match(input,RULE_BLUE,FOLLOW_2); 

                            							newLeafNode(lv_blue_4_0, grammarAccess.getKColorAccess().getBlueBLUETerminalRuleCall_0_2_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getKColorRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"blue",
                            								lv_blue_4_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.BLUE");
                            						

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7798:3: ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? )
                    {
                    // InternalKGraph.g:7798:3: ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? )
                    // InternalKGraph.g:7799:4: ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )?
                    {
                    // InternalKGraph.g:7799:4: ( (lv_green_5_0= RULE_GREEN ) )
                    // InternalKGraph.g:7800:5: (lv_green_5_0= RULE_GREEN )
                    {
                    // InternalKGraph.g:7800:5: (lv_green_5_0= RULE_GREEN )
                    // InternalKGraph.g:7801:6: lv_green_5_0= RULE_GREEN
                    {
                    lv_green_5_0=(Token)match(input,RULE_GREEN,FOLLOW_132); 

                    						newLeafNode(lv_green_5_0, grammarAccess.getKColorAccess().getGreenGREENTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getKColorRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"green",
                    							lv_green_5_0,
                    							"de.cau.cs.kieler.kgraph.text.KGraph.GREEN");
                    					

                    }


                    }

                    // InternalKGraph.g:7817:4: (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )?
                    int alt145=2;
                    int LA145_0 = input.LA(1);

                    if ( (LA145_0==47) ) {
                        int LA145_1 = input.LA(2);

                        if ( (LA145_1==RULE_BLUE) ) {
                            alt145=1;
                        }
                    }
                    switch (alt145) {
                        case 1 :
                            // InternalKGraph.g:7818:5: otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) )
                            {
                            otherlv_6=(Token)match(input,47,FOLLOW_134); 

                            					newLeafNode(otherlv_6, grammarAccess.getKColorAccess().getCommaKeyword_1_1_0());
                            				
                            // InternalKGraph.g:7822:5: ( (lv_blue_7_0= RULE_BLUE ) )
                            // InternalKGraph.g:7823:6: (lv_blue_7_0= RULE_BLUE )
                            {
                            // InternalKGraph.g:7823:6: (lv_blue_7_0= RULE_BLUE )
                            // InternalKGraph.g:7824:7: lv_blue_7_0= RULE_BLUE
                            {
                            lv_blue_7_0=(Token)match(input,RULE_BLUE,FOLLOW_2); 

                            							newLeafNode(lv_blue_7_0, grammarAccess.getKColorAccess().getBlueBLUETerminalRuleCall_1_1_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getKColorRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"blue",
                            								lv_blue_7_0,
                            								"de.cau.cs.kieler.kgraph.text.KGraph.BLUE");
                            						

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:7843:3: ( (lv_blue_8_0= RULE_BLUE ) )
                    {
                    // InternalKGraph.g:7843:3: ( (lv_blue_8_0= RULE_BLUE ) )
                    // InternalKGraph.g:7844:4: (lv_blue_8_0= RULE_BLUE )
                    {
                    // InternalKGraph.g:7844:4: (lv_blue_8_0= RULE_BLUE )
                    // InternalKGraph.g:7845:5: lv_blue_8_0= RULE_BLUE
                    {
                    lv_blue_8_0=(Token)match(input,RULE_BLUE,FOLLOW_2); 

                    					newLeafNode(lv_blue_8_0, grammarAccess.getKColorAccess().getBlueBLUETerminalRuleCall_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getKColorRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"blue",
                    						lv_blue_8_0,
                    						"de.cau.cs.kieler.kgraph.text.KGraph.BLUE");
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKColor"


    // $ANTLR start "entryRuleQualifiedID"
    // InternalKGraph.g:7865:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalKGraph.g:7865:51: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalKGraph.g:7866:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // InternalKGraph.g:7872:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalKGraph.g:7878:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalKGraph.g:7879:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalKGraph.g:7879:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalKGraph.g:7880:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_135); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0());
            		
            // InternalKGraph.g:7887:3: (kw= '.' this_ID_2= RULE_ID )*
            loop147:
            do {
                int alt147=2;
                int LA147_0 = input.LA(1);

                if ( (LA147_0==119) ) {
                    alt147=1;
                }


                switch (alt147) {
            	case 1 :
            	    // InternalKGraph.g:7888:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,119,FOLLOW_10); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_135); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop147;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedID"


    // $ANTLR start "entryRulePropertyValue"
    // InternalKGraph.g:7905:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // InternalKGraph.g:7905:53: (iv_rulePropertyValue= rulePropertyValue EOF )
            // InternalKGraph.g:7906:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePropertyValue"


    // $ANTLR start "rulePropertyValue"
    // InternalKGraph.g:7912:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;



        	enterRule();

        try {
            // InternalKGraph.g:7918:2: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // InternalKGraph.g:7919:2: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // InternalKGraph.g:7919:2: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            int alt148=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt148=1;
                }
                break;
            case RULE_STRING:
                {
                alt148=2;
                }
                break;
            case RULE_NATURAL:
            case RULE_TFLOAT:
                {
                alt148=3;
                }
                break;
            case RULE_ID:
                {
                alt148=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 148, 0, input);

                throw nvae;
            }

            switch (alt148) {
                case 1 :
                    // InternalKGraph.g:7920:3: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_2); 

                    			current.merge(this_BOOLEAN_0);
                    		

                    			newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7928:3: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_1);
                    		

                    			newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:7936:3: this_Float_2= ruleFloat
                    {

                    			newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    			current.merge(this_Float_2);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:7947:3: this_QualifiedID_3= ruleQualifiedID
                    {

                    			newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_QualifiedID_3=ruleQualifiedID();

                    state._fsp--;


                    			current.merge(this_QualifiedID_3);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePropertyValue"


    // $ANTLR start "entryRuleFloat"
    // InternalKGraph.g:7961:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // InternalKGraph.g:7961:45: (iv_ruleFloat= ruleFloat EOF )
            // InternalKGraph.g:7962:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFloat"


    // $ANTLR start "ruleFloat"
    // InternalKGraph.g:7968:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;


        	enterRule();

        try {
            // InternalKGraph.g:7974:2: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // InternalKGraph.g:7975:2: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // InternalKGraph.g:7975:2: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==RULE_TFLOAT) ) {
                alt149=1;
            }
            else if ( (LA149_0==RULE_NATURAL) ) {
                alt149=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 149, 0, input);

                throw nvae;
            }
            switch (alt149) {
                case 1 :
                    // InternalKGraph.g:7976:3: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FOLLOW_2); 

                    			current.merge(this_TFLOAT_0);
                    		

                    			newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7984:3: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FOLLOW_2); 

                    			current.merge(this_NATURAL_1);
                    		

                    			newLeafNode(this_NATURAL_1, grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloat"


    // $ANTLR start "ruleArc"
    // InternalKGraph.g:7995:1: ruleArc returns [Enumerator current=null] : ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) ) ;
    public final Enumerator ruleArc() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalKGraph.g:8001:2: ( ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) ) )
            // InternalKGraph.g:8002:2: ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) )
            {
            // InternalKGraph.g:8002:2: ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) )
            int alt150=3;
            switch ( input.LA(1) ) {
            case 120:
                {
                alt150=1;
                }
                break;
            case 121:
                {
                alt150=2;
                }
                break;
            case 122:
                {
                alt150=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 150, 0, input);

                throw nvae;
            }

            switch (alt150) {
                case 1 :
                    // InternalKGraph.g:8003:3: (enumLiteral_0= 'open' )
                    {
                    // InternalKGraph.g:8003:3: (enumLiteral_0= 'open' )
                    // InternalKGraph.g:8004:4: enumLiteral_0= 'open'
                    {
                    enumLiteral_0=(Token)match(input,120,FOLLOW_2); 

                    				current = grammarAccess.getArcAccess().getOPENEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getArcAccess().getOPENEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8011:3: (enumLiteral_1= 'chord' )
                    {
                    // InternalKGraph.g:8011:3: (enumLiteral_1= 'chord' )
                    // InternalKGraph.g:8012:4: enumLiteral_1= 'chord'
                    {
                    enumLiteral_1=(Token)match(input,121,FOLLOW_2); 

                    				current = grammarAccess.getArcAccess().getCHORDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getArcAccess().getCHORDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8019:3: (enumLiteral_2= 'pie' )
                    {
                    // InternalKGraph.g:8019:3: (enumLiteral_2= 'pie' )
                    // InternalKGraph.g:8020:4: enumLiteral_2= 'pie'
                    {
                    enumLiteral_2=(Token)match(input,122,FOLLOW_2); 

                    				current = grammarAccess.getArcAccess().getPIEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getArcAccess().getPIEEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArc"


    // $ANTLR start "ruleHorizontalAlignment"
    // InternalKGraph.g:8030:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalKGraph.g:8036:2: ( ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) ) )
            // InternalKGraph.g:8037:2: ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) )
            {
            // InternalKGraph.g:8037:2: ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) )
            int alt151=3;
            switch ( input.LA(1) ) {
            case 43:
                {
                alt151=1;
                }
                break;
            case 123:
                {
                alt151=2;
                }
                break;
            case 44:
                {
                alt151=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 151, 0, input);

                throw nvae;
            }

            switch (alt151) {
                case 1 :
                    // InternalKGraph.g:8038:3: (enumLiteral_0= 'left' )
                    {
                    // InternalKGraph.g:8038:3: (enumLiteral_0= 'left' )
                    // InternalKGraph.g:8039:4: enumLiteral_0= 'left'
                    {
                    enumLiteral_0=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8046:3: (enumLiteral_1= 'center' )
                    {
                    // InternalKGraph.g:8046:3: (enumLiteral_1= 'center' )
                    // InternalKGraph.g:8047:4: enumLiteral_1= 'center'
                    {
                    enumLiteral_1=(Token)match(input,123,FOLLOW_2); 

                    				current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8054:3: (enumLiteral_2= 'right' )
                    {
                    // InternalKGraph.g:8054:3: (enumLiteral_2= 'right' )
                    // InternalKGraph.g:8055:4: enumLiteral_2= 'right'
                    {
                    enumLiteral_2=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getHorizontalAlignmentAccess().getRIGHTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getHorizontalAlignmentAccess().getRIGHTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHorizontalAlignment"


    // $ANTLR start "ruleVerticalAlignment"
    // InternalKGraph.g:8065:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalKGraph.g:8071:2: ( ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) )
            // InternalKGraph.g:8072:2: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
            {
            // InternalKGraph.g:8072:2: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
            int alt152=3;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt152=1;
                }
                break;
            case 123:
                {
                alt152=2;
                }
                break;
            case 42:
                {
                alt152=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 152, 0, input);

                throw nvae;
            }

            switch (alt152) {
                case 1 :
                    // InternalKGraph.g:8073:3: (enumLiteral_0= 'top' )
                    {
                    // InternalKGraph.g:8073:3: (enumLiteral_0= 'top' )
                    // InternalKGraph.g:8074:4: enumLiteral_0= 'top'
                    {
                    enumLiteral_0=(Token)match(input,41,FOLLOW_2); 

                    				current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8081:3: (enumLiteral_1= 'center' )
                    {
                    // InternalKGraph.g:8081:3: (enumLiteral_1= 'center' )
                    // InternalKGraph.g:8082:4: enumLiteral_1= 'center'
                    {
                    enumLiteral_1=(Token)match(input,123,FOLLOW_2); 

                    				current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8089:3: (enumLiteral_2= 'bottom' )
                    {
                    // InternalKGraph.g:8089:3: (enumLiteral_2= 'bottom' )
                    // InternalKGraph.g:8090:4: enumLiteral_2= 'bottom'
                    {
                    enumLiteral_2=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getVerticalAlignmentAccess().getBOTTOMEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getVerticalAlignmentAccess().getBOTTOMEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVerticalAlignment"


    // $ANTLR start "ruleUnderline"
    // InternalKGraph.g:8100:1: ruleUnderline returns [Enumerator current=null] : ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) ) ;
    public final Enumerator ruleUnderline() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalKGraph.g:8106:2: ( ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) ) )
            // InternalKGraph.g:8107:2: ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) )
            {
            // InternalKGraph.g:8107:2: ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) )
            int alt153=6;
            switch ( input.LA(1) ) {
            case 124:
                {
                alt153=1;
                }
                break;
            case 125:
                {
                alt153=2;
                }
                break;
            case 126:
                {
                alt153=3;
                }
                break;
            case 127:
                {
                alt153=4;
                }
                break;
            case 128:
                {
                alt153=5;
                }
                break;
            case 129:
                {
                alt153=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 153, 0, input);

                throw nvae;
            }

            switch (alt153) {
                case 1 :
                    // InternalKGraph.g:8108:3: (enumLiteral_0= 'none' )
                    {
                    // InternalKGraph.g:8108:3: (enumLiteral_0= 'none' )
                    // InternalKGraph.g:8109:4: enumLiteral_0= 'none'
                    {
                    enumLiteral_0=(Token)match(input,124,FOLLOW_2); 

                    				current = grammarAccess.getUnderlineAccess().getNONEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getUnderlineAccess().getNONEEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8116:3: (enumLiteral_1= 'single' )
                    {
                    // InternalKGraph.g:8116:3: (enumLiteral_1= 'single' )
                    // InternalKGraph.g:8117:4: enumLiteral_1= 'single'
                    {
                    enumLiteral_1=(Token)match(input,125,FOLLOW_2); 

                    				current = grammarAccess.getUnderlineAccess().getSINGLEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getUnderlineAccess().getSINGLEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8124:3: (enumLiteral_2= 'double' )
                    {
                    // InternalKGraph.g:8124:3: (enumLiteral_2= 'double' )
                    // InternalKGraph.g:8125:4: enumLiteral_2= 'double'
                    {
                    enumLiteral_2=(Token)match(input,126,FOLLOW_2); 

                    				current = grammarAccess.getUnderlineAccess().getDOUBLEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getUnderlineAccess().getDOUBLEEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalKGraph.g:8132:3: (enumLiteral_3= 'error' )
                    {
                    // InternalKGraph.g:8132:3: (enumLiteral_3= 'error' )
                    // InternalKGraph.g:8133:4: enumLiteral_3= 'error'
                    {
                    enumLiteral_3=(Token)match(input,127,FOLLOW_2); 

                    				current = grammarAccess.getUnderlineAccess().getERROREnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getUnderlineAccess().getERROREnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalKGraph.g:8140:3: (enumLiteral_4= 'squiggle' )
                    {
                    // InternalKGraph.g:8140:3: (enumLiteral_4= 'squiggle' )
                    // InternalKGraph.g:8141:4: enumLiteral_4= 'squiggle'
                    {
                    enumLiteral_4=(Token)match(input,128,FOLLOW_2); 

                    				current = grammarAccess.getUnderlineAccess().getSQUIGGLEEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getUnderlineAccess().getSQUIGGLEEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalKGraph.g:8148:3: (enumLiteral_5= 'link' )
                    {
                    // InternalKGraph.g:8148:3: (enumLiteral_5= 'link' )
                    // InternalKGraph.g:8149:4: enumLiteral_5= 'link'
                    {
                    enumLiteral_5=(Token)match(input,129,FOLLOW_2); 

                    				current = grammarAccess.getUnderlineAccess().getLINKEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getUnderlineAccess().getLINKEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnderline"


    // $ANTLR start "ruleLineStyle"
    // InternalKGraph.g:8159:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalKGraph.g:8165:2: ( ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) ) )
            // InternalKGraph.g:8166:2: ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) )
            {
            // InternalKGraph.g:8166:2: ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) )
            int alt154=6;
            switch ( input.LA(1) ) {
            case 130:
                {
                alt154=1;
                }
                break;
            case 131:
                {
                alt154=2;
                }
                break;
            case 132:
                {
                alt154=3;
                }
                break;
            case 133:
                {
                alt154=4;
                }
                break;
            case 134:
                {
                alt154=5;
                }
                break;
            case 135:
                {
                alt154=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 154, 0, input);

                throw nvae;
            }

            switch (alt154) {
                case 1 :
                    // InternalKGraph.g:8167:3: (enumLiteral_0= 'solid' )
                    {
                    // InternalKGraph.g:8167:3: (enumLiteral_0= 'solid' )
                    // InternalKGraph.g:8168:4: enumLiteral_0= 'solid'
                    {
                    enumLiteral_0=(Token)match(input,130,FOLLOW_2); 

                    				current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8175:3: (enumLiteral_1= 'dash' )
                    {
                    // InternalKGraph.g:8175:3: (enumLiteral_1= 'dash' )
                    // InternalKGraph.g:8176:4: enumLiteral_1= 'dash'
                    {
                    enumLiteral_1=(Token)match(input,131,FOLLOW_2); 

                    				current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8183:3: (enumLiteral_2= 'dot' )
                    {
                    // InternalKGraph.g:8183:3: (enumLiteral_2= 'dot' )
                    // InternalKGraph.g:8184:4: enumLiteral_2= 'dot'
                    {
                    enumLiteral_2=(Token)match(input,132,FOLLOW_2); 

                    				current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalKGraph.g:8191:3: (enumLiteral_3= 'dash-dot' )
                    {
                    // InternalKGraph.g:8191:3: (enumLiteral_3= 'dash-dot' )
                    // InternalKGraph.g:8192:4: enumLiteral_3= 'dash-dot'
                    {
                    enumLiteral_3=(Token)match(input,133,FOLLOW_2); 

                    				current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalKGraph.g:8199:3: (enumLiteral_4= 'dash-dot-dot' )
                    {
                    // InternalKGraph.g:8199:3: (enumLiteral_4= 'dash-dot-dot' )
                    // InternalKGraph.g:8200:4: enumLiteral_4= 'dash-dot-dot'
                    {
                    enumLiteral_4=(Token)match(input,134,FOLLOW_2); 

                    				current = grammarAccess.getLineStyleAccess().getDASHDOTDOTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getLineStyleAccess().getDASHDOTDOTEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalKGraph.g:8207:3: (enumLiteral_5= 'custom' )
                    {
                    // InternalKGraph.g:8207:3: (enumLiteral_5= 'custom' )
                    // InternalKGraph.g:8208:4: enumLiteral_5= 'custom'
                    {
                    enumLiteral_5=(Token)match(input,135,FOLLOW_2); 

                    				current = grammarAccess.getLineStyleAccess().getCUSTOMEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getLineStyleAccess().getCUSTOMEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLineStyle"


    // $ANTLR start "ruleLineCap"
    // InternalKGraph.g:8218:1: ruleLineCap returns [Enumerator current=null] : ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) ) ;
    public final Enumerator ruleLineCap() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalKGraph.g:8224:2: ( ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) ) )
            // InternalKGraph.g:8225:2: ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) )
            {
            // InternalKGraph.g:8225:2: ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) )
            int alt155=3;
            switch ( input.LA(1) ) {
            case 136:
                {
                alt155=1;
                }
                break;
            case 137:
                {
                alt155=2;
                }
                break;
            case 138:
                {
                alt155=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 155, 0, input);

                throw nvae;
            }

            switch (alt155) {
                case 1 :
                    // InternalKGraph.g:8226:3: (enumLiteral_0= 'flat' )
                    {
                    // InternalKGraph.g:8226:3: (enumLiteral_0= 'flat' )
                    // InternalKGraph.g:8227:4: enumLiteral_0= 'flat'
                    {
                    enumLiteral_0=(Token)match(input,136,FOLLOW_2); 

                    				current = grammarAccess.getLineCapAccess().getCAP_FLATEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getLineCapAccess().getCAP_FLATEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8234:3: (enumLiteral_1= 'round' )
                    {
                    // InternalKGraph.g:8234:3: (enumLiteral_1= 'round' )
                    // InternalKGraph.g:8235:4: enumLiteral_1= 'round'
                    {
                    enumLiteral_1=(Token)match(input,137,FOLLOW_2); 

                    				current = grammarAccess.getLineCapAccess().getCAP_ROUNDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getLineCapAccess().getCAP_ROUNDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8242:3: (enumLiteral_2= 'square' )
                    {
                    // InternalKGraph.g:8242:3: (enumLiteral_2= 'square' )
                    // InternalKGraph.g:8243:4: enumLiteral_2= 'square'
                    {
                    enumLiteral_2=(Token)match(input,138,FOLLOW_2); 

                    				current = grammarAccess.getLineCapAccess().getCAP_SQUAREEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getLineCapAccess().getCAP_SQUAREEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLineCap"


    // $ANTLR start "ruleLineJoin"
    // InternalKGraph.g:8253:1: ruleLineJoin returns [Enumerator current=null] : ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) ) ;
    public final Enumerator ruleLineJoin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalKGraph.g:8259:2: ( ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) ) )
            // InternalKGraph.g:8260:2: ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) )
            {
            // InternalKGraph.g:8260:2: ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) )
            int alt156=3;
            switch ( input.LA(1) ) {
            case 139:
                {
                alt156=1;
                }
                break;
            case 137:
                {
                alt156=2;
                }
                break;
            case 140:
                {
                alt156=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 156, 0, input);

                throw nvae;
            }

            switch (alt156) {
                case 1 :
                    // InternalKGraph.g:8261:3: (enumLiteral_0= 'miter' )
                    {
                    // InternalKGraph.g:8261:3: (enumLiteral_0= 'miter' )
                    // InternalKGraph.g:8262:4: enumLiteral_0= 'miter'
                    {
                    enumLiteral_0=(Token)match(input,139,FOLLOW_2); 

                    				current = grammarAccess.getLineJoinAccess().getJOIN_MITEREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getLineJoinAccess().getJOIN_MITEREnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8269:3: (enumLiteral_1= 'round' )
                    {
                    // InternalKGraph.g:8269:3: (enumLiteral_1= 'round' )
                    // InternalKGraph.g:8270:4: enumLiteral_1= 'round'
                    {
                    enumLiteral_1=(Token)match(input,137,FOLLOW_2); 

                    				current = grammarAccess.getLineJoinAccess().getJOIN_ROUNDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getLineJoinAccess().getJOIN_ROUNDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8277:3: (enumLiteral_2= 'bevel' )
                    {
                    // InternalKGraph.g:8277:3: (enumLiteral_2= 'bevel' )
                    // InternalKGraph.g:8278:4: enumLiteral_2= 'bevel'
                    {
                    enumLiteral_2=(Token)match(input,140,FOLLOW_2); 

                    				current = grammarAccess.getLineJoinAccess().getJOIN_BEVELEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getLineJoinAccess().getJOIN_BEVELEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLineJoin"


    // $ANTLR start "ruleTrigger"
    // InternalKGraph.g:8288:1: ruleTrigger returns [Enumerator current=null] : ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) ) ;
    public final Enumerator ruleTrigger() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalKGraph.g:8294:2: ( ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) ) )
            // InternalKGraph.g:8295:2: ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) )
            {
            // InternalKGraph.g:8295:2: ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) )
            int alt157=6;
            switch ( input.LA(1) ) {
            case 141:
                {
                alt157=1;
                }
                break;
            case 142:
                {
                alt157=2;
                }
                break;
            case 143:
                {
                alt157=3;
                }
                break;
            case 144:
                {
                alt157=4;
                }
                break;
            case 145:
                {
                alt157=5;
                }
                break;
            case 146:
                {
                alt157=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 157, 0, input);

                throw nvae;
            }

            switch (alt157) {
                case 1 :
                    // InternalKGraph.g:8296:3: (enumLiteral_0= 'singleClick' )
                    {
                    // InternalKGraph.g:8296:3: (enumLiteral_0= 'singleClick' )
                    // InternalKGraph.g:8297:4: enumLiteral_0= 'singleClick'
                    {
                    enumLiteral_0=(Token)match(input,141,FOLLOW_2); 

                    				current = grammarAccess.getTriggerAccess().getSINGLECLICKEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getTriggerAccess().getSINGLECLICKEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8304:3: (enumLiteral_1= 'doubleClick' )
                    {
                    // InternalKGraph.g:8304:3: (enumLiteral_1= 'doubleClick' )
                    // InternalKGraph.g:8305:4: enumLiteral_1= 'doubleClick'
                    {
                    enumLiteral_1=(Token)match(input,142,FOLLOW_2); 

                    				current = grammarAccess.getTriggerAccess().getDOUBLECLICKEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getTriggerAccess().getDOUBLECLICKEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8312:3: (enumLiteral_2= 'singleOrMultiClick' )
                    {
                    // InternalKGraph.g:8312:3: (enumLiteral_2= 'singleOrMultiClick' )
                    // InternalKGraph.g:8313:4: enumLiteral_2= 'singleOrMultiClick'
                    {
                    enumLiteral_2=(Token)match(input,143,FOLLOW_2); 

                    				current = grammarAccess.getTriggerAccess().getSINGLE_OR_MULTICLICKEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getTriggerAccess().getSINGLE_OR_MULTICLICKEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalKGraph.g:8320:3: (enumLiteral_3= 'middleSingleClick' )
                    {
                    // InternalKGraph.g:8320:3: (enumLiteral_3= 'middleSingleClick' )
                    // InternalKGraph.g:8321:4: enumLiteral_3= 'middleSingleClick'
                    {
                    enumLiteral_3=(Token)match(input,144,FOLLOW_2); 

                    				current = grammarAccess.getTriggerAccess().getMIDDLE_SINGLECLICKEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getTriggerAccess().getMIDDLE_SINGLECLICKEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalKGraph.g:8328:3: (enumLiteral_4= 'middleDoubleClick' )
                    {
                    // InternalKGraph.g:8328:3: (enumLiteral_4= 'middleDoubleClick' )
                    // InternalKGraph.g:8329:4: enumLiteral_4= 'middleDoubleClick'
                    {
                    enumLiteral_4=(Token)match(input,145,FOLLOW_2); 

                    				current = grammarAccess.getTriggerAccess().getMIDDLE_DOUBLECLICKEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getTriggerAccess().getMIDDLE_DOUBLECLICKEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalKGraph.g:8336:3: (enumLiteral_5= 'middleSingleOrMultiClick' )
                    {
                    // InternalKGraph.g:8336:3: (enumLiteral_5= 'middleSingleOrMultiClick' )
                    // InternalKGraph.g:8337:4: enumLiteral_5= 'middleSingleOrMultiClick'
                    {
                    enumLiteral_5=(Token)match(input,146,FOLLOW_2); 

                    				current = grammarAccess.getTriggerAccess().getMIDDLE_SINGLE_OR_MULTICLICKEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getTriggerAccess().getMIDDLE_SINGLE_OR_MULTICLICKEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTrigger"

    // Delegated rules


    protected DFA59 dfa59 = new DFA59(this);
    protected DFA77 dfa77 = new DFA77(this);
    static final String dfa_1s = "\6\uffff";
    static final String dfa_2s = "\1\16\1\31\1\16\2\uffff\1\31";
    static final String dfa_3s = "\1\16\1\167\1\16\2\uffff\1\167";
    static final String dfa_4s = "\3\uffff\1\1\1\2\1\uffff";
    static final String dfa_5s = "\6\uffff}>";
    static final String[] dfa_6s = {
            "\1\1",
            "\1\3\1\uffff\1\4\133\uffff\1\2",
            "\1\5",
            "",
            "",
            "\1\3\1\uffff\1\4\133\uffff\1\2"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA59 extends DFA {

        public DFA59(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 59;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "3378:4: ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )?";
        }
    }
    static final String dfa_7s = "\24\uffff";
    static final String dfa_8s = "\1\104\1\105\1\106\20\uffff\1\106";
    static final String dfa_9s = "\1\132\1\131\1\132\20\uffff\1\131";
    static final String dfa_10s = "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\uffff";
    static final String dfa_11s = "\24\uffff}>";
    static final String[] dfa_12s = {
            "\1\1\1\2\2\3\1\4\1\5\1\6\2\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\2\uffff\1\17\1\20\1\uffff\1\21\1\22",
            "\1\23\2\3\1\4\1\5\1\6\2\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\2\uffff\1\17\1\20\1\uffff\1\21",
            "\2\3\1\4\1\5\1\6\2\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\2\uffff\1\17\1\20\1\uffff\1\21\1\22",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\3\1\4\1\5\1\6\2\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\2\uffff\1\17\1\20\1\uffff\1\21"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA77 extends DFA {

        public DFA77(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 77;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "4072:3: (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000019200004000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0xCFF4000030900002L,0x0000000008000003L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0xCFF4000030D00000L,0x0000000008000003L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000001004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000208000000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0xCFF4000010400000L,0x0000000000000003L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000204010L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0xCFF4009200400000L,0x0000000000000003L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0xCFF4000000400000L,0x0000000000000003L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0xCFF4009210400000L,0x0000000000000003L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x000000000000C230L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000100004000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000019E00000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000008200L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x000001F200000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x00001E0000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x00001E0000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000009E00000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x000000F200000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000009200004002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000008000008200L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000408000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000080204002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000080200002L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0003000000400000L,0x0000840600000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0003000000400000L,0x0000840606CFFFF0L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0003000000400000L,0x0000840600000000L,0x000000000007E000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0xCFF7000000400000L,0x0000840620000003L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0xCFF7000000400000L,0x0000840626CFFFF3L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0xCFF7000000400000L,0x0000840620000003L,0x000000000007E000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000800008000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000000L,0x0700000000000000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x1000000008000000L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0xCFF4000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0xEFF7200000400000L,0x0000840620000003L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0xEFF7380000400000L,0x0000840620000003L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0xEFF7600000400000L,0x0000840620000003L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0xEFF7200000400000L,0x0000840626CFFFF3L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0xEFF7200000400000L,0x0000840620000003L,0x000000000007E000L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000000003800L,0x0000000000000008L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000800004000002L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000800001000002L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000A0L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000120L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000220L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000420L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001820L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002020L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x0000000000000000L,0xF000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004020L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_92 = new BitSet(new long[]{0x0000180000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_93 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008020L});
    public static final BitSet FOLLOW_94 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_95 = new BitSet(new long[]{0x0000060000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_96 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010020L});
    public static final BitSet FOLLOW_97 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_98 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020020L});
    public static final BitSet FOLLOW_99 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_100 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000700L});
    public static final BitSet FOLLOW_101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040020L});
    public static final BitSet FOLLOW_102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001A00L});
    public static final BitSet FOLLOW_104 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080020L});
    public static final BitSet FOLLOW_105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000000FCL});
    public static final BitSet FOLLOW_107 = new BitSet(new long[]{0x0000000000000002L,0x0000000000300000L});
    public static final BitSet FOLLOW_108 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_109 = new BitSet(new long[]{0x0000000000008202L});
    public static final BitSet FOLLOW_110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400020L});
    public static final BitSet FOLLOW_111 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_112 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800020L});
    public static final BitSet FOLLOW_113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_114 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_115 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000020L});
    public static final BitSet FOLLOW_116 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_117 = new BitSet(new long[]{0x0000000001003802L,0x0000000000000008L});
    public static final BitSet FOLLOW_118 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_119 = new BitSet(new long[]{0x0000000080200000L});
    public static final BitSet FOLLOW_120 = new BitSet(new long[]{0xCFF4000000400000L,0x0000000010000003L});
    public static final BitSet FOLLOW_121 = new BitSet(new long[]{0x0000000000400000L,0x0000000006CFFFF0L});
    public static final BitSet FOLLOW_122 = new BitSet(new long[]{0x0000000000000002L,0x00000001C0000000L});
    public static final BitSet FOLLOW_123 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_124 = new BitSet(new long[]{0x0000000000000002L,0x00000000C0000000L});
    public static final BitSet FOLLOW_125 = new BitSet(new long[]{0x0000000000000002L,0x000003F800000000L});
    public static final BitSet FOLLOW_126 = new BitSet(new long[]{0x0000000000000002L,0x00007800C0000000L});
    public static final BitSet FOLLOW_127 = new BitSet(new long[]{0x0000006000000002L,0x001F000000000000L});
    public static final BitSet FOLLOW_128 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_129 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_130 = new BitSet(new long[]{0x0000000000008602L});
    public static final BitSet FOLLOW_131 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_132 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_133 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_134 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_135 = new BitSet(new long[]{0x0000000000000002L,0x0080000000000000L});

}
