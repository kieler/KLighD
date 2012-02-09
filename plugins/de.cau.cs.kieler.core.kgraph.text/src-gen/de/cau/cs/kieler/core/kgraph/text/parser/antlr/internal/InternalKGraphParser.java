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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'KNode'", "'{'", "'incomingEdges'", "'('", "','", "')'", "'data'", "'}'", "'labels'", "'children'", "'ports'", "'outgoingEdges'", "'KLabel'", "'text'", "'KPort'", "'edges'", "'KEdge'", "'target'", "'sourcePort'", "'targetPort'", "'KRenderingRef'", "'references'", "'rendering'", "'placementData'", "'styles'", "'propagateToChildren'", "'KStyle'", "'KEllipse'", "'childPlacement'", "'KRectangle'", "'KRoundedRectangle'", "'cornerWidth'", "'cornerHeight'", "'KPolyline'", "'KPolygon'", "'KImage'", "'bundleName'", "'imagePath'", "'KArc'", "'startAngle'", "'arcAngle'", "'KChildArea'", "'clip'", "'KText'", "'KCustomRendering'", "'className'", "'KSpline'", "'relative'", "'KDecoratorPlacementData'", "'location'", "'xOffset'", "'yOffset'", "'KGridPlacementData'", "'widthHint'", "'heightHint'", "'horizontalIndent'", "'verticalIndent'", "'KStackPlacementData'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'KDirectPlacementData'", "'topLeft'", "'bottomRight'", "'KPolylinePlacementData'", "'points'", "'-'", "'.'", "'E'", "'e'", "'KPosition'", "'x'", "'y'", "'KLeftPosition'", "'absolute'", "'KRightPosition'", "'KTopPosition'", "'KBottomPosition'", "'KForegroundColor'", "'red'", "'green'", "'blue'", "'KBackgroundColor'", "'KLineWidth'", "'lineWidth'", "'lineVisible'", "'filled'", "'KVisibility'", "'KLineStyle'", "'lineStyle'", "'KVerticalAlignment'", "'verticalAlignment'", "'KHorizontalAlignment'", "'horizontalAlignment'", "'KGridPlacement'", "'numColumns'", "'KStackPlacement'", "'KShapeLayout'", "'xpos'", "'ypos'", "'width'", "'height'", "'insets'", "'KInsets'", "'top'", "'bottom'", "'left'", "'right'", "'KEdgeLayout'", "'bendPoints'", "'sourcePoint'", "'targetPoint'", "'KPoint'", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
    };
    public static final int RULE_ID=5;
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
    public static final int RULE_STRING=4;
    public static final int T__127=127;
    public static final int T__71=71;
    public static final int T__129=129;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__130=130;
    public static final int T__74=74;
    public static final int T__131=131;
    public static final int T__73=73;
    public static final int T__132=132;
    public static final int T__133=133;
    public static final int T__79=79;
    public static final int T__134=134;
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
    public static final int RULE_INT=6;
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:77:1: ruleKNode returns [EObject current=null] : ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'incomingEdges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKNode ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )* otherlv_26= '}' )? (otherlv_27= 'ports' otherlv_28= '{' ( (lv_ports_29_0= ruleKPort ) ) (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )* otherlv_32= '}' )? (otherlv_33= 'outgoingEdges' otherlv_34= '{' ( (lv_outgoingEdges_35_0= ruleKEdge ) ) (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )* otherlv_38= '}' )? otherlv_39= '}' ) ;
    public final EObject ruleKNode() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_32=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        Token otherlv_36=null;
        Token otherlv_38=null;
        Token otherlv_39=null;
        EObject lv_data_11_0 = null;

        EObject lv_data_13_0 = null;

        EObject lv_labels_17_0 = null;

        EObject lv_labels_19_0 = null;

        EObject lv_children_23_0 = null;

        EObject lv_children_25_0 = null;

        EObject lv_ports_29_0 = null;

        EObject lv_ports_31_0 = null;

        EObject lv_outgoingEdges_35_0 = null;

        EObject lv_outgoingEdges_37_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:80:28: ( ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'incomingEdges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKNode ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )* otherlv_26= '}' )? (otherlv_27= 'ports' otherlv_28= '{' ( (lv_ports_29_0= ruleKPort ) ) (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )* otherlv_32= '}' )? (otherlv_33= 'outgoingEdges' otherlv_34= '{' ( (lv_outgoingEdges_35_0= ruleKEdge ) ) (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )* otherlv_38= '}' )? otherlv_39= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:1: ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'incomingEdges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKNode ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )* otherlv_26= '}' )? (otherlv_27= 'ports' otherlv_28= '{' ( (lv_ports_29_0= ruleKPort ) ) (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )* otherlv_32= '}' )? (otherlv_33= 'outgoingEdges' otherlv_34= '{' ( (lv_outgoingEdges_35_0= ruleKEdge ) ) (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )* otherlv_38= '}' )? otherlv_39= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:1: ( () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'incomingEdges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKNode ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )* otherlv_26= '}' )? (otherlv_27= 'ports' otherlv_28= '{' ( (lv_ports_29_0= ruleKPort ) ) (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )* otherlv_32= '}' )? (otherlv_33= 'outgoingEdges' otherlv_34= '{' ( (lv_outgoingEdges_35_0= ruleKEdge ) ) (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )* otherlv_38= '}' )? otherlv_39= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:81:2: () otherlv_1= 'KNode' otherlv_2= '{' (otherlv_3= 'incomingEdges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKNode ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )* otherlv_26= '}' )? (otherlv_27= 'ports' otherlv_28= '{' ( (lv_ports_29_0= ruleKPort ) ) (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )* otherlv_32= '}' )? (otherlv_33= 'outgoingEdges' otherlv_34= '{' ( (lv_outgoingEdges_35_0= ruleKEdge ) ) (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )* otherlv_38= '}' )? otherlv_39= '}'
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
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:1: (otherlv_3= 'incomingEdges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:95:3: otherlv_3= 'incomingEdges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKNode156); 

                        	newLeafNode(otherlv_3, grammarAccess.getKNodeAccess().getIncomingEdgesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKNode168); 

                        	newLeafNode(otherlv_4, grammarAccess.getKNodeAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:103:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:104:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:104:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:105:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKNodeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getIncomingEdgesKEdgeCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKNode191);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:118:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==15) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:118:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode204); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKNodeAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:122:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:123:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:123:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:124:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKNodeRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getIncomingEdgesKEdgeCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKNode227);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKNode241); 

                        	newLeafNode(otherlv_8, grammarAccess.getKNodeAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:141:3: (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:141:5: otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}'
                    {
                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKNode256); 

                        	newLeafNode(otherlv_9, grammarAccess.getKNodeAccess().getDataKeyword_4_0());
                        
                    otherlv_10=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKNode268); 

                        	newLeafNode(otherlv_10, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:149:1: ( (lv_data_11_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:150:1: (lv_data_11_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:150:1: (lv_data_11_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:151:3: lv_data_11_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKGraphDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKNode289);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:167:2: (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==15) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:167:4: otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_12=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode302); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getKNodeAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:171:1: ( (lv_data_13_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:172:1: (lv_data_13_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:172:1: (lv_data_13_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:173:3: lv_data_13_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getDataKGraphDataParserRuleCall_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKNode323);
                    	    lv_data_13_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_13_0, 
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

                    otherlv_14=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode337); 

                        	newLeafNode(otherlv_14, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_4_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:193:3: (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==19) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:193:5: otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}'
                    {
                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKNode352); 

                        	newLeafNode(otherlv_15, grammarAccess.getKNodeAccess().getLabelsKeyword_5_0());
                        
                    otherlv_16=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKNode364); 

                        	newLeafNode(otherlv_16, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:201:1: ( (lv_labels_17_0= ruleKLabel ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:202:1: (lv_labels_17_0= ruleKLabel )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:202:1: (lv_labels_17_0= ruleKLabel )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:203:3: lv_labels_17_0= ruleKLabel
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getLabelsKLabelParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKNode385);
                    lv_labels_17_0=ruleKLabel();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"labels",
                            		lv_labels_17_0, 
                            		"KLabel");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:219:2: (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==15) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:219:4: otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) )
                    	    {
                    	    otherlv_18=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode398); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getKNodeAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:223:1: ( (lv_labels_19_0= ruleKLabel ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:224:1: (lv_labels_19_0= ruleKLabel )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:224:1: (lv_labels_19_0= ruleKLabel )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:225:3: lv_labels_19_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getLabelsKLabelParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKNode419);
                    	    lv_labels_19_0=ruleKLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_19_0, 
                    	            		"KLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode433); 

                        	newLeafNode(otherlv_20, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:245:3: (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKNode ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )* otherlv_26= '}' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==20) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:245:5: otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKNode ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )* otherlv_26= '}'
                    {
                    otherlv_21=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKNode448); 

                        	newLeafNode(otherlv_21, grammarAccess.getKNodeAccess().getChildrenKeyword_6_0());
                        
                    otherlv_22=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKNode460); 

                        	newLeafNode(otherlv_22, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:253:1: ( (lv_children_23_0= ruleKNode ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:254:1: (lv_children_23_0= ruleKNode )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:254:1: (lv_children_23_0= ruleKNode )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:255:3: lv_children_23_0= ruleKNode
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKNode_in_ruleKNode481);
                    lv_children_23_0=ruleKNode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_23_0, 
                            		"KNode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:271:2: (otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==15) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:271:4: otherlv_24= ',' ( (lv_children_25_0= ruleKNode ) )
                    	    {
                    	    otherlv_24=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode494); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getKNodeAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:275:1: ( (lv_children_25_0= ruleKNode ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:276:1: (lv_children_25_0= ruleKNode )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:276:1: (lv_children_25_0= ruleKNode )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:277:3: lv_children_25_0= ruleKNode
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getChildrenKNodeParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKNode_in_ruleKNode515);
                    	    lv_children_25_0=ruleKNode();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_25_0, 
                    	            		"KNode");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    otherlv_26=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode529); 

                        	newLeafNode(otherlv_26, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:297:3: (otherlv_27= 'ports' otherlv_28= '{' ( (lv_ports_29_0= ruleKPort ) ) (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )* otherlv_32= '}' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==21) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:297:5: otherlv_27= 'ports' otherlv_28= '{' ( (lv_ports_29_0= ruleKPort ) ) (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )* otherlv_32= '}'
                    {
                    otherlv_27=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKNode544); 

                        	newLeafNode(otherlv_27, grammarAccess.getKNodeAccess().getPortsKeyword_7_0());
                        
                    otherlv_28=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKNode556); 

                        	newLeafNode(otherlv_28, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:305:1: ( (lv_ports_29_0= ruleKPort ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:306:1: (lv_ports_29_0= ruleKPort )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:306:1: (lv_ports_29_0= ruleKPort )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:307:3: lv_ports_29_0= ruleKPort
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getPortsKPortParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPort_in_ruleKNode577);
                    lv_ports_29_0=ruleKPort();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"ports",
                            		lv_ports_29_0, 
                            		"KPort");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:323:2: (otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==15) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:323:4: otherlv_30= ',' ( (lv_ports_31_0= ruleKPort ) )
                    	    {
                    	    otherlv_30=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode590); 

                    	        	newLeafNode(otherlv_30, grammarAccess.getKNodeAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:327:1: ( (lv_ports_31_0= ruleKPort ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:328:1: (lv_ports_31_0= ruleKPort )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:328:1: (lv_ports_31_0= ruleKPort )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:329:3: lv_ports_31_0= ruleKPort
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getPortsKPortParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPort_in_ruleKNode611);
                    	    lv_ports_31_0=ruleKPort();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"ports",
                    	            		lv_ports_31_0, 
                    	            		"KPort");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_32=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode625); 

                        	newLeafNode(otherlv_32, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:349:3: (otherlv_33= 'outgoingEdges' otherlv_34= '{' ( (lv_outgoingEdges_35_0= ruleKEdge ) ) (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )* otherlv_38= '}' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==22) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:349:5: otherlv_33= 'outgoingEdges' otherlv_34= '{' ( (lv_outgoingEdges_35_0= ruleKEdge ) ) (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )* otherlv_38= '}'
                    {
                    otherlv_33=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKNode640); 

                        	newLeafNode(otherlv_33, grammarAccess.getKNodeAccess().getOutgoingEdgesKeyword_8_0());
                        
                    otherlv_34=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKNode652); 

                        	newLeafNode(otherlv_34, grammarAccess.getKNodeAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:357:1: ( (lv_outgoingEdges_35_0= ruleKEdge ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:358:1: (lv_outgoingEdges_35_0= ruleKEdge )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:358:1: (lv_outgoingEdges_35_0= ruleKEdge )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:359:3: lv_outgoingEdges_35_0= ruleKEdge
                    {
                     
                    	        newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_ruleKNode673);
                    lv_outgoingEdges_35_0=ruleKEdge();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	        }
                           		add(
                           			current, 
                           			"outgoingEdges",
                            		lv_outgoingEdges_35_0, 
                            		"KEdge");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:375:2: (otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==15) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:375:4: otherlv_36= ',' ( (lv_outgoingEdges_37_0= ruleKEdge ) )
                    	    {
                    	    otherlv_36=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKNode686); 

                    	        	newLeafNode(otherlv_36, grammarAccess.getKNodeAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:379:1: ( (lv_outgoingEdges_37_0= ruleKEdge ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:380:1: (lv_outgoingEdges_37_0= ruleKEdge )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:380:1: (lv_outgoingEdges_37_0= ruleKEdge )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:381:3: lv_outgoingEdges_37_0= ruleKEdge
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKNodeAccess().getOutgoingEdgesKEdgeParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_ruleKNode707);
                    	    lv_outgoingEdges_37_0=ruleKEdge();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outgoingEdges",
                    	            		lv_outgoingEdges_37_0, 
                    	            		"KEdge");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    otherlv_38=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode721); 

                        	newLeafNode(otherlv_38, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            otherlv_39=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKNode735); 

                	newLeafNode(otherlv_39, grammarAccess.getKNodeAccess().getRightCurlyBracketKeyword_9());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleKGraphData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:413:1: entryRuleKGraphData returns [EObject current=null] : iv_ruleKGraphData= ruleKGraphData EOF ;
    public final EObject entryRuleKGraphData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGraphData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:414:2: (iv_ruleKGraphData= ruleKGraphData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:415:2: iv_ruleKGraphData= ruleKGraphData EOF
            {
             newCompositeNode(grammarAccess.getKGraphDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_entryRuleKGraphData771);
            iv_ruleKGraphData=ruleKGraphData();

            state._fsp--;

             current =iv_ruleKGraphData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGraphData781); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:422:1: ruleKGraphData returns [EObject current=null] : (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout ) ;
    public final EObject ruleKGraphData() throws RecognitionException {
        EObject current = null;

        EObject this_KRendering_0 = null;

        EObject this_KShapeLayout_1 = null;

        EObject this_KEdgeLayout_2 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:425:28: ( (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:426:1: (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:426:1: (this_KRendering_0= ruleKRendering | this_KShapeLayout_1= ruleKShapeLayout | this_KEdgeLayout_2= ruleKEdgeLayout )
            int alt13=3;
            switch ( input.LA(1) ) {
            case 31:
            case 38:
            case 40:
            case 41:
            case 44:
            case 45:
            case 46:
            case 49:
            case 52:
            case 53:
            case 55:
            case 57:
                {
                alt13=1;
                }
                break;
            case 109:
                {
                alt13=2;
                }
                break;
            case 120:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:427:5: this_KRendering_0= ruleKRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKRenderingParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKGraphData828);
                    this_KRendering_0=ruleKRendering();

                    state._fsp--;

                     
                            current = this_KRendering_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:437:5: this_KShapeLayout_1= ruleKShapeLayout
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKShapeLayoutParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_ruleKGraphData855);
                    this_KShapeLayout_1=ruleKShapeLayout();

                    state._fsp--;

                     
                            current = this_KShapeLayout_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:447:5: this_KEdgeLayout_2= ruleKEdgeLayout
                    {
                     
                            newCompositeNode(grammarAccess.getKGraphDataAccess().getKEdgeLayoutParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEdgeLayout_in_ruleKGraphData882);
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


    // $ANTLR start "entryRuleKLabel"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:463:1: entryRuleKLabel returns [EObject current=null] : iv_ruleKLabel= ruleKLabel EOF ;
    public final EObject entryRuleKLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLabel = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:464:2: (iv_ruleKLabel= ruleKLabel EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:465:2: iv_ruleKLabel= ruleKLabel EOF
            {
             newCompositeNode(grammarAccess.getKLabelRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_entryRuleKLabel917);
            iv_ruleKLabel=ruleKLabel();

            state._fsp--;

             current =iv_ruleKLabel; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLabel927); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:472:1: ruleKLabel returns [EObject current=null] : (otherlv_0= 'KLabel' otherlv_1= '{' otherlv_2= 'text' ( (lv_text_3_0= ruleEString ) ) (otherlv_4= 'data' otherlv_5= '{' ( (lv_data_6_0= ruleKGraphData ) ) (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )* otherlv_9= '}' )? otherlv_10= '}' ) ;
    public final EObject ruleKLabel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_text_3_0 = null;

        EObject lv_data_6_0 = null;

        EObject lv_data_8_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:475:28: ( (otherlv_0= 'KLabel' otherlv_1= '{' otherlv_2= 'text' ( (lv_text_3_0= ruleEString ) ) (otherlv_4= 'data' otherlv_5= '{' ( (lv_data_6_0= ruleKGraphData ) ) (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )* otherlv_9= '}' )? otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:476:1: (otherlv_0= 'KLabel' otherlv_1= '{' otherlv_2= 'text' ( (lv_text_3_0= ruleEString ) ) (otherlv_4= 'data' otherlv_5= '{' ( (lv_data_6_0= ruleKGraphData ) ) (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )* otherlv_9= '}' )? otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:476:1: (otherlv_0= 'KLabel' otherlv_1= '{' otherlv_2= 'text' ( (lv_text_3_0= ruleEString ) ) (otherlv_4= 'data' otherlv_5= '{' ( (lv_data_6_0= ruleKGraphData ) ) (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )* otherlv_9= '}' )? otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:476:3: otherlv_0= 'KLabel' otherlv_1= '{' otherlv_2= 'text' ( (lv_text_3_0= ruleEString ) ) (otherlv_4= 'data' otherlv_5= '{' ( (lv_data_6_0= ruleKGraphData ) ) (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )* otherlv_9= '}' )? otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKLabel964); 

                	newLeafNode(otherlv_0, grammarAccess.getKLabelAccess().getKLabelKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLabel976); 

                	newLeafNode(otherlv_1, grammarAccess.getKLabelAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKLabel988); 

                	newLeafNode(otherlv_2, grammarAccess.getKLabelAccess().getTextKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:488:1: ( (lv_text_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:489:1: (lv_text_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:489:1: (lv_text_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:490:3: lv_text_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKLabelAccess().getTextEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKLabel1009);
            lv_text_3_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLabelRule());
            	        }
                   		set(
                   			current, 
                   			"text",
                    		lv_text_3_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:506:2: (otherlv_4= 'data' otherlv_5= '{' ( (lv_data_6_0= ruleKGraphData ) ) (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )* otherlv_9= '}' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==17) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:506:4: otherlv_4= 'data' otherlv_5= '{' ( (lv_data_6_0= ruleKGraphData ) ) (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )* otherlv_9= '}'
                    {
                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKLabel1022); 

                        	newLeafNode(otherlv_4, grammarAccess.getKLabelAccess().getDataKeyword_4_0());
                        
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLabel1034); 

                        	newLeafNode(otherlv_5, grammarAccess.getKLabelAccess().getLeftCurlyBracketKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:514:1: ( (lv_data_6_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:515:1: (lv_data_6_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:515:1: (lv_data_6_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:516:3: lv_data_6_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKGraphDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKLabel1055);
                    lv_data_6_0=ruleKGraphData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKLabelRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_6_0, 
                            		"KGraphData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:532:2: (otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==15) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:532:4: otherlv_7= ',' ( (lv_data_8_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKLabel1068); 

                    	        	newLeafNode(otherlv_7, grammarAccess.getKLabelAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:536:1: ( (lv_data_8_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:537:1: (lv_data_8_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:537:1: (lv_data_8_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:538:3: lv_data_8_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKLabelAccess().getDataKGraphDataParserRuleCall_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKLabel1089);
                    	    lv_data_8_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKLabelRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_8_0, 
                    	            		"KGraphData");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLabel1103); 

                        	newLeafNode(otherlv_9, grammarAccess.getKLabelAccess().getRightCurlyBracketKeyword_4_4());
                        

                    }
                    break;

            }

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLabel1117); 

                	newLeafNode(otherlv_10, grammarAccess.getKLabelAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:570:1: entryRuleKPort returns [EObject current=null] : iv_ruleKPort= ruleKPort EOF ;
    public final EObject entryRuleKPort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPort = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:571:2: (iv_ruleKPort= ruleKPort EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:572:2: iv_ruleKPort= ruleKPort EOF
            {
             newCompositeNode(grammarAccess.getKPortRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPort_in_entryRuleKPort1153);
            iv_ruleKPort=ruleKPort();

            state._fsp--;

             current =iv_ruleKPort; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPort1163); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:579:1: ruleKPort returns [EObject current=null] : ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? otherlv_21= '}' ) ;
    public final EObject ruleKPort() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        EObject lv_data_11_0 = null;

        EObject lv_data_13_0 = null;

        EObject lv_labels_17_0 = null;

        EObject lv_labels_19_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:582:28: ( ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? otherlv_21= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:583:1: ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? otherlv_21= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:583:1: ( () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? otherlv_21= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:583:2: () otherlv_1= 'KPort' otherlv_2= '{' (otherlv_3= 'edges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )? (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )? otherlv_21= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:583:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:584:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPortAccess().getKPortAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKPort1209); 

                	newLeafNode(otherlv_1, grammarAccess.getKPortAccess().getKPortKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPort1221); 

                	newLeafNode(otherlv_2, grammarAccess.getKPortAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:597:1: (otherlv_3= 'edges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:597:3: otherlv_3= 'edges' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKPort1234); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPortAccess().getEdgesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPort1246); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPortAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:605:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:606:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:606:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:607:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKPortRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getEdgesKEdgeCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPort1269);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:620:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==15) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:620:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPort1282); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPortAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:624:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:625:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:625:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:626:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKPortRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getEdgesKEdgeCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPort1305);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPort1319); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPortAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:643:3: (otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==17) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:643:5: otherlv_9= 'data' otherlv_10= '{' ( (lv_data_11_0= ruleKGraphData ) ) (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )* otherlv_14= '}'
                    {
                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPort1334); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPortAccess().getDataKeyword_4_0());
                        
                    otherlv_10=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPort1346); 

                        	newLeafNode(otherlv_10, grammarAccess.getKPortAccess().getLeftCurlyBracketKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:651:1: ( (lv_data_11_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:652:1: (lv_data_11_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:652:1: (lv_data_11_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:653:3: lv_data_11_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKGraphDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKPort1367);
                    lv_data_11_0=ruleKGraphData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPortRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_11_0, 
                            		"KGraphData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:669:2: (otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==15) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:669:4: otherlv_12= ',' ( (lv_data_13_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_12=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPort1380); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getKPortAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:673:1: ( (lv_data_13_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:674:1: (lv_data_13_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:674:1: (lv_data_13_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:675:3: lv_data_13_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getDataKGraphDataParserRuleCall_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKPort1401);
                    	    lv_data_13_0=ruleKGraphData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPortRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_13_0, 
                    	            		"KGraphData");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPort1415); 

                        	newLeafNode(otherlv_14, grammarAccess.getKPortAccess().getRightCurlyBracketKeyword_4_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:695:3: (otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==19) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:695:5: otherlv_15= 'labels' otherlv_16= '{' ( (lv_labels_17_0= ruleKLabel ) ) (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )* otherlv_20= '}'
                    {
                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPort1430); 

                        	newLeafNode(otherlv_15, grammarAccess.getKPortAccess().getLabelsKeyword_5_0());
                        
                    otherlv_16=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPort1442); 

                        	newLeafNode(otherlv_16, grammarAccess.getKPortAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:703:1: ( (lv_labels_17_0= ruleKLabel ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:704:1: (lv_labels_17_0= ruleKLabel )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:704:1: (lv_labels_17_0= ruleKLabel )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:705:3: lv_labels_17_0= ruleKLabel
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKPort1463);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:721:2: (otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==15) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:721:4: otherlv_18= ',' ( (lv_labels_19_0= ruleKLabel ) )
                    	    {
                    	    otherlv_18=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPort1476); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getKPortAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:725:1: ( (lv_labels_19_0= ruleKLabel ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:726:1: (lv_labels_19_0= ruleKLabel )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:726:1: (lv_labels_19_0= ruleKLabel )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:727:3: lv_labels_19_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPortAccess().getLabelsKLabelParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKPort1497);
                    	    lv_labels_19_0=ruleKLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPortRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_19_0, 
                    	            		"KLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPort1511); 

                        	newLeafNode(otherlv_20, grammarAccess.getKPortAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            otherlv_21=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPort1525); 

                	newLeafNode(otherlv_21, grammarAccess.getKPortAccess().getRightCurlyBracketKeyword_6());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:759:1: entryRuleKEdge returns [EObject current=null] : iv_ruleKEdge= ruleKEdge EOF ;
    public final EObject entryRuleKEdge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdge = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:760:2: (iv_ruleKEdge= ruleKEdge EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:761:2: iv_ruleKEdge= ruleKEdge EOF
            {
             newCompositeNode(grammarAccess.getKEdgeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEdge_in_entryRuleKEdge1561);
            iv_ruleKEdge=ruleKEdge();

            state._fsp--;

             current =iv_ruleKEdge; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEdge1571); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:768:1: ruleKEdge returns [EObject current=null] : (otherlv_0= 'KEdge' otherlv_1= '{' otherlv_2= 'target' ( ( ruleEString ) ) (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= '{' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* otherlv_13= '}' )? (otherlv_14= 'labels' otherlv_15= '{' ( (lv_labels_16_0= ruleKLabel ) ) (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )* otherlv_19= '}' )? otherlv_20= '}' ) ;
    public final EObject ruleKEdge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        EObject lv_data_10_0 = null;

        EObject lv_data_12_0 = null;

        EObject lv_labels_16_0 = null;

        EObject lv_labels_18_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:771:28: ( (otherlv_0= 'KEdge' otherlv_1= '{' otherlv_2= 'target' ( ( ruleEString ) ) (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= '{' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* otherlv_13= '}' )? (otherlv_14= 'labels' otherlv_15= '{' ( (lv_labels_16_0= ruleKLabel ) ) (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )* otherlv_19= '}' )? otherlv_20= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:772:1: (otherlv_0= 'KEdge' otherlv_1= '{' otherlv_2= 'target' ( ( ruleEString ) ) (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= '{' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* otherlv_13= '}' )? (otherlv_14= 'labels' otherlv_15= '{' ( (lv_labels_16_0= ruleKLabel ) ) (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )* otherlv_19= '}' )? otherlv_20= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:772:1: (otherlv_0= 'KEdge' otherlv_1= '{' otherlv_2= 'target' ( ( ruleEString ) ) (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= '{' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* otherlv_13= '}' )? (otherlv_14= 'labels' otherlv_15= '{' ( (lv_labels_16_0= ruleKLabel ) ) (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )* otherlv_19= '}' )? otherlv_20= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:772:3: otherlv_0= 'KEdge' otherlv_1= '{' otherlv_2= 'target' ( ( ruleEString ) ) (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )? (otherlv_6= 'targetPort' ( ( ruleEString ) ) )? (otherlv_8= 'data' otherlv_9= '{' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* otherlv_13= '}' )? (otherlv_14= 'labels' otherlv_15= '{' ( (lv_labels_16_0= ruleKLabel ) ) (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )* otherlv_19= '}' )? otherlv_20= '}'
            {
            otherlv_0=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKEdge1608); 

                	newLeafNode(otherlv_0, grammarAccess.getKEdgeAccess().getKEdgeKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdge1620); 

                	newLeafNode(otherlv_1, grammarAccess.getKEdgeAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKEdge1632); 

                	newLeafNode(otherlv_2, grammarAccess.getKEdgeAccess().getTargetKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:784:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:785:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:785:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:786:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKEdgeRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetKNodeCrossReference_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1655);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:799:2: (otherlv_4= 'sourcePort' ( ( ruleEString ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==29) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:799:4: otherlv_4= 'sourcePort' ( ( ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKEdge1668); 

                        	newLeafNode(otherlv_4, grammarAccess.getKEdgeAccess().getSourcePortKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:803:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:804:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:804:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:805:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getSourcePortKPortCrossReference_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1691);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:818:4: (otherlv_6= 'targetPort' ( ( ruleEString ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==30) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:818:6: otherlv_6= 'targetPort' ( ( ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKEdge1706); 

                        	newLeafNode(otherlv_6, grammarAccess.getKEdgeAccess().getTargetPortKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:822:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:823:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:823:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:824:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEdgeRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getTargetPortKPortCrossReference_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEdge1729);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:837:4: (otherlv_8= 'data' otherlv_9= '{' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* otherlv_13= '}' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==17) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:837:6: otherlv_8= 'data' otherlv_9= '{' ( (lv_data_10_0= ruleKGraphData ) ) (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )* otherlv_13= '}'
                    {
                    otherlv_8=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEdge1744); 

                        	newLeafNode(otherlv_8, grammarAccess.getKEdgeAccess().getDataKeyword_6_0());
                        
                    otherlv_9=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdge1756); 

                        	newLeafNode(otherlv_9, grammarAccess.getKEdgeAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:845:1: ( (lv_data_10_0= ruleKGraphData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:846:1: (lv_data_10_0= ruleKGraphData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:846:1: (lv_data_10_0= ruleKGraphData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:847:3: lv_data_10_0= ruleKGraphData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKGraphDataParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKEdge1777);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:863:2: (otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==15) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:863:4: otherlv_11= ',' ( (lv_data_12_0= ruleKGraphData ) )
                    	    {
                    	    otherlv_11=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEdge1790); 

                    	        	newLeafNode(otherlv_11, grammarAccess.getKEdgeAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:867:1: ( (lv_data_12_0= ruleKGraphData ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:868:1: (lv_data_12_0= ruleKGraphData )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:868:1: (lv_data_12_0= ruleKGraphData )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:869:3: lv_data_12_0= ruleKGraphData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getDataKGraphDataParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKGraphData_in_ruleKEdge1811);
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
                    	    break loop24;
                        }
                    } while (true);

                    otherlv_13=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdge1825); 

                        	newLeafNode(otherlv_13, grammarAccess.getKEdgeAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:889:3: (otherlv_14= 'labels' otherlv_15= '{' ( (lv_labels_16_0= ruleKLabel ) ) (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )* otherlv_19= '}' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==19) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:889:5: otherlv_14= 'labels' otherlv_15= '{' ( (lv_labels_16_0= ruleKLabel ) ) (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEdge1840); 

                        	newLeafNode(otherlv_14, grammarAccess.getKEdgeAccess().getLabelsKeyword_7_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdge1852); 

                        	newLeafNode(otherlv_15, grammarAccess.getKEdgeAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:897:1: ( (lv_labels_16_0= ruleKLabel ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:898:1: (lv_labels_16_0= ruleKLabel )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:898:1: (lv_labels_16_0= ruleKLabel )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:899:3: lv_labels_16_0= ruleKLabel
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKEdge1873);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:915:2: (otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) ) )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==15) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:915:4: otherlv_17= ',' ( (lv_labels_18_0= ruleKLabel ) )
                    	    {
                    	    otherlv_17=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEdge1886); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKEdgeAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:919:1: ( (lv_labels_18_0= ruleKLabel ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:920:1: (lv_labels_18_0= ruleKLabel )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:920:1: (lv_labels_18_0= ruleKLabel )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:921:3: lv_labels_18_0= ruleKLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeAccess().getLabelsKLabelParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKLabel_in_ruleKEdge1907);
                    	    lv_labels_18_0=ruleKLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_18_0, 
                    	            		"KLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdge1921); 

                        	newLeafNode(otherlv_19, grammarAccess.getKEdgeAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            otherlv_20=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdge1935); 

                	newLeafNode(otherlv_20, grammarAccess.getKEdgeAccess().getRightCurlyBracketKeyword_8());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:953:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:954:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:955:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString1972);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString1983); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:962:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:965:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:966:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:966:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==RULE_STRING) ) {
                alt28=1;
            }
            else if ( (LA28_0==RULE_ID) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:966:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString2023); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:974:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString2049); 

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


    // $ANTLR start "entryRuleKRendering"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:991:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:992:2: (iv_ruleKRendering= ruleKRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:993:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_entryRuleKRendering2096);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRendering2106); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1000:1: ruleKRendering returns [EObject current=null] : (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1003:28: ( (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1004:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1004:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            int alt29=12;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt29=1;
                }
                break;
            case 40:
                {
                alt29=2;
                }
                break;
            case 41:
                {
                alt29=3;
                }
                break;
            case 44:
                {
                alt29=4;
                }
                break;
            case 45:
                {
                alt29=5;
                }
                break;
            case 46:
                {
                alt29=6;
                }
                break;
            case 49:
                {
                alt29=7;
                }
                break;
            case 31:
                {
                alt29=8;
                }
                break;
            case 52:
                {
                alt29=9;
                }
                break;
            case 53:
                {
                alt29=10;
                }
                break;
            case 55:
                {
                alt29=11;
                }
                break;
            case 57:
                {
                alt29=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1005:5: this_KEllipse_0= ruleKEllipse
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKEllipseParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_ruleKRendering2153);
                    this_KEllipse_0=ruleKEllipse();

                    state._fsp--;

                     
                            current = this_KEllipse_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1015:5: this_KRectangle_1= ruleKRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRectangleParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_ruleKRendering2180);
                    this_KRectangle_1=ruleKRectangle();

                    state._fsp--;

                     
                            current = this_KRectangle_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1025:5: this_KRoundedRectangle_2= ruleKRoundedRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRoundedRectangleParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_ruleKRendering2207);
                    this_KRoundedRectangle_2=ruleKRoundedRectangle();

                    state._fsp--;

                     
                            current = this_KRoundedRectangle_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1035:5: this_KPolyline_Impl_3= ruleKPolyline_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolyline_ImplParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_ruleKRendering2234);
                    this_KPolyline_Impl_3=ruleKPolyline_Impl();

                    state._fsp--;

                     
                            current = this_KPolyline_Impl_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1045:5: this_KPolygon_4= ruleKPolygon
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolygonParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_ruleKRendering2261);
                    this_KPolygon_4=ruleKPolygon();

                    state._fsp--;

                     
                            current = this_KPolygon_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1055:5: this_KImage_5= ruleKImage
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKImageParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKImage_in_ruleKRendering2288);
                    this_KImage_5=ruleKImage();

                    state._fsp--;

                     
                            current = this_KImage_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1065:5: this_KArc_6= ruleKArc
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKArcParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKArc_in_ruleKRendering2315);
                    this_KArc_6=ruleKArc();

                    state._fsp--;

                     
                            current = this_KArc_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1075:5: this_KRenderingRef_7= ruleKRenderingRef
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRenderingRefParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_ruleKRendering2342);
                    this_KRenderingRef_7=ruleKRenderingRef();

                    state._fsp--;

                     
                            current = this_KRenderingRef_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1085:5: this_KChildArea_8= ruleKChildArea
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKChildAreaParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_ruleKRendering2369);
                    this_KChildArea_8=ruleKChildArea();

                    state._fsp--;

                     
                            current = this_KChildArea_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1095:5: this_KText_9= ruleKText
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKTextParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKText_in_ruleKRendering2396);
                    this_KText_9=ruleKText();

                    state._fsp--;

                     
                            current = this_KText_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1105:5: this_KCustomRendering_10= ruleKCustomRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKCustomRenderingParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_ruleKRendering2423);
                    this_KCustomRendering_10=ruleKCustomRendering();

                    state._fsp--;

                     
                            current = this_KCustomRendering_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1115:5: this_KSpline_11= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSplineParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_ruleKRendering2450);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1131:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1132:2: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1133:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData2485);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacementData2495); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1140:1: ruleKPlacementData returns [EObject current=null] : (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KDecoratorPlacementData_0 = null;

        EObject this_KGridPlacementData_1 = null;

        EObject this_KStackPlacementData_2 = null;

        EObject this_KDirectPlacementData_3 = null;

        EObject this_KPolylinePlacementData_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1143:28: ( (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1144:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1144:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            int alt30=5;
            switch ( input.LA(1) ) {
            case 58:
                {
                alt30=1;
                }
                break;
            case 63:
                {
                alt30=2;
                }
                break;
            case 68:
                {
                alt30=3;
                }
                break;
            case 73:
                {
                alt30=4;
                }
                break;
            case 76:
                {
                alt30=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1145:5: this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData2542);
                    this_KDecoratorPlacementData_0=ruleKDecoratorPlacementData();

                    state._fsp--;

                     
                            current = this_KDecoratorPlacementData_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1155:5: this_KGridPlacementData_1= ruleKGridPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData2569);
                    this_KGridPlacementData_1=ruleKGridPlacementData();

                    state._fsp--;

                     
                            current = this_KGridPlacementData_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1165:5: this_KStackPlacementData_2= ruleKStackPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKStackPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData2596);
                    this_KStackPlacementData_2=ruleKStackPlacementData();

                    state._fsp--;

                     
                            current = this_KStackPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1175:5: this_KDirectPlacementData_3= ruleKDirectPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDirectPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData2623);
                    this_KDirectPlacementData_3=ruleKDirectPlacementData();

                    state._fsp--;

                     
                            current = this_KDirectPlacementData_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1185:5: this_KPolylinePlacementData_4= ruleKPolylinePlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPolylinePlacementDataParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData2650);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1201:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1202:2: (iv_ruleKStyle= ruleKStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1203:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_entryRuleKStyle2685);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle2695); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1210:1: ruleKStyle returns [EObject current=null] : (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment ) ;
    public final EObject ruleKStyle() throws RecognitionException {
        EObject current = null;

        EObject this_KStyle_Impl_0 = null;

        EObject this_KForegroundColor_1 = null;

        EObject this_KBackgroundColor_2 = null;

        EObject this_KLineWidth_3 = null;

        EObject this_KVisibility_4 = null;

        EObject this_KLineStyle_5 = null;

        EObject this_KVerticalAlignment_6 = null;

        EObject this_KHorizontalAlignment_7 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1213:28: ( (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1214:1: (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1214:1: (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment )
            int alt31=8;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1215:5: this_KStyle_Impl_0= ruleKStyle_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKStyle_ImplParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_Impl_in_ruleKStyle2742);
                    this_KStyle_Impl_0=ruleKStyle_Impl();

                    state._fsp--;

                     
                            current = this_KStyle_Impl_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1225:5: this_KForegroundColor_1= ruleKForegroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundColorParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_ruleKStyle2769);
                    this_KForegroundColor_1=ruleKForegroundColor();

                    state._fsp--;

                     
                            current = this_KForegroundColor_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1235:5: this_KBackgroundColor_2= ruleKBackgroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKBackgroundColorParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_ruleKStyle2796);
                    this_KBackgroundColor_2=ruleKBackgroundColor();

                    state._fsp--;

                     
                            current = this_KBackgroundColor_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1245:5: this_KLineWidth_3= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle2823);
                    this_KLineWidth_3=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1255:5: this_KVisibility_4= ruleKVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVisibilityParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_ruleKStyle2850);
                    this_KVisibility_4=ruleKVisibility();

                    state._fsp--;

                     
                            current = this_KVisibility_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1265:5: this_KLineStyle_5= ruleKLineStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_ruleKStyle2877);
                    this_KLineStyle_5=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1275:5: this_KVerticalAlignment_6= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle2904);
                    this_KVerticalAlignment_6=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1285:5: this_KHorizontalAlignment_7= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle2931);
                    this_KHorizontalAlignment_7=ruleKHorizontalAlignment();

                    state._fsp--;

                     
                            current = this_KHorizontalAlignment_7; 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1301:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1302:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1303:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_entryRuleKPlacement2966);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacement2976); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1310:1: ruleKPlacement returns [EObject current=null] : (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;

        EObject this_KStackPlacement_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1313:28: ( (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1314:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1314:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==106) ) {
                alt32=1;
            }
            else if ( (LA32_0==108) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1315:5: this_KGridPlacement_0= ruleKGridPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement3023);
                    this_KGridPlacement_0=ruleKGridPlacement();

                    state._fsp--;

                     
                            current = this_KGridPlacement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1325:5: this_KStackPlacement_1= ruleKStackPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKStackPlacementParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_ruleKPlacement3050);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1341:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1342:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1343:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition3085);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition3095); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1350:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1353:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1354:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1354:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==85) ) {
                alt33=1;
            }
            else if ( (LA33_0==87) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1355:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition3142);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1365:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition3169);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1381:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1382:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1383:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition3204);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition3214); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1390:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1393:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1394:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1394:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==88) ) {
                alt34=1;
            }
            else if ( (LA34_0==89) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1395:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition3261);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1405:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition3288);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1423:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1424:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1425:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef3325);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef3335); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1432:1: ruleKRenderingRef returns [EObject current=null] : (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1435:28: ( (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1436:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1436:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1436:3: otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            otherlv_0=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKRenderingRef3372); 

                	newLeafNode(otherlv_0, grammarAccess.getKRenderingRefAccess().getKRenderingRefKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef3384); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1444:1: (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==32) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1444:3: otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')'
                    {
                    otherlv_2=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKRenderingRef3397); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRenderingRefAccess().getReferencesKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef3409); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftParenthesisKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1452:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1453:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1453:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1454:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef3432);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1467:2: (otherlv_5= ',' ( ( ruleEString ) ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==15) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1467:4: otherlv_5= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef3445); 

                    	        	newLeafNode(otherlv_5, grammarAccess.getKRenderingRefAccess().getCommaKeyword_2_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1471:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1472:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1472:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1473:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef3468);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRenderingRef3482); 

                        	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getRightParenthesisKeyword_2_4());
                        

                    }
                    break;

            }

            otherlv_8=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKRenderingRef3496); 

                	newLeafNode(otherlv_8, grammarAccess.getKRenderingRefAccess().getRenderingKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1494:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1495:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1495:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1496:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef3519);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1509:2: (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==34) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1509:4: otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) )
                    {
                    otherlv_10=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKRenderingRef3532); 

                        	newLeafNode(otherlv_10, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1513:1: ( (lv_placementData_11_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1514:1: (lv_placementData_11_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1514:1: (lv_placementData_11_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1515:3: lv_placementData_11_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef3553);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1531:4: (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==35) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1531:6: otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKRenderingRef3568); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRenderingRefAccess().getStylesKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef3580); 

                        	newLeafNode(otherlv_13, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1539:1: ( (lv_styles_14_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1540:1: (lv_styles_14_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1540:1: (lv_styles_14_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1541:3: lv_styles_14_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef3601);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1557:2: (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )*
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==15) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1557:4: otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) )
                    	    {
                    	    otherlv_15=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef3614); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getKRenderingRefAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1561:1: ( (lv_styles_16_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1562:1: (lv_styles_16_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1562:1: (lv_styles_16_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1563:3: lv_styles_16_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef3635);
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
                    	    break loop38;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef3649); 

                        	newLeafNode(otherlv_17, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef3663); 

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


    // $ANTLR start "entryRuleKStyle_Impl"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1595:1: entryRuleKStyle_Impl returns [EObject current=null] : iv_ruleKStyle_Impl= ruleKStyle_Impl EOF ;
    public final EObject entryRuleKStyle_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1596:2: (iv_ruleKStyle_Impl= ruleKStyle_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1597:2: iv_ruleKStyle_Impl= ruleKStyle_Impl EOF
            {
             newCompositeNode(grammarAccess.getKStyle_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_Impl_in_entryRuleKStyle_Impl3699);
            iv_ruleKStyle_Impl=ruleKStyle_Impl();

            state._fsp--;

             current =iv_ruleKStyle_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle_Impl3709); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKStyle_Impl"


    // $ANTLR start "ruleKStyle_Impl"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1604:1: ruleKStyle_Impl returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' ) ;
    public final EObject ruleKStyle_Impl() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1607:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1608:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1608:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1608:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1608:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1609:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1609:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1610:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKStyle_Impl3752); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKStyle_ImplAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKStyle_ImplRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKStyle_Impl3777); 

                	newLeafNode(otherlv_1, grammarAccess.getKStyle_ImplAccess().getKStyleKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKStyle_Impl"


    // $ANTLR start "entryRuleKEllipse"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1635:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1636:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1637:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse3813);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse3823); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1644:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1647:28: ( ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1648:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1648:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1648:2: () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1648:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1649:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKEllipse3869); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getKEllipseKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse3881); 

                	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1662:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==32) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1662:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKEllipse3894); 

                        	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse3906); 

                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1670:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1671:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1671:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1672:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEllipseRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEllipse3929);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1685:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==15) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1685:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEllipse3942); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1689:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1690:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1690:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1691:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKEllipseRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEllipse3965);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse3979); 

                        	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1708:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==34) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1708:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKEllipse3994); 

                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1712:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1713:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1713:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1714:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse4015);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1730:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==35) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1730:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKEllipse4030); 

                        	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse4042); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1738:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1739:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1739:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1740:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse4063);
                    lv_styles_13_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_13_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1756:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==15) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1756:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEllipse4076); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1760:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1761:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1761:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1762:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse4097);
                    	    lv_styles_15_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
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
                    	    break loop43;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse4111); 

                        	newLeafNode(otherlv_16, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1782:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==20) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1782:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKEllipse4126); 

                        	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse4138); 

                        	newLeafNode(otherlv_18, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1790:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1791:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1791:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1792:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse4159);
                    lv_children_19_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_19_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1808:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==15) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1808:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEllipse4172); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKEllipseAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1812:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1813:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1813:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1814:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse4193);
                    	    lv_children_21_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
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
                    	    break loop45;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse4207); 

                        	newLeafNode(otherlv_22, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1834:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==39) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1834:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKEllipse4222); 

                        	newLeafNode(otherlv_23, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1838:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1839:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1839:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1840:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse4243);
                    lv_childPlacement_24_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEllipseRule());
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

            otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse4257); 

                	newLeafNode(otherlv_25, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_8());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1868:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1869:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1870:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle4293);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle4303); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1877:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1880:28: ( ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1881:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1881:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1881:2: () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1881:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1882:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKRectangle4349); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getKRectangleKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle4361); 

                	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1895:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==32) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1895:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKRectangle4374); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle4386); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1903:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1904:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1904:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1905:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRectangleRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRectangle4409);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1918:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==15) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1918:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRectangle4422); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1922:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1923:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1923:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1924:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRectangleRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRectangle4445);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle4459); 

                        	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1941:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==34) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1941:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKRectangle4474); 

                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1945:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1946:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1946:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1947:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle4495);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1963:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==35) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1963:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKRectangle4510); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle4522); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1971:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1972:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1972:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1973:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle4543);
                    lv_styles_13_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_13_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1989:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==15) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1989:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRectangle4556); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1993:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1994:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1994:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:1995:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle4577);
                    	    lv_styles_15_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
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
                    	    break loop51;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle4591); 

                        	newLeafNode(otherlv_16, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2015:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==20) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2015:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRectangle4606); 

                        	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle4618); 

                        	newLeafNode(otherlv_18, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2023:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2024:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2024:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2025:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle4639);
                    lv_children_19_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_19_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2041:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==15) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2041:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRectangle4652); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2045:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2046:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2046:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2047:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle4673);
                    	    lv_children_21_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
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
                    	    break loop53;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle4687); 

                        	newLeafNode(otherlv_22, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2067:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==39) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2067:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKRectangle4702); 

                        	newLeafNode(otherlv_23, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2071:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2072:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2072:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2073:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle4723);
                    lv_childPlacement_24_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRectangleRule());
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

            otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle4737); 

                	newLeafNode(otherlv_25, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_8());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2101:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2102:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2103:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle4773);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle4783); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2110:1: ruleKRoundedRectangle returns [EObject current=null] : (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
    public final EObject ruleKRoundedRectangle() throws RecognitionException {
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
        AntlrDatatypeRuleToken lv_cornerWidth_3_0 = null;

        AntlrDatatypeRuleToken lv_cornerHeight_5_0 = null;

        EObject lv_placementData_13_0 = null;

        EObject lv_styles_16_0 = null;

        EObject lv_styles_18_0 = null;

        EObject lv_children_22_0 = null;

        EObject lv_children_24_0 = null;

        EObject lv_childPlacement_27_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2113:28: ( (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2114:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2114:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2114:3: otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKRoundedRectangle4820); 

                	newLeafNode(otherlv_0, grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle4832); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKRoundedRectangle4844); 

                	newLeafNode(otherlv_2, grammarAccess.getKRoundedRectangleAccess().getCornerWidthKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2126:1: ( (lv_cornerWidth_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2127:1: (lv_cornerWidth_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2127:1: (lv_cornerWidth_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2128:3: lv_cornerWidth_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4865);
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

            otherlv_4=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKRoundedRectangle4877); 

                	newLeafNode(otherlv_4, grammarAccess.getKRoundedRectangleAccess().getCornerHeightKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2148:1: ( (lv_cornerHeight_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2149:1: (lv_cornerHeight_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2149:1: (lv_cornerHeight_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2150:3: lv_cornerHeight_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4898);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2166:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==32) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2166:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKRoundedRectangle4911); 

                        	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle4923); 

                        	newLeafNode(otherlv_7, grammarAccess.getKRoundedRectangleAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2174:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2175:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2175:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2176:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRoundedRectangleRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRoundedRectangle4946);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2189:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==15) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2189:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRoundedRectangle4959); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2193:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2194:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2194:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2195:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRoundedRectangleRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRoundedRectangle4982);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle4996); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2212:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==34) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2212:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKRoundedRectangle5011); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2216:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2217:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2217:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2218:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle5032);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2234:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==35) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2234:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKRoundedRectangle5047); 

                        	newLeafNode(otherlv_14, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle5059); 

                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2242:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2243:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2243:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2244:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle5080);
                    lv_styles_16_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_16_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2260:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==15) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2260:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRoundedRectangle5093); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2264:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2265:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2265:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2266:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle5114);
                    	    lv_styles_18_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
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
                    	    break loop59;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle5128); 

                        	newLeafNode(otherlv_19, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2286:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==20) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2286:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedRectangle5143); 

                        	newLeafNode(otherlv_20, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle5155); 

                        	newLeafNode(otherlv_21, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2294:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2295:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2295:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2296:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle5176);
                    lv_children_22_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_22_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2312:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==15) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2312:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRoundedRectangle5189); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2316:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2317:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2317:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2318:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle5210);
                    	    lv_children_24_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
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
                    	    break loop61;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle5224); 

                        	newLeafNode(otherlv_25, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2338:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==39) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2338:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKRoundedRectangle5239); 

                        	newLeafNode(otherlv_26, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2342:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2343:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2343:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2344:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle5260);
                    lv_childPlacement_27_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedRectangleRule());
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

            otherlv_28=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle5274); 

                	newLeafNode(otherlv_28, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_11());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2372:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2373:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2374:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl5310);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl5320); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2381:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2384:28: ( ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2385:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2385:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2385:2: () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2385:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2386:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKPolyline_Impl5366); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getKPolylineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl5378); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2399:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==32) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2399:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKPolyline_Impl5391); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl5403); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2407:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2408:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2408:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2409:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKPolyline_ImplRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolyline_Impl5426);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2422:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==15) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2422:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolyline_Impl5439); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2426:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2427:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2427:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2428:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKPolyline_ImplRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolyline_Impl5462);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl5476); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2445:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==34) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2445:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKPolyline_Impl5491); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2449:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2450:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2450:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2451:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl5512);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2467:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==35) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2467:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKPolyline_Impl5527); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl5539); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2475:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2476:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2476:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2477:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl5560);
                    lv_styles_13_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_13_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2493:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==15) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2493:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolyline_Impl5573); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2497:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2498:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2498:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2499:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl5594);
                    	    lv_styles_15_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
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
                    	    break loop67;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl5608); 

                        	newLeafNode(otherlv_16, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2519:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==20) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2519:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolyline_Impl5623); 

                        	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl5635); 

                        	newLeafNode(otherlv_18, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2527:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2528:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2528:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2529:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5656);
                    lv_children_19_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_19_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2545:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==15) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2545:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolyline_Impl5669); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2549:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2550:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2550:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2551:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5690);
                    	    lv_children_21_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
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
                    	    break loop69;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl5704); 

                        	newLeafNode(otherlv_22, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2571:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==39) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2571:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKPolyline_Impl5719); 

                        	newLeafNode(otherlv_23, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2575:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2576:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2576:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2577:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl5740);
                    lv_childPlacement_24_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
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

            otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl5754); 

                	newLeafNode(otherlv_25, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_8());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2605:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2606:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2607:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon5790);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon5800); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2614:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2617:28: ( ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2618:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2618:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2618:2: () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2618:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2619:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKPolygon5846); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getKPolygonKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon5858); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2632:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==32) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2632:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKPolygon5871); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon5883); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2640:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2641:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2641:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2642:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKPolygonRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolygon5906);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2655:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop72:
                    do {
                        int alt72=2;
                        int LA72_0 = input.LA(1);

                        if ( (LA72_0==15) ) {
                            alt72=1;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2655:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolygon5919); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2659:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2660:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2660:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2661:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKPolygonRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolygon5942);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop72;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon5956); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2678:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==34) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2678:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKPolygon5971); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2682:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2683:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2683:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2684:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon5992);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2700:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==35) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2700:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKPolygon6007); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon6019); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2708:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2709:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2709:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2710:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon6040);
                    lv_styles_13_0=ruleKStyle();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	        }
                           		add(
                           			current, 
                           			"styles",
                            		lv_styles_13_0, 
                            		"KStyle");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2726:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==15) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2726:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolygon6053); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2730:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2731:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2731:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2732:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon6074);
                    	    lv_styles_15_0=ruleKStyle();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
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
                    	    break loop75;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon6088); 

                        	newLeafNode(otherlv_16, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2752:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==20) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2752:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolygon6103); 

                        	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon6115); 

                        	newLeafNode(otherlv_18, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2760:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2761:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2761:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2762:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon6136);
                    lv_children_19_0=ruleKRendering();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
                    	        }
                           		add(
                           			current, 
                           			"children",
                            		lv_children_19_0, 
                            		"KRendering");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2778:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==15) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2778:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolygon6149); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKPolygonAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2782:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2783:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2783:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2784:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon6170);
                    	    lv_children_21_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
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
                    	    break loop77;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon6184); 

                        	newLeafNode(otherlv_22, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2804:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==39) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2804:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKPolygon6199); 

                        	newLeafNode(otherlv_23, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2808:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2809:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2809:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2810:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon6220);
                    lv_childPlacement_24_0=ruleKPlacement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolygonRule());
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

            otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon6234); 

                	newLeafNode(otherlv_25, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_8());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2838:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2839:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2840:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage6270);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage6280); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2847:1: ruleKImage returns [EObject current=null] : (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2850:28: ( (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2851:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2851:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2851:3: otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKImage6317); 

                	newLeafNode(otherlv_0, grammarAccess.getKImageAccess().getKImageKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage6329); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKImage6341); 

                	newLeafNode(otherlv_2, grammarAccess.getKImageAccess().getBundleNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2863:1: ( (lv_bundleName_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2864:1: (lv_bundleName_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2864:1: (lv_bundleName_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2865:3: lv_bundleName_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage6362);
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

            otherlv_4=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKImage6374); 

                	newLeafNode(otherlv_4, grammarAccess.getKImageAccess().getImagePathKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2885:1: ( (lv_imagePath_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2886:1: (lv_imagePath_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2886:1: (lv_imagePath_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2887:3: lv_imagePath_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage6395);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2903:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==32) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2903:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKImage6408); 

                        	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage6420); 

                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2911:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2912:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2912:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2913:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKImageRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage6443);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2926:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==15) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2926:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKImage6456); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2930:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2931:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2931:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2932:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKImageRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage6479);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop80;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKImage6493); 

                        	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2949:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==34) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2949:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKImage6508); 

                        	newLeafNode(otherlv_12, grammarAccess.getKImageAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2953:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2954:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2954:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2955:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage6529);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2971:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==35) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2971:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKImage6544); 

                        	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage6556); 

                        	newLeafNode(otherlv_15, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2979:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2980:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2980:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2981:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage6577);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2997:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop83:
                    do {
                        int alt83=2;
                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==15) ) {
                            alt83=1;
                        }


                        switch (alt83) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:2997:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKImage6590); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKImageAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3001:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3002:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3002:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3003:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage6611);
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
                    	    break loop83;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage6625); 

                        	newLeafNode(otherlv_19, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3023:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==20) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3023:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKImage6640); 

                        	newLeafNode(otherlv_20, grammarAccess.getKImageAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage6652); 

                        	newLeafNode(otherlv_21, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3031:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3032:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3032:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3033:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage6673);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3049:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==15) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3049:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKImage6686); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKImageAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3053:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3054:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3054:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3055:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage6707);
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
                    	    break loop85;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage6721); 

                        	newLeafNode(otherlv_25, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3075:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==39) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3075:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKImage6736); 

                        	newLeafNode(otherlv_26, grammarAccess.getKImageAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3079:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3080:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3080:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3081:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage6757);
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

            otherlv_28=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage6771); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3109:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3110:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3111:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc6807);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc6817); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3118:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3121:28: ( ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3122:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3122:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3122:2: () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3122:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3123:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKArc6863); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getKArcKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc6875); 

                	newLeafNode(otherlv_2, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3136:1: (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==50) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3136:3: otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKArc6888); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getStartAngleKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3140:1: ( (lv_startAngle_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3141:1: (lv_startAngle_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3141:1: (lv_startAngle_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3142:3: lv_startAngle_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc6909);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3158:4: (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==51) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3158:6: otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKArc6924); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getArcAngleKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3162:1: ( (lv_arcAngle_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3163:1: (lv_arcAngle_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3163:1: (lv_arcAngle_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3164:3: lv_arcAngle_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc6945);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3180:4: (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==32) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3180:6: otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKArc6960); 

                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getReferencesKeyword_5_0());
                        
                    otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc6972); 

                        	newLeafNode(otherlv_8, grammarAccess.getKArcAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3188:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3189:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3189:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3190:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKArcRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc6995);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3203:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==15) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3203:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKArc7008); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getKArcAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3207:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3208:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3208:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3209:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKArcRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc7031);
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

                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKArc7045); 

                        	newLeafNode(otherlv_12, grammarAccess.getKArcAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3226:3: (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==34) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3226:5: otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) )
                    {
                    otherlv_13=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKArc7060); 

                        	newLeafNode(otherlv_13, grammarAccess.getKArcAccess().getPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3230:1: ( (lv_placementData_14_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3231:1: (lv_placementData_14_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3231:1: (lv_placementData_14_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3232:3: lv_placementData_14_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc7081);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3248:4: (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==35) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3248:6: otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}'
                    {
                    otherlv_15=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKArc7096); 

                        	newLeafNode(otherlv_15, grammarAccess.getKArcAccess().getStylesKeyword_7_0());
                        
                    otherlv_16=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc7108); 

                        	newLeafNode(otherlv_16, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3256:1: ( (lv_styles_17_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3257:1: (lv_styles_17_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3257:1: (lv_styles_17_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3258:3: lv_styles_17_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc7129);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3274:2: (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==15) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3274:4: otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) )
                    	    {
                    	    otherlv_18=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKArc7142); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3278:1: ( (lv_styles_19_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3279:1: (lv_styles_19_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3279:1: (lv_styles_19_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3280:3: lv_styles_19_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc7163);
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
                    	    break loop93;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc7177); 

                        	newLeafNode(otherlv_20, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3300:3: (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==20) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3300:5: otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}'
                    {
                    otherlv_21=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKArc7192); 

                        	newLeafNode(otherlv_21, grammarAccess.getKArcAccess().getChildrenKeyword_8_0());
                        
                    otherlv_22=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc7204); 

                        	newLeafNode(otherlv_22, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3308:1: ( (lv_children_23_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3309:1: (lv_children_23_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3309:1: (lv_children_23_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3310:3: lv_children_23_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc7225);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3326:2: (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==15) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3326:4: otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) )
                    	    {
                    	    otherlv_24=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKArc7238); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getKArcAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3330:1: ( (lv_children_25_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3331:1: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3331:1: (lv_children_25_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3332:3: lv_children_25_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc7259);
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
                    	    break loop95;
                        }
                    } while (true);

                    otherlv_26=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc7273); 

                        	newLeafNode(otherlv_26, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3352:3: (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==39) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3352:5: otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    {
                    otherlv_27=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKArc7288); 

                        	newLeafNode(otherlv_27, grammarAccess.getKArcAccess().getChildPlacementKeyword_9_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3356:1: ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3357:1: (lv_childPlacement_28_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3357:1: (lv_childPlacement_28_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3358:3: lv_childPlacement_28_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc7309);
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

            otherlv_29=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc7323); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3386:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3387:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3388:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea7359);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea7369); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3395:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3398:28: ( ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3399:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3399:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3399:2: () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3399:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3400:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKChildArea7415); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getKChildAreaKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea7427); 

                	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3413:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==32) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3413:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKChildArea7440); 

                        	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea7452); 

                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3421:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3422:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3422:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3423:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea7475);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3436:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop98:
                    do {
                        int alt98=2;
                        int LA98_0 = input.LA(1);

                        if ( (LA98_0==15) ) {
                            alt98=1;
                        }


                        switch (alt98) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3436:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKChildArea7488); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3440:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3441:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3441:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3442:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea7511);
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

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKChildArea7525); 

                        	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3459:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==34) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3459:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKChildArea7540); 

                        	newLeafNode(otherlv_9, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3463:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3464:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3464:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3465:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea7561);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3481:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==35) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3481:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKChildArea7576); 

                        	newLeafNode(otherlv_11, grammarAccess.getKChildAreaAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea7588); 

                        	newLeafNode(otherlv_12, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3489:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3490:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3490:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3491:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea7609);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3507:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop101:
                    do {
                        int alt101=2;
                        int LA101_0 = input.LA(1);

                        if ( (LA101_0==15) ) {
                            alt101=1;
                        }


                        switch (alt101) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3507:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKChildArea7622); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKChildAreaAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3511:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3512:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3512:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3513:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea7643);
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
                    	    break loop101;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea7657); 

                        	newLeafNode(otherlv_16, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            otherlv_17=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea7671); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3545:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3546:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3547:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText7707);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText7717); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3554:1: ruleKText returns [EObject current=null] : ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) ;
    public final EObject ruleKText() throws RecognitionException {
        EObject current = null;

        Token lv_clip_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3557:28: ( ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3558:1: ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3558:1: ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3558:2: ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3558:2: ( (lv_clip_0_0= 'clip' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3559:1: (lv_clip_0_0= 'clip' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3559:1: (lv_clip_0_0= 'clip' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3560:3: lv_clip_0_0= 'clip'
            {
            lv_clip_0_0=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKText7760); 

                    newLeafNode(lv_clip_0_0, grammarAccess.getKTextAccess().getClipClipKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKTextRule());
            	        }
                   		setWithLastConsumed(current, "clip", true, "clip");
            	    

            }


            }

            otherlv_1=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKText7785); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getKTextKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText7797); 

                	newLeafNode(otherlv_2, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3581:1: (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==24) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3581:3: otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKText7810); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getTextKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3585:1: ( (lv_text_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3586:1: (lv_text_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3586:1: (lv_text_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3587:3: lv_text_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText7831);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3603:4: (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==32) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3603:6: otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')'
                    {
                    otherlv_5=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKText7846); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getReferencesKeyword_4_0());
                        
                    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText7858); 

                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getLeftParenthesisKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3611:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3612:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3612:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3613:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKTextRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText7881);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3626:2: (otherlv_8= ',' ( ( ruleEString ) ) )*
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==15) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3626:4: otherlv_8= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_8=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKText7894); 

                    	        	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3630:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3631:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3631:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3632:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKTextRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText7917);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop104;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKText7931); 

                        	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getRightParenthesisKeyword_4_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3649:3: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==34) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3649:5: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                    {
                    otherlv_11=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKText7946); 

                        	newLeafNode(otherlv_11, grammarAccess.getKTextAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3653:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3654:1: (lv_placementData_12_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3654:1: (lv_placementData_12_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3655:3: lv_placementData_12_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText7967);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3671:4: (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==35) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3671:6: otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}'
                    {
                    otherlv_13=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKText7982); 

                        	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getStylesKeyword_6_0());
                        
                    otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText7994); 

                        	newLeafNode(otherlv_14, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3679:1: ( (lv_styles_15_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3680:1: (lv_styles_15_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3680:1: (lv_styles_15_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3681:3: lv_styles_15_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText8015);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3697:2: (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )*
                    loop107:
                    do {
                        int alt107=2;
                        int LA107_0 = input.LA(1);

                        if ( (LA107_0==15) ) {
                            alt107=1;
                        }


                        switch (alt107) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3697:4: otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) )
                    	    {
                    	    otherlv_16=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKText8028); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKTextAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3701:1: ( (lv_styles_17_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3702:1: (lv_styles_17_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3702:1: (lv_styles_17_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3703:3: lv_styles_17_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText8049);
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
                    	    break loop107;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText8063); 

                        	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3723:3: (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==20) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3723:5: otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}'
                    {
                    otherlv_19=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKText8078); 

                        	newLeafNode(otherlv_19, grammarAccess.getKTextAccess().getChildrenKeyword_7_0());
                        
                    otherlv_20=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText8090); 

                        	newLeafNode(otherlv_20, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3731:1: ( (lv_children_21_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3732:1: (lv_children_21_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3732:1: (lv_children_21_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3733:3: lv_children_21_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText8111);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3749:2: (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )*
                    loop109:
                    do {
                        int alt109=2;
                        int LA109_0 = input.LA(1);

                        if ( (LA109_0==15) ) {
                            alt109=1;
                        }


                        switch (alt109) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3749:4: otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) )
                    	    {
                    	    otherlv_22=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKText8124); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getKTextAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3753:1: ( (lv_children_23_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3754:1: (lv_children_23_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3754:1: (lv_children_23_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3755:3: lv_children_23_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText8145);
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
                    	    break loop109;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText8159); 

                        	newLeafNode(otherlv_24, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3775:3: (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==39) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3775:5: otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    {
                    otherlv_25=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKText8174); 

                        	newLeafNode(otherlv_25, grammarAccess.getKTextAccess().getChildPlacementKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3779:1: ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3780:1: (lv_childPlacement_26_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3780:1: (lv_childPlacement_26_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3781:3: lv_childPlacement_26_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText8195);
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

            otherlv_27=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText8209); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3809:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3810:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3811:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering8245);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering8255); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3818:1: ruleKCustomRendering returns [EObject current=null] : (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3821:28: ( (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3822:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3822:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3822:3: otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKCustomRendering8292); 

                	newLeafNode(otherlv_0, grammarAccess.getKCustomRenderingAccess().getKCustomRenderingKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering8304); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKCustomRendering8316); 

                	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3834:1: ( (lv_className_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3835:1: (lv_className_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3835:1: (lv_className_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3836:3: lv_className_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering8337);
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

            otherlv_4=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKCustomRendering8349); 

                	newLeafNode(otherlv_4, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3856:1: ( (lv_bundleName_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3857:1: (lv_bundleName_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3857:1: (lv_bundleName_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3858:3: lv_bundleName_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering8370);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3874:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==32) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3874:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKCustomRendering8383); 

                        	newLeafNode(otherlv_6, grammarAccess.getKCustomRenderingAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering8395); 

                        	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3882:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3883:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3883:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3884:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering8418);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3897:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==15) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3897:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKCustomRendering8431); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3901:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3902:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3902:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3903:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering8454);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop112;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKCustomRendering8468); 

                        	newLeafNode(otherlv_11, grammarAccess.getKCustomRenderingAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3920:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==34) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3920:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKCustomRendering8483); 

                        	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3924:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3925:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3925:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3926:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering8504);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3942:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==35) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3942:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKCustomRendering8519); 

                        	newLeafNode(otherlv_14, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering8531); 

                        	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3950:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3951:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3951:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3952:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering8552);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3968:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop115:
                    do {
                        int alt115=2;
                        int LA115_0 = input.LA(1);

                        if ( (LA115_0==15) ) {
                            alt115=1;
                        }


                        switch (alt115) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3968:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKCustomRendering8565); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3972:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3973:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3973:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3974:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering8586);
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
                    	    break loop115;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering8600); 

                        	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3994:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==20) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:3994:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKCustomRendering8615); 

                        	newLeafNode(otherlv_20, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering8627); 

                        	newLeafNode(otherlv_21, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4002:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4003:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4003:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4004:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering8648);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4020:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop117:
                    do {
                        int alt117=2;
                        int LA117_0 = input.LA(1);

                        if ( (LA117_0==15) ) {
                            alt117=1;
                        }


                        switch (alt117) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4020:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKCustomRendering8661); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4024:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4025:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4025:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4026:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering8682);
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
                    	    break loop117;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering8696); 

                        	newLeafNode(otherlv_25, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4046:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==39) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4046:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKCustomRendering8711); 

                        	newLeafNode(otherlv_26, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4050:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4051:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4051:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4052:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering8732);
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

            otherlv_28=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering8746); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4080:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4081:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4082:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline8782);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline8792); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4089:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4092:28: ( ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4093:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4093:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4093:2: () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4093:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4094:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKSpline8838); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getKSplineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline8850); 

                	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4107:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==32) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4107:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKSpline8863); 

                        	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline8875); 

                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4115:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4116:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4116:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4117:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline8898);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4130:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop120:
                    do {
                        int alt120=2;
                        int LA120_0 = input.LA(1);

                        if ( (LA120_0==15) ) {
                            alt120=1;
                        }


                        switch (alt120) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4130:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKSpline8911); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4134:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4135:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4135:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4136:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline8934);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop120;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKSpline8948); 

                        	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4153:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==34) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4153:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKSpline8963); 

                        	newLeafNode(otherlv_9, grammarAccess.getKSplineAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4157:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4158:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4158:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4159:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline8984);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4175:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==35) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4175:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKSpline8999); 

                        	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline9011); 

                        	newLeafNode(otherlv_12, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4183:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4184:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4184:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4185:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline9032);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4201:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop123:
                    do {
                        int alt123=2;
                        int LA123_0 = input.LA(1);

                        if ( (LA123_0==15) ) {
                            alt123=1;
                        }


                        switch (alt123) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4201:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKSpline9045); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKSplineAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4205:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4206:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4206:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4207:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline9066);
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
                    	    break loop123;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline9080); 

                        	newLeafNode(otherlv_16, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4227:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==20) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4227:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKSpline9095); 

                        	newLeafNode(otherlv_17, grammarAccess.getKSplineAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline9107); 

                        	newLeafNode(otherlv_18, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4235:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4236:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4236:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4237:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline9128);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4253:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==15) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4253:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKSpline9141); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKSplineAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4257:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4258:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4258:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4259:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline9162);
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
                    	    break loop125;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline9176); 

                        	newLeafNode(otherlv_22, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4279:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==39) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4279:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKSpline9191); 

                        	newLeafNode(otherlv_23, grammarAccess.getKSplineAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4283:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4284:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4284:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4285:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline9212);
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

            otherlv_25=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline9226); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4313:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4314:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4315:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData9262);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData9272); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4322:1: ruleKDecoratorPlacementData returns [EObject current=null] : ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' ) ;
    public final EObject ruleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        Token lv_relative_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_location_4_0 = null;

        AntlrDatatypeRuleToken lv_xOffset_6_0 = null;

        AntlrDatatypeRuleToken lv_yOffset_8_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4325:28: ( ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4326:1: ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4326:1: ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4326:2: ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4326:2: ( (lv_relative_0_0= 'relative' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4327:1: (lv_relative_0_0= 'relative' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4327:1: (lv_relative_0_0= 'relative' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4328:3: lv_relative_0_0= 'relative'
            {
            lv_relative_0_0=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKDecoratorPlacementData9315); 

                    newLeafNode(lv_relative_0_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
            	        }
                   		setWithLastConsumed(current, "relative", true, "relative");
            	    

            }


            }

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKDecoratorPlacementData9340); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getKDecoratorPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData9352); 

                	newLeafNode(otherlv_2, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKDecoratorPlacementData9364); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4353:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4354:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4354:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4355:3: lv_location_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getLocationEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData9385);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4371:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==61) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4371:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKDecoratorPlacementData9398); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4375:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4376:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4376:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4377:3: lv_xOffset_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData9419);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4393:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==62) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4393:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKDecoratorPlacementData9434); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4397:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4398:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4398:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4399:3: lv_yOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData9455);
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

            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKDecoratorPlacementData9469); 

                	newLeafNode(otherlv_9, grammarAccess.getKDecoratorPlacementDataAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4427:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4428:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4429:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData9505);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData9515); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4436:1: ruleKGridPlacementData returns [EObject current=null] : (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4439:28: ( (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4440:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4440:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4440:3: otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKGridPlacementData9552); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData9564); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKGridPlacementData9576); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4452:1: ( (lv_widthHint_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4453:1: (lv_widthHint_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4453:1: (lv_widthHint_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4454:3: lv_widthHint_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData9597);
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

            otherlv_4=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKGridPlacementData9609); 

                	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4474:1: ( (lv_heightHint_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4475:1: (lv_heightHint_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4475:1: (lv_heightHint_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4476:3: lv_heightHint_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData9630);
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

            otherlv_6=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKGridPlacementData9642); 

                	newLeafNode(otherlv_6, grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4496:1: ( (lv_horizontalIndent_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4497:1: (lv_horizontalIndent_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4497:1: (lv_horizontalIndent_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4498:3: lv_horizontalIndent_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData9663);
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

            otherlv_8=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKGridPlacementData9675); 

                	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getVerticalIndentKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4518:1: ( (lv_verticalIndent_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4519:1: (lv_verticalIndent_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4519:1: (lv_verticalIndent_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4520:3: lv_verticalIndent_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getVerticalIndentEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData9696);
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

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKGridPlacementData9708); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4548:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4549:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4550:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData9744);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData9754); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4557:1: ruleKStackPlacementData returns [EObject current=null] : (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4560:28: ( (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4561:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4561:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4561:3: otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKStackPlacementData9791); 

                	newLeafNode(otherlv_0, grammarAccess.getKStackPlacementDataAccess().getKStackPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData9803); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKStackPlacementData9815); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4573:1: ( (lv_insetRight_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4574:1: (lv_insetRight_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4574:1: (lv_insetRight_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4575:3: lv_insetRight_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData9836);
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

            otherlv_4=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKStackPlacementData9848); 

                	newLeafNode(otherlv_4, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4595:1: ( (lv_insetBottom_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4596:1: (lv_insetBottom_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4596:1: (lv_insetBottom_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4597:3: lv_insetBottom_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData9869);
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

            otherlv_6=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKStackPlacementData9881); 

                	newLeafNode(otherlv_6, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4617:1: ( (lv_insetLeft_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4618:1: (lv_insetLeft_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4618:1: (lv_insetLeft_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4619:3: lv_insetLeft_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData9902);
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

            otherlv_8=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKStackPlacementData9914); 

                	newLeafNode(otherlv_8, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4639:1: ( (lv_insetTop_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4640:1: (lv_insetTop_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4640:1: (lv_insetTop_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4641:3: lv_insetTop_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData9935);
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

            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKStackPlacementData9947); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4669:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4670:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4671:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData9983);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData9993); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4678:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4681:28: ( (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4682:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4682:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4682:3: otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKDirectPlacementData10030); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getKDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData10042); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKDirectPlacementData10054); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4694:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4695:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4695:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4696:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData10075);
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

            otherlv_4=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKDirectPlacementData10087); 

                	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4716:1: ( (lv_bottomRight_5_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4717:1: (lv_bottomRight_5_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4717:1: (lv_bottomRight_5_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4718:3: lv_bottomRight_5_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData10108);
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

            otherlv_6=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKDirectPlacementData10120); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4746:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4747:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4748:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData10156);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData10166); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4755:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' ) ;
    public final EObject ruleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_points_4_0 = null;

        EObject lv_points_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4758:28: ( (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4759:1: (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4759:1: (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4759:3: otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKPolylinePlacementData10203); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getKPolylinePlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData10215); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKPolylinePlacementData10227); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_2());
                
            otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData10239); 

                	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4775:1: ( (lv_points_4_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4776:1: (lv_points_4_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4776:1: (lv_points_4_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4777:3: lv_points_4_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData10260);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4793:2: (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )*
            loop130:
            do {
                int alt130=2;
                int LA130_0 = input.LA(1);

                if ( (LA130_0==15) ) {
                    alt130=1;
                }


                switch (alt130) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4793:4: otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) )
            	    {
            	    otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolylinePlacementData10273); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_5_0());
            	        
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4797:1: ( (lv_points_6_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4798:1: (lv_points_6_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4798:1: (lv_points_6_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4799:3: lv_points_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData10294);
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
            	    break loop130;
                }
            } while (true);

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolylinePlacementData10308); 

                	newLeafNode(otherlv_7, grammarAccess.getKPolylinePlacementDataAccess().getRightCurlyBracketKeyword_6());
                
            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolylinePlacementData10320); 

                	newLeafNode(otherlv_8, grammarAccess.getKPolylinePlacementDataAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4831:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4832:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4833:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat10357);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat10368); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4840:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4843:28: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4844:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4844:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4844:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4844:2: (kw= '-' )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==78) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4845:2: kw= '-'
                    {
                    kw=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleEFloat10407); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4850:3: (this_INT_1= RULE_INT )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==RULE_INT) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4850:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat10425); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleEFloat10445); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 
                
            this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat10460); 

            		current.merge(this_INT_3);
                
             
                newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4870:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( ((LA135_0>=80 && LA135_0<=81)) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4870:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4870:2: (kw= 'E' | kw= 'e' )
                    int alt133=2;
                    int LA133_0 = input.LA(1);

                    if ( (LA133_0==80) ) {
                        alt133=1;
                    }
                    else if ( (LA133_0==81) ) {
                        alt133=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 133, 0, input);

                        throw nvae;
                    }
                    switch (alt133) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4871:2: kw= 'E'
                            {
                            kw=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleEFloat10480); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4878:2: kw= 'e'
                            {
                            kw=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleEFloat10499); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4883:2: (kw= '-' )?
                    int alt134=2;
                    int LA134_0 = input.LA(1);

                    if ( (LA134_0==78) ) {
                        alt134=1;
                    }
                    switch (alt134) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4884:2: kw= '-'
                            {
                            kw=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleEFloat10514); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat10531); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4906:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4907:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4908:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition10580);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition10590); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4915:1: ruleKPosition returns [EObject current=null] : (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4918:28: ( (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4919:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4919:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4919:3: otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKPosition10627); 

                	newLeafNode(otherlv_0, grammarAccess.getKPositionAccess().getKPositionKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPosition10639); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKPosition10651); 

                	newLeafNode(otherlv_2, grammarAccess.getKPositionAccess().getXKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4931:1: ( (lv_x_3_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4932:1: (lv_x_3_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4932:1: (lv_x_3_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4933:3: lv_x_3_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition10672);
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

            otherlv_4=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKPosition10684); 

                	newLeafNode(otherlv_4, grammarAccess.getKPositionAccess().getYKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4953:1: ( (lv_y_5_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4954:1: (lv_y_5_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4954:1: (lv_y_5_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4955:3: lv_y_5_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition10705);
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

            otherlv_6=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPosition10717); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4983:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4984:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4985:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition10753);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition10763); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4992:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4995:28: ( ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4996:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4996:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4996:2: () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4996:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:4997:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKLeftPosition10809); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getKLeftPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLeftPosition10821); 

                	newLeafNode(otherlv_2, grammarAccess.getKLeftPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5010:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==86) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5010:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKLeftPosition10834); 

                        	newLeafNode(otherlv_3, grammarAccess.getKLeftPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5014:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5015:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5015:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5016:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition10855);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5032:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==58) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5032:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKLeftPosition10870); 

                        	newLeafNode(otherlv_5, grammarAccess.getKLeftPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5036:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5037:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5037:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5038:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition10891);
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

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLeftPosition10905); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5066:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5067:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5068:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition10941);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition10951); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5075:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5078:28: ( ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5079:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5079:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5079:2: () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5079:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5080:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleKRightPosition10997); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getKRightPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRightPosition11009); 

                	newLeafNode(otherlv_2, grammarAccess.getKRightPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5093:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==86) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5093:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKRightPosition11022); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRightPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5097:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5098:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5098:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5099:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition11043);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5115:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==58) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5115:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKRightPosition11058); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRightPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5119:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5120:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5120:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5121:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition11079);
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

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRightPosition11093); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5149:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5150:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5151:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition11129);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition11139); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5158:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5161:28: ( ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5162:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5162:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5162:2: () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5162:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5163:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleKTopPosition11185); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getKTopPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKTopPosition11197); 

                	newLeafNode(otherlv_2, grammarAccess.getKTopPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5176:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==86) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5176:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKTopPosition11210); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTopPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5180:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5181:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5181:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5182:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition11231);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5198:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==58) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5198:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKTopPosition11246); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTopPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5202:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5203:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5203:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5204:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition11267);
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

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKTopPosition11281); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5232:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5233:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5234:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition11317);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition11327); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5241:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5244:28: ( ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5245:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5245:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5245:2: () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5245:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5246:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,89,FollowSets000.FOLLOW_89_in_ruleKBottomPosition11373); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getKBottomPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBottomPosition11385); 

                	newLeafNode(otherlv_2, grammarAccess.getKBottomPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5259:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==86) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5259:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKBottomPosition11398); 

                        	newLeafNode(otherlv_3, grammarAccess.getKBottomPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5263:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5264:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5264:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5265:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition11419);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5281:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==58) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5281:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKBottomPosition11434); 

                        	newLeafNode(otherlv_5, grammarAccess.getKBottomPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5285:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5286:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5286:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5287:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition11455);
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

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKBottomPosition11469); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5315:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5316:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5317:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor11505);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor11515); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5324:1: ruleKForegroundColor returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) ;
    public final EObject ruleKForegroundColor() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_red_4_0 = null;

        AntlrDatatypeRuleToken lv_green_6_0 = null;

        AntlrDatatypeRuleToken lv_blue_8_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5327:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5328:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5328:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5328:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5328:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5329:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5329:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5330:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKForegroundColor11558); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKForegroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKForegroundColorRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKForegroundColor11583); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getKForegroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKForegroundColor11595); 

                	newLeafNode(otherlv_2, grammarAccess.getKForegroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleKForegroundColor11607); 

                	newLeafNode(otherlv_3, grammarAccess.getKForegroundColorAccess().getRedKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5355:1: ( (lv_red_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5356:1: (lv_red_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5356:1: (lv_red_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5357:3: lv_red_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor11628);
            lv_red_4_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"red",
                    		lv_red_4_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleKForegroundColor11640); 

                	newLeafNode(otherlv_5, grammarAccess.getKForegroundColorAccess().getGreenKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5377:1: ( (lv_green_6_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5378:1: (lv_green_6_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5378:1: (lv_green_6_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5379:3: lv_green_6_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor11661);
            lv_green_6_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"green",
                    		lv_green_6_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleKForegroundColor11673); 

                	newLeafNode(otherlv_7, grammarAccess.getKForegroundColorAccess().getBlueKeyword_7());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5399:1: ( (lv_blue_8_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5400:1: (lv_blue_8_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5400:1: (lv_blue_8_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5401:3: lv_blue_8_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_8_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor11694);
            lv_blue_8_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKForegroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"blue",
                    		lv_blue_8_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKForegroundColor11706); 

                	newLeafNode(otherlv_9, grammarAccess.getKForegroundColorAccess().getRightCurlyBracketKeyword_9());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5429:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5430:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5431:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor11742);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor11752); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5438:1: ruleKBackgroundColor returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) ;
    public final EObject ruleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_red_4_0 = null;

        AntlrDatatypeRuleToken lv_green_6_0 = null;

        AntlrDatatypeRuleToken lv_blue_8_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5441:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5442:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5442:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5442:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5442:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5443:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5443:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5444:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKBackgroundColor11795); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKBackgroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKBackgroundColorRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleKBackgroundColor11820); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getKBackgroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBackgroundColor11832); 

                	newLeafNode(otherlv_2, grammarAccess.getKBackgroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleKBackgroundColor11844); 

                	newLeafNode(otherlv_3, grammarAccess.getKBackgroundColorAccess().getRedKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5469:1: ( (lv_red_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5470:1: (lv_red_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5470:1: (lv_red_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5471:3: lv_red_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor11865);
            lv_red_4_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"red",
                    		lv_red_4_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleKBackgroundColor11877); 

                	newLeafNode(otherlv_5, grammarAccess.getKBackgroundColorAccess().getGreenKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5491:1: ( (lv_green_6_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5492:1: (lv_green_6_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5492:1: (lv_green_6_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5493:3: lv_green_6_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor11898);
            lv_green_6_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"green",
                    		lv_green_6_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleKBackgroundColor11910); 

                	newLeafNode(otherlv_7, grammarAccess.getKBackgroundColorAccess().getBlueKeyword_7());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5513:1: ( (lv_blue_8_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5514:1: (lv_blue_8_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5514:1: (lv_blue_8_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5515:3: lv_blue_8_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_8_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor11931);
            lv_blue_8_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKBackgroundColorRule());
            	        }
                   		set(
                   			current, 
                   			"blue",
                    		lv_blue_8_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKBackgroundColor11943); 

                	newLeafNode(otherlv_9, grammarAccess.getKBackgroundColorAccess().getRightCurlyBracketKeyword_9());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5543:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5544:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5545:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth11979);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth11989); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5552:1: ruleKLineWidth returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_lineWidth_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5555:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5556:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5556:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5556:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5556:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5557:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5557:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5558:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKLineWidth12032); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineWidthAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKLineWidthRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleKLineWidth12057); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineWidthAccess().getKLineWidthKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLineWidth12069); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineWidthAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleKLineWidth12081); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5583:1: ( (lv_lineWidth_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5584:1: (lv_lineWidth_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5584:1: (lv_lineWidth_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5585:3: lv_lineWidth_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth12102);
            lv_lineWidth_4_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLineWidthRule());
            	        }
                   		set(
                   			current, 
                   			"lineWidth",
                    		lv_lineWidth_4_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLineWidth12114); 

                	newLeafNode(otherlv_5, grammarAccess.getKLineWidthAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5613:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5614:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5615:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility12150);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility12160); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5622:1: ruleKVisibility returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_lineVisible_1_0=null;
        Token lv_filled_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5625:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5626:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5626:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5626:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5626:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5627:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5627:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5628:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKVisibility12203); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKVisibilityAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVisibilityRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5641:2: ( (lv_lineVisible_1_0= 'lineVisible' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5642:1: (lv_lineVisible_1_0= 'lineVisible' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5642:1: (lv_lineVisible_1_0= 'lineVisible' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5643:3: lv_lineVisible_1_0= 'lineVisible'
            {
            lv_lineVisible_1_0=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleKVisibility12234); 

                    newLeafNode(lv_lineVisible_1_0, grammarAccess.getKVisibilityAccess().getLineVisibleLineVisibleKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVisibilityRule());
            	        }
                   		setWithLastConsumed(current, "lineVisible", true, "lineVisible");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5656:2: ( (lv_filled_2_0= 'filled' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5657:1: (lv_filled_2_0= 'filled' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5657:1: (lv_filled_2_0= 'filled' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5658:3: lv_filled_2_0= 'filled'
            {
            lv_filled_2_0=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleKVisibility12265); 

                    newLeafNode(lv_filled_2_0, grammarAccess.getKVisibilityAccess().getFilledFilledKeyword_2_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVisibilityRule());
            	        }
                   		setWithLastConsumed(current, "filled", true, "filled");
            	    

            }


            }

            otherlv_3=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleKVisibility12290); 

                	newLeafNode(otherlv_3, grammarAccess.getKVisibilityAccess().getKVisibilityKeyword_3());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleKLineStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5683:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5684:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5685:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle12326);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle12336); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5692:1: ruleKLineStyle returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_lineStyle_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5695:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5696:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5696:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5696:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5696:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5697:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5697:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5698:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKLineStyle12379); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineStyleAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKLineStyleRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,100,FollowSets000.FOLLOW_100_in_ruleKLineStyle12404); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getKLineStyleKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLineStyle12416); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineStyleAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,101,FollowSets000.FOLLOW_101_in_ruleKLineStyle12428); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5723:1: ( (lv_lineStyle_4_0= ruleLineStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5724:1: (lv_lineStyle_4_0= ruleLineStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5724:1: (lv_lineStyle_4_0= ruleLineStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5725:3: lv_lineStyle_4_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle12449);
            lv_lineStyle_4_0=ruleLineStyle();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLineStyleRule());
            	        }
                   		set(
                   			current, 
                   			"lineStyle",
                    		lv_lineStyle_4_0, 
                    		"LineStyle");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKLineStyle12461); 

                	newLeafNode(otherlv_5, grammarAccess.getKLineStyleAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5753:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5754:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5755:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment12497);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment12507); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5762:1: ruleKVerticalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_verticalAlignment_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5765:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5766:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5766:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5766:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5766:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5767:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5767:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5768:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKVerticalAlignment12550); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKVerticalAlignmentAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,102,FollowSets000.FOLLOW_102_in_ruleKVerticalAlignment12575); 

                	newLeafNode(otherlv_1, grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKVerticalAlignment12587); 

                	newLeafNode(otherlv_2, grammarAccess.getKVerticalAlignmentAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,103,FollowSets000.FOLLOW_103_in_ruleKVerticalAlignment12599); 

                	newLeafNode(otherlv_3, grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5793:1: ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5794:1: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5794:1: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5795:3: lv_verticalAlignment_4_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment12620);
            lv_verticalAlignment_4_0=ruleVerticalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKVerticalAlignmentRule());
            	        }
                   		set(
                   			current, 
                   			"verticalAlignment",
                    		lv_verticalAlignment_4_0, 
                    		"VerticalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKVerticalAlignment12632); 

                	newLeafNode(otherlv_5, grammarAccess.getKVerticalAlignmentAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5823:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5824:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5825:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment12668);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment12678); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5832:1: ruleKHorizontalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_horizontalAlignment_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5835:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5836:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5836:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5836:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5836:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5837:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5837:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5838:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKHorizontalAlignment12721); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKHorizontalAlignmentAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,104,FollowSets000.FOLLOW_104_in_ruleKHorizontalAlignment12746); 

                	newLeafNode(otherlv_1, grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKHorizontalAlignment12758); 

                	newLeafNode(otherlv_2, grammarAccess.getKHorizontalAlignmentAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,105,FollowSets000.FOLLOW_105_in_ruleKHorizontalAlignment12770); 

                	newLeafNode(otherlv_3, grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5863:1: ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5864:1: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5864:1: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5865:3: lv_horizontalAlignment_4_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment12791);
            lv_horizontalAlignment_4_0=ruleHorizontalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKHorizontalAlignmentRule());
            	        }
                   		set(
                   			current, 
                   			"horizontalAlignment",
                    		lv_horizontalAlignment_4_0, 
                    		"HorizontalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKHorizontalAlignment12803); 

                	newLeafNode(otherlv_5, grammarAccess.getKHorizontalAlignmentAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleEInt"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5893:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5894:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5895:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt12840);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt12851); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5902:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5905:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5906:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5906:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5906:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5906:2: (kw= '-' )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==78) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5907:2: kw= '-'
                    {
                    kw=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleEInt12890); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt12907); 

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


    // $ANTLR start "entryRuleKGridPlacement"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5927:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5928:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5929:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement12952);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement12962); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5936:1: ruleKGridPlacement returns [EObject current=null] : (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_numColumns_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5939:28: ( (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5940:1: (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5940:1: (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5940:3: otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,106,FollowSets000.FOLLOW_106_in_ruleKGridPlacement12999); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementAccess().getKGridPlacementKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacement13011); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,107,FollowSets000.FOLLOW_107_in_ruleKGridPlacement13023); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementAccess().getNumColumnsKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5952:1: ( (lv_numColumns_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5953:1: (lv_numColumns_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5953:1: (lv_numColumns_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5954:3: lv_numColumns_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement13044);
            lv_numColumns_3_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKGridPlacementRule());
            	        }
                   		set(
                   			current, 
                   			"numColumns",
                    		lv_numColumns_3_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKGridPlacement13056); 

                	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5982:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5983:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5984:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement13092);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement13102); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5991:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'KStackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5994:28: ( ( () otherlv_1= 'KStackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5995:1: ( () otherlv_1= 'KStackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5995:1: ( () otherlv_1= 'KStackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5995:2: () otherlv_1= 'KStackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5995:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:5996:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,108,FollowSets000.FOLLOW_108_in_ruleKStackPlacement13148); 

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


    // $ANTLR start "entryRuleKShapeLayout"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6013:1: entryRuleKShapeLayout returns [EObject current=null] : iv_ruleKShapeLayout= ruleKShapeLayout EOF ;
    public final EObject entryRuleKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShapeLayout = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6014:2: (iv_ruleKShapeLayout= ruleKShapeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6015:2: iv_ruleKShapeLayout= ruleKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getKShapeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout13184);
            iv_ruleKShapeLayout=ruleKShapeLayout();

            state._fsp--;

             current =iv_ruleKShapeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKShapeLayout13194); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6022:1: ruleKShapeLayout returns [EObject current=null] : ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' ) ;
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
        AntlrDatatypeRuleToken lv_xpos_4_0 = null;

        AntlrDatatypeRuleToken lv_ypos_6_0 = null;

        AntlrDatatypeRuleToken lv_width_8_0 = null;

        AntlrDatatypeRuleToken lv_height_10_0 = null;

        EObject lv_insets_12_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6025:28: ( ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6026:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6026:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6026:2: () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6026:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6027:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,109,FollowSets000.FOLLOW_109_in_ruleKShapeLayout13240); 

                	newLeafNode(otherlv_1, grammarAccess.getKShapeLayoutAccess().getKShapeLayoutKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKShapeLayout13252); 

                	newLeafNode(otherlv_2, grammarAccess.getKShapeLayoutAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6040:1: (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==110) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6040:3: otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,110,FollowSets000.FOLLOW_110_in_ruleKShapeLayout13265); 

                        	newLeafNode(otherlv_3, grammarAccess.getKShapeLayoutAccess().getXposKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6044:1: ( (lv_xpos_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6045:1: (lv_xpos_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6045:1: (lv_xpos_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6046:3: lv_xpos_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getXposEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout13286);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6062:4: (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==111) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6062:6: otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,111,FollowSets000.FOLLOW_111_in_ruleKShapeLayout13301); 

                        	newLeafNode(otherlv_5, grammarAccess.getKShapeLayoutAccess().getYposKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6066:1: ( (lv_ypos_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6067:1: (lv_ypos_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6067:1: (lv_ypos_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6068:3: lv_ypos_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getYposEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout13322);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6084:4: (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==112) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6084:6: otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,112,FollowSets000.FOLLOW_112_in_ruleKShapeLayout13337); 

                        	newLeafNode(otherlv_7, grammarAccess.getKShapeLayoutAccess().getWidthKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6088:1: ( (lv_width_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6089:1: (lv_width_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6089:1: (lv_width_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6090:3: lv_width_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getWidthEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout13358);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6106:4: (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==113) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6106:6: otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,113,FollowSets000.FOLLOW_113_in_ruleKShapeLayout13373); 

                        	newLeafNode(otherlv_9, grammarAccess.getKShapeLayoutAccess().getHeightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6110:1: ( (lv_height_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6111:1: (lv_height_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6111:1: (lv_height_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6112:3: lv_height_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getHeightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout13394);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6128:4: (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==114) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6128:6: otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) )
                    {
                    otherlv_11=(Token)match(input,114,FollowSets000.FOLLOW_114_in_ruleKShapeLayout13409); 

                        	newLeafNode(otherlv_11, grammarAccess.getKShapeLayoutAccess().getInsetsKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6132:1: ( (lv_insets_12_0= ruleKInsets ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6133:1: (lv_insets_12_0= ruleKInsets )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6133:1: (lv_insets_12_0= ruleKInsets )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6134:3: lv_insets_12_0= ruleKInsets
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getInsetsKInsetsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_ruleKShapeLayout13430);
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

            otherlv_13=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKShapeLayout13444); 

                	newLeafNode(otherlv_13, grammarAccess.getKShapeLayoutAccess().getRightCurlyBracketKeyword_8());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6162:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6163:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6164:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets13480);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets13490); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6171:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6174:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6175:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6175:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6175:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6175:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6176:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,115,FollowSets000.FOLLOW_115_in_ruleKInsets13536); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets13548); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6189:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==116) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6189:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,116,FollowSets000.FOLLOW_116_in_ruleKInsets13561); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6193:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6194:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6194:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6195:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets13582);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6211:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==117) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6211:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,117,FollowSets000.FOLLOW_117_in_ruleKInsets13597); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6215:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6216:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6216:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6217:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets13618);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6233:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==118) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6233:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,118,FollowSets000.FOLLOW_118_in_ruleKInsets13633); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6237:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6238:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6238:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6239:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets13654);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6255:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==119) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6255:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,119,FollowSets000.FOLLOW_119_in_ruleKInsets13669); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6259:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6260:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6260:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6261:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets13690);
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

            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKInsets13704); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6289:1: entryRuleKEdgeLayout returns [EObject current=null] : iv_ruleKEdgeLayout= ruleKEdgeLayout EOF ;
    public final EObject entryRuleKEdgeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEdgeLayout = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6290:2: (iv_ruleKEdgeLayout= ruleKEdgeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6291:2: iv_ruleKEdgeLayout= ruleKEdgeLayout EOF
            {
             newCompositeNode(grammarAccess.getKEdgeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEdgeLayout_in_entryRuleKEdgeLayout13740);
            iv_ruleKEdgeLayout=ruleKEdgeLayout();

            state._fsp--;

             current =iv_ruleKEdgeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEdgeLayout13750); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6298:1: ruleKEdgeLayout returns [EObject current=null] : (otherlv_0= 'KEdgeLayout' otherlv_1= '{' (otherlv_2= 'bendPoints' otherlv_3= '{' ( (lv_bendPoints_4_0= ruleKPoint ) ) (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )* otherlv_7= '}' )? otherlv_8= 'sourcePoint' ( (lv_sourcePoint_9_0= ruleKPoint ) ) otherlv_10= 'targetPoint' ( (lv_targetPoint_11_0= ruleKPoint ) ) otherlv_12= '}' ) ;
    public final EObject ruleKEdgeLayout() throws RecognitionException {
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
        EObject lv_bendPoints_4_0 = null;

        EObject lv_bendPoints_6_0 = null;

        EObject lv_sourcePoint_9_0 = null;

        EObject lv_targetPoint_11_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6301:28: ( (otherlv_0= 'KEdgeLayout' otherlv_1= '{' (otherlv_2= 'bendPoints' otherlv_3= '{' ( (lv_bendPoints_4_0= ruleKPoint ) ) (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )* otherlv_7= '}' )? otherlv_8= 'sourcePoint' ( (lv_sourcePoint_9_0= ruleKPoint ) ) otherlv_10= 'targetPoint' ( (lv_targetPoint_11_0= ruleKPoint ) ) otherlv_12= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6302:1: (otherlv_0= 'KEdgeLayout' otherlv_1= '{' (otherlv_2= 'bendPoints' otherlv_3= '{' ( (lv_bendPoints_4_0= ruleKPoint ) ) (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )* otherlv_7= '}' )? otherlv_8= 'sourcePoint' ( (lv_sourcePoint_9_0= ruleKPoint ) ) otherlv_10= 'targetPoint' ( (lv_targetPoint_11_0= ruleKPoint ) ) otherlv_12= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6302:1: (otherlv_0= 'KEdgeLayout' otherlv_1= '{' (otherlv_2= 'bendPoints' otherlv_3= '{' ( (lv_bendPoints_4_0= ruleKPoint ) ) (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )* otherlv_7= '}' )? otherlv_8= 'sourcePoint' ( (lv_sourcePoint_9_0= ruleKPoint ) ) otherlv_10= 'targetPoint' ( (lv_targetPoint_11_0= ruleKPoint ) ) otherlv_12= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6302:3: otherlv_0= 'KEdgeLayout' otherlv_1= '{' (otherlv_2= 'bendPoints' otherlv_3= '{' ( (lv_bendPoints_4_0= ruleKPoint ) ) (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )* otherlv_7= '}' )? otherlv_8= 'sourcePoint' ( (lv_sourcePoint_9_0= ruleKPoint ) ) otherlv_10= 'targetPoint' ( (lv_targetPoint_11_0= ruleKPoint ) ) otherlv_12= '}'
            {
            otherlv_0=(Token)match(input,120,FollowSets000.FOLLOW_120_in_ruleKEdgeLayout13787); 

                	newLeafNode(otherlv_0, grammarAccess.getKEdgeLayoutAccess().getKEdgeLayoutKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdgeLayout13799); 

                	newLeafNode(otherlv_1, grammarAccess.getKEdgeLayoutAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6310:1: (otherlv_2= 'bendPoints' otherlv_3= '{' ( (lv_bendPoints_4_0= ruleKPoint ) ) (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )* otherlv_7= '}' )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==121) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6310:3: otherlv_2= 'bendPoints' otherlv_3= '{' ( (lv_bendPoints_4_0= ruleKPoint ) ) (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )* otherlv_7= '}'
                    {
                    otherlv_2=(Token)match(input,121,FollowSets000.FOLLOW_121_in_ruleKEdgeLayout13812); 

                        	newLeafNode(otherlv_2, grammarAccess.getKEdgeLayoutAccess().getBendPointsKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEdgeLayout13824); 

                        	newLeafNode(otherlv_3, grammarAccess.getKEdgeLayoutAccess().getLeftCurlyBracketKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6318:1: ( (lv_bendPoints_4_0= ruleKPoint ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6319:1: (lv_bendPoints_4_0= ruleKPoint )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6319:1: (lv_bendPoints_4_0= ruleKPoint )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6320:3: lv_bendPoints_4_0= ruleKPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13845);
                    lv_bendPoints_4_0=ruleKPoint();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    	        }
                           		add(
                           			current, 
                           			"bendPoints",
                            		lv_bendPoints_4_0, 
                            		"KPoint");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6336:2: (otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) ) )*
                    loop154:
                    do {
                        int alt154=2;
                        int LA154_0 = input.LA(1);

                        if ( (LA154_0==15) ) {
                            alt154=1;
                        }


                        switch (alt154) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6336:4: otherlv_5= ',' ( (lv_bendPoints_6_0= ruleKPoint ) )
                    	    {
                    	    otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEdgeLayout13858); 

                    	        	newLeafNode(otherlv_5, grammarAccess.getKEdgeLayoutAccess().getCommaKeyword_2_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6340:1: ( (lv_bendPoints_6_0= ruleKPoint ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6341:1: (lv_bendPoints_6_0= ruleKPoint )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6341:1: (lv_bendPoints_6_0= ruleKPoint )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6342:3: lv_bendPoints_6_0= ruleKPoint
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getBendPointsKPointParserRuleCall_2_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13879);
                    	    lv_bendPoints_6_0=ruleKPoint();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"bendPoints",
                    	            		lv_bendPoints_6_0, 
                    	            		"KPoint");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop154;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdgeLayout13893); 

                        	newLeafNode(otherlv_7, grammarAccess.getKEdgeLayoutAccess().getRightCurlyBracketKeyword_2_4());
                        

                    }
                    break;

            }

            otherlv_8=(Token)match(input,122,FollowSets000.FOLLOW_122_in_ruleKEdgeLayout13907); 

                	newLeafNode(otherlv_8, grammarAccess.getKEdgeLayoutAccess().getSourcePointKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6366:1: ( (lv_sourcePoint_9_0= ruleKPoint ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6367:1: (lv_sourcePoint_9_0= ruleKPoint )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6367:1: (lv_sourcePoint_9_0= ruleKPoint )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6368:3: lv_sourcePoint_9_0= ruleKPoint
            {
             
            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getSourcePointKPointParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13928);
            lv_sourcePoint_9_0=ruleKPoint();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
            	        }
                   		set(
                   			current, 
                   			"sourcePoint",
                    		lv_sourcePoint_9_0, 
                    		"KPoint");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_10=(Token)match(input,123,FollowSets000.FOLLOW_123_in_ruleKEdgeLayout13940); 

                	newLeafNode(otherlv_10, grammarAccess.getKEdgeLayoutAccess().getTargetPointKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6388:1: ( (lv_targetPoint_11_0= ruleKPoint ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6389:1: (lv_targetPoint_11_0= ruleKPoint )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6389:1: (lv_targetPoint_11_0= ruleKPoint )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6390:3: lv_targetPoint_11_0= ruleKPoint
            {
             
            	        newCompositeNode(grammarAccess.getKEdgeLayoutAccess().getTargetPointKPointParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_ruleKEdgeLayout13961);
            lv_targetPoint_11_0=ruleKPoint();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKEdgeLayoutRule());
            	        }
                   		set(
                   			current, 
                   			"targetPoint",
                    		lv_targetPoint_11_0, 
                    		"KPoint");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_12=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEdgeLayout13973); 

                	newLeafNode(otherlv_12, grammarAccess.getKEdgeLayoutAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6418:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6419:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6420:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint14009);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint14019); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6427:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_x_4_0 = null;

        AntlrDatatypeRuleToken lv_y_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6430:28: ( ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6431:1: ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6431:1: ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6431:2: () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6431:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6432:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,124,FollowSets000.FOLLOW_124_in_ruleKPoint14065); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPoint14077); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6445:1: (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==83) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6445:3: otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKPoint14090); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPointAccess().getXKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6449:1: ( (lv_x_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6450:1: (lv_x_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6450:1: (lv_x_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6451:3: lv_x_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint14111);
                    lv_x_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPointRule());
                    	        }
                           		set(
                           			current, 
                           			"x",
                            		lv_x_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6467:4: (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==84) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6467:6: otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKPoint14126); 

                        	newLeafNode(otherlv_5, grammarAccess.getKPointAccess().getYKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6471:1: ( (lv_y_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6472:1: (lv_y_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6472:1: (lv_y_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6473:3: lv_y_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint14147);
                    lv_y_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPointRule());
                    	        }
                           		set(
                           			current, 
                           			"y",
                            		lv_y_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPoint14161); 

                	newLeafNode(otherlv_7, grammarAccess.getKPointAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "ruleLineStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6501:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6503:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6504:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6504:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt158=5;
            switch ( input.LA(1) ) {
            case 125:
                {
                alt158=1;
                }
                break;
            case 126:
                {
                alt158=2;
                }
                break;
            case 127:
                {
                alt158=3;
                }
                break;
            case 128:
                {
                alt158=4;
                }
                break;
            case 129:
                {
                alt158=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 158, 0, input);

                throw nvae;
            }

            switch (alt158) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6504:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6504:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6504:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,125,FollowSets000.FOLLOW_125_in_ruleLineStyle14211); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6510:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6510:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6510:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,126,FollowSets000.FOLLOW_126_in_ruleLineStyle14228); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6516:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6516:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6516:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,127,FollowSets000.FOLLOW_127_in_ruleLineStyle14245); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6522:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6522:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6522:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,128,FollowSets000.FOLLOW_128_in_ruleLineStyle14262); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6528:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6528:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6528:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,129,FollowSets000.FOLLOW_129_in_ruleLineStyle14279); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6538:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6540:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6541:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6541:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt159=3;
            switch ( input.LA(1) ) {
            case 130:
                {
                alt159=1;
                }
                break;
            case 131:
                {
                alt159=2;
                }
                break;
            case 132:
                {
                alt159=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 159, 0, input);

                throw nvae;
            }

            switch (alt159) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6541:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6541:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6541:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,130,FollowSets000.FOLLOW_130_in_ruleVerticalAlignment14324); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6547:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6547:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6547:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,131,FollowSets000.FOLLOW_131_in_ruleVerticalAlignment14341); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6553:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6553:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6553:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,132,FollowSets000.FOLLOW_132_in_ruleVerticalAlignment14358); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6563:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6565:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6566:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6566:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt160=3;
            switch ( input.LA(1) ) {
            case 133:
                {
                alt160=1;
                }
                break;
            case 131:
                {
                alt160=2;
                }
                break;
            case 134:
                {
                alt160=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 160, 0, input);

                throw nvae;
            }

            switch (alt160) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6566:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6566:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6566:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,133,FollowSets000.FOLLOW_133_in_ruleHorizontalAlignment14403); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6572:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6572:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6572:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,131,FollowSets000.FOLLOW_131_in_ruleHorizontalAlignment14420); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6578:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6578:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/parser/antlr/internal/InternalKGraph.g:6578:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,134,FollowSets000.FOLLOW_134_in_ruleHorizontalAlignment14437); 

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


    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA31_eotS =
        "\12\uffff";
    static final String DFA31_eofS =
        "\12\uffff";
    static final String DFA31_minS =
        "\1\44\1\45\10\uffff";
    static final String DFA31_maxS =
        "\1\44\1\150\10\uffff";
    static final String DFA31_acceptS =
        "\2\uffff\1\3\1\6\1\2\1\4\1\5\1\1\1\10\1\7";
    static final String DFA31_specialS =
        "\12\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\1",
            "\1\7\64\uffff\1\4\3\uffff\1\2\1\5\1\uffff\1\6\2\uffff\1\3\1"+
            "\uffff\1\11\1\uffff\1\10",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "1214:1: (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment )";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKNode_in_entryRuleKNode75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKNode85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_ruleKNode131 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKNode143 = new BitSet(new long[]{0x00000000007E2000L});
        public static final BitSet FOLLOW_13_in_ruleKNode156 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKNode168 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKNode191 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKNode204 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKNode227 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKNode241 = new BitSet(new long[]{0x00000000007E0000L});
        public static final BitSet FOLLOW_17_in_ruleKNode256 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKNode268 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKNode289 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKNode302 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKNode323 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKNode337 = new BitSet(new long[]{0x00000000007C0000L});
        public static final BitSet FOLLOW_19_in_ruleKNode352 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKNode364 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKNode385 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKNode398 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKNode419 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKNode433 = new BitSet(new long[]{0x0000000000740000L});
        public static final BitSet FOLLOW_20_in_ruleKNode448 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKNode460 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_ruleKNode_in_ruleKNode481 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKNode494 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_ruleKNode_in_ruleKNode515 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKNode529 = new BitSet(new long[]{0x0000000000640000L});
        public static final BitSet FOLLOW_21_in_ruleKNode544 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKNode556 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_ruleKPort_in_ruleKNode577 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKNode590 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_ruleKPort_in_ruleKNode611 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKNode625 = new BitSet(new long[]{0x0000000000440000L});
        public static final BitSet FOLLOW_22_in_ruleKNode640 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKNode652 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_ruleKEdge_in_ruleKNode673 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKNode686 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_ruleKEdge_in_ruleKNode707 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKNode721 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKNode735 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGraphData_in_entryRuleKGraphData771 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGraphData781 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKGraphData828 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKShapeLayout_in_ruleKGraphData855 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdgeLayout_in_ruleKGraphData882 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLabel_in_entryRuleKLabel917 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLabel927 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKLabel964 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLabel976 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_24_in_ruleKLabel988 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKLabel1009 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKLabel1022 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLabel1034 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKLabel1055 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKLabel1068 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKLabel1089 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKLabel1103 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKLabel1117 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPort_in_entryRuleKPort1153 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPort1163 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_ruleKPort1209 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPort1221 = new BitSet(new long[]{0x00000000040E0000L});
        public static final BitSet FOLLOW_26_in_ruleKPort1234 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPort1246 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPort1269 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKPort1282 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPort1305 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKPort1319 = new BitSet(new long[]{0x00000000000E0000L});
        public static final BitSet FOLLOW_17_in_ruleKPort1334 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPort1346 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKPort1367 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKPort1380 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKPort1401 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKPort1415 = new BitSet(new long[]{0x00000000000C0000L});
        public static final BitSet FOLLOW_19_in_ruleKPort1430 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPort1442 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKPort1463 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKPort1476 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKPort1497 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKPort1511 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPort1525 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdge_in_entryRuleKEdge1561 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEdge1571 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleKEdge1608 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdge1620 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleKEdge1632 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1655 = new BitSet(new long[]{0x00000000600E0000L});
        public static final BitSet FOLLOW_29_in_ruleKEdge1668 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1691 = new BitSet(new long[]{0x00000000400E0000L});
        public static final BitSet FOLLOW_30_in_ruleKEdge1706 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEdge1729 = new BitSet(new long[]{0x00000000000E0000L});
        public static final BitSet FOLLOW_17_in_ruleKEdge1744 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdge1756 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKEdge1777 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKEdge1790 = new BitSet(new long[]{0x02B2734080000000L,0x0100200000000000L});
        public static final BitSet FOLLOW_ruleKGraphData_in_ruleKEdge1811 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKEdge1825 = new BitSet(new long[]{0x00000000000C0000L});
        public static final BitSet FOLLOW_19_in_ruleKEdge1840 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdge1852 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKEdge1873 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKEdge1886 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKLabel_in_ruleKEdge1907 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKEdge1921 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKEdge1935 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString1972 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString1983 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString2023 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString2049 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_entryRuleKRendering2096 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRendering2106 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_ruleKRendering2153 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_ruleKRendering2180 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_ruleKRendering2207 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_ruleKRendering2234 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_ruleKRendering2261 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_ruleKRendering2288 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_ruleKRendering2315 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_ruleKRendering2342 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_ruleKRendering2369 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_ruleKRendering2396 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_ruleKRendering2423 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_ruleKRendering2450 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData2485 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacementData2495 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData2542 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData2569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData2596 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData2623 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData2650 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle2685 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle2695 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_Impl_in_ruleKStyle2742 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_ruleKStyle2769 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_ruleKStyle2796 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle2823 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle2850 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle2877 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle2904 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle2931 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement2966 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement2976 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement3023 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_ruleKPlacement3050 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition3085 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition3095 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition3142 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition3169 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition3204 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition3214 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition3261 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition3288 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef3325 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef3335 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_31_in_ruleKRenderingRef3372 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef3384 = new BitSet(new long[]{0x0000000300000000L});
        public static final BitSet FOLLOW_32_in_ruleKRenderingRef3397 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef3409 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef3432 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef3445 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef3468 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKRenderingRef3482 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_33_in_ruleKRenderingRef3496 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef3519 = new BitSet(new long[]{0x0000000C00040000L});
        public static final BitSet FOLLOW_34_in_ruleKRenderingRef3532 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef3553 = new BitSet(new long[]{0x0000000800040000L});
        public static final BitSet FOLLOW_35_in_ruleKRenderingRef3568 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef3580 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef3601 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef3614 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef3635 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef3649 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef3663 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_Impl_in_entryRuleKStyle_Impl3699 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle_Impl3709 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKStyle_Impl3752 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_37_in_ruleKStyle_Impl3777 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse3813 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse3823 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleKEllipse3869 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse3881 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKEllipse3894 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse3906 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEllipse3929 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKEllipse3942 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEllipse3965 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse3979 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKEllipse3994 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse4015 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKEllipse4030 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse4042 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse4063 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKEllipse4076 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse4097 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse4111 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKEllipse4126 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse4138 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse4159 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKEllipse4172 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse4193 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse4207 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKEllipse4222 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse4243 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse4257 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle4293 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle4303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_40_in_ruleKRectangle4349 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle4361 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKRectangle4374 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle4386 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRectangle4409 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKRectangle4422 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRectangle4445 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle4459 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKRectangle4474 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle4495 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKRectangle4510 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle4522 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle4543 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKRectangle4556 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle4577 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKRectangle4591 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKRectangle4606 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle4618 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle4639 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKRectangle4652 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle4673 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKRectangle4687 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKRectangle4702 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle4723 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRectangle4737 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle4773 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle4783 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_41_in_ruleKRoundedRectangle4820 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle4832 = new BitSet(new long[]{0x0000040000000000L});
        public static final BitSet FOLLOW_42_in_ruleKRoundedRectangle4844 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4865 = new BitSet(new long[]{0x0000080000000000L});
        public static final BitSet FOLLOW_43_in_ruleKRoundedRectangle4877 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle4898 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKRoundedRectangle4911 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle4923 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRoundedRectangle4946 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKRoundedRectangle4959 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRoundedRectangle4982 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle4996 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKRoundedRectangle5011 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle5032 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKRoundedRectangle5047 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle5059 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle5080 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKRoundedRectangle5093 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle5114 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle5128 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedRectangle5143 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle5155 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle5176 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKRoundedRectangle5189 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle5210 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle5224 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKRoundedRectangle5239 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle5260 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle5274 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl5310 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl5320 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_44_in_ruleKPolyline_Impl5366 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl5378 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKPolyline_Impl5391 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl5403 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolyline_Impl5426 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKPolyline_Impl5439 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolyline_Impl5462 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl5476 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKPolyline_Impl5491 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl5512 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKPolyline_Impl5527 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl5539 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl5560 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKPolyline_Impl5573 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl5594 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl5608 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKPolyline_Impl5623 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl5635 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5656 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKPolyline_Impl5669 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl5690 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl5704 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKPolyline_Impl5719 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl5740 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl5754 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon5790 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon5800 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_45_in_ruleKPolygon5846 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon5858 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKPolygon5871 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon5883 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolygon5906 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKPolygon5919 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolygon5942 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon5956 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKPolygon5971 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon5992 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKPolygon6007 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon6019 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon6040 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKPolygon6053 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon6074 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKPolygon6088 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKPolygon6103 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon6115 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon6136 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKPolygon6149 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon6170 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKPolygon6184 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKPolygon6199 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon6220 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPolygon6234 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage6270 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage6280 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_46_in_ruleKImage6317 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage6329 = new BitSet(new long[]{0x0000800000000000L});
        public static final BitSet FOLLOW_47_in_ruleKImage6341 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage6362 = new BitSet(new long[]{0x0001000000000000L});
        public static final BitSet FOLLOW_48_in_ruleKImage6374 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage6395 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKImage6408 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKImage6420 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage6443 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKImage6456 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage6479 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKImage6493 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKImage6508 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage6529 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKImage6544 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage6556 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage6577 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKImage6590 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage6611 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKImage6625 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKImage6640 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage6652 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage6673 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKImage6686 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage6707 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKImage6721 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKImage6736 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage6757 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKImage6771 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc6807 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc6817 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_49_in_ruleKArc6863 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc6875 = new BitSet(new long[]{0x000C008D00140000L});
        public static final BitSet FOLLOW_50_in_ruleKArc6888 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc6909 = new BitSet(new long[]{0x0008008D00140000L});
        public static final BitSet FOLLOW_51_in_ruleKArc6924 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc6945 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKArc6960 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKArc6972 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc6995 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKArc7008 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc7031 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKArc7045 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKArc7060 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc7081 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKArc7096 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc7108 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc7129 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKArc7142 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc7163 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKArc7177 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKArc7192 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc7204 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc7225 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKArc7238 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc7259 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKArc7273 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKArc7288 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc7309 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKArc7323 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea7359 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea7369 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_52_in_ruleKChildArea7415 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea7427 = new BitSet(new long[]{0x0000000D00040000L});
        public static final BitSet FOLLOW_32_in_ruleKChildArea7440 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea7452 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea7475 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKChildArea7488 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea7511 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKChildArea7525 = new BitSet(new long[]{0x0000000C00040000L});
        public static final BitSet FOLLOW_34_in_ruleKChildArea7540 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea7561 = new BitSet(new long[]{0x0000000800040000L});
        public static final BitSet FOLLOW_35_in_ruleKChildArea7576 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea7588 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea7609 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKChildArea7622 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea7643 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea7657 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea7671 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText7707 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText7717 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleKText7760 = new BitSet(new long[]{0x0040000000000000L});
        public static final BitSet FOLLOW_54_in_ruleKText7785 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText7797 = new BitSet(new long[]{0x0000008D01140000L});
        public static final BitSet FOLLOW_24_in_ruleKText7810 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText7831 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKText7846 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKText7858 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText7881 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKText7894 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText7917 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKText7931 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKText7946 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText7967 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKText7982 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText7994 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText8015 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKText8028 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText8049 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKText8063 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKText8078 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText8090 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText8111 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKText8124 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText8145 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKText8159 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKText8174 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText8195 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKText8209 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering8245 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering8255 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_55_in_ruleKCustomRendering8292 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering8304 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_56_in_ruleKCustomRendering8316 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering8337 = new BitSet(new long[]{0x0000800000000000L});
        public static final BitSet FOLLOW_47_in_ruleKCustomRendering8349 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering8370 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKCustomRendering8383 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering8395 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering8418 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKCustomRendering8431 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering8454 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKCustomRendering8468 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKCustomRendering8483 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering8504 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKCustomRendering8519 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering8531 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering8552 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKCustomRendering8565 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering8586 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering8600 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKCustomRendering8615 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering8627 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering8648 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKCustomRendering8661 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering8682 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering8696 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKCustomRendering8711 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering8732 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering8746 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline8782 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline8792 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_57_in_ruleKSpline8838 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline8850 = new BitSet(new long[]{0x0000008D00140000L});
        public static final BitSet FOLLOW_32_in_ruleKSpline8863 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline8875 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline8898 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_15_in_ruleKSpline8911 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline8934 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_16_in_ruleKSpline8948 = new BitSet(new long[]{0x0000008C00140000L});
        public static final BitSet FOLLOW_34_in_ruleKSpline8963 = new BitSet(new long[]{0x8400000000000000L,0x0000000000001210L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline8984 = new BitSet(new long[]{0x0000008800140000L});
        public static final BitSet FOLLOW_35_in_ruleKSpline8999 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline9011 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline9032 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKSpline9045 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline9066 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline9080 = new BitSet(new long[]{0x0000008000140000L});
        public static final BitSet FOLLOW_20_in_ruleKSpline9095 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline9107 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline9128 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKSpline9141 = new BitSet(new long[]{0x02B2734080000000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline9162 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline9176 = new BitSet(new long[]{0x0000008000040000L});
        public static final BitSet FOLLOW_39_in_ruleKSpline9191 = new BitSet(new long[]{0x0000000000000000L,0x0000140000000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline9212 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline9226 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData9262 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData9272 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_58_in_ruleKDecoratorPlacementData9315 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_59_in_ruleKDecoratorPlacementData9340 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData9352 = new BitSet(new long[]{0x1000000000000000L});
        public static final BitSet FOLLOW_60_in_ruleKDecoratorPlacementData9364 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData9385 = new BitSet(new long[]{0x6000000000040000L});
        public static final BitSet FOLLOW_61_in_ruleKDecoratorPlacementData9398 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData9419 = new BitSet(new long[]{0x4000000000040000L});
        public static final BitSet FOLLOW_62_in_ruleKDecoratorPlacementData9434 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData9455 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKDecoratorPlacementData9469 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData9505 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData9515 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKGridPlacementData9552 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData9564 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKGridPlacementData9576 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData9597 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKGridPlacementData9609 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData9630 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
        public static final BitSet FOLLOW_66_in_ruleKGridPlacementData9642 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData9663 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
        public static final BitSet FOLLOW_67_in_ruleKGridPlacementData9675 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData9696 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKGridPlacementData9708 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData9744 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData9754 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_68_in_ruleKStackPlacementData9791 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData9803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
        public static final BitSet FOLLOW_69_in_ruleKStackPlacementData9815 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData9836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_70_in_ruleKStackPlacementData9848 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData9869 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_71_in_ruleKStackPlacementData9881 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData9902 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
        public static final BitSet FOLLOW_72_in_ruleKStackPlacementData9914 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData9935 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKStackPlacementData9947 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData9983 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData9993 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_73_in_ruleKDirectPlacementData10030 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData10042 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_74_in_ruleKDirectPlacementData10054 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData10075 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
        public static final BitSet FOLLOW_75_in_ruleKDirectPlacementData10087 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData10108 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKDirectPlacementData10120 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData10156 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData10166 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKPolylinePlacementData10203 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData10215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleKPolylinePlacementData10227 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData10239 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData10260 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKPolylinePlacementData10273 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData10294 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKPolylinePlacementData10308 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPolylinePlacementData10320 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat10357 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat10368 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_78_in_ruleEFloat10407 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat10425 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_79_in_ruleEFloat10445 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat10460 = new BitSet(new long[]{0x0000000000000002L,0x0000000000030000L});
        public static final BitSet FOLLOW_80_in_ruleEFloat10480 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_81_in_ruleEFloat10499 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_78_in_ruleEFloat10514 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat10531 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition10580 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition10590 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleKPosition10627 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPosition10639 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_83_in_ruleKPosition10651 = new BitSet(new long[]{0x0000000000000000L,0x0000000000A00000L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition10672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
        public static final BitSet FOLLOW_84_in_ruleKPosition10684 = new BitSet(new long[]{0x0000000000000000L,0x0000000003000000L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition10705 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPosition10717 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition10753 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition10763 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_85_in_ruleKLeftPosition10809 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLeftPosition10821 = new BitSet(new long[]{0x0400000000040000L,0x0000000000400000L});
        public static final BitSet FOLLOW_86_in_ruleKLeftPosition10834 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition10855 = new BitSet(new long[]{0x0400000000040000L});
        public static final BitSet FOLLOW_58_in_ruleKLeftPosition10870 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition10891 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKLeftPosition10905 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition10941 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition10951 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleKRightPosition10997 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRightPosition11009 = new BitSet(new long[]{0x0400000000040000L,0x0000000000400000L});
        public static final BitSet FOLLOW_86_in_ruleKRightPosition11022 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition11043 = new BitSet(new long[]{0x0400000000040000L});
        public static final BitSet FOLLOW_58_in_ruleKRightPosition11058 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition11079 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRightPosition11093 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition11129 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition11139 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_88_in_ruleKTopPosition11185 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKTopPosition11197 = new BitSet(new long[]{0x0400000000040000L,0x0000000000400000L});
        public static final BitSet FOLLOW_86_in_ruleKTopPosition11210 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition11231 = new BitSet(new long[]{0x0400000000040000L});
        public static final BitSet FOLLOW_58_in_ruleKTopPosition11246 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition11267 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKTopPosition11281 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition11317 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition11327 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_89_in_ruleKBottomPosition11373 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBottomPosition11385 = new BitSet(new long[]{0x0400000000040000L,0x0000000000400000L});
        public static final BitSet FOLLOW_86_in_ruleKBottomPosition11398 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition11419 = new BitSet(new long[]{0x0400000000040000L});
        public static final BitSet FOLLOW_58_in_ruleKBottomPosition11434 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition11455 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKBottomPosition11469 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor11505 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor11515 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKForegroundColor11558 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKForegroundColor11583 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKForegroundColor11595 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleKForegroundColor11607 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor11628 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_92_in_ruleKForegroundColor11640 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor11661 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
        public static final BitSet FOLLOW_93_in_ruleKForegroundColor11673 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor11694 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKForegroundColor11706 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor11742 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor11752 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKBackgroundColor11795 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_94_in_ruleKBackgroundColor11820 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBackgroundColor11832 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleKBackgroundColor11844 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor11865 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_92_in_ruleKBackgroundColor11877 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor11898 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
        public static final BitSet FOLLOW_93_in_ruleKBackgroundColor11910 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor11931 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKBackgroundColor11943 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth11979 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth11989 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKLineWidth12032 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleKLineWidth12057 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLineWidth12069 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleKLineWidth12081 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth12102 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKLineWidth12114 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility12150 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility12160 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKVisibility12203 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
        public static final BitSet FOLLOW_97_in_ruleKVisibility12234 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
        public static final BitSet FOLLOW_98_in_ruleKVisibility12265 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
        public static final BitSet FOLLOW_99_in_ruleKVisibility12290 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle12326 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle12336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKLineStyle12379 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
        public static final BitSet FOLLOW_100_in_ruleKLineStyle12404 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLineStyle12416 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_101_in_ruleKLineStyle12428 = new BitSet(new long[]{0x0000000000000000L,0xE000000000000000L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle12449 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKLineStyle12461 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment12497 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment12507 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKVerticalAlignment12550 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
        public static final BitSet FOLLOW_102_in_ruleKVerticalAlignment12575 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKVerticalAlignment12587 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
        public static final BitSet FOLLOW_103_in_ruleKVerticalAlignment12599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000000001CL});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment12620 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKVerticalAlignment12632 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment12668 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment12678 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKHorizontalAlignment12721 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
        public static final BitSet FOLLOW_104_in_ruleKHorizontalAlignment12746 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKHorizontalAlignment12758 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
        public static final BitSet FOLLOW_105_in_ruleKHorizontalAlignment12770 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000068L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment12791 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKHorizontalAlignment12803 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt12840 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt12851 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_78_in_ruleEInt12890 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt12907 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement12952 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement12962 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_106_in_ruleKGridPlacement12999 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacement13011 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
        public static final BitSet FOLLOW_107_in_ruleKGridPlacement13023 = new BitSet(new long[]{0x0000000000000040L,0x0000000000004000L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement13044 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKGridPlacement13056 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement13092 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement13102 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_108_in_ruleKStackPlacement13148 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout13184 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKShapeLayout13194 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_109_in_ruleKShapeLayout13240 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKShapeLayout13252 = new BitSet(new long[]{0x0000000000040000L,0x0007C00000000000L});
        public static final BitSet FOLLOW_110_in_ruleKShapeLayout13265 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout13286 = new BitSet(new long[]{0x0000000000040000L,0x0007800000000000L});
        public static final BitSet FOLLOW_111_in_ruleKShapeLayout13301 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout13322 = new BitSet(new long[]{0x0000000000040000L,0x0007000000000000L});
        public static final BitSet FOLLOW_112_in_ruleKShapeLayout13337 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout13358 = new BitSet(new long[]{0x0000000000040000L,0x0006000000000000L});
        public static final BitSet FOLLOW_113_in_ruleKShapeLayout13373 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout13394 = new BitSet(new long[]{0x0000000000040000L,0x0004000000000000L});
        public static final BitSet FOLLOW_114_in_ruleKShapeLayout13409 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
        public static final BitSet FOLLOW_ruleKInsets_in_ruleKShapeLayout13430 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKShapeLayout13444 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets13480 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets13490 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_115_in_ruleKInsets13536 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets13548 = new BitSet(new long[]{0x0000000000040000L,0x00F0000000000000L});
        public static final BitSet FOLLOW_116_in_ruleKInsets13561 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets13582 = new BitSet(new long[]{0x0000000000040000L,0x00E0000000000000L});
        public static final BitSet FOLLOW_117_in_ruleKInsets13597 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets13618 = new BitSet(new long[]{0x0000000000040000L,0x00C0000000000000L});
        public static final BitSet FOLLOW_118_in_ruleKInsets13633 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets13654 = new BitSet(new long[]{0x0000000000040000L,0x0080000000000000L});
        public static final BitSet FOLLOW_119_in_ruleKInsets13669 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets13690 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKInsets13704 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEdgeLayout_in_entryRuleKEdgeLayout13740 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEdgeLayout13750 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_120_in_ruleKEdgeLayout13787 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdgeLayout13799 = new BitSet(new long[]{0x0000000000000000L,0x0600000000000000L});
        public static final BitSet FOLLOW_121_in_ruleKEdgeLayout13812 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEdgeLayout13824 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13845 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_15_in_ruleKEdgeLayout13858 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13879 = new BitSet(new long[]{0x0000000000048000L});
        public static final BitSet FOLLOW_18_in_ruleKEdgeLayout13893 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
        public static final BitSet FOLLOW_122_in_ruleKEdgeLayout13907 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13928 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
        public static final BitSet FOLLOW_123_in_ruleKEdgeLayout13940 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
        public static final BitSet FOLLOW_ruleKPoint_in_ruleKEdgeLayout13961 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKEdgeLayout13973 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint14009 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint14019 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_124_in_ruleKPoint14065 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPoint14077 = new BitSet(new long[]{0x0000000000040000L,0x0000000000180000L});
        public static final BitSet FOLLOW_83_in_ruleKPoint14090 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint14111 = new BitSet(new long[]{0x0000000000040000L,0x0000000000100000L});
        public static final BitSet FOLLOW_84_in_ruleKPoint14126 = new BitSet(new long[]{0x0000000000000040L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint14147 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPoint14161 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_125_in_ruleLineStyle14211 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_126_in_ruleLineStyle14228 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_127_in_ruleLineStyle14245 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_128_in_ruleLineStyle14262 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_129_in_ruleLineStyle14279 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_130_in_ruleVerticalAlignment14324 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_131_in_ruleVerticalAlignment14341 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_132_in_ruleVerticalAlignment14358 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_133_in_ruleHorizontalAlignment14403 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_131_in_ruleHorizontalAlignment14420 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_134_in_ruleHorizontalAlignment14437 = new BitSet(new long[]{0x0000000000000002L});
    }


}