package de.cau.cs.kieler.core.kgraph.text.parser.antlr.internal; 

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
import de.cau.cs.kieler.core.kgraph.text.services.KGraphGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKGraphParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'KNode'", "'{'", "'ports'", "','", "'data'", "':'", "'children'", "'}'", "'KLabel'", "'KPort'", "'edges'", "'labels'", "'-->'", "'target'", "'sourcePort'", "'targetPort'", "'KRenderingRef'", "'references'", "'('", "')'", "'rendering'", "'placementData'", "'styles'", "'KEllipse'", "'childPlacement'", "'KRectangle'", "'KRoundedRectangle'", "'cornerWidth'", "'cornerHeight'", "'KPolyline'", "'KPolygon'", "'KImage'", "'bundleName'", "'imagePath'", "'KArc'", "'startAngle'", "'arcAngle'", "'KChildArea'", "'KText'", "'clip'", "'text'", "'KCustomRendering'", "'className'", "'KSpline'", "'KDecoratorPlacementData'", "'relative'", "'location'", "'xOffset'", "'yOffset'", "'width'", "'height'", "'KGridPlacementData'", "'widthHint'", "'heightHint'", "'horizontalIndent'", "'verticalIndent'", "'KStackPlacementData'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'KDirectPlacementData'", "'topLeft'", "'bottomRight'", "'points'", "'detailedPlacementData'", "'-'", "'.'", "'E'", "'e'", "'KPosition'", "'x'", "'y'", "'KLeftPosition'", "'absolute'", "'KRightPosition'", "'KTopPosition'", "'KBottomPosition'", "'KForegroundColor'", "'propagateToChildren'", "'red'", "'green'", "'blue'", "'KBackgroundColor'", "'KLineWidth'", "'KForegroundVisibility'", "'visible'", "'KBackgroundVisibility'", "'KLineStyle'", "'KVerticalAlignment'", "'KHorizontalAlignment'", "'KGridPlacement'", "'KStackPlacement'", "'KShapeLayout'", "'xpos'", "'ypos'", "'insets'", "'mapProperties'", "'KInsets'", "'top'", "'bottom'", "'left'", "'right'", "'KEdgeLayout'", "'sourcePoint'", "'targetPoint'", "'bendPoints'", "'KPoint'", "'='", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
    };
    public static final int RULE_ID=6;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int T__93=93;
    public static final int T__19=19;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__90=90;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__126=126;
    public static final int T__125=125;
    public static final int T__128=128;
    public static final int RULE_STRING=5;
    public static final int T__127=127;
    public static final int T__71=71;
    public static final int T__129=129;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__124=124;
    public static final int T__123=123;
    public static final int T__122=122;
    public static final int T__121=121;
    public static final int T__120=120;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int RULE_INT=4;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_WS=9;

    // delegates
    // delegators


        public InternalKGraphParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalKGraphParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalKGraphParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g"; }



     	private KGraphGrammarAccess grammarAccess;
     	
        public InternalKGraphParser(TokenStream input, KGraphGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "KNode";	
       	}
       	
       	@Override
       	protected KGraphGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleKNode"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:68:1: entryRuleKNode returns [EObject current=null] : iv_ruleKNode= ruleKNode EOF ;
    public final EObject entryRuleKNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKNode = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:69:2: (iv_ruleKNode= ruleKNode EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:70:2: iv_ruleKNode= ruleKNode EOF
            {
             newCompositeNode(grammarAccess.getKNodeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKNode_in_entryRuleKNode75);
            iv_ruleKNode=ruleKNode();

            state._fsp--;

             current =iv_ruleKNode; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKNode85); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:77:1: ruleKNode returns [EObject current=null] : ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' otherlv_8= ':' ( (lv_data_9_0= ruleKGraphData ) ) (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' otherlv_13= ':' ( (lv_children_14_0= ruleKNode ) ) (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? ) ;
    public final EObject ruleKNode() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_ports_4_0 = null;

        EObject lv_ports_6_0 = null;

        EObject lv_data_9_0 = null;

        EObject lv_data_11_0 = null;

        EObject lv_children_14_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_outgoingEdges_18_0 = null;

        EObject lv_outgoingEdges_20_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:80:28: ( ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' otherlv_8= ':' ( (lv_data_9_0= ruleKGraphData ) ) (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' otherlv_13= ':' ( (lv_children_14_0= ruleKNode ) ) (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:1: ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' otherlv_8= ':' ( (lv_data_9_0= ruleKGraphData ) ) (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' otherlv_13= ':' ( (lv_children_14_0= ruleKNode ) ) (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:1: ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' otherlv_8= ':' ( (lv_data_9_0= ruleKGraphData ) ) (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' otherlv_13= ':' ( (lv_children_14_0= ruleKNode ) ) (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:2: () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' otherlv_8= ':' ( (lv_data_9_0= ruleKGraphData ) ) (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' otherlv_13= ':' ( (lv_children_14_0= ruleKNode ) ) (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:82:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKNodeAccess().getKNodeAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleKNode131); 

                	newLeafNode(otherlv_1, grammarAccess.getKNodeAccess().getKNodeKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKNode143); 

                	newLeafNode(otherlv_2, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:1: (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:3: otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )*
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKNode156); 

                        	newLeafNode(otherlv_3, grammarAccess.getKNodeAccess().getPortsKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:99:1: ( (lv_ports_4_0= ruleKPort ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:100:1: (lv_ports_4_0= ruleKPort )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:100:1: (lv_ports_4_0= ruleKPort )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:101:3: lv_ports_4_0= ruleKPort
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getPortsKPortParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPort_in_ruleKNode177);
                    lv_ports_4_0=ruleKPort();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"ports",
                            		lv_ports_4_0, 
                            		"KPort");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:2: (otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==14) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:4: otherlv_5= ',' ( (lv_ports_6_0= ruleKPort ) )
                    	    {
                    	    otherlv_5=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode190); 

                    	        	newLeafNode(otherlv_5, grammarAccess.getKNodeAccess().getCommaKeyword_3_2_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:121:1: ( (lv_ports_6_0= ruleKPort ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:122:1: (lv_ports_6_0= ruleKPort )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:122:1: (lv_ports_6_0= ruleKPort )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:123:3: lv_ports_6_0= ruleKPort
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getPortsKPortParserRuleCall_3_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPort_in_ruleKNode211);
                    	    lv_ports_6_0=ruleKPort();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"ports",
                    	            		lv_ports_6_0, 
                    	            		"KPort");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:139:6: (otherlv_7= 'data' otherlv_8= ':' ( (lv_data_9_0= ruleKGraphData ) ) (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:139:8: otherlv_7= 'data' otherlv_8= ':' ( (lv_data_9_0= ruleKGraphData ) ) (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )*
                    {
                    otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode228); 

                        	newLeafNode(otherlv_7, grammarAccess.getKNodeAccess().getDataKeyword_4_0());
                        
                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKNode240); 

                        	newLeafNode(otherlv_8, grammarAccess.getKNodeAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:147:1: ( (lv_data_9_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:148:1: (lv_data_9_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:148:1: (lv_data_9_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:149:3: lv_data_9_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKGraphDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKNode261);
                    lv_data_9_0=ruleKGraphData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_9_0, 
                            		"KGraphData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:165:2: (otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==14) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:165:4: otherlv_10= ',' ( (lv_data_11_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode274); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getKNodeAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:169:1: ( (lv_data_11_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:170:1: (lv_data_11_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:170:1: (lv_data_11_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:171:3: lv_data_11_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKGraphDataParserRuleCall_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKNode295);
                    	    lv_data_11_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_11_0, 
                    	            		"KGraphData");
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
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:187:6: (otherlv_12= 'children' otherlv_13= ':' ( (lv_children_14_0= ruleKNode ) ) (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:187:8: otherlv_12= 'children' otherlv_13= ':' ( (lv_children_14_0= ruleKNode ) ) (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )*
                    {
                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKNode312); 

                        	newLeafNode(otherlv_12, grammarAccess.getKNodeAccess().getChildrenKeyword_5_0());
                        
                    otherlv_13=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKNode324); 

                        	newLeafNode(otherlv_13, grammarAccess.getKNodeAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:195:1: ( (lv_children_14_0= ruleKNode ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:196:1: (lv_children_14_0= ruleKNode )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:196:1: (lv_children_14_0= ruleKNode )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:197:3: lv_children_14_0= ruleKNode
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKNode_in_ruleKNode345);
                    lv_children_14_0=ruleKNode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_14_0, 
                            		"KNode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:213:2: (otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==14) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:213:4: otherlv_15= ',' ( (lv_children_16_0= ruleKNode ) )
                    	    {
                    	    otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode358); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getKNodeAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:217:1: ( (lv_children_16_0= ruleKNode ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:218:1: (lv_children_16_0= ruleKNode )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:218:1: (lv_children_16_0= ruleKNode )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:219:3: lv_children_16_0= ruleKNode
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKNode_in_ruleKNode379);
                    	    lv_children_16_0=ruleKNode();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_16_0, 
                    	            		"KNode");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode395); 

                	newLeafNode(otherlv_17, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:239:1: ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:239:2: ( (lv_outgoingEdges_18_0= ruleKEdge ) ) (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )*
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:239:2: ( (lv_outgoingEdges_18_0= ruleKEdge ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:240:1: (lv_outgoingEdges_18_0= ruleKEdge )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:240:1: (lv_outgoingEdges_18_0= ruleKEdge )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:241:3: lv_outgoingEdges_18_0= ruleKEdge
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_7_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_ruleKNode417);
                    lv_outgoingEdges_18_0=ruleKEdge();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"outgoingEdges",
                            		lv_outgoingEdges_18_0, 
                            		"KEdge");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:257:2: (otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==14) ) {
                            int LA7_2 = input.LA(2);

                            if ( (LA7_2==23) ) {
                                alt7=1;
                            }


                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:257:4: otherlv_19= ',' ( (lv_outgoingEdges_20_0= ruleKEdge ) )
                    	    {
                    	    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode430); 

                    	        	newLeafNode(otherlv_19, grammarAccess.getKNodeAccess().getCommaKeyword_7_1_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:261:1: ( (lv_outgoingEdges_20_0= ruleKEdge ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:262:1: (lv_outgoingEdges_20_0= ruleKEdge )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:262:1: (lv_outgoingEdges_20_0= ruleKEdge )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:263:3: lv_outgoingEdges_20_0= ruleKEdge
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_7_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_ruleKNode451);
                    	    lv_outgoingEdges_20_0=ruleKEdge();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outgoingEdges",
                    	            		lv_outgoingEdges_20_0, 
                    	            		"KEdge");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
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
    // $ANTLR end "ruleKNode"


    // $ANTLR start "entryRuleKLabel"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:287:1: entryRuleKLabel returns [EObject current=null] : iv_ruleKLabel= ruleKLabel EOF ;
    public final EObject entryRuleKLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLabel = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:288:2: (iv_ruleKLabel= ruleKLabel EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:289:2: iv_ruleKLabel= ruleKLabel EOF
            {
             newCompositeNode(grammarAccess.getKLabelRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_entryRuleKLabel491);
            iv_ruleKLabel=ruleKLabel();

            state._fsp--;

             current =iv_ruleKLabel; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLabel501); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:296:1: ruleKLabel returns [EObject current=null] : (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' otherlv_4= ':' ( (lv_data_5_0= ruleKGraphData ) ) (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' ) ;
    public final EObject ruleKLabel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_text_1_0 = null;

        EObject lv_data_5_0 = null;

        EObject lv_data_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:299:28: ( (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' otherlv_4= ':' ( (lv_data_5_0= ruleKGraphData ) ) (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:300:1: (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' otherlv_4= ':' ( (lv_data_5_0= ruleKGraphData ) ) (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:300:1: (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' otherlv_4= ':' ( (lv_data_5_0= ruleKGraphData ) ) (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:300:3: otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' otherlv_4= ':' ( (lv_data_5_0= ruleKGraphData ) ) (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKLabel538); 

                	newLeafNode(otherlv_0, grammarAccess.getKLabelAccess().getKLabelKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:304:1: ( (lv_text_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:305:1: (lv_text_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:305:1: (lv_text_1_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:306:3: lv_text_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKLabelAccess().getTextEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKLabel559);
            lv_text_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLabelRule());
            	        }
                   		set(
                   			current, 
                   			"text",
                    		lv_text_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLabel571); 

                	newLeafNode(otherlv_2, grammarAccess.getKLabelAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:326:1: (otherlv_3= 'data' otherlv_4= ':' ( (lv_data_5_0= ruleKGraphData ) ) (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==15) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:326:3: otherlv_3= 'data' otherlv_4= ':' ( (lv_data_5_0= ruleKGraphData ) ) (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )*
                    {
                    otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKLabel584); 

                        	newLeafNode(otherlv_3, grammarAccess.getKLabelAccess().getDataKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKLabel596); 

                        	newLeafNode(otherlv_4, grammarAccess.getKLabelAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:334:1: ( (lv_data_5_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:335:1: (lv_data_5_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:335:1: (lv_data_5_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:336:3: lv_data_5_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKGraphDataParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKLabel617);
                    lv_data_5_0=ruleKGraphData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKLabelRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_5_0, 
                            		"KGraphData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:352:2: (otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==14) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:352:4: otherlv_6= ',' ( (lv_data_7_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKLabel630); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKLabelAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:356:1: ( (lv_data_7_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:357:1: (lv_data_7_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:357:1: (lv_data_7_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:358:3: lv_data_7_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKGraphDataParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKLabel651);
                    	    lv_data_7_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKLabelRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_7_0, 
                    	            		"KGraphData");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLabel667); 

                	newLeafNode(otherlv_8, grammarAccess.getKLabelAccess().getRightCurlyBracketKeyword_4());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:386:1: entryRuleKPort returns [EObject current=null] : iv_ruleKPort= ruleKPort EOF ;
    public final EObject entryRuleKPort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPort = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:387:2: (iv_ruleKPort= ruleKPort EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:388:2: iv_ruleKPort= ruleKPort EOF
            {
             newCompositeNode(grammarAccess.getKPortRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPort_in_entryRuleKPort703);
            iv_ruleKPort=ruleKPort();

            state._fsp--;

             current =iv_ruleKPort; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPort713); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:395:1: ruleKPort returns [EObject current=null] : ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= ':' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' ) ;
    public final EObject ruleKPort() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        EObject lv_data_10_0 = null;

        EObject lv_data_12_0 = null;

        EObject lv_labels_15_0 = null;

        EObject lv_labels_17_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:398:28: ( ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= ':' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:1: ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= ':' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:1: ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= ':' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:2: () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= ':' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:400:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPortAccess().getKPortAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPort759); 

                	newLeafNode(otherlv_1, grammarAccess.getKPortAccess().getKPortKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPort771); 

                	newLeafNode(otherlv_2, grammarAccess.getKPortAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:413:1: (otherlv_3= 'edges' otherlv_4= ':' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:413:3: otherlv_3= 'edges' otherlv_4= ':' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )*
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPort784); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPortAccess().getEdgesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPort796); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPortAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:421:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:422:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:422:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:423:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKPortRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getEdgesKEdgeCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPort819);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:436:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==14) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:436:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPort832); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPortAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:440:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:441:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:441:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:442:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKPortRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getEdgesKEdgeCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPort855);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:455:6: (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==15) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:455:8: otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )*
                    {
                    otherlv_8=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPort872); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPortAccess().getDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPort884); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPortAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:463:1: ( (lv_data_10_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:464:1: (lv_data_10_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:464:1: (lv_data_10_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:465:3: lv_data_10_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKGraphDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKPort905);
                    lv_data_10_0=ruleKGraphData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPortRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_10_0, 
                            		"KGraphData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:481:2: (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==14) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:481:4: otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPort918); 

                    	        	newLeafNode(otherlv_11, grammarAccess.getKPortAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:485:1: ( (lv_data_12_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:486:1: (lv_data_12_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:486:1: (lv_data_12_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:487:3: lv_data_12_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKGraphDataParserRuleCall_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKPort939);
                    	    lv_data_12_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPortRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_12_0, 
                    	            		"KGraphData");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:503:6: (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==22) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:503:8: otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )*
                    {
                    otherlv_13=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKPort956); 

                        	newLeafNode(otherlv_13, grammarAccess.getKPortAccess().getLabelsKeyword_5_0());
                        
                    otherlv_14=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPort968); 

                        	newLeafNode(otherlv_14, grammarAccess.getKPortAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:511:1: ( (lv_labels_15_0= ruleKLabel ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:512:1: (lv_labels_15_0= ruleKLabel )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:512:1: (lv_labels_15_0= ruleKLabel )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:513:3: lv_labels_15_0= ruleKLabel
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKPort989);
                    lv_labels_15_0=ruleKLabel();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPortRule());
                    	        }
                           		add(
                           			current, 
                           			"labels",
                            		lv_labels_15_0, 
                            		"KLabel");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:529:2: (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==14) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:529:4: otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) )
                    	    {
                    	    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPort1002); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKPortAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:533:1: ( (lv_labels_17_0= ruleKLabel ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:534:1: (lv_labels_17_0= ruleKLabel )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:534:1: (lv_labels_17_0= ruleKLabel )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:535:3: lv_labels_17_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKPort1023);
                    	    lv_labels_17_0=ruleKLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPortRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_17_0, 
                    	            		"KLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPort1039); 

                	newLeafNode(otherlv_18, grammarAccess.getKPortAccess().getRightCurlyBracketKeyword_6());
                

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


    // $ANTLR start "entryRuleKEdge"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:563:1: entryRuleKEdge returns [EObject current=null] : iv_ruleKEdge= ruleKEdge EOF ;
    public final EObject entryRuleKEdge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdge = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:564:2: (iv_ruleKEdge= ruleKEdge EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:565:2: iv_ruleKEdge= ruleKEdge EOF
            {
             newCompositeNode(grammarAccess.getKEdgeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_entryRuleKEdge1075);
            iv_ruleKEdge=ruleKEdge();

            state._fsp--;

             current =iv_ruleKEdge; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEdge1085); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:572:1: ruleKEdge returns [EObject current=null] : (otherlv_0= '-->' otherlv_1= 'target' ( ( ruleEString ) ) otherlv_3= '{' (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' ) ;
    public final EObject ruleKEdge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        EObject lv_data_10_0 = null;

        EObject lv_data_12_0 = null;

        EObject lv_labels_15_0 = null;

        EObject lv_labels_17_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:575:28: ( (otherlv_0= '-->' otherlv_1= 'target' ( ( ruleEString ) ) otherlv_3= '{' (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:576:1: (otherlv_0= '-->' otherlv_1= 'target' ( ( ruleEString ) ) otherlv_3= '{' (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:576:1: (otherlv_0= '-->' otherlv_1= 'target' ( ( ruleEString ) ) otherlv_3= '{' (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:576:3: otherlv_0= '-->' otherlv_1= 'target' ( ( ruleEString ) ) otherlv_3= '{' (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}'
            {
            otherlv_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKEdge1122); 

                	newLeafNode(otherlv_0, grammarAccess.getKEdgeAccess().getHyphenMinusHyphenMinusGreaterThanSignKeyword_0());
                
            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKEdge1134); 

                	newLeafNode(otherlv_1, grammarAccess.getKEdgeAccess().getTargetKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:584:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:585:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:585:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:586:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKEdgeRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetKNodeCrossReference_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1157);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdge1169); 

                	newLeafNode(otherlv_3, grammarAccess.getKEdgeAccess().getLeftCurlyBracketKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:603:1: (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==25) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:603:3: otherlv_4= 'sourcePort' ( ( ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKEdge1182); 

                        	newLeafNode(otherlv_4, grammarAccess.getKEdgeAccess().getSourcePortKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:607:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:608:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:608:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:609:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getSourcePortKPortCrossReference_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1205);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:622:4: (otherlv_6= 'targetPort' ( ( ruleEString ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==26) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:622:6: otherlv_6= 'targetPort' ( ( ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKEdge1220); 

                        	newLeafNode(otherlv_6, grammarAccess.getKEdgeAccess().getTargetPortKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:626:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:627:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:627:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:628:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetPortKPortCrossReference_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1243);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:641:4: (otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==15) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:641:6: otherlv_8= 'data' otherlv_9= ':' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )*
                    {
                    otherlv_8=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEdge1258); 

                        	newLeafNode(otherlv_8, grammarAccess.getKEdgeAccess().getDataKeyword_6_0());
                        
                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdge1270); 

                        	newLeafNode(otherlv_9, grammarAccess.getKEdgeAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:649:1: ( (lv_data_10_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:650:1: (lv_data_10_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:650:1: (lv_data_10_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:651:3: lv_data_10_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKGraphDataParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKEdge1291);
                    lv_data_10_0=ruleKGraphData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_10_0, 
                            		"KGraphData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:667:2: (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==14) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:667:4: otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdge1304); 

                    	        	newLeafNode(otherlv_11, grammarAccess.getKEdgeAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:671:1: ( (lv_data_12_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:672:1: (lv_data_12_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:672:1: (lv_data_12_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:673:3: lv_data_12_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKGraphDataParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKEdge1325);
                    	    lv_data_12_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_12_0, 
                    	            		"KGraphData");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:689:6: (otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )* )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==22) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:689:8: otherlv_13= 'labels' otherlv_14= ':' ( (lv_labels_15_0= ruleKLabel ) ) (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )*
                    {
                    otherlv_13=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKEdge1342); 

                        	newLeafNode(otherlv_13, grammarAccess.getKEdgeAccess().getLabelsKeyword_7_0());
                        
                    otherlv_14=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdge1354); 

                        	newLeafNode(otherlv_14, grammarAccess.getKEdgeAccess().getColonKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:697:1: ( (lv_labels_15_0= ruleKLabel ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:698:1: (lv_labels_15_0= ruleKLabel )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:698:1: (lv_labels_15_0= ruleKLabel )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:699:3: lv_labels_15_0= ruleKLabel
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKEdge1375);
                    lv_labels_15_0=ruleKLabel();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	        }
                           		add(
                           			current, 
                           			"labels",
                            		lv_labels_15_0, 
                            		"KLabel");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:715:2: (otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) ) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==14) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:715:4: otherlv_16= ',' ( (lv_labels_17_0= ruleKLabel ) )
                    	    {
                    	    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdge1388); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKEdgeAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:719:1: ( (lv_labels_17_0= ruleKLabel ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:720:1: (lv_labels_17_0= ruleKLabel )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:720:1: (lv_labels_17_0= ruleKLabel )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:721:3: lv_labels_17_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKEdge1409);
                    	    lv_labels_17_0=ruleKLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_17_0, 
                    	            		"KLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdge1425); 

                	newLeafNode(otherlv_18, grammarAccess.getKEdgeAccess().getRightCurlyBracketKeyword_8());
                

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


    // $ANTLR start "entryRuleKGraphData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:749:1: entryRuleKGraphData returns [EObject current=null] : iv_ruleKGraphData= ruleKGraphData EOF ;
    public final EObject entryRuleKGraphData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGraphData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:750:2: (iv_ruleKGraphData= ruleKGraphData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:751:2: iv_ruleKGraphData= ruleKGraphData EOF
            {
             newCompositeNode(grammarAccess.getKGraphDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_entryRuleKGraphData1461);
            iv_ruleKGraphData=ruleKGraphData();

            state._fsp--;

             current =iv_ruleKGraphData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGraphData1471); 

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
    // $ANTLR end "entryRuleKGraphData"


    // $ANTLR start "ruleKGraphData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:758:1: ruleKGraphData returns [EObject current=null] : (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout ) ;
    public final EObject ruleKGraphData() throws RecognitionException {
        EObject current = null;

        EObject this_KRendering_0 = null;

        EObject this_KShapeLayout_1 = null;

        EObject this_KEdgeLayout_2 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:761:28: ( (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:762:1: (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:762:1: (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout )
            int alt23=3;
            switch ( input.LA(1) ) {
            case 27:
            case 34:
            case 36:
            case 37:
            case 40:
            case 41:
            case 42:
            case 45:
            case 48:
            case 49:
            case 52:
            case 54:
                {
                alt23=1;
                }
                break;
            case 104:
                {
                alt23=2;
                }
                break;
            case 114:
                {
                alt23=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:763:5: this_KRendering_0= ruleKRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKRenderingParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKGraphData1518);
                    this_KRendering_0=ruleKRendering();

                    state._fsp--;

                     
                            current = this_KRendering_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:773:5: this_KShapeLayout_1= ruleKShapeLayout
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKShapeLayoutParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_ruleKGraphData1545);
                    this_KShapeLayout_1=ruleKShapeLayout();

                    state._fsp--;

                     
                            current = this_KShapeLayout_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:783:5: this_KEdgeLayout_2= ruleKEdgeLayout
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKEdgeLayoutParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEdgeLayout_in_ruleKGraphData1572);
                    this_KEdgeLayout_2=ruleKEdgeLayout();

                    state._fsp--;

                     
                            current = this_KEdgeLayout_2; 
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
    // $ANTLR end "ruleKGraphData"


    // $ANTLR start "entryRuleKRendering"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:801:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:802:2: (iv_ruleKRendering= ruleKRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:803:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_entryRuleKRendering1609);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRendering1619); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:810:1: ruleKRendering returns [EObject current=null] : (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) ;
    public final EObject ruleKRendering() throws RecognitionException {
        EObject current = null;

        EObject this_KEllipse_0 = null;

        EObject this_KRectangle_1 = null;

        EObject this_KRoundedRectangle_2 = null;

        EObject this_KPolyline_Impl_3 = null;

        EObject this_KPolygon_4 = null;

        EObject this_KImage_5 = null;

        EObject this_KArc_6 = null;

        EObject this_KRenderingRef_7 = null;

        EObject this_KChildArea_8 = null;

        EObject this_KText_9 = null;

        EObject this_KCustomRendering_10 = null;

        EObject this_KSpline_11 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:813:28: ( (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:814:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:814:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            int alt24=12;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt24=1;
                }
                break;
            case 36:
                {
                alt24=2;
                }
                break;
            case 37:
                {
                alt24=3;
                }
                break;
            case 40:
                {
                alt24=4;
                }
                break;
            case 41:
                {
                alt24=5;
                }
                break;
            case 42:
                {
                alt24=6;
                }
                break;
            case 45:
                {
                alt24=7;
                }
                break;
            case 27:
                {
                alt24=8;
                }
                break;
            case 48:
                {
                alt24=9;
                }
                break;
            case 49:
                {
                alt24=10;
                }
                break;
            case 52:
                {
                alt24=11;
                }
                break;
            case 54:
                {
                alt24=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:815:5: this_KEllipse_0= ruleKEllipse
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKEllipseParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_ruleKRendering1666);
                    this_KEllipse_0=ruleKEllipse();

                    state._fsp--;

                     
                            current = this_KEllipse_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:825:5: this_KRectangle_1= ruleKRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRectangleParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_ruleKRendering1693);
                    this_KRectangle_1=ruleKRectangle();

                    state._fsp--;

                     
                            current = this_KRectangle_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:835:5: this_KRoundedRectangle_2= ruleKRoundedRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRoundedRectangleParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_ruleKRendering1720);
                    this_KRoundedRectangle_2=ruleKRoundedRectangle();

                    state._fsp--;

                     
                            current = this_KRoundedRectangle_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:845:5: this_KPolyline_Impl_3= ruleKPolyline_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolyline_ImplParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_ruleKRendering1747);
                    this_KPolyline_Impl_3=ruleKPolyline_Impl();

                    state._fsp--;

                     
                            current = this_KPolyline_Impl_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:855:5: this_KPolygon_4= ruleKPolygon
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolygonParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_ruleKRendering1774);
                    this_KPolygon_4=ruleKPolygon();

                    state._fsp--;

                     
                            current = this_KPolygon_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:865:5: this_KImage_5= ruleKImage
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKImageParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKImage_in_ruleKRendering1801);
                    this_KImage_5=ruleKImage();

                    state._fsp--;

                     
                            current = this_KImage_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:875:5: this_KArc_6= ruleKArc
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKArcParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKArc_in_ruleKRendering1828);
                    this_KArc_6=ruleKArc();

                    state._fsp--;

                     
                            current = this_KArc_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:885:5: this_KRenderingRef_7= ruleKRenderingRef
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRenderingRefParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_ruleKRendering1855);
                    this_KRenderingRef_7=ruleKRenderingRef();

                    state._fsp--;

                     
                            current = this_KRenderingRef_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:895:5: this_KChildArea_8= ruleKChildArea
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKChildAreaParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_ruleKRendering1882);
                    this_KChildArea_8=ruleKChildArea();

                    state._fsp--;

                     
                            current = this_KChildArea_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:905:5: this_KText_9= ruleKText
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKTextParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKText_in_ruleKRendering1909);
                    this_KText_9=ruleKText();

                    state._fsp--;

                     
                            current = this_KText_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:915:5: this_KCustomRendering_10= ruleKCustomRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKCustomRenderingParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_ruleKRendering1936);
                    this_KCustomRendering_10=ruleKCustomRendering();

                    state._fsp--;

                     
                            current = this_KCustomRendering_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:925:5: this_KSpline_11= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSplineParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_ruleKRendering1963);
                    this_KSpline_11=ruleKSpline();

                    state._fsp--;

                     
                            current = this_KSpline_11; 
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


    // $ANTLR start "entryRuleKPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:941:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:942:2: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:943:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData1998);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacementData2008); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:950:1: ruleKPlacementData returns [EObject current=null] : (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KDecoratorPlacementData_0 = null;

        EObject this_KGridPlacementData_1 = null;

        EObject this_KStackPlacementData_2 = null;

        EObject this_KDirectPlacementData_3 = null;

        EObject this_KPolylinePlacementData_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:953:28: ( (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:954:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:954:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            int alt25=5;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt25=1;
                }
                break;
            case 62:
                {
                alt25=2;
                }
                break;
            case 67:
                {
                alt25=3;
                }
                break;
            case 72:
                {
                alt25=4;
                }
                break;
            case 75:
                {
                alt25=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:955:5: this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData2055);
                    this_KDecoratorPlacementData_0=ruleKDecoratorPlacementData();

                    state._fsp--;

                     
                            current = this_KDecoratorPlacementData_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:965:5: this_KGridPlacementData_1= ruleKGridPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData2082);
                    this_KGridPlacementData_1=ruleKGridPlacementData();

                    state._fsp--;

                     
                            current = this_KGridPlacementData_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:975:5: this_KStackPlacementData_2= ruleKStackPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKStackPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData2109);
                    this_KStackPlacementData_2=ruleKStackPlacementData();

                    state._fsp--;

                     
                            current = this_KStackPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:985:5: this_KDirectPlacementData_3= ruleKDirectPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDirectPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData2136);
                    this_KDirectPlacementData_3=ruleKDirectPlacementData();

                    state._fsp--;

                     
                            current = this_KDirectPlacementData_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:995:5: this_KPolylinePlacementData_4= ruleKPolylinePlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPolylinePlacementDataParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData2163);
                    this_KPolylinePlacementData_4=ruleKPolylinePlacementData();

                    state._fsp--;

                     
                            current = this_KPolylinePlacementData_4; 
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


    // $ANTLR start "entryRuleKStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1011:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1012:2: (iv_ruleKStyle= ruleKStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1013:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_entryRuleKStyle2198);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle2208); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1020:1: ruleKStyle returns [EObject current=null] : (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment ) ;
    public final EObject ruleKStyle() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundColor_0 = null;

        EObject this_KBackgroundColor_1 = null;

        EObject this_KLineWidth_2 = null;

        EObject this_KVisibility_3 = null;

        EObject this_KLineStyle_4 = null;

        EObject this_KVerticalAlignment_5 = null;

        EObject this_KHorizontalAlignment_6 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1023:28: ( (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1024:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1024:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment )
            int alt26=7;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt26=1;
                }
                break;
            case 94:
                {
                alt26=2;
                }
                break;
            case 95:
                {
                alt26=3;
                }
                break;
            case 96:
            case 98:
                {
                alt26=4;
                }
                break;
            case 99:
                {
                alt26=5;
                }
                break;
            case 100:
                {
                alt26=6;
                }
                break;
            case 101:
                {
                alt26=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1025:5: this_KForegroundColor_0= ruleKForegroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundColorParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_ruleKStyle2255);
                    this_KForegroundColor_0=ruleKForegroundColor();

                    state._fsp--;

                     
                            current = this_KForegroundColor_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1035:5: this_KBackgroundColor_1= ruleKBackgroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKBackgroundColorParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_ruleKStyle2282);
                    this_KBackgroundColor_1=ruleKBackgroundColor();

                    state._fsp--;

                     
                            current = this_KBackgroundColor_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1045:5: this_KLineWidth_2= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle2309);
                    this_KLineWidth_2=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1055:5: this_KVisibility_3= ruleKVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVisibilityParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_ruleKStyle2336);
                    this_KVisibility_3=ruleKVisibility();

                    state._fsp--;

                     
                            current = this_KVisibility_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1065:5: this_KLineStyle_4= ruleKLineStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_ruleKStyle2363);
                    this_KLineStyle_4=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1075:5: this_KVerticalAlignment_5= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle2390);
                    this_KVerticalAlignment_5=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1085:5: this_KHorizontalAlignment_6= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle2417);
                    this_KHorizontalAlignment_6=ruleKHorizontalAlignment();

                    state._fsp--;

                     
                            current = this_KHorizontalAlignment_6; 
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
    // $ANTLR end "ruleKStyle"


    // $ANTLR start "entryRuleKPlacement"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1101:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1102:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1103:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_entryRuleKPlacement2452);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacement2462); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1110:1: ruleKPlacement returns [EObject current=null] : (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;

        EObject this_KStackPlacement_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1113:28: ( (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1114:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1114:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==102) ) {
                alt27=1;
            }
            else if ( (LA27_0==103) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1115:5: this_KGridPlacement_0= ruleKGridPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement2509);
                    this_KGridPlacement_0=ruleKGridPlacement();

                    state._fsp--;

                     
                            current = this_KGridPlacement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1125:5: this_KStackPlacement_1= ruleKStackPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKStackPlacementParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_ruleKPlacement2536);
                    this_KStackPlacement_1=ruleKStackPlacement();

                    state._fsp--;

                     
                            current = this_KStackPlacement_1; 
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
    // $ANTLR end "ruleKPlacement"


    // $ANTLR start "entryRuleKXPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1141:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1142:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1143:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition2571);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition2581); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1150:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1153:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1154:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1154:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==84) ) {
                alt28=1;
            }
            else if ( (LA28_0==86) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1155:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition2628);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1165:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition2655);
                    this_KRightPosition_1=ruleKRightPosition();

                    state._fsp--;

                     
                            current = this_KRightPosition_1; 
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
    // $ANTLR end "ruleKXPosition"


    // $ANTLR start "entryRuleKYPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1181:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1182:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1183:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition2690);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition2700); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1190:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1193:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1194:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1194:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==87) ) {
                alt29=1;
            }
            else if ( (LA29_0==88) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1195:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition2747);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1205:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition2774);
                    this_KBottomPosition_1=ruleKBottomPosition();

                    state._fsp--;

                     
                            current = this_KBottomPosition_1; 
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
    // $ANTLR end "ruleKYPosition"


    // $ANTLR start "entryRuleKRenderingRef"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1221:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1222:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1223:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef2809);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef2819); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1230:1: ruleKRenderingRef returns [EObject current=null] : (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
    public final EObject ruleKRenderingRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        EObject lv_placementData_11_0 = null;

        EObject lv_styles_14_0 = null;

        EObject lv_styles_16_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1233:28: ( (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1234:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1234:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1234:3: otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            otherlv_0=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKRenderingRef2856); 

                	newLeafNode(otherlv_0, grammarAccess.getKRenderingRefAccess().getKRenderingRefKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef2868); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1242:1: (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==28) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1242:3: otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')'
                    {
                    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKRenderingRef2881); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRenderingRefAccess().getReferencesKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKRenderingRef2893); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftParenthesisKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1250:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1251:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1251:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1252:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef2916);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1265:2: (otherlv_5= ',' ( ( ruleEString ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==14) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1265:4: otherlv_5= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_5=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef2929); 

                    	        	newLeafNode(otherlv_5, grammarAccess.getKRenderingRefAccess().getCommaKeyword_2_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1269:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1270:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1270:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1271:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef2952);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKRenderingRef2966); 

                        	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getRightParenthesisKeyword_2_4());
                        

                    }
                    break;

            }

            otherlv_8=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKRenderingRef2980); 

                	newLeafNode(otherlv_8, grammarAccess.getKRenderingRefAccess().getRenderingKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1292:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1293:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1293:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1294:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef3003);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1307:2: (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==32) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1307:4: otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) )
                    {
                    otherlv_10=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKRenderingRef3016); 

                        	newLeafNode(otherlv_10, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1311:1: ( (lv_placementData_11_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1312:1: (lv_placementData_11_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1312:1: (lv_placementData_11_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1313:3: lv_placementData_11_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef3037);
                    lv_placementData_11_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRenderingRefRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_11_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1329:4: (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==33) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1329:6: otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKRenderingRef3052); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRenderingRefAccess().getStylesKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef3064); 

                        	newLeafNode(otherlv_13, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1337:1: ( (lv_styles_14_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1338:1: (lv_styles_14_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1338:1: (lv_styles_14_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1339:3: lv_styles_14_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef3085);
                    lv_styles_14_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRenderingRefRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_14_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1355:2: (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==14) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1355:4: otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) )
                    	    {
                    	    otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef3098); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getKRenderingRefAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1359:1: ( (lv_styles_16_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1360:1: (lv_styles_16_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1360:1: (lv_styles_16_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1361:3: lv_styles_16_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef3119);
                    	    lv_styles_16_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRenderingRefRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_16_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef3133); 

                        	newLeafNode(otherlv_17, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef3147); 

                	newLeafNode(otherlv_18, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "entryRuleKEllipse"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1393:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1394:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1395:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse3183);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse3193); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1402:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
    public final EObject ruleKEllipse() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_styles_5_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_placementData_10_0 = null;

        EObject lv_childPlacement_13_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_children_18_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1405:28: ( ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1406:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1406:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1406:2: () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1406:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1407:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKEllipse3239); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getKEllipseKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse3251); 

                	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1420:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==33) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1420:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKEllipse3264); 

                        	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3276); 

                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1428:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1429:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1429:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1430:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse3297);
                    lv_styles_5_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_5_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1446:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==14) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1446:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse3310); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1450:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1451:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1451:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1452:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse3331);
                    	    lv_styles_7_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_7_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1468:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==32) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1468:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKEllipse3348); 

                        	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3360); 

                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1476:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1477:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1477:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1478:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse3381);
                    lv_placementData_10_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_10_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1494:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==35) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1494:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKEllipse3396); 

                        	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3408); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1502:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1503:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1503:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1504:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse3429);
                    lv_childPlacement_13_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_13_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1520:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==17) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1520:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse3444); 

                        	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3456); 

                        	newLeafNode(otherlv_15, grammarAccess.getKEllipseAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1528:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1529:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1529:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1530:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse3477);
                    lv_children_16_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_16_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1546:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==14) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1546:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse3490); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1550:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1551:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1551:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1552:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse3511);
                    	    lv_children_18_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_18_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse3527); 

                	newLeafNode(otherlv_19, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "entryRuleKRectangle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1580:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1581:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1582:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle3563);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle3573); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1589:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
    public final EObject ruleKRectangle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_styles_5_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_placementData_10_0 = null;

        EObject lv_childPlacement_13_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_children_18_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1592:28: ( ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1593:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1593:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1593:2: () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1593:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1594:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKRectangle3619); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getKRectangleKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle3631); 

                	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1607:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==33) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1607:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKRectangle3644); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle3656); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1615:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1616:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1616:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1617:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle3677);
                    lv_styles_5_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_5_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1633:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==14) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1633:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle3690); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1637:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1638:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1638:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1639:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle3711);
                    	    lv_styles_7_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_7_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1655:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==32) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1655:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKRectangle3728); 

                        	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle3740); 

                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1663:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1664:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1664:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1665:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle3761);
                    lv_placementData_10_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_10_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1681:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==35) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1681:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKRectangle3776); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle3788); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1689:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1690:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1690:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1691:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle3809);
                    lv_childPlacement_13_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_13_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1707:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==17) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1707:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle3824); 

                        	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle3836); 

                        	newLeafNode(otherlv_15, grammarAccess.getKRectangleAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1715:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1716:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1716:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1717:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle3857);
                    lv_children_16_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_16_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1733:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==14) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1733:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle3870); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1737:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1738:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1738:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1739:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle3891);
                    	    lv_children_18_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_18_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle3907); 

                	newLeafNode(otherlv_19, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_7());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1767:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1768:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1769:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle3943);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle3953); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1776:1: ruleKRoundedRectangle returns [EObject current=null] : (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' ) ;
    public final EObject ruleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        AntlrDatatypeRuleToken lv_cornerWidth_3_0 = null;

        AntlrDatatypeRuleToken lv_cornerHeight_5_0 = null;

        AntlrDatatypeRuleToken lv_cornerHeight_7_0 = null;

        AntlrDatatypeRuleToken lv_cornerWidth_9_0 = null;

        EObject lv_styles_12_0 = null;

        EObject lv_styles_14_0 = null;

        EObject lv_placementData_17_0 = null;

        EObject lv_childPlacement_20_0 = null;

        EObject lv_children_23_0 = null;

        EObject lv_children_25_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1779:28: ( (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1780:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1780:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1780:3: otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}'
            {
            otherlv_0=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKRoundedRectangle3990); 

                	newLeafNode(otherlv_0, grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle4002); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1788:1: ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==38) ) {
                alt47=1;
            }
            else if ( (LA47_0==39) ) {
                alt47=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1788:2: (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1788:2: (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1788:4: otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) )
                    {
                    otherlv_2=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKRoundedRectangle4016); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRoundedRectangleAccess().getCornerWidthKeyword_2_0_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1792:1: ( (lv_cornerWidth_3_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1793:1: (lv_cornerWidth_3_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1793:1: (lv_cornerWidth_3_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1794:3: lv_cornerWidth_3_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_0_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4037);
                    lv_cornerWidth_3_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"cornerWidth",
                            		lv_cornerWidth_3_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_4=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKRoundedRectangle4049); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRoundedRectangleAccess().getCornerHeightKeyword_2_0_2());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1814:1: ( (lv_cornerHeight_5_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1815:1: (lv_cornerHeight_5_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1815:1: (lv_cornerHeight_5_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1816:3: lv_cornerHeight_5_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_2_0_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4070);
                    lv_cornerHeight_5_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"cornerHeight",
                            		lv_cornerHeight_5_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1833:6: (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1833:6: (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1833:8: otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) )
                    {
                    otherlv_6=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKRoundedRectangle4090); 

                        	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getCornerHeightKeyword_2_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1837:1: ( (lv_cornerHeight_7_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1838:1: (lv_cornerHeight_7_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1838:1: (lv_cornerHeight_7_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1839:3: lv_cornerHeight_7_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_2_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4111);
                    lv_cornerHeight_7_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"cornerHeight",
                            		lv_cornerHeight_7_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_8=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKRoundedRectangle4123); 

                        	newLeafNode(otherlv_8, grammarAccess.getKRoundedRectangleAccess().getCornerWidthKeyword_2_1_2());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1859:1: ( (lv_cornerWidth_9_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1860:1: (lv_cornerWidth_9_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1860:1: (lv_cornerWidth_9_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1861:3: lv_cornerWidth_9_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_1_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4144);
                    lv_cornerWidth_9_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"cornerWidth",
                            		lv_cornerWidth_9_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1877:4: (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==33) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1877:6: otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )*
                    {
                    otherlv_10=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKRoundedRectangle4159); 

                        	newLeafNode(otherlv_10, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_3_0());
                        
                    otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4171); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1885:1: ( (lv_styles_12_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1886:1: (lv_styles_12_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1886:1: (lv_styles_12_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1887:3: lv_styles_12_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4192);
                    lv_styles_12_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_12_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1903:2: (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==14) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1903:4: otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) )
                    	    {
                    	    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle4205); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1907:1: ( (lv_styles_14_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1908:1: (lv_styles_14_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1908:1: (lv_styles_14_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1909:3: lv_styles_14_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4226);
                    	    lv_styles_14_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_14_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1925:6: (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==32) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1925:8: otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) )
                    {
                    otherlv_15=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKRoundedRectangle4243); 

                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_16=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4255); 

                        	newLeafNode(otherlv_16, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1933:1: ( (lv_placementData_17_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1934:1: (lv_placementData_17_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1934:1: (lv_placementData_17_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1935:3: lv_placementData_17_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle4276);
                    lv_placementData_17_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_17_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1951:4: (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==35) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1951:6: otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                    {
                    otherlv_18=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKRoundedRectangle4291); 

                        	newLeafNode(otherlv_18, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_19=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4303); 

                        	newLeafNode(otherlv_19, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1959:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1960:1: (lv_childPlacement_20_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1960:1: (lv_childPlacement_20_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1961:3: lv_childPlacement_20_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle4324);
                    lv_childPlacement_20_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_20_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1977:4: (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==17) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1977:6: otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    {
                    otherlv_21=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle4339); 

                        	newLeafNode(otherlv_21, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_6_0());
                        
                    otherlv_22=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4351); 

                        	newLeafNode(otherlv_22, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1985:1: ( (lv_children_23_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1986:1: (lv_children_23_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1986:1: (lv_children_23_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1987:3: lv_children_23_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4372);
                    lv_children_23_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_23_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2003:2: (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==14) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2003:4: otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) )
                    	    {
                    	    otherlv_24=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle4385); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2007:1: ( (lv_children_25_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2008:1: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2008:1: (lv_children_25_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2009:3: lv_children_25_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4406);
                    	    lv_children_25_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_25_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_26=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle4422); 

                	newLeafNode(otherlv_26, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "entryRuleKPolyline_Impl"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2037:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2038:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2039:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl4458);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl4468); 

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
    // $ANTLR end "entryRuleKPolyline_Impl"


    // $ANTLR start "ruleKPolyline_Impl"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2046:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
    public final EObject ruleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_styles_5_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_placementData_10_0 = null;

        EObject lv_childPlacement_13_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_children_18_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2049:28: ( ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2050:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2050:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2050:2: () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2050:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2051:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKPolyline_Impl4514); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getKPolylineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl4526); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2064:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==33) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2064:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKPolyline_Impl4539); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4551); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2072:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2073:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2073:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2074:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4572);
                    lv_styles_5_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_5_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2090:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==14) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2090:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl4585); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2094:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2095:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2095:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2096:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4606);
                    	    lv_styles_7_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_7_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop54;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2112:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==32) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2112:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKPolyline_Impl4623); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4635); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2120:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2121:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2121:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2122:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl4656);
                    lv_placementData_10_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_10_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2138:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==35) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2138:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKPolyline_Impl4671); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4683); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2146:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2147:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2147:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2148:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl4704);
                    lv_childPlacement_13_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_13_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2164:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==17) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2164:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl4719); 

                        	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4731); 

                        	newLeafNode(otherlv_15, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2172:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2173:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2173:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2174:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl4752);
                    lv_children_16_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_16_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2190:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==14) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2190:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl4765); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2194:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2195:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2195:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2196:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl4786);
                    	    lv_children_18_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_18_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl4802); 

                	newLeafNode(otherlv_19, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_7());
                

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
    // $ANTLR end "ruleKPolyline_Impl"


    // $ANTLR start "entryRuleKPolygon"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2224:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2225:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2226:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon4838);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon4848); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2233:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
    public final EObject ruleKPolygon() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_styles_5_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_placementData_10_0 = null;

        EObject lv_childPlacement_13_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_children_18_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2236:28: ( ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2237:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2237:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2237:2: () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2237:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2238:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKPolygon4894); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getKPolygonKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon4906); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2251:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==33) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2251:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKPolygon4919); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon4931); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2259:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2260:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2260:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2261:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4952);
                    lv_styles_5_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_5_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2277:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==14) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2277:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon4965); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2281:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2282:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2282:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2283:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4986);
                    	    lv_styles_7_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_7_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2299:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==32) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2299:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKPolygon5003); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5015); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2307:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2308:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2308:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2309:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon5036);
                    lv_placementData_10_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_10_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2325:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==35) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2325:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKPolygon5051); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5063); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2333:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2334:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2334:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2335:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon5084);
                    lv_childPlacement_13_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_13_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2351:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==17) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2351:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon5099); 

                        	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5111); 

                        	newLeafNode(otherlv_15, grammarAccess.getKPolygonAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2359:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2360:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2360:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2361:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon5132);
                    lv_children_16_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_16_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2377:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==14) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2377:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon5145); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2381:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2382:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2382:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2383:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon5166);
                    	    lv_children_18_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_18_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon5182); 

                	newLeafNode(otherlv_19, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "entryRuleKImage"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2411:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2412:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2413:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage5218);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage5228); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2420:1: ruleKImage returns [EObject current=null] : (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
    public final EObject ruleKImage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        AntlrDatatypeRuleToken lv_bundleName_3_0 = null;

        AntlrDatatypeRuleToken lv_imagePath_5_0 = null;

        EObject lv_placementData_13_0 = null;

        EObject lv_styles_16_0 = null;

        EObject lv_styles_18_0 = null;

        EObject lv_children_22_0 = null;

        EObject lv_children_24_0 = null;

        EObject lv_childPlacement_27_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2423:28: ( (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2424:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2424:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2424:3: otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKImage5265); 

                	newLeafNode(otherlv_0, grammarAccess.getKImageAccess().getKImageKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage5277); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKImage5289); 

                	newLeafNode(otherlv_2, grammarAccess.getKImageAccess().getBundleNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2436:1: ( (lv_bundleName_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2437:1: (lv_bundleName_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2437:1: (lv_bundleName_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2438:3: lv_bundleName_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage5310);
            lv_bundleName_3_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKImageRule());
            	        }
                   		set(
                   			current, 
                   			"bundleName",
                    		lv_bundleName_3_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKImage5322); 

                	newLeafNode(otherlv_4, grammarAccess.getKImageAccess().getImagePathKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2458:1: ( (lv_imagePath_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2459:1: (lv_imagePath_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2459:1: (lv_imagePath_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2460:3: lv_imagePath_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage5343);
            lv_imagePath_5_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKImageRule());
            	        }
                   		set(
                   			current, 
                   			"imagePath",
                    		lv_imagePath_5_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2476:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==28) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2476:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKImage5356); 

                        	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKImage5368); 

                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2484:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2485:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2485:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2486:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKImageRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage5391);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2499:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==14) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2499:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage5404); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2503:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2504:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2504:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2505:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKImageRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage5427);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKImage5441); 

                        	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2522:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==32) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2522:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKImage5456); 

                        	newLeafNode(otherlv_12, grammarAccess.getKImageAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2526:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2527:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2527:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2528:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage5477);
                    lv_placementData_13_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_13_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2544:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==33) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2544:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKImage5492); 

                        	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage5504); 

                        	newLeafNode(otherlv_15, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2552:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2553:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2553:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2554:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage5525);
                    lv_styles_16_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_16_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2570:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==14) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2570:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage5538); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKImageAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2574:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2575:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2575:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2576:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage5559);
                    	    lv_styles_18_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_18_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage5573); 

                        	newLeafNode(otherlv_19, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2596:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==17) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2596:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage5588); 

                        	newLeafNode(otherlv_20, grammarAccess.getKImageAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage5600); 

                        	newLeafNode(otherlv_21, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2604:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2605:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2605:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2606:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage5621);
                    lv_children_22_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_22_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2622:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==14) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2622:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage5634); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKImageAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2626:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2627:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2627:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2628:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage5655);
                    	    lv_children_24_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_24_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage5669); 

                        	newLeafNode(otherlv_25, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2648:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==35) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2648:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKImage5684); 

                        	newLeafNode(otherlv_26, grammarAccess.getKImageAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2652:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2653:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2653:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2654:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage5705);
                    lv_childPlacement_27_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_27_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_28=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage5719); 

                	newLeafNode(otherlv_28, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_11());
                

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


    // $ANTLR start "entryRuleKArc"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2682:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2683:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2684:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc5755);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc5765); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2691:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) ;
    public final EObject ruleKArc() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token otherlv_29=null;
        AntlrDatatypeRuleToken lv_startAngle_4_0 = null;

        AntlrDatatypeRuleToken lv_arcAngle_6_0 = null;

        EObject lv_placementData_14_0 = null;

        EObject lv_styles_17_0 = null;

        EObject lv_styles_19_0 = null;

        EObject lv_children_23_0 = null;

        EObject lv_children_25_0 = null;

        EObject lv_childPlacement_28_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2694:28: ( ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2695:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2695:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2695:2: () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2695:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2696:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKArc5811); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getKArcKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc5823); 

                	newLeafNode(otherlv_2, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2709:1: (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==46) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2709:3: otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKArc5836); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getStartAngleKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2713:1: ( (lv_startAngle_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2714:1: (lv_startAngle_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2714:1: (lv_startAngle_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2715:3: lv_startAngle_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc5857);
                    lv_startAngle_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	        }
                           		set(
                           			current, 
                           			"startAngle",
                            		lv_startAngle_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2731:4: (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==47) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2731:6: otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKArc5872); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getArcAngleKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2735:1: ( (lv_arcAngle_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2736:1: (lv_arcAngle_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2736:1: (lv_arcAngle_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2737:3: lv_arcAngle_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc5893);
                    lv_arcAngle_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	        }
                           		set(
                           			current, 
                           			"arcAngle",
                            		lv_arcAngle_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2753:4: (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==28) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2753:6: otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKArc5908); 

                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getReferencesKeyword_5_0());
                        
                    otherlv_8=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKArc5920); 

                        	newLeafNode(otherlv_8, grammarAccess.getKArcAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2761:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2762:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2762:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2763:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKArcRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc5943);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2776:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop76:
                    do {
                        int alt76=2;
                        int LA76_0 = input.LA(1);

                        if ( (LA76_0==14) ) {
                            alt76=1;
                        }


                        switch (alt76) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2776:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5956); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getKArcAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2780:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2781:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2781:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2782:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKArcRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc5979);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop76;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKArc5993); 

                        	newLeafNode(otherlv_12, grammarAccess.getKArcAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2799:3: (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==32) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2799:5: otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) )
                    {
                    otherlv_13=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKArc6008); 

                        	newLeafNode(otherlv_13, grammarAccess.getKArcAccess().getPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2803:1: ( (lv_placementData_14_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2804:1: (lv_placementData_14_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2804:1: (lv_placementData_14_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2805:3: lv_placementData_14_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc6029);
                    lv_placementData_14_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_14_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2821:4: (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==33) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2821:6: otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}'
                    {
                    otherlv_15=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKArc6044); 

                        	newLeafNode(otherlv_15, grammarAccess.getKArcAccess().getStylesKeyword_7_0());
                        
                    otherlv_16=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc6056); 

                        	newLeafNode(otherlv_16, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2829:1: ( (lv_styles_17_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2830:1: (lv_styles_17_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2830:1: (lv_styles_17_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2831:3: lv_styles_17_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc6077);
                    lv_styles_17_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_17_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2847:2: (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )*
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==14) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2847:4: otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) )
                    	    {
                    	    otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc6090); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2851:1: ( (lv_styles_19_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2852:1: (lv_styles_19_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2852:1: (lv_styles_19_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2853:3: lv_styles_19_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc6111);
                    	    lv_styles_19_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_19_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop79;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc6125); 

                        	newLeafNode(otherlv_20, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2873:3: (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==17) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2873:5: otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}'
                    {
                    otherlv_21=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc6140); 

                        	newLeafNode(otherlv_21, grammarAccess.getKArcAccess().getChildrenKeyword_8_0());
                        
                    otherlv_22=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc6152); 

                        	newLeafNode(otherlv_22, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2881:1: ( (lv_children_23_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2882:1: (lv_children_23_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2882:1: (lv_children_23_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2883:3: lv_children_23_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc6173);
                    lv_children_23_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_23_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2899:2: (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    loop81:
                    do {
                        int alt81=2;
                        int LA81_0 = input.LA(1);

                        if ( (LA81_0==14) ) {
                            alt81=1;
                        }


                        switch (alt81) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2899:4: otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) )
                    	    {
                    	    otherlv_24=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc6186); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getKArcAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2903:1: ( (lv_children_25_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2904:1: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2904:1: (lv_children_25_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2905:3: lv_children_25_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc6207);
                    	    lv_children_25_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_25_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop81;
                        }
                    } while (true);

                    otherlv_26=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc6221); 

                        	newLeafNode(otherlv_26, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2925:3: (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==35) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2925:5: otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    {
                    otherlv_27=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKArc6236); 

                        	newLeafNode(otherlv_27, grammarAccess.getKArcAccess().getChildPlacementKeyword_9_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2929:1: ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2930:1: (lv_childPlacement_28_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2930:1: (lv_childPlacement_28_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2931:3: lv_childPlacement_28_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc6257);
                    lv_childPlacement_28_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_28_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_29=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc6271); 

                	newLeafNode(otherlv_29, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_10());
                

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


    // $ANTLR start "entryRuleKChildArea"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2959:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2960:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2961:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea6307);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea6317); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2968:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) ;
    public final EObject ruleKChildArea() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        EObject lv_placementData_10_0 = null;

        EObject lv_styles_13_0 = null;

        EObject lv_styles_15_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2971:28: ( ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2972:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2972:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2972:2: () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2972:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2973:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKChildArea6363); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getKChildAreaKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea6375); 

                	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2986:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==28) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2986:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKChildArea6388); 

                        	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKChildArea6400); 

                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2994:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2995:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2995:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2996:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea6423);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3009:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop84:
                    do {
                        int alt84=2;
                        int LA84_0 = input.LA(1);

                        if ( (LA84_0==14) ) {
                            alt84=1;
                        }


                        switch (alt84) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3009:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea6436); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3013:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3014:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3014:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3015:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea6459);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop84;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKChildArea6473); 

                        	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3032:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==32) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3032:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKChildArea6488); 

                        	newLeafNode(otherlv_9, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3036:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3037:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3037:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3038:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea6509);
                    lv_placementData_10_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKChildAreaRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_10_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3054:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==33) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3054:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKChildArea6524); 

                        	newLeafNode(otherlv_11, grammarAccess.getKChildAreaAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea6536); 

                        	newLeafNode(otherlv_12, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3062:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3063:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3063:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3064:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea6557);
                    lv_styles_13_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKChildAreaRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_13_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3080:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==14) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3080:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea6570); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKChildAreaAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3084:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3085:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3085:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3086:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea6591);
                    	    lv_styles_15_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKChildAreaRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_15_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop87;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea6605); 

                        	newLeafNode(otherlv_16, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea6619); 

                	newLeafNode(otherlv_17, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_6());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3118:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3119:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3120:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText6655);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText6665); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3127:1: ruleKText returns [EObject current=null] : (otherlv_0= 'KText' otherlv_1= '{' ( (lv_clip_2_0= 'clip' ) ) (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) ;
    public final EObject ruleKText() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_clip_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        AntlrDatatypeRuleToken lv_text_4_0 = null;

        EObject lv_placementData_12_0 = null;

        EObject lv_styles_15_0 = null;

        EObject lv_styles_17_0 = null;

        EObject lv_children_21_0 = null;

        EObject lv_children_23_0 = null;

        EObject lv_childPlacement_26_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3130:28: ( (otherlv_0= 'KText' otherlv_1= '{' ( (lv_clip_2_0= 'clip' ) ) (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3131:1: (otherlv_0= 'KText' otherlv_1= '{' ( (lv_clip_2_0= 'clip' ) ) (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3131:1: (otherlv_0= 'KText' otherlv_1= '{' ( (lv_clip_2_0= 'clip' ) ) (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3131:3: otherlv_0= 'KText' otherlv_1= '{' ( (lv_clip_2_0= 'clip' ) ) (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}'
            {
            otherlv_0=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKText6702); 

                	newLeafNode(otherlv_0, grammarAccess.getKTextAccess().getKTextKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText6714); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3139:1: ( (lv_clip_2_0= 'clip' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3140:1: (lv_clip_2_0= 'clip' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3140:1: (lv_clip_2_0= 'clip' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3141:3: lv_clip_2_0= 'clip'
            {
            lv_clip_2_0=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKText6732); 

                    newLeafNode(lv_clip_2_0, grammarAccess.getKTextAccess().getClipClipKeyword_2_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKTextRule());
            	        }
                   		setWithLastConsumed(current, "clip", true, "clip");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3154:2: (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==51) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3154:4: otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKText6758); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getTextKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3158:1: ( (lv_text_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3159:1: (lv_text_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3159:1: (lv_text_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3160:3: lv_text_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText6779);
                    lv_text_4_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	        }
                           		set(
                           			current, 
                           			"text",
                            		lv_text_4_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3176:4: (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==28) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3176:6: otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')'
                    {
                    otherlv_5=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKText6794); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getReferencesKeyword_4_0());
                        
                    otherlv_6=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKText6806); 

                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getLeftParenthesisKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3184:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3185:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3185:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3186:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKTextRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText6829);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3199:2: (otherlv_8= ',' ( ( ruleEString ) ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==14) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3199:4: otherlv_8= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6842); 

                    	        	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3203:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3204:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3204:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3205:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKTextRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText6865);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop90;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKText6879); 

                        	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getRightParenthesisKeyword_4_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3222:3: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==32) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3222:5: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                    {
                    otherlv_11=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKText6894); 

                        	newLeafNode(otherlv_11, grammarAccess.getKTextAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3226:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3227:1: (lv_placementData_12_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3227:1: (lv_placementData_12_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3228:3: lv_placementData_12_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText6915);
                    lv_placementData_12_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_12_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3244:4: (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==33) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3244:6: otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}'
                    {
                    otherlv_13=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKText6930); 

                        	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getStylesKeyword_6_0());
                        
                    otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText6942); 

                        	newLeafNode(otherlv_14, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3252:1: ( (lv_styles_15_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3253:1: (lv_styles_15_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3253:1: (lv_styles_15_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3254:3: lv_styles_15_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText6963);
                    lv_styles_15_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_15_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3270:2: (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==14) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3270:4: otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) )
                    	    {
                    	    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6976); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKTextAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3274:1: ( (lv_styles_17_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3275:1: (lv_styles_17_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3275:1: (lv_styles_17_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3276:3: lv_styles_17_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText6997);
                    	    lv_styles_17_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_17_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText7011); 

                        	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3296:3: (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==17) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3296:5: otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}'
                    {
                    otherlv_19=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText7026); 

                        	newLeafNode(otherlv_19, grammarAccess.getKTextAccess().getChildrenKeyword_7_0());
                        
                    otherlv_20=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText7038); 

                        	newLeafNode(otherlv_20, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3304:1: ( (lv_children_21_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3305:1: (lv_children_21_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3305:1: (lv_children_21_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3306:3: lv_children_21_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText7059);
                    lv_children_21_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_21_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3322:2: (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==14) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3322:4: otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) )
                    	    {
                    	    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText7072); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getKTextAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3326:1: ( (lv_children_23_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3327:1: (lv_children_23_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3327:1: (lv_children_23_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3328:3: lv_children_23_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText7093);
                    	    lv_children_23_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_23_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop95;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText7107); 

                        	newLeafNode(otherlv_24, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3348:3: (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==35) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3348:5: otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    {
                    otherlv_25=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKText7122); 

                        	newLeafNode(otherlv_25, grammarAccess.getKTextAccess().getChildPlacementKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3352:1: ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3353:1: (lv_childPlacement_26_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3353:1: (lv_childPlacement_26_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3354:3: lv_childPlacement_26_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText7143);
                    lv_childPlacement_26_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_26_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_27=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText7157); 

                	newLeafNode(otherlv_27, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_9());
                

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


    // $ANTLR start "entryRuleKCustomRendering"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3382:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3383:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3384:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering7193);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering7203); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3391:1: ruleKCustomRendering returns [EObject current=null] : (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
    public final EObject ruleKCustomRendering() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        AntlrDatatypeRuleToken lv_className_3_0 = null;

        AntlrDatatypeRuleToken lv_bundleName_5_0 = null;

        EObject lv_placementData_13_0 = null;

        EObject lv_styles_16_0 = null;

        EObject lv_styles_18_0 = null;

        EObject lv_children_22_0 = null;

        EObject lv_children_24_0 = null;

        EObject lv_childPlacement_27_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3394:28: ( (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3395:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3395:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3395:3: otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKCustomRendering7240); 

                	newLeafNode(otherlv_0, grammarAccess.getKCustomRenderingAccess().getKCustomRenderingKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering7252); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKCustomRendering7264); 

                	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3407:1: ( (lv_className_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3408:1: (lv_className_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3408:1: (lv_className_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3409:3: lv_className_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering7285);
            lv_className_3_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
            	        }
                   		set(
                   			current, 
                   			"className",
                    		lv_className_3_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKCustomRendering7297); 

                	newLeafNode(otherlv_4, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3429:1: ( (lv_bundleName_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3430:1: (lv_bundleName_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3430:1: (lv_bundleName_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3431:3: lv_bundleName_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering7318);
            lv_bundleName_5_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
            	        }
                   		set(
                   			current, 
                   			"bundleName",
                    		lv_bundleName_5_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3447:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==28) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3447:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKCustomRendering7331); 

                        	newLeafNode(otherlv_6, grammarAccess.getKCustomRenderingAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKCustomRendering7343); 

                        	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3455:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3456:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3456:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3457:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering7366);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3470:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop98:
                    do {
                        int alt98=2;
                        int LA98_0 = input.LA(1);

                        if ( (LA98_0==14) ) {
                            alt98=1;
                        }


                        switch (alt98) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3470:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering7379); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3474:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3475:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3475:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3476:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering7402);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop98;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKCustomRendering7416); 

                        	newLeafNode(otherlv_11, grammarAccess.getKCustomRenderingAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3493:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==32) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3493:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKCustomRendering7431); 

                        	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3497:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3498:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3498:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3499:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering7452);
                    lv_placementData_13_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_13_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3515:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==33) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3515:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKCustomRendering7467); 

                        	newLeafNode(otherlv_14, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering7479); 

                        	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3523:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3524:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3524:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3525:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering7500);
                    lv_styles_16_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_16_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3541:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop101:
                    do {
                        int alt101=2;
                        int LA101_0 = input.LA(1);

                        if ( (LA101_0==14) ) {
                            alt101=1;
                        }


                        switch (alt101) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3541:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering7513); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3545:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3546:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3546:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3547:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering7534);
                    	    lv_styles_18_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_18_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop101;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering7548); 

                        	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3567:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==17) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3567:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering7563); 

                        	newLeafNode(otherlv_20, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering7575); 

                        	newLeafNode(otherlv_21, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3575:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3576:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3576:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3577:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering7596);
                    lv_children_22_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_22_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3593:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop103:
                    do {
                        int alt103=2;
                        int LA103_0 = input.LA(1);

                        if ( (LA103_0==14) ) {
                            alt103=1;
                        }


                        switch (alt103) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3593:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering7609); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3597:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3598:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3598:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3599:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering7630);
                    	    lv_children_24_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_24_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop103;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering7644); 

                        	newLeafNode(otherlv_25, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3619:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==35) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3619:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKCustomRendering7659); 

                        	newLeafNode(otherlv_26, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3623:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3624:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3624:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3625:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering7680);
                    lv_childPlacement_27_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_27_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_28=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering7694); 

                	newLeafNode(otherlv_28, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_11());
                

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


    // $ANTLR start "entryRuleKSpline"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3653:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3654:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3655:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline7730);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline7740); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3662:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
    public final EObject ruleKSpline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        EObject lv_placementData_10_0 = null;

        EObject lv_styles_13_0 = null;

        EObject lv_styles_15_0 = null;

        EObject lv_children_19_0 = null;

        EObject lv_children_21_0 = null;

        EObject lv_childPlacement_24_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3665:28: ( ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3666:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3666:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3666:2: () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3666:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3667:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKSpline7786); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getKSplineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline7798); 

                	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3680:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==28) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3680:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKSpline7811); 

                        	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKSpline7823); 

                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3688:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3689:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3689:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3690:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline7846);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3703:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop106:
                    do {
                        int alt106=2;
                        int LA106_0 = input.LA(1);

                        if ( (LA106_0==14) ) {
                            alt106=1;
                        }


                        switch (alt106) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3703:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7859); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3707:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3708:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3708:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3709:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline7882);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop106;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKSpline7896); 

                        	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3726:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==32) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3726:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKSpline7911); 

                        	newLeafNode(otherlv_9, grammarAccess.getKSplineAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3730:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3731:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3731:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3732:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline7932);
                    lv_placementData_10_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                    	        }
                           		set(
                           			current, 
                           			"placementData",
                            		lv_placementData_10_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3748:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==33) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3748:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKSpline7947); 

                        	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline7959); 

                        	newLeafNode(otherlv_12, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3756:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3757:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3757:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3758:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline7980);
                    lv_styles_13_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_13_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3774:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop109:
                    do {
                        int alt109=2;
                        int LA109_0 = input.LA(1);

                        if ( (LA109_0==14) ) {
                            alt109=1;
                        }


                        switch (alt109) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3774:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7993); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKSplineAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3778:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3779:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3779:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3780:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline8014);
                    	    lv_styles_15_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"styles",
                    	            		lv_styles_15_0, 
                    	            		"KStyle");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop109;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline8028); 

                        	newLeafNode(otherlv_16, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3800:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==17) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3800:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline8043); 

                        	newLeafNode(otherlv_17, grammarAccess.getKSplineAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline8055); 

                        	newLeafNode(otherlv_18, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3808:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3809:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3809:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3810:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline8076);
                    lv_children_19_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_19_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3826:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==14) ) {
                            alt111=1;
                        }


                        switch (alt111) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3826:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline8089); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKSplineAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3830:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3831:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3831:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3832:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline8110);
                    	    lv_children_21_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_21_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop111;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline8124); 

                        	newLeafNode(otherlv_22, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3852:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==35) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3852:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKSpline8139); 

                        	newLeafNode(otherlv_23, grammarAccess.getKSplineAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3856:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3857:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3857:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3858:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline8160);
                    lv_childPlacement_24_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                    	        }
                           		set(
                           			current, 
                           			"childPlacement",
                            		lv_childPlacement_24_0, 
                            		"KPlacement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline8174); 

                	newLeafNode(otherlv_25, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_8());
                

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


    // $ANTLR start "entryRuleKDecoratorPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3886:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3887:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3888:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData8210);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData8220); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3895:1: ruleKDecoratorPlacementData returns [EObject current=null] : (otherlv_0= 'KDecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) ) otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) ;
    public final EObject ruleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_relative_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_location_4_0 = null;

        AntlrDatatypeRuleToken lv_xOffset_6_0 = null;

        AntlrDatatypeRuleToken lv_yOffset_8_0 = null;

        AntlrDatatypeRuleToken lv_width_10_0 = null;

        AntlrDatatypeRuleToken lv_height_12_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3898:28: ( (otherlv_0= 'KDecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) ) otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3899:1: (otherlv_0= 'KDecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) ) otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3899:1: (otherlv_0= 'KDecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) ) otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3899:3: otherlv_0= 'KDecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) ) otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}'
            {
            otherlv_0=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKDecoratorPlacementData8257); 

                	newLeafNode(otherlv_0, grammarAccess.getKDecoratorPlacementDataAccess().getKDecoratorPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData8269); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3907:1: ( (lv_relative_2_0= 'relative' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3908:1: (lv_relative_2_0= 'relative' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3908:1: (lv_relative_2_0= 'relative' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3909:3: lv_relative_2_0= 'relative'
            {
            lv_relative_2_0=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKDecoratorPlacementData8287); 

                    newLeafNode(lv_relative_2_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_2_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
            	        }
                   		setWithLastConsumed(current, "relative", true, "relative");
            	    

            }


            }

            otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKDecoratorPlacementData8312); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3926:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3927:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3927:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3928:3: lv_location_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getLocationEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8333);
            lv_location_4_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"location",
                    		lv_location_4_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3944:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==58) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3944:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKDecoratorPlacementData8346); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3948:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3949:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3949:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3950:3: lv_xOffset_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8367);
                    lv_xOffset_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"xOffset",
                            		lv_xOffset_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3966:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==59) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3966:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKDecoratorPlacementData8382); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3970:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3971:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3971:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3972:3: lv_yOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8403);
                    lv_yOffset_8_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"yOffset",
                            		lv_yOffset_8_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3988:4: (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==60) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3988:6: otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKDecoratorPlacementData8418); 

                        	newLeafNode(otherlv_9, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3992:1: ( (lv_width_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3993:1: (lv_width_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3993:1: (lv_width_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3994:3: lv_width_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8439);
                    lv_width_10_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"width",
                            		lv_width_10_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4010:4: (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==61) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4010:6: otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKDecoratorPlacementData8454); 

                        	newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4014:1: ( (lv_height_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4015:1: (lv_height_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4015:1: (lv_height_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4016:3: lv_height_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8475);
                    lv_height_12_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"height",
                            		lv_height_12_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_13=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKDecoratorPlacementData8489); 

                	newLeafNode(otherlv_13, grammarAccess.getKDecoratorPlacementDataAccess().getRightCurlyBracketKeyword_9());
                

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


    // $ANTLR start "entryRuleKGridPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4044:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4045:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4046:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData8525);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData8535); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4053:1: ruleKGridPlacementData returns [EObject current=null] : (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
    public final EObject ruleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_widthHint_3_0 = null;

        AntlrDatatypeRuleToken lv_heightHint_5_0 = null;

        AntlrDatatypeRuleToken lv_horizontalIndent_7_0 = null;

        AntlrDatatypeRuleToken lv_verticalIndent_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4056:28: ( (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4057:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4057:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4057:3: otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKGridPlacementData8572); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData8584); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKGridPlacementData8596); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4069:1: ( (lv_widthHint_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4070:1: (lv_widthHint_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4070:1: (lv_widthHint_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4071:3: lv_widthHint_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8617);
            lv_widthHint_3_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"widthHint",
                    		lv_widthHint_3_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKGridPlacementData8629); 

                	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4091:1: ( (lv_heightHint_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4092:1: (lv_heightHint_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4092:1: (lv_heightHint_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4093:3: lv_heightHint_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8650);
            lv_heightHint_5_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"heightHint",
                    		lv_heightHint_5_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_6=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKGridPlacementData8662); 

                	newLeafNode(otherlv_6, grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4113:1: ( (lv_horizontalIndent_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4114:1: (lv_horizontalIndent_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4114:1: (lv_horizontalIndent_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4115:3: lv_horizontalIndent_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8683);
            lv_horizontalIndent_7_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"horizontalIndent",
                    		lv_horizontalIndent_7_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKGridPlacementData8695); 

                	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getVerticalIndentKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4135:1: ( (lv_verticalIndent_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4136:1: (lv_verticalIndent_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4136:1: (lv_verticalIndent_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4137:3: lv_verticalIndent_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getVerticalIndentEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8716);
            lv_verticalIndent_9_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"verticalIndent",
                    		lv_verticalIndent_9_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKGridPlacementData8728); 

                	newLeafNode(otherlv_10, grammarAccess.getKGridPlacementDataAccess().getRightCurlyBracketKeyword_10());
                

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


    // $ANTLR start "entryRuleKStackPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4165:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4166:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4167:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData8764);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData8774); 

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
    // $ANTLR end "entryRuleKStackPlacementData"


    // $ANTLR start "ruleKStackPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4174:1: ruleKStackPlacementData returns [EObject current=null] : (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
    public final EObject ruleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_insetRight_3_0 = null;

        AntlrDatatypeRuleToken lv_insetBottom_5_0 = null;

        AntlrDatatypeRuleToken lv_insetLeft_7_0 = null;

        AntlrDatatypeRuleToken lv_insetTop_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4177:28: ( (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4178:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4178:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4178:3: otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKStackPlacementData8811); 

                	newLeafNode(otherlv_0, grammarAccess.getKStackPlacementDataAccess().getKStackPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData8823); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKStackPlacementData8835); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4190:1: ( (lv_insetRight_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4191:1: (lv_insetRight_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4191:1: (lv_insetRight_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4192:3: lv_insetRight_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8856);
            lv_insetRight_3_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetRight",
                    		lv_insetRight_3_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKStackPlacementData8868); 

                	newLeafNode(otherlv_4, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4212:1: ( (lv_insetBottom_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4213:1: (lv_insetBottom_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4213:1: (lv_insetBottom_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4214:3: lv_insetBottom_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8889);
            lv_insetBottom_5_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetBottom",
                    		lv_insetBottom_5_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_6=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKStackPlacementData8901); 

                	newLeafNode(otherlv_6, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4234:1: ( (lv_insetLeft_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4235:1: (lv_insetLeft_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4235:1: (lv_insetLeft_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4236:3: lv_insetLeft_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8922);
            lv_insetLeft_7_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetLeft",
                    		lv_insetLeft_7_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKStackPlacementData8934); 

                	newLeafNode(otherlv_8, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4256:1: ( (lv_insetTop_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4257:1: (lv_insetTop_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4257:1: (lv_insetTop_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4258:3: lv_insetTop_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8955);
            lv_insetTop_9_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetTop",
                    		lv_insetTop_9_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKStackPlacementData8967); 

                	newLeafNode(otherlv_10, grammarAccess.getKStackPlacementDataAccess().getRightCurlyBracketKeyword_10());
                

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
    // $ANTLR end "ruleKStackPlacementData"


    // $ANTLR start "entryRuleKDirectPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4286:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4287:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4288:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData9003);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData9013); 

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
    // $ANTLR end "entryRuleKDirectPlacementData"


    // $ANTLR start "ruleKDirectPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4295:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) ;
    public final EObject ruleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_topLeft_3_0 = null;

        EObject lv_bottomRight_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4298:28: ( (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4299:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4299:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4299:3: otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKDirectPlacementData9050); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getKDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData9062); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKDirectPlacementData9074); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4311:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4312:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4312:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4313:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData9095);
            lv_topLeft_3_0=ruleKPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKDirectPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"topLeft",
                    		lv_topLeft_3_0, 
                    		"KPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKDirectPlacementData9107); 

                	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4333:1: ( (lv_bottomRight_5_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4334:1: (lv_bottomRight_5_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4334:1: (lv_bottomRight_5_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4335:3: lv_bottomRight_5_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData9128);
            lv_bottomRight_5_0=ruleKPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKDirectPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"bottomRight",
                    		lv_bottomRight_5_0, 
                    		"KPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_6=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKDirectPlacementData9140); 

                	newLeafNode(otherlv_6, grammarAccess.getKDirectPlacementDataAccess().getRightCurlyBracketKeyword_6());
                

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
    // $ANTLR end "ruleKDirectPlacementData"


    // $ANTLR start "entryRuleKPolylinePlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4363:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4364:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4365:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData9176);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData9186); 

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
    // $ANTLR end "entryRuleKPolylinePlacementData"


    // $ANTLR start "ruleKPolylinePlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4372:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* otherlv_5= 'detailedPlacementData' ( (lv_detailPlacementData_6_0= ruleKPlacementData ) )? ) ;
    public final EObject ruleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_points_2_0 = null;

        EObject lv_points_4_0 = null;

        EObject lv_detailPlacementData_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4375:28: ( (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* otherlv_5= 'detailedPlacementData' ( (lv_detailPlacementData_6_0= ruleKPlacementData ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4376:1: (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* otherlv_5= 'detailedPlacementData' ( (lv_detailPlacementData_6_0= ruleKPlacementData ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4376:1: (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* otherlv_5= 'detailedPlacementData' ( (lv_detailPlacementData_6_0= ruleKPlacementData ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4376:3: otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* otherlv_5= 'detailedPlacementData' ( (lv_detailPlacementData_6_0= ruleKPlacementData ) )?
            {
            otherlv_0=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKPolylinePlacementData9223); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_0());
                
            otherlv_1=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolylinePlacementData9235); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4384:1: ( (lv_points_2_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4385:1: (lv_points_2_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4385:1: (lv_points_2_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4386:3: lv_points_2_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9256);
            lv_points_2_0=ruleKPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPolylinePlacementDataRule());
            	        }
                   		add(
                   			current, 
                   			"points",
                    		lv_points_2_0, 
                    		"KPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4402:2: (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )*
            loop118:
            do {
                int alt118=2;
                int LA118_0 = input.LA(1);

                if ( (LA118_0==14) ) {
                    alt118=1;
                }


                switch (alt118) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4402:4: otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) )
            	    {
            	    otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolylinePlacementData9269); 

            	        	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_3_0());
            	        
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4406:1: ( (lv_points_4_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4407:1: (lv_points_4_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4407:1: (lv_points_4_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4408:3: lv_points_4_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9290);
            	    lv_points_4_0=ruleKPosition();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKPolylinePlacementDataRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"points",
            	            		lv_points_4_0, 
            	            		"KPosition");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop118;
                }
            } while (true);

            otherlv_5=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKPolylinePlacementData9304); 

                	newLeafNode(otherlv_5, grammarAccess.getKPolylinePlacementDataAccess().getDetailedPlacementDataKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4428:1: ( (lv_detailPlacementData_6_0= ruleKPlacementData ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==55||LA119_0==62||LA119_0==67||LA119_0==72||LA119_0==75) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4429:1: (lv_detailPlacementData_6_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4429:1: (lv_detailPlacementData_6_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4430:3: lv_detailPlacementData_6_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getDetailPlacementDataKPlacementDataParserRuleCall_5_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData9325);
                    lv_detailPlacementData_6_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolylinePlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"detailPlacementData",
                            		lv_detailPlacementData_6_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

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
    // $ANTLR end "ruleKPolylinePlacementData"


    // $ANTLR start "entryRuleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4454:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4455:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4456:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat9363);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat9374); 

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
    // $ANTLR end "entryRuleEFloat"


    // $ANTLR start "ruleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4463:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4466:28: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4467:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4467:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4467:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4467:2: (kw= '-' )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==77) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4468:2: kw= '-'
                    {
                    kw=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleEFloat9413); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4473:3: (this_INT_1= RULE_INT )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==RULE_INT) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4473:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat9431); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleEFloat9451); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 
                
            this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat9466); 

            		current.merge(this_INT_3);
                
             
                newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4493:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( ((LA124_0>=79 && LA124_0<=80)) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4493:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4493:2: (kw= 'E' | kw= 'e' )
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==79) ) {
                        alt122=1;
                    }
                    else if ( (LA122_0==80) ) {
                        alt122=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 122, 0, input);

                        throw nvae;
                    }
                    switch (alt122) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4494:2: kw= 'E'
                            {
                            kw=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleEFloat9486); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4501:2: kw= 'e'
                            {
                            kw=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleEFloat9505); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4506:2: (kw= '-' )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==77) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4507:2: kw= '-'
                            {
                            kw=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleEFloat9520); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat9537); 

                    		current.merge(this_INT_7);
                        
                     
                        newLeafNode(this_INT_7, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_4_2()); 
                        

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
    // $ANTLR end "ruleEFloat"


    // $ANTLR start "entryRuleKPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4529:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4530:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4531:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition9586);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition9596); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4538:1: ruleKPosition returns [EObject current=null] : (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_x_3_0 = null;

        EObject lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4541:28: ( (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4542:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4542:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4542:3: otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleKPosition9633); 

                	newLeafNode(otherlv_0, grammarAccess.getKPositionAccess().getKPositionKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPosition9645); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKPosition9657); 

                	newLeafNode(otherlv_2, grammarAccess.getKPositionAccess().getXKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4554:1: ( (lv_x_3_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4555:1: (lv_x_3_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4555:1: (lv_x_3_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4556:3: lv_x_3_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition9678);
            lv_x_3_0=ruleKXPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPositionRule());
            	        }
                   		set(
                   			current, 
                   			"x",
                    		lv_x_3_0, 
                    		"KXPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKPosition9690); 

                	newLeafNode(otherlv_4, grammarAccess.getKPositionAccess().getYKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4576:1: ( (lv_y_5_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4577:1: (lv_y_5_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4577:1: (lv_y_5_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4578:3: lv_y_5_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition9711);
            lv_y_5_0=ruleKYPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPositionRule());
            	        }
                   		set(
                   			current, 
                   			"y",
                    		lv_y_5_0, 
                    		"KYPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_6=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPosition9723); 

                	newLeafNode(otherlv_6, grammarAccess.getKPositionAccess().getRightCurlyBracketKeyword_6());
                

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


    // $ANTLR start "entryRuleKLeftPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4606:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4607:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4608:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition9759);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition9769); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4615:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_absolute_4_0 = null;

        AntlrDatatypeRuleToken lv_relative_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4618:28: ( ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4619:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4619:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4619:2: () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4619:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4620:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKLeftPosition9815); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getKLeftPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLeftPosition9827); 

                	newLeafNode(otherlv_2, grammarAccess.getKLeftPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4633:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==85) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4633:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKLeftPosition9840); 

                        	newLeafNode(otherlv_3, grammarAccess.getKLeftPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4637:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4638:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4638:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4639:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition9861);
                    lv_absolute_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKLeftPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"absolute",
                            		lv_absolute_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4655:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==56) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4655:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKLeftPosition9876); 

                        	newLeafNode(otherlv_5, grammarAccess.getKLeftPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4659:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4660:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4660:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4661:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition9897);
                    lv_relative_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKLeftPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"relative",
                            		lv_relative_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLeftPosition9911); 

                	newLeafNode(otherlv_7, grammarAccess.getKLeftPositionAccess().getRightCurlyBracketKeyword_5());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4689:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4690:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4691:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition9947);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition9957); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4698:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_absolute_4_0 = null;

        AntlrDatatypeRuleToken lv_relative_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4701:28: ( ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4702:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4702:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4702:2: () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4702:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4703:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKRightPosition10003); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getKRightPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRightPosition10015); 

                	newLeafNode(otherlv_2, grammarAccess.getKRightPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4716:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==85) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4716:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKRightPosition10028); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRightPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4720:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4721:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4721:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4722:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition10049);
                    lv_absolute_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRightPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"absolute",
                            		lv_absolute_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4738:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==56) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4738:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKRightPosition10064); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRightPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4742:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4743:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4743:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4744:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition10085);
                    lv_relative_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRightPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"relative",
                            		lv_relative_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRightPosition10099); 

                	newLeafNode(otherlv_7, grammarAccess.getKRightPositionAccess().getRightCurlyBracketKeyword_5());
                

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


    // $ANTLR start "entryRuleKTopPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4772:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4773:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4774:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition10135);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition10145); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4781:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_absolute_4_0 = null;

        AntlrDatatypeRuleToken lv_relative_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4784:28: ( ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4785:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4785:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4785:2: () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4785:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4786:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleKTopPosition10191); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getKTopPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKTopPosition10203); 

                	newLeafNode(otherlv_2, grammarAccess.getKTopPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4799:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==85) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4799:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKTopPosition10216); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTopPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4803:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4804:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4804:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4805:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition10237);
                    lv_absolute_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTopPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"absolute",
                            		lv_absolute_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4821:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==56) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4821:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKTopPosition10252); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTopPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4825:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4826:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4826:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4827:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition10273);
                    lv_relative_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTopPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"relative",
                            		lv_relative_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKTopPosition10287); 

                	newLeafNode(otherlv_7, grammarAccess.getKTopPositionAccess().getRightCurlyBracketKeyword_5());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4855:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4856:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4857:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition10323);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition10333); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4864:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_absolute_4_0 = null;

        AntlrDatatypeRuleToken lv_relative_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4867:28: ( ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4868:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4868:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4868:2: () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4868:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4869:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleKBottomPosition10379); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getKBottomPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBottomPosition10391); 

                	newLeafNode(otherlv_2, grammarAccess.getKBottomPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4882:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==85) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4882:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKBottomPosition10404); 

                        	newLeafNode(otherlv_3, grammarAccess.getKBottomPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4886:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4887:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4887:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4888:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition10425);
                    lv_absolute_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBottomPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"absolute",
                            		lv_absolute_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4904:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==56) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4904:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKBottomPosition10440); 

                        	newLeafNode(otherlv_5, grammarAccess.getKBottomPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4908:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4909:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4909:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4910:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition10461);
                    lv_relative_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBottomPositionRule());
                    	        }
                           		set(
                           			current, 
                           			"relative",
                            		lv_relative_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKBottomPosition10475); 

                	newLeafNode(otherlv_7, grammarAccess.getKBottomPositionAccess().getRightCurlyBracketKeyword_5());
                

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


    // $ANTLR start "entryRuleKForegroundColor"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4938:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4939:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4940:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor10511);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor10521); 

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
    // $ANTLR end "entryRuleKForegroundColor"


    // $ANTLR start "ruleKForegroundColor"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4947:1: ruleKForegroundColor returns [EObject current=null] : ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) ;
    public final EObject ruleKForegroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_propagateToChildren_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_red_5_0 = null;

        AntlrDatatypeRuleToken lv_green_7_0 = null;

        AntlrDatatypeRuleToken lv_blue_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4950:28: ( ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4951:1: ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4951:1: ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4951:2: () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4951:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4952:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundColorAccess().getKForegroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,89,FollowSets000.FOLLOW_89_in_ruleKForegroundColor10567); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getKForegroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKForegroundColor10579); 

                	newLeafNode(otherlv_2, grammarAccess.getKForegroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4965:1: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==90) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4966:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4966:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4967:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKForegroundColor10597); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKForegroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundColorRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4980:3: (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==91) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4980:5: otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) )
                    {
                    otherlv_4=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleKForegroundColor10624); 

                        	newLeafNode(otherlv_4, grammarAccess.getKForegroundColorAccess().getRedKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4984:1: ( (lv_red_5_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4985:1: (lv_red_5_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4985:1: (lv_red_5_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4986:3: lv_red_5_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor10645);
                    lv_red_5_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
                    	        }
                           		set(
                           			current, 
                           			"red",
                            		lv_red_5_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5002:4: (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==92) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5002:6: otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) )
                    {
                    otherlv_6=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleKForegroundColor10660); 

                        	newLeafNode(otherlv_6, grammarAccess.getKForegroundColorAccess().getGreenKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5006:1: ( (lv_green_7_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5007:1: (lv_green_7_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5007:1: (lv_green_7_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5008:3: lv_green_7_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor10681);
                    lv_green_7_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
                    	        }
                           		set(
                           			current, 
                           			"green",
                            		lv_green_7_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5024:4: (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==93) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5024:6: otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) )
                    {
                    otherlv_8=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleKForegroundColor10696); 

                        	newLeafNode(otherlv_8, grammarAccess.getKForegroundColorAccess().getBlueKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5028:1: ( (lv_blue_9_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5029:1: (lv_blue_9_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5029:1: (lv_blue_9_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5030:3: lv_blue_9_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor10717);
                    lv_blue_9_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
                    	        }
                           		set(
                           			current, 
                           			"blue",
                            		lv_blue_9_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKForegroundColor10731); 

                	newLeafNode(otherlv_10, grammarAccess.getKForegroundColorAccess().getRightCurlyBracketKeyword_7());
                

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
    // $ANTLR end "ruleKForegroundColor"


    // $ANTLR start "entryRuleKBackgroundColor"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5058:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5059:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5060:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor10767);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor10777); 

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
    // $ANTLR end "entryRuleKBackgroundColor"


    // $ANTLR start "ruleKBackgroundColor"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5067:1: ruleKBackgroundColor returns [EObject current=null] : ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) ;
    public final EObject ruleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_propagateToChildren_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_red_5_0 = null;

        AntlrDatatypeRuleToken lv_green_7_0 = null;

        AntlrDatatypeRuleToken lv_blue_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5070:28: ( ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5071:1: ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5071:1: ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5071:2: () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5071:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5072:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundColorAccess().getKBackgroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleKBackgroundColor10823); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getKBackgroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBackgroundColor10835); 

                	newLeafNode(otherlv_2, grammarAccess.getKBackgroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5085:1: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==90) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5086:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5086:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5087:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKBackgroundColor10853); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKBackgroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundColorRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5100:3: (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==91) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5100:5: otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) )
                    {
                    otherlv_4=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleKBackgroundColor10880); 

                        	newLeafNode(otherlv_4, grammarAccess.getKBackgroundColorAccess().getRedKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5104:1: ( (lv_red_5_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5105:1: (lv_red_5_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5105:1: (lv_red_5_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5106:3: lv_red_5_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10901);
                    lv_red_5_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
                    	        }
                           		set(
                           			current, 
                           			"red",
                            		lv_red_5_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5122:4: (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==92) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5122:6: otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) )
                    {
                    otherlv_6=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleKBackgroundColor10916); 

                        	newLeafNode(otherlv_6, grammarAccess.getKBackgroundColorAccess().getGreenKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5126:1: ( (lv_green_7_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5127:1: (lv_green_7_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5127:1: (lv_green_7_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5128:3: lv_green_7_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10937);
                    lv_green_7_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
                    	        }
                           		set(
                           			current, 
                           			"green",
                            		lv_green_7_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5144:4: (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==93) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5144:6: otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) )
                    {
                    otherlv_8=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleKBackgroundColor10952); 

                        	newLeafNode(otherlv_8, grammarAccess.getKBackgroundColorAccess().getBlueKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5148:1: ( (lv_blue_9_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5149:1: (lv_blue_9_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5149:1: (lv_blue_9_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5150:3: lv_blue_9_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10973);
                    lv_blue_9_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
                    	        }
                           		set(
                           			current, 
                           			"blue",
                            		lv_blue_9_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKBackgroundColor10987); 

                	newLeafNode(otherlv_10, grammarAccess.getKBackgroundColorAccess().getRightCurlyBracketKeyword_7());
                

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
    // $ANTLR end "ruleKBackgroundColor"


    // $ANTLR start "entryRuleKLineWidth"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5178:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5179:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5180:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth11023);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth11033); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5187:1: ruleKLineWidth returns [EObject current=null] : (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_lineWidth_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5190:28: ( (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5191:1: (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5191:1: (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5191:3: otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            {
            otherlv_0=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleKLineWidth11070); 

                	newLeafNode(otherlv_0, grammarAccess.getKLineWidthAccess().getKLineWidthKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5195:1: ( (lv_lineWidth_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5196:1: (lv_lineWidth_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5196:1: (lv_lineWidth_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5197:3: lv_lineWidth_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth11091);
            lv_lineWidth_1_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLineWidthRule());
            	        }
                   		set(
                   			current, 
                   			"lineWidth",
                    		lv_lineWidth_1_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5213:2: ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==90) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5214:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5214:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5215:3: lv_propagateToChildren_2_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKLineWidth11109); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKLineWidthAccess().getPropagateToChildrenPropagateToChildrenKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineWidthRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

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
    // $ANTLR end "ruleKLineWidth"


    // $ANTLR start "entryRuleKVisibility"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5236:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5237:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5238:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility11159);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility11169); 

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
    // $ANTLR end "entryRuleKVisibility"


    // $ANTLR start "ruleKVisibility"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5245:1: ruleKVisibility returns [EObject current=null] : (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundVisibility_0 = null;

        EObject this_KBackgroundVisibility_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5248:28: ( (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5249:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5249:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==96) ) {
                alt142=1;
            }
            else if ( (LA142_0==98) ) {
                alt142=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 142, 0, input);

                throw nvae;
            }
            switch (alt142) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5250:5: this_KForegroundVisibility_0= ruleKForegroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKForegroundVisibilityParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility11216);
                    this_KForegroundVisibility_0=ruleKForegroundVisibility();

                    state._fsp--;

                     
                            current = this_KForegroundVisibility_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5260:5: this_KBackgroundVisibility_1= ruleKBackgroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKBackgroundVisibilityParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility11243);
                    this_KBackgroundVisibility_1=ruleKBackgroundVisibility();

                    state._fsp--;

                     
                            current = this_KBackgroundVisibility_1; 
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
    // $ANTLR end "ruleKVisibility"


    // $ANTLR start "entryRuleKForegroundVisibility"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5276:1: entryRuleKForegroundVisibility returns [EObject current=null] : iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF ;
    public final EObject entryRuleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5277:2: (iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5278:2: iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKForegroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility11278);
            iv_ruleKForegroundVisibility=ruleKForegroundVisibility();

            state._fsp--;

             current =iv_ruleKForegroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundVisibility11288); 

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
    // $ANTLR end "entryRuleKForegroundVisibility"


    // $ANTLR start "ruleKForegroundVisibility"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5285:1: ruleKForegroundVisibility returns [EObject current=null] : ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_visible_2_0=null;
        Token lv_propagateToChildren_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5288:28: ( ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5289:1: ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5289:1: ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5289:2: () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5289:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5290:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleKForegroundVisibility11334); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5299:1: ( (lv_visible_2_0= 'visible' ) )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==97) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5300:1: (lv_visible_2_0= 'visible' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5300:1: (lv_visible_2_0= 'visible' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5301:3: lv_visible_2_0= 'visible'
                    {
                    lv_visible_2_0=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleKForegroundVisibility11352); 

                            newLeafNode(lv_visible_2_0, grammarAccess.getKForegroundVisibilityAccess().getVisibleVisibleKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "visible", true, "visible");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5314:3: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==90) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5315:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5315:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5316:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKForegroundVisibility11384); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKForegroundVisibilityAccess().getPropagateToChildrenPropagateToChildrenKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

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
    // $ANTLR end "ruleKForegroundVisibility"


    // $ANTLR start "entryRuleKBackgroundVisibility"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5337:1: entryRuleKBackgroundVisibility returns [EObject current=null] : iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF ;
    public final EObject entryRuleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5338:2: (iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5339:2: iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility11434);
            iv_ruleKBackgroundVisibility=ruleKBackgroundVisibility();

            state._fsp--;

             current =iv_ruleKBackgroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundVisibility11444); 

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
    // $ANTLR end "entryRuleKBackgroundVisibility"


    // $ANTLR start "ruleKBackgroundVisibility"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5346:1: ruleKBackgroundVisibility returns [EObject current=null] : ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_visible_2_0=null;
        Token lv_propagateToChildren_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5349:28: ( ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5350:1: ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5350:1: ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5350:2: () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5350:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5351:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleKBackgroundVisibility11490); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5360:1: ( (lv_visible_2_0= 'visible' ) )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==97) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5361:1: (lv_visible_2_0= 'visible' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5361:1: (lv_visible_2_0= 'visible' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5362:3: lv_visible_2_0= 'visible'
                    {
                    lv_visible_2_0=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleKBackgroundVisibility11508); 

                            newLeafNode(lv_visible_2_0, grammarAccess.getKBackgroundVisibilityAccess().getVisibleVisibleKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "visible", true, "visible");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5375:3: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==90) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5376:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5376:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5377:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKBackgroundVisibility11540); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKBackgroundVisibilityAccess().getPropagateToChildrenPropagateToChildrenKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

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
    // $ANTLR end "ruleKBackgroundVisibility"


    // $ANTLR start "entryRuleKLineStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5398:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5399:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5400:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle11590);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle11600); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5407:1: ruleKLineStyle returns [EObject current=null] : ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_lineStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5410:28: ( ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5411:1: ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5411:1: ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5411:2: () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5411:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5412:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineStyleAccess().getKLineStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleKLineStyle11646); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getKLineStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5421:1: ( (lv_lineStyle_2_0= ruleLineStyle ) )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( ((LA147_0>=120 && LA147_0<=124)) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5422:1: (lv_lineStyle_2_0= ruleLineStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5422:1: (lv_lineStyle_2_0= ruleLineStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5423:3: lv_lineStyle_2_0= ruleLineStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle11667);
                    lv_lineStyle_2_0=ruleLineStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKLineStyleRule());
                    	        }
                           		set(
                           			current, 
                           			"lineStyle",
                            		lv_lineStyle_2_0, 
                            		"LineStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5439:3: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==90) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5440:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5440:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5441:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKLineStyle11686); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKLineStyleAccess().getPropagateToChildrenPropagateToChildrenKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineStyleRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

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
    // $ANTLR end "ruleKLineStyle"


    // $ANTLR start "entryRuleKVerticalAlignment"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5462:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5463:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5464:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment11736);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment11746); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5471:1: ruleKVerticalAlignment returns [EObject current=null] : (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        Enumerator lv_verticalAlignment_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5474:28: ( (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5475:1: (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5475:1: (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5475:3: otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            {
            otherlv_0=(Token)match(input,100,FollowSets000.FOLLOW_100_in_ruleKVerticalAlignment11783); 

                	newLeafNode(otherlv_0, grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5479:1: ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5480:1: (lv_verticalAlignment_1_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5480:1: (lv_verticalAlignment_1_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5481:3: lv_verticalAlignment_1_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment11804);
            lv_verticalAlignment_1_0=ruleVerticalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKVerticalAlignmentRule());
            	        }
                   		set(
                   			current, 
                   			"verticalAlignment",
                    		lv_verticalAlignment_1_0, 
                    		"VerticalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5497:2: ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==90) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5498:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5498:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5499:3: lv_propagateToChildren_2_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKVerticalAlignment11822); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKVerticalAlignmentAccess().getPropagateToChildrenPropagateToChildrenKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

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
    // $ANTLR end "ruleKVerticalAlignment"


    // $ANTLR start "entryRuleKHorizontalAlignment"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5520:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5521:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5522:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment11872);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment11882); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5529:1: ruleKHorizontalAlignment returns [EObject current=null] : (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        Enumerator lv_horizontalAlignment_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5532:28: ( (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5533:1: (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5533:1: (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5533:3: otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )
            {
            otherlv_0=(Token)match(input,101,FollowSets000.FOLLOW_101_in_ruleKHorizontalAlignment11919); 

                	newLeafNode(otherlv_0, grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5537:1: ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5538:1: (lv_horizontalAlignment_1_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5538:1: (lv_horizontalAlignment_1_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5539:3: lv_horizontalAlignment_1_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11940);
            lv_horizontalAlignment_1_0=ruleHorizontalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKHorizontalAlignmentRule());
            	        }
                   		set(
                   			current, 
                   			"horizontalAlignment",
                    		lv_horizontalAlignment_1_0, 
                    		"HorizontalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5555:2: ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5556:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5556:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5557:3: lv_propagateToChildren_2_0= 'propagateToChildren'
            {
            lv_propagateToChildren_2_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKHorizontalAlignment11958); 

                    newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKHorizontalAlignmentAccess().getPropagateToChildrenPropagateToChildrenKeyword_2_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

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


    // $ANTLR start "entryRuleKGridPlacement"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5578:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5579:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5580:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement12007);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement12017); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5587:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_numColumns_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5590:28: ( ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5591:1: ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5591:1: ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5591:2: () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5591:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5592:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,102,FollowSets000.FOLLOW_102_in_ruleKGridPlacement12063); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getKGridPlacementKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5601:1: ( (lv_numColumns_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5602:1: (lv_numColumns_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5602:1: (lv_numColumns_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5603:3: lv_numColumns_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement12084);
            lv_numColumns_2_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementRule());
            	        }
                   		set(
                   			current, 
                   			"numColumns",
                    		lv_numColumns_2_0, 
                    		"EInt");
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
    // $ANTLR end "ruleKGridPlacement"


    // $ANTLR start "entryRuleKStackPlacement"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5627:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5628:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5629:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement12120);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement12130); 

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
    // $ANTLR end "entryRuleKStackPlacement"


    // $ANTLR start "ruleKStackPlacement"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5636:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'KStackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5639:28: ( ( () otherlv_1= 'KStackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5640:1: ( () otherlv_1= 'KStackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5640:1: ( () otherlv_1= 'KStackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5640:2: () otherlv_1= 'KStackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5640:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5641:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,103,FollowSets000.FOLLOW_103_in_ruleKStackPlacement12176); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementAccess().getKStackPlacementKeyword_1());
                

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
    // $ANTLR end "ruleKStackPlacement"


    // $ANTLR start "entryRuleEInt"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5658:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5659:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5660:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt12213);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt12224); 

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
    // $ANTLR end "entryRuleEInt"


    // $ANTLR start "ruleEInt"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5667:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5670:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5671:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5671:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5671:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5671:2: (kw= '-' )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==77) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5672:2: kw= '-'
                    {
                    kw=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleEInt12263); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt12280); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEIntAccess().getINTTerminalRuleCall_1()); 
                

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
    // $ANTLR end "ruleEInt"


    // $ANTLR start "entryRuleKShapeLayout"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5692:1: entryRuleKShapeLayout returns [EObject current=null] : iv_ruleKShapeLayout= ruleKShapeLayout EOF ;
    public final EObject entryRuleKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShapeLayout = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5693:2: (iv_ruleKShapeLayout= ruleKShapeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5694:2: iv_ruleKShapeLayout= ruleKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getKShapeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout12325);
            iv_ruleKShapeLayout=ruleKShapeLayout();

            state._fsp--;

             current =iv_ruleKShapeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKShapeLayout12335); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5701:1: ruleKShapeLayout returns [EObject current=null] : ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' ) ;
    public final EObject ruleKShapeLayout() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        AntlrDatatypeRuleToken lv_xpos_4_0 = null;

        AntlrDatatypeRuleToken lv_ypos_6_0 = null;

        AntlrDatatypeRuleToken lv_width_8_0 = null;

        AntlrDatatypeRuleToken lv_height_10_0 = null;

        EObject lv_insets_12_0 = null;

        EObject lv_persistentEntries_15_0 = null;

        EObject lv_persistentEntries_17_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5704:28: ( ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5705:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5705:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5705:2: () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5705:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5706:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,104,FollowSets000.FOLLOW_104_in_ruleKShapeLayout12381); 

                	newLeafNode(otherlv_1, grammarAccess.getKShapeLayoutAccess().getKShapeLayoutKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKShapeLayout12393); 

                	newLeafNode(otherlv_2, grammarAccess.getKShapeLayoutAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5719:1: (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==105) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5719:3: otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,105,FollowSets000.FOLLOW_105_in_ruleKShapeLayout12406); 

                        	newLeafNode(otherlv_3, grammarAccess.getKShapeLayoutAccess().getXposKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5723:1: ( (lv_xpos_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5724:1: (lv_xpos_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5724:1: (lv_xpos_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5725:3: lv_xpos_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getXposEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12427);
                    lv_xpos_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
                    	        }
                           		set(
                           			current, 
                           			"xpos",
                            		lv_xpos_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5741:4: (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==106) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5741:6: otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,106,FollowSets000.FOLLOW_106_in_ruleKShapeLayout12442); 

                        	newLeafNode(otherlv_5, grammarAccess.getKShapeLayoutAccess().getYposKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5745:1: ( (lv_ypos_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5746:1: (lv_ypos_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5746:1: (lv_ypos_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5747:3: lv_ypos_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getYposEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12463);
                    lv_ypos_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
                    	        }
                           		set(
                           			current, 
                           			"ypos",
                            		lv_ypos_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5763:4: (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==60) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5763:6: otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKShapeLayout12478); 

                        	newLeafNode(otherlv_7, grammarAccess.getKShapeLayoutAccess().getWidthKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5767:1: ( (lv_width_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5768:1: (lv_width_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5768:1: (lv_width_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5769:3: lv_width_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getWidthEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12499);
                    lv_width_8_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
                    	        }
                           		set(
                           			current, 
                           			"width",
                            		lv_width_8_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5785:4: (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==61) ) {
                alt154=1;
            }
            switch (alt154) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5785:6: otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKShapeLayout12514); 

                        	newLeafNode(otherlv_9, grammarAccess.getKShapeLayoutAccess().getHeightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5789:1: ( (lv_height_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5790:1: (lv_height_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5790:1: (lv_height_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5791:3: lv_height_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getHeightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12535);
                    lv_height_10_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
                    	        }
                           		set(
                           			current, 
                           			"height",
                            		lv_height_10_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5807:4: (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==107) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5807:6: otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) )
                    {
                    otherlv_11=(Token)match(input,107,FollowSets000.FOLLOW_107_in_ruleKShapeLayout12550); 

                        	newLeafNode(otherlv_11, grammarAccess.getKShapeLayoutAccess().getInsetsKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5811:1: ( (lv_insets_12_0= ruleKInsets ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5812:1: (lv_insets_12_0= ruleKInsets )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5812:1: (lv_insets_12_0= ruleKInsets )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5813:3: lv_insets_12_0= ruleKInsets
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getInsetsKInsetsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_ruleKShapeLayout12571);
                    lv_insets_12_0=ruleKInsets();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
                    	        }
                           		set(
                           			current, 
                           			"insets",
                            		lv_insets_12_0, 
                            		"KInsets");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5829:4: (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==108) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5829:6: otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )*
                    {
                    otherlv_13=(Token)match(input,108,FollowSets000.FOLLOW_108_in_ruleKShapeLayout12586); 

                        	newLeafNode(otherlv_13, grammarAccess.getKShapeLayoutAccess().getMapPropertiesKeyword_8_0());
                        
                    otherlv_14=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKShapeLayout12598); 

                        	newLeafNode(otherlv_14, grammarAccess.getKShapeLayoutAccess().getColonKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5837:1: ( (lv_persistentEntries_15_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5838:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5838:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5839:3: lv_persistentEntries_15_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12619);
                    lv_persistentEntries_15_0=rulePersistentEntry();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
                    	        }
                           		add(
                           			current, 
                           			"persistentEntries",
                            		lv_persistentEntries_15_0, 
                            		"PersistentEntry");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5855:2: (otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )*
                    loop156:
                    do {
                        int alt156=2;
                        int LA156_0 = input.LA(1);

                        if ( (LA156_0==14) ) {
                            alt156=1;
                        }


                        switch (alt156) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5855:4: otherlv_16= ',' ( (lv_persistentEntries_17_0= rulePersistentEntry ) )
                    	    {
                    	    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKShapeLayout12632); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKShapeLayoutAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5859:1: ( (lv_persistentEntries_17_0= rulePersistentEntry ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5860:1: (lv_persistentEntries_17_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5860:1: (lv_persistentEntries_17_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5861:3: lv_persistentEntries_17_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12653);
                    	    lv_persistentEntries_17_0=rulePersistentEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKShapeLayoutRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"persistentEntries",
                    	            		lv_persistentEntries_17_0, 
                    	            		"PersistentEntry");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop156;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKShapeLayout12669); 

                	newLeafNode(otherlv_18, grammarAccess.getKShapeLayoutAccess().getRightCurlyBracketKeyword_9());
                

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


    // $ANTLR start "entryRuleKInsets"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5889:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5890:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5891:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets12705);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets12715); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5898:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
    public final EObject ruleKInsets() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        AntlrDatatypeRuleToken lv_top_4_0 = null;

        AntlrDatatypeRuleToken lv_bottom_6_0 = null;

        AntlrDatatypeRuleToken lv_left_8_0 = null;

        AntlrDatatypeRuleToken lv_right_10_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5901:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5902:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5902:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5902:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5902:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5903:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,109,FollowSets000.FOLLOW_109_in_ruleKInsets12761); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets12773); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5916:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==110) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5916:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,110,FollowSets000.FOLLOW_110_in_ruleKInsets12786); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5920:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5921:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5921:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5922:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12807);
                    lv_top_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKInsetsRule());
                    	        }
                           		set(
                           			current, 
                           			"top",
                            		lv_top_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5938:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==111) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5938:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,111,FollowSets000.FOLLOW_111_in_ruleKInsets12822); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5942:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5943:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5943:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5944:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12843);
                    lv_bottom_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKInsetsRule());
                    	        }
                           		set(
                           			current, 
                           			"bottom",
                            		lv_bottom_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5960:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==112) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5960:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,112,FollowSets000.FOLLOW_112_in_ruleKInsets12858); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5964:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5965:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5965:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5966:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12879);
                    lv_left_8_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKInsetsRule());
                    	        }
                           		set(
                           			current, 
                           			"left",
                            		lv_left_8_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5982:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==113) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5982:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,113,FollowSets000.FOLLOW_113_in_ruleKInsets12894); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5986:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5987:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5987:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5988:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12915);
                    lv_right_10_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKInsetsRule());
                    	        }
                           		set(
                           			current, 
                           			"right",
                            		lv_right_10_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKInsets12929); 

                	newLeafNode(otherlv_11, grammarAccess.getKInsetsAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "entryRuleKEdgeLayout"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6016:1: entryRuleKEdgeLayout returns [EObject current=null] : iv_ruleKEdgeLayout= ruleKEdgeLayout EOF ;
    public final EObject entryRuleKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdgeLayout = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6017:2: (iv_ruleKEdgeLayout= ruleKEdgeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6018:2: iv_ruleKEdgeLayout= ruleKEdgeLayout EOF
            {
             newCompositeNode(grammarAccess.getKEdgeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEdgeLayout_in_entryRuleKEdgeLayout12965);
            iv_ruleKEdgeLayout=ruleKEdgeLayout();

            state._fsp--;

             current =iv_ruleKEdgeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEdgeLayout12975); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6025:1: ruleKEdgeLayout returns [EObject current=null] : (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' ) ;
    public final EObject ruleKEdgeLayout() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        EObject lv_sourcePoint_3_0 = null;

        EObject lv_targetPoint_5_0 = null;

        EObject lv_bendPoints_8_0 = null;

        EObject lv_bendPoints_10_0 = null;

        EObject lv_persistentEntries_13_0 = null;

        EObject lv_persistentEntries_15_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6028:28: ( (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6029:1: (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6029:1: (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6029:3: otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}'
            {
            otherlv_0=(Token)match(input,114,FollowSets000.FOLLOW_114_in_ruleKEdgeLayout13012); 

                	newLeafNode(otherlv_0, grammarAccess.getKEdgeLayoutAccess().getKEdgeLayoutKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdgeLayout13024); 

                	newLeafNode(otherlv_1, grammarAccess.getKEdgeLayoutAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,115,FollowSets000.FOLLOW_115_in_ruleKEdgeLayout13036); 

                	newLeafNode(otherlv_2, grammarAccess.getKEdgeLayoutAccess().getSourcePointKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6041:1: ( (lv_sourcePoint_3_0= ruleKPoint ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6042:1: (lv_sourcePoint_3_0= ruleKPoint )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6042:1: (lv_sourcePoint_3_0= ruleKPoint )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6043:3: lv_sourcePoint_3_0= ruleKPoint
            {
             
            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointKPointParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13057);
            lv_sourcePoint_3_0=ruleKPoint();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
            	        }
                   		set(
                   			current, 
                   			"sourcePoint",
                    		lv_sourcePoint_3_0, 
                    		"KPoint");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,116,FollowSets000.FOLLOW_116_in_ruleKEdgeLayout13069); 

                	newLeafNode(otherlv_4, grammarAccess.getKEdgeLayoutAccess().getTargetPointKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6063:1: ( (lv_targetPoint_5_0= ruleKPoint ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6064:1: (lv_targetPoint_5_0= ruleKPoint )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6064:1: (lv_targetPoint_5_0= ruleKPoint )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6065:3: lv_targetPoint_5_0= ruleKPoint
            {
             
            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointKPointParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13090);
            lv_targetPoint_5_0=ruleKPoint();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
            	        }
                   		set(
                   			current, 
                   			"targetPoint",
                    		lv_targetPoint_5_0, 
                    		"KPoint");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6081:2: (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )?
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==117) ) {
                alt163=1;
            }
            switch (alt163) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6081:4: otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )*
                    {
                    otherlv_6=(Token)match(input,117,FollowSets000.FOLLOW_117_in_ruleKEdgeLayout13103); 

                        	newLeafNode(otherlv_6, grammarAccess.getKEdgeLayoutAccess().getBendPointsKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdgeLayout13115); 

                        	newLeafNode(otherlv_7, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6089:1: ( (lv_bendPoints_8_0= ruleKPoint ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6090:1: (lv_bendPoints_8_0= ruleKPoint )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6090:1: (lv_bendPoints_8_0= ruleKPoint )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6091:3: lv_bendPoints_8_0= ruleKPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13136);
                    lv_bendPoints_8_0=ruleKPoint();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    	        }
                           		add(
                           			current, 
                           			"bendPoints",
                            		lv_bendPoints_8_0, 
                            		"KPoint");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6107:2: (otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) ) )*
                    loop162:
                    do {
                        int alt162=2;
                        int LA162_0 = input.LA(1);

                        if ( (LA162_0==14) ) {
                            alt162=1;
                        }


                        switch (alt162) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6107:4: otherlv_9= ',' ( (lv_bendPoints_10_0= ruleKPoint ) )
                    	    {
                    	    otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdgeLayout13149); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6111:1: ( (lv_bendPoints_10_0= ruleKPoint ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6112:1: (lv_bendPoints_10_0= ruleKPoint )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6112:1: (lv_bendPoints_10_0= ruleKPoint )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6113:3: lv_bendPoints_10_0= ruleKPoint
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13170);
                    	    lv_bendPoints_10_0=ruleKPoint();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"bendPoints",
                    	            		lv_bendPoints_10_0, 
                    	            		"KPoint");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop162;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6129:6: (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==108) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6129:8: otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )*
                    {
                    otherlv_11=(Token)match(input,108,FollowSets000.FOLLOW_108_in_ruleKEdgeLayout13187); 

                        	newLeafNode(otherlv_11, grammarAccess.getKEdgeLayoutAccess().getMapPropertiesKeyword_7_0());
                        
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdgeLayout13199); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6137:1: ( (lv_persistentEntries_13_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6138:1: (lv_persistentEntries_13_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6138:1: (lv_persistentEntries_13_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6139:3: lv_persistentEntries_13_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13220);
                    lv_persistentEntries_13_0=rulePersistentEntry();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    	        }
                           		add(
                           			current, 
                           			"persistentEntries",
                            		lv_persistentEntries_13_0, 
                            		"PersistentEntry");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6155:2: (otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )*
                    loop164:
                    do {
                        int alt164=2;
                        int LA164_0 = input.LA(1);

                        if ( (LA164_0==14) ) {
                            alt164=1;
                        }


                        switch (alt164) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6155:4: otherlv_14= ',' ( (lv_persistentEntries_15_0= rulePersistentEntry ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdgeLayout13233); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6159:1: ( (lv_persistentEntries_15_0= rulePersistentEntry ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6160:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6160:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6161:3: lv_persistentEntries_15_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13254);
                    	    lv_persistentEntries_15_0=rulePersistentEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"persistentEntries",
                    	            		lv_persistentEntries_15_0, 
                    	            		"PersistentEntry");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop164;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdgeLayout13270); 

                	newLeafNode(otherlv_16, grammarAccess.getKEdgeLayoutAccess().getRightCurlyBracketKeyword_8());
                

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


    // $ANTLR start "entryRuleKPoint"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6189:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6190:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6191:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint13306);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint13316); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6198:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_x_3_0 = null;

        AntlrDatatypeRuleToken lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6201:28: ( ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6202:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6202:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6202:2: () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6202:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6203:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,118,FollowSets000.FOLLOW_118_in_ruleKPoint13362); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6212:1: (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6212:3: otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) )
            {
            otherlv_2=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKPoint13375); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getXKeyword_2_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6216:1: ( (lv_x_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6217:1: (lv_x_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6217:1: (lv_x_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6218:3: lv_x_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint13396);
            lv_x_3_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPointRule());
            	        }
                   		set(
                   			current, 
                   			"x",
                    		lv_x_3_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6234:3: (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6234:5: otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) )
            {
            otherlv_4=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKPoint13410); 

                	newLeafNode(otherlv_4, grammarAccess.getKPointAccess().getYKeyword_3_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6238:1: ( (lv_y_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6239:1: (lv_y_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6239:1: (lv_y_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6240:3: lv_y_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint13431);
            lv_y_5_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPointRule());
            	        }
                   		set(
                   			current, 
                   			"y",
                    		lv_y_5_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


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


    // $ANTLR start "entryRulePersistentEntry"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6264:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6265:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6266:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry13468);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry13478); 

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
    // $ANTLR end "entryRulePersistentEntry"


    // $ANTLR start "rulePersistentEntry"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6273:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6276:28: ( ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6277:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6277:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6277:2: ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6277:2: ( (lv_key_0_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6278:1: (lv_key_0_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6278:1: (lv_key_0_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6279:3: lv_key_0_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry13524);
            lv_key_0_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"key",
                    		lv_key_0_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6295:2: (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            int alt166=2;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==119) ) {
                alt166=1;
            }
            switch (alt166) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6295:4: otherlv_1= '=' ( (lv_value_2_0= ruleEString ) )
                    {
                    otherlv_1=(Token)match(input,119,FollowSets000.FOLLOW_119_in_rulePersistentEntry13537); 

                        	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6299:1: ( (lv_value_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6300:1: (lv_value_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6300:1: (lv_value_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6301:3: lv_value_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry13558);
                    lv_value_2_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
                    	        }
                           		set(
                           			current, 
                           			"value",
                            		lv_value_2_0, 
                            		"EString");
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
    // $ANTLR end "rulePersistentEntry"


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6325:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6326:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6327:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString13597);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString13608); 

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
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6334:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6337:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6338:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6338:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==RULE_STRING) ) {
                alt167=1;
            }
            else if ( (LA167_0==RULE_ID) ) {
                alt167=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 167, 0, input);

                throw nvae;
            }
            switch (alt167) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6338:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString13648); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6346:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString13674); 

                    		current.merge(this_ID_1);
                        
                     
                        newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                        

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
    // $ANTLR end "ruleEString"


    // $ANTLR start "ruleLineStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6361:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6363:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6364:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6364:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt168=5;
            switch ( input.LA(1) ) {
            case 120:
                {
                alt168=1;
                }
                break;
            case 121:
                {
                alt168=2;
                }
                break;
            case 122:
                {
                alt168=3;
                }
                break;
            case 123:
                {
                alt168=4;
                }
                break;
            case 124:
                {
                alt168=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 168, 0, input);

                throw nvae;
            }

            switch (alt168) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6364:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6364:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6364:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,120,FollowSets000.FOLLOW_120_in_ruleLineStyle13733); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6370:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6370:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6370:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,121,FollowSets000.FOLLOW_121_in_ruleLineStyle13750); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6376:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6376:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6376:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,122,FollowSets000.FOLLOW_122_in_ruleLineStyle13767); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6382:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6382:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6382:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,123,FollowSets000.FOLLOW_123_in_ruleLineStyle13784); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6388:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6388:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6388:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,124,FollowSets000.FOLLOW_124_in_ruleLineStyle13801); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTDOTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getLineStyleAccess().getDASHDOTDOTEnumLiteralDeclaration_4()); 
                        

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


    // $ANTLR start "ruleVerticalAlignment"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6398:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6400:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6401:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6401:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt169=3;
            switch ( input.LA(1) ) {
            case 125:
                {
                alt169=1;
                }
                break;
            case 126:
                {
                alt169=2;
                }
                break;
            case 127:
                {
                alt169=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 169, 0, input);

                throw nvae;
            }

            switch (alt169) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6401:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6401:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6401:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,125,FollowSets000.FOLLOW_125_in_ruleVerticalAlignment13846); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6407:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6407:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6407:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,126,FollowSets000.FOLLOW_126_in_ruleVerticalAlignment13863); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6413:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6413:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6413:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,127,FollowSets000.FOLLOW_127_in_ruleVerticalAlignment13880); 

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


    // $ANTLR start "ruleHorizontalAlignment"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6423:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6425:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6426:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6426:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt170=3;
            switch ( input.LA(1) ) {
            case 128:
                {
                alt170=1;
                }
                break;
            case 126:
                {
                alt170=2;
                }
                break;
            case 129:
                {
                alt170=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 170, 0, input);

                throw nvae;
            }

            switch (alt170) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6426:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6426:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6426:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,128,FollowSets000.FOLLOW_128_in_ruleHorizontalAlignment13925); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6432:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6432:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6432:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,126,FollowSets000.FOLLOW_126_in_ruleHorizontalAlignment13942); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6438:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6438:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6438:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,129,FollowSets000.FOLLOW_129_in_ruleHorizontalAlignment13959); 

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

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKNode_in_entryRuleKNode75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKNode85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_ruleKNode131 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKNode143 = new BitSet(new long[]{0x000000000006A000L});
        public static final BitSet FOLLOW_13_in_ruleKNode156 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_ruleKPort_in_ruleKNode177 = new BitSet(new long[]{0x000000000006C000L});
        public static final BitSet FOLLOW_14_in_ruleKNode190 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_ruleKPort_in_ruleKNode211 = new BitSet(new long[]{0x000000000006C000L});
        public static final BitSet FOLLOW_15_in_ruleKNode228 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKNode240 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKNode261 = new BitSet(new long[]{0x0000000000064000L});
        public static final BitSet FOLLOW_14_in_ruleKNode274 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKNode295 = new BitSet(new long[]{0x0000000000064000L});
        public static final BitSet FOLLOW_17_in_ruleKNode312 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKNode324 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_ruleKNode_in_ruleKNode345 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKNode358 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_ruleKNode_in_ruleKNode379 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKNode395 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_ruleKEdge_in_ruleKNode417 = new BitSet(new long[]{0x0000000000004002L});
        public static final BitSet FOLLOW_14_in_ruleKNode430 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKEdge_in_ruleKNode451 = new BitSet(new long[]{0x0000000000004002L});
        public static final BitSet FOLLOW_ruleKLabel_in_entryRuleKLabel491 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLabel501 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_ruleKLabel538 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKLabel559 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLabel571 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKLabel584 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKLabel596 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKLabel617 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKLabel630 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKLabel651 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKLabel667 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPort_in_entryRuleKPort703 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPort713 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_20_in_ruleKPort759 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPort771 = new BitSet(new long[]{0x0000000000648000L});
        public static final BitSet FOLLOW_21_in_ruleKPort784 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPort796 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPort819 = new BitSet(new long[]{0x000000000044C000L});
        public static final BitSet FOLLOW_14_in_ruleKPort832 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPort855 = new BitSet(new long[]{0x000000000044C000L});
        public static final BitSet FOLLOW_15_in_ruleKPort872 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPort884 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKPort905 = new BitSet(new long[]{0x0000000000444000L});
        public static final BitSet FOLLOW_14_in_ruleKPort918 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKPort939 = new BitSet(new long[]{0x0000000000444000L});
        public static final BitSet FOLLOW_22_in_ruleKPort956 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPort968 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKPort989 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKPort1002 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKPort1023 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKPort1039 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdge_in_entryRuleKEdge1075 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEdge1085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKEdge1122 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_24_in_ruleKEdge1134 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1157 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdge1169 = new BitSet(new long[]{0x0000000006448000L});
        public static final BitSet FOLLOW_25_in_ruleKEdge1182 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1205 = new BitSet(new long[]{0x0000000004448000L});
        public static final BitSet FOLLOW_26_in_ruleKEdge1220 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1243 = new BitSet(new long[]{0x0000000000448000L});
        public static final BitSet FOLLOW_15_in_ruleKEdge1258 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEdge1270 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKEdge1291 = new BitSet(new long[]{0x0000000000444000L});
        public static final BitSet FOLLOW_14_in_ruleKEdge1304 = new BitSet(new long[]{0x0053273408000000L,0x0004010000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKEdge1325 = new BitSet(new long[]{0x0000000000444000L});
        public static final BitSet FOLLOW_22_in_ruleKEdge1342 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEdge1354 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKEdge1375 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKEdge1388 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKEdge1409 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKEdge1425 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGraphData_in_entryRuleKGraphData1461 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGraphData1471 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKGraphData1518 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKShapeLayout_in_ruleKGraphData1545 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdgeLayout_in_ruleKGraphData1572 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_entryRuleKRendering1609 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRendering1619 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_ruleKRendering1666 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_ruleKRendering1693 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_ruleKRendering1720 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_ruleKRendering1747 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_ruleKRendering1774 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_ruleKRendering1801 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_ruleKRendering1828 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_ruleKRendering1855 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_ruleKRendering1882 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_ruleKRendering1909 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_ruleKRendering1936 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_ruleKRendering1963 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData1998 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacementData2008 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData2055 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData2082 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData2109 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData2136 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData2163 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle2198 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle2208 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_ruleKStyle2255 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_ruleKStyle2282 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle2309 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle2336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle2363 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle2390 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle2417 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement2452 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement2462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement2509 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_ruleKPlacement2536 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition2571 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition2581 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition2628 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition2655 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition2690 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition2700 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition2747 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition2774 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef2809 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef2819 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleKRenderingRef2856 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef2868 = new BitSet(new long[]{0x0000000090000000L});
        public static final BitSet FOLLOW_28_in_ruleKRenderingRef2881 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKRenderingRef2893 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef2916 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef2929 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef2952 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_30_in_ruleKRenderingRef2966 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_31_in_ruleKRenderingRef2980 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef3003 = new BitSet(new long[]{0x0000000300040000L});
        public static final BitSet FOLLOW_32_in_ruleKRenderingRef3016 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef3037 = new BitSet(new long[]{0x0000000200040000L});
        public static final BitSet FOLLOW_33_in_ruleKRenderingRef3052 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef3064 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef3085 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef3098 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef3119 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef3133 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef3147 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse3183 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse3193 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_ruleKEllipse3239 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse3251 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_33_in_ruleKEllipse3264 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3276 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse3297 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse3310 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse3331 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_32_in_ruleKEllipse3348 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3360 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse3381 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_35_in_ruleKEllipse3396 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3408 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse3429 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse3444 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3456 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse3477 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse3490 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse3511 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse3527 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle3563 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle3573 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKRectangle3619 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle3631 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_33_in_ruleKRectangle3644 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle3656 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle3677 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle3690 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle3711 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_32_in_ruleKRectangle3728 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle3740 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle3761 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_35_in_ruleKRectangle3776 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle3788 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle3809 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle3824 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle3836 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle3857 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle3870 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle3891 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKRectangle3907 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle3943 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle3953 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_37_in_ruleKRoundedRectangle3990 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle4002 = new BitSet(new long[]{0x000000C000000000L});
        public static final BitSet FOLLOW_38_in_ruleKRoundedRectangle4016 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4037 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleKRoundedRectangle4049 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4070 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_39_in_ruleKRoundedRectangle4090 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4111 = new BitSet(new long[]{0x0000004000000000L});
        public static final BitSet FOLLOW_38_in_ruleKRoundedRectangle4123 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4144 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_33_in_ruleKRoundedRectangle4159 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4171 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4192 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle4205 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4226 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_32_in_ruleKRoundedRectangle4243 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4255 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle4276 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_35_in_ruleKRoundedRectangle4291 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4303 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle4324 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle4339 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4351 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4372 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle4385 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4406 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle4422 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl4458 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl4468 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_40_in_ruleKPolyline_Impl4514 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl4526 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_33_in_ruleKPolyline_Impl4539 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4551 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4572 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl4585 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4606 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_32_in_ruleKPolyline_Impl4623 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4635 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl4656 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_35_in_ruleKPolyline_Impl4671 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4683 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl4704 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl4719 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4731 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl4752 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl4765 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl4786 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl4802 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon4838 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon4848 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_41_in_ruleKPolygon4894 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon4906 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_33_in_ruleKPolygon4919 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon4931 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4952 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon4965 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4986 = new BitSet(new long[]{0x0000000900064000L});
        public static final BitSet FOLLOW_32_in_ruleKPolygon5003 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5015 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon5036 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_35_in_ruleKPolygon5051 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5063 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon5084 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon5099 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5111 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon5132 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon5145 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon5166 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKPolygon5182 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage5218 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage5228 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_42_in_ruleKImage5265 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage5277 = new BitSet(new long[]{0x0000080000000000L});
        public static final BitSet FOLLOW_43_in_ruleKImage5289 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage5310 = new BitSet(new long[]{0x0000100000000000L});
        public static final BitSet FOLLOW_44_in_ruleKImage5322 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage5343 = new BitSet(new long[]{0x0000000B10060000L});
        public static final BitSet FOLLOW_28_in_ruleKImage5356 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKImage5368 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage5391 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_14_in_ruleKImage5404 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage5427 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_30_in_ruleKImage5441 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_32_in_ruleKImage5456 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage5477 = new BitSet(new long[]{0x0000000A00060000L});
        public static final BitSet FOLLOW_33_in_ruleKImage5492 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage5504 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage5525 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKImage5538 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage5559 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKImage5573 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_17_in_ruleKImage5588 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage5600 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage5621 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKImage5634 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage5655 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKImage5669 = new BitSet(new long[]{0x0000000800040000L});
        public static final BitSet FOLLOW_35_in_ruleKImage5684 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage5705 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKImage5719 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc5755 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc5765 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_45_in_ruleKArc5811 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc5823 = new BitSet(new long[]{0x0000C00B10060000L});
        public static final BitSet FOLLOW_46_in_ruleKArc5836 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc5857 = new BitSet(new long[]{0x0000800B10060000L});
        public static final BitSet FOLLOW_47_in_ruleKArc5872 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc5893 = new BitSet(new long[]{0x0000000B10060000L});
        public static final BitSet FOLLOW_28_in_ruleKArc5908 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKArc5920 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc5943 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5956 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc5979 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_30_in_ruleKArc5993 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_32_in_ruleKArc6008 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc6029 = new BitSet(new long[]{0x0000000A00060000L});
        public static final BitSet FOLLOW_33_in_ruleKArc6044 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc6056 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc6077 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKArc6090 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc6111 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKArc6125 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_17_in_ruleKArc6140 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc6152 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc6173 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKArc6186 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc6207 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKArc6221 = new BitSet(new long[]{0x0000000800040000L});
        public static final BitSet FOLLOW_35_in_ruleKArc6236 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc6257 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKArc6271 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea6307 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea6317 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_48_in_ruleKChildArea6363 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea6375 = new BitSet(new long[]{0x0000000310040000L});
        public static final BitSet FOLLOW_28_in_ruleKChildArea6388 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKChildArea6400 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea6423 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea6436 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea6459 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_30_in_ruleKChildArea6473 = new BitSet(new long[]{0x0000000300040000L});
        public static final BitSet FOLLOW_32_in_ruleKChildArea6488 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea6509 = new BitSet(new long[]{0x0000000200040000L});
        public static final BitSet FOLLOW_33_in_ruleKChildArea6524 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea6536 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea6557 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea6570 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea6591 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea6605 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea6619 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText6655 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText6665 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_49_in_ruleKText6702 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText6714 = new BitSet(new long[]{0x0004000000000000L});
        public static final BitSet FOLLOW_50_in_ruleKText6732 = new BitSet(new long[]{0x0008000B10060000L});
        public static final BitSet FOLLOW_51_in_ruleKText6758 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText6779 = new BitSet(new long[]{0x0000000B10060000L});
        public static final BitSet FOLLOW_28_in_ruleKText6794 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKText6806 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText6829 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_14_in_ruleKText6842 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText6865 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_30_in_ruleKText6879 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_32_in_ruleKText6894 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText6915 = new BitSet(new long[]{0x0000000A00060000L});
        public static final BitSet FOLLOW_33_in_ruleKText6930 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText6942 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText6963 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKText6976 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText6997 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKText7011 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_17_in_ruleKText7026 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText7038 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText7059 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKText7072 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText7093 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKText7107 = new BitSet(new long[]{0x0000000800040000L});
        public static final BitSet FOLLOW_35_in_ruleKText7122 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText7143 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKText7157 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering7193 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering7203 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_52_in_ruleKCustomRendering7240 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering7252 = new BitSet(new long[]{0x0020000000000000L});
        public static final BitSet FOLLOW_53_in_ruleKCustomRendering7264 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering7285 = new BitSet(new long[]{0x0000080000000000L});
        public static final BitSet FOLLOW_43_in_ruleKCustomRendering7297 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering7318 = new BitSet(new long[]{0x0000000B10060000L});
        public static final BitSet FOLLOW_28_in_ruleKCustomRendering7331 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKCustomRendering7343 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering7366 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering7379 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering7402 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_30_in_ruleKCustomRendering7416 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_32_in_ruleKCustomRendering7431 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering7452 = new BitSet(new long[]{0x0000000A00060000L});
        public static final BitSet FOLLOW_33_in_ruleKCustomRendering7467 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering7479 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering7500 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering7513 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering7534 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering7548 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering7563 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering7575 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering7596 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering7609 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering7630 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering7644 = new BitSet(new long[]{0x0000000800040000L});
        public static final BitSet FOLLOW_35_in_ruleKCustomRendering7659 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering7680 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering7694 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline7730 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline7740 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_54_in_ruleKSpline7786 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline7798 = new BitSet(new long[]{0x0000000B10060000L});
        public static final BitSet FOLLOW_28_in_ruleKSpline7811 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKSpline7823 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline7846 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7859 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline7882 = new BitSet(new long[]{0x0000000040004000L});
        public static final BitSet FOLLOW_30_in_ruleKSpline7896 = new BitSet(new long[]{0x0000000B00060000L});
        public static final BitSet FOLLOW_32_in_ruleKSpline7911 = new BitSet(new long[]{0x4080000000000000L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline7932 = new BitSet(new long[]{0x0000000A00060000L});
        public static final BitSet FOLLOW_33_in_ruleKSpline7947 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline7959 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline7980 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7993 = new BitSet(new long[]{0x0000000000000000L,0x0000003DC2000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline8014 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline8028 = new BitSet(new long[]{0x0000000800060000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline8043 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline8055 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline8076 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline8089 = new BitSet(new long[]{0x0053273408000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline8110 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline8124 = new BitSet(new long[]{0x0000000800040000L});
        public static final BitSet FOLLOW_35_in_ruleKSpline8139 = new BitSet(new long[]{0x0000000000000000L,0x000000C000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline8160 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline8174 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData8210 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData8220 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_55_in_ruleKDecoratorPlacementData8257 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData8269 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_56_in_ruleKDecoratorPlacementData8287 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKDecoratorPlacementData8312 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8333 = new BitSet(new long[]{0x3C00000000040000L});
        public static final BitSet FOLLOW_58_in_ruleKDecoratorPlacementData8346 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8367 = new BitSet(new long[]{0x3800000000040000L});
        public static final BitSet FOLLOW_59_in_ruleKDecoratorPlacementData8382 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8403 = new BitSet(new long[]{0x3000000000040000L});
        public static final BitSet FOLLOW_60_in_ruleKDecoratorPlacementData8418 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8439 = new BitSet(new long[]{0x2000000000040000L});
        public static final BitSet FOLLOW_61_in_ruleKDecoratorPlacementData8454 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8475 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKDecoratorPlacementData8489 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData8525 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData8535 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_62_in_ruleKGridPlacementData8572 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData8584 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_63_in_ruleKGridPlacementData8596 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKGridPlacementData8629 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKGridPlacementData8662 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8683 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
        public static final BitSet FOLLOW_66_in_ruleKGridPlacementData8695 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8716 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKGridPlacementData8728 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData8764 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData8774 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_67_in_ruleKStackPlacementData8811 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData8823 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_68_in_ruleKStackPlacementData8835 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8856 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
        public static final BitSet FOLLOW_69_in_ruleKStackPlacementData8868 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8889 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_70_in_ruleKStackPlacementData8901 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_71_in_ruleKStackPlacementData8934 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8955 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKStackPlacementData8967 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData9003 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData9013 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_72_in_ruleKDirectPlacementData9050 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData9062 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKDirectPlacementData9074 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData9095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_74_in_ruleKDirectPlacementData9107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData9128 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKDirectPlacementData9140 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData9176 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData9186 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleKPolylinePlacementData9223 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolylinePlacementData9235 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9256 = new BitSet(new long[]{0x0000000000004000L,0x0000000000001000L});
        public static final BitSet FOLLOW_14_in_ruleKPolylinePlacementData9269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9290 = new BitSet(new long[]{0x0000000000004000L,0x0000000000001000L});
        public static final BitSet FOLLOW_76_in_ruleKPolylinePlacementData9304 = new BitSet(new long[]{0x4080000000000002L,0x0000000000000908L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData9325 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat9363 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat9374 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleEFloat9413 = new BitSet(new long[]{0x0000000000000010L,0x0000000000004000L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat9431 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
        public static final BitSet FOLLOW_78_in_ruleEFloat9451 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat9466 = new BitSet(new long[]{0x0000000000000002L,0x0000000000018000L});
        public static final BitSet FOLLOW_79_in_ruleEFloat9486 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_80_in_ruleEFloat9505 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleEFloat9520 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat9537 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition9586 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition9596 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_81_in_ruleKPosition9633 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPosition9645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_82_in_ruleKPosition9657 = new BitSet(new long[]{0x0000000000000000L,0x0000000000500000L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition9678 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_83_in_ruleKPosition9690 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition9711 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPosition9723 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition9759 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition9769 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_84_in_ruleKLeftPosition9815 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLeftPosition9827 = new BitSet(new long[]{0x0100000000040000L,0x0000000000200000L});
        public static final BitSet FOLLOW_85_in_ruleKLeftPosition9840 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition9861 = new BitSet(new long[]{0x0100000000040000L});
        public static final BitSet FOLLOW_56_in_ruleKLeftPosition9876 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition9897 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKLeftPosition9911 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition9947 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition9957 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_86_in_ruleKRightPosition10003 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRightPosition10015 = new BitSet(new long[]{0x0100000000040000L,0x0000000000200000L});
        public static final BitSet FOLLOW_85_in_ruleKRightPosition10028 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition10049 = new BitSet(new long[]{0x0100000000040000L});
        public static final BitSet FOLLOW_56_in_ruleKRightPosition10064 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition10085 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRightPosition10099 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition10135 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition10145 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleKTopPosition10191 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKTopPosition10203 = new BitSet(new long[]{0x0100000000040000L,0x0000000000200000L});
        public static final BitSet FOLLOW_85_in_ruleKTopPosition10216 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition10237 = new BitSet(new long[]{0x0100000000040000L});
        public static final BitSet FOLLOW_56_in_ruleKTopPosition10252 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition10273 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKTopPosition10287 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition10323 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition10333 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_88_in_ruleKBottomPosition10379 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBottomPosition10391 = new BitSet(new long[]{0x0100000000040000L,0x0000000000200000L});
        public static final BitSet FOLLOW_85_in_ruleKBottomPosition10404 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition10425 = new BitSet(new long[]{0x0100000000040000L});
        public static final BitSet FOLLOW_56_in_ruleKBottomPosition10440 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition10461 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKBottomPosition10475 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor10511 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor10521 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_89_in_ruleKForegroundColor10567 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKForegroundColor10579 = new BitSet(new long[]{0x0000000000040000L,0x000000003C000000L});
        public static final BitSet FOLLOW_90_in_ruleKForegroundColor10597 = new BitSet(new long[]{0x0000000000040000L,0x0000000038000000L});
        public static final BitSet FOLLOW_91_in_ruleKForegroundColor10624 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor10645 = new BitSet(new long[]{0x0000000000040000L,0x0000000030000000L});
        public static final BitSet FOLLOW_92_in_ruleKForegroundColor10660 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor10681 = new BitSet(new long[]{0x0000000000040000L,0x0000000020000000L});
        public static final BitSet FOLLOW_93_in_ruleKForegroundColor10696 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor10717 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKForegroundColor10731 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor10767 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor10777 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_94_in_ruleKBackgroundColor10823 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBackgroundColor10835 = new BitSet(new long[]{0x0000000000040000L,0x000000003C000000L});
        public static final BitSet FOLLOW_90_in_ruleKBackgroundColor10853 = new BitSet(new long[]{0x0000000000040000L,0x0000000038000000L});
        public static final BitSet FOLLOW_91_in_ruleKBackgroundColor10880 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10901 = new BitSet(new long[]{0x0000000000040000L,0x0000000030000000L});
        public static final BitSet FOLLOW_92_in_ruleKBackgroundColor10916 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10937 = new BitSet(new long[]{0x0000000000040000L,0x0000000020000000L});
        public static final BitSet FOLLOW_93_in_ruleKBackgroundColor10952 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10973 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKBackgroundColor10987 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth11023 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth11033 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleKLineWidth11070 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth11091 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKLineWidth11109 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility11159 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility11169 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility11216 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility11243 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility11278 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundVisibility11288 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleKForegroundVisibility11334 = new BitSet(new long[]{0x0000000000000002L,0x0000000204000000L});
        public static final BitSet FOLLOW_97_in_ruleKForegroundVisibility11352 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKForegroundVisibility11384 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility11434 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundVisibility11444 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleKBackgroundVisibility11490 = new BitSet(new long[]{0x0000000000000002L,0x0000000204000000L});
        public static final BitSet FOLLOW_97_in_ruleKBackgroundVisibility11508 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKBackgroundVisibility11540 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle11590 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle11600 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_99_in_ruleKLineStyle11646 = new BitSet(new long[]{0x0000000000000002L,0x1F00000004000000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle11667 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKLineStyle11686 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment11736 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment11746 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_100_in_ruleKVerticalAlignment11783 = new BitSet(new long[]{0x0000000000000000L,0xE000000000000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment11804 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKVerticalAlignment11822 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment11872 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment11882 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_101_in_ruleKHorizontalAlignment11919 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11940 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKHorizontalAlignment11958 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement12007 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement12017 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_102_in_ruleKGridPlacement12063 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement12084 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement12120 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement12130 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_103_in_ruleKStackPlacement12176 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt12213 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt12224 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleEInt12263 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt12280 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout12325 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKShapeLayout12335 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_104_in_ruleKShapeLayout12381 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKShapeLayout12393 = new BitSet(new long[]{0x3000000000040000L,0x00001E0000000000L});
        public static final BitSet FOLLOW_105_in_ruleKShapeLayout12406 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12427 = new BitSet(new long[]{0x3000000000040000L,0x00001C0000000000L});
        public static final BitSet FOLLOW_106_in_ruleKShapeLayout12442 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12463 = new BitSet(new long[]{0x3000000000040000L,0x0000180000000000L});
        public static final BitSet FOLLOW_60_in_ruleKShapeLayout12478 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12499 = new BitSet(new long[]{0x2000000000040000L,0x0000180000000000L});
        public static final BitSet FOLLOW_61_in_ruleKShapeLayout12514 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12535 = new BitSet(new long[]{0x0000000000040000L,0x0000180000000000L});
        public static final BitSet FOLLOW_107_in_ruleKShapeLayout12550 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
        public static final BitSet FOLLOW_ruleKInsets_in_ruleKShapeLayout12571 = new BitSet(new long[]{0x0000000000040000L,0x0000100000000000L});
        public static final BitSet FOLLOW_108_in_ruleKShapeLayout12586 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKShapeLayout12598 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12619 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKShapeLayout12632 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12653 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKShapeLayout12669 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets12705 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets12715 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_109_in_ruleKInsets12761 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets12773 = new BitSet(new long[]{0x0000000000040000L,0x0003C00000000000L});
        public static final BitSet FOLLOW_110_in_ruleKInsets12786 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12807 = new BitSet(new long[]{0x0000000000040000L,0x0003800000000000L});
        public static final BitSet FOLLOW_111_in_ruleKInsets12822 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12843 = new BitSet(new long[]{0x0000000000040000L,0x0003000000000000L});
        public static final BitSet FOLLOW_112_in_ruleKInsets12858 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12879 = new BitSet(new long[]{0x0000000000040000L,0x0002000000000000L});
        public static final BitSet FOLLOW_113_in_ruleKInsets12894 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12915 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKInsets12929 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdgeLayout_in_entryRuleKEdgeLayout12965 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEdgeLayout12975 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_114_in_ruleKEdgeLayout13012 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdgeLayout13024 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
        public static final BitSet FOLLOW_115_in_ruleKEdgeLayout13036 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13057 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
        public static final BitSet FOLLOW_116_in_ruleKEdgeLayout13069 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13090 = new BitSet(new long[]{0x0000000000040000L,0x0020100000000000L});
        public static final BitSet FOLLOW_117_in_ruleKEdgeLayout13103 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEdgeLayout13115 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13136 = new BitSet(new long[]{0x0000000000044000L,0x0000100000000000L});
        public static final BitSet FOLLOW_14_in_ruleKEdgeLayout13149 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13170 = new BitSet(new long[]{0x0000000000044000L,0x0000100000000000L});
        public static final BitSet FOLLOW_108_in_ruleKEdgeLayout13187 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEdgeLayout13199 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13220 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_14_in_ruleKEdgeLayout13233 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13254 = new BitSet(new long[]{0x0000000000044000L});
        public static final BitSet FOLLOW_18_in_ruleKEdgeLayout13270 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint13306 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint13316 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_118_in_ruleKPoint13362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_82_in_ruleKPoint13375 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint13396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_83_in_ruleKPoint13410 = new BitSet(new long[]{0x0000000000000010L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint13431 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry13468 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry13478 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry13524 = new BitSet(new long[]{0x0000000000000002L,0x0080000000000000L});
        public static final BitSet FOLLOW_119_in_rulePersistentEntry13537 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry13558 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString13597 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString13608 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString13648 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString13674 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_120_in_ruleLineStyle13733 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_121_in_ruleLineStyle13750 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_122_in_ruleLineStyle13767 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_123_in_ruleLineStyle13784 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_124_in_ruleLineStyle13801 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_125_in_ruleVerticalAlignment13846 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_126_in_ruleVerticalAlignment13863 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_127_in_ruleVerticalAlignment13880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_128_in_ruleHorizontalAlignment13925 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_126_in_ruleHorizontalAlignment13942 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_129_in_ruleHorizontalAlignment13959 = new BitSet(new long[]{0x0000000000000002L});
    }


}