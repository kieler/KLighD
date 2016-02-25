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
    // InternalKGraph.g:68:1: entryRuleParentKNode returns [EObject current=null] : iv_ruleParentKNode= ruleParentKNode EOF ;
    public final EObject entryRuleParentKNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParentKNode = null;


        try {
            // InternalKGraph.g:69:2: (iv_ruleParentKNode= ruleParentKNode EOF )
            // InternalKGraph.g:70:2: iv_ruleParentKNode= ruleParentKNode EOF
            {
             newCompositeNode(grammarAccess.getParentKNodeRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParentKNode=ruleParentKNode();

            state._fsp--;

             current =iv_ruleParentKNode; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:77:1: ruleParentKNode returns [EObject current=null] : ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* ) ;
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
            // InternalKGraph.g:80:28: ( ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* ) )
            // InternalKGraph.g:81:1: ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* )
            {
            // InternalKGraph.g:81:1: ( () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )* )
            // InternalKGraph.g:81:2: () (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )? ( (lv_data_3_0= ruleKNodeLayout ) ) ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )*
            {
            // InternalKGraph.g:81:2: ()
            // InternalKGraph.g:82:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getParentKNodeAccess().getKNodeAction_0(),
                        current);
                

            }

            // InternalKGraph.g:87:2: (otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )? )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==19) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalKGraph.g:87:4: otherlv_1= 'kgraph' ( (lv_data_2_0= ruleKIdentifier ) )?
                    {
                    otherlv_1=(Token)match(input,19,FollowSets000.FOLLOW_3); 

                        	newLeafNode(otherlv_1, grammarAccess.getParentKNodeAccess().getKgraphKeyword_1_0());
                        
                    // InternalKGraph.g:91:1: ( (lv_data_2_0= ruleKIdentifier ) )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==RULE_ID) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // InternalKGraph.g:92:1: (lv_data_2_0= ruleKIdentifier )
                            {
                            // InternalKGraph.g:92:1: (lv_data_2_0= ruleKIdentifier )
                            // InternalKGraph.g:93:3: lv_data_2_0= ruleKIdentifier
                            {
                             
                            	        newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKIdentifierParserRuleCall_1_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_3);
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

            // InternalKGraph.g:109:5: ( (lv_data_3_0= ruleKNodeLayout ) )
            // InternalKGraph.g:110:1: (lv_data_3_0= ruleKNodeLayout )
            {
            // InternalKGraph.g:110:1: (lv_data_3_0= ruleKNodeLayout )
            // InternalKGraph.g:111:3: lv_data_3_0= ruleKNodeLayout
            {
             
            	        newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKNodeLayoutParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_4);
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

            // InternalKGraph.g:127:2: ( ( (lv_labels_4_0= ruleKLabel ) ) | ( (lv_children_5_0= ruleKNode ) ) | ( (lv_ports_6_0= ruleKPort ) ) | ( (lv_outgoingEdges_7_0= ruleKEdge ) ) | ( (lv_data_8_0= ruleKRendering ) ) | ( (lv_data_9_0= ruleKRenderingLibrary ) ) )*
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
            	    // InternalKGraph.g:127:3: ( (lv_labels_4_0= ruleKLabel ) )
            	    {
            	    // InternalKGraph.g:127:3: ( (lv_labels_4_0= ruleKLabel ) )
            	    // InternalKGraph.g:128:1: (lv_labels_4_0= ruleKLabel )
            	    {
            	    // InternalKGraph.g:128:1: (lv_labels_4_0= ruleKLabel )
            	    // InternalKGraph.g:129:3: lv_labels_4_0= ruleKLabel
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getParentKNodeAccess().getLabelsKLabelParserRuleCall_3_0_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_4);
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
            	    // InternalKGraph.g:146:6: ( (lv_children_5_0= ruleKNode ) )
            	    {
            	    // InternalKGraph.g:146:6: ( (lv_children_5_0= ruleKNode ) )
            	    // InternalKGraph.g:147:1: (lv_children_5_0= ruleKNode )
            	    {
            	    // InternalKGraph.g:147:1: (lv_children_5_0= ruleKNode )
            	    // InternalKGraph.g:148:3: lv_children_5_0= ruleKNode
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getParentKNodeAccess().getChildrenKNodeParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_4);
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
            	    // InternalKGraph.g:165:6: ( (lv_ports_6_0= ruleKPort ) )
            	    {
            	    // InternalKGraph.g:165:6: ( (lv_ports_6_0= ruleKPort ) )
            	    // InternalKGraph.g:166:1: (lv_ports_6_0= ruleKPort )
            	    {
            	    // InternalKGraph.g:166:1: (lv_ports_6_0= ruleKPort )
            	    // InternalKGraph.g:167:3: lv_ports_6_0= ruleKPort
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getParentKNodeAccess().getPortsKPortParserRuleCall_3_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_4);
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
            	    // InternalKGraph.g:184:6: ( (lv_outgoingEdges_7_0= ruleKEdge ) )
            	    {
            	    // InternalKGraph.g:184:6: ( (lv_outgoingEdges_7_0= ruleKEdge ) )
            	    // InternalKGraph.g:185:1: (lv_outgoingEdges_7_0= ruleKEdge )
            	    {
            	    // InternalKGraph.g:185:1: (lv_outgoingEdges_7_0= ruleKEdge )
            	    // InternalKGraph.g:186:3: lv_outgoingEdges_7_0= ruleKEdge
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getParentKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_3_3_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_4);
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
            	    // InternalKGraph.g:203:6: ( (lv_data_8_0= ruleKRendering ) )
            	    {
            	    // InternalKGraph.g:203:6: ( (lv_data_8_0= ruleKRendering ) )
            	    // InternalKGraph.g:204:1: (lv_data_8_0= ruleKRendering )
            	    {
            	    // InternalKGraph.g:204:1: (lv_data_8_0= ruleKRendering )
            	    // InternalKGraph.g:205:3: lv_data_8_0= ruleKRendering
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKRenderingParserRuleCall_3_4_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_4);
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
            	    // InternalKGraph.g:222:6: ( (lv_data_9_0= ruleKRenderingLibrary ) )
            	    {
            	    // InternalKGraph.g:222:6: ( (lv_data_9_0= ruleKRenderingLibrary ) )
            	    // InternalKGraph.g:223:1: (lv_data_9_0= ruleKRenderingLibrary )
            	    {
            	    // InternalKGraph.g:223:1: (lv_data_9_0= ruleKRenderingLibrary )
            	    // InternalKGraph.g:224:3: lv_data_9_0= ruleKRenderingLibrary
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getParentKNodeAccess().getDataKRenderingLibraryParserRuleCall_3_5_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_4);
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
    // InternalKGraph.g:248:1: entryRuleKNode returns [EObject current=null] : iv_ruleKNode= ruleKNode EOF ;
    public final EObject entryRuleKNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKNode = null;


        try {
            // InternalKGraph.g:249:2: (iv_ruleKNode= ruleKNode EOF )
            // InternalKGraph.g:250:2: iv_ruleKNode= ruleKNode EOF
            {
             newCompositeNode(grammarAccess.getKNodeRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKNode=ruleKNode();

            state._fsp--;

             current =iv_ruleKNode; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:257:1: ruleKNode returns [EObject current=null] : ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) ) ;
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
            // InternalKGraph.g:260:28: ( ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) ) )
            // InternalKGraph.g:261:1: ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) )
            {
            // InternalKGraph.g:261:1: ( () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) ) )
            // InternalKGraph.g:261:2: () otherlv_1= 'knode' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) )
            {
            // InternalKGraph.g:261:2: ()
            // InternalKGraph.g:262:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKNodeAccess().getKNodeAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,20,FollowSets000.FOLLOW_5); 

                	newLeafNode(otherlv_1, grammarAccess.getKNodeAccess().getKnodeKeyword_1());
                
            // InternalKGraph.g:271:1: ( (lv_data_2_0= ruleKIdentifier ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalKGraph.g:272:1: (lv_data_2_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:272:1: (lv_data_2_0= ruleKIdentifier )
                    // InternalKGraph.g:273:3: lv_data_2_0= ruleKIdentifier
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKIdentifierParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_5);
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

            // InternalKGraph.g:289:3: ( (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' ) | ( (lv_data_12_0= ruleEmptyKNodeLayout ) ) )
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
                    // InternalKGraph.g:289:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' )
                    {
                    // InternalKGraph.g:289:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}' )
                    // InternalKGraph.g:289:6: otherlv_3= '{' ( (lv_data_4_0= ruleKNodeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )* otherlv_11= '}'
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_3); 

                        	newLeafNode(otherlv_3, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_3_0_0());
                        
                    // InternalKGraph.g:293:1: ( (lv_data_4_0= ruleKNodeLayout ) )
                    // InternalKGraph.g:294:1: (lv_data_4_0= ruleKNodeLayout )
                    {
                    // InternalKGraph.g:294:1: (lv_data_4_0= ruleKNodeLayout )
                    // InternalKGraph.g:295:3: lv_data_4_0= ruleKNodeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKNodeLayoutParserRuleCall_3_0_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_6);
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

                    // InternalKGraph.g:311:2: ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_children_6_0= ruleKNode ) ) | ( (lv_ports_7_0= ruleKPort ) ) | ( (lv_outgoingEdges_8_0= ruleKEdge ) ) | ( (lv_data_9_0= ruleKRendering ) ) | ( (lv_data_10_0= ruleKRenderingLibrary ) ) )*
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
                    	    // InternalKGraph.g:311:3: ( (lv_labels_5_0= ruleKLabel ) )
                    	    {
                    	    // InternalKGraph.g:311:3: ( (lv_labels_5_0= ruleKLabel ) )
                    	    // InternalKGraph.g:312:1: (lv_labels_5_0= ruleKLabel )
                    	    {
                    	    // InternalKGraph.g:312:1: (lv_labels_5_0= ruleKLabel )
                    	    // InternalKGraph.g:313:3: lv_labels_5_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getLabelsKLabelParserRuleCall_3_0_2_0_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_6);
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
                    	    // InternalKGraph.g:330:6: ( (lv_children_6_0= ruleKNode ) )
                    	    {
                    	    // InternalKGraph.g:330:6: ( (lv_children_6_0= ruleKNode ) )
                    	    // InternalKGraph.g:331:1: (lv_children_6_0= ruleKNode )
                    	    {
                    	    // InternalKGraph.g:331:1: (lv_children_6_0= ruleKNode )
                    	    // InternalKGraph.g:332:3: lv_children_6_0= ruleKNode
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_3_0_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_6);
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
                    	    // InternalKGraph.g:349:6: ( (lv_ports_7_0= ruleKPort ) )
                    	    {
                    	    // InternalKGraph.g:349:6: ( (lv_ports_7_0= ruleKPort ) )
                    	    // InternalKGraph.g:350:1: (lv_ports_7_0= ruleKPort )
                    	    {
                    	    // InternalKGraph.g:350:1: (lv_ports_7_0= ruleKPort )
                    	    // InternalKGraph.g:351:3: lv_ports_7_0= ruleKPort
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getPortsKPortParserRuleCall_3_0_2_2_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_6);
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
                    	    // InternalKGraph.g:368:6: ( (lv_outgoingEdges_8_0= ruleKEdge ) )
                    	    {
                    	    // InternalKGraph.g:368:6: ( (lv_outgoingEdges_8_0= ruleKEdge ) )
                    	    // InternalKGraph.g:369:1: (lv_outgoingEdges_8_0= ruleKEdge )
                    	    {
                    	    // InternalKGraph.g:369:1: (lv_outgoingEdges_8_0= ruleKEdge )
                    	    // InternalKGraph.g:370:3: lv_outgoingEdges_8_0= ruleKEdge
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_3_0_2_3_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_6);
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
                    	    // InternalKGraph.g:387:6: ( (lv_data_9_0= ruleKRendering ) )
                    	    {
                    	    // InternalKGraph.g:387:6: ( (lv_data_9_0= ruleKRendering ) )
                    	    // InternalKGraph.g:388:1: (lv_data_9_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:388:1: (lv_data_9_0= ruleKRendering )
                    	    // InternalKGraph.g:389:3: lv_data_9_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKRenderingParserRuleCall_3_0_2_4_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_6);
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
                    	    // InternalKGraph.g:406:6: ( (lv_data_10_0= ruleKRenderingLibrary ) )
                    	    {
                    	    // InternalKGraph.g:406:6: ( (lv_data_10_0= ruleKRenderingLibrary ) )
                    	    // InternalKGraph.g:407:1: (lv_data_10_0= ruleKRenderingLibrary )
                    	    {
                    	    // InternalKGraph.g:407:1: (lv_data_10_0= ruleKRenderingLibrary )
                    	    // InternalKGraph.g:408:3: lv_data_10_0= ruleKRenderingLibrary
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKRenderingLibraryParserRuleCall_3_0_2_5_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_6);
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

                    otherlv_11=(Token)match(input,22,FollowSets000.FOLLOW_2); 

                        	newLeafNode(otherlv_11, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_3_0_3());
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:429:6: ( (lv_data_12_0= ruleEmptyKNodeLayout ) )
                    {
                    // InternalKGraph.g:429:6: ( (lv_data_12_0= ruleEmptyKNodeLayout ) )
                    // InternalKGraph.g:430:1: (lv_data_12_0= ruleEmptyKNodeLayout )
                    {
                    // InternalKGraph.g:430:1: (lv_data_12_0= ruleEmptyKNodeLayout )
                    // InternalKGraph.g:431:3: lv_data_12_0= ruleEmptyKNodeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataEmptyKNodeLayoutParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:455:1: entryRuleKEdge returns [EObject current=null] : iv_ruleKEdge= ruleKEdge EOF ;
    public final EObject entryRuleKEdge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdge = null;


        try {
            // InternalKGraph.g:456:2: (iv_ruleKEdge= ruleKEdge EOF )
            // InternalKGraph.g:457:2: iv_ruleKEdge= ruleKEdge EOF
            {
             newCompositeNode(grammarAccess.getKEdgeRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKEdge=ruleKEdge();

            state._fsp--;

             current =iv_ruleKEdge; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:464:1: ruleKEdge returns [EObject current=null] : (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) ) ;
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
            // InternalKGraph.g:467:28: ( (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) ) )
            // InternalKGraph.g:468:1: (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) )
            {
            // InternalKGraph.g:468:1: (otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) ) )
            // InternalKGraph.g:468:3: otherlv_0= 'kedge' ( (lv_data_1_0= ruleKIdentifier ) )? otherlv_2= '(' (otherlv_3= ':' ( ( ruleQualifiedID ) ) )? otherlv_5= '->' ( ( ruleQualifiedID ) ) (otherlv_7= ':' ( ( ruleQualifiedID ) ) )? otherlv_9= ')' ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) )
            {
            otherlv_0=(Token)match(input,23,FollowSets000.FOLLOW_7); 

                	newLeafNode(otherlv_0, grammarAccess.getKEdgeAccess().getKedgeKeyword_0());
                
            // InternalKGraph.g:472:1: ( (lv_data_1_0= ruleKIdentifier ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalKGraph.g:473:1: (lv_data_1_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:473:1: (lv_data_1_0= ruleKIdentifier )
                    // InternalKGraph.g:474:3: lv_data_1_0= ruleKIdentifier
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKIdentifierParserRuleCall_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_8);
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

            otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_9); 

                	newLeafNode(otherlv_2, grammarAccess.getKEdgeAccess().getLeftParenthesisKeyword_2());
                
            // InternalKGraph.g:494:1: (otherlv_3= ':' ( ( ruleQualifiedID ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==25) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalKGraph.g:494:3: otherlv_3= ':' ( ( ruleQualifiedID ) )
                    {
                    otherlv_3=(Token)match(input,25,FollowSets000.FOLLOW_10); 

                        	newLeafNode(otherlv_3, grammarAccess.getKEdgeAccess().getColonKeyword_3_0());
                        
                    // InternalKGraph.g:498:1: ( ( ruleQualifiedID ) )
                    // InternalKGraph.g:499:1: ( ruleQualifiedID )
                    {
                    // InternalKGraph.g:499:1: ( ruleQualifiedID )
                    // InternalKGraph.g:500:3: ruleQualifiedID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getSourcePortKPortCrossReference_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_11);
                    ruleQualifiedID();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,26,FollowSets000.FOLLOW_10); 

                	newLeafNode(otherlv_5, grammarAccess.getKEdgeAccess().getHyphenMinusGreaterThanSignKeyword_4());
                
            // InternalKGraph.g:517:1: ( ( ruleQualifiedID ) )
            // InternalKGraph.g:518:1: ( ruleQualifiedID )
            {
            // InternalKGraph.g:518:1: ( ruleQualifiedID )
            // InternalKGraph.g:519:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKEdgeRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetKNodeCrossReference_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_12);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalKGraph.g:532:2: (otherlv_7= ':' ( ( ruleQualifiedID ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==25) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalKGraph.g:532:4: otherlv_7= ':' ( ( ruleQualifiedID ) )
                    {
                    otherlv_7=(Token)match(input,25,FollowSets000.FOLLOW_10); 

                        	newLeafNode(otherlv_7, grammarAccess.getKEdgeAccess().getColonKeyword_6_0());
                        
                    // InternalKGraph.g:536:1: ( ( ruleQualifiedID ) )
                    // InternalKGraph.g:537:1: ( ruleQualifiedID )
                    {
                    // InternalKGraph.g:537:1: ( ruleQualifiedID )
                    // InternalKGraph.g:538:3: ruleQualifiedID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetPortKPortCrossReference_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_13);
                    ruleQualifiedID();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_9=(Token)match(input,27,FollowSets000.FOLLOW_14); 

                	newLeafNode(otherlv_9, grammarAccess.getKEdgeAccess().getRightParenthesisKeyword_7());
                
            // InternalKGraph.g:555:1: ( (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' ) | ( (lv_data_15_0= ruleEmptyKEdgeLayout ) ) )
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
                    // InternalKGraph.g:555:2: (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' )
                    {
                    // InternalKGraph.g:555:2: (otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}' )
                    // InternalKGraph.g:555:4: otherlv_10= '{' ( (lv_data_11_0= ruleKEdgeLayout ) ) ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )* otherlv_14= '}'
                    {
                    otherlv_10=(Token)match(input,21,FollowSets000.FOLLOW_15); 

                        	newLeafNode(otherlv_10, grammarAccess.getKEdgeAccess().getLeftCurlyBracketKeyword_8_0_0());
                        
                    // InternalKGraph.g:559:1: ( (lv_data_11_0= ruleKEdgeLayout ) )
                    // InternalKGraph.g:560:1: (lv_data_11_0= ruleKEdgeLayout )
                    {
                    // InternalKGraph.g:560:1: (lv_data_11_0= ruleKEdgeLayout )
                    // InternalKGraph.g:561:3: lv_data_11_0= ruleKEdgeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKEdgeLayoutParserRuleCall_8_0_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_16);
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

                    // InternalKGraph.g:577:2: ( ( (lv_labels_12_0= ruleKLabel ) ) | ( (lv_data_13_0= ruleKRendering ) ) )*
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
                    	    // InternalKGraph.g:577:3: ( (lv_labels_12_0= ruleKLabel ) )
                    	    {
                    	    // InternalKGraph.g:577:3: ( (lv_labels_12_0= ruleKLabel ) )
                    	    // InternalKGraph.g:578:1: (lv_labels_12_0= ruleKLabel )
                    	    {
                    	    // InternalKGraph.g:578:1: (lv_labels_12_0= ruleKLabel )
                    	    // InternalKGraph.g:579:3: lv_labels_12_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_8_0_2_0_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_16);
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
                    	    // InternalKGraph.g:596:6: ( (lv_data_13_0= ruleKRendering ) )
                    	    {
                    	    // InternalKGraph.g:596:6: ( (lv_data_13_0= ruleKRendering ) )
                    	    // InternalKGraph.g:597:1: (lv_data_13_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:597:1: (lv_data_13_0= ruleKRendering )
                    	    // InternalKGraph.g:598:3: lv_data_13_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKRenderingParserRuleCall_8_0_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_16);
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

                    otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_2); 

                        	newLeafNode(otherlv_14, grammarAccess.getKEdgeAccess().getRightCurlyBracketKeyword_8_0_3());
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:619:6: ( (lv_data_15_0= ruleEmptyKEdgeLayout ) )
                    {
                    // InternalKGraph.g:619:6: ( (lv_data_15_0= ruleEmptyKEdgeLayout ) )
                    // InternalKGraph.g:620:1: (lv_data_15_0= ruleEmptyKEdgeLayout )
                    {
                    // InternalKGraph.g:620:1: (lv_data_15_0= ruleEmptyKEdgeLayout )
                    // InternalKGraph.g:621:3: lv_data_15_0= ruleEmptyKEdgeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataEmptyKEdgeLayoutParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:645:1: entryRuleKLabel returns [EObject current=null] : iv_ruleKLabel= ruleKLabel EOF ;
    public final EObject entryRuleKLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLabel = null;


        try {
            // InternalKGraph.g:646:2: (iv_ruleKLabel= ruleKLabel EOF )
            // InternalKGraph.g:647:2: iv_ruleKLabel= ruleKLabel EOF
            {
             newCompositeNode(grammarAccess.getKLabelRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKLabel=ruleKLabel();

            state._fsp--;

             current =iv_ruleKLabel; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:654:1: ruleKLabel returns [EObject current=null] : ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) ;
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
            // InternalKGraph.g:657:28: ( ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) )
            // InternalKGraph.g:658:1: ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            {
            // InternalKGraph.g:658:1: ( () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            // InternalKGraph.g:658:2: () otherlv_1= 'klabel' ( (lv_data_2_0= ruleKIdentifier ) )? ( (lv_text_3_0= RULE_STRING ) )? ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
            {
            // InternalKGraph.g:658:2: ()
            // InternalKGraph.g:659:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLabelAccess().getKLabelAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_17); 

                	newLeafNode(otherlv_1, grammarAccess.getKLabelAccess().getKlabelKeyword_1());
                
            // InternalKGraph.g:668:1: ( (lv_data_2_0= ruleKIdentifier ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalKGraph.g:669:1: (lv_data_2_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:669:1: (lv_data_2_0= ruleKIdentifier )
                    // InternalKGraph.g:670:3: lv_data_2_0= ruleKIdentifier
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKIdentifierParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_18);
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

            // InternalKGraph.g:686:3: ( (lv_text_3_0= RULE_STRING ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalKGraph.g:687:1: (lv_text_3_0= RULE_STRING )
                    {
                    // InternalKGraph.g:687:1: (lv_text_3_0= RULE_STRING )
                    // InternalKGraph.g:688:3: lv_text_3_0= RULE_STRING
                    {
                    lv_text_3_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_14); 

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

            // InternalKGraph.g:704:3: ( (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
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
                    // InternalKGraph.g:704:4: (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' )
                    {
                    // InternalKGraph.g:704:4: (otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}' )
                    // InternalKGraph.g:704:6: otherlv_4= '{' ( (lv_data_5_0= ruleKShapeLayout ) ) ( (lv_data_6_0= ruleKRendering ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,21,FollowSets000.FOLLOW_19); 

                        	newLeafNode(otherlv_4, grammarAccess.getKLabelAccess().getLeftCurlyBracketKeyword_4_0_0());
                        
                    // InternalKGraph.g:708:1: ( (lv_data_5_0= ruleKShapeLayout ) )
                    // InternalKGraph.g:709:1: (lv_data_5_0= ruleKShapeLayout )
                    {
                    // InternalKGraph.g:709:1: (lv_data_5_0= ruleKShapeLayout )
                    // InternalKGraph.g:710:3: lv_data_5_0= ruleKShapeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKShapeLayoutParserRuleCall_4_0_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_20);
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

                    // InternalKGraph.g:726:2: ( (lv_data_6_0= ruleKRendering ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==50||(LA14_0>=52 && LA14_0<=59)||(LA14_0>=62 && LA14_0<=65)) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalKGraph.g:727:1: (lv_data_6_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:727:1: (lv_data_6_0= ruleKRendering )
                    	    // InternalKGraph.g:728:3: lv_data_6_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKRenderingParserRuleCall_4_0_2_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_20);
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

                    otherlv_7=(Token)match(input,22,FollowSets000.FOLLOW_2); 

                        	newLeafNode(otherlv_7, grammarAccess.getKLabelAccess().getRightCurlyBracketKeyword_4_0_3());
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:749:6: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    {
                    // InternalKGraph.g:749:6: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    // InternalKGraph.g:750:1: (lv_data_8_0= ruleEmptyKShapeLayout )
                    {
                    // InternalKGraph.g:750:1: (lv_data_8_0= ruleEmptyKShapeLayout )
                    // InternalKGraph.g:751:3: lv_data_8_0= ruleEmptyKShapeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataEmptyKShapeLayoutParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:775:1: entryRuleKPort returns [EObject current=null] : iv_ruleKPort= ruleKPort EOF ;
    public final EObject entryRuleKPort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPort = null;


        try {
            // InternalKGraph.g:776:2: (iv_ruleKPort= ruleKPort EOF )
            // InternalKGraph.g:777:2: iv_ruleKPort= ruleKPort EOF
            {
             newCompositeNode(grammarAccess.getKPortRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPort=ruleKPort();

            state._fsp--;

             current =iv_ruleKPort; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:784:1: ruleKPort returns [EObject current=null] : ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) ;
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
            // InternalKGraph.g:787:28: ( ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) ) )
            // InternalKGraph.g:788:1: ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            {
            // InternalKGraph.g:788:1: ( () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) ) )
            // InternalKGraph.g:788:2: () otherlv_1= 'kport' ( (lv_data_2_0= ruleKIdentifier ) )? ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
            {
            // InternalKGraph.g:788:2: ()
            // InternalKGraph.g:789:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPortAccess().getKPortAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_5); 

                	newLeafNode(otherlv_1, grammarAccess.getKPortAccess().getKportKeyword_1());
                
            // InternalKGraph.g:798:1: ( (lv_data_2_0= ruleKIdentifier ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalKGraph.g:799:1: (lv_data_2_0= ruleKIdentifier )
                    {
                    // InternalKGraph.g:799:1: (lv_data_2_0= ruleKIdentifier )
                    // InternalKGraph.g:800:3: lv_data_2_0= ruleKIdentifier
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKIdentifierParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_14);
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

            // InternalKGraph.g:816:3: ( (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' ) | ( (lv_data_8_0= ruleEmptyKShapeLayout ) ) )
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
                    // InternalKGraph.g:816:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' )
                    {
                    // InternalKGraph.g:816:4: (otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}' )
                    // InternalKGraph.g:816:6: otherlv_3= '{' ( (lv_data_4_0= ruleKShapeLayout ) ) ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )* otherlv_7= '}'
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_21); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPortAccess().getLeftCurlyBracketKeyword_3_0_0());
                        
                    // InternalKGraph.g:820:1: ( (lv_data_4_0= ruleKShapeLayout ) )
                    // InternalKGraph.g:821:1: (lv_data_4_0= ruleKShapeLayout )
                    {
                    // InternalKGraph.g:821:1: (lv_data_4_0= ruleKShapeLayout )
                    // InternalKGraph.g:822:3: lv_data_4_0= ruleKShapeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKShapeLayoutParserRuleCall_3_0_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_16);
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

                    // InternalKGraph.g:838:2: ( ( (lv_labels_5_0= ruleKLabel ) ) | ( (lv_data_6_0= ruleKRendering ) ) )*
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
                    	    // InternalKGraph.g:838:3: ( (lv_labels_5_0= ruleKLabel ) )
                    	    {
                    	    // InternalKGraph.g:838:3: ( (lv_labels_5_0= ruleKLabel ) )
                    	    // InternalKGraph.g:839:1: (lv_labels_5_0= ruleKLabel )
                    	    {
                    	    // InternalKGraph.g:839:1: (lv_labels_5_0= ruleKLabel )
                    	    // InternalKGraph.g:840:3: lv_labels_5_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_3_0_2_0_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_16);
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
                    	    // InternalKGraph.g:857:6: ( (lv_data_6_0= ruleKRendering ) )
                    	    {
                    	    // InternalKGraph.g:857:6: ( (lv_data_6_0= ruleKRendering ) )
                    	    // InternalKGraph.g:858:1: (lv_data_6_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:858:1: (lv_data_6_0= ruleKRendering )
                    	    // InternalKGraph.g:859:3: lv_data_6_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKRenderingParserRuleCall_3_0_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_16);
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

                    otherlv_7=(Token)match(input,22,FollowSets000.FOLLOW_2); 

                        	newLeafNode(otherlv_7, grammarAccess.getKPortAccess().getRightCurlyBracketKeyword_3_0_3());
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:880:6: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    {
                    // InternalKGraph.g:880:6: ( (lv_data_8_0= ruleEmptyKShapeLayout ) )
                    // InternalKGraph.g:881:1: (lv_data_8_0= ruleEmptyKShapeLayout )
                    {
                    // InternalKGraph.g:881:1: (lv_data_8_0= ruleEmptyKShapeLayout )
                    // InternalKGraph.g:882:3: lv_data_8_0= ruleEmptyKShapeLayout
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getDataEmptyKShapeLayoutParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:906:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // InternalKGraph.g:907:2: (iv_ruleProperty= ruleProperty EOF )
            // InternalKGraph.g:908:2: iv_ruleProperty= ruleProperty EOF
            {
             newCompositeNode(grammarAccess.getPropertyRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleProperty=ruleProperty();

            state._fsp--;

             current =iv_ruleProperty; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:915:1: ruleProperty returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:918:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // InternalKGraph.g:919:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // InternalKGraph.g:919:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) ) )
            // InternalKGraph.g:919:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= '=' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // InternalKGraph.g:919:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // InternalKGraph.g:920:1: (lv_key_0_0= ruleQualifiedID )
            {
            // InternalKGraph.g:920:1: (lv_key_0_0= ruleQualifiedID )
            // InternalKGraph.g:921:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPropertyAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_22);
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

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_23); 

                	newLeafNode(otherlv_1, grammarAccess.getPropertyAccess().getEqualsSignKeyword_1());
                
            // InternalKGraph.g:941:1: ( (lv_value_2_0= rulePropertyValue ) )
            // InternalKGraph.g:942:1: (lv_value_2_0= rulePropertyValue )
            {
            // InternalKGraph.g:942:1: (lv_value_2_0= rulePropertyValue )
            // InternalKGraph.g:943:3: lv_value_2_0= rulePropertyValue
            {
             
            	        newCompositeNode(grammarAccess.getPropertyAccess().getValuePropertyValueParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:967:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // InternalKGraph.g:968:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // InternalKGraph.g:969:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:976:1: ruleKIdentifier returns [EObject current=null] : ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        EObject lv_persistentEntries_2_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:979:28: ( ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? ) )
            // InternalKGraph.g:980:1: ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? )
            {
            // InternalKGraph.g:980:1: ( ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )? )
            // InternalKGraph.g:980:2: ( (lv_id_0_0= ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )?
            {
            // InternalKGraph.g:980:2: ( (lv_id_0_0= ruleQualifiedID ) )
            // InternalKGraph.g:981:1: (lv_id_0_0= ruleQualifiedID )
            {
            // InternalKGraph.g:981:1: (lv_id_0_0= ruleQualifiedID )
            // InternalKGraph.g:982:3: lv_id_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getKIdentifierAccess().getIdQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_24);
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

            // InternalKGraph.g:998:2: (otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==31) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalKGraph.g:998:4: otherlv_1= '[' ( (lv_persistentEntries_2_0= ruleProperty ) )* otherlv_3= ']'
                    {
                    otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_25); 

                        	newLeafNode(otherlv_1, grammarAccess.getKIdentifierAccess().getLeftSquareBracketKeyword_1_0());
                        
                    // InternalKGraph.g:1002:1: ( (lv_persistentEntries_2_0= ruleProperty ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==RULE_ID) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalKGraph.g:1003:1: (lv_persistentEntries_2_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:1003:1: (lv_persistentEntries_2_0= ruleProperty )
                    	    // InternalKGraph.g:1004:3: lv_persistentEntries_2_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPropertyParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_25);
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

                    otherlv_3=(Token)match(input,32,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1032:1: entryRuleKNodeLayout returns [EObject current=null] : iv_ruleKNodeLayout= ruleKNodeLayout EOF ;
    public final EObject entryRuleKNodeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKNodeLayout = null;


        try {
            // InternalKGraph.g:1033:2: (iv_ruleKNodeLayout= ruleKNodeLayout EOF )
            // InternalKGraph.g:1034:2: iv_ruleKNodeLayout= ruleKNodeLayout EOF
            {
             newCompositeNode(grammarAccess.getKNodeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKNodeLayout=ruleKNodeLayout();

            state._fsp--;

             current =iv_ruleKNodeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1041:1: ruleKNodeLayout returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) ) ;
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
            // InternalKGraph.g:1044:28: ( ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) ) )
            // InternalKGraph.g:1045:1: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) )
            {
            // InternalKGraph.g:1045:1: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) ) )
            // InternalKGraph.g:1045:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) )
            {
            // InternalKGraph.g:1045:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) ) )
            // InternalKGraph.g:1047:1: ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) )
            {
            // InternalKGraph.g:1047:1: ( ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* ) )
            // InternalKGraph.g:1048:2: ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0());
            	
            // InternalKGraph.g:1051:2: ( ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )* )
            // InternalKGraph.g:1052:3: ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )*
            {
            // InternalKGraph.g:1052:3: ( ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) ) )*
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
            	    // InternalKGraph.g:1054:4: ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1054:4: ({...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1055:5: {...}? => ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalKGraph.g:1055:108: ( ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1056:6: ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // InternalKGraph.g:1059:6: ({...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1059:7: {...}? => (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    }
            	    // InternalKGraph.g:1059:16: (otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1059:18: otherlv_1= 'pos' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_1=(Token)match(input,33,FollowSets000.FOLLOW_26); 

            	        	newLeafNode(otherlv_1, grammarAccess.getKNodeLayoutAccess().getPosKeyword_0_0_0());
            	        
            	    otherlv_2=(Token)match(input,25,FollowSets000.FOLLOW_27); 

            	        	newLeafNode(otherlv_2, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_0_1());
            	        
            	    // InternalKGraph.g:1067:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1069:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1069:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1070:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    	  getUnorderedGroupHelper().enter(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2());
            	    	
            	    // InternalKGraph.g:1073:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1074:3: ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1074:3: ( ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) ) )*
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
            	    	    // InternalKGraph.g:1076:4: ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1076:4: ({...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1077:5: {...}? => ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1077:112: ( ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1078:6: ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 0);
            	    	    	 				
            	    	    // InternalKGraph.g:1081:6: ({...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1081:7: {...}? => (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1081:16: (otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1081:18: otherlv_4= 'x' otherlv_5= '=' ( (lv_xpos_6_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_4=(Token)match(input,34,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_4, grammarAccess.getKNodeLayoutAccess().getXKeyword_0_0_2_0_0());
            	    	        
            	    	    otherlv_5=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_5, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_0_2_0_1());
            	    	        
            	    	    // InternalKGraph.g:1089:1: ( (lv_xpos_6_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1090:1: (lv_xpos_6_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1090:1: (lv_xpos_6_0= ruleFloat )
            	    	    // InternalKGraph.g:1091:3: lv_xpos_6_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKNodeLayoutAccess().getXposFloatParserRuleCall_0_0_2_0_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_27);
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
            	    	    // InternalKGraph.g:1114:4: ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1114:4: ({...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1115:5: {...}? => ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1115:112: ( ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1116:6: ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_0_2(), 1);
            	    	    	 				
            	    	    // InternalKGraph.g:1119:6: ({...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1119:7: {...}? => (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1119:16: (otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1119:18: otherlv_7= 'y' otherlv_8= '=' ( (lv_ypos_9_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_7=(Token)match(input,35,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_7, grammarAccess.getKNodeLayoutAccess().getYKeyword_0_0_2_1_0());
            	    	        
            	    	    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_8, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_0_2_1_1());
            	    	        
            	    	    // InternalKGraph.g:1127:1: ( (lv_ypos_9_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1128:1: (lv_ypos_9_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1128:1: (lv_ypos_9_0= ruleFloat )
            	    	    // InternalKGraph.g:1129:3: lv_ypos_9_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKNodeLayoutAccess().getYposFloatParserRuleCall_0_0_2_1_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_27);
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
            	    // InternalKGraph.g:1166:4: ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1166:4: ({...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1167:5: {...}? => ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalKGraph.g:1167:108: ( ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1168:6: ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // InternalKGraph.g:1171:6: ({...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1171:7: {...}? => (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    }
            	    // InternalKGraph.g:1171:16: (otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1171:18: otherlv_10= 'size' otherlv_11= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_10=(Token)match(input,36,FollowSets000.FOLLOW_26); 

            	        	newLeafNode(otherlv_10, grammarAccess.getKNodeLayoutAccess().getSizeKeyword_0_1_0());
            	        
            	    otherlv_11=(Token)match(input,25,FollowSets000.FOLLOW_29); 

            	        	newLeafNode(otherlv_11, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_1_1());
            	        
            	    // InternalKGraph.g:1179:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1181:1: ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1181:1: ( ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1182:2: ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    	  getUnorderedGroupHelper().enter(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2());
            	    	
            	    // InternalKGraph.g:1185:2: ( ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1186:3: ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1186:3: ( ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) ) )*
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
            	    	    // InternalKGraph.g:1188:4: ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1188:4: ({...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1189:5: {...}? => ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1189:112: ( ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1190:6: ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 0);
            	    	    	 				
            	    	    // InternalKGraph.g:1193:6: ({...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1193:7: {...}? => (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1193:16: (otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1193:18: otherlv_13= 'width' otherlv_14= '=' ( (lv_width_15_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_13=(Token)match(input,37,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_13, grammarAccess.getKNodeLayoutAccess().getWidthKeyword_0_1_2_0_0());
            	    	        
            	    	    otherlv_14=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_14, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_1_2_0_1());
            	    	        
            	    	    // InternalKGraph.g:1201:1: ( (lv_width_15_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1202:1: (lv_width_15_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1202:1: (lv_width_15_0= ruleFloat )
            	    	    // InternalKGraph.g:1203:3: lv_width_15_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKNodeLayoutAccess().getWidthFloatParserRuleCall_0_1_2_0_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_29);
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
            	    	    // InternalKGraph.g:1226:4: ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1226:4: ({...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1227:5: {...}? => ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1227:112: ( ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1228:6: ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0_1_2(), 1);
            	    	    	 				
            	    	    // InternalKGraph.g:1231:6: ({...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1231:7: {...}? => (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1231:16: (otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1231:18: otherlv_16= 'height' otherlv_17= '=' ( (lv_height_18_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_16=(Token)match(input,38,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_16, grammarAccess.getKNodeLayoutAccess().getHeightKeyword_0_1_2_1_0());
            	    	        
            	    	    otherlv_17=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_17, grammarAccess.getKNodeLayoutAccess().getEqualsSignKeyword_0_1_2_1_1());
            	    	        
            	    	    // InternalKGraph.g:1239:1: ( (lv_height_18_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1240:1: (lv_height_18_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1240:1: (lv_height_18_0= ruleFloat )
            	    	    // InternalKGraph.g:1241:3: lv_height_18_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKNodeLayoutAccess().getHeightFloatParserRuleCall_0_1_2_1_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_29);
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
            	    // InternalKGraph.g:1278:4: ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) )
            	    {
            	    // InternalKGraph.g:1278:4: ({...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) ) )
            	    // InternalKGraph.g:1279:5: {...}? => ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 2)");
            	    }
            	    // InternalKGraph.g:1279:108: ( ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) ) )
            	    // InternalKGraph.g:1280:6: ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKNodeLayoutAccess().getUnorderedGroup_0(), 2);
            	    	 				
            	    // InternalKGraph.g:1283:6: ({...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* ) )
            	    // InternalKGraph.g:1283:7: {...}? => (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKNodeLayout", "true");
            	    }
            	    // InternalKGraph.g:1283:16: (otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )* )
            	    // InternalKGraph.g:1283:18: otherlv_19= 'properties' otherlv_20= ':' ( (lv_persistentEntries_21_0= ruleProperty ) )*
            	    {
            	    otherlv_19=(Token)match(input,39,FollowSets000.FOLLOW_26); 

            	        	newLeafNode(otherlv_19, grammarAccess.getKNodeLayoutAccess().getPropertiesKeyword_0_2_0());
            	        
            	    otherlv_20=(Token)match(input,25,FollowSets000.FOLLOW_3); 

            	        	newLeafNode(otherlv_20, grammarAccess.getKNodeLayoutAccess().getColonKeyword_0_2_1());
            	        
            	    // InternalKGraph.g:1291:1: ( (lv_persistentEntries_21_0= ruleProperty ) )*
            	    loop23:
            	    do {
            	        int alt23=2;
            	        int LA23_0 = input.LA(1);

            	        if ( (LA23_0==RULE_ID) ) {
            	            alt23=1;
            	        }


            	        switch (alt23) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1292:1: (lv_persistentEntries_21_0= ruleProperty )
            	    	    {
            	    	    // InternalKGraph.g:1292:1: (lv_persistentEntries_21_0= ruleProperty )
            	    	    // InternalKGraph.g:1293:3: lv_persistentEntries_21_0= ruleProperty
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKNodeLayoutAccess().getPersistentEntriesPropertyParserRuleCall_0_2_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_3);
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

            // InternalKGraph.g:1323:2: ( (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) ) | ( (lv_insets_25_0= ruleEmptyKInsets ) ) )
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
                    // InternalKGraph.g:1323:3: (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) )
                    {
                    // InternalKGraph.g:1323:3: (otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) ) )
                    // InternalKGraph.g:1323:5: otherlv_22= 'insets' otherlv_23= ':' ( (lv_insets_24_0= ruleKInsets ) )
                    {
                    otherlv_22=(Token)match(input,40,FollowSets000.FOLLOW_26); 

                        	newLeafNode(otherlv_22, grammarAccess.getKNodeLayoutAccess().getInsetsKeyword_1_0_0());
                        
                    otherlv_23=(Token)match(input,25,FollowSets000.FOLLOW_30); 

                        	newLeafNode(otherlv_23, grammarAccess.getKNodeLayoutAccess().getColonKeyword_1_0_1());
                        
                    // InternalKGraph.g:1331:1: ( (lv_insets_24_0= ruleKInsets ) )
                    // InternalKGraph.g:1332:1: (lv_insets_24_0= ruleKInsets )
                    {
                    // InternalKGraph.g:1332:1: (lv_insets_24_0= ruleKInsets )
                    // InternalKGraph.g:1333:3: lv_insets_24_0= ruleKInsets
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeLayoutAccess().getInsetsKInsetsParserRuleCall_1_0_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
                    // InternalKGraph.g:1350:6: ( (lv_insets_25_0= ruleEmptyKInsets ) )
                    {
                    // InternalKGraph.g:1350:6: ( (lv_insets_25_0= ruleEmptyKInsets ) )
                    // InternalKGraph.g:1351:1: (lv_insets_25_0= ruleEmptyKInsets )
                    {
                    // InternalKGraph.g:1351:1: (lv_insets_25_0= ruleEmptyKInsets )
                    // InternalKGraph.g:1352:3: lv_insets_25_0= ruleEmptyKInsets
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeLayoutAccess().getInsetsEmptyKInsetsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:1376:1: entryRuleEmptyKNodeLayout returns [EObject current=null] : iv_ruleEmptyKNodeLayout= ruleEmptyKNodeLayout EOF ;
    public final EObject entryRuleEmptyKNodeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKNodeLayout = null;


        try {
            // InternalKGraph.g:1377:2: (iv_ruleEmptyKNodeLayout= ruleEmptyKNodeLayout EOF )
            // InternalKGraph.g:1378:2: iv_ruleEmptyKNodeLayout= ruleEmptyKNodeLayout EOF
            {
             newCompositeNode(grammarAccess.getEmptyKNodeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEmptyKNodeLayout=ruleEmptyKNodeLayout();

            state._fsp--;

             current =iv_ruleEmptyKNodeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1385:1: ruleEmptyKNodeLayout returns [EObject current=null] : ( (lv_insets_0_0= ruleEmptyKInsets ) ) ;
    public final EObject ruleEmptyKNodeLayout() throws RecognitionException {
        EObject current = null;

        EObject lv_insets_0_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:1388:28: ( ( (lv_insets_0_0= ruleEmptyKInsets ) ) )
            // InternalKGraph.g:1389:1: ( (lv_insets_0_0= ruleEmptyKInsets ) )
            {
            // InternalKGraph.g:1389:1: ( (lv_insets_0_0= ruleEmptyKInsets ) )
            // InternalKGraph.g:1390:1: (lv_insets_0_0= ruleEmptyKInsets )
            {
            // InternalKGraph.g:1390:1: (lv_insets_0_0= ruleEmptyKInsets )
            // InternalKGraph.g:1391:3: lv_insets_0_0= ruleEmptyKInsets
            {
             
            	        newCompositeNode(grammarAccess.getEmptyKNodeLayoutAccess().getInsetsEmptyKInsetsParserRuleCall_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:1415:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // InternalKGraph.g:1416:2: (iv_ruleKInsets= ruleKInsets EOF )
            // InternalKGraph.g:1417:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1424:1: ruleKInsets returns [EObject current=null] : ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ;
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
            // InternalKGraph.g:1427:28: ( ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:1428:1: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:1428:1: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:1428:2: () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:1428:2: ()
            // InternalKGraph.g:1429:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            // InternalKGraph.g:1434:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:1436:1: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:1436:1: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:1437:2: ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKInsetsAccess().getUnorderedGroup_1());
            	
            // InternalKGraph.g:1440:2: ( ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )* )
            // InternalKGraph.g:1441:3: ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:1441:3: ( ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) ) )*
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
            	    // InternalKGraph.g:1443:4: ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1443:4: ({...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1444:5: {...}? => ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 0)");
            	    }
            	    // InternalKGraph.g:1444:104: ( ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1445:6: ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 0);
            	    	 				
            	    // InternalKGraph.g:1448:6: ({...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1448:7: {...}? => (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1448:16: (otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1448:18: otherlv_2= 'top' otherlv_3= '=' ( (lv_top_4_0= ruleFloat ) )
            	    {
            	    otherlv_2=(Token)match(input,41,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getTopKeyword_1_0_0());
            	        
            	    otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_0_1());
            	        
            	    // InternalKGraph.g:1456:1: ( (lv_top_4_0= ruleFloat ) )
            	    // InternalKGraph.g:1457:1: (lv_top_4_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1457:1: (lv_top_4_0= ruleFloat )
            	    // InternalKGraph.g:1458:3: lv_top_4_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopFloatParserRuleCall_1_0_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_31);
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
            	    // InternalKGraph.g:1481:4: ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1481:4: ({...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1482:5: {...}? => ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 1)");
            	    }
            	    // InternalKGraph.g:1482:104: ( ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1483:6: ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 1);
            	    	 				
            	    // InternalKGraph.g:1486:6: ({...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1486:7: {...}? => (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1486:16: (otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1486:18: otherlv_5= 'bottom' otherlv_6= '=' ( (lv_bottom_7_0= ruleFloat ) )
            	    {
            	    otherlv_5=(Token)match(input,42,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_1_1_0());
            	        
            	    otherlv_6=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_6, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_1_1());
            	        
            	    // InternalKGraph.g:1494:1: ( (lv_bottom_7_0= ruleFloat ) )
            	    // InternalKGraph.g:1495:1: (lv_bottom_7_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1495:1: (lv_bottom_7_0= ruleFloat )
            	    // InternalKGraph.g:1496:3: lv_bottom_7_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomFloatParserRuleCall_1_1_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_31);
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
            	    // InternalKGraph.g:1519:4: ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1519:4: ({...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1520:5: {...}? => ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 2)");
            	    }
            	    // InternalKGraph.g:1520:104: ( ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1521:6: ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 2);
            	    	 				
            	    // InternalKGraph.g:1524:6: ({...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1524:7: {...}? => (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1524:16: (otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1524:18: otherlv_8= 'left' otherlv_9= '=' ( (lv_left_10_0= ruleFloat ) )
            	    {
            	    otherlv_8=(Token)match(input,43,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_8, grammarAccess.getKInsetsAccess().getLeftKeyword_1_2_0());
            	        
            	    otherlv_9=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_2_1());
            	        
            	    // InternalKGraph.g:1532:1: ( (lv_left_10_0= ruleFloat ) )
            	    // InternalKGraph.g:1533:1: (lv_left_10_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1533:1: (lv_left_10_0= ruleFloat )
            	    // InternalKGraph.g:1534:3: lv_left_10_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftFloatParserRuleCall_1_2_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_31);
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
            	    // InternalKGraph.g:1557:4: ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1557:4: ({...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:1558:5: {...}? => ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "getUnorderedGroupHelper().canSelect(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 3)");
            	    }
            	    // InternalKGraph.g:1558:104: ( ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:1559:6: ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKInsetsAccess().getUnorderedGroup_1(), 3);
            	    	 				
            	    // InternalKGraph.g:1562:6: ({...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:1562:7: {...}? => (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKInsets", "true");
            	    }
            	    // InternalKGraph.g:1562:16: (otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) ) )
            	    // InternalKGraph.g:1562:18: otherlv_11= 'right' otherlv_12= '=' ( (lv_right_13_0= ruleFloat ) )
            	    {
            	    otherlv_11=(Token)match(input,44,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_11, grammarAccess.getKInsetsAccess().getRightKeyword_1_3_0());
            	        
            	    otherlv_12=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_12, grammarAccess.getKInsetsAccess().getEqualsSignKeyword_1_3_1());
            	        
            	    // InternalKGraph.g:1570:1: ( (lv_right_13_0= ruleFloat ) )
            	    // InternalKGraph.g:1571:1: (lv_right_13_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:1571:1: (lv_right_13_0= ruleFloat )
            	    // InternalKGraph.g:1572:3: lv_right_13_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightFloatParserRuleCall_1_3_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_31);
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
    // InternalKGraph.g:1610:1: entryRuleEmptyKInsets returns [EObject current=null] : iv_ruleEmptyKInsets= ruleEmptyKInsets EOF ;
    public final EObject entryRuleEmptyKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKInsets = null;


        try {
            // InternalKGraph.g:1611:2: (iv_ruleEmptyKInsets= ruleEmptyKInsets EOF )
            // InternalKGraph.g:1612:2: iv_ruleEmptyKInsets= ruleEmptyKInsets EOF
            {
             newCompositeNode(grammarAccess.getEmptyKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEmptyKInsets=ruleEmptyKInsets();

            state._fsp--;

             current =iv_ruleEmptyKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1619:1: ruleEmptyKInsets returns [EObject current=null] : () ;
    public final EObject ruleEmptyKInsets() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:1622:28: ( () )
            // InternalKGraph.g:1623:1: ()
            {
            // InternalKGraph.g:1623:1: ()
            // InternalKGraph.g:1624:5: 
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
    // InternalKGraph.g:1637:1: entryRuleKShapeLayout returns [EObject current=null] : iv_ruleKShapeLayout= ruleKShapeLayout EOF ;
    public final EObject entryRuleKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShapeLayout = null;


        try {
            // InternalKGraph.g:1638:2: (iv_ruleKShapeLayout= ruleKShapeLayout EOF )
            // InternalKGraph.g:1639:2: iv_ruleKShapeLayout= ruleKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getKShapeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKShapeLayout=ruleKShapeLayout();

            state._fsp--;

             current =iv_ruleKShapeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1646:1: ruleKShapeLayout returns [EObject current=null] : ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ) ;
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
            // InternalKGraph.g:1649:28: ( ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:1650:1: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:1650:1: ( () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:1650:2: () ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:1650:2: ()
            // InternalKGraph.g:1651:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0(),
                        current);
                

            }

            // InternalKGraph.g:1656:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) ) )
            // InternalKGraph.g:1658:1: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) )
            {
            // InternalKGraph.g:1658:1: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* ) )
            // InternalKGraph.g:1659:2: ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1());
            	
            // InternalKGraph.g:1662:2: ( ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )* )
            // InternalKGraph.g:1663:3: ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )*
            {
            // InternalKGraph.g:1663:3: ( ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) ) )*
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
            	    // InternalKGraph.g:1665:4: ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1665:4: ({...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1666:5: {...}? => ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 0)");
            	    }
            	    // InternalKGraph.g:1666:109: ( ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1667:6: ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 0);
            	    	 				
            	    // InternalKGraph.g:1670:6: ({...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1670:7: {...}? => (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    }
            	    // InternalKGraph.g:1670:16: (otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1670:18: otherlv_2= 'pos' otherlv_3= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_2=(Token)match(input,33,FollowSets000.FOLLOW_26); 

            	        	newLeafNode(otherlv_2, grammarAccess.getKShapeLayoutAccess().getPosKeyword_1_0_0());
            	        
            	    otherlv_3=(Token)match(input,25,FollowSets000.FOLLOW_32); 

            	        	newLeafNode(otherlv_3, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_0_1());
            	        
            	    // InternalKGraph.g:1678:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1680:1: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1680:1: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1681:2: ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    	  getUnorderedGroupHelper().enter(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2());
            	    	
            	    // InternalKGraph.g:1684:2: ( ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1685:3: ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1685:3: ( ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) ) )*
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
            	    	    // InternalKGraph.g:1687:4: ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1687:4: ({...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1688:5: {...}? => ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1688:113: ( ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1689:6: ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 0);
            	    	    	 				
            	    	    // InternalKGraph.g:1692:6: ({...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1692:7: {...}? => (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1692:16: (otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1692:18: otherlv_5= 'x' otherlv_6= '=' ( (lv_xpos_7_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_5=(Token)match(input,34,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_5, grammarAccess.getKShapeLayoutAccess().getXKeyword_1_0_2_0_0());
            	    	        
            	    	    otherlv_6=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_6, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_0_2_0_1());
            	    	        
            	    	    // InternalKGraph.g:1700:1: ( (lv_xpos_7_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1701:1: (lv_xpos_7_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1701:1: (lv_xpos_7_0= ruleFloat )
            	    	    // InternalKGraph.g:1702:3: lv_xpos_7_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getXposFloatParserRuleCall_1_0_2_0_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_32);
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
            	    	    // InternalKGraph.g:1725:4: ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1725:4: ({...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1726:5: {...}? => ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1726:113: ( ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1727:6: ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_0_2(), 1);
            	    	    	 				
            	    	    // InternalKGraph.g:1730:6: ({...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1730:7: {...}? => (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1730:16: (otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1730:18: otherlv_8= 'y' otherlv_9= '=' ( (lv_ypos_10_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_8=(Token)match(input,35,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_8, grammarAccess.getKShapeLayoutAccess().getYKeyword_1_0_2_1_0());
            	    	        
            	    	    otherlv_9=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_9, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_0_2_1_1());
            	    	        
            	    	    // InternalKGraph.g:1738:1: ( (lv_ypos_10_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1739:1: (lv_ypos_10_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1739:1: (lv_ypos_10_0= ruleFloat )
            	    	    // InternalKGraph.g:1740:3: lv_ypos_10_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getYposFloatParserRuleCall_1_0_2_1_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_32);
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
            	    // InternalKGraph.g:1777:4: ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:1777:4: ({...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) ) )
            	    // InternalKGraph.g:1778:5: {...}? => ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 1)");
            	    }
            	    // InternalKGraph.g:1778:109: ( ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ) )
            	    // InternalKGraph.g:1779:6: ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 1);
            	    	 				
            	    // InternalKGraph.g:1782:6: ({...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            	    // InternalKGraph.g:1782:7: {...}? => (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    }
            	    // InternalKGraph.g:1782:16: (otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            	    // InternalKGraph.g:1782:18: otherlv_11= 'size' otherlv_12= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    {
            	    otherlv_11=(Token)match(input,36,FollowSets000.FOLLOW_26); 

            	        	newLeafNode(otherlv_11, grammarAccess.getKShapeLayoutAccess().getSizeKeyword_1_1_0());
            	        
            	    otherlv_12=(Token)match(input,25,FollowSets000.FOLLOW_33); 

            	        	newLeafNode(otherlv_12, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_1_1());
            	        
            	    // InternalKGraph.g:1790:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            	    // InternalKGraph.g:1792:1: ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    {
            	    // InternalKGraph.g:1792:1: ( ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* ) )
            	    // InternalKGraph.g:1793:2: ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* )
            	    {
            	     
            	    	  getUnorderedGroupHelper().enter(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2());
            	    	
            	    // InternalKGraph.g:1796:2: ( ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )* )
            	    // InternalKGraph.g:1797:3: ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )*
            	    {
            	    // InternalKGraph.g:1797:3: ( ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) ) )*
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
            	    	    // InternalKGraph.g:1799:4: ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1799:4: ({...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1800:5: {...}? => ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 0) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 0)");
            	    	    }
            	    	    // InternalKGraph.g:1800:113: ( ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1801:6: ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 0);
            	    	    	 				
            	    	    // InternalKGraph.g:1804:6: ({...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1804:7: {...}? => (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1804:16: (otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1804:18: otherlv_14= 'width' otherlv_15= '=' ( (lv_width_16_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_14=(Token)match(input,37,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_14, grammarAccess.getKShapeLayoutAccess().getWidthKeyword_1_1_2_0_0());
            	    	        
            	    	    otherlv_15=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_15, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_1_2_0_1());
            	    	        
            	    	    // InternalKGraph.g:1812:1: ( (lv_width_16_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1813:1: (lv_width_16_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1813:1: (lv_width_16_0= ruleFloat )
            	    	    // InternalKGraph.g:1814:3: lv_width_16_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getWidthFloatParserRuleCall_1_1_2_0_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_33);
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
            	    	    // InternalKGraph.g:1837:4: ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) )
            	    	    {
            	    	    // InternalKGraph.g:1837:4: ({...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) ) )
            	    	    // InternalKGraph.g:1838:5: {...}? => ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) )
            	    	    {
            	    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 1) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 1)");
            	    	    }
            	    	    // InternalKGraph.g:1838:113: ( ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) ) )
            	    	    // InternalKGraph.g:1839:6: ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) )
            	    	    {
            	    	     
            	    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1_1_2(), 1);
            	    	    	 				
            	    	    // InternalKGraph.g:1842:6: ({...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) ) )
            	    	    // InternalKGraph.g:1842:7: {...}? => (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    	    }
            	    	    // InternalKGraph.g:1842:16: (otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) ) )
            	    	    // InternalKGraph.g:1842:18: otherlv_17= 'height' otherlv_18= '=' ( (lv_height_19_0= ruleFloat ) )
            	    	    {
            	    	    otherlv_17=(Token)match(input,38,FollowSets000.FOLLOW_22); 

            	    	        	newLeafNode(otherlv_17, grammarAccess.getKShapeLayoutAccess().getHeightKeyword_1_1_2_1_0());
            	    	        
            	    	    otherlv_18=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	    	        	newLeafNode(otherlv_18, grammarAccess.getKShapeLayoutAccess().getEqualsSignKeyword_1_1_2_1_1());
            	    	        
            	    	    // InternalKGraph.g:1850:1: ( (lv_height_19_0= ruleFloat ) )
            	    	    // InternalKGraph.g:1851:1: (lv_height_19_0= ruleFloat )
            	    	    {
            	    	    // InternalKGraph.g:1851:1: (lv_height_19_0= ruleFloat )
            	    	    // InternalKGraph.g:1852:3: lv_height_19_0= ruleFloat
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getHeightFloatParserRuleCall_1_1_2_1_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_33);
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
            	    // InternalKGraph.g:1889:4: ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) )
            	    {
            	    // InternalKGraph.g:1889:4: ({...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) ) )
            	    // InternalKGraph.g:1890:5: {...}? => ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 2)");
            	    }
            	    // InternalKGraph.g:1890:109: ( ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) ) )
            	    // InternalKGraph.g:1891:6: ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKShapeLayoutAccess().getUnorderedGroup_1(), 2);
            	    	 				
            	    // InternalKGraph.g:1894:6: ({...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* ) )
            	    // InternalKGraph.g:1894:7: {...}? => (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKShapeLayout", "true");
            	    }
            	    // InternalKGraph.g:1894:16: (otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )* )
            	    // InternalKGraph.g:1894:18: otherlv_20= 'properties' otherlv_21= ':' ( (lv_persistentEntries_22_0= ruleProperty ) )*
            	    {
            	    otherlv_20=(Token)match(input,39,FollowSets000.FOLLOW_26); 

            	        	newLeafNode(otherlv_20, grammarAccess.getKShapeLayoutAccess().getPropertiesKeyword_1_2_0());
            	        
            	    otherlv_21=(Token)match(input,25,FollowSets000.FOLLOW_34); 

            	        	newLeafNode(otherlv_21, grammarAccess.getKShapeLayoutAccess().getColonKeyword_1_2_1());
            	        
            	    // InternalKGraph.g:1902:1: ( (lv_persistentEntries_22_0= ruleProperty ) )*
            	    loop29:
            	    do {
            	        int alt29=2;
            	        int LA29_0 = input.LA(1);

            	        if ( (LA29_0==RULE_ID) ) {
            	            alt29=1;
            	        }


            	        switch (alt29) {
            	    	case 1 :
            	    	    // InternalKGraph.g:1903:1: (lv_persistentEntries_22_0= ruleProperty )
            	    	    {
            	    	    // InternalKGraph.g:1903:1: (lv_persistentEntries_22_0= ruleProperty )
            	    	    // InternalKGraph.g:1904:3: lv_persistentEntries_22_0= ruleProperty
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPropertyParserRuleCall_1_2_2_0()); 
            	    	    	    
            	    	    pushFollow(FollowSets000.FOLLOW_34);
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
    // InternalKGraph.g:1942:1: entryRuleEmptyKShapeLayout returns [EObject current=null] : iv_ruleEmptyKShapeLayout= ruleEmptyKShapeLayout EOF ;
    public final EObject entryRuleEmptyKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKShapeLayout = null;


        try {
            // InternalKGraph.g:1943:2: (iv_ruleEmptyKShapeLayout= ruleEmptyKShapeLayout EOF )
            // InternalKGraph.g:1944:2: iv_ruleEmptyKShapeLayout= ruleEmptyKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getEmptyKShapeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEmptyKShapeLayout=ruleEmptyKShapeLayout();

            state._fsp--;

             current =iv_ruleEmptyKShapeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1951:1: ruleEmptyKShapeLayout returns [EObject current=null] : () ;
    public final EObject ruleEmptyKShapeLayout() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:1954:28: ( () )
            // InternalKGraph.g:1955:1: ()
            {
            // InternalKGraph.g:1955:1: ()
            // InternalKGraph.g:1956:5: 
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
    // InternalKGraph.g:1969:1: entryRuleKEdgeLayout returns [EObject current=null] : iv_ruleKEdgeLayout= ruleKEdgeLayout EOF ;
    public final EObject entryRuleKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdgeLayout = null;


        try {
            // InternalKGraph.g:1970:2: (iv_ruleKEdgeLayout= ruleKEdgeLayout EOF )
            // InternalKGraph.g:1971:2: iv_ruleKEdgeLayout= ruleKEdgeLayout EOF
            {
             newCompositeNode(grammarAccess.getKEdgeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKEdgeLayout=ruleKEdgeLayout();

            state._fsp--;

             current =iv_ruleKEdgeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:1978:1: ruleKEdgeLayout returns [EObject current=null] : ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? ) ;
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
            // InternalKGraph.g:1981:28: ( ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? ) )
            // InternalKGraph.g:1982:1: ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? )
            {
            // InternalKGraph.g:1982:1: ( ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )? )
            // InternalKGraph.g:1982:2: ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) ) (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )?
            {
            // InternalKGraph.g:1982:2: ( (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) ) | ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) ) )
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
                    // InternalKGraph.g:1982:3: (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) )
                    {
                    // InternalKGraph.g:1982:3: (otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) ) )
                    // InternalKGraph.g:1982:5: otherlv_0= 'points' otherlv_1= ':' ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) )
                    {
                    otherlv_0=(Token)match(input,45,FollowSets000.FOLLOW_26); 

                        	newLeafNode(otherlv_0, grammarAccess.getKEdgeLayoutAccess().getPointsKeyword_0_0_0());
                        
                    otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_35); 

                        	newLeafNode(otherlv_1, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_0_0_1());
                        
                    // InternalKGraph.g:1990:1: ( ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) ) | ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) ) )
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
                            // InternalKGraph.g:1990:2: ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) )
                            {
                            // InternalKGraph.g:1990:2: ( ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) ) )
                            // InternalKGraph.g:1990:3: ( (lv_sourcePoint_2_0= ruleKPoint ) ) ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) )
                            {
                            // InternalKGraph.g:1990:3: ( (lv_sourcePoint_2_0= ruleKPoint ) )
                            // InternalKGraph.g:1991:1: (lv_sourcePoint_2_0= ruleKPoint )
                            {
                            // InternalKGraph.g:1991:1: (lv_sourcePoint_2_0= ruleKPoint )
                            // InternalKGraph.g:1992:3: lv_sourcePoint_2_0= ruleKPoint
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointKPointParserRuleCall_0_0_2_0_0_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_36);
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

                            // InternalKGraph.g:2008:2: ( (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) ) | ( (lv_targetPoint_7_0= ruleEmptyKPoint ) ) )
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
                                    // InternalKGraph.g:2008:3: (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) )
                                    {
                                    // InternalKGraph.g:2008:3: (otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) ) )
                                    // InternalKGraph.g:2008:5: otherlv_3= ';' ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )* ( (lv_targetPoint_6_0= ruleKPoint ) )
                                    {
                                    otherlv_3=(Token)match(input,46,FollowSets000.FOLLOW_28); 

                                        	newLeafNode(otherlv_3, grammarAccess.getKEdgeLayoutAccess().getSemicolonKeyword_0_0_2_0_1_0_0());
                                        
                                    // InternalKGraph.g:2012:1: ( ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';' )*
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
                                    	    // InternalKGraph.g:2012:2: ( (lv_bendPoints_4_0= ruleKPoint ) ) otherlv_5= ';'
                                    	    {
                                    	    // InternalKGraph.g:2012:2: ( (lv_bendPoints_4_0= ruleKPoint ) )
                                    	    // InternalKGraph.g:2013:1: (lv_bendPoints_4_0= ruleKPoint )
                                    	    {
                                    	    // InternalKGraph.g:2013:1: (lv_bendPoints_4_0= ruleKPoint )
                                    	    // InternalKGraph.g:2014:3: lv_bendPoints_4_0= ruleKPoint
                                    	    {
                                    	     
                                    	    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_0_0_2_0_1_0_1_0_0()); 
                                    	    	    
                                    	    pushFollow(FollowSets000.FOLLOW_37);
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

                                    	    otherlv_5=(Token)match(input,46,FollowSets000.FOLLOW_28); 

                                    	        	newLeafNode(otherlv_5, grammarAccess.getKEdgeLayoutAccess().getSemicolonKeyword_0_0_2_0_1_0_1_1());
                                    	        

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop31;
                                        }
                                    } while (true);

                                    // InternalKGraph.g:2034:3: ( (lv_targetPoint_6_0= ruleKPoint ) )
                                    // InternalKGraph.g:2035:1: (lv_targetPoint_6_0= ruleKPoint )
                                    {
                                    // InternalKGraph.g:2035:1: (lv_targetPoint_6_0= ruleKPoint )
                                    // InternalKGraph.g:2036:3: lv_targetPoint_6_0= ruleKPoint
                                    {
                                     
                                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointKPointParserRuleCall_0_0_2_0_1_0_2_0()); 
                                    	    
                                    pushFollow(FollowSets000.FOLLOW_38);
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
                                    // InternalKGraph.g:2053:6: ( (lv_targetPoint_7_0= ruleEmptyKPoint ) )
                                    {
                                    // InternalKGraph.g:2053:6: ( (lv_targetPoint_7_0= ruleEmptyKPoint ) )
                                    // InternalKGraph.g:2054:1: (lv_targetPoint_7_0= ruleEmptyKPoint )
                                    {
                                    // InternalKGraph.g:2054:1: (lv_targetPoint_7_0= ruleEmptyKPoint )
                                    // InternalKGraph.g:2055:3: lv_targetPoint_7_0= ruleEmptyKPoint
                                    {
                                     
                                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_0_0_2_0_1_1_0()); 
                                    	    
                                    pushFollow(FollowSets000.FOLLOW_38);
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
                            // InternalKGraph.g:2072:6: ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) )
                            {
                            // InternalKGraph.g:2072:6: ( ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) ) )
                            // InternalKGraph.g:2072:7: ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_9_0= ruleEmptyKPoint ) )
                            {
                            // InternalKGraph.g:2072:7: ( (lv_sourcePoint_8_0= ruleEmptyKPoint ) )
                            // InternalKGraph.g:2073:1: (lv_sourcePoint_8_0= ruleEmptyKPoint )
                            {
                            // InternalKGraph.g:2073:1: (lv_sourcePoint_8_0= ruleEmptyKPoint )
                            // InternalKGraph.g:2074:3: lv_sourcePoint_8_0= ruleEmptyKPoint
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointEmptyKPointParserRuleCall_0_0_2_1_0_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_39);
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

                            // InternalKGraph.g:2090:2: ( (lv_targetPoint_9_0= ruleEmptyKPoint ) )
                            // InternalKGraph.g:2091:1: (lv_targetPoint_9_0= ruleEmptyKPoint )
                            {
                            // InternalKGraph.g:2091:1: (lv_targetPoint_9_0= ruleEmptyKPoint )
                            // InternalKGraph.g:2092:3: lv_targetPoint_9_0= ruleEmptyKPoint
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_0_0_2_1_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_38);
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
                    // InternalKGraph.g:2109:6: ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) )
                    {
                    // InternalKGraph.g:2109:6: ( ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) ) )
                    // InternalKGraph.g:2109:7: ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_11_0= ruleEmptyKPoint ) )
                    {
                    // InternalKGraph.g:2109:7: ( (lv_sourcePoint_10_0= ruleEmptyKPoint ) )
                    // InternalKGraph.g:2110:1: (lv_sourcePoint_10_0= ruleEmptyKPoint )
                    {
                    // InternalKGraph.g:2110:1: (lv_sourcePoint_10_0= ruleEmptyKPoint )
                    // InternalKGraph.g:2111:3: lv_sourcePoint_10_0= ruleEmptyKPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointEmptyKPointParserRuleCall_0_1_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_39);
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

                    // InternalKGraph.g:2127:2: ( (lv_targetPoint_11_0= ruleEmptyKPoint ) )
                    // InternalKGraph.g:2128:1: (lv_targetPoint_11_0= ruleEmptyKPoint )
                    {
                    // InternalKGraph.g:2128:1: (lv_targetPoint_11_0= ruleEmptyKPoint )
                    // InternalKGraph.g:2129:3: lv_targetPoint_11_0= ruleEmptyKPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_0_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_38);
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

            // InternalKGraph.g:2145:4: (otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )* )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==39) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalKGraph.g:2145:6: otherlv_12= 'properties' otherlv_13= ':' ( (lv_persistentEntries_14_0= ruleProperty ) )*
                    {
                    otherlv_12=(Token)match(input,39,FollowSets000.FOLLOW_26); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEdgeLayoutAccess().getPropertiesKeyword_1_0());
                        
                    otherlv_13=(Token)match(input,25,FollowSets000.FOLLOW_40); 

                        	newLeafNode(otherlv_13, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_1_1());
                        
                    // InternalKGraph.g:2153:1: ( (lv_persistentEntries_14_0= ruleProperty ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==RULE_ID) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // InternalKGraph.g:2154:1: (lv_persistentEntries_14_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:2154:1: (lv_persistentEntries_14_0= ruleProperty )
                    	    // InternalKGraph.g:2155:3: lv_persistentEntries_14_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getPersistentEntriesPropertyParserRuleCall_1_2_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_40);
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
    // InternalKGraph.g:2179:1: entryRuleEmptyKEdgeLayout returns [EObject current=null] : iv_ruleEmptyKEdgeLayout= ruleEmptyKEdgeLayout EOF ;
    public final EObject entryRuleEmptyKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKEdgeLayout = null;


        try {
            // InternalKGraph.g:2180:2: (iv_ruleEmptyKEdgeLayout= ruleEmptyKEdgeLayout EOF )
            // InternalKGraph.g:2181:2: iv_ruleEmptyKEdgeLayout= ruleEmptyKEdgeLayout EOF
            {
             newCompositeNode(grammarAccess.getEmptyKEdgeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEmptyKEdgeLayout=ruleEmptyKEdgeLayout();

            state._fsp--;

             current =iv_ruleEmptyKEdgeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2188:1: ruleEmptyKEdgeLayout returns [EObject current=null] : ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) ) ;
    public final EObject ruleEmptyKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject lv_sourcePoint_0_0 = null;

        EObject lv_targetPoint_1_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:2191:28: ( ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) ) )
            // InternalKGraph.g:2192:1: ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) )
            {
            // InternalKGraph.g:2192:1: ( ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) ) )
            // InternalKGraph.g:2192:2: ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) ) ( (lv_targetPoint_1_0= ruleEmptyKPoint ) )
            {
            // InternalKGraph.g:2192:2: ( (lv_sourcePoint_0_0= ruleEmptyKPoint ) )
            // InternalKGraph.g:2193:1: (lv_sourcePoint_0_0= ruleEmptyKPoint )
            {
            // InternalKGraph.g:2193:1: (lv_sourcePoint_0_0= ruleEmptyKPoint )
            // InternalKGraph.g:2194:3: lv_sourcePoint_0_0= ruleEmptyKPoint
            {
             
            	        newCompositeNode(grammarAccess.getEmptyKEdgeLayoutAccess().getSourcePointEmptyKPointParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_1);
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

            // InternalKGraph.g:2210:2: ( (lv_targetPoint_1_0= ruleEmptyKPoint ) )
            // InternalKGraph.g:2211:1: (lv_targetPoint_1_0= ruleEmptyKPoint )
            {
            // InternalKGraph.g:2211:1: (lv_targetPoint_1_0= ruleEmptyKPoint )
            // InternalKGraph.g:2212:3: lv_targetPoint_1_0= ruleEmptyKPoint
            {
             
            	        newCompositeNode(grammarAccess.getEmptyKEdgeLayoutAccess().getTargetPointEmptyKPointParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:2236:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // InternalKGraph.g:2237:2: (iv_ruleKPoint= ruleKPoint EOF )
            // InternalKGraph.g:2238:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2245:1: ruleKPoint returns [EObject current=null] : ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_x_0_0 = null;

        AntlrDatatypeRuleToken lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:2248:28: ( ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) ) )
            // InternalKGraph.g:2249:1: ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) )
            {
            // InternalKGraph.g:2249:1: ( ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) ) )
            // InternalKGraph.g:2249:2: ( (lv_x_0_0= ruleFloat ) ) otherlv_1= ',' ( (lv_y_2_0= ruleFloat ) )
            {
            // InternalKGraph.g:2249:2: ( (lv_x_0_0= ruleFloat ) )
            // InternalKGraph.g:2250:1: (lv_x_0_0= ruleFloat )
            {
            // InternalKGraph.g:2250:1: (lv_x_0_0= ruleFloat )
            // InternalKGraph.g:2251:3: lv_x_0_0= ruleFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXFloatParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_41);
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

            otherlv_1=(Token)match(input,47,FollowSets000.FOLLOW_28); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getCommaKeyword_1());
                
            // InternalKGraph.g:2271:1: ( (lv_y_2_0= ruleFloat ) )
            // InternalKGraph.g:2272:1: (lv_y_2_0= ruleFloat )
            {
            // InternalKGraph.g:2272:1: (lv_y_2_0= ruleFloat )
            // InternalKGraph.g:2273:3: lv_y_2_0= ruleFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:2297:1: entryRuleEmptyKPoint returns [EObject current=null] : iv_ruleEmptyKPoint= ruleEmptyKPoint EOF ;
    public final EObject entryRuleEmptyKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyKPoint = null;


        try {
            // InternalKGraph.g:2298:2: (iv_ruleEmptyKPoint= ruleEmptyKPoint EOF )
            // InternalKGraph.g:2299:2: iv_ruleEmptyKPoint= ruleEmptyKPoint EOF
            {
             newCompositeNode(grammarAccess.getEmptyKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEmptyKPoint=ruleEmptyKPoint();

            state._fsp--;

             current =iv_ruleEmptyKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2306:1: ruleEmptyKPoint returns [EObject current=null] : () ;
    public final EObject ruleEmptyKPoint() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:2309:28: ( () )
            // InternalKGraph.g:2310:1: ()
            {
            // InternalKGraph.g:2310:1: ()
            // InternalKGraph.g:2311:5: 
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
    // InternalKGraph.g:2324:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // InternalKGraph.g:2325:2: (iv_ruleKRendering= ruleKRendering EOF )
            // InternalKGraph.g:2326:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2333:1: ruleKRendering returns [EObject current=null] : (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline ) ;
    public final EObject ruleKRendering() throws RecognitionException {
        EObject current = null;

        EObject this_KSimpleRendering_0 = null;

        EObject this_KContainerRendering_1 = null;

        EObject this_KPolyline_2 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:2336:28: ( (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline ) )
            // InternalKGraph.g:2337:1: (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline )
            {
            // InternalKGraph.g:2337:1: (this_KSimpleRendering_0= ruleKSimpleRendering | this_KContainerRendering_1= ruleKContainerRendering | this_KPolyline_2= ruleKPolyline )
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
                    // InternalKGraph.g:2338:5: this_KSimpleRendering_0= ruleKSimpleRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSimpleRenderingParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_KSimpleRendering_0=ruleKSimpleRendering();

                    state._fsp--;

                     
                            current = this_KSimpleRendering_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:2348:5: this_KContainerRendering_1= ruleKContainerRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKContainerRenderingParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_KContainerRendering_1=ruleKContainerRendering();

                    state._fsp--;

                     
                            current = this_KContainerRendering_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:2358:5: this_KPolyline_2= ruleKPolyline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolylineParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:2374:1: entryRuleKSimpleRendering returns [EObject current=null] : iv_ruleKSimpleRendering= ruleKSimpleRendering EOF ;
    public final EObject entryRuleKSimpleRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSimpleRendering = null;


        try {
            // InternalKGraph.g:2375:2: (iv_ruleKSimpleRendering= ruleKSimpleRendering EOF )
            // InternalKGraph.g:2376:2: iv_ruleKSimpleRendering= ruleKSimpleRendering EOF
            {
             newCompositeNode(grammarAccess.getKSimpleRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKSimpleRendering=ruleKSimpleRendering();

            state._fsp--;

             current =iv_ruleKSimpleRendering; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2383:1: ruleKSimpleRendering returns [EObject current=null] : ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? ) ;
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
            // InternalKGraph.g:2386:28: ( ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? ) )
            // InternalKGraph.g:2387:1: ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? )
            {
            // InternalKGraph.g:2387:1: ( (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? )
            // InternalKGraph.g:2387:2: (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText ) ( (lv_id_3_0= ruleQualifiedID ) )? (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )? (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )?
            {
            // InternalKGraph.g:2387:2: (this_KRenderingRef_0= ruleKRenderingRef | this_KChildArea_1= ruleKChildArea | this_KText_2= ruleKText )
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
                    // InternalKGraph.g:2388:5: this_KRenderingRef_0= ruleKRenderingRef
                    {
                     
                            newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getKRenderingRefParserRuleCall_0_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KRenderingRef_0=ruleKRenderingRef();

                    state._fsp--;

                     
                            current = this_KRenderingRef_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:2398:5: this_KChildArea_1= ruleKChildArea
                    {
                     
                            newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getKChildAreaParserRuleCall_0_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KChildArea_1=ruleKChildArea();

                    state._fsp--;

                     
                            current = this_KChildArea_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:2408:5: this_KText_2= ruleKText
                    {
                     
                            newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getKTextParserRuleCall_0_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KText_2=ruleKText();

                    state._fsp--;

                     
                            current = this_KText_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // InternalKGraph.g:2416:2: ( (lv_id_3_0= ruleQualifiedID ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==RULE_ID) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalKGraph.g:2417:1: (lv_id_3_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:2417:1: (lv_id_3_0= ruleQualifiedID )
                    // InternalKGraph.g:2418:3: lv_id_3_0= ruleQualifiedID
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getIdQualifiedIDParserRuleCall_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_43);
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

            // InternalKGraph.g:2434:3: (otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==31) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalKGraph.g:2434:5: otherlv_4= '[' ( (lv_persistentEntries_5_0= ruleProperty ) )* otherlv_6= ']'
                    {
                    otherlv_4=(Token)match(input,31,FollowSets000.FOLLOW_25); 

                        	newLeafNode(otherlv_4, grammarAccess.getKSimpleRenderingAccess().getLeftSquareBracketKeyword_2_0());
                        
                    // InternalKGraph.g:2438:1: ( (lv_persistentEntries_5_0= ruleProperty ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==RULE_ID) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // InternalKGraph.g:2439:1: (lv_persistentEntries_5_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:2439:1: (lv_persistentEntries_5_0= ruleProperty )
                    	    // InternalKGraph.g:2440:3: lv_persistentEntries_5_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_25);
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

                    otherlv_6=(Token)match(input,32,FollowSets000.FOLLOW_44); 

                        	newLeafNode(otherlv_6, grammarAccess.getKSimpleRenderingAccess().getRightSquareBracketKeyword_2_2());
                        

                    }
                    break;

            }

            // InternalKGraph.g:2460:3: (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==21) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalKGraph.g:2460:5: otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) ) otherlv_16= '}'
                    {
                    otherlv_7=(Token)match(input,21,FollowSets000.FOLLOW_45); 

                        	newLeafNode(otherlv_7, grammarAccess.getKSimpleRenderingAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // InternalKGraph.g:2464:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) ) )
                    // InternalKGraph.g:2466:1: ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) )
                    {
                    // InternalKGraph.g:2466:1: ( ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* ) )
                    // InternalKGraph.g:2467:2: ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* )
                    {
                     
                    	  getUnorderedGroupHelper().enter(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1());
                    	
                    // InternalKGraph.g:2470:2: ( ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )* )
                    // InternalKGraph.g:2471:3: ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )*
                    {
                    // InternalKGraph.g:2471:3: ( ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) ) )*
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
                    	    // InternalKGraph.g:2473:4: ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2473:4: ({...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) ) )
                    	    // InternalKGraph.g:2474:5: {...}? => ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 0)");
                    	    }
                    	    // InternalKGraph.g:2474:115: ( ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) ) )
                    	    // InternalKGraph.g:2475:6: ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 0);
                    	    	 				
                    	    // InternalKGraph.g:2478:6: ({...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* ) )
                    	    // InternalKGraph.g:2478:7: {...}? => (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2478:16: (otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )* )
                    	    // InternalKGraph.g:2478:18: otherlv_9= 'styles' otherlv_10= ':' ( (lv_styles_11_0= ruleKStyle ) )*
                    	    {
                    	    otherlv_9=(Token)match(input,48,FollowSets000.FOLLOW_26); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKSimpleRenderingAccess().getStylesKeyword_3_1_0_0());
                    	        
                    	    otherlv_10=(Token)match(input,25,FollowSets000.FOLLOW_46); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_0_1());
                    	        
                    	    // InternalKGraph.g:2486:1: ( (lv_styles_11_0= ruleKStyle ) )*
                    	    loop42:
                    	    do {
                    	        int alt42=2;
                    	        int LA42_0 = input.LA(1);

                    	        if ( ((LA42_0>=68 && LA42_0<=83)||(LA42_0>=86 && LA42_0<=87)||(LA42_0>=89 && LA42_0<=90)) ) {
                    	            alt42=1;
                    	        }


                    	        switch (alt42) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2487:1: (lv_styles_11_0= ruleKStyle )
                    	    	    {
                    	    	    // InternalKGraph.g:2487:1: (lv_styles_11_0= ruleKStyle )
                    	    	    // InternalKGraph.g:2488:3: lv_styles_11_0= ruleKStyle
                    	    	    {
                    	    	     
                    	    	    	        newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getStylesKStyleParserRuleCall_3_1_0_2_0()); 
                    	    	    	    
                    	    	    pushFollow(FollowSets000.FOLLOW_46);
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
                    	    // InternalKGraph.g:2511:4: ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2511:4: ({...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) ) )
                    	    // InternalKGraph.g:2512:5: {...}? => ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 1)");
                    	    }
                    	    // InternalKGraph.g:2512:115: ( ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) ) )
                    	    // InternalKGraph.g:2513:6: ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 1);
                    	    	 				
                    	    // InternalKGraph.g:2516:6: ({...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* ) )
                    	    // InternalKGraph.g:2516:7: {...}? => (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2516:16: (otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )* )
                    	    // InternalKGraph.g:2516:18: otherlv_12= 'actions' otherlv_13= ':' ( (lv_actions_14_0= ruleKAction ) )*
                    	    {
                    	    otherlv_12=(Token)match(input,49,FollowSets000.FOLLOW_26); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getKSimpleRenderingAccess().getActionsKeyword_3_1_1_0());
                    	        
                    	    otherlv_13=(Token)match(input,25,FollowSets000.FOLLOW_47); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getKSimpleRenderingAccess().getColonKeyword_3_1_1_1());
                    	        
                    	    // InternalKGraph.g:2524:1: ( (lv_actions_14_0= ruleKAction ) )*
                    	    loop43:
                    	    do {
                    	        int alt43=2;
                    	        int LA43_0 = input.LA(1);

                    	        if ( ((LA43_0>=141 && LA43_0<=146)) ) {
                    	            alt43=1;
                    	        }


                    	        switch (alt43) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2525:1: (lv_actions_14_0= ruleKAction )
                    	    	    {
                    	    	    // InternalKGraph.g:2525:1: (lv_actions_14_0= ruleKAction )
                    	    	    // InternalKGraph.g:2526:3: lv_actions_14_0= ruleKAction
                    	    	    {
                    	    	     
                    	    	    	        newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getActionsKActionParserRuleCall_3_1_1_2_0()); 
                    	    	    	    
                    	    	    pushFollow(FollowSets000.FOLLOW_47);
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
                    	    // InternalKGraph.g:2549:4: ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2549:4: ({...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) ) )
                    	    // InternalKGraph.g:2550:5: {...}? => ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 2)");
                    	    }
                    	    // InternalKGraph.g:2550:115: ( ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) ) )
                    	    // InternalKGraph.g:2551:6: ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKSimpleRenderingAccess().getUnorderedGroup_3_1(), 2);
                    	    	 				
                    	    // InternalKGraph.g:2554:6: ({...}? => ( (lv_placementData_15_0= ruleKPlacementData ) ) )
                    	    // InternalKGraph.g:2554:7: {...}? => ( (lv_placementData_15_0= ruleKPlacementData ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKSimpleRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2554:16: ( (lv_placementData_15_0= ruleKPlacementData ) )
                    	    // InternalKGraph.g:2555:1: (lv_placementData_15_0= ruleKPlacementData )
                    	    {
                    	    // InternalKGraph.g:2555:1: (lv_placementData_15_0= ruleKPlacementData )
                    	    // InternalKGraph.g:2556:3: lv_placementData_15_0= ruleKPlacementData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSimpleRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_2_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_45);
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

                    otherlv_16=(Token)match(input,22,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2598:1: entryRuleKContainerRendering returns [EObject current=null] : iv_ruleKContainerRendering= ruleKContainerRendering EOF ;
    public final EObject entryRuleKContainerRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKContainerRendering = null;


        try {
            // InternalKGraph.g:2599:2: (iv_ruleKContainerRendering= ruleKContainerRendering EOF )
            // InternalKGraph.g:2600:2: iv_ruleKContainerRendering= ruleKContainerRendering EOF
            {
             newCompositeNode(grammarAccess.getKContainerRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKContainerRendering=ruleKContainerRendering();

            state._fsp--;

             current =iv_ruleKContainerRendering; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2607:1: ruleKContainerRendering returns [EObject current=null] : ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? ) ;
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
            // InternalKGraph.g:2610:28: ( ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? ) )
            // InternalKGraph.g:2611:1: ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? )
            {
            // InternalKGraph.g:2611:1: ( (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )? )
            // InternalKGraph.g:2611:2: (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage ) ( (lv_id_6_0= ruleQualifiedID ) )? (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )? (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )?
            {
            // InternalKGraph.g:2611:2: (this_KRectangle_0= ruleKRectangle | this_KRoundedRectangle_1= ruleKRoundedRectangle | this_KEllipse_2= ruleKEllipse | this_KArc_3= ruleKArc | this_KCustomRendering_4= ruleKCustomRendering | this_KImage_5= ruleKImage )
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
                    // InternalKGraph.g:2612:5: this_KRectangle_0= ruleKRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKRectangleParserRuleCall_0_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KRectangle_0=ruleKRectangle();

                    state._fsp--;

                     
                            current = this_KRectangle_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:2622:5: this_KRoundedRectangle_1= ruleKRoundedRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKRoundedRectangleParserRuleCall_0_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KRoundedRectangle_1=ruleKRoundedRectangle();

                    state._fsp--;

                     
                            current = this_KRoundedRectangle_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:2632:5: this_KEllipse_2= ruleKEllipse
                    {
                     
                            newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKEllipseParserRuleCall_0_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KEllipse_2=ruleKEllipse();

                    state._fsp--;

                     
                            current = this_KEllipse_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:2642:5: this_KArc_3= ruleKArc
                    {
                     
                            newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKArcParserRuleCall_0_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KArc_3=ruleKArc();

                    state._fsp--;

                     
                            current = this_KArc_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // InternalKGraph.g:2652:5: this_KCustomRendering_4= ruleKCustomRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKCustomRenderingParserRuleCall_0_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KCustomRendering_4=ruleKCustomRendering();

                    state._fsp--;

                     
                            current = this_KCustomRendering_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // InternalKGraph.g:2662:5: this_KImage_5= ruleKImage
                    {
                     
                            newCompositeNode(grammarAccess.getKContainerRenderingAccess().getKImageParserRuleCall_0_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KImage_5=ruleKImage();

                    state._fsp--;

                     
                            current = this_KImage_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // InternalKGraph.g:2670:2: ( (lv_id_6_0= ruleQualifiedID ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==RULE_ID) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalKGraph.g:2671:1: (lv_id_6_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:2671:1: (lv_id_6_0= ruleQualifiedID )
                    // InternalKGraph.g:2672:3: lv_id_6_0= ruleQualifiedID
                    {
                     
                    	        newCompositeNode(grammarAccess.getKContainerRenderingAccess().getIdQualifiedIDParserRuleCall_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_43);
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

            // InternalKGraph.g:2688:3: (otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==31) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalKGraph.g:2688:5: otherlv_7= '[' ( (lv_persistentEntries_8_0= ruleProperty ) )* otherlv_9= ']'
                    {
                    otherlv_7=(Token)match(input,31,FollowSets000.FOLLOW_25); 

                        	newLeafNode(otherlv_7, grammarAccess.getKContainerRenderingAccess().getLeftSquareBracketKeyword_2_0());
                        
                    // InternalKGraph.g:2692:1: ( (lv_persistentEntries_8_0= ruleProperty ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==RULE_ID) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // InternalKGraph.g:2693:1: (lv_persistentEntries_8_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:2693:1: (lv_persistentEntries_8_0= ruleProperty )
                    	    // InternalKGraph.g:2694:3: lv_persistentEntries_8_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKContainerRenderingAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_25);
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

                    otherlv_9=(Token)match(input,32,FollowSets000.FOLLOW_44); 

                        	newLeafNode(otherlv_9, grammarAccess.getKContainerRenderingAccess().getRightSquareBracketKeyword_2_2());
                        

                    }
                    break;

            }

            // InternalKGraph.g:2714:3: (otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}' )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==21) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalKGraph.g:2714:5: otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) ) ( (lv_children_20_0= ruleKRendering ) )* otherlv_21= '}'
                    {
                    otherlv_10=(Token)match(input,21,FollowSets000.FOLLOW_48); 

                        	newLeafNode(otherlv_10, grammarAccess.getKContainerRenderingAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // InternalKGraph.g:2718:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) ) )
                    // InternalKGraph.g:2720:1: ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) )
                    {
                    // InternalKGraph.g:2720:1: ( ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* ) )
                    // InternalKGraph.g:2721:2: ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* )
                    {
                     
                    	  getUnorderedGroupHelper().enter(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1());
                    	
                    // InternalKGraph.g:2724:2: ( ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )* )
                    // InternalKGraph.g:2725:3: ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )*
                    {
                    // InternalKGraph.g:2725:3: ( ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) ) )*
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
                    	    // InternalKGraph.g:2727:4: ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2727:4: ({...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) ) )
                    	    // InternalKGraph.g:2728:5: {...}? => ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 0)");
                    	    }
                    	    // InternalKGraph.g:2728:118: ( ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) ) )
                    	    // InternalKGraph.g:2729:6: ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 0);
                    	    	 				
                    	    // InternalKGraph.g:2732:6: ({...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* ) )
                    	    // InternalKGraph.g:2732:7: {...}? => (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2732:16: (otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )* )
                    	    // InternalKGraph.g:2732:18: otherlv_12= 'styles' otherlv_13= ':' ( (lv_styles_14_0= ruleKStyle ) )*
                    	    {
                    	    otherlv_12=(Token)match(input,48,FollowSets000.FOLLOW_26); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getKContainerRenderingAccess().getStylesKeyword_3_1_0_0());
                    	        
                    	    otherlv_13=(Token)match(input,25,FollowSets000.FOLLOW_49); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_0_1());
                    	        
                    	    // InternalKGraph.g:2740:1: ( (lv_styles_14_0= ruleKStyle ) )*
                    	    loop50:
                    	    do {
                    	        int alt50=2;
                    	        int LA50_0 = input.LA(1);

                    	        if ( ((LA50_0>=68 && LA50_0<=83)||(LA50_0>=86 && LA50_0<=87)||(LA50_0>=89 && LA50_0<=90)) ) {
                    	            alt50=1;
                    	        }


                    	        switch (alt50) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2741:1: (lv_styles_14_0= ruleKStyle )
                    	    	    {
                    	    	    // InternalKGraph.g:2741:1: (lv_styles_14_0= ruleKStyle )
                    	    	    // InternalKGraph.g:2742:3: lv_styles_14_0= ruleKStyle
                    	    	    {
                    	    	     
                    	    	    	        newCompositeNode(grammarAccess.getKContainerRenderingAccess().getStylesKStyleParserRuleCall_3_1_0_2_0()); 
                    	    	    	    
                    	    	    pushFollow(FollowSets000.FOLLOW_49);
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
                    	    // InternalKGraph.g:2765:4: ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2765:4: ({...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) ) )
                    	    // InternalKGraph.g:2766:5: {...}? => ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 1)");
                    	    }
                    	    // InternalKGraph.g:2766:118: ( ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) ) )
                    	    // InternalKGraph.g:2767:6: ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 1);
                    	    	 				
                    	    // InternalKGraph.g:2770:6: ({...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* ) )
                    	    // InternalKGraph.g:2770:7: {...}? => (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2770:16: (otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )* )
                    	    // InternalKGraph.g:2770:18: otherlv_15= 'actions' otherlv_16= ':' ( (lv_actions_17_0= ruleKAction ) )*
                    	    {
                    	    otherlv_15=(Token)match(input,49,FollowSets000.FOLLOW_26); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getKContainerRenderingAccess().getActionsKeyword_3_1_1_0());
                    	        
                    	    otherlv_16=(Token)match(input,25,FollowSets000.FOLLOW_50); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKContainerRenderingAccess().getColonKeyword_3_1_1_1());
                    	        
                    	    // InternalKGraph.g:2778:1: ( (lv_actions_17_0= ruleKAction ) )*
                    	    loop51:
                    	    do {
                    	        int alt51=2;
                    	        int LA51_0 = input.LA(1);

                    	        if ( ((LA51_0>=141 && LA51_0<=146)) ) {
                    	            alt51=1;
                    	        }


                    	        switch (alt51) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:2779:1: (lv_actions_17_0= ruleKAction )
                    	    	    {
                    	    	    // InternalKGraph.g:2779:1: (lv_actions_17_0= ruleKAction )
                    	    	    // InternalKGraph.g:2780:3: lv_actions_17_0= ruleKAction
                    	    	    {
                    	    	     
                    	    	    	        newCompositeNode(grammarAccess.getKContainerRenderingAccess().getActionsKActionParserRuleCall_3_1_1_2_0()); 
                    	    	    	    
                    	    	    pushFollow(FollowSets000.FOLLOW_50);
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
                    	    // InternalKGraph.g:2803:4: ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2803:4: ({...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) ) )
                    	    // InternalKGraph.g:2804:5: {...}? => ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 2)");
                    	    }
                    	    // InternalKGraph.g:2804:118: ( ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) ) )
                    	    // InternalKGraph.g:2805:6: ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 2);
                    	    	 				
                    	    // InternalKGraph.g:2808:6: ({...}? => ( (lv_placementData_18_0= ruleKPlacementData ) ) )
                    	    // InternalKGraph.g:2808:7: {...}? => ( (lv_placementData_18_0= ruleKPlacementData ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2808:16: ( (lv_placementData_18_0= ruleKPlacementData ) )
                    	    // InternalKGraph.g:2809:1: (lv_placementData_18_0= ruleKPlacementData )
                    	    {
                    	    // InternalKGraph.g:2809:1: (lv_placementData_18_0= ruleKPlacementData )
                    	    // InternalKGraph.g:2810:3: lv_placementData_18_0= ruleKPlacementData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKContainerRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_2_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_48);
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
                    	    // InternalKGraph.g:2833:4: ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:2833:4: ({...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) ) )
                    	    // InternalKGraph.g:2834:5: {...}? => ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 3) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "getUnorderedGroupHelper().canSelect(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 3)");
                    	    }
                    	    // InternalKGraph.g:2834:118: ( ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) ) )
                    	    // InternalKGraph.g:2835:6: ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKContainerRenderingAccess().getUnorderedGroup_3_1(), 3);
                    	    	 				
                    	    // InternalKGraph.g:2838:6: ({...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) ) )
                    	    // InternalKGraph.g:2838:7: {...}? => ( (lv_childPlacement_19_0= ruleKPlacement ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKContainerRendering", "true");
                    	    }
                    	    // InternalKGraph.g:2838:16: ( (lv_childPlacement_19_0= ruleKPlacement ) )
                    	    // InternalKGraph.g:2839:1: (lv_childPlacement_19_0= ruleKPlacement )
                    	    {
                    	    // InternalKGraph.g:2839:1: (lv_childPlacement_19_0= ruleKPlacement )
                    	    // InternalKGraph.g:2840:3: lv_childPlacement_19_0= ruleKPlacement
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKContainerRenderingAccess().getChildPlacementKPlacementParserRuleCall_3_1_3_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_48);
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

                    // InternalKGraph.g:2870:2: ( (lv_children_20_0= ruleKRendering ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==50||(LA53_0>=52 && LA53_0<=59)||(LA53_0>=62 && LA53_0<=65)) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // InternalKGraph.g:2871:1: (lv_children_20_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:2871:1: (lv_children_20_0= ruleKRendering )
                    	    // InternalKGraph.g:2872:3: lv_children_20_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKContainerRenderingAccess().getChildrenKRenderingParserRuleCall_3_2_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_20);
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

                    otherlv_21=(Token)match(input,22,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2900:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // InternalKGraph.g:2901:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // InternalKGraph.g:2902:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2909:1: ruleKRenderingRef returns [EObject current=null] : (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) ) ;
    public final EObject ruleKRenderingRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:2912:28: ( (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) ) )
            // InternalKGraph.g:2913:1: (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) )
            {
            // InternalKGraph.g:2913:1: (otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) ) )
            // InternalKGraph.g:2913:3: otherlv_0= 'krendering' otherlv_1= '*' ( ( ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,50,FollowSets000.FOLLOW_51); 

                	newLeafNode(otherlv_0, grammarAccess.getKRenderingRefAccess().getKrenderingKeyword_0());
                
            otherlv_1=(Token)match(input,51,FollowSets000.FOLLOW_10); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getAsteriskKeyword_1());
                
            // InternalKGraph.g:2921:1: ( ( ruleQualifiedID ) )
            // InternalKGraph.g:2922:1: ( ruleQualifiedID )
            {
            // InternalKGraph.g:2922:1: ( ruleQualifiedID )
            // InternalKGraph.g:2923:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:2944:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // InternalKGraph.g:2945:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // InternalKGraph.g:2946:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2953:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'kchildArea' ) ;
    public final EObject ruleKChildArea() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:2956:28: ( ( () otherlv_1= 'kchildArea' ) )
            // InternalKGraph.g:2957:1: ( () otherlv_1= 'kchildArea' )
            {
            // InternalKGraph.g:2957:1: ( () otherlv_1= 'kchildArea' )
            // InternalKGraph.g:2957:2: () otherlv_1= 'kchildArea'
            {
            // InternalKGraph.g:2957:2: ()
            // InternalKGraph.g:2958:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,52,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2975:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // InternalKGraph.g:2976:2: (iv_ruleKText= ruleKText EOF )
            // InternalKGraph.g:2977:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:2984:1: ruleKText returns [EObject current=null] : ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? ) ;
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
            // InternalKGraph.g:2987:28: ( ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? ) )
            // InternalKGraph.g:2988:1: ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? )
            {
            // InternalKGraph.g:2988:1: ( () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )? )
            // InternalKGraph.g:2988:2: () otherlv_1= 'ktext' (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )?
            {
            // InternalKGraph.g:2988:2: ()
            // InternalKGraph.g:2989:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTextAccess().getKTextAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,53,FollowSets000.FOLLOW_52); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getKtextKeyword_1());
                
            // InternalKGraph.g:2998:1: (otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')' )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==24) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalKGraph.g:2998:3: otherlv_2= '(' ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )? otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_53); 

                        	newLeafNode(otherlv_2, grammarAccess.getKTextAccess().getLeftParenthesisKeyword_2_0());
                        
                    // InternalKGraph.g:3002:1: ( (lv_text_3_0= RULE_STRING ) )
                    // InternalKGraph.g:3003:1: (lv_text_3_0= RULE_STRING )
                    {
                    // InternalKGraph.g:3003:1: (lv_text_3_0= RULE_STRING )
                    // InternalKGraph.g:3004:3: lv_text_3_0= RULE_STRING
                    {
                    lv_text_3_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_54); 

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

                    // InternalKGraph.g:3020:2: (otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) ) )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==47) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // InternalKGraph.g:3020:4: otherlv_4= ',' ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) )
                            {
                            otherlv_4=(Token)match(input,47,FollowSets000.FOLLOW_55); 

                                	newLeafNode(otherlv_4, grammarAccess.getKTextAccess().getCommaKeyword_2_2_0());
                                
                            // InternalKGraph.g:3024:1: ( (lv_cursorSelectable_5_0= RULE_BOOLEAN ) )
                            // InternalKGraph.g:3025:1: (lv_cursorSelectable_5_0= RULE_BOOLEAN )
                            {
                            // InternalKGraph.g:3025:1: (lv_cursorSelectable_5_0= RULE_BOOLEAN )
                            // InternalKGraph.g:3026:3: lv_cursorSelectable_5_0= RULE_BOOLEAN
                            {
                            lv_cursorSelectable_5_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_13); 

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

                    otherlv_6=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3054:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // InternalKGraph.g:3055:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // InternalKGraph.g:3056:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3063:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'krectangle' ) ;
    public final EObject ruleKRectangle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:3066:28: ( ( () otherlv_1= 'krectangle' ) )
            // InternalKGraph.g:3067:1: ( () otherlv_1= 'krectangle' )
            {
            // InternalKGraph.g:3067:1: ( () otherlv_1= 'krectangle' )
            // InternalKGraph.g:3067:2: () otherlv_1= 'krectangle'
            {
            // InternalKGraph.g:3067:2: ()
            // InternalKGraph.g:3068:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,54,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3085:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // InternalKGraph.g:3086:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // InternalKGraph.g:3087:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3094:1: ruleKRoundedRectangle returns [EObject current=null] : ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? ) ;
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
            // InternalKGraph.g:3097:28: ( ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? ) )
            // InternalKGraph.g:3098:1: ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? )
            {
            // InternalKGraph.g:3098:1: ( () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )? )
            // InternalKGraph.g:3098:2: () otherlv_1= 'kroundedRectangle' (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )?
            {
            // InternalKGraph.g:3098:2: ()
            // InternalKGraph.g:3099:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,55,FollowSets000.FOLLOW_52); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getKroundedRectangleKeyword_1());
                
            // InternalKGraph.g:3108:1: (otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==24) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalKGraph.g:3108:3: otherlv_2= '(' ( (lv_cornerWidth_3_0= ruleFloat ) ) otherlv_4= ',' ( (lv_cornerHeight_5_0= ruleFloat ) ) otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_28); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRoundedRectangleAccess().getLeftParenthesisKeyword_2_0());
                        
                    // InternalKGraph.g:3112:1: ( (lv_cornerWidth_3_0= ruleFloat ) )
                    // InternalKGraph.g:3113:1: (lv_cornerWidth_3_0= ruleFloat )
                    {
                    // InternalKGraph.g:3113:1: (lv_cornerWidth_3_0= ruleFloat )
                    // InternalKGraph.g:3114:3: lv_cornerWidth_3_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthFloatParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_41);
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

                    otherlv_4=(Token)match(input,47,FollowSets000.FOLLOW_28); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_2_2());
                        
                    // InternalKGraph.g:3134:1: ( (lv_cornerHeight_5_0= ruleFloat ) )
                    // InternalKGraph.g:3135:1: (lv_cornerHeight_5_0= ruleFloat )
                    {
                    // InternalKGraph.g:3135:1: (lv_cornerHeight_5_0= ruleFloat )
                    // InternalKGraph.g:3136:3: lv_cornerHeight_5_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightFloatParserRuleCall_2_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_13);
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

                    otherlv_6=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3164:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // InternalKGraph.g:3165:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // InternalKGraph.g:3166:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3173:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'kellipse' ) ;
    public final EObject ruleKEllipse() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:3176:28: ( ( () otherlv_1= 'kellipse' ) )
            // InternalKGraph.g:3177:1: ( () otherlv_1= 'kellipse' )
            {
            // InternalKGraph.g:3177:1: ( () otherlv_1= 'kellipse' )
            // InternalKGraph.g:3177:2: () otherlv_1= 'kellipse'
            {
            // InternalKGraph.g:3177:2: ()
            // InternalKGraph.g:3178:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,56,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3195:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // InternalKGraph.g:3196:2: (iv_ruleKArc= ruleKArc EOF )
            // InternalKGraph.g:3197:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3204:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) ) ;
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
            // InternalKGraph.g:3207:28: ( ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) ) )
            // InternalKGraph.g:3208:1: ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) )
            {
            // InternalKGraph.g:3208:1: ( () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' ) )
            // InternalKGraph.g:3208:2: () otherlv_1= 'karc' (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' )
            {
            // InternalKGraph.g:3208:2: ()
            // InternalKGraph.g:3209:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,57,FollowSets000.FOLLOW_8); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getKarcKeyword_1());
                
            // InternalKGraph.g:3218:1: (otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')' )
            // InternalKGraph.g:3218:3: otherlv_2= '(' ( (lv_startAngle_3_0= RULE_DEGREES ) ) otherlv_4= ',' ( (lv_arcAngle_5_0= RULE_DEGREES ) ) (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )? otherlv_8= ')'
            {
            otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_56); 

                	newLeafNode(otherlv_2, grammarAccess.getKArcAccess().getLeftParenthesisKeyword_2_0());
                
            // InternalKGraph.g:3222:1: ( (lv_startAngle_3_0= RULE_DEGREES ) )
            // InternalKGraph.g:3223:1: (lv_startAngle_3_0= RULE_DEGREES )
            {
            // InternalKGraph.g:3223:1: (lv_startAngle_3_0= RULE_DEGREES )
            // InternalKGraph.g:3224:3: lv_startAngle_3_0= RULE_DEGREES
            {
            lv_startAngle_3_0=(Token)match(input,RULE_DEGREES,FollowSets000.FOLLOW_41); 

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

            otherlv_4=(Token)match(input,47,FollowSets000.FOLLOW_56); 

                	newLeafNode(otherlv_4, grammarAccess.getKArcAccess().getCommaKeyword_2_2());
                
            // InternalKGraph.g:3244:1: ( (lv_arcAngle_5_0= RULE_DEGREES ) )
            // InternalKGraph.g:3245:1: (lv_arcAngle_5_0= RULE_DEGREES )
            {
            // InternalKGraph.g:3245:1: (lv_arcAngle_5_0= RULE_DEGREES )
            // InternalKGraph.g:3246:3: lv_arcAngle_5_0= RULE_DEGREES
            {
            lv_arcAngle_5_0=(Token)match(input,RULE_DEGREES,FollowSets000.FOLLOW_54); 

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

            // InternalKGraph.g:3262:2: (otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==47) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalKGraph.g:3262:4: otherlv_6= ',' ( (lv_arcType_7_0= ruleArc ) )
                    {
                    otherlv_6=(Token)match(input,47,FollowSets000.FOLLOW_57); 

                        	newLeafNode(otherlv_6, grammarAccess.getKArcAccess().getCommaKeyword_2_4_0());
                        
                    // InternalKGraph.g:3266:1: ( (lv_arcType_7_0= ruleArc ) )
                    // InternalKGraph.g:3267:1: (lv_arcType_7_0= ruleArc )
                    {
                    // InternalKGraph.g:3267:1: (lv_arcType_7_0= ruleArc )
                    // InternalKGraph.g:3268:3: lv_arcType_7_0= ruleArc
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getArcTypeArcEnumRuleCall_2_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_13);
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

            otherlv_8=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3296:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // InternalKGraph.g:3297:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // InternalKGraph.g:3298:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3305:1: ruleKCustomRendering returns [EObject current=null] : ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? ) ;
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
            // InternalKGraph.g:3308:28: ( ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? ) )
            // InternalKGraph.g:3309:1: ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? )
            {
            // InternalKGraph.g:3309:1: ( () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )? )
            // InternalKGraph.g:3309:2: () otherlv_1= 'kcustomRendering' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )?
            {
            // InternalKGraph.g:3309:2: ()
            // InternalKGraph.g:3310:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKCustomRenderingAccess().getKCustomRenderingAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,58,FollowSets000.FOLLOW_52); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getKcustomRenderingKeyword_1());
                
            // InternalKGraph.g:3319:1: (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==24) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalKGraph.g:3319:3: otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_className_5_0= ruleQualifiedID ) ) otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_10); 

                        	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getLeftParenthesisKeyword_2_0());
                        
                    // InternalKGraph.g:3323:1: ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )?
                    int alt59=2;
                    alt59 = dfa59.predict(input);
                    switch (alt59) {
                        case 1 :
                            // InternalKGraph.g:3323:2: ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':'
                            {
                            // InternalKGraph.g:3323:2: ( (lv_bundleName_3_0= ruleQualifiedID ) )
                            // InternalKGraph.g:3324:1: (lv_bundleName_3_0= ruleQualifiedID )
                            {
                            // InternalKGraph.g:3324:1: (lv_bundleName_3_0= ruleQualifiedID )
                            // InternalKGraph.g:3325:3: lv_bundleName_3_0= ruleQualifiedID
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameQualifiedIDParserRuleCall_2_1_0_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_26);
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

                            otherlv_4=(Token)match(input,25,FollowSets000.FOLLOW_10); 

                                	newLeafNode(otherlv_4, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_1_1());
                                

                            }
                            break;

                    }

                    // InternalKGraph.g:3345:3: ( (lv_className_5_0= ruleQualifiedID ) )
                    // InternalKGraph.g:3346:1: (lv_className_5_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:3346:1: (lv_className_5_0= ruleQualifiedID )
                    // InternalKGraph.g:3347:3: lv_className_5_0= ruleQualifiedID
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameQualifiedIDParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_13);
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

                    otherlv_6=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3375:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // InternalKGraph.g:3376:2: (iv_ruleKImage= ruleKImage EOF )
            // InternalKGraph.g:3377:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3384:1: ruleKImage returns [EObject current=null] : ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? ) ;
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
            // InternalKGraph.g:3387:28: ( ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? ) )
            // InternalKGraph.g:3388:1: ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? )
            {
            // InternalKGraph.g:3388:1: ( () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )? )
            // InternalKGraph.g:3388:2: () otherlv_1= 'kimage' (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )?
            {
            // InternalKGraph.g:3388:2: ()
            // InternalKGraph.g:3389:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKImageAccess().getKImageAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_52); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getKimageKeyword_1());
                
            // InternalKGraph.g:3398:1: (otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')' )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==24) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalKGraph.g:3398:3: otherlv_2= '(' ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )? ( (lv_imagePath_5_0= RULE_STRING ) ) (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )? otherlv_8= ')'
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_58); 

                        	newLeafNode(otherlv_2, grammarAccess.getKImageAccess().getLeftParenthesisKeyword_2_0());
                        
                    // InternalKGraph.g:3402:1: ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==RULE_ID) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // InternalKGraph.g:3402:2: ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':'
                            {
                            // InternalKGraph.g:3402:2: ( (lv_bundleName_3_0= ruleQualifiedID ) )
                            // InternalKGraph.g:3403:1: (lv_bundleName_3_0= ruleQualifiedID )
                            {
                            // InternalKGraph.g:3403:1: (lv_bundleName_3_0= ruleQualifiedID )
                            // InternalKGraph.g:3404:3: lv_bundleName_3_0= ruleQualifiedID
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameQualifiedIDParserRuleCall_2_1_0_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_26);
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

                            otherlv_4=(Token)match(input,25,FollowSets000.FOLLOW_53); 

                                	newLeafNode(otherlv_4, grammarAccess.getKImageAccess().getColonKeyword_2_1_1());
                                

                            }
                            break;

                    }

                    // InternalKGraph.g:3424:3: ( (lv_imagePath_5_0= RULE_STRING ) )
                    // InternalKGraph.g:3425:1: (lv_imagePath_5_0= RULE_STRING )
                    {
                    // InternalKGraph.g:3425:1: (lv_imagePath_5_0= RULE_STRING )
                    // InternalKGraph.g:3426:3: lv_imagePath_5_0= RULE_STRING
                    {
                    lv_imagePath_5_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_59); 

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

                    // InternalKGraph.g:3442:2: (otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) ) )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==60) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // InternalKGraph.g:3442:4: otherlv_6= 'clipShape' ( (lv_clipShape_7_0= ruleKRendering ) )
                            {
                            otherlv_6=(Token)match(input,60,FollowSets000.FOLLOW_60); 

                                	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getClipShapeKeyword_2_3_0());
                                
                            // InternalKGraph.g:3446:1: ( (lv_clipShape_7_0= ruleKRendering ) )
                            // InternalKGraph.g:3447:1: (lv_clipShape_7_0= ruleKRendering )
                            {
                            // InternalKGraph.g:3447:1: (lv_clipShape_7_0= ruleKRendering )
                            // InternalKGraph.g:3448:3: lv_clipShape_7_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getClipShapeKRenderingParserRuleCall_2_3_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_13);
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

                    otherlv_8=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3476:1: entryRuleKPolyline returns [EObject current=null] : iv_ruleKPolyline= ruleKPolyline EOF ;
    public final EObject entryRuleKPolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline = null;


        try {
            // InternalKGraph.g:3477:2: (iv_ruleKPolyline= ruleKPolyline EOF )
            // InternalKGraph.g:3478:2: iv_ruleKPolyline= ruleKPolyline EOF
            {
             newCompositeNode(grammarAccess.getKPolylineRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPolyline=ruleKPolyline();

            state._fsp--;

             current =iv_ruleKPolyline; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3485:1: ruleKPolyline returns [EObject current=null] : ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? ) ;
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
            // InternalKGraph.g:3488:28: ( ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? ) )
            // InternalKGraph.g:3489:1: ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? )
            {
            // InternalKGraph.g:3489:1: ( (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )? )
            // InternalKGraph.g:3489:2: (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline ) ( (lv_id_4_0= ruleQualifiedID ) )? (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )? (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )?
            {
            // InternalKGraph.g:3489:2: (this_KSimplePolyline_0= ruleKSimplePolyline | this_KPolygon_1= ruleKPolygon | this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline | this_KSpline_3= ruleKSpline )
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
                    // InternalKGraph.g:3490:5: this_KSimplePolyline_0= ruleKSimplePolyline
                    {
                     
                            newCompositeNode(grammarAccess.getKPolylineAccess().getKSimplePolylineParserRuleCall_0_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KSimplePolyline_0=ruleKSimplePolyline();

                    state._fsp--;

                     
                            current = this_KSimplePolyline_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:3500:5: this_KPolygon_1= ruleKPolygon
                    {
                     
                            newCompositeNode(grammarAccess.getKPolylineAccess().getKPolygonParserRuleCall_0_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KPolygon_1=ruleKPolygon();

                    state._fsp--;

                     
                            current = this_KPolygon_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:3510:5: this_KRoundedBendsPolyline_2= ruleKRoundedBendsPolyline
                    {
                     
                            newCompositeNode(grammarAccess.getKPolylineAccess().getKRoundedBendsPolylineParserRuleCall_0_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KRoundedBendsPolyline_2=ruleKRoundedBendsPolyline();

                    state._fsp--;

                     
                            current = this_KRoundedBendsPolyline_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:3520:5: this_KSpline_3= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKPolylineAccess().getKSplineParserRuleCall_0_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_42);
                    this_KSpline_3=ruleKSpline();

                    state._fsp--;

                     
                            current = this_KSpline_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // InternalKGraph.g:3528:2: ( (lv_id_4_0= ruleQualifiedID ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==RULE_ID) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalKGraph.g:3529:1: (lv_id_4_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:3529:1: (lv_id_4_0= ruleQualifiedID )
                    // InternalKGraph.g:3530:3: lv_id_4_0= ruleQualifiedID
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolylineAccess().getIdQualifiedIDParserRuleCall_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_43);
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

            // InternalKGraph.g:3546:3: (otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==31) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalKGraph.g:3546:5: otherlv_5= '[' ( (lv_persistentEntries_6_0= ruleProperty ) )* otherlv_7= ']'
                    {
                    otherlv_5=(Token)match(input,31,FollowSets000.FOLLOW_25); 

                        	newLeafNode(otherlv_5, grammarAccess.getKPolylineAccess().getLeftSquareBracketKeyword_2_0());
                        
                    // InternalKGraph.g:3550:1: ( (lv_persistentEntries_6_0= ruleProperty ) )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==RULE_ID) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // InternalKGraph.g:3551:1: (lv_persistentEntries_6_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:3551:1: (lv_persistentEntries_6_0= ruleProperty )
                    	    // InternalKGraph.g:3552:3: lv_persistentEntries_6_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolylineAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_25);
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

                    otherlv_7=(Token)match(input,32,FollowSets000.FOLLOW_44); 

                        	newLeafNode(otherlv_7, grammarAccess.getKPolylineAccess().getRightSquareBracketKeyword_2_2());
                        

                    }
                    break;

            }

            // InternalKGraph.g:3572:3: (otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}' )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==21) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalKGraph.g:3572:5: otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) ) (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )? ( (lv_children_25_0= ruleKRendering ) )* otherlv_26= '}'
                    {
                    otherlv_8=(Token)match(input,21,FollowSets000.FOLLOW_61); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolylineAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // InternalKGraph.g:3576:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) ) )
                    // InternalKGraph.g:3578:1: ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) )
                    {
                    // InternalKGraph.g:3578:1: ( ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* ) )
                    // InternalKGraph.g:3579:2: ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* )
                    {
                     
                    	  getUnorderedGroupHelper().enter(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1());
                    	
                    // InternalKGraph.g:3582:2: ( ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )* )
                    // InternalKGraph.g:3583:3: ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )*
                    {
                    // InternalKGraph.g:3583:3: ( ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) ) | ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) ) | ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) ) )*
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
                    	    // InternalKGraph.g:3585:4: ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3585:4: ({...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) ) )
                    	    // InternalKGraph.g:3586:5: {...}? => ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 0)");
                    	    }
                    	    // InternalKGraph.g:3586:108: ( ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) ) )
                    	    // InternalKGraph.g:3587:6: ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 0);
                    	    	 				
                    	    // InternalKGraph.g:3590:6: ({...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? ) )
                    	    // InternalKGraph.g:3590:7: {...}? => (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3590:16: (otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )? )
                    	    // InternalKGraph.g:3590:18: otherlv_10= 'points' otherlv_11= ':' ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )?
                    	    {
                    	    otherlv_10=(Token)match(input,45,FollowSets000.FOLLOW_26); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getKPolylineAccess().getPointsKeyword_3_1_0_0());
                    	        
                    	    otherlv_11=(Token)match(input,25,FollowSets000.FOLLOW_62); 

                    	        	newLeafNode(otherlv_11, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_0_1());
                    	        
                    	    // InternalKGraph.g:3598:1: ( ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )* )?
                    	    int alt69=2;
                    	    int LA69_0 = input.LA(1);

                    	    if ( ((LA69_0>=43 && LA69_0<=44)) ) {
                    	        alt69=1;
                    	    }
                    	    switch (alt69) {
                    	        case 1 :
                    	            // InternalKGraph.g:3598:2: ( (lv_points_12_0= ruleKPosition ) ) (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )*
                    	            {
                    	            // InternalKGraph.g:3598:2: ( (lv_points_12_0= ruleKPosition ) )
                    	            // InternalKGraph.g:3599:1: (lv_points_12_0= ruleKPosition )
                    	            {
                    	            // InternalKGraph.g:3599:1: (lv_points_12_0= ruleKPosition )
                    	            // InternalKGraph.g:3600:3: lv_points_12_0= ruleKPosition
                    	            {
                    	             
                    	            	        newCompositeNode(grammarAccess.getKPolylineAccess().getPointsKPositionParserRuleCall_3_1_0_2_0_0()); 
                    	            	    
                    	            pushFollow(FollowSets000.FOLLOW_63);
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

                    	            // InternalKGraph.g:3616:2: (otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) ) )*
                    	            loop68:
                    	            do {
                    	                int alt68=2;
                    	                int LA68_0 = input.LA(1);

                    	                if ( (LA68_0==46) ) {
                    	                    alt68=1;
                    	                }


                    	                switch (alt68) {
                    	            	case 1 :
                    	            	    // InternalKGraph.g:3616:4: otherlv_13= ';' ( (lv_points_14_0= ruleKPosition ) )
                    	            	    {
                    	            	    otherlv_13=(Token)match(input,46,FollowSets000.FOLLOW_64); 

                    	            	        	newLeafNode(otherlv_13, grammarAccess.getKPolylineAccess().getSemicolonKeyword_3_1_0_2_1_0());
                    	            	        
                    	            	    // InternalKGraph.g:3620:1: ( (lv_points_14_0= ruleKPosition ) )
                    	            	    // InternalKGraph.g:3621:1: (lv_points_14_0= ruleKPosition )
                    	            	    {
                    	            	    // InternalKGraph.g:3621:1: (lv_points_14_0= ruleKPosition )
                    	            	    // InternalKGraph.g:3622:3: lv_points_14_0= ruleKPosition
                    	            	    {
                    	            	     
                    	            	    	        newCompositeNode(grammarAccess.getKPolylineAccess().getPointsKPositionParserRuleCall_3_1_0_2_1_1_0()); 
                    	            	    	    
                    	            	    pushFollow(FollowSets000.FOLLOW_63);
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
                    	    // InternalKGraph.g:3645:4: ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3645:4: ({...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) ) )
                    	    // InternalKGraph.g:3646:5: {...}? => ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 1)");
                    	    }
                    	    // InternalKGraph.g:3646:108: ( ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) ) )
                    	    // InternalKGraph.g:3647:6: ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 1);
                    	    	 				
                    	    // InternalKGraph.g:3650:6: ({...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* ) )
                    	    // InternalKGraph.g:3650:7: {...}? => (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3650:16: (otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )* )
                    	    // InternalKGraph.g:3650:18: otherlv_15= 'styles' otherlv_16= ':' ( (lv_styles_17_0= ruleKStyle ) )*
                    	    {
                    	    otherlv_15=(Token)match(input,48,FollowSets000.FOLLOW_26); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getKPolylineAccess().getStylesKeyword_3_1_1_0());
                    	        
                    	    otherlv_16=(Token)match(input,25,FollowSets000.FOLLOW_65); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_1_1());
                    	        
                    	    // InternalKGraph.g:3658:1: ( (lv_styles_17_0= ruleKStyle ) )*
                    	    loop70:
                    	    do {
                    	        int alt70=2;
                    	        int LA70_0 = input.LA(1);

                    	        if ( ((LA70_0>=68 && LA70_0<=83)||(LA70_0>=86 && LA70_0<=87)||(LA70_0>=89 && LA70_0<=90)) ) {
                    	            alt70=1;
                    	        }


                    	        switch (alt70) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:3659:1: (lv_styles_17_0= ruleKStyle )
                    	    	    {
                    	    	    // InternalKGraph.g:3659:1: (lv_styles_17_0= ruleKStyle )
                    	    	    // InternalKGraph.g:3660:3: lv_styles_17_0= ruleKStyle
                    	    	    {
                    	    	     
                    	    	    	        newCompositeNode(grammarAccess.getKPolylineAccess().getStylesKStyleParserRuleCall_3_1_1_2_0()); 
                    	    	    	    
                    	    	    pushFollow(FollowSets000.FOLLOW_65);
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
                    	    // InternalKGraph.g:3683:4: ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3683:4: ({...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) ) )
                    	    // InternalKGraph.g:3684:5: {...}? => ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 2)");
                    	    }
                    	    // InternalKGraph.g:3684:108: ( ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) ) )
                    	    // InternalKGraph.g:3685:6: ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 2);
                    	    	 				
                    	    // InternalKGraph.g:3688:6: ({...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* ) )
                    	    // InternalKGraph.g:3688:7: {...}? => (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3688:16: (otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )* )
                    	    // InternalKGraph.g:3688:18: otherlv_18= 'actions' otherlv_19= ':' ( (lv_actions_20_0= ruleKAction ) )*
                    	    {
                    	    otherlv_18=(Token)match(input,49,FollowSets000.FOLLOW_26); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getKPolylineAccess().getActionsKeyword_3_1_2_0());
                    	        
                    	    otherlv_19=(Token)match(input,25,FollowSets000.FOLLOW_66); 

                    	        	newLeafNode(otherlv_19, grammarAccess.getKPolylineAccess().getColonKeyword_3_1_2_1());
                    	        
                    	    // InternalKGraph.g:3696:1: ( (lv_actions_20_0= ruleKAction ) )*
                    	    loop71:
                    	    do {
                    	        int alt71=2;
                    	        int LA71_0 = input.LA(1);

                    	        if ( ((LA71_0>=141 && LA71_0<=146)) ) {
                    	            alt71=1;
                    	        }


                    	        switch (alt71) {
                    	    	case 1 :
                    	    	    // InternalKGraph.g:3697:1: (lv_actions_20_0= ruleKAction )
                    	    	    {
                    	    	    // InternalKGraph.g:3697:1: (lv_actions_20_0= ruleKAction )
                    	    	    // InternalKGraph.g:3698:3: lv_actions_20_0= ruleKAction
                    	    	    {
                    	    	     
                    	    	    	        newCompositeNode(grammarAccess.getKPolylineAccess().getActionsKActionParserRuleCall_3_1_2_2_0()); 
                    	    	    	    
                    	    	    pushFollow(FollowSets000.FOLLOW_66);
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
                    	    // InternalKGraph.g:3721:4: ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3721:4: ({...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) ) )
                    	    // InternalKGraph.g:3722:5: {...}? => ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 3) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 3)");
                    	    }
                    	    // InternalKGraph.g:3722:108: ( ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) ) )
                    	    // InternalKGraph.g:3723:6: ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 3);
                    	    	 				
                    	    // InternalKGraph.g:3726:6: ({...}? => ( (lv_placementData_21_0= ruleKPlacementData ) ) )
                    	    // InternalKGraph.g:3726:7: {...}? => ( (lv_placementData_21_0= ruleKPlacementData ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3726:16: ( (lv_placementData_21_0= ruleKPlacementData ) )
                    	    // InternalKGraph.g:3727:1: (lv_placementData_21_0= ruleKPlacementData )
                    	    {
                    	    // InternalKGraph.g:3727:1: (lv_placementData_21_0= ruleKPlacementData )
                    	    // InternalKGraph.g:3728:3: lv_placementData_21_0= ruleKPlacementData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolylineAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_3_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_61);
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
                    	    // InternalKGraph.g:3751:4: ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) )
                    	    {
                    	    // InternalKGraph.g:3751:4: ({...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) ) )
                    	    // InternalKGraph.g:3752:5: {...}? => ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 4) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 4)");
                    	    }
                    	    // InternalKGraph.g:3752:108: ( ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) ) )
                    	    // InternalKGraph.g:3753:6: ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) )
                    	    {
                    	     
                    	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPolylineAccess().getUnorderedGroup_3_1(), 4);
                    	    	 				
                    	    // InternalKGraph.g:3756:6: ({...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) ) )
                    	    // InternalKGraph.g:3756:7: {...}? => ( (lv_childPlacement_22_0= ruleKPlacement ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleKPolyline", "true");
                    	    }
                    	    // InternalKGraph.g:3756:16: ( (lv_childPlacement_22_0= ruleKPlacement ) )
                    	    // InternalKGraph.g:3757:1: (lv_childPlacement_22_0= ruleKPlacement )
                    	    {
                    	    // InternalKGraph.g:3757:1: (lv_childPlacement_22_0= ruleKPlacement )
                    	    // InternalKGraph.g:3758:3: lv_childPlacement_22_0= ruleKPlacement
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolylineAccess().getChildPlacementKPlacementParserRuleCall_3_1_4_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_61);
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

                    // InternalKGraph.g:3788:2: (otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) ) )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==61) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // InternalKGraph.g:3788:4: otherlv_23= 'junction' ( (lv_junctionPointRendering_24_0= ruleKRendering ) )
                            {
                            otherlv_23=(Token)match(input,61,FollowSets000.FOLLOW_60); 

                                	newLeafNode(otherlv_23, grammarAccess.getKPolylineAccess().getJunctionKeyword_3_2_0());
                                
                            // InternalKGraph.g:3792:1: ( (lv_junctionPointRendering_24_0= ruleKRendering ) )
                            // InternalKGraph.g:3793:1: (lv_junctionPointRendering_24_0= ruleKRendering )
                            {
                            // InternalKGraph.g:3793:1: (lv_junctionPointRendering_24_0= ruleKRendering )
                            // InternalKGraph.g:3794:3: lv_junctionPointRendering_24_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolylineAccess().getJunctionPointRenderingKRenderingParserRuleCall_3_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_20);
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

                    // InternalKGraph.g:3810:4: ( (lv_children_25_0= ruleKRendering ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==50||(LA74_0>=52 && LA74_0<=59)||(LA74_0>=62 && LA74_0<=65)) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // InternalKGraph.g:3811:1: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // InternalKGraph.g:3811:1: (lv_children_25_0= ruleKRendering )
                    	    // InternalKGraph.g:3812:3: lv_children_25_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolylineAccess().getChildrenKRenderingParserRuleCall_3_3_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_20);
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

                    otherlv_26=(Token)match(input,22,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3840:1: entryRuleKSimplePolyline returns [EObject current=null] : iv_ruleKSimplePolyline= ruleKSimplePolyline EOF ;
    public final EObject entryRuleKSimplePolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSimplePolyline = null;


        try {
            // InternalKGraph.g:3841:2: (iv_ruleKSimplePolyline= ruleKSimplePolyline EOF )
            // InternalKGraph.g:3842:2: iv_ruleKSimplePolyline= ruleKSimplePolyline EOF
            {
             newCompositeNode(grammarAccess.getKSimplePolylineRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKSimplePolyline=ruleKSimplePolyline();

            state._fsp--;

             current =iv_ruleKSimplePolyline; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3849:1: ruleKSimplePolyline returns [EObject current=null] : ( () otherlv_1= 'kpolyline' ) ;
    public final EObject ruleKSimplePolyline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:3852:28: ( ( () otherlv_1= 'kpolyline' ) )
            // InternalKGraph.g:3853:1: ( () otherlv_1= 'kpolyline' )
            {
            // InternalKGraph.g:3853:1: ( () otherlv_1= 'kpolyline' )
            // InternalKGraph.g:3853:2: () otherlv_1= 'kpolyline'
            {
            // InternalKGraph.g:3853:2: ()
            // InternalKGraph.g:3854:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSimplePolylineAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,62,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3871:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // InternalKGraph.g:3872:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // InternalKGraph.g:3873:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3880:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'kpolygon' ) ;
    public final EObject ruleKPolygon() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:3883:28: ( ( () otherlv_1= 'kpolygon' ) )
            // InternalKGraph.g:3884:1: ( () otherlv_1= 'kpolygon' )
            {
            // InternalKGraph.g:3884:1: ( () otherlv_1= 'kpolygon' )
            // InternalKGraph.g:3884:2: () otherlv_1= 'kpolygon'
            {
            // InternalKGraph.g:3884:2: ()
            // InternalKGraph.g:3885:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,63,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3902:1: entryRuleKRoundedBendsPolyline returns [EObject current=null] : iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF ;
    public final EObject entryRuleKRoundedBendsPolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedBendsPolyline = null;


        try {
            // InternalKGraph.g:3903:2: (iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF )
            // InternalKGraph.g:3904:2: iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF
            {
             newCompositeNode(grammarAccess.getKRoundedBendsPolylineRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRoundedBendsPolyline=ruleKRoundedBendsPolyline();

            state._fsp--;

             current =iv_ruleKRoundedBendsPolyline; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3911:1: ruleKRoundedBendsPolyline returns [EObject current=null] : ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? ) ;
    public final EObject ruleKRoundedBendsPolyline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_bendRadius_3_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:3914:28: ( ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? ) )
            // InternalKGraph.g:3915:1: ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? )
            {
            // InternalKGraph.g:3915:1: ( () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )? )
            // InternalKGraph.g:3915:2: () otherlv_1= 'kroundedPolyline' (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )?
            {
            // InternalKGraph.g:3915:2: ()
            // InternalKGraph.g:3916:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedBendsPolylineAccess().getKRoundedBendsPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,64,FollowSets000.FOLLOW_52); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedBendsPolylineAccess().getKroundedPolylineKeyword_1());
                
            // InternalKGraph.g:3925:1: (otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')' )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==24) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalKGraph.g:3925:3: otherlv_2= '(' ( (lv_bendRadius_3_0= ruleFloat ) ) otherlv_4= ')'
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_28); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRoundedBendsPolylineAccess().getLeftParenthesisKeyword_2_0());
                        
                    // InternalKGraph.g:3929:1: ( (lv_bendRadius_3_0= ruleFloat ) )
                    // InternalKGraph.g:3930:1: (lv_bendRadius_3_0= ruleFloat )
                    {
                    // InternalKGraph.g:3930:1: (lv_bendRadius_3_0= ruleFloat )
                    // InternalKGraph.g:3931:3: lv_bendRadius_3_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getBendRadiusFloatParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_13);
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

                    otherlv_4=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3959:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // InternalKGraph.g:3960:2: (iv_ruleKSpline= ruleKSpline EOF )
            // InternalKGraph.g:3961:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3968:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'kspline' ) ;
    public final EObject ruleKSpline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:3971:28: ( ( () otherlv_1= 'kspline' ) )
            // InternalKGraph.g:3972:1: ( () otherlv_1= 'kspline' )
            {
            // InternalKGraph.g:3972:1: ( () otherlv_1= 'kspline' )
            // InternalKGraph.g:3972:2: () otherlv_1= 'kspline'
            {
            // InternalKGraph.g:3972:2: ()
            // InternalKGraph.g:3973:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,65,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3990:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // InternalKGraph.g:3991:2: (iv_ruleKStyle= ruleKStyle EOF )
            // InternalKGraph.g:3992:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:3999:1: ruleKStyle returns [EObject current=null] : ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? ) ;
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
            // InternalKGraph.g:4002:28: ( ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? ) )
            // InternalKGraph.g:4003:1: ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? )
            {
            // InternalKGraph.g:4003:1: ( (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )? )
            // InternalKGraph.g:4003:2: (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef ) (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )?
            {
            // InternalKGraph.g:4003:2: (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef )
            int alt77=16;
            alt77 = dfa77.predict(input);
            switch (alt77) {
                case 1 :
                    // InternalKGraph.g:4004:5: this_KColoring_0= ruleKColoring
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKColoringParserRuleCall_0_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KColoring_0=ruleKColoring();

                    state._fsp--;

                     
                            current = this_KColoring_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:4014:5: this_KFontBold_1= ruleKFontBold
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontBoldParserRuleCall_0_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KFontBold_1=ruleKFontBold();

                    state._fsp--;

                     
                            current = this_KFontBold_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:4024:5: this_KFontItalic_2= ruleKFontItalic
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontItalicParserRuleCall_0_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KFontItalic_2=ruleKFontItalic();

                    state._fsp--;

                     
                            current = this_KFontItalic_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:4034:5: this_KFontName_3= ruleKFontName
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontNameParserRuleCall_0_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KFontName_3=ruleKFontName();

                    state._fsp--;

                     
                            current = this_KFontName_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // InternalKGraph.g:4044:5: this_KFontSize_4= ruleKFontSize
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontSizeParserRuleCall_0_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KFontSize_4=ruleKFontSize();

                    state._fsp--;

                     
                            current = this_KFontSize_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // InternalKGraph.g:4054:5: this_KTextUnderline_5= ruleKTextUnderline
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKTextUnderlineParserRuleCall_0_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KTextUnderline_5=ruleKTextUnderline();

                    state._fsp--;

                     
                            current = this_KTextUnderline_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // InternalKGraph.g:4064:5: this_KHorizontalAlignment_6= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_0_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KHorizontalAlignment_6=ruleKHorizontalAlignment();

                    state._fsp--;

                     
                            current = this_KHorizontalAlignment_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // InternalKGraph.g:4074:5: this_KVerticalAlignment_7= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_0_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KVerticalAlignment_7=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // InternalKGraph.g:4084:5: this_KInvisibility_8= ruleKInvisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKInvisibilityParserRuleCall_0_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KInvisibility_8=ruleKInvisibility();

                    state._fsp--;

                     
                            current = this_KInvisibility_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // InternalKGraph.g:4094:5: this_KLineCap_9= ruleKLineCap
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineCapParserRuleCall_0_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KLineCap_9=ruleKLineCap();

                    state._fsp--;

                     
                            current = this_KLineCap_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // InternalKGraph.g:4104:5: this_KLineJoin_10= ruleKLineJoin
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineJoinParserRuleCall_0_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KLineJoin_10=ruleKLineJoin();

                    state._fsp--;

                     
                            current = this_KLineJoin_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // InternalKGraph.g:4114:5: this_KLineStyle_11= ruleKLineStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_0_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KLineStyle_11=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_11; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 13 :
                    // InternalKGraph.g:4124:5: this_KLineWidth_12= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_0_12()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KLineWidth_12=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_12; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 14 :
                    // InternalKGraph.g:4134:5: this_KRotation_13= ruleKRotation
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKRotationParserRuleCall_0_13()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KRotation_13=ruleKRotation();

                    state._fsp--;

                     
                            current = this_KRotation_13; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 15 :
                    // InternalKGraph.g:4144:5: this_KShadow_14= ruleKShadow
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKShadowParserRuleCall_0_14()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KShadow_14=ruleKShadow();

                    state._fsp--;

                     
                            current = this_KShadow_14; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 16 :
                    // InternalKGraph.g:4154:5: this_KStyleRef_15= ruleKStyleRef
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKStyleRefParserRuleCall_0_15()); 
                        
                    pushFollow(FollowSets000.FOLLOW_67);
                    this_KStyleRef_15=ruleKStyleRef();

                    state._fsp--;

                     
                            current = this_KStyleRef_15; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // InternalKGraph.g:4162:2: (otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==66) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalKGraph.g:4162:4: otherlv_16= 'modifier' ( (lv_modifierId_17_0= ruleQualifiedID ) )
                    {
                    otherlv_16=(Token)match(input,66,FollowSets000.FOLLOW_10); 

                        	newLeafNode(otherlv_16, grammarAccess.getKStyleAccess().getModifierKeyword_1_0());
                        
                    // InternalKGraph.g:4166:1: ( (lv_modifierId_17_0= ruleQualifiedID ) )
                    // InternalKGraph.g:4167:1: (lv_modifierId_17_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:4167:1: (lv_modifierId_17_0= ruleQualifiedID )
                    // InternalKGraph.g:4168:3: lv_modifierId_17_0= ruleQualifiedID
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStyleAccess().getModifierIdQualifiedIDParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:4192:1: entryRuleKColoring returns [EObject current=null] : iv_ruleKColoring= ruleKColoring EOF ;
    public final EObject entryRuleKColoring() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKColoring = null;


        try {
            // InternalKGraph.g:4193:2: (iv_ruleKColoring= ruleKColoring EOF )
            // InternalKGraph.g:4194:2: iv_ruleKColoring= ruleKColoring EOF
            {
             newCompositeNode(grammarAccess.getKColoringRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKColoring=ruleKColoring();

            state._fsp--;

             current =iv_ruleKColoring; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4201:1: ruleKColoring returns [EObject current=null] : ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) ) ;
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
            // InternalKGraph.g:4204:28: ( ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) ) )
            // InternalKGraph.g:4205:1: ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) )
            {
            // InternalKGraph.g:4205:1: ( (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) ) )
            // InternalKGraph.g:4205:2: (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground ) otherlv_2= '=' (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) )
            {
            // InternalKGraph.g:4205:2: (this_KForeground_0= ruleKForeground | this_KBackground_1= ruleKBackground )
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
                    // InternalKGraph.g:4206:5: this_KForeground_0= ruleKForeground
                    {
                     
                            newCompositeNode(grammarAccess.getKColoringAccess().getKForegroundParserRuleCall_0_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_22);
                    this_KForeground_0=ruleKForeground();

                    state._fsp--;

                     
                            current = this_KForeground_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:4216:5: this_KBackground_1= ruleKBackground
                    {
                     
                            newCompositeNode(grammarAccess.getKColoringAccess().getKBackgroundParserRuleCall_0_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_22);
                    this_KBackground_1=ruleKBackground();

                    state._fsp--;

                     
                            current = this_KBackground_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_68); 

                	newLeafNode(otherlv_2, grammarAccess.getKColoringAccess().getEqualsSignKeyword_1());
                
            // InternalKGraph.g:4228:1: (otherlv_3= 'null' | ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? ) )
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
                    // InternalKGraph.g:4228:3: otherlv_3= 'null'
                    {
                    otherlv_3=(Token)match(input,67,FollowSets000.FOLLOW_2); 

                        	newLeafNode(otherlv_3, grammarAccess.getKColoringAccess().getNullKeyword_2_0());
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:4233:6: ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? )
                    {
                    // InternalKGraph.g:4233:6: ( ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )? )
                    // InternalKGraph.g:4233:7: ( (lv_color_4_0= ruleKColor ) ) (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )? (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )?
                    {
                    // InternalKGraph.g:4233:7: ( (lv_color_4_0= ruleKColor ) )
                    // InternalKGraph.g:4234:1: (lv_color_4_0= ruleKColor )
                    {
                    // InternalKGraph.g:4234:1: (lv_color_4_0= ruleKColor )
                    // InternalKGraph.g:4235:3: lv_color_4_0= ruleKColor
                    {
                     
                    	        newCompositeNode(grammarAccess.getKColoringAccess().getColorKColorParserRuleCall_2_1_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_69);
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

                    // InternalKGraph.g:4251:2: (otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) ) )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==47) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // InternalKGraph.g:4251:4: otherlv_5= ',' ( (lv_alpha_6_0= RULE_ALPHA ) )
                            {
                            otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_70); 

                                	newLeafNode(otherlv_5, grammarAccess.getKColoringAccess().getCommaKeyword_2_1_1_0());
                                
                            // InternalKGraph.g:4255:1: ( (lv_alpha_6_0= RULE_ALPHA ) )
                            // InternalKGraph.g:4256:1: (lv_alpha_6_0= RULE_ALPHA )
                            {
                            // InternalKGraph.g:4256:1: (lv_alpha_6_0= RULE_ALPHA )
                            // InternalKGraph.g:4257:3: lv_alpha_6_0= RULE_ALPHA
                            {
                            lv_alpha_6_0=(Token)match(input,RULE_ALPHA,FollowSets000.FOLLOW_71); 

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

                    // InternalKGraph.g:4273:4: (otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )? )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==26) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // InternalKGraph.g:4273:6: otherlv_7= '->' ( (lv_targetColor_8_0= ruleKColor ) ) (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )? (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )?
                            {
                            otherlv_7=(Token)match(input,26,FollowSets000.FOLLOW_68); 

                                	newLeafNode(otherlv_7, grammarAccess.getKColoringAccess().getHyphenMinusGreaterThanSignKeyword_2_1_2_0());
                                
                            // InternalKGraph.g:4277:1: ( (lv_targetColor_8_0= ruleKColor ) )
                            // InternalKGraph.g:4278:1: (lv_targetColor_8_0= ruleKColor )
                            {
                            // InternalKGraph.g:4278:1: (lv_targetColor_8_0= ruleKColor )
                            // InternalKGraph.g:4279:3: lv_targetColor_8_0= ruleKColor
                            {
                             
                            	        newCompositeNode(grammarAccess.getKColoringAccess().getTargetColorKColorParserRuleCall_2_1_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_72);
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

                            // InternalKGraph.g:4295:2: (otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) ) )?
                            int alt81=2;
                            int LA81_0 = input.LA(1);

                            if ( (LA81_0==47) ) {
                                alt81=1;
                            }
                            switch (alt81) {
                                case 1 :
                                    // InternalKGraph.g:4295:4: otherlv_9= ',' ( (lv_targetAlpha_10_0= RULE_ALPHA ) )
                                    {
                                    otherlv_9=(Token)match(input,47,FollowSets000.FOLLOW_70); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKColoringAccess().getCommaKeyword_2_1_2_2_0());
                                        
                                    // InternalKGraph.g:4299:1: ( (lv_targetAlpha_10_0= RULE_ALPHA ) )
                                    // InternalKGraph.g:4300:1: (lv_targetAlpha_10_0= RULE_ALPHA )
                                    {
                                    // InternalKGraph.g:4300:1: (lv_targetAlpha_10_0= RULE_ALPHA )
                                    // InternalKGraph.g:4301:3: lv_targetAlpha_10_0= RULE_ALPHA
                                    {
                                    lv_targetAlpha_10_0=(Token)match(input,RULE_ALPHA,FollowSets000.FOLLOW_52); 

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

                            // InternalKGraph.g:4317:4: (otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')' )?
                            int alt82=2;
                            int LA82_0 = input.LA(1);

                            if ( (LA82_0==24) ) {
                                alt82=1;
                            }
                            switch (alt82) {
                                case 1 :
                                    // InternalKGraph.g:4317:6: otherlv_11= '(' ( (lv_gradientAngle_12_0= ruleFloat ) ) otherlv_13= ')'
                                    {
                                    otherlv_11=(Token)match(input,24,FollowSets000.FOLLOW_28); 

                                        	newLeafNode(otherlv_11, grammarAccess.getKColoringAccess().getLeftParenthesisKeyword_2_1_2_3_0());
                                        
                                    // InternalKGraph.g:4321:1: ( (lv_gradientAngle_12_0= ruleFloat ) )
                                    // InternalKGraph.g:4322:1: (lv_gradientAngle_12_0= ruleFloat )
                                    {
                                    // InternalKGraph.g:4322:1: (lv_gradientAngle_12_0= ruleFloat )
                                    // InternalKGraph.g:4323:3: lv_gradientAngle_12_0= ruleFloat
                                    {
                                     
                                    	        newCompositeNode(grammarAccess.getKColoringAccess().getGradientAngleFloatParserRuleCall_2_1_2_3_1_0()); 
                                    	    
                                    pushFollow(FollowSets000.FOLLOW_13);
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

                                    otherlv_13=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4351:1: entryRuleKForeground returns [EObject current=null] : iv_ruleKForeground= ruleKForeground EOF ;
    public final EObject entryRuleKForeground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForeground = null;


        try {
            // InternalKGraph.g:4352:2: (iv_ruleKForeground= ruleKForeground EOF )
            // InternalKGraph.g:4353:2: iv_ruleKForeground= ruleKForeground EOF
            {
             newCompositeNode(grammarAccess.getKForegroundRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKForeground=ruleKForeground();

            state._fsp--;

             current =iv_ruleKForeground; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4360:1: ruleKForeground returns [EObject current=null] : ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' ) ;
    public final EObject ruleKForeground() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_1_0=null;
        Token lv_selection_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:4363:28: ( ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' ) )
            // InternalKGraph.g:4364:1: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' )
            {
            // InternalKGraph.g:4364:1: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground' )
            // InternalKGraph.g:4364:2: () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'foreground'
            {
            // InternalKGraph.g:4364:2: ()
            // InternalKGraph.g:4365:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundAccess().getKForegroundAction_0(),
                        current);
                

            }

            // InternalKGraph.g:4370:2: ( (lv_propagateToChildren_1_0= 'propagate' ) )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==68) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalKGraph.g:4371:1: (lv_propagateToChildren_1_0= 'propagate' )
                    {
                    // InternalKGraph.g:4371:1: (lv_propagateToChildren_1_0= 'propagate' )
                    // InternalKGraph.g:4372:3: lv_propagateToChildren_1_0= 'propagate'
                    {
                    lv_propagateToChildren_1_0=(Token)match(input,68,FollowSets000.FOLLOW_73); 

                            newLeafNode(lv_propagateToChildren_1_0, grammarAccess.getKForegroundAccess().getPropagateToChildrenPropagateKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4385:3: ( (lv_selection_2_0= 'selection' ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==69) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalKGraph.g:4386:1: (lv_selection_2_0= 'selection' )
                    {
                    // InternalKGraph.g:4386:1: (lv_selection_2_0= 'selection' )
                    // InternalKGraph.g:4387:3: lv_selection_2_0= 'selection'
                    {
                    lv_selection_2_0=(Token)match(input,69,FollowSets000.FOLLOW_74); 

                            newLeafNode(lv_selection_2_0, grammarAccess.getKForegroundAccess().getSelectionSelectionKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,70,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4412:1: entryRuleKBackground returns [EObject current=null] : iv_ruleKBackground= ruleKBackground EOF ;
    public final EObject entryRuleKBackground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackground = null;


        try {
            // InternalKGraph.g:4413:2: (iv_ruleKBackground= ruleKBackground EOF )
            // InternalKGraph.g:4414:2: iv_ruleKBackground= ruleKBackground EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKBackground=ruleKBackground();

            state._fsp--;

             current =iv_ruleKBackground; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4421:1: ruleKBackground returns [EObject current=null] : ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' ) ;
    public final EObject ruleKBackground() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_1_0=null;
        Token lv_selection_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:4424:28: ( ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' ) )
            // InternalKGraph.g:4425:1: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' )
            {
            // InternalKGraph.g:4425:1: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background' )
            // InternalKGraph.g:4425:2: () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'background'
            {
            // InternalKGraph.g:4425:2: ()
            // InternalKGraph.g:4426:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundAccess().getKBackgroundAction_0(),
                        current);
                

            }

            // InternalKGraph.g:4431:2: ( (lv_propagateToChildren_1_0= 'propagate' ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==68) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalKGraph.g:4432:1: (lv_propagateToChildren_1_0= 'propagate' )
                    {
                    // InternalKGraph.g:4432:1: (lv_propagateToChildren_1_0= 'propagate' )
                    // InternalKGraph.g:4433:3: lv_propagateToChildren_1_0= 'propagate'
                    {
                    lv_propagateToChildren_1_0=(Token)match(input,68,FollowSets000.FOLLOW_75); 

                            newLeafNode(lv_propagateToChildren_1_0, grammarAccess.getKBackgroundAccess().getPropagateToChildrenPropagateKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4446:3: ( (lv_selection_2_0= 'selection' ) )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==69) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalKGraph.g:4447:1: (lv_selection_2_0= 'selection' )
                    {
                    // InternalKGraph.g:4447:1: (lv_selection_2_0= 'selection' )
                    // InternalKGraph.g:4448:3: lv_selection_2_0= 'selection'
                    {
                    lv_selection_2_0=(Token)match(input,69,FollowSets000.FOLLOW_76); 

                            newLeafNode(lv_selection_2_0, grammarAccess.getKBackgroundAccess().getSelectionSelectionKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,71,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4473:1: entryRuleKFontBold returns [EObject current=null] : iv_ruleKFontBold= ruleKFontBold EOF ;
    public final EObject entryRuleKFontBold() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontBold = null;


        try {
            // InternalKGraph.g:4474:2: (iv_ruleKFontBold= ruleKFontBold EOF )
            // InternalKGraph.g:4475:2: iv_ruleKFontBold= ruleKFontBold EOF
            {
             newCompositeNode(grammarAccess.getKFontBoldRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKFontBold=ruleKFontBold();

            state._fsp--;

             current =iv_ruleKFontBold; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4482:1: ruleKFontBold returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleKFontBold() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_bold_4_0=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:4485:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) ) )
            // InternalKGraph.g:4486:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) )
            {
            // InternalKGraph.g:4486:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) ) )
            // InternalKGraph.g:4486:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'bold' otherlv_3= '=' ( (lv_bold_4_0= RULE_BOOLEAN ) )
            {
            // InternalKGraph.g:4486:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==68) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // InternalKGraph.g:4487:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4487:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4488:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_77); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontBoldAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontBoldRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4501:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==69) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // InternalKGraph.g:4502:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4502:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4503:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_78); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKFontBoldAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontBoldRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,72,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKFontBoldAccess().getBoldKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_55); 

                	newLeafNode(otherlv_3, grammarAccess.getKFontBoldAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:4524:1: ( (lv_bold_4_0= RULE_BOOLEAN ) )
            // InternalKGraph.g:4525:1: (lv_bold_4_0= RULE_BOOLEAN )
            {
            // InternalKGraph.g:4525:1: (lv_bold_4_0= RULE_BOOLEAN )
            // InternalKGraph.g:4526:3: lv_bold_4_0= RULE_BOOLEAN
            {
            lv_bold_4_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4550:1: entryRuleKFontItalic returns [EObject current=null] : iv_ruleKFontItalic= ruleKFontItalic EOF ;
    public final EObject entryRuleKFontItalic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontItalic = null;


        try {
            // InternalKGraph.g:4551:2: (iv_ruleKFontItalic= ruleKFontItalic EOF )
            // InternalKGraph.g:4552:2: iv_ruleKFontItalic= ruleKFontItalic EOF
            {
             newCompositeNode(grammarAccess.getKFontItalicRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKFontItalic=ruleKFontItalic();

            state._fsp--;

             current =iv_ruleKFontItalic; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4559:1: ruleKFontItalic returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleKFontItalic() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_italic_4_0=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:4562:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) ) )
            // InternalKGraph.g:4563:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) )
            {
            // InternalKGraph.g:4563:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) ) )
            // InternalKGraph.g:4563:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'italic' otherlv_3= '=' ( (lv_italic_4_0= RULE_BOOLEAN ) )
            {
            // InternalKGraph.g:4563:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==68) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // InternalKGraph.g:4564:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4564:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4565:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_79); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontItalicAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontItalicRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4578:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==69) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalKGraph.g:4579:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4579:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4580:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_80); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKFontItalicAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontItalicRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,73,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKFontItalicAccess().getItalicKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_55); 

                	newLeafNode(otherlv_3, grammarAccess.getKFontItalicAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:4601:1: ( (lv_italic_4_0= RULE_BOOLEAN ) )
            // InternalKGraph.g:4602:1: (lv_italic_4_0= RULE_BOOLEAN )
            {
            // InternalKGraph.g:4602:1: (lv_italic_4_0= RULE_BOOLEAN )
            // InternalKGraph.g:4603:3: lv_italic_4_0= RULE_BOOLEAN
            {
            lv_italic_4_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4627:1: entryRuleKFontName returns [EObject current=null] : iv_ruleKFontName= ruleKFontName EOF ;
    public final EObject entryRuleKFontName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontName = null;


        try {
            // InternalKGraph.g:4628:2: (iv_ruleKFontName= ruleKFontName EOF )
            // InternalKGraph.g:4629:2: iv_ruleKFontName= ruleKFontName EOF
            {
             newCompositeNode(grammarAccess.getKFontNameRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKFontName=ruleKFontName();

            state._fsp--;

             current =iv_ruleKFontName; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4636:1: ruleKFontName returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) ) ;
    public final EObject ruleKFontName() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_name_4_0=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:4639:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) ) )
            // InternalKGraph.g:4640:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) )
            {
            // InternalKGraph.g:4640:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) ) )
            // InternalKGraph.g:4640:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'fontName' otherlv_3= '=' ( (lv_name_4_0= RULE_STRING ) )
            {
            // InternalKGraph.g:4640:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==68) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // InternalKGraph.g:4641:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4641:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4642:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_81); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontNameAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontNameRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4655:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==69) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalKGraph.g:4656:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4656:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4657:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_82); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKFontNameAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontNameRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,74,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKFontNameAccess().getFontNameKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_53); 

                	newLeafNode(otherlv_3, grammarAccess.getKFontNameAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:4678:1: ( (lv_name_4_0= RULE_STRING ) )
            // InternalKGraph.g:4679:1: (lv_name_4_0= RULE_STRING )
            {
            // InternalKGraph.g:4679:1: (lv_name_4_0= RULE_STRING )
            // InternalKGraph.g:4680:3: lv_name_4_0= RULE_STRING
            {
            lv_name_4_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4704:1: entryRuleKFontSize returns [EObject current=null] : iv_ruleKFontSize= ruleKFontSize EOF ;
    public final EObject entryRuleKFontSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontSize = null;


        try {
            // InternalKGraph.g:4705:2: (iv_ruleKFontSize= ruleKFontSize EOF )
            // InternalKGraph.g:4706:2: iv_ruleKFontSize= ruleKFontSize EOF
            {
             newCompositeNode(grammarAccess.getKFontSizeRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKFontSize=ruleKFontSize();

            state._fsp--;

             current =iv_ruleKFontSize; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4713:1: ruleKFontSize returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) ) ;
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
            // InternalKGraph.g:4716:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) ) )
            // InternalKGraph.g:4717:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) )
            {
            // InternalKGraph.g:4717:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) ) )
            // InternalKGraph.g:4717:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? ( (lv_scaleWithZoom_2_0= 'scale' ) )? otherlv_3= 'fontSize' otherlv_4= '=' ( (lv_size_5_0= RULE_FSIZE ) )
            {
            // InternalKGraph.g:4717:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==68) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalKGraph.g:4718:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4718:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4719:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_83); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKFontSizeAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontSizeRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4732:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==69) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalKGraph.g:4733:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4733:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4734:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_84); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKFontSizeAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontSizeRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4747:3: ( (lv_scaleWithZoom_2_0= 'scale' ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==75) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalKGraph.g:4748:1: (lv_scaleWithZoom_2_0= 'scale' )
                    {
                    // InternalKGraph.g:4748:1: (lv_scaleWithZoom_2_0= 'scale' )
                    // InternalKGraph.g:4749:3: lv_scaleWithZoom_2_0= 'scale'
                    {
                    lv_scaleWithZoom_2_0=(Token)match(input,75,FollowSets000.FOLLOW_85); 

                            newLeafNode(lv_scaleWithZoom_2_0, grammarAccess.getKFontSizeAccess().getScaleWithZoomScaleKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontSizeRule());
                    	        }
                           		setWithLastConsumed(current, "scaleWithZoom", true, "scale");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,76,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_3, grammarAccess.getKFontSizeAccess().getFontSizeKeyword_3());
                
            otherlv_4=(Token)match(input,30,FollowSets000.FOLLOW_86); 

                	newLeafNode(otherlv_4, grammarAccess.getKFontSizeAccess().getEqualsSignKeyword_4());
                
            // InternalKGraph.g:4770:1: ( (lv_size_5_0= RULE_FSIZE ) )
            // InternalKGraph.g:4771:1: (lv_size_5_0= RULE_FSIZE )
            {
            // InternalKGraph.g:4771:1: (lv_size_5_0= RULE_FSIZE )
            // InternalKGraph.g:4772:3: lv_size_5_0= RULE_FSIZE
            {
            lv_size_5_0=(Token)match(input,RULE_FSIZE,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4796:1: entryRuleKTextUnderline returns [EObject current=null] : iv_ruleKTextUnderline= ruleKTextUnderline EOF ;
    public final EObject entryRuleKTextUnderline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTextUnderline = null;


        try {
            // InternalKGraph.g:4797:2: (iv_ruleKTextUnderline= ruleKTextUnderline EOF )
            // InternalKGraph.g:4798:2: iv_ruleKTextUnderline= ruleKTextUnderline EOF
            {
             newCompositeNode(grammarAccess.getKTextUnderlineRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKTextUnderline=ruleKTextUnderline();

            state._fsp--;

             current =iv_ruleKTextUnderline; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4805:1: ruleKTextUnderline returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) ) ;
    public final EObject ruleKTextUnderline() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_underline_4_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:4808:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) ) )
            // InternalKGraph.g:4809:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) )
            {
            // InternalKGraph.g:4809:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) ) )
            // InternalKGraph.g:4809:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'underline' otherlv_3= '=' ( (lv_underline_4_0= ruleUnderline ) )
            {
            // InternalKGraph.g:4809:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==68) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // InternalKGraph.g:4810:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4810:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4811:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_87); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKTextUnderlineAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKTextUnderlineRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4824:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==69) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // InternalKGraph.g:4825:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4825:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4826:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_88); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKTextUnderlineAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKTextUnderlineRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,77,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKTextUnderlineAccess().getUnderlineKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_89); 

                	newLeafNode(otherlv_3, grammarAccess.getKTextUnderlineAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:4847:1: ( (lv_underline_4_0= ruleUnderline ) )
            // InternalKGraph.g:4848:1: (lv_underline_4_0= ruleUnderline )
            {
            // InternalKGraph.g:4848:1: (lv_underline_4_0= ruleUnderline )
            // InternalKGraph.g:4849:3: lv_underline_4_0= ruleUnderline
            {
             
            	        newCompositeNode(grammarAccess.getKTextUnderlineAccess().getUnderlineUnderlineEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:4873:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // InternalKGraph.g:4874:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // InternalKGraph.g:4875:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4882:1: ruleKHorizontalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_horizontalAlignment_4_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:4885:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) ) )
            // InternalKGraph.g:4886:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) )
            {
            // InternalKGraph.g:4886:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) )
            // InternalKGraph.g:4886:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'hAlign' otherlv_3= '=' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) )
            {
            // InternalKGraph.g:4886:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==68) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // InternalKGraph.g:4887:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4887:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4888:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_90); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKHorizontalAlignmentAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4901:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==69) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // InternalKGraph.g:4902:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4902:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4903:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_91); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKHorizontalAlignmentAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,78,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKHorizontalAlignmentAccess().getHAlignKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_92); 

                	newLeafNode(otherlv_3, grammarAccess.getKHorizontalAlignmentAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:4924:1: ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) )
            // InternalKGraph.g:4925:1: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            {
            // InternalKGraph.g:4925:1: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            // InternalKGraph.g:4926:3: lv_horizontalAlignment_4_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:4950:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // InternalKGraph.g:4951:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // InternalKGraph.g:4952:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:4959:1: ruleKVerticalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_verticalAlignment_4_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:4962:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) ) )
            // InternalKGraph.g:4963:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) )
            {
            // InternalKGraph.g:4963:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) )
            // InternalKGraph.g:4963:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'vAlign' otherlv_3= '=' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) )
            {
            // InternalKGraph.g:4963:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==68) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // InternalKGraph.g:4964:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:4964:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:4965:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_93); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKVerticalAlignmentAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:4978:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==69) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalKGraph.g:4979:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:4979:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:4980:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_94); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKVerticalAlignmentAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,79,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKVerticalAlignmentAccess().getVAlignKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_95); 

                	newLeafNode(otherlv_3, grammarAccess.getKVerticalAlignmentAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:5001:1: ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) )
            // InternalKGraph.g:5002:1: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            {
            // InternalKGraph.g:5002:1: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            // InternalKGraph.g:5003:3: lv_verticalAlignment_4_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:5027:1: entryRuleKInvisibility returns [EObject current=null] : iv_ruleKInvisibility= ruleKInvisibility EOF ;
    public final EObject entryRuleKInvisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInvisibility = null;


        try {
            // InternalKGraph.g:5028:2: (iv_ruleKInvisibility= ruleKInvisibility EOF )
            // InternalKGraph.g:5029:2: iv_ruleKInvisibility= ruleKInvisibility EOF
            {
             newCompositeNode(grammarAccess.getKInvisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKInvisibility=ruleKInvisibility();

            state._fsp--;

             current =iv_ruleKInvisibility; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5036:1: ruleKInvisibility returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleKInvisibility() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_invisible_4_0=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:5039:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) ) )
            // InternalKGraph.g:5040:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) )
            {
            // InternalKGraph.g:5040:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) ) )
            // InternalKGraph.g:5040:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'invisible' otherlv_3= '=' ( (lv_invisible_4_0= RULE_BOOLEAN ) )
            {
            // InternalKGraph.g:5040:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==68) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // InternalKGraph.g:5041:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5041:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5042:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_96); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKInvisibilityAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKInvisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5055:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==69) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalKGraph.g:5056:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5056:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5057:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_97); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKInvisibilityAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKInvisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,80,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKInvisibilityAccess().getInvisibleKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_55); 

                	newLeafNode(otherlv_3, grammarAccess.getKInvisibilityAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:5078:1: ( (lv_invisible_4_0= RULE_BOOLEAN ) )
            // InternalKGraph.g:5079:1: (lv_invisible_4_0= RULE_BOOLEAN )
            {
            // InternalKGraph.g:5079:1: (lv_invisible_4_0= RULE_BOOLEAN )
            // InternalKGraph.g:5080:3: lv_invisible_4_0= RULE_BOOLEAN
            {
            lv_invisible_4_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5104:1: entryRuleKLineCap returns [EObject current=null] : iv_ruleKLineCap= ruleKLineCap EOF ;
    public final EObject entryRuleKLineCap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineCap = null;


        try {
            // InternalKGraph.g:5105:2: (iv_ruleKLineCap= ruleKLineCap EOF )
            // InternalKGraph.g:5106:2: iv_ruleKLineCap= ruleKLineCap EOF
            {
             newCompositeNode(grammarAccess.getKLineCapRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKLineCap=ruleKLineCap();

            state._fsp--;

             current =iv_ruleKLineCap; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5113:1: ruleKLineCap returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) ) ;
    public final EObject ruleKLineCap() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_lineCap_4_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:5116:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) ) )
            // InternalKGraph.g:5117:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) )
            {
            // InternalKGraph.g:5117:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) ) )
            // InternalKGraph.g:5117:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineCap' otherlv_3= '=' ( (lv_lineCap_4_0= ruleLineCap ) )
            {
            // InternalKGraph.g:5117:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==68) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // InternalKGraph.g:5118:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5118:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5119:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_98); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineCapAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineCapRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5132:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==69) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // InternalKGraph.g:5133:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5133:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5134:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_99); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKLineCapAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineCapRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,81,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineCapAccess().getLineCapKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_100); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineCapAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:5155:1: ( (lv_lineCap_4_0= ruleLineCap ) )
            // InternalKGraph.g:5156:1: (lv_lineCap_4_0= ruleLineCap )
            {
            // InternalKGraph.g:5156:1: (lv_lineCap_4_0= ruleLineCap )
            // InternalKGraph.g:5157:3: lv_lineCap_4_0= ruleLineCap
            {
             
            	        newCompositeNode(grammarAccess.getKLineCapAccess().getLineCapLineCapEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:5181:1: entryRuleKLineJoin returns [EObject current=null] : iv_ruleKLineJoin= ruleKLineJoin EOF ;
    public final EObject entryRuleKLineJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineJoin = null;


        try {
            // InternalKGraph.g:5182:2: (iv_ruleKLineJoin= ruleKLineJoin EOF )
            // InternalKGraph.g:5183:2: iv_ruleKLineJoin= ruleKLineJoin EOF
            {
             newCompositeNode(grammarAccess.getKLineJoinRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKLineJoin=ruleKLineJoin();

            state._fsp--;

             current =iv_ruleKLineJoin; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5190:1: ruleKLineJoin returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) ) ;
    public final EObject ruleKLineJoin() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Enumerator lv_lineJoin_4_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:5193:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) ) )
            // InternalKGraph.g:5194:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) )
            {
            // InternalKGraph.g:5194:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) ) )
            // InternalKGraph.g:5194:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineJoin' otherlv_3= '=' ( (lv_lineJoin_4_0= ruleLineJoin ) )
            {
            // InternalKGraph.g:5194:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==68) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // InternalKGraph.g:5195:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5195:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5196:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_101); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineJoinAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineJoinRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5209:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==69) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // InternalKGraph.g:5210:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5210:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5211:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_102); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKLineJoinAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineJoinRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,82,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineJoinAccess().getLineJoinKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_103); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineJoinAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:5232:1: ( (lv_lineJoin_4_0= ruleLineJoin ) )
            // InternalKGraph.g:5233:1: (lv_lineJoin_4_0= ruleLineJoin )
            {
            // InternalKGraph.g:5233:1: (lv_lineJoin_4_0= ruleLineJoin )
            // InternalKGraph.g:5234:3: lv_lineJoin_4_0= ruleLineJoin
            {
             
            	        newCompositeNode(grammarAccess.getKLineJoinAccess().getLineJoinLineJoinEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:5258:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // InternalKGraph.g:5259:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // InternalKGraph.g:5260:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5267:1: ruleKLineStyle returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? ) ;
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
            // InternalKGraph.g:5270:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? ) )
            // InternalKGraph.g:5271:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? )
            {
            // InternalKGraph.g:5271:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )? )
            // InternalKGraph.g:5271:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineStyle' otherlv_3= '=' ( (lv_lineStyle_4_0= ruleLineStyle ) ) (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )? (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )?
            {
            // InternalKGraph.g:5271:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==68) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // InternalKGraph.g:5272:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5272:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5273:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_104); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineStyleAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineStyleRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5286:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==69) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // InternalKGraph.g:5287:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5287:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5288:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_105); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKLineStyleAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineStyleRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,83,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_106); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineStyleAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:5309:1: ( (lv_lineStyle_4_0= ruleLineStyle ) )
            // InternalKGraph.g:5310:1: (lv_lineStyle_4_0= ruleLineStyle )
            {
            // InternalKGraph.g:5310:1: (lv_lineStyle_4_0= ruleLineStyle )
            // InternalKGraph.g:5311:3: lv_lineStyle_4_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_107);
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

            // InternalKGraph.g:5327:2: (otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) ) )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==84) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // InternalKGraph.g:5327:4: otherlv_5= 'dashOffset' otherlv_6= '=' ( (lv_dashOffset_7_0= ruleFloat ) )
                    {
                    otherlv_5=(Token)match(input,84,FollowSets000.FOLLOW_22); 

                        	newLeafNode(otherlv_5, grammarAccess.getKLineStyleAccess().getDashOffsetKeyword_5_0());
                        
                    otherlv_6=(Token)match(input,30,FollowSets000.FOLLOW_28); 

                        	newLeafNode(otherlv_6, grammarAccess.getKLineStyleAccess().getEqualsSignKeyword_5_1());
                        
                    // InternalKGraph.g:5335:1: ( (lv_dashOffset_7_0= ruleFloat ) )
                    // InternalKGraph.g:5336:1: (lv_dashOffset_7_0= ruleFloat )
                    {
                    // InternalKGraph.g:5336:1: (lv_dashOffset_7_0= ruleFloat )
                    // InternalKGraph.g:5337:3: lv_dashOffset_7_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLineStyleAccess().getDashOffsetFloatParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_108);
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

            // InternalKGraph.g:5353:4: (otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )* )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==85) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // InternalKGraph.g:5353:6: otherlv_8= 'dashPattern' otherlv_9= '=' ( (lv_dashPattern_10_0= ruleFloat ) )*
                    {
                    otherlv_8=(Token)match(input,85,FollowSets000.FOLLOW_22); 

                        	newLeafNode(otherlv_8, grammarAccess.getKLineStyleAccess().getDashPatternKeyword_6_0());
                        
                    otherlv_9=(Token)match(input,30,FollowSets000.FOLLOW_109); 

                        	newLeafNode(otherlv_9, grammarAccess.getKLineStyleAccess().getEqualsSignKeyword_6_1());
                        
                    // InternalKGraph.g:5361:1: ( (lv_dashPattern_10_0= ruleFloat ) )*
                    loop113:
                    do {
                        int alt113=2;
                        int LA113_0 = input.LA(1);

                        if ( (LA113_0==RULE_NATURAL||LA113_0==RULE_TFLOAT) ) {
                            alt113=1;
                        }


                        switch (alt113) {
                    	case 1 :
                    	    // InternalKGraph.g:5362:1: (lv_dashPattern_10_0= ruleFloat )
                    	    {
                    	    // InternalKGraph.g:5362:1: (lv_dashPattern_10_0= ruleFloat )
                    	    // InternalKGraph.g:5363:3: lv_dashPattern_10_0= ruleFloat
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKLineStyleAccess().getDashPatternFloatParserRuleCall_6_2_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_109);
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
    // InternalKGraph.g:5387:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // InternalKGraph.g:5388:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // InternalKGraph.g:5389:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5396:1: ruleKLineWidth returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_selection_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_lineWidth_4_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:5399:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) ) )
            // InternalKGraph.g:5400:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) )
            {
            // InternalKGraph.g:5400:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) ) )
            // InternalKGraph.g:5400:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'lineWidth' otherlv_3= '=' ( (lv_lineWidth_4_0= ruleFloat ) )
            {
            // InternalKGraph.g:5400:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==68) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // InternalKGraph.g:5401:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5401:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5402:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_110); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineWidthAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineWidthRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5415:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==69) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // InternalKGraph.g:5416:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5416:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5417:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_111); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKLineWidthAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineWidthRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,86,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_28); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineWidthAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:5438:1: ( (lv_lineWidth_4_0= ruleFloat ) )
            // InternalKGraph.g:5439:1: (lv_lineWidth_4_0= ruleFloat )
            {
            // InternalKGraph.g:5439:1: (lv_lineWidth_4_0= ruleFloat )
            // InternalKGraph.g:5440:3: lv_lineWidth_4_0= ruleFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:5464:1: entryRuleKRotation returns [EObject current=null] : iv_ruleKRotation= ruleKRotation EOF ;
    public final EObject entryRuleKRotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRotation = null;


        try {
            // InternalKGraph.g:5465:2: (iv_ruleKRotation= ruleKRotation EOF )
            // InternalKGraph.g:5466:2: iv_ruleKRotation= ruleKRotation EOF
            {
             newCompositeNode(grammarAccess.getKRotationRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRotation=ruleKRotation();

            state._fsp--;

             current =iv_ruleKRotation; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5473:1: ruleKRotation returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? ) ;
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
            // InternalKGraph.g:5476:28: ( ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? ) )
            // InternalKGraph.g:5477:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? )
            {
            // InternalKGraph.g:5477:1: ( ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )? )
            // InternalKGraph.g:5477:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )? ( (lv_selection_1_0= 'selection' ) )? otherlv_2= 'rotation' otherlv_3= '=' ( (lv_rotation_4_0= ruleFloat ) ) (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )?
            {
            // InternalKGraph.g:5477:2: ( (lv_propagateToChildren_0_0= 'propagate' ) )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==68) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // InternalKGraph.g:5478:1: (lv_propagateToChildren_0_0= 'propagate' )
                    {
                    // InternalKGraph.g:5478:1: (lv_propagateToChildren_0_0= 'propagate' )
                    // InternalKGraph.g:5479:3: lv_propagateToChildren_0_0= 'propagate'
                    {
                    lv_propagateToChildren_0_0=(Token)match(input,68,FollowSets000.FOLLOW_112); 

                            newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKRotationAccess().getPropagateToChildrenPropagateKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRotationRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5492:3: ( (lv_selection_1_0= 'selection' ) )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==69) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // InternalKGraph.g:5493:1: (lv_selection_1_0= 'selection' )
                    {
                    // InternalKGraph.g:5493:1: (lv_selection_1_0= 'selection' )
                    // InternalKGraph.g:5494:3: lv_selection_1_0= 'selection'
                    {
                    lv_selection_1_0=(Token)match(input,69,FollowSets000.FOLLOW_113); 

                            newLeafNode(lv_selection_1_0, grammarAccess.getKRotationAccess().getSelectionSelectionKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRotationRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_2, grammarAccess.getKRotationAccess().getRotationKeyword_2());
                
            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_28); 

                	newLeafNode(otherlv_3, grammarAccess.getKRotationAccess().getEqualsSignKeyword_3());
                
            // InternalKGraph.g:5515:1: ( (lv_rotation_4_0= ruleFloat ) )
            // InternalKGraph.g:5516:1: (lv_rotation_4_0= ruleFloat )
            {
            // InternalKGraph.g:5516:1: (lv_rotation_4_0= ruleFloat )
            // InternalKGraph.g:5517:3: lv_rotation_4_0= ruleFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRotationAccess().getRotationFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_52);
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

            // InternalKGraph.g:5533:2: (otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')' )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==24) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // InternalKGraph.g:5533:4: otherlv_5= '(' otherlv_6= 'anchor' otherlv_7= '=' ( (lv_rotationAnchor_8_0= ruleKPosition ) ) otherlv_9= ')'
                    {
                    otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_114); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRotationAccess().getLeftParenthesisKeyword_5_0());
                        
                    otherlv_6=(Token)match(input,88,FollowSets000.FOLLOW_22); 

                        	newLeafNode(otherlv_6, grammarAccess.getKRotationAccess().getAnchorKeyword_5_1());
                        
                    otherlv_7=(Token)match(input,30,FollowSets000.FOLLOW_64); 

                        	newLeafNode(otherlv_7, grammarAccess.getKRotationAccess().getEqualsSignKeyword_5_2());
                        
                    // InternalKGraph.g:5545:1: ( (lv_rotationAnchor_8_0= ruleKPosition ) )
                    // InternalKGraph.g:5546:1: (lv_rotationAnchor_8_0= ruleKPosition )
                    {
                    // InternalKGraph.g:5546:1: (lv_rotationAnchor_8_0= ruleKPosition )
                    // InternalKGraph.g:5547:3: lv_rotationAnchor_8_0= ruleKPosition
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRotationAccess().getRotationAnchorKPositionParserRuleCall_5_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_13);
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

                    otherlv_9=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5575:1: entryRuleKShadow returns [EObject current=null] : iv_ruleKShadow= ruleKShadow EOF ;
    public final EObject entryRuleKShadow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShadow = null;


        try {
            // InternalKGraph.g:5576:2: (iv_ruleKShadow= ruleKShadow EOF )
            // InternalKGraph.g:5577:2: iv_ruleKShadow= ruleKShadow EOF
            {
             newCompositeNode(grammarAccess.getKShadowRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKShadow=ruleKShadow();

            state._fsp--;

             current =iv_ruleKShadow; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5584:1: ruleKShadow returns [EObject current=null] : ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? ) ;
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
            // InternalKGraph.g:5587:28: ( ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? ) )
            // InternalKGraph.g:5588:1: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? )
            {
            // InternalKGraph.g:5588:1: ( () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )? )
            // InternalKGraph.g:5588:2: () ( (lv_propagateToChildren_1_0= 'propagate' ) )? ( (lv_selection_2_0= 'selection' ) )? otherlv_3= 'shadow' otherlv_4= '=' ( (lv_color_5_0= ruleKColor ) )? (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )?
            {
            // InternalKGraph.g:5588:2: ()
            // InternalKGraph.g:5589:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKShadowAccess().getKShadowAction_0(),
                        current);
                

            }

            // InternalKGraph.g:5594:2: ( (lv_propagateToChildren_1_0= 'propagate' ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==68) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // InternalKGraph.g:5595:1: (lv_propagateToChildren_1_0= 'propagate' )
                    {
                    // InternalKGraph.g:5595:1: (lv_propagateToChildren_1_0= 'propagate' )
                    // InternalKGraph.g:5596:3: lv_propagateToChildren_1_0= 'propagate'
                    {
                    lv_propagateToChildren_1_0=(Token)match(input,68,FollowSets000.FOLLOW_115); 

                            newLeafNode(lv_propagateToChildren_1_0, grammarAccess.getKShadowAccess().getPropagateToChildrenPropagateKeyword_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKShadowRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagate");
                    	    

                    }


                    }
                    break;

            }

            // InternalKGraph.g:5609:3: ( (lv_selection_2_0= 'selection' ) )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==69) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // InternalKGraph.g:5610:1: (lv_selection_2_0= 'selection' )
                    {
                    // InternalKGraph.g:5610:1: (lv_selection_2_0= 'selection' )
                    // InternalKGraph.g:5611:3: lv_selection_2_0= 'selection'
                    {
                    lv_selection_2_0=(Token)match(input,69,FollowSets000.FOLLOW_116); 

                            newLeafNode(lv_selection_2_0, grammarAccess.getKShadowAccess().getSelectionSelectionKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKShadowRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,89,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_3, grammarAccess.getKShadowAccess().getShadowKeyword_3());
                
            otherlv_4=(Token)match(input,30,FollowSets000.FOLLOW_117); 

                	newLeafNode(otherlv_4, grammarAccess.getKShadowAccess().getEqualsSignKeyword_4());
                
            // InternalKGraph.g:5632:1: ( (lv_color_5_0= ruleKColor ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( ((LA122_0>=RULE_RED && LA122_0<=RULE_BLUE)) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // InternalKGraph.g:5633:1: (lv_color_5_0= ruleKColor )
                    {
                    // InternalKGraph.g:5633:1: (lv_color_5_0= ruleKColor )
                    // InternalKGraph.g:5634:3: lv_color_5_0= ruleKColor
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShadowAccess().getColorKColorParserRuleCall_5_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_52);
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

            // InternalKGraph.g:5650:3: (otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')' )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==24) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // InternalKGraph.g:5650:5: otherlv_6= '(' ( (lv_xOffset_7_0= ruleFloat ) ) otherlv_8= ',' ( (lv_yOffset_9_0= ruleFloat ) ) (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )? otherlv_12= ')'
                    {
                    otherlv_6=(Token)match(input,24,FollowSets000.FOLLOW_28); 

                        	newLeafNode(otherlv_6, grammarAccess.getKShadowAccess().getLeftParenthesisKeyword_6_0());
                        
                    // InternalKGraph.g:5654:1: ( (lv_xOffset_7_0= ruleFloat ) )
                    // InternalKGraph.g:5655:1: (lv_xOffset_7_0= ruleFloat )
                    {
                    // InternalKGraph.g:5655:1: (lv_xOffset_7_0= ruleFloat )
                    // InternalKGraph.g:5656:3: lv_xOffset_7_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShadowAccess().getXOffsetFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_41);
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

                    otherlv_8=(Token)match(input,47,FollowSets000.FOLLOW_28); 

                        	newLeafNode(otherlv_8, grammarAccess.getKShadowAccess().getCommaKeyword_6_2());
                        
                    // InternalKGraph.g:5676:1: ( (lv_yOffset_9_0= ruleFloat ) )
                    // InternalKGraph.g:5677:1: (lv_yOffset_9_0= ruleFloat )
                    {
                    // InternalKGraph.g:5677:1: (lv_yOffset_9_0= ruleFloat )
                    // InternalKGraph.g:5678:3: lv_yOffset_9_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShadowAccess().getYOffsetFloatParserRuleCall_6_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_54);
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

                    // InternalKGraph.g:5694:2: (otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) ) )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==47) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // InternalKGraph.g:5694:4: otherlv_10= ',' ( (lv_blur_11_0= ruleFloat ) )
                            {
                            otherlv_10=(Token)match(input,47,FollowSets000.FOLLOW_28); 

                                	newLeafNode(otherlv_10, grammarAccess.getKShadowAccess().getCommaKeyword_6_4_0());
                                
                            // InternalKGraph.g:5698:1: ( (lv_blur_11_0= ruleFloat ) )
                            // InternalKGraph.g:5699:1: (lv_blur_11_0= ruleFloat )
                            {
                            // InternalKGraph.g:5699:1: (lv_blur_11_0= ruleFloat )
                            // InternalKGraph.g:5700:3: lv_blur_11_0= ruleFloat
                            {
                             
                            	        newCompositeNode(grammarAccess.getKShadowAccess().getBlurFloatParserRuleCall_6_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_13);
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

                    otherlv_12=(Token)match(input,27,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5728:1: entryRuleKStyleRef returns [EObject current=null] : iv_ruleKStyleRef= ruleKStyleRef EOF ;
    public final EObject entryRuleKStyleRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyleRef = null;


        try {
            // InternalKGraph.g:5729:2: (iv_ruleKStyleRef= ruleKStyleRef EOF )
            // InternalKGraph.g:5730:2: iv_ruleKStyleRef= ruleKStyleRef EOF
            {
             newCompositeNode(grammarAccess.getKStyleRefRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKStyleRef=ruleKStyleRef();

            state._fsp--;

             current =iv_ruleKStyleRef; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5737:1: ruleKStyleRef returns [EObject current=null] : ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) ) ;
    public final EObject ruleKStyleRef() throws RecognitionException {
        EObject current = null;

        Token lv_selection_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:5740:28: ( ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) ) )
            // InternalKGraph.g:5741:1: ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) )
            {
            // InternalKGraph.g:5741:1: ( ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) ) )
            // InternalKGraph.g:5741:2: ( (lv_selection_0_0= 'selection' ) )? otherlv_1= 'reference' otherlv_2= '=' ( ( ruleQualifiedID ) )
            {
            // InternalKGraph.g:5741:2: ( (lv_selection_0_0= 'selection' ) )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==69) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // InternalKGraph.g:5742:1: (lv_selection_0_0= 'selection' )
                    {
                    // InternalKGraph.g:5742:1: (lv_selection_0_0= 'selection' )
                    // InternalKGraph.g:5743:3: lv_selection_0_0= 'selection'
                    {
                    lv_selection_0_0=(Token)match(input,69,FollowSets000.FOLLOW_118); 

                            newLeafNode(lv_selection_0_0, grammarAccess.getKStyleRefAccess().getSelectionSelectionKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKStyleRefRule());
                    	        }
                           		setWithLastConsumed(current, "selection", true, "selection");
                    	    

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,90,FollowSets000.FOLLOW_22); 

                	newLeafNode(otherlv_1, grammarAccess.getKStyleRefAccess().getReferenceKeyword_1());
                
            otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_10); 

                	newLeafNode(otherlv_2, grammarAccess.getKStyleRefAccess().getEqualsSignKeyword_2());
                
            // InternalKGraph.g:5764:1: ( ( ruleQualifiedID ) )
            // InternalKGraph.g:5765:1: ( ruleQualifiedID )
            {
            // InternalKGraph.g:5765:1: ( ruleQualifiedID )
            // InternalKGraph.g:5766:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKStyleRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKStyleRefAccess().getStyleHolderKStyleHolderCrossReference_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:5787:1: entryRuleKRenderingLibrary returns [EObject current=null] : iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF ;
    public final EObject entryRuleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingLibrary = null;


        try {
            // InternalKGraph.g:5788:2: (iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF )
            // InternalKGraph.g:5789:2: iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF
            {
             newCompositeNode(grammarAccess.getKRenderingLibraryRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRenderingLibrary=ruleKRenderingLibrary();

            state._fsp--;

             current =iv_ruleKRenderingLibrary; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5796:1: ruleKRenderingLibrary returns [EObject current=null] : ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' ) ;
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
            // InternalKGraph.g:5799:28: ( ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' ) )
            // InternalKGraph.g:5800:1: ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' )
            {
            // InternalKGraph.g:5800:1: ( () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}' )
            // InternalKGraph.g:5800:2: () otherlv_1= 'krenderingLibrary' (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )? otherlv_5= '{' ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )* otherlv_8= '}'
            {
            // InternalKGraph.g:5800:2: ()
            // InternalKGraph.g:5801:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingLibraryAccess().getKRenderingLibraryAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,91,FollowSets000.FOLLOW_119); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingLibraryAccess().getKrenderingLibraryKeyword_1());
                
            // InternalKGraph.g:5810:1: (otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']' )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==31) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // InternalKGraph.g:5810:3: otherlv_2= '[' ( (lv_persistentEntries_3_0= ruleProperty ) )* otherlv_4= ']'
                    {
                    otherlv_2=(Token)match(input,31,FollowSets000.FOLLOW_25); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRenderingLibraryAccess().getLeftSquareBracketKeyword_2_0());
                        
                    // InternalKGraph.g:5814:1: ( (lv_persistentEntries_3_0= ruleProperty ) )*
                    loop126:
                    do {
                        int alt126=2;
                        int LA126_0 = input.LA(1);

                        if ( (LA126_0==RULE_ID) ) {
                            alt126=1;
                        }


                        switch (alt126) {
                    	case 1 :
                    	    // InternalKGraph.g:5815:1: (lv_persistentEntries_3_0= ruleProperty )
                    	    {
                    	    // InternalKGraph.g:5815:1: (lv_persistentEntries_3_0= ruleProperty )
                    	    // InternalKGraph.g:5816:3: lv_persistentEntries_3_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getPersistentEntriesPropertyParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_25);
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

                    otherlv_4=(Token)match(input,32,FollowSets000.FOLLOW_14); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRenderingLibraryAccess().getRightSquareBracketKeyword_2_2());
                        

                    }
                    break;

            }

            otherlv_5=(Token)match(input,21,FollowSets000.FOLLOW_120); 

                	newLeafNode(otherlv_5, grammarAccess.getKRenderingLibraryAccess().getLeftCurlyBracketKeyword_3());
                
            // InternalKGraph.g:5840:1: ( ( (lv_renderings_6_0= ruleKRendering ) ) | ( (lv_renderings_7_0= ruleKStyleHolder ) ) )*
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
            	    // InternalKGraph.g:5840:2: ( (lv_renderings_6_0= ruleKRendering ) )
            	    {
            	    // InternalKGraph.g:5840:2: ( (lv_renderings_6_0= ruleKRendering ) )
            	    // InternalKGraph.g:5841:1: (lv_renderings_6_0= ruleKRendering )
            	    {
            	    // InternalKGraph.g:5841:1: (lv_renderings_6_0= ruleKRendering )
            	    // InternalKGraph.g:5842:3: lv_renderings_6_0= ruleKRendering
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_4_0_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_120);
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
            	    // InternalKGraph.g:5859:6: ( (lv_renderings_7_0= ruleKStyleHolder ) )
            	    {
            	    // InternalKGraph.g:5859:6: ( (lv_renderings_7_0= ruleKStyleHolder ) )
            	    // InternalKGraph.g:5860:1: (lv_renderings_7_0= ruleKStyleHolder )
            	    {
            	    // InternalKGraph.g:5860:1: (lv_renderings_7_0= ruleKStyleHolder )
            	    // InternalKGraph.g:5861:3: lv_renderings_7_0= ruleKStyleHolder
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKStyleHolderParserRuleCall_4_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_120);
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

            otherlv_8=(Token)match(input,22,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5889:1: entryRuleKStyleHolder returns [EObject current=null] : iv_ruleKStyleHolder= ruleKStyleHolder EOF ;
    public final EObject entryRuleKStyleHolder() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyleHolder = null;


        try {
            // InternalKGraph.g:5890:2: (iv_ruleKStyleHolder= ruleKStyleHolder EOF )
            // InternalKGraph.g:5891:2: iv_ruleKStyleHolder= ruleKStyleHolder EOF
            {
             newCompositeNode(grammarAccess.getKStyleHolderRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKStyleHolder=ruleKStyleHolder();

            state._fsp--;

             current =iv_ruleKStyleHolder; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5898:1: ruleKStyleHolder returns [EObject current=null] : ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' ) ;
    public final EObject ruleKStyleHolder() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_id_2_0 = null;

        EObject lv_styles_4_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:5901:28: ( ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' ) )
            // InternalKGraph.g:5902:1: ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' )
            {
            // InternalKGraph.g:5902:1: ( () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}' )
            // InternalKGraph.g:5902:2: () otherlv_1= 'kstylesTemplate' ( (lv_id_2_0= ruleQualifiedID ) )? otherlv_3= '{' ( (lv_styles_4_0= ruleKStyle ) )* otherlv_5= '}'
            {
            // InternalKGraph.g:5902:2: ()
            // InternalKGraph.g:5903:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStyleHolderAccess().getKStyleHolderAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,92,FollowSets000.FOLLOW_5); 

                	newLeafNode(otherlv_1, grammarAccess.getKStyleHolderAccess().getKstylesTemplateKeyword_1());
                
            // InternalKGraph.g:5912:1: ( (lv_id_2_0= ruleQualifiedID ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==RULE_ID) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // InternalKGraph.g:5913:1: (lv_id_2_0= ruleQualifiedID )
                    {
                    // InternalKGraph.g:5913:1: (lv_id_2_0= ruleQualifiedID )
                    // InternalKGraph.g:5914:3: lv_id_2_0= ruleQualifiedID
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStyleHolderAccess().getIdQualifiedIDParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_14);
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

            otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_121); 

                	newLeafNode(otherlv_3, grammarAccess.getKStyleHolderAccess().getLeftCurlyBracketKeyword_3());
                
            // InternalKGraph.g:5934:1: ( (lv_styles_4_0= ruleKStyle ) )*
            loop130:
            do {
                int alt130=2;
                int LA130_0 = input.LA(1);

                if ( ((LA130_0>=68 && LA130_0<=83)||(LA130_0>=86 && LA130_0<=87)||(LA130_0>=89 && LA130_0<=90)) ) {
                    alt130=1;
                }


                switch (alt130) {
            	case 1 :
            	    // InternalKGraph.g:5935:1: (lv_styles_4_0= ruleKStyle )
            	    {
            	    // InternalKGraph.g:5935:1: (lv_styles_4_0= ruleKStyle )
            	    // InternalKGraph.g:5936:3: lv_styles_4_0= ruleKStyle
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKStyleHolderAccess().getStylesKStyleParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_121);
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

            otherlv_5=(Token)match(input,22,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5964:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // InternalKGraph.g:5965:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // InternalKGraph.g:5966:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:5973:1: ruleKPlacement returns [EObject current=null] : this_KGridPlacement_0= ruleKGridPlacement ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:5976:28: (this_KGridPlacement_0= ruleKGridPlacement )
            // InternalKGraph.g:5978:5: this_KGridPlacement_0= ruleKGridPlacement
            {
             
                    newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall()); 
                
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:5994:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // InternalKGraph.g:5995:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // InternalKGraph.g:5996:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:6003:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) ) ;
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
            // InternalKGraph.g:6006:28: ( ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6007:1: ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6007:1: ( () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6007:2: () otherlv_1= 'grid' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6007:2: ()
            // InternalKGraph.g:6008:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,93,FollowSets000.FOLLOW_26); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getGridKeyword_1());
                
            otherlv_2=(Token)match(input,25,FollowSets000.FOLLOW_122); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementAccess().getColonKeyword_2());
                
            // InternalKGraph.g:6021:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6023:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6023:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6024:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3());
            	
            // InternalKGraph.g:6027:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )* )
            // InternalKGraph.g:6028:3: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6028:3: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) ) )*
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
            	    // InternalKGraph.g:6030:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6030:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6031:5: {...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6031:111: ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6032:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 0);
            	    	 				
            	    // InternalKGraph.g:6035:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6035:7: {...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "true");
            	    }
            	    // InternalKGraph.g:6035:16: (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6035:18: otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,94,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementAccess().getTopLeftAnchorKeyword_3_0_0());
            	        
            	    otherlv_5=(Token)match(input,30,FollowSets000.FOLLOW_64); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKGridPlacementAccess().getEqualsSignKeyword_3_0_1());
            	        
            	    // InternalKGraph.g:6043:1: ( (lv_topLeft_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6044:1: (lv_topLeft_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6044:1: (lv_topLeft_6_0= ruleKPosition )
            	    // InternalKGraph.g:6045:3: lv_topLeft_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getTopLeftKPositionParserRuleCall_3_0_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_122);
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
            	    // InternalKGraph.g:6068:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6068:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6069:5: {...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6069:111: ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6070:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 1);
            	    	 				
            	    // InternalKGraph.g:6073:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6073:7: {...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "true");
            	    }
            	    // InternalKGraph.g:6073:16: (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6073:18: otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    {
            	    otherlv_7=(Token)match(input,95,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_7, grammarAccess.getKGridPlacementAccess().getBottomRightAnchorKeyword_3_1_0());
            	        
            	    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_64); 

            	        	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementAccess().getEqualsSignKeyword_3_1_1());
            	        
            	    // InternalKGraph.g:6081:1: ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    // InternalKGraph.g:6082:1: (lv_bottomRight_9_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6082:1: (lv_bottomRight_9_0= ruleKPosition )
            	    // InternalKGraph.g:6083:3: lv_bottomRight_9_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getBottomRightKPositionParserRuleCall_3_1_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_122);
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
            	    // InternalKGraph.g:6106:4: ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6106:4: ({...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) ) )
            	    // InternalKGraph.g:6107:5: {...}? => ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:6107:111: ( ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) ) )
            	    // InternalKGraph.g:6108:6: ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementAccess().getUnorderedGroup_3(), 2);
            	    	 				
            	    // InternalKGraph.g:6111:6: ({...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) ) )
            	    // InternalKGraph.g:6111:7: {...}? => (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacement", "true");
            	    }
            	    // InternalKGraph.g:6111:16: (otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) ) )
            	    // InternalKGraph.g:6111:18: otherlv_10= 'columns' otherlv_11= '=' ( (lv_numColumns_12_0= RULE_NATURAL ) )
            	    {
            	    otherlv_10=(Token)match(input,96,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_10, grammarAccess.getKGridPlacementAccess().getColumnsKeyword_3_2_0());
            	        
            	    otherlv_11=(Token)match(input,30,FollowSets000.FOLLOW_123); 

            	        	newLeafNode(otherlv_11, grammarAccess.getKGridPlacementAccess().getEqualsSignKeyword_3_2_1());
            	        
            	    // InternalKGraph.g:6119:1: ( (lv_numColumns_12_0= RULE_NATURAL ) )
            	    // InternalKGraph.g:6120:1: (lv_numColumns_12_0= RULE_NATURAL )
            	    {
            	    // InternalKGraph.g:6120:1: (lv_numColumns_12_0= RULE_NATURAL )
            	    // InternalKGraph.g:6121:3: lv_numColumns_12_0= RULE_NATURAL
            	    {
            	    lv_numColumns_12_0=(Token)match(input,RULE_NATURAL,FollowSets000.FOLLOW_122); 

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
    // InternalKGraph.g:6159:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // InternalKGraph.g:6160:2: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // InternalKGraph.g:6161:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:6168:1: ruleKPlacementData returns [EObject current=null] : (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KAreaPlacementData_0 = null;

        EObject this_KPointPlacementData_1 = null;

        EObject this_KGridPlacementData_2 = null;

        EObject this_KDecoratorPlacementData_3 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:6171:28: ( (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData ) )
            // InternalKGraph.g:6172:1: (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData )
            {
            // InternalKGraph.g:6172:1: (this_KAreaPlacementData_0= ruleKAreaPlacementData | this_KPointPlacementData_1= ruleKPointPlacementData | this_KGridPlacementData_2= ruleKGridPlacementData | this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData )
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
                    // InternalKGraph.g:6173:5: this_KAreaPlacementData_0= ruleKAreaPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKAreaPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_KAreaPlacementData_0=ruleKAreaPlacementData();

                    state._fsp--;

                     
                            current = this_KAreaPlacementData_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:6183:5: this_KPointPlacementData_1= ruleKPointPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPointPlacementDataParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_KPointPlacementData_1=ruleKPointPlacementData();

                    state._fsp--;

                     
                            current = this_KPointPlacementData_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:6193:5: this_KGridPlacementData_2= ruleKGridPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_KGridPlacementData_2=ruleKGridPlacementData();

                    state._fsp--;

                     
                            current = this_KGridPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:6203:5: this_KDecoratorPlacementData_3= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:6219:1: entryRuleKAreaPlacementData returns [EObject current=null] : iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF ;
    public final EObject entryRuleKAreaPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKAreaPlacementData = null;


        try {
            // InternalKGraph.g:6220:2: (iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF )
            // InternalKGraph.g:6221:2: iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKAreaPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKAreaPlacementData=ruleKAreaPlacementData();

            state._fsp--;

             current =iv_ruleKAreaPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:6228:1: ruleKAreaPlacementData returns [EObject current=null] : ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) ) ;
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
            // InternalKGraph.g:6231:28: ( ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6232:1: ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6232:1: ( () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6232:2: () otherlv_1= 'areaData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6232:2: ()
            // InternalKGraph.g:6233:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKAreaPlacementDataAccess().getKAreaPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,97,FollowSets000.FOLLOW_26); 

                	newLeafNode(otherlv_1, grammarAccess.getKAreaPlacementDataAccess().getAreaDataKeyword_1());
                
            otherlv_2=(Token)match(input,25,FollowSets000.FOLLOW_124); 

                	newLeafNode(otherlv_2, grammarAccess.getKAreaPlacementDataAccess().getColonKeyword_2());
                
            // InternalKGraph.g:6246:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6248:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6248:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6249:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3());
            	
            // InternalKGraph.g:6252:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )* )
            // InternalKGraph.g:6253:3: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6253:3: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) )*
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
            	    // InternalKGraph.g:6255:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6255:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6256:5: {...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6256:115: ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6257:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    	 				
            	    // InternalKGraph.g:6260:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6260:7: {...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6260:16: (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6260:18: otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,94,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_4, grammarAccess.getKAreaPlacementDataAccess().getTopLeftAnchorKeyword_3_0_0());
            	        
            	    otherlv_5=(Token)match(input,30,FollowSets000.FOLLOW_64); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKAreaPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	        
            	    // InternalKGraph.g:6268:1: ( (lv_topLeft_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6269:1: (lv_topLeft_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6269:1: (lv_topLeft_6_0= ruleKPosition )
            	    // InternalKGraph.g:6270:3: lv_topLeft_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKAreaPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_124);
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
            	    // InternalKGraph.g:6293:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6293:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6294:5: {...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6294:115: ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6295:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKAreaPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    	 				
            	    // InternalKGraph.g:6298:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6298:7: {...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKAreaPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6298:16: (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6298:18: otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    {
            	    otherlv_7=(Token)match(input,95,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_7, grammarAccess.getKAreaPlacementDataAccess().getBottomRightAnchorKeyword_3_1_0());
            	        
            	    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_64); 

            	        	newLeafNode(otherlv_8, grammarAccess.getKAreaPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	        
            	    // InternalKGraph.g:6306:1: ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    // InternalKGraph.g:6307:1: (lv_bottomRight_9_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6307:1: (lv_bottomRight_9_0= ruleKPosition )
            	    // InternalKGraph.g:6308:3: lv_bottomRight_9_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKAreaPlacementDataAccess().getBottomRightKPositionParserRuleCall_3_1_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_124);
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
    // InternalKGraph.g:6346:1: entryRuleKPointPlacementData returns [EObject current=null] : iv_ruleKPointPlacementData= ruleKPointPlacementData EOF ;
    public final EObject entryRuleKPointPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPointPlacementData = null;


        try {
            // InternalKGraph.g:6347:2: (iv_ruleKPointPlacementData= ruleKPointPlacementData EOF )
            // InternalKGraph.g:6348:2: iv_ruleKPointPlacementData= ruleKPointPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPointPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPointPlacementData=ruleKPointPlacementData();

            state._fsp--;

             current =iv_ruleKPointPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:6355:1: ruleKPointPlacementData returns [EObject current=null] : ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) ;
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
            // InternalKGraph.g:6358:28: ( ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6359:1: ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6359:1: ( () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6359:2: () otherlv_1= 'pointData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6359:2: ()
            // InternalKGraph.g:6360:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointPlacementDataAccess().getKPointPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,98,FollowSets000.FOLLOW_26); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointPlacementDataAccess().getPointDataKeyword_1());
                
            otherlv_2=(Token)match(input,25,FollowSets000.FOLLOW_125); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointPlacementDataAccess().getColonKeyword_2());
                
            // InternalKGraph.g:6373:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6375:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6375:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6376:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3());
            	
            // InternalKGraph.g:6379:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )* )
            // InternalKGraph.g:6380:3: ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6380:3: ( ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) ) )*
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
            	    // InternalKGraph.g:6382:4: ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6382:4: ({...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6383:5: {...}? => ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6383:116: ( ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6384:6: ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    	 				
            	    // InternalKGraph.g:6387:6: ({...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6387:7: {...}? => (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6387:16: (otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6387:18: otherlv_4= 'referencePoint' otherlv_5= '=' ( (lv_referencePoint_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,99,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_4, grammarAccess.getKPointPlacementDataAccess().getReferencePointKeyword_3_0_0());
            	        
            	    otherlv_5=(Token)match(input,30,FollowSets000.FOLLOW_64); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	        
            	    // InternalKGraph.g:6395:1: ( (lv_referencePoint_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6396:1: (lv_referencePoint_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6396:1: (lv_referencePoint_6_0= ruleKPosition )
            	    // InternalKGraph.g:6397:3: lv_referencePoint_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getReferencePointKPositionParserRuleCall_3_0_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_125);
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
            	    // InternalKGraph.g:6420:4: ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6420:4: ({...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6421:5: {...}? => ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6421:116: ( ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6422:6: ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    	 				
            	    // InternalKGraph.g:6425:6: ({...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6425:7: {...}? => (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6425:16: (otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6425:18: otherlv_7= 'minimalWidth' otherlv_8= '=' ( (lv_minWidth_9_0= ruleFloat ) )
            	    {
            	    otherlv_7=(Token)match(input,100,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_7, grammarAccess.getKPointPlacementDataAccess().getMinimalWidthKeyword_3_1_0());
            	        
            	    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_8, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	        
            	    // InternalKGraph.g:6433:1: ( (lv_minWidth_9_0= ruleFloat ) )
            	    // InternalKGraph.g:6434:1: (lv_minWidth_9_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6434:1: (lv_minWidth_9_0= ruleFloat )
            	    // InternalKGraph.g:6435:3: lv_minWidth_9_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getMinWidthFloatParserRuleCall_3_1_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_125);
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
            	    // InternalKGraph.g:6458:4: ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6458:4: ({...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6459:5: {...}? => ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:6459:116: ( ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6460:6: ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 2);
            	    	 				
            	    // InternalKGraph.g:6463:6: ({...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6463:7: {...}? => (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6463:16: (otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6463:18: otherlv_10= 'minimalHeight' otherlv_11= '=' ( (lv_minHeight_12_0= ruleFloat ) )
            	    {
            	    otherlv_10=(Token)match(input,101,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_10, grammarAccess.getKPointPlacementDataAccess().getMinimalHeightKeyword_3_2_0());
            	        
            	    otherlv_11=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_11, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_2_1());
            	        
            	    // InternalKGraph.g:6471:1: ( (lv_minHeight_12_0= ruleFloat ) )
            	    // InternalKGraph.g:6472:1: (lv_minHeight_12_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6472:1: (lv_minHeight_12_0= ruleFloat )
            	    // InternalKGraph.g:6473:3: lv_minHeight_12_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getMinHeightFloatParserRuleCall_3_2_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_125);
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
            	    // InternalKGraph.g:6496:4: ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6496:4: ({...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) ) )
            	    // InternalKGraph.g:6497:5: {...}? => ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalKGraph.g:6497:116: ( ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) ) )
            	    // InternalKGraph.g:6498:6: ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 3);
            	    	 				
            	    // InternalKGraph.g:6501:6: ({...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) ) )
            	    // InternalKGraph.g:6501:7: {...}? => (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6501:16: (otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) ) )
            	    // InternalKGraph.g:6501:18: otherlv_13= 'horizontalAlignment' otherlv_14= '=' ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) )
            	    {
            	    otherlv_13=(Token)match(input,102,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_13, grammarAccess.getKPointPlacementDataAccess().getHorizontalAlignmentKeyword_3_3_0());
            	        
            	    otherlv_14=(Token)match(input,30,FollowSets000.FOLLOW_92); 

            	        	newLeafNode(otherlv_14, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_3_1());
            	        
            	    // InternalKGraph.g:6509:1: ( (lv_horizontalAlignment_15_0= ruleHorizontalAlignment ) )
            	    // InternalKGraph.g:6510:1: (lv_horizontalAlignment_15_0= ruleHorizontalAlignment )
            	    {
            	    // InternalKGraph.g:6510:1: (lv_horizontalAlignment_15_0= ruleHorizontalAlignment )
            	    // InternalKGraph.g:6511:3: lv_horizontalAlignment_15_0= ruleHorizontalAlignment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_3_3_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_125);
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
            	    // InternalKGraph.g:6534:4: ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6534:4: ({...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) ) )
            	    // InternalKGraph.g:6535:5: {...}? => ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalKGraph.g:6535:116: ( ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) ) )
            	    // InternalKGraph.g:6536:6: ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 4);
            	    	 				
            	    // InternalKGraph.g:6539:6: ({...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) ) )
            	    // InternalKGraph.g:6539:7: {...}? => (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6539:16: (otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) ) )
            	    // InternalKGraph.g:6539:18: otherlv_16= 'verticalAlignment' otherlv_17= '=' ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) )
            	    {
            	    otherlv_16=(Token)match(input,103,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_16, grammarAccess.getKPointPlacementDataAccess().getVerticalAlignmentKeyword_3_4_0());
            	        
            	    otherlv_17=(Token)match(input,30,FollowSets000.FOLLOW_95); 

            	        	newLeafNode(otherlv_17, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_4_1());
            	        
            	    // InternalKGraph.g:6547:1: ( (lv_verticalAlignment_18_0= ruleVerticalAlignment ) )
            	    // InternalKGraph.g:6548:1: (lv_verticalAlignment_18_0= ruleVerticalAlignment )
            	    {
            	    // InternalKGraph.g:6548:1: (lv_verticalAlignment_18_0= ruleVerticalAlignment )
            	    // InternalKGraph.g:6549:3: lv_verticalAlignment_18_0= ruleVerticalAlignment
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_3_4_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_125);
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
            	    // InternalKGraph.g:6572:4: ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6572:4: ({...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6573:5: {...}? => ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 5)");
            	    }
            	    // InternalKGraph.g:6573:116: ( ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6574:6: ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 5);
            	    	 				
            	    // InternalKGraph.g:6577:6: ({...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6577:7: {...}? => (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6577:16: (otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6577:18: otherlv_19= 'horizontalMargin' otherlv_20= '=' ( (lv_horizontalMargin_21_0= ruleFloat ) )
            	    {
            	    otherlv_19=(Token)match(input,104,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_19, grammarAccess.getKPointPlacementDataAccess().getHorizontalMarginKeyword_3_5_0());
            	        
            	    otherlv_20=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_20, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_5_1());
            	        
            	    // InternalKGraph.g:6585:1: ( (lv_horizontalMargin_21_0= ruleFloat ) )
            	    // InternalKGraph.g:6586:1: (lv_horizontalMargin_21_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6586:1: (lv_horizontalMargin_21_0= ruleFloat )
            	    // InternalKGraph.g:6587:3: lv_horizontalMargin_21_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getHorizontalMarginFloatParserRuleCall_3_5_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_125);
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
            	    // InternalKGraph.g:6610:4: ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6610:4: ({...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6611:5: {...}? => ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 6)");
            	    }
            	    // InternalKGraph.g:6611:116: ( ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6612:6: ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKPointPlacementDataAccess().getUnorderedGroup_3(), 6);
            	    	 				
            	    // InternalKGraph.g:6615:6: ({...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6615:7: {...}? => (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKPointPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6615:16: (otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6615:18: otherlv_22= 'verticalMargin' otherlv_23= '=' ( (lv_verticalMargin_24_0= ruleFloat ) )
            	    {
            	    otherlv_22=(Token)match(input,105,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_22, grammarAccess.getKPointPlacementDataAccess().getVerticalMarginKeyword_3_6_0());
            	        
            	    otherlv_23=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_23, grammarAccess.getKPointPlacementDataAccess().getEqualsSignKeyword_3_6_1());
            	        
            	    // InternalKGraph.g:6623:1: ( (lv_verticalMargin_24_0= ruleFloat ) )
            	    // InternalKGraph.g:6624:1: (lv_verticalMargin_24_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6624:1: (lv_verticalMargin_24_0= ruleFloat )
            	    // InternalKGraph.g:6625:3: lv_verticalMargin_24_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getVerticalMarginFloatParserRuleCall_3_6_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_125);
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
    // InternalKGraph.g:6663:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // InternalKGraph.g:6664:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // InternalKGraph.g:6665:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:6672:1: ruleKGridPlacementData returns [EObject current=null] : ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) ;
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
            // InternalKGraph.g:6675:28: ( ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6676:1: ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6676:1: ( () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6676:2: () otherlv_1= 'gridData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6676:2: ()
            // InternalKGraph.g:6677:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,106,FollowSets000.FOLLOW_26); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getGridDataKeyword_1());
                
            otherlv_2=(Token)match(input,25,FollowSets000.FOLLOW_126); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getColonKeyword_2());
                
            // InternalKGraph.g:6690:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6692:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6692:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6693:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3());
            	
            // InternalKGraph.g:6696:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            // InternalKGraph.g:6697:3: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6697:3: ( ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) ) )*
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
            	    // InternalKGraph.g:6699:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6699:4: ({...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6700:5: {...}? => ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6700:115: ( ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6701:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    	 				
            	    // InternalKGraph.g:6704:6: ({...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6704:7: {...}? => (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6704:16: (otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6704:18: otherlv_4= 'topLeftAnchor' otherlv_5= '=' ( (lv_topLeft_6_0= ruleKPosition ) )
            	    {
            	    otherlv_4=(Token)match(input,94,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getTopLeftAnchorKeyword_3_0_0());
            	        
            	    otherlv_5=(Token)match(input,30,FollowSets000.FOLLOW_64); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	        
            	    // InternalKGraph.g:6712:1: ( (lv_topLeft_6_0= ruleKPosition ) )
            	    // InternalKGraph.g:6713:1: (lv_topLeft_6_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6713:1: (lv_topLeft_6_0= ruleKPosition )
            	    // InternalKGraph.g:6714:3: lv_topLeft_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_126);
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
            	    // InternalKGraph.g:6737:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6737:4: ({...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) ) )
            	    // InternalKGraph.g:6738:5: {...}? => ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:6738:115: ( ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) ) )
            	    // InternalKGraph.g:6739:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    	 				
            	    // InternalKGraph.g:6742:6: ({...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) ) )
            	    // InternalKGraph.g:6742:7: {...}? => (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6742:16: (otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) ) )
            	    // InternalKGraph.g:6742:18: otherlv_7= 'bottomRightAnchor' otherlv_8= '=' ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    {
            	    otherlv_7=(Token)match(input,95,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_7, grammarAccess.getKGridPlacementDataAccess().getBottomRightAnchorKeyword_3_1_0());
            	        
            	    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_64); 

            	        	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	        
            	    // InternalKGraph.g:6750:1: ( (lv_bottomRight_9_0= ruleKPosition ) )
            	    // InternalKGraph.g:6751:1: (lv_bottomRight_9_0= ruleKPosition )
            	    {
            	    // InternalKGraph.g:6751:1: (lv_bottomRight_9_0= ruleKPosition )
            	    // InternalKGraph.g:6752:3: lv_bottomRight_9_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getBottomRightKPositionParserRuleCall_3_1_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_126);
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
            	    // InternalKGraph.g:6775:4: ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6775:4: ({...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6776:5: {...}? => ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:6776:115: ( ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6777:6: ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 2);
            	    	 				
            	    // InternalKGraph.g:6780:6: ({...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6780:7: {...}? => (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6780:16: (otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6780:18: otherlv_10= 'minCellWidth' otherlv_11= '=' ( (lv_minCellWidth_12_0= ruleFloat ) )
            	    {
            	    otherlv_10=(Token)match(input,107,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_10, grammarAccess.getKGridPlacementDataAccess().getMinCellWidthKeyword_3_2_0());
            	        
            	    otherlv_11=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_11, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_2_1());
            	        
            	    // InternalKGraph.g:6788:1: ( (lv_minCellWidth_12_0= ruleFloat ) )
            	    // InternalKGraph.g:6789:1: (lv_minCellWidth_12_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6789:1: (lv_minCellWidth_12_0= ruleFloat )
            	    // InternalKGraph.g:6790:3: lv_minCellWidth_12_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMinCellWidthFloatParserRuleCall_3_2_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_126);
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
            	    // InternalKGraph.g:6813:4: ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6813:4: ({...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6814:5: {...}? => ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalKGraph.g:6814:115: ( ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6815:6: ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 3);
            	    	 				
            	    // InternalKGraph.g:6818:6: ({...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6818:7: {...}? => (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6818:16: (otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6818:18: otherlv_13= 'minCellHeight' otherlv_14= '=' ( (lv_minCellHeight_15_0= ruleFloat ) )
            	    {
            	    otherlv_13=(Token)match(input,108,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_13, grammarAccess.getKGridPlacementDataAccess().getMinCellHeightKeyword_3_3_0());
            	        
            	    otherlv_14=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_14, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_3_1());
            	        
            	    // InternalKGraph.g:6826:1: ( (lv_minCellHeight_15_0= ruleFloat ) )
            	    // InternalKGraph.g:6827:1: (lv_minCellHeight_15_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6827:1: (lv_minCellHeight_15_0= ruleFloat )
            	    // InternalKGraph.g:6828:3: lv_minCellHeight_15_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMinCellHeightFloatParserRuleCall_3_3_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_126);
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
            	    // InternalKGraph.g:6851:4: ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6851:4: ({...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) ) )
            	    // InternalKGraph.g:6852:5: {...}? => ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalKGraph.g:6852:115: ( ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) ) )
            	    // InternalKGraph.g:6853:6: ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 4);
            	    	 				
            	    // InternalKGraph.g:6856:6: ({...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) ) )
            	    // InternalKGraph.g:6856:7: {...}? => (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6856:16: (otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) ) )
            	    // InternalKGraph.g:6856:18: otherlv_16= 'flexibleWidth' otherlv_17= '=' ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) )
            	    {
            	    otherlv_16=(Token)match(input,109,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_16, grammarAccess.getKGridPlacementDataAccess().getFlexibleWidthKeyword_3_4_0());
            	        
            	    otherlv_17=(Token)match(input,30,FollowSets000.FOLLOW_55); 

            	        	newLeafNode(otherlv_17, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_4_1());
            	        
            	    // InternalKGraph.g:6864:1: ( (lv_flexibleWidth_18_0= RULE_BOOLEAN ) )
            	    // InternalKGraph.g:6865:1: (lv_flexibleWidth_18_0= RULE_BOOLEAN )
            	    {
            	    // InternalKGraph.g:6865:1: (lv_flexibleWidth_18_0= RULE_BOOLEAN )
            	    // InternalKGraph.g:6866:3: lv_flexibleWidth_18_0= RULE_BOOLEAN
            	    {
            	    lv_flexibleWidth_18_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_126); 

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
            	    // InternalKGraph.g:6889:4: ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6889:4: ({...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) ) )
            	    // InternalKGraph.g:6890:5: {...}? => ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 5)");
            	    }
            	    // InternalKGraph.g:6890:115: ( ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) ) )
            	    // InternalKGraph.g:6891:6: ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKGridPlacementDataAccess().getUnorderedGroup_3(), 5);
            	    	 				
            	    // InternalKGraph.g:6894:6: ({...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) ) )
            	    // InternalKGraph.g:6894:7: {...}? => (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKGridPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6894:16: (otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) ) )
            	    // InternalKGraph.g:6894:18: otherlv_19= 'flexibleHeight' otherlv_20= '=' ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) )
            	    {
            	    otherlv_19=(Token)match(input,110,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_19, grammarAccess.getKGridPlacementDataAccess().getFlexibleHeightKeyword_3_5_0());
            	        
            	    otherlv_20=(Token)match(input,30,FollowSets000.FOLLOW_55); 

            	        	newLeafNode(otherlv_20, grammarAccess.getKGridPlacementDataAccess().getEqualsSignKeyword_3_5_1());
            	        
            	    // InternalKGraph.g:6902:1: ( (lv_flexibleHeight_21_0= RULE_BOOLEAN ) )
            	    // InternalKGraph.g:6903:1: (lv_flexibleHeight_21_0= RULE_BOOLEAN )
            	    {
            	    // InternalKGraph.g:6903:1: (lv_flexibleHeight_21_0= RULE_BOOLEAN )
            	    // InternalKGraph.g:6904:3: lv_flexibleHeight_21_0= RULE_BOOLEAN
            	    {
            	    lv_flexibleHeight_21_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_126); 

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
    // InternalKGraph.g:6942:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // InternalKGraph.g:6943:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // InternalKGraph.g:6944:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:6951:1: ruleKDecoratorPlacementData returns [EObject current=null] : ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) ;
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
            // InternalKGraph.g:6954:28: ( ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) ) )
            // InternalKGraph.g:6955:1: ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            {
            // InternalKGraph.g:6955:1: ( () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) ) )
            // InternalKGraph.g:6955:2: () otherlv_1= 'decoratorData' otherlv_2= ':' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            {
            // InternalKGraph.g:6955:2: ()
            // InternalKGraph.g:6956:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKDecoratorPlacementDataAccess().getKDecoratorPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,111,FollowSets000.FOLLOW_26); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getDecoratorDataKeyword_1());
                
            otherlv_2=(Token)match(input,25,FollowSets000.FOLLOW_127); 

                	newLeafNode(otherlv_2, grammarAccess.getKDecoratorPlacementDataAccess().getColonKeyword_2());
                
            // InternalKGraph.g:6969:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) ) )
            // InternalKGraph.g:6971:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            {
            // InternalKGraph.g:6971:1: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* ) )
            // InternalKGraph.g:6972:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3());
            	
            // InternalKGraph.g:6975:2: ( ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )* )
            // InternalKGraph.g:6976:3: ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )*
            {
            // InternalKGraph.g:6976:3: ( ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) ) )*
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
            	    // InternalKGraph.g:6978:4: ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:6978:4: ({...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:6979:5: {...}? => ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalKGraph.g:6979:120: ( ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:6980:6: ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 0);
            	    	 				
            	    // InternalKGraph.g:6983:6: ({...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:6983:7: {...}? => (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:6983:16: (otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) ) )
            	    // InternalKGraph.g:6983:18: otherlv_4= 'xoffset' otherlv_5= '=' ( (lv_xOffset_6_0= ruleFloat ) )
            	    {
            	    otherlv_4=(Token)match(input,112,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_4, grammarAccess.getKDecoratorPlacementDataAccess().getXoffsetKeyword_3_0_0());
            	        
            	    otherlv_5=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_0_1());
            	        
            	    // InternalKGraph.g:6991:1: ( (lv_xOffset_6_0= ruleFloat ) )
            	    // InternalKGraph.g:6992:1: (lv_xOffset_6_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:6992:1: (lv_xOffset_6_0= ruleFloat )
            	    // InternalKGraph.g:6993:3: lv_xOffset_6_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetFloatParserRuleCall_3_0_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_127);
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
            	    // InternalKGraph.g:7016:4: ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7016:4: ({...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7017:5: {...}? => ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalKGraph.g:7017:120: ( ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7018:6: ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 1);
            	    	 				
            	    // InternalKGraph.g:7021:6: ({...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7021:7: {...}? => (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7021:16: (otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7021:18: otherlv_7= 'yoffset' otherlv_8= '=' ( (lv_yOffset_9_0= ruleFloat ) )
            	    {
            	    otherlv_7=(Token)match(input,113,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYoffsetKeyword_3_1_0());
            	        
            	    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_8, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_1_1());
            	        
            	    // InternalKGraph.g:7029:1: ( (lv_yOffset_9_0= ruleFloat ) )
            	    // InternalKGraph.g:7030:1: (lv_yOffset_9_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7030:1: (lv_yOffset_9_0= ruleFloat )
            	    // InternalKGraph.g:7031:3: lv_yOffset_9_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetFloatParserRuleCall_3_1_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_127);
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
            	    // InternalKGraph.g:7054:4: ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7054:4: ({...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7055:5: {...}? => ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalKGraph.g:7055:120: ( ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7056:6: ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 2);
            	    	 				
            	    // InternalKGraph.g:7059:6: ({...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7059:7: {...}? => (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7059:16: (otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7059:18: otherlv_10= 'width' otherlv_11= '=' ( (lv_width_12_0= ruleFloat ) )
            	    {
            	    otherlv_10=(Token)match(input,37,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_10, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_3_2_0());
            	        
            	    otherlv_11=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_2_1());
            	        
            	    // InternalKGraph.g:7067:1: ( (lv_width_12_0= ruleFloat ) )
            	    // InternalKGraph.g:7068:1: (lv_width_12_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7068:1: (lv_width_12_0= ruleFloat )
            	    // InternalKGraph.g:7069:3: lv_width_12_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthFloatParserRuleCall_3_2_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_127);
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
            	    // InternalKGraph.g:7092:4: ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7092:4: ({...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7093:5: {...}? => ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalKGraph.g:7093:120: ( ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7094:6: ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 3);
            	    	 				
            	    // InternalKGraph.g:7097:6: ({...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7097:7: {...}? => (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7097:16: (otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7097:18: otherlv_13= 'height' otherlv_14= '=' ( (lv_height_15_0= ruleFloat ) )
            	    {
            	    otherlv_13=(Token)match(input,38,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_13, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_3_3_0());
            	        
            	    otherlv_14=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_14, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_3_1());
            	        
            	    // InternalKGraph.g:7105:1: ( (lv_height_15_0= ruleFloat ) )
            	    // InternalKGraph.g:7106:1: (lv_height_15_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7106:1: (lv_height_15_0= ruleFloat )
            	    // InternalKGraph.g:7107:3: lv_height_15_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightFloatParserRuleCall_3_3_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_127);
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
            	    // InternalKGraph.g:7130:4: ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7130:4: ({...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7131:5: {...}? => ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalKGraph.g:7131:120: ( ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7132:6: ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 4);
            	    	 				
            	    // InternalKGraph.g:7135:6: ({...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7135:7: {...}? => (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7135:16: (otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7135:18: otherlv_16= 'relativePos' otherlv_17= '=' ( (lv_relative_18_0= ruleFloat ) )
            	    {
            	    otherlv_16=(Token)match(input,114,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_16, grammarAccess.getKDecoratorPlacementDataAccess().getRelativePosKeyword_3_4_0());
            	        
            	    otherlv_17=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_17, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_4_1());
            	        
            	    // InternalKGraph.g:7143:1: ( (lv_relative_18_0= ruleFloat ) )
            	    // InternalKGraph.g:7144:1: (lv_relative_18_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7144:1: (lv_relative_18_0= ruleFloat )
            	    // InternalKGraph.g:7145:3: lv_relative_18_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getRelativeFloatParserRuleCall_3_4_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_127);
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
            	    // InternalKGraph.g:7168:4: ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7168:4: ({...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) ) )
            	    // InternalKGraph.g:7169:5: {...}? => ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 5)");
            	    }
            	    // InternalKGraph.g:7169:120: ( ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) ) )
            	    // InternalKGraph.g:7170:6: ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 5);
            	    	 				
            	    // InternalKGraph.g:7173:6: ({...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) ) )
            	    // InternalKGraph.g:7173:7: {...}? => (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7173:16: (otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) ) )
            	    // InternalKGraph.g:7173:18: otherlv_19= 'absolutePos' otherlv_20= '=' ( (lv_absolute_21_0= ruleFloat ) )
            	    {
            	    otherlv_19=(Token)match(input,115,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_19, grammarAccess.getKDecoratorPlacementDataAccess().getAbsolutePosKeyword_3_5_0());
            	        
            	    otherlv_20=(Token)match(input,30,FollowSets000.FOLLOW_28); 

            	        	newLeafNode(otherlv_20, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_5_1());
            	        
            	    // InternalKGraph.g:7181:1: ( (lv_absolute_21_0= ruleFloat ) )
            	    // InternalKGraph.g:7182:1: (lv_absolute_21_0= ruleFloat )
            	    {
            	    // InternalKGraph.g:7182:1: (lv_absolute_21_0= ruleFloat )
            	    // InternalKGraph.g:7183:3: lv_absolute_21_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getAbsoluteFloatParserRuleCall_3_5_2_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_127);
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
            	    // InternalKGraph.g:7206:4: ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) )
            	    {
            	    // InternalKGraph.g:7206:4: ({...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) ) )
            	    // InternalKGraph.g:7207:5: {...}? => ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "getUnorderedGroupHelper().canSelect(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 6)");
            	    }
            	    // InternalKGraph.g:7207:120: ( ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) ) )
            	    // InternalKGraph.g:7208:6: ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getKDecoratorPlacementDataAccess().getUnorderedGroup_3(), 6);
            	    	 				
            	    // InternalKGraph.g:7211:6: ({...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) ) )
            	    // InternalKGraph.g:7211:7: {...}? => (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleKDecoratorPlacementData", "true");
            	    }
            	    // InternalKGraph.g:7211:16: (otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) ) )
            	    // InternalKGraph.g:7211:18: otherlv_22= 'rotateWithLine' otherlv_23= '=' ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) )
            	    {
            	    otherlv_22=(Token)match(input,116,FollowSets000.FOLLOW_22); 

            	        	newLeafNode(otherlv_22, grammarAccess.getKDecoratorPlacementDataAccess().getRotateWithLineKeyword_3_6_0());
            	        
            	    otherlv_23=(Token)match(input,30,FollowSets000.FOLLOW_55); 

            	        	newLeafNode(otherlv_23, grammarAccess.getKDecoratorPlacementDataAccess().getEqualsSignKeyword_3_6_1());
            	        
            	    // InternalKGraph.g:7219:1: ( (lv_rotateWithLine_24_0= RULE_BOOLEAN ) )
            	    // InternalKGraph.g:7220:1: (lv_rotateWithLine_24_0= RULE_BOOLEAN )
            	    {
            	    // InternalKGraph.g:7220:1: (lv_rotateWithLine_24_0= RULE_BOOLEAN )
            	    // InternalKGraph.g:7221:3: lv_rotateWithLine_24_0= RULE_BOOLEAN
            	    {
            	    lv_rotateWithLine_24_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_127); 

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
    // InternalKGraph.g:7259:1: entryRuleKAction returns [EObject current=null] : iv_ruleKAction= ruleKAction EOF ;
    public final EObject entryRuleKAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKAction = null;


        try {
            // InternalKGraph.g:7260:2: (iv_ruleKAction= ruleKAction EOF )
            // InternalKGraph.g:7261:2: iv_ruleKAction= ruleKAction EOF
            {
             newCompositeNode(grammarAccess.getKActionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKAction=ruleKAction();

            state._fsp--;

             current =iv_ruleKAction; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7268:1: ruleKAction returns [EObject current=null] : ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleKAction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Enumerator lv_trigger_0_0 = null;

        AntlrDatatypeRuleToken lv_actionId_2_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:7271:28: ( ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) ) )
            // InternalKGraph.g:7272:1: ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) )
            {
            // InternalKGraph.g:7272:1: ( ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) ) )
            // InternalKGraph.g:7272:2: ( (lv_trigger_0_0= ruleTrigger ) ) otherlv_1= '=>' ( (lv_actionId_2_0= ruleQualifiedID ) )
            {
            // InternalKGraph.g:7272:2: ( (lv_trigger_0_0= ruleTrigger ) )
            // InternalKGraph.g:7273:1: (lv_trigger_0_0= ruleTrigger )
            {
            // InternalKGraph.g:7273:1: (lv_trigger_0_0= ruleTrigger )
            // InternalKGraph.g:7274:3: lv_trigger_0_0= ruleTrigger
            {
             
            	        newCompositeNode(grammarAccess.getKActionAccess().getTriggerTriggerEnumRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_128);
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

            otherlv_1=(Token)match(input,117,FollowSets000.FOLLOW_10); 

                	newLeafNode(otherlv_1, grammarAccess.getKActionAccess().getEqualsSignGreaterThanSignKeyword_1());
                
            // InternalKGraph.g:7294:1: ( (lv_actionId_2_0= ruleQualifiedID ) )
            // InternalKGraph.g:7295:1: (lv_actionId_2_0= ruleQualifiedID )
            {
            // InternalKGraph.g:7295:1: (lv_actionId_2_0= ruleQualifiedID )
            // InternalKGraph.g:7296:3: lv_actionId_2_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getKActionAccess().getActionIdQualifiedIDParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:7320:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // InternalKGraph.g:7321:2: (iv_ruleKPosition= ruleKPosition EOF )
            // InternalKGraph.g:7322:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7329:1: ruleKPosition returns [EObject current=null] : ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_x_0_0 = null;

        EObject lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:7332:28: ( ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) ) )
            // InternalKGraph.g:7333:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) )
            {
            // InternalKGraph.g:7333:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) ) )
            // InternalKGraph.g:7333:2: ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= ',' ( (lv_y_2_0= ruleKYPosition ) )
            {
            // InternalKGraph.g:7333:2: ( (lv_x_0_0= ruleKXPosition ) )
            // InternalKGraph.g:7334:1: (lv_x_0_0= ruleKXPosition )
            {
            // InternalKGraph.g:7334:1: (lv_x_0_0= ruleKXPosition )
            // InternalKGraph.g:7335:3: lv_x_0_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_41);
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

            otherlv_1=(Token)match(input,47,FollowSets000.FOLLOW_129); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getCommaKeyword_1());
                
            // InternalKGraph.g:7355:1: ( (lv_y_2_0= ruleKYPosition ) )
            // InternalKGraph.g:7356:1: (lv_y_2_0= ruleKYPosition )
            {
            // InternalKGraph.g:7356:1: (lv_y_2_0= ruleKYPosition )
            // InternalKGraph.g:7357:3: lv_y_2_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:7381:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // InternalKGraph.g:7382:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // InternalKGraph.g:7383:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7390:1: ruleKXPosition returns [EObject current=null] : ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) ;
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
            // InternalKGraph.g:7393:28: ( ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) )
            // InternalKGraph.g:7394:1: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            {
            // InternalKGraph.g:7394:1: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            // InternalKGraph.g:7394:2: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
            {
            // InternalKGraph.g:7394:2: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
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
                    // InternalKGraph.g:7395:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_130);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7405:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_0_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_130);
                    this_KRightPosition_1=ruleKRightPosition();

                    state._fsp--;

                     
                            current = this_KRightPosition_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // InternalKGraph.g:7413:2: ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
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
                    // InternalKGraph.g:7413:3: ( (lv_relative_2_0= RULE_PERCENT ) )
                    {
                    // InternalKGraph.g:7413:3: ( (lv_relative_2_0= RULE_PERCENT ) )
                    // InternalKGraph.g:7414:1: (lv_relative_2_0= RULE_PERCENT )
                    {
                    // InternalKGraph.g:7414:1: (lv_relative_2_0= RULE_PERCENT )
                    // InternalKGraph.g:7415:3: lv_relative_2_0= RULE_PERCENT
                    {
                    lv_relative_2_0=(Token)match(input,RULE_PERCENT,FollowSets000.FOLLOW_2); 

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
                    // InternalKGraph.g:7432:6: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    {
                    // InternalKGraph.g:7432:6: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    // InternalKGraph.g:7432:7: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) )
                    {
                    // InternalKGraph.g:7432:7: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )?
                    int alt138=2;
                    int LA138_0 = input.LA(1);

                    if ( (LA138_0==RULE_PERCENT) ) {
                        alt138=1;
                    }
                    switch (alt138) {
                        case 1 :
                            // InternalKGraph.g:7432:8: ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+'
                            {
                            // InternalKGraph.g:7432:8: ( (lv_relative_3_0= RULE_PERCENT ) )
                            // InternalKGraph.g:7433:1: (lv_relative_3_0= RULE_PERCENT )
                            {
                            // InternalKGraph.g:7433:1: (lv_relative_3_0= RULE_PERCENT )
                            // InternalKGraph.g:7434:3: lv_relative_3_0= RULE_PERCENT
                            {
                            lv_relative_3_0=(Token)match(input,RULE_PERCENT,FollowSets000.FOLLOW_131); 

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

                            otherlv_4=(Token)match(input,118,FollowSets000.FOLLOW_28); 

                                	newLeafNode(otherlv_4, grammarAccess.getKXPositionAccess().getPlusSignKeyword_1_1_0_1());
                                

                            }
                            break;

                    }

                    // InternalKGraph.g:7454:3: ( (lv_absolute_5_0= ruleFloat ) )
                    // InternalKGraph.g:7455:1: (lv_absolute_5_0= ruleFloat )
                    {
                    // InternalKGraph.g:7455:1: (lv_absolute_5_0= ruleFloat )
                    // InternalKGraph.g:7456:3: lv_absolute_5_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKXPositionAccess().getAbsoluteFloatParserRuleCall_1_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:7480:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // InternalKGraph.g:7481:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // InternalKGraph.g:7482:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7489:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'left' ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:7492:28: ( ( () otherlv_1= 'left' ) )
            // InternalKGraph.g:7493:1: ( () otherlv_1= 'left' )
            {
            // InternalKGraph.g:7493:1: ( () otherlv_1= 'left' )
            // InternalKGraph.g:7493:2: () otherlv_1= 'left'
            {
            // InternalKGraph.g:7493:2: ()
            // InternalKGraph.g:7494:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,43,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7511:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // InternalKGraph.g:7512:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // InternalKGraph.g:7513:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7520:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'right' ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:7523:28: ( ( () otherlv_1= 'right' ) )
            // InternalKGraph.g:7524:1: ( () otherlv_1= 'right' )
            {
            // InternalKGraph.g:7524:1: ( () otherlv_1= 'right' )
            // InternalKGraph.g:7524:2: () otherlv_1= 'right'
            {
            // InternalKGraph.g:7524:2: ()
            // InternalKGraph.g:7525:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,44,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7542:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // InternalKGraph.g:7543:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // InternalKGraph.g:7544:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7551:1: ruleKYPosition returns [EObject current=null] : ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) ;
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
            // InternalKGraph.g:7554:28: ( ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? ) )
            // InternalKGraph.g:7555:1: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            {
            // InternalKGraph.g:7555:1: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )? )
            // InternalKGraph.g:7555:2: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
            {
            // InternalKGraph.g:7555:2: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
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
                    // InternalKGraph.g:7556:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_130);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7566:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_0_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_130);
                    this_KBottomPosition_1=ruleKBottomPosition();

                    state._fsp--;

                     
                            current = this_KBottomPosition_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // InternalKGraph.g:7574:2: ( ( (lv_relative_2_0= RULE_PERCENT ) ) | ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) ) )?
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
                    // InternalKGraph.g:7574:3: ( (lv_relative_2_0= RULE_PERCENT ) )
                    {
                    // InternalKGraph.g:7574:3: ( (lv_relative_2_0= RULE_PERCENT ) )
                    // InternalKGraph.g:7575:1: (lv_relative_2_0= RULE_PERCENT )
                    {
                    // InternalKGraph.g:7575:1: (lv_relative_2_0= RULE_PERCENT )
                    // InternalKGraph.g:7576:3: lv_relative_2_0= RULE_PERCENT
                    {
                    lv_relative_2_0=(Token)match(input,RULE_PERCENT,FollowSets000.FOLLOW_2); 

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
                    // InternalKGraph.g:7593:6: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    {
                    // InternalKGraph.g:7593:6: ( ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) ) )
                    // InternalKGraph.g:7593:7: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )? ( (lv_absolute_5_0= ruleFloat ) )
                    {
                    // InternalKGraph.g:7593:7: ( ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+' )?
                    int alt141=2;
                    int LA141_0 = input.LA(1);

                    if ( (LA141_0==RULE_PERCENT) ) {
                        alt141=1;
                    }
                    switch (alt141) {
                        case 1 :
                            // InternalKGraph.g:7593:8: ( (lv_relative_3_0= RULE_PERCENT ) ) otherlv_4= '+'
                            {
                            // InternalKGraph.g:7593:8: ( (lv_relative_3_0= RULE_PERCENT ) )
                            // InternalKGraph.g:7594:1: (lv_relative_3_0= RULE_PERCENT )
                            {
                            // InternalKGraph.g:7594:1: (lv_relative_3_0= RULE_PERCENT )
                            // InternalKGraph.g:7595:3: lv_relative_3_0= RULE_PERCENT
                            {
                            lv_relative_3_0=(Token)match(input,RULE_PERCENT,FollowSets000.FOLLOW_131); 

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

                            otherlv_4=(Token)match(input,118,FollowSets000.FOLLOW_28); 

                                	newLeafNode(otherlv_4, grammarAccess.getKYPositionAccess().getPlusSignKeyword_1_1_0_1());
                                

                            }
                            break;

                    }

                    // InternalKGraph.g:7615:3: ( (lv_absolute_5_0= ruleFloat ) )
                    // InternalKGraph.g:7616:1: (lv_absolute_5_0= ruleFloat )
                    {
                    // InternalKGraph.g:7616:1: (lv_absolute_5_0= ruleFloat )
                    // InternalKGraph.g:7617:3: lv_absolute_5_0= ruleFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKYPositionAccess().getAbsoluteFloatParserRuleCall_1_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:7641:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // InternalKGraph.g:7642:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // InternalKGraph.g:7643:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7650:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'top' ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:7653:28: ( ( () otherlv_1= 'top' ) )
            // InternalKGraph.g:7654:1: ( () otherlv_1= 'top' )
            {
            // InternalKGraph.g:7654:1: ( () otherlv_1= 'top' )
            // InternalKGraph.g:7654:2: () otherlv_1= 'top'
            {
            // InternalKGraph.g:7654:2: ()
            // InternalKGraph.g:7655:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,41,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7672:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // InternalKGraph.g:7673:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // InternalKGraph.g:7674:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7681:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'bottom' ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:7684:28: ( ( () otherlv_1= 'bottom' ) )
            // InternalKGraph.g:7685:1: ( () otherlv_1= 'bottom' )
            {
            // InternalKGraph.g:7685:1: ( () otherlv_1= 'bottom' )
            // InternalKGraph.g:7685:2: () otherlv_1= 'bottom'
            {
            // InternalKGraph.g:7685:2: ()
            // InternalKGraph.g:7686:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,42,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7703:1: entryRuleKColor returns [EObject current=null] : iv_ruleKColor= ruleKColor EOF ;
    public final EObject entryRuleKColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKColor = null;


        try {
            // InternalKGraph.g:7704:2: (iv_ruleKColor= ruleKColor EOF )
            // InternalKGraph.g:7705:2: iv_ruleKColor= ruleKColor EOF
            {
             newCompositeNode(grammarAccess.getKColorRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKColor=ruleKColor();

            state._fsp--;

             current =iv_ruleKColor; 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7712:1: ruleKColor returns [EObject current=null] : ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) ) ;
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
            // InternalKGraph.g:7715:28: ( ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) ) )
            // InternalKGraph.g:7716:1: ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) )
            {
            // InternalKGraph.g:7716:1: ( ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? ) | ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? ) | ( (lv_blue_8_0= RULE_BLUE ) ) )
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
                    // InternalKGraph.g:7716:2: ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? )
                    {
                    // InternalKGraph.g:7716:2: ( ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )? )
                    // InternalKGraph.g:7716:3: ( (lv_red_0_0= RULE_RED ) ) (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )? (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )?
                    {
                    // InternalKGraph.g:7716:3: ( (lv_red_0_0= RULE_RED ) )
                    // InternalKGraph.g:7717:1: (lv_red_0_0= RULE_RED )
                    {
                    // InternalKGraph.g:7717:1: (lv_red_0_0= RULE_RED )
                    // InternalKGraph.g:7718:3: lv_red_0_0= RULE_RED
                    {
                    lv_red_0_0=(Token)match(input,RULE_RED,FollowSets000.FOLLOW_132); 

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

                    // InternalKGraph.g:7734:2: (otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) ) )?
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
                            // InternalKGraph.g:7734:4: otherlv_1= ',' ( (lv_green_2_0= RULE_GREEN ) )
                            {
                            otherlv_1=(Token)match(input,47,FollowSets000.FOLLOW_133); 

                                	newLeafNode(otherlv_1, grammarAccess.getKColorAccess().getCommaKeyword_0_1_0());
                                
                            // InternalKGraph.g:7738:1: ( (lv_green_2_0= RULE_GREEN ) )
                            // InternalKGraph.g:7739:1: (lv_green_2_0= RULE_GREEN )
                            {
                            // InternalKGraph.g:7739:1: (lv_green_2_0= RULE_GREEN )
                            // InternalKGraph.g:7740:3: lv_green_2_0= RULE_GREEN
                            {
                            lv_green_2_0=(Token)match(input,RULE_GREEN,FollowSets000.FOLLOW_132); 

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

                    // InternalKGraph.g:7756:4: (otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) ) )?
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
                            // InternalKGraph.g:7756:6: otherlv_3= ',' ( (lv_blue_4_0= RULE_BLUE ) )
                            {
                            otherlv_3=(Token)match(input,47,FollowSets000.FOLLOW_134); 

                                	newLeafNode(otherlv_3, grammarAccess.getKColorAccess().getCommaKeyword_0_2_0());
                                
                            // InternalKGraph.g:7760:1: ( (lv_blue_4_0= RULE_BLUE ) )
                            // InternalKGraph.g:7761:1: (lv_blue_4_0= RULE_BLUE )
                            {
                            // InternalKGraph.g:7761:1: (lv_blue_4_0= RULE_BLUE )
                            // InternalKGraph.g:7762:3: lv_blue_4_0= RULE_BLUE
                            {
                            lv_blue_4_0=(Token)match(input,RULE_BLUE,FollowSets000.FOLLOW_2); 

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
                    // InternalKGraph.g:7779:6: ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? )
                    {
                    // InternalKGraph.g:7779:6: ( ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )? )
                    // InternalKGraph.g:7779:7: ( (lv_green_5_0= RULE_GREEN ) ) (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )?
                    {
                    // InternalKGraph.g:7779:7: ( (lv_green_5_0= RULE_GREEN ) )
                    // InternalKGraph.g:7780:1: (lv_green_5_0= RULE_GREEN )
                    {
                    // InternalKGraph.g:7780:1: (lv_green_5_0= RULE_GREEN )
                    // InternalKGraph.g:7781:3: lv_green_5_0= RULE_GREEN
                    {
                    lv_green_5_0=(Token)match(input,RULE_GREEN,FollowSets000.FOLLOW_132); 

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

                    // InternalKGraph.g:7797:2: (otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) ) )?
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
                            // InternalKGraph.g:7797:4: otherlv_6= ',' ( (lv_blue_7_0= RULE_BLUE ) )
                            {
                            otherlv_6=(Token)match(input,47,FollowSets000.FOLLOW_134); 

                                	newLeafNode(otherlv_6, grammarAccess.getKColorAccess().getCommaKeyword_1_1_0());
                                
                            // InternalKGraph.g:7801:1: ( (lv_blue_7_0= RULE_BLUE ) )
                            // InternalKGraph.g:7802:1: (lv_blue_7_0= RULE_BLUE )
                            {
                            // InternalKGraph.g:7802:1: (lv_blue_7_0= RULE_BLUE )
                            // InternalKGraph.g:7803:3: lv_blue_7_0= RULE_BLUE
                            {
                            lv_blue_7_0=(Token)match(input,RULE_BLUE,FollowSets000.FOLLOW_2); 

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
                    // InternalKGraph.g:7820:6: ( (lv_blue_8_0= RULE_BLUE ) )
                    {
                    // InternalKGraph.g:7820:6: ( (lv_blue_8_0= RULE_BLUE ) )
                    // InternalKGraph.g:7821:1: (lv_blue_8_0= RULE_BLUE )
                    {
                    // InternalKGraph.g:7821:1: (lv_blue_8_0= RULE_BLUE )
                    // InternalKGraph.g:7822:3: lv_blue_8_0= RULE_BLUE
                    {
                    lv_blue_8_0=(Token)match(input,RULE_BLUE,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7846:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalKGraph.g:7847:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalKGraph.g:7848:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7855:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:7858:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalKGraph.g:7859:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalKGraph.g:7859:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalKGraph.g:7859:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_135); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // InternalKGraph.g:7866:1: (kw= '.' this_ID_2= RULE_ID )*
            loop147:
            do {
                int alt147=2;
                int LA147_0 = input.LA(1);

                if ( (LA147_0==119) ) {
                    alt147=1;
                }


                switch (alt147) {
            	case 1 :
            	    // InternalKGraph.g:7867:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,119,FollowSets000.FOLLOW_10); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_135); 

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
    // InternalKGraph.g:7887:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // InternalKGraph.g:7888:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // InternalKGraph.g:7889:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7896:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // InternalKGraph.g:7899:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // InternalKGraph.g:7900:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // InternalKGraph.g:7900:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
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
                    // InternalKGraph.g:7900:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_2); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7908:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_2); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalKGraph.g:7917:5: this_Float_2= ruleFloat
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    		current.merge(this_Float_2);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // InternalKGraph.g:7929:5: this_QualifiedID_3= ruleQualifiedID
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalKGraph.g:7947:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // InternalKGraph.g:7948:2: (iv_ruleFloat= ruleFloat EOF )
            // InternalKGraph.g:7949:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7956:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // InternalKGraph.g:7959:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // InternalKGraph.g:7960:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // InternalKGraph.g:7960:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
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
                    // InternalKGraph.g:7960:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FollowSets000.FOLLOW_2); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7968:10: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:7983:1: ruleArc returns [Enumerator current=null] : ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) ) ;
    public final Enumerator ruleArc() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalKGraph.g:7985:28: ( ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) ) )
            // InternalKGraph.g:7986:1: ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) )
            {
            // InternalKGraph.g:7986:1: ( (enumLiteral_0= 'open' ) | (enumLiteral_1= 'chord' ) | (enumLiteral_2= 'pie' ) )
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
                    // InternalKGraph.g:7986:2: (enumLiteral_0= 'open' )
                    {
                    // InternalKGraph.g:7986:2: (enumLiteral_0= 'open' )
                    // InternalKGraph.g:7986:4: enumLiteral_0= 'open'
                    {
                    enumLiteral_0=(Token)match(input,120,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getArcAccess().getOPENEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getArcAccess().getOPENEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:7992:6: (enumLiteral_1= 'chord' )
                    {
                    // InternalKGraph.g:7992:6: (enumLiteral_1= 'chord' )
                    // InternalKGraph.g:7992:8: enumLiteral_1= 'chord'
                    {
                    enumLiteral_1=(Token)match(input,121,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getArcAccess().getCHORDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getArcAccess().getCHORDEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:7998:6: (enumLiteral_2= 'pie' )
                    {
                    // InternalKGraph.g:7998:6: (enumLiteral_2= 'pie' )
                    // InternalKGraph.g:7998:8: enumLiteral_2= 'pie'
                    {
                    enumLiteral_2=(Token)match(input,122,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:8008:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalKGraph.g:8010:28: ( ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) ) )
            // InternalKGraph.g:8011:1: ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) )
            {
            // InternalKGraph.g:8011:1: ( (enumLiteral_0= 'left' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'right' ) )
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
                    // InternalKGraph.g:8011:2: (enumLiteral_0= 'left' )
                    {
                    // InternalKGraph.g:8011:2: (enumLiteral_0= 'left' )
                    // InternalKGraph.g:8011:4: enumLiteral_0= 'left'
                    {
                    enumLiteral_0=(Token)match(input,43,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8017:6: (enumLiteral_1= 'center' )
                    {
                    // InternalKGraph.g:8017:6: (enumLiteral_1= 'center' )
                    // InternalKGraph.g:8017:8: enumLiteral_1= 'center'
                    {
                    enumLiteral_1=(Token)match(input,123,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8023:6: (enumLiteral_2= 'right' )
                    {
                    // InternalKGraph.g:8023:6: (enumLiteral_2= 'right' )
                    // InternalKGraph.g:8023:8: enumLiteral_2= 'right'
                    {
                    enumLiteral_2=(Token)match(input,44,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:8033:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalKGraph.g:8035:28: ( ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) )
            // InternalKGraph.g:8036:1: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
            {
            // InternalKGraph.g:8036:1: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
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
                    // InternalKGraph.g:8036:2: (enumLiteral_0= 'top' )
                    {
                    // InternalKGraph.g:8036:2: (enumLiteral_0= 'top' )
                    // InternalKGraph.g:8036:4: enumLiteral_0= 'top'
                    {
                    enumLiteral_0=(Token)match(input,41,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8042:6: (enumLiteral_1= 'center' )
                    {
                    // InternalKGraph.g:8042:6: (enumLiteral_1= 'center' )
                    // InternalKGraph.g:8042:8: enumLiteral_1= 'center'
                    {
                    enumLiteral_1=(Token)match(input,123,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8048:6: (enumLiteral_2= 'bottom' )
                    {
                    // InternalKGraph.g:8048:6: (enumLiteral_2= 'bottom' )
                    // InternalKGraph.g:8048:8: enumLiteral_2= 'bottom'
                    {
                    enumLiteral_2=(Token)match(input,42,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:8058:1: ruleUnderline returns [Enumerator current=null] : ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) ) ;
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
            // InternalKGraph.g:8060:28: ( ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) ) )
            // InternalKGraph.g:8061:1: ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) )
            {
            // InternalKGraph.g:8061:1: ( (enumLiteral_0= 'none' ) | (enumLiteral_1= 'single' ) | (enumLiteral_2= 'double' ) | (enumLiteral_3= 'error' ) | (enumLiteral_4= 'squiggle' ) | (enumLiteral_5= 'link' ) )
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
                    // InternalKGraph.g:8061:2: (enumLiteral_0= 'none' )
                    {
                    // InternalKGraph.g:8061:2: (enumLiteral_0= 'none' )
                    // InternalKGraph.g:8061:4: enumLiteral_0= 'none'
                    {
                    enumLiteral_0=(Token)match(input,124,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getUnderlineAccess().getNONEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getUnderlineAccess().getNONEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8067:6: (enumLiteral_1= 'single' )
                    {
                    // InternalKGraph.g:8067:6: (enumLiteral_1= 'single' )
                    // InternalKGraph.g:8067:8: enumLiteral_1= 'single'
                    {
                    enumLiteral_1=(Token)match(input,125,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getUnderlineAccess().getSINGLEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getUnderlineAccess().getSINGLEEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8073:6: (enumLiteral_2= 'double' )
                    {
                    // InternalKGraph.g:8073:6: (enumLiteral_2= 'double' )
                    // InternalKGraph.g:8073:8: enumLiteral_2= 'double'
                    {
                    enumLiteral_2=(Token)match(input,126,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getUnderlineAccess().getDOUBLEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getUnderlineAccess().getDOUBLEEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalKGraph.g:8079:6: (enumLiteral_3= 'error' )
                    {
                    // InternalKGraph.g:8079:6: (enumLiteral_3= 'error' )
                    // InternalKGraph.g:8079:8: enumLiteral_3= 'error'
                    {
                    enumLiteral_3=(Token)match(input,127,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getUnderlineAccess().getERROREnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getUnderlineAccess().getERROREnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalKGraph.g:8085:6: (enumLiteral_4= 'squiggle' )
                    {
                    // InternalKGraph.g:8085:6: (enumLiteral_4= 'squiggle' )
                    // InternalKGraph.g:8085:8: enumLiteral_4= 'squiggle'
                    {
                    enumLiteral_4=(Token)match(input,128,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getUnderlineAccess().getSQUIGGLEEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getUnderlineAccess().getSQUIGGLEEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalKGraph.g:8091:6: (enumLiteral_5= 'link' )
                    {
                    // InternalKGraph.g:8091:6: (enumLiteral_5= 'link' )
                    // InternalKGraph.g:8091:8: enumLiteral_5= 'link'
                    {
                    enumLiteral_5=(Token)match(input,129,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:8101:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) ) ;
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
            // InternalKGraph.g:8103:28: ( ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) ) )
            // InternalKGraph.g:8104:1: ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) )
            {
            // InternalKGraph.g:8104:1: ( (enumLiteral_0= 'solid' ) | (enumLiteral_1= 'dash' ) | (enumLiteral_2= 'dot' ) | (enumLiteral_3= 'dash-dot' ) | (enumLiteral_4= 'dash-dot-dot' ) | (enumLiteral_5= 'custom' ) )
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
                    // InternalKGraph.g:8104:2: (enumLiteral_0= 'solid' )
                    {
                    // InternalKGraph.g:8104:2: (enumLiteral_0= 'solid' )
                    // InternalKGraph.g:8104:4: enumLiteral_0= 'solid'
                    {
                    enumLiteral_0=(Token)match(input,130,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8110:6: (enumLiteral_1= 'dash' )
                    {
                    // InternalKGraph.g:8110:6: (enumLiteral_1= 'dash' )
                    // InternalKGraph.g:8110:8: enumLiteral_1= 'dash'
                    {
                    enumLiteral_1=(Token)match(input,131,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8116:6: (enumLiteral_2= 'dot' )
                    {
                    // InternalKGraph.g:8116:6: (enumLiteral_2= 'dot' )
                    // InternalKGraph.g:8116:8: enumLiteral_2= 'dot'
                    {
                    enumLiteral_2=(Token)match(input,132,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalKGraph.g:8122:6: (enumLiteral_3= 'dash-dot' )
                    {
                    // InternalKGraph.g:8122:6: (enumLiteral_3= 'dash-dot' )
                    // InternalKGraph.g:8122:8: enumLiteral_3= 'dash-dot'
                    {
                    enumLiteral_3=(Token)match(input,133,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalKGraph.g:8128:6: (enumLiteral_4= 'dash-dot-dot' )
                    {
                    // InternalKGraph.g:8128:6: (enumLiteral_4= 'dash-dot-dot' )
                    // InternalKGraph.g:8128:8: enumLiteral_4= 'dash-dot-dot'
                    {
                    enumLiteral_4=(Token)match(input,134,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTDOTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getLineStyleAccess().getDASHDOTDOTEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalKGraph.g:8134:6: (enumLiteral_5= 'custom' )
                    {
                    // InternalKGraph.g:8134:6: (enumLiteral_5= 'custom' )
                    // InternalKGraph.g:8134:8: enumLiteral_5= 'custom'
                    {
                    enumLiteral_5=(Token)match(input,135,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:8144:1: ruleLineCap returns [Enumerator current=null] : ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) ) ;
    public final Enumerator ruleLineCap() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalKGraph.g:8146:28: ( ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) ) )
            // InternalKGraph.g:8147:1: ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) )
            {
            // InternalKGraph.g:8147:1: ( (enumLiteral_0= 'flat' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'square' ) )
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
                    // InternalKGraph.g:8147:2: (enumLiteral_0= 'flat' )
                    {
                    // InternalKGraph.g:8147:2: (enumLiteral_0= 'flat' )
                    // InternalKGraph.g:8147:4: enumLiteral_0= 'flat'
                    {
                    enumLiteral_0=(Token)match(input,136,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineCapAccess().getCAP_FLATEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineCapAccess().getCAP_FLATEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8153:6: (enumLiteral_1= 'round' )
                    {
                    // InternalKGraph.g:8153:6: (enumLiteral_1= 'round' )
                    // InternalKGraph.g:8153:8: enumLiteral_1= 'round'
                    {
                    enumLiteral_1=(Token)match(input,137,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineCapAccess().getCAP_ROUNDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineCapAccess().getCAP_ROUNDEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8159:6: (enumLiteral_2= 'square' )
                    {
                    // InternalKGraph.g:8159:6: (enumLiteral_2= 'square' )
                    // InternalKGraph.g:8159:8: enumLiteral_2= 'square'
                    {
                    enumLiteral_2=(Token)match(input,138,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:8169:1: ruleLineJoin returns [Enumerator current=null] : ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) ) ;
    public final Enumerator ruleLineJoin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalKGraph.g:8171:28: ( ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) ) )
            // InternalKGraph.g:8172:1: ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) )
            {
            // InternalKGraph.g:8172:1: ( (enumLiteral_0= 'miter' ) | (enumLiteral_1= 'round' ) | (enumLiteral_2= 'bevel' ) )
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
                    // InternalKGraph.g:8172:2: (enumLiteral_0= 'miter' )
                    {
                    // InternalKGraph.g:8172:2: (enumLiteral_0= 'miter' )
                    // InternalKGraph.g:8172:4: enumLiteral_0= 'miter'
                    {
                    enumLiteral_0=(Token)match(input,139,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineJoinAccess().getJOIN_MITEREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineJoinAccess().getJOIN_MITEREnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8178:6: (enumLiteral_1= 'round' )
                    {
                    // InternalKGraph.g:8178:6: (enumLiteral_1= 'round' )
                    // InternalKGraph.g:8178:8: enumLiteral_1= 'round'
                    {
                    enumLiteral_1=(Token)match(input,137,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getLineJoinAccess().getJOIN_ROUNDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineJoinAccess().getJOIN_ROUNDEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8184:6: (enumLiteral_2= 'bevel' )
                    {
                    // InternalKGraph.g:8184:6: (enumLiteral_2= 'bevel' )
                    // InternalKGraph.g:8184:8: enumLiteral_2= 'bevel'
                    {
                    enumLiteral_2=(Token)match(input,140,FollowSets000.FOLLOW_2); 

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
    // InternalKGraph.g:8194:1: ruleTrigger returns [Enumerator current=null] : ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) ) ;
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
            // InternalKGraph.g:8196:28: ( ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) ) )
            // InternalKGraph.g:8197:1: ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) )
            {
            // InternalKGraph.g:8197:1: ( (enumLiteral_0= 'singleClick' ) | (enumLiteral_1= 'doubleClick' ) | (enumLiteral_2= 'singleOrMultiClick' ) | (enumLiteral_3= 'middleSingleClick' ) | (enumLiteral_4= 'middleDoubleClick' ) | (enumLiteral_5= 'middleSingleOrMultiClick' ) )
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
                    // InternalKGraph.g:8197:2: (enumLiteral_0= 'singleClick' )
                    {
                    // InternalKGraph.g:8197:2: (enumLiteral_0= 'singleClick' )
                    // InternalKGraph.g:8197:4: enumLiteral_0= 'singleClick'
                    {
                    enumLiteral_0=(Token)match(input,141,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getTriggerAccess().getSINGLECLICKEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getTriggerAccess().getSINGLECLICKEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalKGraph.g:8203:6: (enumLiteral_1= 'doubleClick' )
                    {
                    // InternalKGraph.g:8203:6: (enumLiteral_1= 'doubleClick' )
                    // InternalKGraph.g:8203:8: enumLiteral_1= 'doubleClick'
                    {
                    enumLiteral_1=(Token)match(input,142,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getTriggerAccess().getDOUBLECLICKEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getTriggerAccess().getDOUBLECLICKEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalKGraph.g:8209:6: (enumLiteral_2= 'singleOrMultiClick' )
                    {
                    // InternalKGraph.g:8209:6: (enumLiteral_2= 'singleOrMultiClick' )
                    // InternalKGraph.g:8209:8: enumLiteral_2= 'singleOrMultiClick'
                    {
                    enumLiteral_2=(Token)match(input,143,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getTriggerAccess().getSINGLE_OR_MULTICLICKEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getTriggerAccess().getSINGLE_OR_MULTICLICKEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // InternalKGraph.g:8215:6: (enumLiteral_3= 'middleSingleClick' )
                    {
                    // InternalKGraph.g:8215:6: (enumLiteral_3= 'middleSingleClick' )
                    // InternalKGraph.g:8215:8: enumLiteral_3= 'middleSingleClick'
                    {
                    enumLiteral_3=(Token)match(input,144,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getTriggerAccess().getMIDDLE_SINGLECLICKEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getTriggerAccess().getMIDDLE_SINGLECLICKEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // InternalKGraph.g:8221:6: (enumLiteral_4= 'middleDoubleClick' )
                    {
                    // InternalKGraph.g:8221:6: (enumLiteral_4= 'middleDoubleClick' )
                    // InternalKGraph.g:8221:8: enumLiteral_4= 'middleDoubleClick'
                    {
                    enumLiteral_4=(Token)match(input,145,FollowSets000.FOLLOW_2); 

                            current = grammarAccess.getTriggerAccess().getMIDDLE_DOUBLECLICKEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getTriggerAccess().getMIDDLE_DOUBLECLICKEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // InternalKGraph.g:8227:6: (enumLiteral_5= 'middleSingleOrMultiClick' )
                    {
                    // InternalKGraph.g:8227:6: (enumLiteral_5= 'middleSingleOrMultiClick' )
                    // InternalKGraph.g:8227:8: enumLiteral_5= 'middleSingleOrMultiClick'
                    {
                    enumLiteral_5=(Token)match(input,146,FollowSets000.FOLLOW_2); 

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
            return "3323:1: ( ( (lv_bundleName_3_0= ruleQualifiedID ) ) otherlv_4= ':' )?";
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
            return "4003:2: (this_KColoring_0= ruleKColoring | this_KFontBold_1= ruleKFontBold | this_KFontItalic_2= ruleKFontItalic | this_KFontName_3= ruleKFontName | this_KFontSize_4= ruleKFontSize | this_KTextUnderline_5= ruleKTextUnderline | this_KHorizontalAlignment_6= ruleKHorizontalAlignment | this_KVerticalAlignment_7= ruleKVerticalAlignment | this_KInvisibility_8= ruleKInvisibility | this_KLineCap_9= ruleKLineCap | this_KLineJoin_10= ruleKLineJoin | this_KLineStyle_11= ruleKLineStyle | this_KLineWidth_12= ruleKLineWidth | this_KRotation_13= ruleKRotation | this_KShadow_14= ruleKShadow | this_KStyleRef_15= ruleKStyleRef )";
        }
    }
 

    
    private static class FollowSets000 {
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


}