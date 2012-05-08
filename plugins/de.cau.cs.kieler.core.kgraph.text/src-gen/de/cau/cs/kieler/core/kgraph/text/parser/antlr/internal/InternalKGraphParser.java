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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'KNode'", "'{'", "'ports'", "','", "'data'", "':'", "'children'", "'}'", "'KLabel'", "'KPort'", "'edges'", "'labels'", "'-->'", "'sourcePort'", "'targetPort'", "'RenderingLibrary'", "'RenderingRef'", "'placementData'", "'styles'", "'Ellipse'", "'childPlacement'", "'Rectangle'", "'RoundedRectangle'", "'Polyline'", "'Polygon'", "'Image'", "'-'", "'Arc'", "'ChildArea'", "'Text'", "'clip'", "'CustomRendering'", "'className'", "'bundleName'", "'Spline'", "'DecoratorPlacementData'", "'relative'", "'location'", "'xOffset'", "'yOffset'", "'width'", "'height'", "'GridPlacementData'", "'widthHint'", "'heightHint'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'StackPlacementData'", "'DirectPlacementData'", "'topLeft'", "'bottomRight'", "'PolylinePlacementData'", "'points'", "'detailedPlacementData'", "'/'", "'left'", "'right'", "'top'", "'bottom'", "'ForegroundColor'", "'!'", "'BackgroundColor'", "'LineWidth'", "'ForegroundVisibility'", "'BackgroundVisibility'", "'LineStyle'", "'bold'", "'italic'", "'font'", "'fontSize'", "'VerticalAlignment'", "'HorizontalAlignment'", "'GridPlacement'", "'StackPlacement'", "'.'", "'E'", "'e'", "'true'", "'false'", "'KShapeLayout'", "'xpos'", "'ypos'", "'insets'", "'mapProperties'", "'KInsets'", "'KEdgeLayout'", "'sourcePoint'", "'targetPoint'", "'bendPoints'", "'KPoint'", "'x'", "'y'", "'='", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
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
    public static final int RULE_STRING=5;
    public static final int T__71=71;
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
    public static final int T__114=114;
    public static final int T__115=115;
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:77:1: ruleKNode returns [EObject current=null] : ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' (otherlv_13= ':' )? ( (lv_children_14_0= ruleKNode ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:80:28: ( ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' (otherlv_13= ':' )? ( (lv_children_14_0= ruleKNode ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:1: ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' (otherlv_13= ':' )? ( (lv_children_14_0= ruleKNode ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:1: ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' (otherlv_13= ':' )? ( (lv_children_14_0= ruleKNode ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:2: () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )* )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'children' (otherlv_13= ':' )? ( (lv_children_14_0= ruleKNode ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )* )? otherlv_17= '}' ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )?
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
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:1: (otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==13) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:3: otherlv_3= 'ports' ( (lv_ports_4_0= ruleKPort ) ) ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )*
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:2: ( (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==14||LA2_0==20) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:3: (otherlv_5= ',' )? ( (lv_ports_6_0= ruleKPort ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:3: (otherlv_5= ',' )?
                    	    int alt1=2;
                    	    int LA1_0 = input.LA(1);

                    	    if ( (LA1_0==14) ) {
                    	        alt1=1;
                    	    }
                    	    switch (alt1) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:117:5: otherlv_5= ','
                    	            {
                    	            otherlv_5=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode191); 

                    	                	newLeafNode(otherlv_5, grammarAccess.getKNodeAccess().getCommaKeyword_3_2_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:121:3: ( (lv_ports_6_0= ruleKPort ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:122:1: (lv_ports_6_0= ruleKPort )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:122:1: (lv_ports_6_0= ruleKPort )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:123:3: lv_ports_6_0= ruleKPort
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getPortsKPortParserRuleCall_3_2_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPort_in_ruleKNode214);
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
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:139:6: (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:139:8: otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )*
                    {
                    otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode231); 

                        	newLeafNode(otherlv_7, grammarAccess.getKNodeAccess().getDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:143:1: (otherlv_8= ':' )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==16) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:143:3: otherlv_8= ':'
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKNode244); 

                                	newLeafNode(otherlv_8, grammarAccess.getKNodeAccess().getColonKeyword_4_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:147:3: ( (lv_data_9_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:148:1: (lv_data_9_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:148:1: (lv_data_9_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:149:3: lv_data_9_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKGraphDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKNode267);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:165:2: ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==14||(LA6_0>=26 && LA6_0<=27)||LA6_0==30||(LA6_0>=32 && LA6_0<=36)||(LA6_0>=38 && LA6_0<=40)||LA6_0==42||LA6_0==45||LA6_0==92||LA6_0==98) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:165:3: (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:165:3: (otherlv_10= ',' )?
                    	    int alt5=2;
                    	    int LA5_0 = input.LA(1);

                    	    if ( (LA5_0==14) ) {
                    	        alt5=1;
                    	    }
                    	    switch (alt5) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:165:5: otherlv_10= ','
                    	            {
                    	            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode281); 

                    	                	newLeafNode(otherlv_10, grammarAccess.getKNodeAccess().getCommaKeyword_4_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:169:3: ( (lv_data_11_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:170:1: (lv_data_11_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:170:1: (lv_data_11_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:171:3: lv_data_11_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKGraphDataParserRuleCall_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKNode304);
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
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:187:6: (otherlv_12= 'children' (otherlv_13= ':' )? ( (lv_children_14_0= ruleKNode ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )* )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==17) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:187:8: otherlv_12= 'children' (otherlv_13= ':' )? ( (lv_children_14_0= ruleKNode ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )*
                    {
                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKNode321); 

                        	newLeafNode(otherlv_12, grammarAccess.getKNodeAccess().getChildrenKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:191:1: (otherlv_13= ':' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==16) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:191:3: otherlv_13= ':'
                            {
                            otherlv_13=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKNode334); 

                                	newLeafNode(otherlv_13, grammarAccess.getKNodeAccess().getColonKeyword_5_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:195:3: ( (lv_children_14_0= ruleKNode ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:196:1: (lv_children_14_0= ruleKNode )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:196:1: (lv_children_14_0= ruleKNode )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:197:3: lv_children_14_0= ruleKNode
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKNode_in_ruleKNode357);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:213:2: ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==11||LA10_0==14) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:213:3: (otherlv_15= ',' )? ( (lv_children_16_0= ruleKNode ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:213:3: (otherlv_15= ',' )?
                    	    int alt9=2;
                    	    int LA9_0 = input.LA(1);

                    	    if ( (LA9_0==14) ) {
                    	        alt9=1;
                    	    }
                    	    switch (alt9) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:213:5: otherlv_15= ','
                    	            {
                    	            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode371); 

                    	                	newLeafNode(otherlv_15, grammarAccess.getKNodeAccess().getCommaKeyword_5_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:217:3: ( (lv_children_16_0= ruleKNode ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:218:1: (lv_children_16_0= ruleKNode )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:218:1: (lv_children_16_0= ruleKNode )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:219:3: lv_children_16_0= ruleKNode
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKNode_in_ruleKNode394);
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
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode410); 

                	newLeafNode(otherlv_17, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:239:1: ( ( (lv_outgoingEdges_18_0= ruleKEdge ) ) ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )* )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==23) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:239:2: ( (lv_outgoingEdges_18_0= ruleKEdge ) ) ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )*
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:239:2: ( (lv_outgoingEdges_18_0= ruleKEdge ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:240:1: (lv_outgoingEdges_18_0= ruleKEdge )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:240:1: (lv_outgoingEdges_18_0= ruleKEdge )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:241:3: lv_outgoingEdges_18_0= ruleKEdge
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_7_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_ruleKNode432);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:257:2: ( (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==14) ) {
                            int LA13_2 = input.LA(2);

                            if ( (LA13_2==23) ) {
                                alt13=1;
                            }


                        }
                        else if ( (LA13_0==23) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:257:3: (otherlv_19= ',' )? ( (lv_outgoingEdges_20_0= ruleKEdge ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:257:3: (otherlv_19= ',' )?
                    	    int alt12=2;
                    	    int LA12_0 = input.LA(1);

                    	    if ( (LA12_0==14) ) {
                    	        alt12=1;
                    	    }
                    	    switch (alt12) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:257:5: otherlv_19= ','
                    	            {
                    	            otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode446); 

                    	                	newLeafNode(otherlv_19, grammarAccess.getKNodeAccess().getCommaKeyword_7_1_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:261:3: ( (lv_outgoingEdges_20_0= ruleKEdge ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:262:1: (lv_outgoingEdges_20_0= ruleKEdge )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:262:1: (lv_outgoingEdges_20_0= ruleKEdge )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:263:3: lv_outgoingEdges_20_0= ruleKEdge
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_7_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_ruleKNode469);
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
                    	    break loop13;
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
            pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_entryRuleKLabel509);
            iv_ruleKLabel=ruleKLabel();

            state._fsp--;

             current =iv_ruleKLabel; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLabel519); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:296:1: ruleKLabel returns [EObject current=null] : (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' (otherlv_4= ':' )? ( (lv_data_5_0= ruleKGraphData ) ) ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:299:28: ( (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' (otherlv_4= ':' )? ( (lv_data_5_0= ruleKGraphData ) ) ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:300:1: (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' (otherlv_4= ':' )? ( (lv_data_5_0= ruleKGraphData ) ) ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:300:1: (otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' (otherlv_4= ':' )? ( (lv_data_5_0= ruleKGraphData ) ) ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:300:3: otherlv_0= 'KLabel' ( (lv_text_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'data' (otherlv_4= ':' )? ( (lv_data_5_0= ruleKGraphData ) ) ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )* )? otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKLabel556); 

                	newLeafNode(otherlv_0, grammarAccess.getKLabelAccess().getKLabelKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:304:1: ( (lv_text_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:305:1: (lv_text_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:305:1: (lv_text_1_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:306:3: lv_text_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKLabelAccess().getTextEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKLabel577);
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

            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLabel589); 

                	newLeafNode(otherlv_2, grammarAccess.getKLabelAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:326:1: (otherlv_3= 'data' (otherlv_4= ':' )? ( (lv_data_5_0= ruleKGraphData ) ) ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==15) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:326:3: otherlv_3= 'data' (otherlv_4= ':' )? ( (lv_data_5_0= ruleKGraphData ) ) ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )*
                    {
                    otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKLabel602); 

                        	newLeafNode(otherlv_3, grammarAccess.getKLabelAccess().getDataKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:330:1: (otherlv_4= ':' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==16) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:330:3: otherlv_4= ':'
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKLabel615); 

                                	newLeafNode(otherlv_4, grammarAccess.getKLabelAccess().getColonKeyword_3_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:334:3: ( (lv_data_5_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:335:1: (lv_data_5_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:335:1: (lv_data_5_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:336:3: lv_data_5_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKGraphDataParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKLabel638);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:352:2: ( (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==14||(LA17_0>=26 && LA17_0<=27)||LA17_0==30||(LA17_0>=32 && LA17_0<=36)||(LA17_0>=38 && LA17_0<=40)||LA17_0==42||LA17_0==45||LA17_0==92||LA17_0==98) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:352:3: (otherlv_6= ',' )? ( (lv_data_7_0= ruleKGraphData ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:352:3: (otherlv_6= ',' )?
                    	    int alt16=2;
                    	    int LA16_0 = input.LA(1);

                    	    if ( (LA16_0==14) ) {
                    	        alt16=1;
                    	    }
                    	    switch (alt16) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:352:5: otherlv_6= ','
                    	            {
                    	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKLabel652); 

                    	                	newLeafNode(otherlv_6, grammarAccess.getKLabelAccess().getCommaKeyword_3_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:356:3: ( (lv_data_7_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:357:1: (lv_data_7_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:357:1: (lv_data_7_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:358:3: lv_data_7_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKGraphDataParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKLabel675);
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
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLabel691); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKPort_in_entryRuleKPort727);
            iv_ruleKPort=ruleKPort();

            state._fsp--;

             current =iv_ruleKPort; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPort737); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:395:1: ruleKPort returns [EObject current=null] : ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' (otherlv_4= ':' )? ( ( ruleEString ) ) ( (otherlv_6= ',' )? ( ( ruleEString ) ) )* )? (otherlv_8= 'data' (otherlv_9= ':' )? ( (lv_data_10_0= ruleKGraphData ) ) ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' (otherlv_14= ':' )? ( (lv_labels_15_0= ruleKLabel ) ) ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:398:28: ( ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' (otherlv_4= ':' )? ( ( ruleEString ) ) ( (otherlv_6= ',' )? ( ( ruleEString ) ) )* )? (otherlv_8= 'data' (otherlv_9= ':' )? ( (lv_data_10_0= ruleKGraphData ) ) ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' (otherlv_14= ':' )? ( (lv_labels_15_0= ruleKLabel ) ) ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:1: ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' (otherlv_4= ':' )? ( ( ruleEString ) ) ( (otherlv_6= ',' )? ( ( ruleEString ) ) )* )? (otherlv_8= 'data' (otherlv_9= ':' )? ( (lv_data_10_0= ruleKGraphData ) ) ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' (otherlv_14= ':' )? ( (lv_labels_15_0= ruleKLabel ) ) ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:1: ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' (otherlv_4= ':' )? ( ( ruleEString ) ) ( (otherlv_6= ',' )? ( ( ruleEString ) ) )* )? (otherlv_8= 'data' (otherlv_9= ':' )? ( (lv_data_10_0= ruleKGraphData ) ) ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' (otherlv_14= ':' )? ( (lv_labels_15_0= ruleKLabel ) ) ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:2: () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' (otherlv_4= ':' )? ( ( ruleEString ) ) ( (otherlv_6= ',' )? ( ( ruleEString ) ) )* )? (otherlv_8= 'data' (otherlv_9= ':' )? ( (lv_data_10_0= ruleKGraphData ) ) ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )* )? (otherlv_13= 'labels' (otherlv_14= ':' )? ( (lv_labels_15_0= ruleKLabel ) ) ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )* )? otherlv_18= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:399:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:400:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPortAccess().getKPortAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPort783); 

                	newLeafNode(otherlv_1, grammarAccess.getKPortAccess().getKPortKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPort795); 

                	newLeafNode(otherlv_2, grammarAccess.getKPortAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:413:1: (otherlv_3= 'edges' (otherlv_4= ':' )? ( ( ruleEString ) ) ( (otherlv_6= ',' )? ( ( ruleEString ) ) )* )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==21) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:413:3: otherlv_3= 'edges' (otherlv_4= ':' )? ( ( ruleEString ) ) ( (otherlv_6= ',' )? ( ( ruleEString ) ) )*
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPort808); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPortAccess().getEdgesKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:417:1: (otherlv_4= ':' )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==16) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:417:3: otherlv_4= ':'
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPort821); 

                                	newLeafNode(otherlv_4, grammarAccess.getKPortAccess().getColonKeyword_3_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:421:3: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:422:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:422:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:423:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKPortRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getEdgesKEdgeCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPort846);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:436:2: ( (otherlv_6= ',' )? ( ( ruleEString ) ) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( ((LA21_0>=RULE_STRING && LA21_0<=RULE_ID)||LA21_0==14) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:436:3: (otherlv_6= ',' )? ( ( ruleEString ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:436:3: (otherlv_6= ',' )?
                    	    int alt20=2;
                    	    int LA20_0 = input.LA(1);

                    	    if ( (LA20_0==14) ) {
                    	        alt20=1;
                    	    }
                    	    switch (alt20) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:436:5: otherlv_6= ','
                    	            {
                    	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPort860); 

                    	                	newLeafNode(otherlv_6, grammarAccess.getKPortAccess().getCommaKeyword_3_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:440:3: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:441:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:441:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:442:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKPortRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getEdgesKEdgeCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPort885);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:455:6: (otherlv_8= 'data' (otherlv_9= ':' )? ( (lv_data_10_0= ruleKGraphData ) ) ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )* )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==15) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:455:8: otherlv_8= 'data' (otherlv_9= ':' )? ( (lv_data_10_0= ruleKGraphData ) ) ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )*
                    {
                    otherlv_8=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPort902); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPortAccess().getDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:459:1: (otherlv_9= ':' )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==16) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:459:3: otherlv_9= ':'
                            {
                            otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPort915); 

                                	newLeafNode(otherlv_9, grammarAccess.getKPortAccess().getColonKeyword_4_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:463:3: ( (lv_data_10_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:464:1: (lv_data_10_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:464:1: (lv_data_10_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:465:3: lv_data_10_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKGraphDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKPort938);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:481:2: ( (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==14||(LA25_0>=26 && LA25_0<=27)||LA25_0==30||(LA25_0>=32 && LA25_0<=36)||(LA25_0>=38 && LA25_0<=40)||LA25_0==42||LA25_0==45||LA25_0==92||LA25_0==98) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:481:3: (otherlv_11= ',' )? ( (lv_data_12_0= ruleKGraphData ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:481:3: (otherlv_11= ',' )?
                    	    int alt24=2;
                    	    int LA24_0 = input.LA(1);

                    	    if ( (LA24_0==14) ) {
                    	        alt24=1;
                    	    }
                    	    switch (alt24) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:481:5: otherlv_11= ','
                    	            {
                    	            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPort952); 

                    	                	newLeafNode(otherlv_11, grammarAccess.getKPortAccess().getCommaKeyword_4_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:485:3: ( (lv_data_12_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:486:1: (lv_data_12_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:486:1: (lv_data_12_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:487:3: lv_data_12_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKGraphDataParserRuleCall_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKPort975);
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
                    	    break loop25;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:503:6: (otherlv_13= 'labels' (otherlv_14= ':' )? ( (lv_labels_15_0= ruleKLabel ) ) ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )* )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==22) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:503:8: otherlv_13= 'labels' (otherlv_14= ':' )? ( (lv_labels_15_0= ruleKLabel ) ) ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )*
                    {
                    otherlv_13=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKPort992); 

                        	newLeafNode(otherlv_13, grammarAccess.getKPortAccess().getLabelsKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:507:1: (otherlv_14= ':' )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==16) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:507:3: otherlv_14= ':'
                            {
                            otherlv_14=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPort1005); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPortAccess().getColonKeyword_5_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:511:3: ( (lv_labels_15_0= ruleKLabel ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:512:1: (lv_labels_15_0= ruleKLabel )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:512:1: (lv_labels_15_0= ruleKLabel )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:513:3: lv_labels_15_0= ruleKLabel
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKPort1028);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:529:2: ( (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==14||LA29_0==19) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:529:3: (otherlv_16= ',' )? ( (lv_labels_17_0= ruleKLabel ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:529:3: (otherlv_16= ',' )?
                    	    int alt28=2;
                    	    int LA28_0 = input.LA(1);

                    	    if ( (LA28_0==14) ) {
                    	        alt28=1;
                    	    }
                    	    switch (alt28) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:529:5: otherlv_16= ','
                    	            {
                    	            otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPort1042); 

                    	                	newLeafNode(otherlv_16, grammarAccess.getKPortAccess().getCommaKeyword_5_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:533:3: ( (lv_labels_17_0= ruleKLabel ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:534:1: (lv_labels_17_0= ruleKLabel )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:534:1: (lv_labels_17_0= ruleKLabel )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:535:3: lv_labels_17_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKPort1065);
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
                    	    break loop29;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPort1081); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_entryRuleKEdge1117);
            iv_ruleKEdge=ruleKEdge();

            state._fsp--;

             current =iv_ruleKEdge; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEdge1127); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:572:1: ruleKEdge returns [EObject current=null] : (otherlv_0= '-->' ( ( ruleEString ) ) otherlv_2= '{' (otherlv_3= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_5= 'targetPort' ( ( ruleEString ) ) )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'labels' (otherlv_13= ':' )? ( (lv_labels_14_0= ruleKLabel ) ) ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )* )? otherlv_17= '}' ) ;
    public final EObject ruleKEdge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
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
        EObject lv_data_9_0 = null;

        EObject lv_data_11_0 = null;

        EObject lv_labels_14_0 = null;

        EObject lv_labels_16_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:575:28: ( (otherlv_0= '-->' ( ( ruleEString ) ) otherlv_2= '{' (otherlv_3= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_5= 'targetPort' ( ( ruleEString ) ) )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'labels' (otherlv_13= ':' )? ( (lv_labels_14_0= ruleKLabel ) ) ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )* )? otherlv_17= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:576:1: (otherlv_0= '-->' ( ( ruleEString ) ) otherlv_2= '{' (otherlv_3= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_5= 'targetPort' ( ( ruleEString ) ) )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'labels' (otherlv_13= ':' )? ( (lv_labels_14_0= ruleKLabel ) ) ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )* )? otherlv_17= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:576:1: (otherlv_0= '-->' ( ( ruleEString ) ) otherlv_2= '{' (otherlv_3= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_5= 'targetPort' ( ( ruleEString ) ) )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'labels' (otherlv_13= ':' )? ( (lv_labels_14_0= ruleKLabel ) ) ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )* )? otherlv_17= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:576:3: otherlv_0= '-->' ( ( ruleEString ) ) otherlv_2= '{' (otherlv_3= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_5= 'targetPort' ( ( ruleEString ) ) )? (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )? (otherlv_12= 'labels' (otherlv_13= ':' )? ( (lv_labels_14_0= ruleKLabel ) ) ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )* )? otherlv_17= '}'
            {
            otherlv_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKEdge1164); 

                	newLeafNode(otherlv_0, grammarAccess.getKEdgeAccess().getHyphenMinusHyphenMinusGreaterThanSignKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:580:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:581:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:581:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:582:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKEdgeRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetKNodeCrossReference_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1187);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdge1199); 

                	newLeafNode(otherlv_2, grammarAccess.getKEdgeAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:599:1: (otherlv_3= 'sourcePort' ( ( ruleEString ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==24) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:599:3: otherlv_3= 'sourcePort' ( ( ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKEdge1212); 

                        	newLeafNode(otherlv_3, grammarAccess.getKEdgeAccess().getSourcePortKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:603:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:604:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:604:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:605:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getSourcePortKPortCrossReference_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1235);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:618:4: (otherlv_5= 'targetPort' ( ( ruleEString ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==25) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:618:6: otherlv_5= 'targetPort' ( ( ruleEString ) )
                    {
                    otherlv_5=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKEdge1250); 

                        	newLeafNode(otherlv_5, grammarAccess.getKEdgeAccess().getTargetPortKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:622:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:623:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:623:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:624:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetPortKPortCrossReference_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1273);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:637:4: (otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )* )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==15) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:637:6: otherlv_7= 'data' (otherlv_8= ':' )? ( (lv_data_9_0= ruleKGraphData ) ) ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )*
                    {
                    otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEdge1288); 

                        	newLeafNode(otherlv_7, grammarAccess.getKEdgeAccess().getDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:641:1: (otherlv_8= ':' )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==16) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:641:3: otherlv_8= ':'
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdge1301); 

                                	newLeafNode(otherlv_8, grammarAccess.getKEdgeAccess().getColonKeyword_5_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:645:3: ( (lv_data_9_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:646:1: (lv_data_9_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:646:1: (lv_data_9_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:647:3: lv_data_9_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKGraphDataParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKEdge1324);
                    lv_data_9_0=ruleKGraphData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_9_0, 
                            		"KGraphData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:663:2: ( (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==14||(LA35_0>=26 && LA35_0<=27)||LA35_0==30||(LA35_0>=32 && LA35_0<=36)||(LA35_0>=38 && LA35_0<=40)||LA35_0==42||LA35_0==45||LA35_0==92||LA35_0==98) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:663:3: (otherlv_10= ',' )? ( (lv_data_11_0= ruleKGraphData ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:663:3: (otherlv_10= ',' )?
                    	    int alt34=2;
                    	    int LA34_0 = input.LA(1);

                    	    if ( (LA34_0==14) ) {
                    	        alt34=1;
                    	    }
                    	    switch (alt34) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:663:5: otherlv_10= ','
                    	            {
                    	            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdge1338); 

                    	                	newLeafNode(otherlv_10, grammarAccess.getKEdgeAccess().getCommaKeyword_5_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:667:3: ( (lv_data_11_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:668:1: (lv_data_11_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:668:1: (lv_data_11_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:669:3: lv_data_11_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKGraphDataParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKEdge1361);
                    	    lv_data_11_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
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
                    	    break loop35;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:685:6: (otherlv_12= 'labels' (otherlv_13= ':' )? ( (lv_labels_14_0= ruleKLabel ) ) ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )* )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==22) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:685:8: otherlv_12= 'labels' (otherlv_13= ':' )? ( (lv_labels_14_0= ruleKLabel ) ) ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )*
                    {
                    otherlv_12=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKEdge1378); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEdgeAccess().getLabelsKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:689:1: (otherlv_13= ':' )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==16) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:689:3: otherlv_13= ':'
                            {
                            otherlv_13=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdge1391); 

                                	newLeafNode(otherlv_13, grammarAccess.getKEdgeAccess().getColonKeyword_6_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:693:3: ( (lv_labels_14_0= ruleKLabel ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:694:1: (lv_labels_14_0= ruleKLabel )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:694:1: (lv_labels_14_0= ruleKLabel )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:695:3: lv_labels_14_0= ruleKLabel
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKEdge1414);
                    lv_labels_14_0=ruleKLabel();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	        }
                           		add(
                           			current, 
                           			"labels",
                            		lv_labels_14_0, 
                            		"KLabel");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:711:2: ( (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) ) )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==14||LA39_0==19) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:711:3: (otherlv_15= ',' )? ( (lv_labels_16_0= ruleKLabel ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:711:3: (otherlv_15= ',' )?
                    	    int alt38=2;
                    	    int LA38_0 = input.LA(1);

                    	    if ( (LA38_0==14) ) {
                    	        alt38=1;
                    	    }
                    	    switch (alt38) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:711:5: otherlv_15= ','
                    	            {
                    	            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdge1428); 

                    	                	newLeafNode(otherlv_15, grammarAccess.getKEdgeAccess().getCommaKeyword_6_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:715:3: ( (lv_labels_16_0= ruleKLabel ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:716:1: (lv_labels_16_0= ruleKLabel )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:716:1: (lv_labels_16_0= ruleKLabel )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:717:3: lv_labels_16_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKEdge1451);
                    	    lv_labels_16_0=ruleKLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_16_0, 
                    	            		"KLabel");
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

            otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdge1467); 

                	newLeafNode(otherlv_17, grammarAccess.getKEdgeAccess().getRightCurlyBracketKeyword_7());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:745:1: entryRuleKGraphData returns [EObject current=null] : iv_ruleKGraphData= ruleKGraphData EOF ;
    public final EObject entryRuleKGraphData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGraphData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:746:2: (iv_ruleKGraphData= ruleKGraphData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:747:2: iv_ruleKGraphData= ruleKGraphData EOF
            {
             newCompositeNode(grammarAccess.getKGraphDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_entryRuleKGraphData1503);
            iv_ruleKGraphData=ruleKGraphData();

            state._fsp--;

             current =iv_ruleKGraphData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGraphData1513); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:754:1: ruleKGraphData returns [EObject current=null] : (this_KRendering_0= ruleKRendering | this_KRenderingLibrary_1= ruleKRenderingLibrary | this_KShapeLayout_2= ruleKShapeLayout | this_KEdgeLayout_3= ruleKEdgeLayout ) ;
    public final EObject ruleKGraphData() throws RecognitionException {
        EObject current = null;

        EObject this_KRendering_0 = null;

        EObject this_KRenderingLibrary_1 = null;

        EObject this_KShapeLayout_2 = null;

        EObject this_KEdgeLayout_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:757:28: ( (this_KRendering_0= ruleKRendering | this_KRenderingLibrary_1= ruleKRenderingLibrary | this_KShapeLayout_2= ruleKShapeLayout | this_KEdgeLayout_3= ruleKEdgeLayout ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:758:1: (this_KRendering_0= ruleKRendering | this_KRenderingLibrary_1= ruleKRenderingLibrary | this_KShapeLayout_2= ruleKShapeLayout | this_KEdgeLayout_3= ruleKEdgeLayout )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:758:1: (this_KRendering_0= ruleKRendering | this_KRenderingLibrary_1= ruleKRenderingLibrary | this_KShapeLayout_2= ruleKShapeLayout | this_KEdgeLayout_3= ruleKEdgeLayout )
            int alt41=4;
            switch ( input.LA(1) ) {
            case 27:
            case 30:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 38:
            case 39:
            case 40:
            case 42:
            case 45:
                {
                alt41=1;
                }
                break;
            case 26:
                {
                alt41=2;
                }
                break;
            case 92:
                {
                alt41=3;
                }
                break;
            case 98:
                {
                alt41=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:759:5: this_KRendering_0= ruleKRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKRenderingParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKGraphData1560);
                    this_KRendering_0=ruleKRendering();

                    state._fsp--;

                     
                            current = this_KRendering_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:769:5: this_KRenderingLibrary_1= ruleKRenderingLibrary
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKRenderingLibraryParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRenderingLibrary_in_ruleKGraphData1587);
                    this_KRenderingLibrary_1=ruleKRenderingLibrary();

                    state._fsp--;

                     
                            current = this_KRenderingLibrary_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:779:5: this_KShapeLayout_2= ruleKShapeLayout
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKShapeLayoutParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_ruleKGraphData1614);
                    this_KShapeLayout_2=ruleKShapeLayout();

                    state._fsp--;

                     
                            current = this_KShapeLayout_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:789:5: this_KEdgeLayout_3= ruleKEdgeLayout
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKEdgeLayoutParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEdgeLayout_in_ruleKGraphData1641);
                    this_KEdgeLayout_3=ruleKEdgeLayout();

                    state._fsp--;

                     
                            current = this_KEdgeLayout_3; 
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


    // $ANTLR start "entryRuleKRenderingLibrary"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:805:1: entryRuleKRenderingLibrary returns [EObject current=null] : iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF ;
    public final EObject entryRuleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingLibrary = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:806:2: (iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:807:2: iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF
            {
             newCompositeNode(grammarAccess.getKRenderingLibraryRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingLibrary_in_entryRuleKRenderingLibrary1676);
            iv_ruleKRenderingLibrary=ruleKRenderingLibrary();

            state._fsp--;

             current =iv_ruleKRenderingLibrary; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingLibrary1686); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:814:1: ruleKRenderingLibrary returns [EObject current=null] : ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) ;
    public final EObject ruleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_renderings_3_0 = null;

        EObject lv_renderings_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:817:28: ( ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:818:1: ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:818:1: ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:818:2: () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:818:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:819:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingLibraryAccess().getKRenderingLibraryAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKRenderingLibrary1732); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingLibraryAccess().getRenderingLibraryKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingLibrary1744); 

                	newLeafNode(otherlv_2, grammarAccess.getKRenderingLibraryAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:832:1: ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==27||LA44_0==30||(LA44_0>=32 && LA44_0<=36)||(LA44_0>=38 && LA44_0<=40)||LA44_0==42||LA44_0==45) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:832:2: ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )*
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:832:2: ( (lv_renderings_3_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:833:1: (lv_renderings_3_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:833:1: (lv_renderings_3_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:834:3: lv_renderings_3_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRenderingLibrary1766);
                    lv_renderings_3_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRenderingLibraryRule());
                    	        }
                           		add(
                           			current, 
                           			"renderings",
                            		lv_renderings_3_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:850:2: ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==14||LA43_0==27||LA43_0==30||(LA43_0>=32 && LA43_0<=36)||(LA43_0>=38 && LA43_0<=40)||LA43_0==42||LA43_0==45) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:850:3: (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:850:3: (otherlv_4= ',' )?
                    	    int alt42=2;
                    	    int LA42_0 = input.LA(1);

                    	    if ( (LA42_0==14) ) {
                    	        alt42=1;
                    	    }
                    	    switch (alt42) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:850:5: otherlv_4= ','
                    	            {
                    	            otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingLibrary1780); 

                    	                	newLeafNode(otherlv_4, grammarAccess.getKRenderingLibraryAccess().getCommaKeyword_3_1_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:854:3: ( (lv_renderings_5_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:855:1: (lv_renderings_5_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:855:1: (lv_renderings_5_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:856:3: lv_renderings_5_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRenderingLibrary1803);
                    	    lv_renderings_5_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRenderingLibraryRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"renderings",
                    	            		lv_renderings_5_0, 
                    	            		"KRendering");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingLibrary1819); 

                	newLeafNode(otherlv_6, grammarAccess.getKRenderingLibraryAccess().getRightCurlyBracketKeyword_4());
                

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


    // $ANTLR start "entryRuleKRendering"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:884:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:885:2: (iv_ruleKRendering= ruleKRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:886:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_entryRuleKRendering1855);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRendering1865); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:893:1: ruleKRendering returns [EObject current=null] : (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:896:28: ( (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:897:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:897:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            int alt45=12;
            switch ( input.LA(1) ) {
            case 30:
                {
                alt45=1;
                }
                break;
            case 32:
                {
                alt45=2;
                }
                break;
            case 33:
                {
                alt45=3;
                }
                break;
            case 34:
                {
                alt45=4;
                }
                break;
            case 35:
                {
                alt45=5;
                }
                break;
            case 36:
                {
                alt45=6;
                }
                break;
            case 38:
                {
                alt45=7;
                }
                break;
            case 27:
                {
                alt45=8;
                }
                break;
            case 39:
                {
                alt45=9;
                }
                break;
            case 40:
                {
                alt45=10;
                }
                break;
            case 42:
                {
                alt45=11;
                }
                break;
            case 45:
                {
                alt45=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }

            switch (alt45) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:898:5: this_KEllipse_0= ruleKEllipse
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKEllipseParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_ruleKRendering1912);
                    this_KEllipse_0=ruleKEllipse();

                    state._fsp--;

                     
                            current = this_KEllipse_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:908:5: this_KRectangle_1= ruleKRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRectangleParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_ruleKRendering1939);
                    this_KRectangle_1=ruleKRectangle();

                    state._fsp--;

                     
                            current = this_KRectangle_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:918:5: this_KRoundedRectangle_2= ruleKRoundedRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRoundedRectangleParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_ruleKRendering1966);
                    this_KRoundedRectangle_2=ruleKRoundedRectangle();

                    state._fsp--;

                     
                            current = this_KRoundedRectangle_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:928:5: this_KPolyline_Impl_3= ruleKPolyline_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolyline_ImplParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_ruleKRendering1993);
                    this_KPolyline_Impl_3=ruleKPolyline_Impl();

                    state._fsp--;

                     
                            current = this_KPolyline_Impl_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:938:5: this_KPolygon_4= ruleKPolygon
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolygonParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_ruleKRendering2020);
                    this_KPolygon_4=ruleKPolygon();

                    state._fsp--;

                     
                            current = this_KPolygon_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:948:5: this_KImage_5= ruleKImage
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKImageParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKImage_in_ruleKRendering2047);
                    this_KImage_5=ruleKImage();

                    state._fsp--;

                     
                            current = this_KImage_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:958:5: this_KArc_6= ruleKArc
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKArcParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKArc_in_ruleKRendering2074);
                    this_KArc_6=ruleKArc();

                    state._fsp--;

                     
                            current = this_KArc_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:968:5: this_KRenderingRef_7= ruleKRenderingRef
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRenderingRefParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_ruleKRendering2101);
                    this_KRenderingRef_7=ruleKRenderingRef();

                    state._fsp--;

                     
                            current = this_KRenderingRef_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:978:5: this_KChildArea_8= ruleKChildArea
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKChildAreaParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_ruleKRendering2128);
                    this_KChildArea_8=ruleKChildArea();

                    state._fsp--;

                     
                            current = this_KChildArea_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:988:5: this_KText_9= ruleKText
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKTextParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKText_in_ruleKRendering2155);
                    this_KText_9=ruleKText();

                    state._fsp--;

                     
                            current = this_KText_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:998:5: this_KCustomRendering_10= ruleKCustomRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKCustomRenderingParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_ruleKRendering2182);
                    this_KCustomRendering_10=ruleKCustomRendering();

                    state._fsp--;

                     
                            current = this_KCustomRendering_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1008:5: this_KSpline_11= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSplineParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_ruleKRendering2209);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1024:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1025:2: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1026:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData2244);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacementData2254); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1033:1: ruleKPlacementData returns [EObject current=null] : (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KDecoratorPlacementData_0 = null;

        EObject this_KGridPlacementData_1 = null;

        EObject this_KStackPlacementData_2 = null;

        EObject this_KDirectPlacementData_3 = null;

        EObject this_KPolylinePlacementData_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1036:28: ( (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1037:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1037:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            int alt46=5;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt46=1;
                }
                break;
            case 53:
                {
                alt46=2;
                }
                break;
            case 60:
                {
                alt46=3;
                }
                break;
            case 61:
                {
                alt46=4;
                }
                break;
            case 64:
                {
                alt46=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1038:5: this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData2301);
                    this_KDecoratorPlacementData_0=ruleKDecoratorPlacementData();

                    state._fsp--;

                     
                            current = this_KDecoratorPlacementData_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1048:5: this_KGridPlacementData_1= ruleKGridPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData2328);
                    this_KGridPlacementData_1=ruleKGridPlacementData();

                    state._fsp--;

                     
                            current = this_KGridPlacementData_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1058:5: this_KStackPlacementData_2= ruleKStackPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKStackPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData2355);
                    this_KStackPlacementData_2=ruleKStackPlacementData();

                    state._fsp--;

                     
                            current = this_KStackPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1068:5: this_KDirectPlacementData_3= ruleKDirectPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDirectPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData2382);
                    this_KDirectPlacementData_3=ruleKDirectPlacementData();

                    state._fsp--;

                     
                            current = this_KDirectPlacementData_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1078:5: this_KPolylinePlacementData_4= ruleKPolylinePlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPolylinePlacementDataParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData2409);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1094:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1095:2: (iv_ruleKStyle= ruleKStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1096:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_entryRuleKStyle2444);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle2454); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1103:1: ruleKStyle returns [EObject current=null] : (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment ) ;
    public final EObject ruleKStyle() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundColor_0 = null;

        EObject this_KBackgroundColor_1 = null;

        EObject this_KLineWidth_2 = null;

        EObject this_KVisibility_3 = null;

        EObject this_KLineStyle_4 = null;

        EObject this_KFontBold_5 = null;

        EObject this_KFontItalic_6 = null;

        EObject this_KFontName_7 = null;

        EObject this_KFontSize_8 = null;

        EObject this_KVerticalAlignment_9 = null;

        EObject this_KHorizontalAlignment_10 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1106:28: ( (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1107:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1107:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment )
            int alt47=11;
            switch ( input.LA(1) ) {
            case 72:
                {
                alt47=1;
                }
                break;
            case 74:
                {
                alt47=2;
                }
                break;
            case 75:
                {
                alt47=3;
                }
                break;
            case 76:
            case 77:
                {
                alt47=4;
                }
                break;
            case 78:
                {
                alt47=5;
                }
                break;
            case 79:
                {
                alt47=6;
                }
                break;
            case 80:
                {
                alt47=7;
                }
                break;
            case 81:
                {
                alt47=8;
                }
                break;
            case 82:
                {
                alt47=9;
                }
                break;
            case 83:
                {
                alt47=10;
                }
                break;
            case 84:
                {
                alt47=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1108:5: this_KForegroundColor_0= ruleKForegroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundColorParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_ruleKStyle2501);
                    this_KForegroundColor_0=ruleKForegroundColor();

                    state._fsp--;

                     
                            current = this_KForegroundColor_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1118:5: this_KBackgroundColor_1= ruleKBackgroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKBackgroundColorParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_ruleKStyle2528);
                    this_KBackgroundColor_1=ruleKBackgroundColor();

                    state._fsp--;

                     
                            current = this_KBackgroundColor_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1128:5: this_KLineWidth_2= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle2555);
                    this_KLineWidth_2=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1138:5: this_KVisibility_3= ruleKVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVisibilityParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_ruleKStyle2582);
                    this_KVisibility_3=ruleKVisibility();

                    state._fsp--;

                     
                            current = this_KVisibility_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1148:5: this_KLineStyle_4= ruleKLineStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_ruleKStyle2609);
                    this_KLineStyle_4=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1158:5: this_KFontBold_5= ruleKFontBold
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontBoldParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_ruleKStyle2636);
                    this_KFontBold_5=ruleKFontBold();

                    state._fsp--;

                     
                            current = this_KFontBold_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1168:5: this_KFontItalic_6= ruleKFontItalic
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontItalicParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_ruleKStyle2663);
                    this_KFontItalic_6=ruleKFontItalic();

                    state._fsp--;

                     
                            current = this_KFontItalic_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1178:5: this_KFontName_7= ruleKFontName
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontNameParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_ruleKStyle2690);
                    this_KFontName_7=ruleKFontName();

                    state._fsp--;

                     
                            current = this_KFontName_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1188:5: this_KFontSize_8= ruleKFontSize
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontSizeParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_ruleKStyle2717);
                    this_KFontSize_8=ruleKFontSize();

                    state._fsp--;

                     
                            current = this_KFontSize_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1198:5: this_KVerticalAlignment_9= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle2744);
                    this_KVerticalAlignment_9=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1208:5: this_KHorizontalAlignment_10= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle2771);
                    this_KHorizontalAlignment_10=ruleKHorizontalAlignment();

                    state._fsp--;

                     
                            current = this_KHorizontalAlignment_10; 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1224:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1225:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1226:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_entryRuleKPlacement2806);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacement2816); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1233:1: ruleKPlacement returns [EObject current=null] : (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;

        EObject this_KStackPlacement_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1236:28: ( (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1237:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1237:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==85) ) {
                alt48=1;
            }
            else if ( (LA48_0==86) ) {
                alt48=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1238:5: this_KGridPlacement_0= ruleKGridPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement2863);
                    this_KGridPlacement_0=ruleKGridPlacement();

                    state._fsp--;

                     
                            current = this_KGridPlacement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1248:5: this_KStackPlacement_1= ruleKStackPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKStackPlacementParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_ruleKPlacement2890);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1264:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1265:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1266:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition2925);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition2935); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1273:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1276:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1277:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1277:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==68) ) {
                alt49=1;
            }
            else if ( (LA49_0==69) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1278:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition2982);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1288:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition3009);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1304:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1305:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1306:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition3044);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition3054); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1313:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1316:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1317:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1317:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==70) ) {
                alt50=1;
            }
            else if ( (LA50_0==71) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1318:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition3101);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1328:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition3128);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1344:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1345:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1346:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef3163);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef3173); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1353:1: ruleKRenderingRef returns [EObject current=null] : ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? ) ;
    public final EObject ruleKRenderingRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        EObject lv_placementData_5_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_styles_10_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1356:28: ( ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1357:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1357:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1357:2: () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1357:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1358:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingRefAccess().getKRenderingRefAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKRenderingRef3219); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getRenderingRefKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1367:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1368:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1368:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1369:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef3242);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1382:2: (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==12) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1382:4: otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef3255); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1386:1: (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==28) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1386:3: otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) )
                            {
                            otherlv_4=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKRenderingRef3268); 

                                	newLeafNode(otherlv_4, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1390:1: ( (lv_placementData_5_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1391:1: (lv_placementData_5_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1391:1: (lv_placementData_5_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1392:3: lv_placementData_5_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef3289);
                            lv_placementData_5_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRenderingRefRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"placementData",
                                    		lv_placementData_5_0, 
                                    		"KPlacementData");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1408:4: (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==29) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1408:6: otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}'
                            {
                            otherlv_6=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKRenderingRef3304); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRenderingRefAccess().getStylesKeyword_3_2_0());
                                
                            otherlv_7=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef3316); 

                                	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_2_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1416:1: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1417:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1417:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1418:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef3337);
                            lv_styles_8_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRenderingRefRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_8_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1434:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop53:
                            do {
                                int alt53=2;
                                int LA53_0 = input.LA(1);

                                if ( (LA53_0==14||LA53_0==72||(LA53_0>=74 && LA53_0<=84)) ) {
                                    alt53=1;
                                }


                                switch (alt53) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1434:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1434:3: (otherlv_9= ',' )?
                            	    int alt52=2;
                            	    int LA52_0 = input.LA(1);

                            	    if ( (LA52_0==14) ) {
                            	        alt52=1;
                            	    }
                            	    switch (alt52) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1434:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef3351); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKRenderingRefAccess().getCommaKeyword_3_2_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1438:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1439:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1439:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1440:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_2_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef3374);
                            	    lv_styles_10_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKRenderingRefRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_10_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop53;
                                }
                            } while (true);

                            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef3388); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_3_2_4());
                                

                            }
                            break;

                    }

                    otherlv_12=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef3402); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_3_3());
                        

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
    // $ANTLR end "ruleKRenderingRef"


    // $ANTLR start "entryRuleKEllipse"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1472:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1473:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1474:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse3440);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse3450); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1481:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1484:28: ( ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1485:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1485:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1485:2: () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1485:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1486:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKEllipse3496); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getEllipseKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1495:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==12) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1495:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse3509); 

                        	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1499:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==29) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1499:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKEllipse3522); 

                                	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1503:1: (otherlv_4= ':' )?
                            int alt56=2;
                            int LA56_0 = input.LA(1);

                            if ( (LA56_0==16) ) {
                                alt56=1;
                            }
                            switch (alt56) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1503:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3535); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1507:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1508:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1508:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1509:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse3558);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1525:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop58:
                            do {
                                int alt58=2;
                                int LA58_0 = input.LA(1);

                                if ( (LA58_0==14||LA58_0==72||(LA58_0>=74 && LA58_0<=84)) ) {
                                    alt58=1;
                                }


                                switch (alt58) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1525:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1525:3: (otherlv_6= ',' )?
                            	    int alt57=2;
                            	    int LA57_0 = input.LA(1);

                            	    if ( (LA57_0==14) ) {
                            	        alt57=1;
                            	    }
                            	    switch (alt57) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1525:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse3572); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1529:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1530:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1530:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1531:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse3595);
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
                            	    break loop58;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1547:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==28) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1547:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKEllipse3612); 

                                	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1551:1: (otherlv_9= ':' )?
                            int alt60=2;
                            int LA60_0 = input.LA(1);

                            if ( (LA60_0==16) ) {
                                alt60=1;
                            }
                            switch (alt60) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1551:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3625); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1555:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1556:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1556:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1557:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse3648);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1573:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==31) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1573:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKEllipse3663); 

                                	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1577:1: (otherlv_12= ':' )?
                            int alt62=2;
                            int LA62_0 = input.LA(1);

                            if ( (LA62_0==16) ) {
                                alt62=1;
                            }
                            switch (alt62) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1577:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3676); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1581:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1582:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1582:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1583:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse3699);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1599:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==17) ) {
                        alt67=1;
                    }
                    switch (alt67) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1599:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse3714); 

                                	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1603:1: (otherlv_15= ':' )?
                            int alt64=2;
                            int LA64_0 = input.LA(1);

                            if ( (LA64_0==16) ) {
                                alt64=1;
                            }
                            switch (alt64) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1603:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3727); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKEllipseAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1607:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1608:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1608:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1609:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse3750);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1625:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop66:
                            do {
                                int alt66=2;
                                int LA66_0 = input.LA(1);

                                if ( (LA66_0==14||LA66_0==27||LA66_0==30||(LA66_0>=32 && LA66_0<=36)||(LA66_0>=38 && LA66_0<=40)||LA66_0==42||LA66_0==45) ) {
                                    alt66=1;
                                }


                                switch (alt66) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1625:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1625:3: (otherlv_17= ',' )?
                            	    int alt65=2;
                            	    int LA65_0 = input.LA(1);

                            	    if ( (LA65_0==14) ) {
                            	        alt65=1;
                            	    }
                            	    switch (alt65) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1625:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse3764); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1629:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1630:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1630:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1631:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse3787);
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
                            	    break loop66;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse3803); 

                        	newLeafNode(otherlv_19, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_2_5());
                        

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
    // $ANTLR end "ruleKEllipse"


    // $ANTLR start "entryRuleKRectangle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1659:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1660:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1661:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle3841);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle3851); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1668:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1671:28: ( ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1672:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1672:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1672:2: () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1672:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1673:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKRectangle3897); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1682:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==12) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1682:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle3910); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1686:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==29) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1686:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKRectangle3923); 

                                	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1690:1: (otherlv_4= ':' )?
                            int alt69=2;
                            int LA69_0 = input.LA(1);

                            if ( (LA69_0==16) ) {
                                alt69=1;
                            }
                            switch (alt69) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1690:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle3936); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1694:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1695:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1695:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1696:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle3959);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1712:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop71:
                            do {
                                int alt71=2;
                                int LA71_0 = input.LA(1);

                                if ( (LA71_0==14||LA71_0==72||(LA71_0>=74 && LA71_0<=84)) ) {
                                    alt71=1;
                                }


                                switch (alt71) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1712:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1712:3: (otherlv_6= ',' )?
                            	    int alt70=2;
                            	    int LA70_0 = input.LA(1);

                            	    if ( (LA70_0==14) ) {
                            	        alt70=1;
                            	    }
                            	    switch (alt70) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1712:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle3973); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1716:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1717:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1717:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1718:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle3996);
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
                            	    break loop71;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1734:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==28) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1734:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKRectangle4013); 

                                	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1738:1: (otherlv_9= ':' )?
                            int alt73=2;
                            int LA73_0 = input.LA(1);

                            if ( (LA73_0==16) ) {
                                alt73=1;
                            }
                            switch (alt73) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1738:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle4026); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1742:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1743:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1743:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1744:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle4049);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1760:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==31) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1760:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKRectangle4064); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1764:1: (otherlv_12= ':' )?
                            int alt75=2;
                            int LA75_0 = input.LA(1);

                            if ( (LA75_0==16) ) {
                                alt75=1;
                            }
                            switch (alt75) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1764:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle4077); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1768:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1769:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1769:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1770:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle4100);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1786:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==17) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1786:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle4115); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1790:1: (otherlv_15= ':' )?
                            int alt77=2;
                            int LA77_0 = input.LA(1);

                            if ( (LA77_0==16) ) {
                                alt77=1;
                            }
                            switch (alt77) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1790:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle4128); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRectangleAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1794:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1795:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1795:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1796:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle4151);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1812:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop79:
                            do {
                                int alt79=2;
                                int LA79_0 = input.LA(1);

                                if ( (LA79_0==14||LA79_0==27||LA79_0==30||(LA79_0>=32 && LA79_0<=36)||(LA79_0>=38 && LA79_0<=40)||LA79_0==42||LA79_0==45) ) {
                                    alt79=1;
                                }


                                switch (alt79) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1812:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1812:3: (otherlv_17= ',' )?
                            	    int alt78=2;
                            	    int LA78_0 = input.LA(1);

                            	    if ( (LA78_0==14) ) {
                            	        alt78=1;
                            	    }
                            	    switch (alt78) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1812:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle4165); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1816:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1817:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1817:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1818:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle4188);
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
                            	    break loop79;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle4204); 

                        	newLeafNode(otherlv_19, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_2_5());
                        

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
    // $ANTLR end "ruleKRectangle"


    // $ANTLR start "entryRuleKRoundedRectangle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1846:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1847:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1848:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle4242);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle4252); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1855:1: ruleKRoundedRectangle returns [EObject current=null] : ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
    public final EObject ruleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        AntlrDatatypeRuleToken lv_cornerWidth_2_0 = null;

        AntlrDatatypeRuleToken lv_cornerHeight_4_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_styles_10_0 = null;

        EObject lv_placementData_13_0 = null;

        EObject lv_childPlacement_16_0 = null;

        EObject lv_children_19_0 = null;

        EObject lv_children_21_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1858:28: ( ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1859:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1859:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1859:2: () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1859:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1860:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKRoundedRectangle4298); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getRoundedRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1869:1: ( (lv_cornerWidth_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1870:1: (lv_cornerWidth_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1870:1: (lv_cornerWidth_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1871:3: lv_cornerWidth_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4319);
            lv_cornerWidth_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
            	        }
                   		set(
                   			current, 
                   			"cornerWidth",
                    		lv_cornerWidth_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1887:2: (otherlv_3= ',' )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==14) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1887:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle4332); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1891:3: ( (lv_cornerHeight_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1892:1: (lv_cornerHeight_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1892:1: (lv_cornerHeight_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1893:3: lv_cornerHeight_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4355);
            lv_cornerHeight_4_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
            	        }
                   		set(
                   			current, 
                   			"cornerHeight",
                    		lv_cornerHeight_4_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1909:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==12) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1909:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle4368); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1913:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==29) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1913:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKRoundedRectangle4381); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1917:1: (otherlv_7= ':' )?
                            int alt83=2;
                            int LA83_0 = input.LA(1);

                            if ( (LA83_0==16) ) {
                                alt83=1;
                            }
                            switch (alt83) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1917:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4394); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1921:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1922:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1922:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1923:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4417);
                            lv_styles_8_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_8_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1939:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop85:
                            do {
                                int alt85=2;
                                int LA85_0 = input.LA(1);

                                if ( (LA85_0==14||LA85_0==72||(LA85_0>=74 && LA85_0<=84)) ) {
                                    alt85=1;
                                }


                                switch (alt85) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1939:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1939:3: (otherlv_9= ',' )?
                            	    int alt84=2;
                            	    int LA84_0 = input.LA(1);

                            	    if ( (LA84_0==14) ) {
                            	        alt84=1;
                            	    }
                            	    switch (alt84) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1939:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle4431); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1943:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1944:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1944:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1945:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4454);
                            	    lv_styles_10_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_10_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop85;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1961:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==28) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1961:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKRoundedRectangle4471); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1965:1: (otherlv_12= ':' )?
                            int alt87=2;
                            int LA87_0 = input.LA(1);

                            if ( (LA87_0==16) ) {
                                alt87=1;
                            }
                            switch (alt87) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1965:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4484); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1969:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1970:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1970:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1971:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle4507);
                            lv_placementData_13_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1987:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==31) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1987:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKRoundedRectangle4522); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_5_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1991:1: (otherlv_15= ':' )?
                            int alt89=2;
                            int LA89_0 = input.LA(1);

                            if ( (LA89_0==16) ) {
                                alt89=1;
                            }
                            switch (alt89) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1991:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4535); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1995:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1996:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1996:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1997:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle4558);
                            lv_childPlacement_16_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"childPlacement",
                                    		lv_childPlacement_16_0, 
                                    		"KPlacement");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2013:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==17) ) {
                        alt94=1;
                    }
                    switch (alt94) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2013:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle4573); 

                                	newLeafNode(otherlv_17, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2017:1: (otherlv_18= ':' )?
                            int alt91=2;
                            int LA91_0 = input.LA(1);

                            if ( (LA91_0==16) ) {
                                alt91=1;
                            }
                            switch (alt91) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2017:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4586); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2021:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2022:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2022:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2023:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4609);
                            lv_children_19_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_19_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2039:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop93:
                            do {
                                int alt93=2;
                                int LA93_0 = input.LA(1);

                                if ( (LA93_0==14||LA93_0==27||LA93_0==30||(LA93_0>=32 && LA93_0<=36)||(LA93_0>=38 && LA93_0<=40)||LA93_0==42||LA93_0==45) ) {
                                    alt93=1;
                                }


                                switch (alt93) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2039:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2039:3: (otherlv_20= ',' )?
                            	    int alt92=2;
                            	    int LA92_0 = input.LA(1);

                            	    if ( (LA92_0==14) ) {
                            	        alt92=1;
                            	    }
                            	    switch (alt92) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2039:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle4623); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2043:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2044:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2044:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2045:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4646);
                            	    lv_children_21_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
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
                            	    break loop93;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle4662); 

                        	newLeafNode(otherlv_22, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_5_5());
                        

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


    // $ANTLR start "entryRuleKPolyline_Impl"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2073:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2074:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2075:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl4700);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl4710); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2082:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2085:28: ( ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2086:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2086:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2086:2: () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2086:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2087:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKPolyline_Impl4756); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2096:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==12) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2096:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl4769); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2100:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==29) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2100:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKPolyline_Impl4782); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2104:1: (otherlv_4= ':' )?
                            int alt96=2;
                            int LA96_0 = input.LA(1);

                            if ( (LA96_0==16) ) {
                                alt96=1;
                            }
                            switch (alt96) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2104:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4795); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2108:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2109:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2109:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2110:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4818);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2126:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop98:
                            do {
                                int alt98=2;
                                int LA98_0 = input.LA(1);

                                if ( (LA98_0==14||LA98_0==72||(LA98_0>=74 && LA98_0<=84)) ) {
                                    alt98=1;
                                }


                                switch (alt98) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2126:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2126:3: (otherlv_6= ',' )?
                            	    int alt97=2;
                            	    int LA97_0 = input.LA(1);

                            	    if ( (LA97_0==14) ) {
                            	        alt97=1;
                            	    }
                            	    switch (alt97) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2126:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl4832); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2130:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2131:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2131:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2132:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4855);
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
                            	    break loop98;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2148:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt101=2;
                    int LA101_0 = input.LA(1);

                    if ( (LA101_0==28) ) {
                        alt101=1;
                    }
                    switch (alt101) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2148:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKPolyline_Impl4872); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2152:1: (otherlv_9= ':' )?
                            int alt100=2;
                            int LA100_0 = input.LA(1);

                            if ( (LA100_0==16) ) {
                                alt100=1;
                            }
                            switch (alt100) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2152:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4885); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2156:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2157:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2157:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2158:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl4908);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2174:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt103=2;
                    int LA103_0 = input.LA(1);

                    if ( (LA103_0==31) ) {
                        alt103=1;
                    }
                    switch (alt103) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2174:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKPolyline_Impl4923); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2178:1: (otherlv_12= ':' )?
                            int alt102=2;
                            int LA102_0 = input.LA(1);

                            if ( (LA102_0==16) ) {
                                alt102=1;
                            }
                            switch (alt102) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2178:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4936); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2182:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2183:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2183:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2184:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl4959);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2200:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==17) ) {
                        alt107=1;
                    }
                    switch (alt107) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2200:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl4974); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2204:1: (otherlv_15= ':' )?
                            int alt104=2;
                            int LA104_0 = input.LA(1);

                            if ( (LA104_0==16) ) {
                                alt104=1;
                            }
                            switch (alt104) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2204:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl4987); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2208:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2209:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2209:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2210:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5010);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2226:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop106:
                            do {
                                int alt106=2;
                                int LA106_0 = input.LA(1);

                                if ( (LA106_0==14||LA106_0==27||LA106_0==30||(LA106_0>=32 && LA106_0<=36)||(LA106_0>=38 && LA106_0<=40)||LA106_0==42||LA106_0==45) ) {
                                    alt106=1;
                                }


                                switch (alt106) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2226:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2226:3: (otherlv_17= ',' )?
                            	    int alt105=2;
                            	    int LA105_0 = input.LA(1);

                            	    if ( (LA105_0==14) ) {
                            	        alt105=1;
                            	    }
                            	    switch (alt105) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2226:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl5024); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2230:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2231:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2231:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2232:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5047);
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
                            	    break loop106;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl5063); 

                        	newLeafNode(otherlv_19, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_2_5());
                        

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
    // $ANTLR end "ruleKPolyline_Impl"


    // $ANTLR start "entryRuleKPolygon"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2260:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2261:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2262:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon5101);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon5111); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2269:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2272:28: ( ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2273:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2273:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2273:2: () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2273:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2274:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKPolygon5157); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getPolygonKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2283:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==12) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2283:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon5170); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2287:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt112=2;
                    int LA112_0 = input.LA(1);

                    if ( (LA112_0==29) ) {
                        alt112=1;
                    }
                    switch (alt112) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2287:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKPolygon5183); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2291:1: (otherlv_4= ':' )?
                            int alt109=2;
                            int LA109_0 = input.LA(1);

                            if ( (LA109_0==16) ) {
                                alt109=1;
                            }
                            switch (alt109) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2291:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5196); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2295:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2296:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2296:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2297:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon5219);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2313:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop111:
                            do {
                                int alt111=2;
                                int LA111_0 = input.LA(1);

                                if ( (LA111_0==14||LA111_0==72||(LA111_0>=74 && LA111_0<=84)) ) {
                                    alt111=1;
                                }


                                switch (alt111) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2313:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2313:3: (otherlv_6= ',' )?
                            	    int alt110=2;
                            	    int LA110_0 = input.LA(1);

                            	    if ( (LA110_0==14) ) {
                            	        alt110=1;
                            	    }
                            	    switch (alt110) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2313:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon5233); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2317:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2318:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2318:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2319:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon5256);
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
                            	    break loop111;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2335:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt114=2;
                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==28) ) {
                        alt114=1;
                    }
                    switch (alt114) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2335:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKPolygon5273); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2339:1: (otherlv_9= ':' )?
                            int alt113=2;
                            int LA113_0 = input.LA(1);

                            if ( (LA113_0==16) ) {
                                alt113=1;
                            }
                            switch (alt113) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2339:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5286); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2343:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2344:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2344:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2345:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon5309);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2361:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt116=2;
                    int LA116_0 = input.LA(1);

                    if ( (LA116_0==31) ) {
                        alt116=1;
                    }
                    switch (alt116) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2361:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKPolygon5324); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2365:1: (otherlv_12= ':' )?
                            int alt115=2;
                            int LA115_0 = input.LA(1);

                            if ( (LA115_0==16) ) {
                                alt115=1;
                            }
                            switch (alt115) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2365:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5337); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2369:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2370:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2370:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2371:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon5360);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2387:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==17) ) {
                        alt120=1;
                    }
                    switch (alt120) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2387:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon5375); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2391:1: (otherlv_15= ':' )?
                            int alt117=2;
                            int LA117_0 = input.LA(1);

                            if ( (LA117_0==16) ) {
                                alt117=1;
                            }
                            switch (alt117) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2391:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5388); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKPolygonAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2395:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2396:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2396:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2397:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon5411);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2413:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop119:
                            do {
                                int alt119=2;
                                int LA119_0 = input.LA(1);

                                if ( (LA119_0==14||LA119_0==27||LA119_0==30||(LA119_0>=32 && LA119_0<=36)||(LA119_0>=38 && LA119_0<=40)||LA119_0==42||LA119_0==45) ) {
                                    alt119=1;
                                }


                                switch (alt119) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2413:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2413:3: (otherlv_17= ',' )?
                            	    int alt118=2;
                            	    int LA118_0 = input.LA(1);

                            	    if ( (LA118_0==14) ) {
                            	        alt118=1;
                            	    }
                            	    switch (alt118) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2413:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon5425); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2417:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2418:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2418:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2419:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon5448);
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
                            	    break loop119;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon5464); 

                        	newLeafNode(otherlv_19, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_2_5());
                        

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
    // $ANTLR end "ruleKPolygon"


    // $ANTLR start "entryRuleKImage"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2447:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2448:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2449:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage5502);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage5512); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2456:1: ruleKImage returns [EObject current=null] : ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) ;
    public final EObject ruleKImage() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        AntlrDatatypeRuleToken lv_bundleName_2_0 = null;

        AntlrDatatypeRuleToken lv_imagePath_4_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_styles_10_0 = null;

        EObject lv_placementData_12_0 = null;

        EObject lv_children_15_0 = null;

        EObject lv_children_17_0 = null;

        EObject lv_childPlacement_20_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2459:28: ( ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2460:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2460:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2460:2: () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2460:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2461:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKImageAccess().getKImageAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKImage5558); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getImageKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2470:1: ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' )
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( ((LA122_0>=RULE_STRING && LA122_0<=RULE_ID)) ) {
                alt122=1;
            }
            else if ( (LA122_0==37) ) {
                alt122=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }
            switch (alt122) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2470:2: ( (lv_bundleName_2_0= ruleEString ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2470:2: ( (lv_bundleName_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2471:1: (lv_bundleName_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2471:1: (lv_bundleName_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2472:3: lv_bundleName_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage5580);
                    lv_bundleName_2_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                    	        }
                           		set(
                           			current, 
                           			"bundleName",
                            		lv_bundleName_2_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2489:7: otherlv_3= '-'
                    {
                    otherlv_3=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKImage5598); 

                        	newLeafNode(otherlv_3, grammarAccess.getKImageAccess().getHyphenMinusKeyword_2_1());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2493:2: ( (lv_imagePath_4_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2494:1: (lv_imagePath_4_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2494:1: (lv_imagePath_4_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2495:3: lv_imagePath_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage5620);
            lv_imagePath_4_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKImageRule());
            	        }
                   		set(
                   			current, 
                   			"imagePath",
                    		lv_imagePath_4_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2511:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==12) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2511:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage5633); 

                        	newLeafNode(otherlv_5, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2515:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==29) ) {
                        alt126=1;
                    }
                    switch (alt126) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2515:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKImage5646); 

                                	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getStylesKeyword_4_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2519:1: (otherlv_7= ':' )?
                            int alt123=2;
                            int LA123_0 = input.LA(1);

                            if ( (LA123_0==16) ) {
                                alt123=1;
                            }
                            switch (alt123) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2519:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKImage5659); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getColonKeyword_4_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2523:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2524:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2524:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2525:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage5682);
                            lv_styles_8_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKImageRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_8_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2541:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop125:
                            do {
                                int alt125=2;
                                int LA125_0 = input.LA(1);

                                if ( (LA125_0==14||LA125_0==72||(LA125_0>=74 && LA125_0<=84)) ) {
                                    alt125=1;
                                }


                                switch (alt125) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2541:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2541:3: (otherlv_9= ',' )?
                            	    int alt124=2;
                            	    int LA124_0 = input.LA(1);

                            	    if ( (LA124_0==14) ) {
                            	        alt124=1;
                            	    }
                            	    switch (alt124) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2541:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage5696); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_4_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2545:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2546:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2546:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2547:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage5719);
                            	    lv_styles_10_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_10_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop125;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2563:6: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
                    int alt127=2;
                    int LA127_0 = input.LA(1);

                    if ( (LA127_0==28) ) {
                        alt127=1;
                    }
                    switch (alt127) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2563:8: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKImage5736); 

                                	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getPlacementDataKeyword_4_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2567:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2568:1: (lv_placementData_12_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2568:1: (lv_placementData_12_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2569:3: lv_placementData_12_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage5757);
                            lv_placementData_12_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKImageRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2585:4: (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )?
                    int alt130=2;
                    int LA130_0 = input.LA(1);

                    if ( (LA130_0==17) ) {
                        alt130=1;
                    }
                    switch (alt130) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2585:6: otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}'
                            {
                            otherlv_13=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage5772); 

                                	newLeafNode(otherlv_13, grammarAccess.getKImageAccess().getChildrenKeyword_4_3_0());
                                
                            otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage5784); 

                                	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2593:1: ( (lv_children_15_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2594:1: (lv_children_15_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2594:1: (lv_children_15_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2595:3: lv_children_15_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage5805);
                            lv_children_15_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKImageRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_15_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2611:2: ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )*
                            loop129:
                            do {
                                int alt129=2;
                                int LA129_0 = input.LA(1);

                                if ( (LA129_0==14||LA129_0==27||LA129_0==30||(LA129_0>=32 && LA129_0<=36)||(LA129_0>=38 && LA129_0<=40)||LA129_0==42||LA129_0==45) ) {
                                    alt129=1;
                                }


                                switch (alt129) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2611:3: (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2611:3: (otherlv_16= ',' )?
                            	    int alt128=2;
                            	    int LA128_0 = input.LA(1);

                            	    if ( (LA128_0==14) ) {
                            	        alt128=1;
                            	    }
                            	    switch (alt128) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2611:5: otherlv_16= ','
                            	            {
                            	            otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage5819); 

                            	                	newLeafNode(otherlv_16, grammarAccess.getKImageAccess().getCommaKeyword_4_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2615:3: ( (lv_children_17_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2616:1: (lv_children_17_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2616:1: (lv_children_17_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2617:3: lv_children_17_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage5842);
                            	    lv_children_17_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKImageRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"children",
                            	            		lv_children_17_0, 
                            	            		"KRendering");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop129;
                                }
                            } while (true);

                            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage5856); 

                                	newLeafNode(otherlv_18, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_4_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2637:3: (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
                    int alt131=2;
                    int LA131_0 = input.LA(1);

                    if ( (LA131_0==31) ) {
                        alt131=1;
                    }
                    switch (alt131) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2637:5: otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            {
                            otherlv_19=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKImage5871); 

                                	newLeafNode(otherlv_19, grammarAccess.getKImageAccess().getChildPlacementKeyword_4_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2641:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2642:1: (lv_childPlacement_20_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2642:1: (lv_childPlacement_20_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2643:3: lv_childPlacement_20_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_4_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage5892);
                            lv_childPlacement_20_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKImageRule());
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

                    otherlv_21=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage5906); 

                        	newLeafNode(otherlv_21, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_4_5());
                        

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


    // $ANTLR start "entryRuleKArc"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2671:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2672:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2673:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc5944);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc5954); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2680:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) ;
    public final EObject ruleKArc() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        AntlrDatatypeRuleToken lv_startAngle_2_0 = null;

        AntlrDatatypeRuleToken lv_arcAngle_4_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_styles_10_0 = null;

        EObject lv_placementData_12_0 = null;

        EObject lv_children_15_0 = null;

        EObject lv_children_17_0 = null;

        EObject lv_childPlacement_20_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2683:28: ( ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2684:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2684:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2684:2: () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2684:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2685:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKArc6000); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getArcKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2694:1: ( (lv_startAngle_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2695:1: (lv_startAngle_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2695:1: (lv_startAngle_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2696:3: lv_startAngle_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc6021);
            lv_startAngle_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKArcRule());
            	        }
                   		set(
                   			current, 
                   			"startAngle",
                    		lv_startAngle_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2712:2: (otherlv_3= ',' )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==14) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2712:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc6034); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2716:3: ( (lv_arcAngle_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2717:1: (lv_arcAngle_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2717:1: (lv_arcAngle_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2718:3: lv_arcAngle_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc6057);
            lv_arcAngle_4_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKArcRule());
            	        }
                   		set(
                   			current, 
                   			"arcAngle",
                    		lv_arcAngle_4_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2734:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==12) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2734:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc6070); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2738:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt137=2;
                    int LA137_0 = input.LA(1);

                    if ( (LA137_0==29) ) {
                        alt137=1;
                    }
                    switch (alt137) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2738:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKArc6083); 

                                	newLeafNode(otherlv_6, grammarAccess.getKArcAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2742:1: (otherlv_7= ':' )?
                            int alt134=2;
                            int LA134_0 = input.LA(1);

                            if ( (LA134_0==16) ) {
                                alt134=1;
                            }
                            switch (alt134) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2742:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKArc6096); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2746:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2747:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2747:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2748:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc6119);
                            lv_styles_8_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKArcRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_8_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2764:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop136:
                            do {
                                int alt136=2;
                                int LA136_0 = input.LA(1);

                                if ( (LA136_0==14||LA136_0==72||(LA136_0>=74 && LA136_0<=84)) ) {
                                    alt136=1;
                                }


                                switch (alt136) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2764:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2764:3: (otherlv_9= ',' )?
                            	    int alt135=2;
                            	    int LA135_0 = input.LA(1);

                            	    if ( (LA135_0==14) ) {
                            	        alt135=1;
                            	    }
                            	    switch (alt135) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2764:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc6133); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKArcAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2768:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2769:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2769:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2770:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc6156);
                            	    lv_styles_10_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_10_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop136;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2786:6: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
                    int alt138=2;
                    int LA138_0 = input.LA(1);

                    if ( (LA138_0==28) ) {
                        alt138=1;
                    }
                    switch (alt138) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2786:8: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKArc6173); 

                                	newLeafNode(otherlv_11, grammarAccess.getKArcAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2790:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2791:1: (lv_placementData_12_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2791:1: (lv_placementData_12_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2792:3: lv_placementData_12_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc6194);
                            lv_placementData_12_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKArcRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2808:4: (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )?
                    int alt141=2;
                    int LA141_0 = input.LA(1);

                    if ( (LA141_0==17) ) {
                        alt141=1;
                    }
                    switch (alt141) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2808:6: otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}'
                            {
                            otherlv_13=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc6209); 

                                	newLeafNode(otherlv_13, grammarAccess.getKArcAccess().getChildrenKeyword_5_3_0());
                                
                            otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc6221); 

                                	newLeafNode(otherlv_14, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2816:1: ( (lv_children_15_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2817:1: (lv_children_15_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2817:1: (lv_children_15_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2818:3: lv_children_15_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc6242);
                            lv_children_15_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKArcRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_15_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2834:2: ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )*
                            loop140:
                            do {
                                int alt140=2;
                                int LA140_0 = input.LA(1);

                                if ( (LA140_0==14||LA140_0==27||LA140_0==30||(LA140_0>=32 && LA140_0<=36)||(LA140_0>=38 && LA140_0<=40)||LA140_0==42||LA140_0==45) ) {
                                    alt140=1;
                                }


                                switch (alt140) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2834:3: (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2834:3: (otherlv_16= ',' )?
                            	    int alt139=2;
                            	    int LA139_0 = input.LA(1);

                            	    if ( (LA139_0==14) ) {
                            	        alt139=1;
                            	    }
                            	    switch (alt139) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2834:5: otherlv_16= ','
                            	            {
                            	            otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc6256); 

                            	                	newLeafNode(otherlv_16, grammarAccess.getKArcAccess().getCommaKeyword_5_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2838:3: ( (lv_children_17_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2839:1: (lv_children_17_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2839:1: (lv_children_17_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2840:3: lv_children_17_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc6279);
                            	    lv_children_17_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKArcRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"children",
                            	            		lv_children_17_0, 
                            	            		"KRendering");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop140;
                                }
                            } while (true);

                            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc6293); 

                                	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_5_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2860:3: (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
                    int alt142=2;
                    int LA142_0 = input.LA(1);

                    if ( (LA142_0==31) ) {
                        alt142=1;
                    }
                    switch (alt142) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2860:5: otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            {
                            otherlv_19=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKArc6308); 

                                	newLeafNode(otherlv_19, grammarAccess.getKArcAccess().getChildPlacementKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2864:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2865:1: (lv_childPlacement_20_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2865:1: (lv_childPlacement_20_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2866:3: lv_childPlacement_20_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_5_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc6329);
                            lv_childPlacement_20_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKArcRule());
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

                    otherlv_21=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc6343); 

                        	newLeafNode(otherlv_21, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_5_5());
                        

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
    // $ANTLR end "ruleKArc"


    // $ANTLR start "entryRuleKChildArea"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2894:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2895:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2896:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea6381);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea6391); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2903:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? ) ;
    public final EObject ruleKChildArea() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_styles_5_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_placementData_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2906:28: ( ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2907:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2907:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2907:2: () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2907:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2908:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKChildArea6437); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getChildAreaKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2917:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==12) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2917:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea6450); 

                        	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2921:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt147=2;
                    int LA147_0 = input.LA(1);

                    if ( (LA147_0==29) ) {
                        alt147=1;
                    }
                    switch (alt147) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2921:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKChildArea6463); 

                                	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2925:1: (otherlv_4= ':' )?
                            int alt144=2;
                            int LA144_0 = input.LA(1);

                            if ( (LA144_0==16) ) {
                                alt144=1;
                            }
                            switch (alt144) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2925:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKChildArea6476); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2929:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2930:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2930:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2931:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea6499);
                            lv_styles_5_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKChildAreaRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_5_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2947:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop146:
                            do {
                                int alt146=2;
                                int LA146_0 = input.LA(1);

                                if ( (LA146_0==14||LA146_0==72||(LA146_0>=74 && LA146_0<=84)) ) {
                                    alt146=1;
                                }


                                switch (alt146) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2947:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2947:3: (otherlv_6= ',' )?
                            	    int alt145=2;
                            	    int LA145_0 = input.LA(1);

                            	    if ( (LA145_0==14) ) {
                            	        alt145=1;
                            	    }
                            	    switch (alt145) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2947:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea6513); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2951:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2952:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2952:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2953:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea6536);
                            	    lv_styles_7_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKChildAreaRule());
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
                            	    break loop146;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2969:6: (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )?
                    int alt148=2;
                    int LA148_0 = input.LA(1);

                    if ( (LA148_0==28) ) {
                        alt148=1;
                    }
                    switch (alt148) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2969:8: otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKChildArea6553); 

                                	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2973:1: ( (lv_placementData_9_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2974:1: (lv_placementData_9_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2974:1: (lv_placementData_9_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2975:3: lv_placementData_9_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea6574);
                            lv_placementData_9_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKChildAreaRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"placementData",
                                    		lv_placementData_9_0, 
                                    		"KPlacementData");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea6588); 

                        	newLeafNode(otherlv_10, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_2_3());
                        

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
    // $ANTLR end "ruleKChildArea"


    // $ANTLR start "entryRuleKText"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3003:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3004:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3005:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText6626);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText6636); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3012:1: ruleKText returns [EObject current=null] : ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? ) ;
    public final EObject ruleKText() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_clip_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        AntlrDatatypeRuleToken lv_text_2_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_styles_9_0 = null;

        EObject lv_placementData_11_0 = null;

        EObject lv_children_14_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_childPlacement_19_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3015:28: ( ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3016:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3016:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3016:2: () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3016:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3017:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTextAccess().getKTextAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKText6682); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getTextKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3026:1: ( (lv_text_2_0= ruleEString ) )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( ((LA150_0>=RULE_STRING && LA150_0<=RULE_ID)) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3027:1: (lv_text_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3027:1: (lv_text_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3028:3: lv_text_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText6703);
                    lv_text_2_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                    	        }
                           		set(
                           			current, 
                           			"text",
                            		lv_text_2_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3044:3: (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==12) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3044:5: otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText6717); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3048:1: ( (lv_clip_4_0= 'clip' ) )?
                    int alt151=2;
                    int LA151_0 = input.LA(1);

                    if ( (LA151_0==41) ) {
                        alt151=1;
                    }
                    switch (alt151) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3049:1: (lv_clip_4_0= 'clip' )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3049:1: (lv_clip_4_0= 'clip' )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3050:3: lv_clip_4_0= 'clip'
                            {
                            lv_clip_4_0=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKText6735); 

                                    newLeafNode(lv_clip_4_0, grammarAccess.getKTextAccess().getClipClipKeyword_3_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getKTextRule());
                            	        }
                                   		setWithLastConsumed(current, "clip", true, "clip");
                            	    

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3063:3: (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )?
                    int alt155=2;
                    int LA155_0 = input.LA(1);

                    if ( (LA155_0==29) ) {
                        alt155=1;
                    }
                    switch (alt155) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3063:5: otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            {
                            otherlv_5=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKText6762); 

                                	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getStylesKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3067:1: (otherlv_6= ':' )?
                            int alt152=2;
                            int LA152_0 = input.LA(1);

                            if ( (LA152_0==16) ) {
                                alt152=1;
                            }
                            switch (alt152) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3067:3: otherlv_6= ':'
                                    {
                                    otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKText6775); 

                                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3071:3: ( (lv_styles_7_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3072:1: (lv_styles_7_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3072:1: (lv_styles_7_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3073:3: lv_styles_7_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText6798);
                            lv_styles_7_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_7_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3089:2: ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            loop154:
                            do {
                                int alt154=2;
                                int LA154_0 = input.LA(1);

                                if ( (LA154_0==14||LA154_0==72||(LA154_0>=74 && LA154_0<=84)) ) {
                                    alt154=1;
                                }


                                switch (alt154) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3089:3: (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3089:3: (otherlv_8= ',' )?
                            	    int alt153=2;
                            	    int LA153_0 = input.LA(1);

                            	    if ( (LA153_0==14) ) {
                            	        alt153=1;
                            	    }
                            	    switch (alt153) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3089:5: otherlv_8= ','
                            	            {
                            	            otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6812); 

                            	                	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_3_2_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3093:3: ( (lv_styles_9_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3094:1: (lv_styles_9_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3094:1: (lv_styles_9_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3095:3: lv_styles_9_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText6835);
                            	    lv_styles_9_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_9_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop154;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3111:6: (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt156=2;
                    int LA156_0 = input.LA(1);

                    if ( (LA156_0==28) ) {
                        alt156=1;
                    }
                    switch (alt156) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3111:8: otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_10=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKText6852); 

                                	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getPlacementDataKeyword_3_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3115:1: ( (lv_placementData_11_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3116:1: (lv_placementData_11_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3116:1: (lv_placementData_11_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3117:3: lv_placementData_11_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_3_3_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText6873);
                            lv_placementData_11_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3133:4: (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )?
                    int alt159=2;
                    int LA159_0 = input.LA(1);

                    if ( (LA159_0==17) ) {
                        alt159=1;
                    }
                    switch (alt159) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3133:6: otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}'
                            {
                            otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText6888); 

                                	newLeafNode(otherlv_12, grammarAccess.getKTextAccess().getChildrenKeyword_3_4_0());
                                
                            otherlv_13=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText6900); 

                                	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_4_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3141:1: ( (lv_children_14_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3142:1: (lv_children_14_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3142:1: (lv_children_14_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3143:3: lv_children_14_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText6921);
                            lv_children_14_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_14_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3159:2: ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )*
                            loop158:
                            do {
                                int alt158=2;
                                int LA158_0 = input.LA(1);

                                if ( (LA158_0==14||LA158_0==27||LA158_0==30||(LA158_0>=32 && LA158_0<=36)||(LA158_0>=38 && LA158_0<=40)||LA158_0==42||LA158_0==45) ) {
                                    alt158=1;
                                }


                                switch (alt158) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3159:3: (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3159:3: (otherlv_15= ',' )?
                            	    int alt157=2;
                            	    int LA157_0 = input.LA(1);

                            	    if ( (LA157_0==14) ) {
                            	        alt157=1;
                            	    }
                            	    switch (alt157) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3159:5: otherlv_15= ','
                            	            {
                            	            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6935); 

                            	                	newLeafNode(otherlv_15, grammarAccess.getKTextAccess().getCommaKeyword_3_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3163:3: ( (lv_children_16_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3164:1: (lv_children_16_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3164:1: (lv_children_16_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3165:3: lv_children_16_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText6958);
                            	    lv_children_16_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"children",
                            	            		lv_children_16_0, 
                            	            		"KRendering");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop158;
                                }
                            } while (true);

                            otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText6972); 

                                	newLeafNode(otherlv_17, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_4_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3185:3: (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )?
                    int alt160=2;
                    int LA160_0 = input.LA(1);

                    if ( (LA160_0==31) ) {
                        alt160=1;
                    }
                    switch (alt160) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3185:5: otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            {
                            otherlv_18=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKText6987); 

                                	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getChildPlacementKeyword_3_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3189:1: ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3190:1: (lv_childPlacement_19_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3190:1: (lv_childPlacement_19_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3191:3: lv_childPlacement_19_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_3_5_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText7008);
                            lv_childPlacement_19_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"childPlacement",
                                    		lv_childPlacement_19_0, 
                                    		"KPlacement");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    otherlv_20=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText7022); 

                        	newLeafNode(otherlv_20, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_6());
                        

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


    // $ANTLR start "entryRuleKCustomRendering"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3219:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3220:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3221:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering7060);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering7070); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3228:1: ruleKCustomRendering returns [EObject current=null] : ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? ) ;
    public final EObject ruleKCustomRendering() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        AntlrDatatypeRuleToken lv_className_4_0 = null;

        AntlrDatatypeRuleToken lv_bundleName_6_0 = null;

        EObject lv_styles_9_0 = null;

        EObject lv_styles_11_0 = null;

        EObject lv_placementData_13_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_children_18_0 = null;

        EObject lv_childPlacement_21_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3231:28: ( ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3232:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3232:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3232:2: () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3232:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3233:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKCustomRenderingAccess().getKCustomRenderingAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKCustomRendering7116); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getCustomRenderingKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3242:1: (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )?
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==12) ) {
                alt171=1;
            }
            switch (alt171) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3242:3: otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering7129); 

                        	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKCustomRendering7141); 

                        	newLeafNode(otherlv_3, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3250:1: ( (lv_className_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3251:1: (lv_className_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3251:1: (lv_className_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3252:3: lv_className_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering7162);
                    lv_className_4_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	        }
                           		set(
                           			current, 
                           			"className",
                            		lv_className_4_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_5=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKCustomRendering7174); 

                        	newLeafNode(otherlv_5, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_2_3());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3272:1: ( (lv_bundleName_6_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3273:1: (lv_bundleName_6_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3273:1: (lv_bundleName_6_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3274:3: lv_bundleName_6_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_2_4_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering7195);
                    lv_bundleName_6_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                    	        }
                           		set(
                           			current, 
                           			"bundleName",
                            		lv_bundleName_6_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3290:2: (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )?
                    int alt165=2;
                    int LA165_0 = input.LA(1);

                    if ( (LA165_0==29) ) {
                        alt165=1;
                    }
                    switch (alt165) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3290:4: otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            {
                            otherlv_7=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKCustomRendering7208); 

                                	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_2_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3294:1: (otherlv_8= ':' )?
                            int alt162=2;
                            int LA162_0 = input.LA(1);

                            if ( (LA162_0==16) ) {
                                alt162=1;
                            }
                            switch (alt162) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3294:3: otherlv_8= ':'
                                    {
                                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKCustomRendering7221); 

                                        	newLeafNode(otherlv_8, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3298:3: ( (lv_styles_9_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3299:1: (lv_styles_9_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3299:1: (lv_styles_9_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3300:3: lv_styles_9_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering7244);
                            lv_styles_9_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_9_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3316:2: ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            loop164:
                            do {
                                int alt164=2;
                                int LA164_0 = input.LA(1);

                                if ( (LA164_0==14||LA164_0==72||(LA164_0>=74 && LA164_0<=84)) ) {
                                    alt164=1;
                                }


                                switch (alt164) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3316:3: (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3316:3: (otherlv_10= ',' )?
                            	    int alt163=2;
                            	    int LA163_0 = input.LA(1);

                            	    if ( (LA163_0==14) ) {
                            	        alt163=1;
                            	    }
                            	    switch (alt163) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3316:5: otherlv_10= ','
                            	            {
                            	            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering7258); 

                            	                	newLeafNode(otherlv_10, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3320:3: ( (lv_styles_11_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3321:1: (lv_styles_11_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3321:1: (lv_styles_11_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3322:3: lv_styles_11_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering7281);
                            	    lv_styles_11_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_11_0, 
                            	            		"KStyle");
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3338:6: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt166=2;
                    int LA166_0 = input.LA(1);

                    if ( (LA166_0==28) ) {
                        alt166=1;
                    }
                    switch (alt166) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3338:8: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_12=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKCustomRendering7298); 

                                	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_2_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3342:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3343:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3343:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3344:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_2_6_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering7319);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3360:4: (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )?
                    int alt169=2;
                    int LA169_0 = input.LA(1);

                    if ( (LA169_0==17) ) {
                        alt169=1;
                    }
                    switch (alt169) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3360:6: otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}'
                            {
                            otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering7334); 

                                	newLeafNode(otherlv_14, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_2_7_0());
                                
                            otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering7346); 

                                	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_7_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3368:1: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3369:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3369:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3370:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_7_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering7367);
                            lv_children_16_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_16_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3386:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop168:
                            do {
                                int alt168=2;
                                int LA168_0 = input.LA(1);

                                if ( (LA168_0==14||LA168_0==27||LA168_0==30||(LA168_0>=32 && LA168_0<=36)||(LA168_0>=38 && LA168_0<=40)||LA168_0==42||LA168_0==45) ) {
                                    alt168=1;
                                }


                                switch (alt168) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3386:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3386:3: (otherlv_17= ',' )?
                            	    int alt167=2;
                            	    int LA167_0 = input.LA(1);

                            	    if ( (LA167_0==14) ) {
                            	        alt167=1;
                            	    }
                            	    switch (alt167) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3386:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering7381); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_7_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3390:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3391:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3391:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3392:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_7_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering7404);
                            	    lv_children_18_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
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
                            	    break loop168;
                                }
                            } while (true);

                            otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering7418); 

                                	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_2_7_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3412:3: (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )?
                    int alt170=2;
                    int LA170_0 = input.LA(1);

                    if ( (LA170_0==31) ) {
                        alt170=1;
                    }
                    switch (alt170) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3412:5: otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) )
                            {
                            otherlv_20=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKCustomRendering7433); 

                                	newLeafNode(otherlv_20, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_2_8_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3416:1: ( (lv_childPlacement_21_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3417:1: (lv_childPlacement_21_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3417:1: (lv_childPlacement_21_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3418:3: lv_childPlacement_21_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_2_8_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering7454);
                            lv_childPlacement_21_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"childPlacement",
                                    		lv_childPlacement_21_0, 
                                    		"KPlacement");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering7468); 

                        	newLeafNode(otherlv_22, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_2_9());
                        

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


    // $ANTLR start "entryRuleKSpline"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3446:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3447:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3448:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline7506);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline7516); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3455:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? ) ;
    public final EObject ruleKSpline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        EObject lv_styles_5_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_placementData_9_0 = null;

        EObject lv_children_12_0 = null;

        EObject lv_children_14_0 = null;

        EObject lv_childPlacement_17_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3458:28: ( ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3459:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3459:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3459:2: () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3459:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3460:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKSpline7562); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getSplineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3469:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==12) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3469:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline7575); 

                        	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3473:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt175=2;
                    int LA175_0 = input.LA(1);

                    if ( (LA175_0==29) ) {
                        alt175=1;
                    }
                    switch (alt175) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3473:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKSpline7588); 

                                	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3477:1: (otherlv_4= ':' )?
                            int alt172=2;
                            int LA172_0 = input.LA(1);

                            if ( (LA172_0==16) ) {
                                alt172=1;
                            }
                            switch (alt172) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3477:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKSpline7601); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3481:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3482:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3482:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3483:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline7624);
                            lv_styles_5_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_5_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3499:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop174:
                            do {
                                int alt174=2;
                                int LA174_0 = input.LA(1);

                                if ( (LA174_0==14||LA174_0==72||(LA174_0>=74 && LA174_0<=84)) ) {
                                    alt174=1;
                                }


                                switch (alt174) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3499:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3499:3: (otherlv_6= ',' )?
                            	    int alt173=2;
                            	    int LA173_0 = input.LA(1);

                            	    if ( (LA173_0==14) ) {
                            	        alt173=1;
                            	    }
                            	    switch (alt173) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3499:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7638); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3503:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3504:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3504:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3505:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline7661);
                            	    lv_styles_7_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
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
                            	    break loop174;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3521:6: (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )?
                    int alt176=2;
                    int LA176_0 = input.LA(1);

                    if ( (LA176_0==28) ) {
                        alt176=1;
                    }
                    switch (alt176) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3521:8: otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKSpline7678); 

                                	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3525:1: ( (lv_placementData_9_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3526:1: (lv_placementData_9_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3526:1: (lv_placementData_9_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3527:3: lv_placementData_9_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline7699);
                            lv_placementData_9_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"placementData",
                                    		lv_placementData_9_0, 
                                    		"KPlacementData");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3543:4: (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )?
                    int alt179=2;
                    int LA179_0 = input.LA(1);

                    if ( (LA179_0==17) ) {
                        alt179=1;
                    }
                    switch (alt179) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3543:6: otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}'
                            {
                            otherlv_10=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline7714); 

                                	newLeafNode(otherlv_10, grammarAccess.getKSplineAccess().getChildrenKeyword_2_3_0());
                                
                            otherlv_11=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline7726); 

                                	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3551:1: ( (lv_children_12_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3552:1: (lv_children_12_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3552:1: (lv_children_12_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3553:3: lv_children_12_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline7747);
                            lv_children_12_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_12_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3569:2: ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )*
                            loop178:
                            do {
                                int alt178=2;
                                int LA178_0 = input.LA(1);

                                if ( (LA178_0==14||LA178_0==27||LA178_0==30||(LA178_0>=32 && LA178_0<=36)||(LA178_0>=38 && LA178_0<=40)||LA178_0==42||LA178_0==45) ) {
                                    alt178=1;
                                }


                                switch (alt178) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3569:3: (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3569:3: (otherlv_13= ',' )?
                            	    int alt177=2;
                            	    int LA177_0 = input.LA(1);

                            	    if ( (LA177_0==14) ) {
                            	        alt177=1;
                            	    }
                            	    switch (alt177) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3569:5: otherlv_13= ','
                            	            {
                            	            otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7761); 

                            	                	newLeafNode(otherlv_13, grammarAccess.getKSplineAccess().getCommaKeyword_2_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3573:3: ( (lv_children_14_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3574:1: (lv_children_14_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3574:1: (lv_children_14_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3575:3: lv_children_14_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline7784);
                            	    lv_children_14_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"children",
                            	            		lv_children_14_0, 
                            	            		"KRendering");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop178;
                                }
                            } while (true);

                            otherlv_15=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline7798); 

                                	newLeafNode(otherlv_15, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_2_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3595:3: (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )?
                    int alt180=2;
                    int LA180_0 = input.LA(1);

                    if ( (LA180_0==31) ) {
                        alt180=1;
                    }
                    switch (alt180) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3595:5: otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            {
                            otherlv_16=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKSpline7813); 

                                	newLeafNode(otherlv_16, grammarAccess.getKSplineAccess().getChildPlacementKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3599:1: ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3600:1: (lv_childPlacement_17_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3600:1: (lv_childPlacement_17_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3601:3: lv_childPlacement_17_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_2_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline7834);
                            lv_childPlacement_17_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"childPlacement",
                                    		lv_childPlacement_17_0, 
                                    		"KPlacement");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline7848); 

                        	newLeafNode(otherlv_18, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_2_5());
                        

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
    // $ANTLR end "ruleKSpline"


    // $ANTLR start "entryRuleKDecoratorPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3629:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3630:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3631:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData7886);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData7896); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3638:1: ruleKDecoratorPlacementData returns [EObject current=null] : (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3641:28: ( (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3642:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3642:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3642:3: otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}'
            {
            otherlv_0=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKDecoratorPlacementData7933); 

                	newLeafNode(otherlv_0, grammarAccess.getKDecoratorPlacementDataAccess().getDecoratorPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData7945); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3650:1: ( (lv_relative_2_0= 'relative' ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==47) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3651:1: (lv_relative_2_0= 'relative' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3651:1: (lv_relative_2_0= 'relative' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3652:3: lv_relative_2_0= 'relative'
                    {
                    lv_relative_2_0=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKDecoratorPlacementData7963); 

                            newLeafNode(lv_relative_2_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		setWithLastConsumed(current, "relative", true, "relative");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKDecoratorPlacementData7989); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3669:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3670:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3670:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3671:3: lv_location_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getLocationEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8010);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3687:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==49) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3687:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKDecoratorPlacementData8023); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3691:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3692:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3692:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3693:3: lv_xOffset_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8044);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3709:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==50) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3709:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKDecoratorPlacementData8059); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3713:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3714:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3714:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3715:3: lv_yOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8080);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3731:4: (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )?
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==51) ) {
                alt185=1;
            }
            switch (alt185) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3731:6: otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKDecoratorPlacementData8095); 

                        	newLeafNode(otherlv_9, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3735:1: ( (lv_width_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3736:1: (lv_width_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3736:1: (lv_width_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3737:3: lv_width_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8116);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3753:4: (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==52) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3753:6: otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKDecoratorPlacementData8131); 

                        	newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3757:1: ( (lv_height_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3758:1: (lv_height_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3758:1: (lv_height_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3759:3: lv_height_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8152);
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

            otherlv_13=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKDecoratorPlacementData8166); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3787:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3788:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3789:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData8202);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData8212); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3796:1: ruleKGridPlacementData returns [EObject current=null] : (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' ) ;
    public final EObject ruleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        AntlrDatatypeRuleToken lv_widthHint_3_0 = null;

        AntlrDatatypeRuleToken lv_heightHint_5_0 = null;

        AntlrDatatypeRuleToken lv_insetRight_7_0 = null;

        AntlrDatatypeRuleToken lv_insetBottom_9_0 = null;

        AntlrDatatypeRuleToken lv_insetLeft_11_0 = null;

        AntlrDatatypeRuleToken lv_insetTop_13_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3799:28: ( (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3800:1: (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3800:1: (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3800:3: otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}'
            {
            otherlv_0=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKGridPlacementData8249); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementDataAccess().getGridPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData8261); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKGridPlacementData8273); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3812:1: ( (lv_widthHint_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3813:1: (lv_widthHint_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3813:1: (lv_widthHint_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3814:3: lv_widthHint_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8294);
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

            otherlv_4=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKGridPlacementData8306); 

                	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3834:1: ( (lv_heightHint_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3835:1: (lv_heightHint_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3835:1: (lv_heightHint_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3836:3: lv_heightHint_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8327);
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

            otherlv_6=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKGridPlacementData8339); 

                	newLeafNode(otherlv_6, grammarAccess.getKGridPlacementDataAccess().getInsetRightKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3856:1: ( (lv_insetRight_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3857:1: (lv_insetRight_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3857:1: (lv_insetRight_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3858:3: lv_insetRight_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetRightEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8360);
            lv_insetRight_7_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetRight",
                    		lv_insetRight_7_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKGridPlacementData8372); 

                	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getInsetBottomKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3878:1: ( (lv_insetBottom_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3879:1: (lv_insetBottom_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3879:1: (lv_insetBottom_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3880:3: lv_insetBottom_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetBottomEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8393);
            lv_insetBottom_9_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetBottom",
                    		lv_insetBottom_9_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_10=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKGridPlacementData8405); 

                	newLeafNode(otherlv_10, grammarAccess.getKGridPlacementDataAccess().getInsetLeftKeyword_10());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3900:1: ( (lv_insetLeft_11_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3901:1: (lv_insetLeft_11_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3901:1: (lv_insetLeft_11_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3902:3: lv_insetLeft_11_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetLeftEFloatParserRuleCall_11_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8426);
            lv_insetLeft_11_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetLeft",
                    		lv_insetLeft_11_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_12=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKGridPlacementData8438); 

                	newLeafNode(otherlv_12, grammarAccess.getKGridPlacementDataAccess().getInsetTopKeyword_12());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3922:1: ( (lv_insetTop_13_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3923:1: (lv_insetTop_13_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3923:1: (lv_insetTop_13_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3924:3: lv_insetTop_13_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetTopEFloatParserRuleCall_13_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8459);
            lv_insetTop_13_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"insetTop",
                    		lv_insetTop_13_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_14=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKGridPlacementData8471); 

                	newLeafNode(otherlv_14, grammarAccess.getKGridPlacementDataAccess().getRightCurlyBracketKeyword_14());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3952:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3953:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3954:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData8507);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData8517); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3961:1: ruleKStackPlacementData returns [EObject current=null] : (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3964:28: ( (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3965:1: (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3965:1: (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3965:3: otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKStackPlacementData8554); 

                	newLeafNode(otherlv_0, grammarAccess.getKStackPlacementDataAccess().getStackPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData8566); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKStackPlacementData8578); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3977:1: ( (lv_insetRight_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3978:1: (lv_insetRight_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3978:1: (lv_insetRight_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3979:3: lv_insetRight_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8599);
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

            otherlv_4=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKStackPlacementData8611); 

                	newLeafNode(otherlv_4, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3999:1: ( (lv_insetBottom_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4000:1: (lv_insetBottom_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4000:1: (lv_insetBottom_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4001:3: lv_insetBottom_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8632);
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

            otherlv_6=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKStackPlacementData8644); 

                	newLeafNode(otherlv_6, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4021:1: ( (lv_insetLeft_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4022:1: (lv_insetLeft_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4022:1: (lv_insetLeft_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4023:3: lv_insetLeft_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8665);
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

            otherlv_8=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKStackPlacementData8677); 

                	newLeafNode(otherlv_8, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4043:1: ( (lv_insetTop_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4044:1: (lv_insetTop_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4044:1: (lv_insetTop_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4045:3: lv_insetTop_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8698);
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

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKStackPlacementData8710); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4073:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4074:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4075:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData8746);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData8756); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4082:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) ;
    public final EObject ruleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_topLeft_3_0 = null;

        EObject lv_bottomRight_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4085:28: ( (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4086:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4086:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4086:3: otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKDirectPlacementData8793); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData8805); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKDirectPlacementData8817); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4098:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4099:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4099:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4100:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8838);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4116:2: (otherlv_4= ',' )?
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==14) ) {
                alt187=1;
            }
            switch (alt187) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4116:4: otherlv_4= ','
                    {
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDirectPlacementData8851); 

                        	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getCommaKeyword_4());
                        

                    }
                    break;

            }

            otherlv_5=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKDirectPlacementData8865); 

                	newLeafNode(otherlv_5, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4124:1: ( (lv_bottomRight_6_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4125:1: (lv_bottomRight_6_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4125:1: (lv_bottomRight_6_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4126:3: lv_bottomRight_6_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8886);
            lv_bottomRight_6_0=ruleKPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKDirectPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"bottomRight",
                    		lv_bottomRight_6_0, 
                    		"KPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKDirectPlacementData8898); 

                	newLeafNode(otherlv_7, grammarAccess.getKDirectPlacementDataAccess().getRightCurlyBracketKeyword_7());
                

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4154:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4155:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4156:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData8934);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData8944); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4163:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' ) ;
    public final EObject ruleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_points_4_0 = null;

        EObject lv_points_6_0 = null;

        EObject lv_detailPlacementData_8_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4166:28: ( (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4167:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4167:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4167:3: otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKPolylinePlacementData8981); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getPolylinePlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData8993); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKPolylinePlacementData9005); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4179:1: (otherlv_3= ':' )?
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==16) ) {
                alt188=1;
            }
            switch (alt188) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4179:3: otherlv_3= ':'
                    {
                    otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolylinePlacementData9018); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4183:3: ( (lv_points_4_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4184:1: (lv_points_4_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4184:1: (lv_points_4_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4185:3: lv_points_4_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9041);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4201:2: ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )*
            loop190:
            do {
                int alt190=2;
                int LA190_0 = input.LA(1);

                if ( (LA190_0==14||(LA190_0>=68 && LA190_0<=69)) ) {
                    alt190=1;
                }


                switch (alt190) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4201:3: (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4201:3: (otherlv_5= ',' )?
            	    int alt189=2;
            	    int LA189_0 = input.LA(1);

            	    if ( (LA189_0==14) ) {
            	        alt189=1;
            	    }
            	    switch (alt189) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4201:5: otherlv_5= ','
            	            {
            	            otherlv_5=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolylinePlacementData9055); 

            	                	newLeafNode(otherlv_5, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_5_0());
            	                

            	            }
            	            break;

            	    }

            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4205:3: ( (lv_points_6_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4206:1: (lv_points_6_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4206:1: (lv_points_6_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4207:3: lv_points_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9078);
            	    lv_points_6_0=ruleKPosition();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKPolylinePlacementDataRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"points",
            	            		lv_points_6_0, 
            	            		"KPosition");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop190;
                }
            } while (true);

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4223:4: (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==66) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4223:6: otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) )
                    {
                    otherlv_7=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKPolylinePlacementData9093); 

                        	newLeafNode(otherlv_7, grammarAccess.getKPolylinePlacementDataAccess().getDetailedPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4227:1: ( (lv_detailPlacementData_8_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4228:1: (lv_detailPlacementData_8_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4228:1: (lv_detailPlacementData_8_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4229:3: lv_detailPlacementData_8_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getDetailPlacementDataKPlacementDataParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData9114);
                    lv_detailPlacementData_8_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolylinePlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"detailPlacementData",
                            		lv_detailPlacementData_8_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolylinePlacementData9128); 

                	newLeafNode(otherlv_9, grammarAccess.getKPolylinePlacementDataAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "entryRuleKPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4257:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4258:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4259:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition9164);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition9174); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4266:1: ruleKPosition returns [EObject current=null] : ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_x_0_0 = null;

        EObject lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4269:28: ( ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4270:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4270:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4270:2: ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4270:2: ( (lv_x_0_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4271:1: (lv_x_0_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4271:1: (lv_x_0_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4272:3: lv_x_0_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition9220);
            lv_x_0_0=ruleKXPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPositionRule());
            	        }
                   		set(
                   			current, 
                   			"x",
                    		lv_x_0_0, 
                    		"KXPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKPosition9232); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getSolidusKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4292:1: ( (lv_y_2_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4293:1: (lv_y_2_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4293:1: (lv_y_2_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4294:3: lv_y_2_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition9253);
            lv_y_2_0=ruleKYPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPositionRule());
            	        }
                   		set(
                   			current, 
                   			"y",
                    		lv_y_2_0, 
                    		"KYPosition");
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


    // $ANTLR start "entryRuleKLeftPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4318:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4319:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4320:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition9289);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition9299); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4327:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4330:28: ( ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4331:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4331:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4331:2: () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4331:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4332:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKLeftPosition9345); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getLeftKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4341:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4342:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4342:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4343:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition9366);
            lv_absolute_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLeftPositionRule());
            	        }
                   		set(
                   			current, 
                   			"absolute",
                    		lv_absolute_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4359:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4360:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4360:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4361:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition9387);
            lv_relative_3_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLeftPositionRule());
            	        }
                   		set(
                   			current, 
                   			"relative",
                    		lv_relative_3_0, 
                    		"EFloat");
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
    // $ANTLR end "ruleKLeftPosition"


    // $ANTLR start "entryRuleKRightPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4385:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4386:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4387:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition9423);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition9433); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4394:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4397:28: ( ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4398:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4398:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4398:2: () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4398:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4399:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKRightPosition9479); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getRightKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4408:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4409:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4409:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4410:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition9500);
            lv_absolute_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKRightPositionRule());
            	        }
                   		set(
                   			current, 
                   			"absolute",
                    		lv_absolute_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4426:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4427:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4427:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4428:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition9521);
            lv_relative_3_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKRightPositionRule());
            	        }
                   		set(
                   			current, 
                   			"relative",
                    		lv_relative_3_0, 
                    		"EFloat");
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
    // $ANTLR end "ruleKRightPosition"


    // $ANTLR start "entryRuleKTopPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4452:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4453:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4454:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition9557);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition9567); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4461:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4464:28: ( ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4465:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4465:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4465:2: () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4465:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4466:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKTopPosition9613); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getTopKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4475:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4476:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4476:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4477:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition9634);
            lv_absolute_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKTopPositionRule());
            	        }
                   		set(
                   			current, 
                   			"absolute",
                    		lv_absolute_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4493:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4494:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4494:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4495:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition9655);
            lv_relative_3_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKTopPositionRule());
            	        }
                   		set(
                   			current, 
                   			"relative",
                    		lv_relative_3_0, 
                    		"EFloat");
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
    // $ANTLR end "ruleKTopPosition"


    // $ANTLR start "entryRuleKBottomPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4519:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4520:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4521:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition9691);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition9701); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4528:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4531:28: ( ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4532:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4532:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4532:2: () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4532:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4533:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKBottomPosition9747); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getBottomKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4542:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4543:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4543:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4544:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition9768);
            lv_absolute_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBottomPositionRule());
            	        }
                   		set(
                   			current, 
                   			"absolute",
                    		lv_absolute_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4560:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4561:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4561:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4562:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition9789);
            lv_relative_3_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBottomPositionRule());
            	        }
                   		set(
                   			current, 
                   			"relative",
                    		lv_relative_3_0, 
                    		"EFloat");
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
    // $ANTLR end "ruleKBottomPosition"


    // $ANTLR start "entryRuleKForegroundColor"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4586:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4587:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4588:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor9825);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor9835); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4595:1: ruleKForegroundColor returns [EObject current=null] : ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKForegroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4598:28: ( ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4599:1: ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4599:1: ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4599:2: () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4599:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4600:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundColorAccess().getKForegroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKForegroundColor9881); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getForegroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4609:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4610:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4610:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4611:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9902);
            lv_red_2_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"red",
                    		lv_red_2_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4627:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4628:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4628:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4629:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9923);
            lv_green_3_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"green",
                    		lv_green_3_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4645:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4646:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4646:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4647:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9944);
            lv_blue_4_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"blue",
                    		lv_blue_4_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4663:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==73) ) {
                alt192=1;
            }
            switch (alt192) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4664:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4664:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4665:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKForegroundColor9962); 

                            newLeafNode(lv_propagateToChildren_5_0, grammarAccess.getKForegroundColorAccess().getPropagateToChildrenExclamationMarkKeyword_5_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundColorRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // $ANTLR end "ruleKForegroundColor"


    // $ANTLR start "entryRuleKBackgroundColor"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4686:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4687:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4688:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor10012);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor10022); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4695:1: ruleKBackgroundColor returns [EObject current=null] : ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4698:28: ( ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4699:1: ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4699:1: ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4699:2: () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4699:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4700:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundColorAccess().getKBackgroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKBackgroundColor10068); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getBackgroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4709:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4710:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4710:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4711:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10089);
            lv_red_2_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"red",
                    		lv_red_2_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4727:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4728:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4728:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4729:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10110);
            lv_green_3_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"green",
                    		lv_green_3_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4745:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4746:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4746:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4747:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10131);
            lv_blue_4_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"blue",
                    		lv_blue_4_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4763:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==73) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4764:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4764:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4765:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKBackgroundColor10149); 

                            newLeafNode(lv_propagateToChildren_5_0, grammarAccess.getKBackgroundColorAccess().getPropagateToChildrenExclamationMarkKeyword_5_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundColorRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // $ANTLR end "ruleKBackgroundColor"


    // $ANTLR start "entryRuleKLineWidth"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4786:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4787:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4788:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth10199);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth10209); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4795:1: ruleKLineWidth returns [EObject current=null] : (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_lineWidth_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4798:28: ( (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4799:1: (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4799:1: (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4799:3: otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKLineWidth10246); 

                	newLeafNode(otherlv_0, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4803:1: ( (lv_lineWidth_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4804:1: (lv_lineWidth_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4804:1: (lv_lineWidth_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4805:3: lv_lineWidth_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth10267);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4821:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt194=2;
            int LA194_0 = input.LA(1);

            if ( (LA194_0==73) ) {
                alt194=1;
            }
            switch (alt194) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4822:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4822:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4823:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKLineWidth10285); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKLineWidthAccess().getPropagateToChildrenExclamationMarkKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineWidthRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4844:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4845:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4846:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility10335);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility10345); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4853:1: ruleKVisibility returns [EObject current=null] : (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundVisibility_0 = null;

        EObject this_KBackgroundVisibility_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4856:28: ( (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4857:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4857:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            int alt195=2;
            int LA195_0 = input.LA(1);

            if ( (LA195_0==76) ) {
                alt195=1;
            }
            else if ( (LA195_0==77) ) {
                alt195=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 195, 0, input);

                throw nvae;
            }
            switch (alt195) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4858:5: this_KForegroundVisibility_0= ruleKForegroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKForegroundVisibilityParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility10392);
                    this_KForegroundVisibility_0=ruleKForegroundVisibility();

                    state._fsp--;

                     
                            current = this_KForegroundVisibility_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4868:5: this_KBackgroundVisibility_1= ruleKBackgroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKBackgroundVisibilityParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility10419);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4884:1: entryRuleKForegroundVisibility returns [EObject current=null] : iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF ;
    public final EObject entryRuleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4885:2: (iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4886:2: iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKForegroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility10454);
            iv_ruleKForegroundVisibility=ruleKForegroundVisibility();

            state._fsp--;

             current =iv_ruleKForegroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundVisibility10464); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4893:1: ruleKForegroundVisibility returns [EObject current=null] : ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4896:28: ( ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4897:1: ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4897:1: ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4897:2: () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4897:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4898:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKForegroundVisibility10510); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundVisibilityAccess().getForegroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4907:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4908:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4908:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4909:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility10531);
            lv_visible_2_0=ruleEBoolean();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKForegroundVisibilityRule());
            	        }
                   		set(
                   			current, 
                   			"visible",
                    		lv_visible_2_0, 
                    		"EBoolean");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4925:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==73) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4926:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4926:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4927:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKForegroundVisibility10549); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKForegroundVisibilityAccess().getPropagateToChildrenExclamationMarkKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4948:1: entryRuleKBackgroundVisibility returns [EObject current=null] : iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF ;
    public final EObject entryRuleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4949:2: (iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4950:2: iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility10599);
            iv_ruleKBackgroundVisibility=ruleKBackgroundVisibility();

            state._fsp--;

             current =iv_ruleKBackgroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundVisibility10609); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4957:1: ruleKBackgroundVisibility returns [EObject current=null] : ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4960:28: ( ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4961:1: ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4961:1: ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4961:2: () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4961:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4962:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKBackgroundVisibility10655); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundVisibilityAccess().getBackgroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4971:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4972:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4972:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4973:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility10676);
            lv_visible_2_0=ruleEBoolean();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBackgroundVisibilityRule());
            	        }
                   		set(
                   			current, 
                   			"visible",
                    		lv_visible_2_0, 
                    		"EBoolean");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4989:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt197=2;
            int LA197_0 = input.LA(1);

            if ( (LA197_0==73) ) {
                alt197=1;
            }
            switch (alt197) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4990:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4990:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4991:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKBackgroundVisibility10694); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKBackgroundVisibilityAccess().getPropagateToChildrenExclamationMarkKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5012:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5013:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5014:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle10744);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle10754); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5021:1: ruleKLineStyle returns [EObject current=null] : ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_lineStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5024:28: ( ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5025:1: ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5025:1: ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5025:2: () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5025:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5026:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineStyleAccess().getKLineStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleKLineStyle10800); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5035:1: ( (lv_lineStyle_2_0= ruleLineStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5036:1: (lv_lineStyle_2_0= ruleLineStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5036:1: (lv_lineStyle_2_0= ruleLineStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5037:3: lv_lineStyle_2_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle10821);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5053:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==73) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5054:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5054:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5055:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKLineStyle10839); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKLineStyleAccess().getPropagateToChildrenExclamationMarkKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineStyleRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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


    // $ANTLR start "entryRuleKFontBold"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5076:1: entryRuleKFontBold returns [EObject current=null] : iv_ruleKFontBold= ruleKFontBold EOF ;
    public final EObject entryRuleKFontBold() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontBold = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5077:2: (iv_ruleKFontBold= ruleKFontBold EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5078:2: iv_ruleKFontBold= ruleKFontBold EOF
            {
             newCompositeNode(grammarAccess.getKFontBoldRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_entryRuleKFontBold10889);
            iv_ruleKFontBold=ruleKFontBold();

            state._fsp--;

             current =iv_ruleKFontBold; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontBold10899); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5085:1: ruleKFontBold returns [EObject current=null] : ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontBold() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5088:28: ( ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5089:1: ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5089:1: ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5089:2: () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5089:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5090:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontBoldAccess().getKFontBoldAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleKFontBold10945); 

                	newLeafNode(otherlv_1, grammarAccess.getKFontBoldAccess().getBoldKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5099:1: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==73) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5100:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5100:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5101:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKFontBold10963); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKFontBoldAccess().getPropagateToChildrenExclamationMarkKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontBoldRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // $ANTLR end "ruleKFontBold"


    // $ANTLR start "entryRuleKFontItalic"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5122:1: entryRuleKFontItalic returns [EObject current=null] : iv_ruleKFontItalic= ruleKFontItalic EOF ;
    public final EObject entryRuleKFontItalic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontItalic = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5123:2: (iv_ruleKFontItalic= ruleKFontItalic EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5124:2: iv_ruleKFontItalic= ruleKFontItalic EOF
            {
             newCompositeNode(grammarAccess.getKFontItalicRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic11013);
            iv_ruleKFontItalic=ruleKFontItalic();

            state._fsp--;

             current =iv_ruleKFontItalic; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontItalic11023); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5131:1: ruleKFontItalic returns [EObject current=null] : ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontItalic() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5134:28: ( ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5135:1: ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5135:1: ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5135:2: () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5135:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5136:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontItalicAccess().getKFontItalicAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleKFontItalic11069); 

                	newLeafNode(otherlv_1, grammarAccess.getKFontItalicAccess().getItalicKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5145:1: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt200=2;
            int LA200_0 = input.LA(1);

            if ( (LA200_0==73) ) {
                alt200=1;
            }
            switch (alt200) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5146:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5146:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5147:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKFontItalic11087); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKFontItalicAccess().getPropagateToChildrenExclamationMarkKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontItalicRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // $ANTLR end "ruleKFontItalic"


    // $ANTLR start "entryRuleKFontName"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5168:1: entryRuleKFontName returns [EObject current=null] : iv_ruleKFontName= ruleKFontName EOF ;
    public final EObject entryRuleKFontName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontName = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5169:2: (iv_ruleKFontName= ruleKFontName EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5170:2: iv_ruleKFontName= ruleKFontName EOF
            {
             newCompositeNode(grammarAccess.getKFontNameRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_entryRuleKFontName11137);
            iv_ruleKFontName=ruleKFontName();

            state._fsp--;

             current =iv_ruleKFontName; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontName11147); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5177:1: ruleKFontName returns [EObject current=null] : (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontName() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5180:28: ( (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5181:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5181:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5181:3: otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleKFontName11184); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontNameAccess().getFontKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5185:1: ( (lv_name_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5186:1: (lv_name_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5186:1: (lv_name_1_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5187:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKFontNameAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKFontName11205);
            lv_name_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKFontNameRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5203:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==73) ) {
                alt201=1;
            }
            switch (alt201) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5204:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5204:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5205:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKFontName11223); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKFontNameAccess().getPropagateToChildrenExclamationMarkKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontNameRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // $ANTLR end "ruleKFontName"


    // $ANTLR start "entryRuleKFontSize"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5226:1: entryRuleKFontSize returns [EObject current=null] : iv_ruleKFontSize= ruleKFontSize EOF ;
    public final EObject entryRuleKFontSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontSize = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5227:2: (iv_ruleKFontSize= ruleKFontSize EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5228:2: iv_ruleKFontSize= ruleKFontSize EOF
            {
             newCompositeNode(grammarAccess.getKFontSizeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_entryRuleKFontSize11273);
            iv_ruleKFontSize=ruleKFontSize();

            state._fsp--;

             current =iv_ruleKFontSize; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontSize11283); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5235:1: ruleKFontSize returns [EObject current=null] : (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontSize() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_size_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5238:28: ( (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5239:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5239:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5239:3: otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKFontSize11320); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontSizeAccess().getFontSizeKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5243:1: ( (lv_size_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5244:1: (lv_size_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5244:1: (lv_size_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5245:3: lv_size_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKFontSizeAccess().getSizeEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKFontSize11341);
            lv_size_1_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKFontSizeRule());
            	        }
                   		set(
                   			current, 
                   			"size",
                    		lv_size_1_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5261:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt202=2;
            int LA202_0 = input.LA(1);

            if ( (LA202_0==73) ) {
                alt202=1;
            }
            switch (alt202) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5262:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5262:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5263:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKFontSize11359); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKFontSizeAccess().getPropagateToChildrenExclamationMarkKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKFontSizeRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // $ANTLR end "ruleKFontSize"


    // $ANTLR start "entryRuleKVerticalAlignment"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5284:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5285:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5286:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment11409);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment11419); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5293:1: ruleKVerticalAlignment returns [EObject current=null] : ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_verticalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5296:28: ( ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5297:1: ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5297:1: ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5297:2: () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5297:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5298:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKVerticalAlignment11465); 

                	newLeafNode(otherlv_1, grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5307:1: ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5308:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5308:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5309:3: lv_verticalAlignment_2_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment11486);
            lv_verticalAlignment_2_0=ruleVerticalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKVerticalAlignmentRule());
            	        }
                   		set(
                   			current, 
                   			"verticalAlignment",
                    		lv_verticalAlignment_2_0, 
                    		"VerticalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5325:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt203=2;
            int LA203_0 = input.LA(1);

            if ( (LA203_0==73) ) {
                alt203=1;
            }
            switch (alt203) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5326:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5326:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5327:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKVerticalAlignment11504); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKVerticalAlignmentAccess().getPropagateToChildrenExclamationMarkKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5348:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5349:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5350:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment11554);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment11564); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5357:1: ruleKHorizontalAlignment returns [EObject current=null] : ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_horizontalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5360:28: ( ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5361:1: ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5361:1: ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5361:2: () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5361:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5362:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKHorizontalAlignment11610); 

                	newLeafNode(otherlv_1, grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5371:1: ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5372:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5372:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5373:3: lv_horizontalAlignment_2_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11631);
            lv_horizontalAlignment_2_0=ruleHorizontalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKHorizontalAlignmentRule());
            	        }
                   		set(
                   			current, 
                   			"horizontalAlignment",
                    		lv_horizontalAlignment_2_0, 
                    		"HorizontalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5389:2: ( (lv_propagateToChildren_3_0= '!' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5390:1: (lv_propagateToChildren_3_0= '!' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5390:1: (lv_propagateToChildren_3_0= '!' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5391:3: lv_propagateToChildren_3_0= '!'
            {
            lv_propagateToChildren_3_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKHorizontalAlignment11649); 

                    newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKHorizontalAlignmentAccess().getPropagateToChildrenExclamationMarkKeyword_3_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "!");
            	    

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5412:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5413:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5414:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11698);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement11708); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5421:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_numColumns_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5424:28: ( ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5425:1: ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5425:1: ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5425:2: () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5425:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5426:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKGridPlacement11754); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getGridPlacementKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5435:1: ( (lv_numColumns_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5436:1: (lv_numColumns_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5436:1: (lv_numColumns_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5437:3: lv_numColumns_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement11775);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5461:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5462:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5463:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11811);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement11821); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5470:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'StackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5473:28: ( ( () otherlv_1= 'StackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5474:1: ( () otherlv_1= 'StackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5474:1: ( () otherlv_1= 'StackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5474:2: () otherlv_1= 'StackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5474:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5475:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKStackPlacement11867); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementAccess().getStackPlacementKeyword_1());
                

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


    // $ANTLR start "entryRuleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5492:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5493:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5494:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat11904);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat11915); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5501:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5504:28: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5505:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5505:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5505:2: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5505:2: (kw= '-' )?
            int alt204=2;
            int LA204_0 = input.LA(1);

            if ( (LA204_0==37) ) {
                alt204=1;
            }
            switch (alt204) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5506:2: kw= '-'
                    {
                    kw=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleEFloat11954); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11971); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5518:1: (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            int alt208=2;
            int LA208_0 = input.LA(1);

            if ( (LA208_0==87) ) {
                alt208=1;
            }
            switch (alt208) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5519:2: kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    {
                    kw=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleEFloat11990); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2_0()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat12005); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_2_1()); 
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5531:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    int alt207=2;
                    int LA207_0 = input.LA(1);

                    if ( ((LA207_0>=88 && LA207_0<=89)) ) {
                        alt207=1;
                    }
                    switch (alt207) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5531:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5531:2: (kw= 'E' | kw= 'e' )
                            int alt205=2;
                            int LA205_0 = input.LA(1);

                            if ( (LA205_0==88) ) {
                                alt205=1;
                            }
                            else if ( (LA205_0==89) ) {
                                alt205=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 205, 0, input);

                                throw nvae;
                            }
                            switch (alt205) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5532:2: kw= 'E'
                                    {
                                    kw=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleEFloat12025); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_0()); 
                                        

                                    }
                                    break;
                                case 2 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5539:2: kw= 'e'
                                    {
                                    kw=(Token)match(input,89,FollowSets000.FOLLOW_89_in_ruleEFloat12044); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_1()); 
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5544:2: (kw= '-' )?
                            int alt206=2;
                            int LA206_0 = input.LA(1);

                            if ( (LA206_0==37) ) {
                                alt206=1;
                            }
                            switch (alt206) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5545:2: kw= '-'
                                    {
                                    kw=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleEFloat12059); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_2_2_1()); 
                                        

                                    }
                                    break;

                            }

                            this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat12076); 

                            		current.merge(this_INT_7);
                                
                             
                                newLeafNode(this_INT_7, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_2_2_2()); 
                                

                            }
                            break;

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
    // $ANTLR end "ruleEFloat"


    // $ANTLR start "entryRuleEBoolean"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5565:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5566:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5567:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             newCompositeNode(grammarAccess.getEBooleanRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean12126);
            iv_ruleEBoolean=ruleEBoolean();

            state._fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean12137); 

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
    // $ANTLR end "entryRuleEBoolean"


    // $ANTLR start "ruleEBoolean"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5574:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5577:28: ( (kw= 'true' | kw= 'false' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5578:1: (kw= 'true' | kw= 'false' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5578:1: (kw= 'true' | kw= 'false' )
            int alt209=2;
            int LA209_0 = input.LA(1);

            if ( (LA209_0==90) ) {
                alt209=1;
            }
            else if ( (LA209_0==91) ) {
                alt209=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 209, 0, input);

                throw nvae;
            }
            switch (alt209) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5579:2: kw= 'true'
                    {
                    kw=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleEBoolean12175); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEBooleanAccess().getTrueKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5586:2: kw= 'false'
                    {
                    kw=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleEBoolean12194); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEBooleanAccess().getFalseKeyword_1()); 
                        

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
    // $ANTLR end "ruleEBoolean"


    // $ANTLR start "entryRuleEInt"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5599:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5600:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5601:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt12235);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt12246); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5608:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5611:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5612:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5612:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5612:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5612:2: (kw= '-' )?
            int alt210=2;
            int LA210_0 = input.LA(1);

            if ( (LA210_0==37) ) {
                alt210=1;
            }
            switch (alt210) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5613:2: kw= '-'
                    {
                    kw=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleEInt12285); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt12302); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5633:1: entryRuleKShapeLayout returns [EObject current=null] : iv_ruleKShapeLayout= ruleKShapeLayout EOF ;
    public final EObject entryRuleKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShapeLayout = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5634:2: (iv_ruleKShapeLayout= ruleKShapeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5635:2: iv_ruleKShapeLayout= ruleKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getKShapeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout12347);
            iv_ruleKShapeLayout=ruleKShapeLayout();

            state._fsp--;

             current =iv_ruleKShapeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKShapeLayout12357); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5642:1: ruleKShapeLayout returns [EObject current=null] : ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5645:28: ( ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5646:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5646:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5646:2: () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )? otherlv_18= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5646:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5647:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleKShapeLayout12403); 

                	newLeafNode(otherlv_1, grammarAccess.getKShapeLayoutAccess().getKShapeLayoutKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKShapeLayout12415); 

                	newLeafNode(otherlv_2, grammarAccess.getKShapeLayoutAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5660:1: (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )?
            int alt211=2;
            int LA211_0 = input.LA(1);

            if ( (LA211_0==93) ) {
                alt211=1;
            }
            switch (alt211) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5660:3: otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleKShapeLayout12428); 

                        	newLeafNode(otherlv_3, grammarAccess.getKShapeLayoutAccess().getXposKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5664:1: ( (lv_xpos_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5665:1: (lv_xpos_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5665:1: (lv_xpos_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5666:3: lv_xpos_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getXposEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12449);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5682:4: (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )?
            int alt212=2;
            int LA212_0 = input.LA(1);

            if ( (LA212_0==94) ) {
                alt212=1;
            }
            switch (alt212) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5682:6: otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleKShapeLayout12464); 

                        	newLeafNode(otherlv_5, grammarAccess.getKShapeLayoutAccess().getYposKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5686:1: ( (lv_ypos_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5687:1: (lv_ypos_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5687:1: (lv_ypos_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5688:3: lv_ypos_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getYposEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12485);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5704:4: (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )?
            int alt213=2;
            int LA213_0 = input.LA(1);

            if ( (LA213_0==51) ) {
                alt213=1;
            }
            switch (alt213) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5704:6: otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKShapeLayout12500); 

                        	newLeafNode(otherlv_7, grammarAccess.getKShapeLayoutAccess().getWidthKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5708:1: ( (lv_width_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5709:1: (lv_width_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5709:1: (lv_width_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5710:3: lv_width_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getWidthEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12521);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5726:4: (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )?
            int alt214=2;
            int LA214_0 = input.LA(1);

            if ( (LA214_0==52) ) {
                alt214=1;
            }
            switch (alt214) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5726:6: otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKShapeLayout12536); 

                        	newLeafNode(otherlv_9, grammarAccess.getKShapeLayoutAccess().getHeightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5730:1: ( (lv_height_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5731:1: (lv_height_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5731:1: (lv_height_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5732:3: lv_height_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getHeightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout12557);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5748:4: (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )?
            int alt215=2;
            int LA215_0 = input.LA(1);

            if ( (LA215_0==95) ) {
                alt215=1;
            }
            switch (alt215) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5748:6: otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) )
                    {
                    otherlv_11=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleKShapeLayout12572); 

                        	newLeafNode(otherlv_11, grammarAccess.getKShapeLayoutAccess().getInsetsKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5752:1: ( (lv_insets_12_0= ruleKInsets ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5753:1: (lv_insets_12_0= ruleKInsets )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5753:1: (lv_insets_12_0= ruleKInsets )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5754:3: lv_insets_12_0= ruleKInsets
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getInsetsKInsetsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_ruleKShapeLayout12593);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5770:4: (otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )* )?
            int alt218=2;
            int LA218_0 = input.LA(1);

            if ( (LA218_0==96) ) {
                alt218=1;
            }
            switch (alt218) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5770:6: otherlv_13= 'mapProperties' otherlv_14= ':' ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )*
                    {
                    otherlv_13=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleKShapeLayout12608); 

                        	newLeafNode(otherlv_13, grammarAccess.getKShapeLayoutAccess().getMapPropertiesKeyword_8_0());
                        
                    otherlv_14=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKShapeLayout12620); 

                        	newLeafNode(otherlv_14, grammarAccess.getKShapeLayoutAccess().getColonKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5778:1: ( (lv_persistentEntries_15_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5779:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5779:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5780:3: lv_persistentEntries_15_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12641);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5796:2: ( (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) ) )*
                    loop217:
                    do {
                        int alt217=2;
                        int LA217_0 = input.LA(1);

                        if ( ((LA217_0>=RULE_STRING && LA217_0<=RULE_ID)||LA217_0==14) ) {
                            alt217=1;
                        }


                        switch (alt217) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5796:3: (otherlv_16= ',' )? ( (lv_persistentEntries_17_0= rulePersistentEntry ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5796:3: (otherlv_16= ',' )?
                    	    int alt216=2;
                    	    int LA216_0 = input.LA(1);

                    	    if ( (LA216_0==14) ) {
                    	        alt216=1;
                    	    }
                    	    switch (alt216) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5796:5: otherlv_16= ','
                    	            {
                    	            otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKShapeLayout12655); 

                    	                	newLeafNode(otherlv_16, grammarAccess.getKShapeLayoutAccess().getCommaKeyword_8_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5800:3: ( (lv_persistentEntries_17_0= rulePersistentEntry ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5801:1: (lv_persistentEntries_17_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5801:1: (lv_persistentEntries_17_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5802:3: lv_persistentEntries_17_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12678);
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
                    	    break loop217;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKShapeLayout12694); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5830:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5831:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5832:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets12730);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets12740); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5839:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5842:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5843:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5843:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5843:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5843:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5844:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleKInsets12786); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets12798); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5857:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt219=2;
            int LA219_0 = input.LA(1);

            if ( (LA219_0==70) ) {
                alt219=1;
            }
            switch (alt219) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5857:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKInsets12811); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5861:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5862:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5862:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5863:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12832);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5879:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt220=2;
            int LA220_0 = input.LA(1);

            if ( (LA220_0==71) ) {
                alt220=1;
            }
            switch (alt220) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5879:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKInsets12847); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5883:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5884:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5884:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5885:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12868);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5901:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt221=2;
            int LA221_0 = input.LA(1);

            if ( (LA221_0==68) ) {
                alt221=1;
            }
            switch (alt221) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5901:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKInsets12883); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5905:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5906:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5906:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5907:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12904);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5923:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt222=2;
            int LA222_0 = input.LA(1);

            if ( (LA222_0==69) ) {
                alt222=1;
            }
            switch (alt222) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5923:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKInsets12919); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5927:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5928:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5928:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5929:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets12940);
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

            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKInsets12954); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5957:1: entryRuleKEdgeLayout returns [EObject current=null] : iv_ruleKEdgeLayout= ruleKEdgeLayout EOF ;
    public final EObject entryRuleKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdgeLayout = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5958:2: (iv_ruleKEdgeLayout= ruleKEdgeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5959:2: iv_ruleKEdgeLayout= ruleKEdgeLayout EOF
            {
             newCompositeNode(grammarAccess.getKEdgeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEdgeLayout_in_entryRuleKEdgeLayout12990);
            iv_ruleKEdgeLayout=ruleKEdgeLayout();

            state._fsp--;

             current =iv_ruleKEdgeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEdgeLayout13000); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5966:1: ruleKEdgeLayout returns [EObject current=null] : (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5969:28: ( (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5970:1: (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5970:1: (otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5970:3: otherlv_0= 'KEdgeLayout' otherlv_1= '{' otherlv_2= 'sourcePoint' ( (lv_sourcePoint_3_0= ruleKPoint ) ) otherlv_4= 'targetPoint' ( (lv_targetPoint_5_0= ruleKPoint ) ) (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )? (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )? otherlv_16= '}'
            {
            otherlv_0=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleKEdgeLayout13037); 

                	newLeafNode(otherlv_0, grammarAccess.getKEdgeLayoutAccess().getKEdgeLayoutKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdgeLayout13049); 

                	newLeafNode(otherlv_1, grammarAccess.getKEdgeLayoutAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleKEdgeLayout13061); 

                	newLeafNode(otherlv_2, grammarAccess.getKEdgeLayoutAccess().getSourcePointKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5982:1: ( (lv_sourcePoint_3_0= ruleKPoint ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5983:1: (lv_sourcePoint_3_0= ruleKPoint )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5983:1: (lv_sourcePoint_3_0= ruleKPoint )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5984:3: lv_sourcePoint_3_0= ruleKPoint
            {
             
            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointKPointParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13082);
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

            otherlv_4=(Token)match(input,100,FollowSets000.FOLLOW_100_in_ruleKEdgeLayout13094); 

                	newLeafNode(otherlv_4, grammarAccess.getKEdgeLayoutAccess().getTargetPointKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6004:1: ( (lv_targetPoint_5_0= ruleKPoint ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6005:1: (lv_targetPoint_5_0= ruleKPoint )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6005:1: (lv_targetPoint_5_0= ruleKPoint )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6006:3: lv_targetPoint_5_0= ruleKPoint
            {
             
            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointKPointParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13115);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6022:2: (otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )* )?
            int alt225=2;
            int LA225_0 = input.LA(1);

            if ( (LA225_0==101) ) {
                alt225=1;
            }
            switch (alt225) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6022:4: otherlv_6= 'bendPoints' otherlv_7= ':' ( (lv_bendPoints_8_0= ruleKPoint ) ) ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )*
                    {
                    otherlv_6=(Token)match(input,101,FollowSets000.FOLLOW_101_in_ruleKEdgeLayout13128); 

                        	newLeafNode(otherlv_6, grammarAccess.getKEdgeLayoutAccess().getBendPointsKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdgeLayout13140); 

                        	newLeafNode(otherlv_7, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6030:1: ( (lv_bendPoints_8_0= ruleKPoint ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6031:1: (lv_bendPoints_8_0= ruleKPoint )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6031:1: (lv_bendPoints_8_0= ruleKPoint )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6032:3: lv_bendPoints_8_0= ruleKPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13161);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6048:2: ( (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) ) )*
                    loop224:
                    do {
                        int alt224=2;
                        int LA224_0 = input.LA(1);

                        if ( (LA224_0==14||LA224_0==102) ) {
                            alt224=1;
                        }


                        switch (alt224) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6048:3: (otherlv_9= ',' )? ( (lv_bendPoints_10_0= ruleKPoint ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6048:3: (otherlv_9= ',' )?
                    	    int alt223=2;
                    	    int LA223_0 = input.LA(1);

                    	    if ( (LA223_0==14) ) {
                    	        alt223=1;
                    	    }
                    	    switch (alt223) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6048:5: otherlv_9= ','
                    	            {
                    	            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdgeLayout13175); 

                    	                	newLeafNode(otherlv_9, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_6_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6052:3: ( (lv_bendPoints_10_0= ruleKPoint ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6053:1: (lv_bendPoints_10_0= ruleKPoint )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6053:1: (lv_bendPoints_10_0= ruleKPoint )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6054:3: lv_bendPoints_10_0= ruleKPoint
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13198);
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
                    	    break loop224;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6070:6: (otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )* )?
            int alt228=2;
            int LA228_0 = input.LA(1);

            if ( (LA228_0==96) ) {
                alt228=1;
            }
            switch (alt228) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6070:8: otherlv_11= 'mapProperties' otherlv_12= ':' ( (lv_persistentEntries_13_0= rulePersistentEntry ) ) ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )*
                    {
                    otherlv_11=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleKEdgeLayout13215); 

                        	newLeafNode(otherlv_11, grammarAccess.getKEdgeLayoutAccess().getMapPropertiesKeyword_7_0());
                        
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEdgeLayout13227); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEdgeLayoutAccess().getColonKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6078:1: ( (lv_persistentEntries_13_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6079:1: (lv_persistentEntries_13_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6079:1: (lv_persistentEntries_13_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6080:3: lv_persistentEntries_13_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13248);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6096:2: ( (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) ) )*
                    loop227:
                    do {
                        int alt227=2;
                        int LA227_0 = input.LA(1);

                        if ( ((LA227_0>=RULE_STRING && LA227_0<=RULE_ID)||LA227_0==14) ) {
                            alt227=1;
                        }


                        switch (alt227) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6096:3: (otherlv_14= ',' )? ( (lv_persistentEntries_15_0= rulePersistentEntry ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6096:3: (otherlv_14= ',' )?
                    	    int alt226=2;
                    	    int LA226_0 = input.LA(1);

                    	    if ( (LA226_0==14) ) {
                    	        alt226=1;
                    	    }
                    	    switch (alt226) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6096:5: otherlv_14= ','
                    	            {
                    	            otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEdgeLayout13262); 

                    	                	newLeafNode(otherlv_14, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_7_3_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6100:3: ( (lv_persistentEntries_15_0= rulePersistentEntry ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6101:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6101:1: (lv_persistentEntries_15_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6102:3: lv_persistentEntries_15_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13285);
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
                    	    break loop227;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdgeLayout13301); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6130:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6131:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6132:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint13337);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint13347); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6139:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_x_3_0 = null;

        AntlrDatatypeRuleToken lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6142:28: ( ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6143:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6143:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6143:2: () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6143:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6144:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,102,FollowSets000.FOLLOW_102_in_ruleKPoint13393); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6153:1: (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6153:3: otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) )
            {
            otherlv_2=(Token)match(input,103,FollowSets000.FOLLOW_103_in_ruleKPoint13406); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getXKeyword_2_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6157:1: ( (lv_x_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6158:1: (lv_x_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6158:1: (lv_x_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6159:3: lv_x_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint13427);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6175:3: (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6175:5: otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) )
            {
            otherlv_4=(Token)match(input,104,FollowSets000.FOLLOW_104_in_ruleKPoint13441); 

                	newLeafNode(otherlv_4, grammarAccess.getKPointAccess().getYKeyword_3_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6179:1: ( (lv_y_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6180:1: (lv_y_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6180:1: (lv_y_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6181:3: lv_y_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint13462);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6205:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6206:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6207:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry13499);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry13509); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6214:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6217:28: ( ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6218:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6218:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6218:2: ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6218:2: ( (lv_key_0_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6219:1: (lv_key_0_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6219:1: (lv_key_0_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6220:3: lv_key_0_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry13555);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6236:2: (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            int alt229=2;
            int LA229_0 = input.LA(1);

            if ( (LA229_0==105) ) {
                alt229=1;
            }
            switch (alt229) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6236:4: otherlv_1= '=' ( (lv_value_2_0= ruleEString ) )
                    {
                    otherlv_1=(Token)match(input,105,FollowSets000.FOLLOW_105_in_rulePersistentEntry13568); 

                        	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6240:1: ( (lv_value_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6241:1: (lv_value_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6241:1: (lv_value_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6242:3: lv_value_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry13589);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6266:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6267:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6268:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString13628);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString13639); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6275:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6278:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6279:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6279:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt230=2;
            int LA230_0 = input.LA(1);

            if ( (LA230_0==RULE_STRING) ) {
                alt230=1;
            }
            else if ( (LA230_0==RULE_ID) ) {
                alt230=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 230, 0, input);

                throw nvae;
            }
            switch (alt230) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6279:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString13679); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6287:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString13705); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6302:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6304:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6305:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6305:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt231=5;
            switch ( input.LA(1) ) {
            case 106:
                {
                alt231=1;
                }
                break;
            case 107:
                {
                alt231=2;
                }
                break;
            case 108:
                {
                alt231=3;
                }
                break;
            case 109:
                {
                alt231=4;
                }
                break;
            case 110:
                {
                alt231=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 231, 0, input);

                throw nvae;
            }

            switch (alt231) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6305:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6305:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6305:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,106,FollowSets000.FOLLOW_106_in_ruleLineStyle13764); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6311:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6311:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6311:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,107,FollowSets000.FOLLOW_107_in_ruleLineStyle13781); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6317:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6317:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6317:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,108,FollowSets000.FOLLOW_108_in_ruleLineStyle13798); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6323:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6323:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6323:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,109,FollowSets000.FOLLOW_109_in_ruleLineStyle13815); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6329:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6329:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6329:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,110,FollowSets000.FOLLOW_110_in_ruleLineStyle13832); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6339:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6341:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6342:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6342:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt232=3;
            switch ( input.LA(1) ) {
            case 111:
                {
                alt232=1;
                }
                break;
            case 112:
                {
                alt232=2;
                }
                break;
            case 113:
                {
                alt232=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 232, 0, input);

                throw nvae;
            }

            switch (alt232) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6342:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6342:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6342:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,111,FollowSets000.FOLLOW_111_in_ruleVerticalAlignment13877); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6348:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6348:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6348:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,112,FollowSets000.FOLLOW_112_in_ruleVerticalAlignment13894); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6354:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6354:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6354:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,113,FollowSets000.FOLLOW_113_in_ruleVerticalAlignment13911); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6364:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6366:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6367:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6367:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt233=3;
            switch ( input.LA(1) ) {
            case 114:
                {
                alt233=1;
                }
                break;
            case 112:
                {
                alt233=2;
                }
                break;
            case 115:
                {
                alt233=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 233, 0, input);

                throw nvae;
            }

            switch (alt233) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6367:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6367:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6367:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,114,FollowSets000.FOLLOW_114_in_ruleHorizontalAlignment13956); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6373:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6373:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6373:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,112,FollowSets000.FOLLOW_112_in_ruleHorizontalAlignment13973); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6379:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6379:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6379:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,115,FollowSets000.FOLLOW_115_in_ruleHorizontalAlignment13990); 

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
        public static final BitSet FOLLOW_ruleKPort_in_ruleKNode177 = new BitSet(new long[]{0x000000000016C000L});
        public static final BitSet FOLLOW_14_in_ruleKNode191 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_ruleKPort_in_ruleKNode214 = new BitSet(new long[]{0x000000000016C000L});
        public static final BitSet FOLLOW_15_in_ruleKNode231 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_16_in_ruleKNode244 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKNode267 = new BitSet(new long[]{0x000025DF4C074000L,0x0000000410000000L});
        public static final BitSet FOLLOW_14_in_ruleKNode281 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKNode304 = new BitSet(new long[]{0x000025DF4C074000L,0x0000000410000000L});
        public static final BitSet FOLLOW_17_in_ruleKNode321 = new BitSet(new long[]{0x0000000000010800L});
        public static final BitSet FOLLOW_16_in_ruleKNode334 = new BitSet(new long[]{0x0000000000010800L});
        public static final BitSet FOLLOW_ruleKNode_in_ruleKNode357 = new BitSet(new long[]{0x0000000000054800L});
        public static final BitSet FOLLOW_14_in_ruleKNode371 = new BitSet(new long[]{0x0000000000010800L});
        public static final BitSet FOLLOW_ruleKNode_in_ruleKNode394 = new BitSet(new long[]{0x0000000000054800L});
        public static final BitSet FOLLOW_18_in_ruleKNode410 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_ruleKEdge_in_ruleKNode432 = new BitSet(new long[]{0x0000000000804002L});
        public static final BitSet FOLLOW_14_in_ruleKNode446 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKEdge_in_ruleKNode469 = new BitSet(new long[]{0x0000000000804002L});
        public static final BitSet FOLLOW_ruleKLabel_in_entryRuleKLabel509 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLabel519 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_ruleKLabel556 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKLabel577 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLabel589 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKLabel602 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_16_in_ruleKLabel615 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKLabel638 = new BitSet(new long[]{0x000025DF4C054000L,0x0000000410000000L});
        public static final BitSet FOLLOW_14_in_ruleKLabel652 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKLabel675 = new BitSet(new long[]{0x000025DF4C054000L,0x0000000410000000L});
        public static final BitSet FOLLOW_18_in_ruleKLabel691 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPort_in_entryRuleKPort727 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPort737 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_20_in_ruleKPort783 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPort795 = new BitSet(new long[]{0x0000000000648000L});
        public static final BitSet FOLLOW_21_in_ruleKPort808 = new BitSet(new long[]{0x0000000000010060L});
        public static final BitSet FOLLOW_16_in_ruleKPort821 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPort846 = new BitSet(new long[]{0x000000000044C060L});
        public static final BitSet FOLLOW_14_in_ruleKPort860 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPort885 = new BitSet(new long[]{0x000000000044C060L});
        public static final BitSet FOLLOW_15_in_ruleKPort902 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_16_in_ruleKPort915 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKPort938 = new BitSet(new long[]{0x000025DF4C454000L,0x0000000410000000L});
        public static final BitSet FOLLOW_14_in_ruleKPort952 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKPort975 = new BitSet(new long[]{0x000025DF4C454000L,0x0000000410000000L});
        public static final BitSet FOLLOW_22_in_ruleKPort992 = new BitSet(new long[]{0x0000000000090000L});
        public static final BitSet FOLLOW_16_in_ruleKPort1005 = new BitSet(new long[]{0x0000000000090000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKPort1028 = new BitSet(new long[]{0x00000000000D4000L});
        public static final BitSet FOLLOW_14_in_ruleKPort1042 = new BitSet(new long[]{0x0000000000090000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKPort1065 = new BitSet(new long[]{0x00000000000D4000L});
        public static final BitSet FOLLOW_18_in_ruleKPort1081 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdge_in_entryRuleKEdge1117 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEdge1127 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKEdge1164 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1187 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdge1199 = new BitSet(new long[]{0x0000000003448000L});
        public static final BitSet FOLLOW_24_in_ruleKEdge1212 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1235 = new BitSet(new long[]{0x0000000002448000L});
        public static final BitSet FOLLOW_25_in_ruleKEdge1250 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1273 = new BitSet(new long[]{0x0000000000448000L});
        public static final BitSet FOLLOW_15_in_ruleKEdge1288 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_16_in_ruleKEdge1301 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKEdge1324 = new BitSet(new long[]{0x000025DF4C454000L,0x0000000410000000L});
        public static final BitSet FOLLOW_14_in_ruleKEdge1338 = new BitSet(new long[]{0x000025DF4C010000L,0x0000000410000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKEdge1361 = new BitSet(new long[]{0x000025DF4C454000L,0x0000000410000000L});
        public static final BitSet FOLLOW_22_in_ruleKEdge1378 = new BitSet(new long[]{0x0000000000090000L});
        public static final BitSet FOLLOW_16_in_ruleKEdge1391 = new BitSet(new long[]{0x0000000000090000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKEdge1414 = new BitSet(new long[]{0x00000000000D4000L});
        public static final BitSet FOLLOW_14_in_ruleKEdge1428 = new BitSet(new long[]{0x0000000000090000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKEdge1451 = new BitSet(new long[]{0x00000000000D4000L});
        public static final BitSet FOLLOW_18_in_ruleKEdge1467 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGraphData_in_entryRuleKGraphData1503 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGraphData1513 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKGraphData1560 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingLibrary_in_ruleKGraphData1587 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKShapeLayout_in_ruleKGraphData1614 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdgeLayout_in_ruleKGraphData1641 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingLibrary_in_entryRuleKRenderingLibrary1676 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingLibrary1686 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_ruleKRenderingLibrary1732 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary1744 = new BitSet(new long[]{0x000025DF48040000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary1766 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingLibrary1780 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary1803 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingLibrary1819 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_entryRuleKRendering1855 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRendering1865 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_ruleKRendering1912 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_ruleKRendering1939 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_ruleKRendering1966 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_ruleKRendering1993 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_ruleKRendering2020 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_ruleKRendering2047 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_ruleKRendering2074 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_ruleKRendering2101 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_ruleKRendering2128 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_ruleKRendering2155 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_ruleKRendering2182 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_ruleKRendering2209 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData2244 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacementData2254 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData2301 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData2328 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData2355 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData2382 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData2409 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle2444 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle2454 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_ruleKStyle2501 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_ruleKStyle2528 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle2555 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle2582 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle2609 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_ruleKStyle2636 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_ruleKStyle2663 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_ruleKStyle2690 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_ruleKStyle2717 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle2744 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle2771 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement2806 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement2816 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement2863 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_ruleKPlacement2890 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition2925 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition2935 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition2982 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition3009 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition3044 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition3054 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition3101 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition3128 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef3163 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef3173 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleKRenderingRef3219 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef3242 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef3255 = new BitSet(new long[]{0x0000000030040000L});
        public static final BitSet FOLLOW_28_in_ruleKRenderingRef3268 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef3289 = new BitSet(new long[]{0x0000000020040000L});
        public static final BitSet FOLLOW_29_in_ruleKRenderingRef3304 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef3316 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef3337 = new BitSet(new long[]{0x0000000000044000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef3351 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef3374 = new BitSet(new long[]{0x0000000000044000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef3388 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef3402 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse3440 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse3450 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_ruleKEllipse3496 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse3509 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKEllipse3522 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3535 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse3558 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse3572 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse3595 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKEllipse3612 = new BitSet(new long[]{0x3020400000010000L,0x0000000000000001L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3625 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse3648 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_31_in_ruleKEllipse3663 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3676 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse3699 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse3714 = new BitSet(new long[]{0x000025DF48010000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3727 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse3750 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse3764 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse3787 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse3803 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle3841 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle3851 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_32_in_ruleKRectangle3897 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle3910 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKRectangle3923 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle3936 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle3959 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle3973 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle3996 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKRectangle4013 = new BitSet(new long[]{0x3020400000010000L,0x0000000000000001L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle4026 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle4049 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_31_in_ruleKRectangle4064 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle4077 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle4100 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle4115 = new BitSet(new long[]{0x000025DF48010000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle4128 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle4151 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle4165 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle4188 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKRectangle4204 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle4242 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle4252 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_33_in_ruleKRoundedRectangle4298 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4319 = new BitSet(new long[]{0x0000002000004010L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle4332 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4355 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle4368 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKRoundedRectangle4381 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4394 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4417 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle4431 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle4454 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKRoundedRectangle4471 = new BitSet(new long[]{0x3020400000010000L,0x0000000000000001L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4484 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle4507 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_31_in_ruleKRoundedRectangle4522 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4535 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle4558 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle4573 = new BitSet(new long[]{0x000025DF48010000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4586 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4609 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle4623 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle4646 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle4662 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl4700 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl4710 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_ruleKPolyline_Impl4756 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl4769 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKPolyline_Impl4782 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4795 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4818 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl4832 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl4855 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKPolyline_Impl4872 = new BitSet(new long[]{0x3020400000010000L,0x0000000000000001L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4885 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl4908 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_31_in_ruleKPolyline_Impl4923 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4936 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl4959 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl4974 = new BitSet(new long[]{0x000025DF48010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl4987 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5010 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl5024 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5047 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl5063 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon5101 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon5111 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_35_in_ruleKPolygon5157 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon5170 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKPolygon5183 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5196 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon5219 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon5233 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon5256 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKPolygon5273 = new BitSet(new long[]{0x3020400000010000L,0x0000000000000001L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5286 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon5309 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_31_in_ruleKPolygon5324 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5337 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon5360 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon5375 = new BitSet(new long[]{0x000025DF48010000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5388 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon5411 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon5425 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon5448 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKPolygon5464 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage5502 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage5512 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKImage5558 = new BitSet(new long[]{0x0000002000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage5580 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_37_in_ruleKImage5598 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage5620 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKImage5633 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKImage5646 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKImage5659 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage5682 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKImage5696 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage5719 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKImage5736 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage5757 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_17_in_ruleKImage5772 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage5784 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage5805 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKImage5819 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage5842 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKImage5856 = new BitSet(new long[]{0x0000000080040000L});
        public static final BitSet FOLLOW_31_in_ruleKImage5871 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage5892 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKImage5906 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc5944 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc5954 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleKArc6000 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc6021 = new BitSet(new long[]{0x0000002000004010L});
        public static final BitSet FOLLOW_14_in_ruleKArc6034 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc6057 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKArc6070 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKArc6083 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKArc6096 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc6119 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKArc6133 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc6156 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKArc6173 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc6194 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_17_in_ruleKArc6209 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc6221 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc6242 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKArc6256 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc6279 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKArc6293 = new BitSet(new long[]{0x0000000080040000L});
        public static final BitSet FOLLOW_31_in_ruleKArc6308 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc6329 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKArc6343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea6381 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea6391 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_39_in_ruleKChildArea6437 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea6450 = new BitSet(new long[]{0x0000000030040000L});
        public static final BitSet FOLLOW_29_in_ruleKChildArea6463 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKChildArea6476 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea6499 = new BitSet(new long[]{0x0000000010044000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea6513 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea6536 = new BitSet(new long[]{0x0000000010044000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKChildArea6553 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea6574 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea6588 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText6626 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText6636 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_40_in_ruleKText6682 = new BitSet(new long[]{0x0000000000001062L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText6703 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKText6717 = new BitSet(new long[]{0x00000200B0060000L});
        public static final BitSet FOLLOW_41_in_ruleKText6735 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKText6762 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKText6775 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText6798 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKText6812 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText6835 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKText6852 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText6873 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_17_in_ruleKText6888 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText6900 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText6921 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKText6935 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText6958 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKText6972 = new BitSet(new long[]{0x0000000080040000L});
        public static final BitSet FOLLOW_31_in_ruleKText6987 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText7008 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKText7022 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering7060 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering7070 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_42_in_ruleKCustomRendering7116 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering7129 = new BitSet(new long[]{0x0000080000000000L});
        public static final BitSet FOLLOW_43_in_ruleKCustomRendering7141 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering7162 = new BitSet(new long[]{0x0000100000000000L});
        public static final BitSet FOLLOW_44_in_ruleKCustomRendering7174 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering7195 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKCustomRendering7208 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKCustomRendering7221 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering7244 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering7258 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering7281 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKCustomRendering7298 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering7319 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering7334 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering7346 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering7367 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering7381 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering7404 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering7418 = new BitSet(new long[]{0x0000000080040000L});
        public static final BitSet FOLLOW_31_in_ruleKCustomRendering7433 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering7454 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering7468 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline7506 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline7516 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_45_in_ruleKSpline7562 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKSpline7575 = new BitSet(new long[]{0x00000000B0060000L});
        public static final BitSet FOLLOW_29_in_ruleKSpline7588 = new BitSet(new long[]{0x0000000000010000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_16_in_ruleKSpline7601 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline7624 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7638 = new BitSet(new long[]{0x0000000000000000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline7661 = new BitSet(new long[]{0x0000000090064000L,0x00000000001FFD00L});
        public static final BitSet FOLLOW_28_in_ruleKSpline7678 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline7699 = new BitSet(new long[]{0x0000000080060000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline7714 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline7726 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline7747 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7761 = new BitSet(new long[]{0x000025DF48000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline7784 = new BitSet(new long[]{0x000025DF48044000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline7798 = new BitSet(new long[]{0x0000000080040000L});
        public static final BitSet FOLLOW_31_in_ruleKSpline7813 = new BitSet(new long[]{0x0000000000010000L,0x0000000000600000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline7834 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline7848 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData7886 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData7896 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_46_in_ruleKDecoratorPlacementData7933 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData7945 = new BitSet(new long[]{0x0001800000000000L});
        public static final BitSet FOLLOW_47_in_ruleKDecoratorPlacementData7963 = new BitSet(new long[]{0x0001000000000000L});
        public static final BitSet FOLLOW_48_in_ruleKDecoratorPlacementData7989 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8010 = new BitSet(new long[]{0x001E000000040000L});
        public static final BitSet FOLLOW_49_in_ruleKDecoratorPlacementData8023 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8044 = new BitSet(new long[]{0x001C000000040000L});
        public static final BitSet FOLLOW_50_in_ruleKDecoratorPlacementData8059 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8080 = new BitSet(new long[]{0x0018000000040000L});
        public static final BitSet FOLLOW_51_in_ruleKDecoratorPlacementData8095 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8116 = new BitSet(new long[]{0x0010000000040000L});
        public static final BitSet FOLLOW_52_in_ruleKDecoratorPlacementData8131 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData8152 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKDecoratorPlacementData8166 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData8202 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData8212 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleKGridPlacementData8249 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData8261 = new BitSet(new long[]{0x0040000000000000L});
        public static final BitSet FOLLOW_54_in_ruleKGridPlacementData8273 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8294 = new BitSet(new long[]{0x0080000000000000L});
        public static final BitSet FOLLOW_55_in_ruleKGridPlacementData8306 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8327 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_56_in_ruleKGridPlacementData8339 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8360 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKGridPlacementData8372 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8393 = new BitSet(new long[]{0x0400000000000000L});
        public static final BitSet FOLLOW_58_in_ruleKGridPlacementData8405 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8426 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_59_in_ruleKGridPlacementData8438 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8459 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKGridPlacementData8471 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData8507 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData8517 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_60_in_ruleKStackPlacementData8554 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData8566 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_56_in_ruleKStackPlacementData8578 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8599 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKStackPlacementData8611 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8632 = new BitSet(new long[]{0x0400000000000000L});
        public static final BitSet FOLLOW_58_in_ruleKStackPlacementData8644 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8665 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_59_in_ruleKStackPlacementData8677 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8698 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKStackPlacementData8710 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData8746 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData8756 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_61_in_ruleKDirectPlacementData8793 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData8805 = new BitSet(new long[]{0x4000000000000000L});
        public static final BitSet FOLLOW_62_in_ruleKDirectPlacementData8817 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000030L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8838 = new BitSet(new long[]{0x8000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDirectPlacementData8851 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_63_in_ruleKDirectPlacementData8865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000030L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8886 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKDirectPlacementData8898 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData8934 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData8944 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_64_in_ruleKPolylinePlacementData8981 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData8993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKPolylinePlacementData9005 = new BitSet(new long[]{0x0000000000010000L,0x0000000000000030L});
        public static final BitSet FOLLOW_16_in_ruleKPolylinePlacementData9018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000030L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9041 = new BitSet(new long[]{0x0000000000044000L,0x0000000000000034L});
        public static final BitSet FOLLOW_14_in_ruleKPolylinePlacementData9055 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000030L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData9078 = new BitSet(new long[]{0x0000000000044000L,0x0000000000000034L});
        public static final BitSet FOLLOW_66_in_ruleKPolylinePlacementData9093 = new BitSet(new long[]{0x3020400000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData9114 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPolylinePlacementData9128 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition9164 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition9174 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition9220 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
        public static final BitSet FOLLOW_67_in_ruleKPosition9232 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition9253 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition9289 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition9299 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_68_in_ruleKLeftPosition9345 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition9366 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition9387 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition9423 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition9433 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_69_in_ruleKRightPosition9479 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition9500 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition9521 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition9557 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition9567 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_70_in_ruleKTopPosition9613 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition9634 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition9655 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition9691 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition9701 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleKBottomPosition9747 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition9768 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition9789 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor9825 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor9835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_72_in_ruleKForegroundColor9881 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9902 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9923 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9944 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKForegroundColor9962 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor10012 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor10022 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_74_in_ruleKBackgroundColor10068 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10089 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10110 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10131 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKBackgroundColor10149 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth10199 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth10209 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleKLineWidth10246 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth10267 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKLineWidth10285 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility10335 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility10345 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility10392 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility10419 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility10454 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundVisibility10464 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKForegroundVisibility10510 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility10531 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKForegroundVisibility10549 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility10599 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundVisibility10609 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleKBackgroundVisibility10655 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility10676 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKBackgroundVisibility10694 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle10744 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle10754 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_78_in_ruleKLineStyle10800 = new BitSet(new long[]{0x0000000000000000L,0x00007C0000000000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle10821 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKLineStyle10839 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_entryRuleKFontBold10889 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontBold10899 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_79_in_ruleKFontBold10945 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKFontBold10963 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic11013 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontItalic11023 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_80_in_ruleKFontItalic11069 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKFontItalic11087 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_entryRuleKFontName11137 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontName11147 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_81_in_ruleKFontName11184 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKFontName11205 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKFontName11223 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_entryRuleKFontSize11273 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontSize11283 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleKFontSize11320 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKFontSize11341 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKFontSize11359 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment11409 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment11419 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_83_in_ruleKVerticalAlignment11465 = new BitSet(new long[]{0x0000000000000000L,0x0003800000000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment11486 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKVerticalAlignment11504 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment11554 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment11564 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_84_in_ruleKHorizontalAlignment11610 = new BitSet(new long[]{0x0000000000000000L,0x000D000000000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11631 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKHorizontalAlignment11649 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11698 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement11708 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_85_in_ruleKGridPlacement11754 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement11775 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11811 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement11821 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_86_in_ruleKStackPlacement11867 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat11904 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat11915 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_37_in_ruleEFloat11954 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11971 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
        public static final BitSet FOLLOW_87_in_ruleEFloat11990 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat12005 = new BitSet(new long[]{0x0000000000000002L,0x0000000003000000L});
        public static final BitSet FOLLOW_88_in_ruleEFloat12025 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_89_in_ruleEFloat12044 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_37_in_ruleEFloat12059 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat12076 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean12126 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean12137 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_90_in_ruleEBoolean12175 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_91_in_ruleEBoolean12194 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt12235 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt12246 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_37_in_ruleEInt12285 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt12302 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout12347 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKShapeLayout12357 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_92_in_ruleKShapeLayout12403 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKShapeLayout12415 = new BitSet(new long[]{0x0018000000040000L,0x00000001E0000000L});
        public static final BitSet FOLLOW_93_in_ruleKShapeLayout12428 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12449 = new BitSet(new long[]{0x0018000000040000L,0x00000001C0000000L});
        public static final BitSet FOLLOW_94_in_ruleKShapeLayout12464 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12485 = new BitSet(new long[]{0x0018000000040000L,0x0000000180000000L});
        public static final BitSet FOLLOW_51_in_ruleKShapeLayout12500 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12521 = new BitSet(new long[]{0x0010000000040000L,0x0000000180000000L});
        public static final BitSet FOLLOW_52_in_ruleKShapeLayout12536 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout12557 = new BitSet(new long[]{0x0000000000040000L,0x0000000180000000L});
        public static final BitSet FOLLOW_95_in_ruleKShapeLayout12572 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
        public static final BitSet FOLLOW_ruleKInsets_in_ruleKShapeLayout12593 = new BitSet(new long[]{0x0000000000040000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleKShapeLayout12608 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKShapeLayout12620 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12641 = new BitSet(new long[]{0x0000000000044060L});
        public static final BitSet FOLLOW_14_in_ruleKShapeLayout12655 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKShapeLayout12678 = new BitSet(new long[]{0x0000000000044060L});
        public static final BitSet FOLLOW_18_in_ruleKShapeLayout12694 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets12730 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets12740 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_97_in_ruleKInsets12786 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets12798 = new BitSet(new long[]{0x0000000000040000L,0x00000000000000F0L});
        public static final BitSet FOLLOW_70_in_ruleKInsets12811 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12832 = new BitSet(new long[]{0x0000000000040000L,0x00000000000000B0L});
        public static final BitSet FOLLOW_71_in_ruleKInsets12847 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12868 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000030L});
        public static final BitSet FOLLOW_68_in_ruleKInsets12883 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12904 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000020L});
        public static final BitSet FOLLOW_69_in_ruleKInsets12919 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets12940 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKInsets12954 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdgeLayout_in_entryRuleKEdgeLayout12990 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEdgeLayout13000 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleKEdgeLayout13037 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdgeLayout13049 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
        public static final BitSet FOLLOW_99_in_ruleKEdgeLayout13061 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13082 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_100_in_ruleKEdgeLayout13094 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13115 = new BitSet(new long[]{0x0000000000040000L,0x0000002100000000L});
        public static final BitSet FOLLOW_101_in_ruleKEdgeLayout13128 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEdgeLayout13140 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13161 = new BitSet(new long[]{0x0000000000044000L,0x0000004100000000L});
        public static final BitSet FOLLOW_14_in_ruleKEdgeLayout13175 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13198 = new BitSet(new long[]{0x0000000000044000L,0x0000004100000000L});
        public static final BitSet FOLLOW_96_in_ruleKEdgeLayout13215 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleKEdgeLayout13227 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13248 = new BitSet(new long[]{0x0000000000044060L});
        public static final BitSet FOLLOW_14_in_ruleKEdgeLayout13262 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKEdgeLayout13285 = new BitSet(new long[]{0x0000000000044060L});
        public static final BitSet FOLLOW_18_in_ruleKEdgeLayout13301 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint13337 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint13347 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_102_in_ruleKPoint13393 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
        public static final BitSet FOLLOW_103_in_ruleKPoint13406 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint13427 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
        public static final BitSet FOLLOW_104_in_ruleKPoint13441 = new BitSet(new long[]{0x0000002000000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint13462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry13499 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry13509 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry13555 = new BitSet(new long[]{0x0000000000000002L,0x0000020000000000L});
        public static final BitSet FOLLOW_105_in_rulePersistentEntry13568 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry13589 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString13628 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString13639 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString13679 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString13705 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_106_in_ruleLineStyle13764 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_107_in_ruleLineStyle13781 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_108_in_ruleLineStyle13798 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_109_in_ruleLineStyle13815 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_110_in_ruleLineStyle13832 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_111_in_ruleVerticalAlignment13877 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_112_in_ruleVerticalAlignment13894 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_113_in_ruleVerticalAlignment13911 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_114_in_ruleHorizontalAlignment13956 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_112_in_ruleHorizontalAlignment13973 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_115_in_ruleHorizontalAlignment13990 = new BitSet(new long[]{0x0000000000000002L});
    }


}