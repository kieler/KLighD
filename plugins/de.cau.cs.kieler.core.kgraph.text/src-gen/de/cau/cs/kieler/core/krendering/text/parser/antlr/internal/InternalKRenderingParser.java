package de.cau.cs.kieler.core.krendering.text.parser.antlr.internal; 

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
import de.cau.cs.kieler.core.krendering.text.services.KRenderingGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKRenderingParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'KRenderingLibrary'", "'{'", "','", "'}'", "'KRenderingRef'", "'references'", "'('", "')'", "'rendering'", "'placementData'", "'styles'", "'KEllipse'", "':'", "'childPlacement'", "'children'", "'KRectangle'", "'KRoundedRectangle'", "'cornerWidth'", "'cornerHeight'", "'KPolyline'", "'KPolygon'", "'KImage'", "'bundleName'", "'imagePath'", "'KArc'", "'startAngle'", "'arcAngle'", "'KChildArea'", "'clip'", "'KText'", "'text'", "'KCustomRendering'", "'className'", "'KSpline'", "'relative'", "'KDecoratorPlacementData'", "'location'", "'xOffset'", "'yOffset'", "'KGridPlacementData'", "'widthHint'", "'heightHint'", "'horizontalIndent'", "'verticalIndent'", "'KStackPlacementData'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'KDirectPlacementData'", "'topLeft'", "'bottomRight'", "'points'", "'-'", "'.'", "'E'", "'e'", "'KPosition'", "'x'", "'y'", "'KLeftPosition'", "'absolute'", "'KRightPosition'", "'KTopPosition'", "'KBottomPosition'", "'KForegroundColor'", "'propagateToChildren'", "'red'", "'green'", "'blue'", "'KBackgroundColor'", "'KLineWidth'", "'KForegroundVisibility'", "'visible'", "'KBackgroundVisibility'", "'KLineStyle'", "'KVerticalAlignment'", "'KHorizontalAlignment'", "'KGridPlacement'", "'KStackPlacement'", "'KInsets'", "'top'", "'bottom'", "'left'", "'right'", "'KPoint'", "'='", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
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
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int RULE_INT=4;
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


        public InternalKRenderingParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalKRenderingParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalKRenderingParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g"; }



     	private KRenderingGrammarAccess grammarAccess;
     	
        public InternalKRenderingParser(TokenStream input, KRenderingGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "KRenderingLibrary";	
       	}
       	
       	@Override
       	protected KRenderingGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleKRenderingLibrary"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:68:1: entryRuleKRenderingLibrary returns [EObject current=null] : iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF ;
    public final EObject entryRuleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingLibrary = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:69:2: (iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:70:2: iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF
            {
             newCompositeNode(grammarAccess.getKRenderingLibraryRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingLibrary_in_entryRuleKRenderingLibrary75);
            iv_ruleKRenderingLibrary=ruleKRenderingLibrary();

            state._fsp--;

             current =iv_ruleKRenderingLibrary; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingLibrary85); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:77:1: ruleKRenderingLibrary returns [EObject current=null] : ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:80:28: ( ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:2: () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:82:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingLibraryAccess().getKRenderingLibraryAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleKRenderingLibrary131); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingLibraryAccess().getKRenderingLibraryKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingLibrary143); 

                	newLeafNode(otherlv_2, grammarAccess.getKRenderingLibraryAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:95:1: ( ( (lv_renderings_3_0= ruleKRendering ) ) (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==15||LA2_0==22||(LA2_0>=26 && LA2_0<=27)||(LA2_0>=30 && LA2_0<=32)||LA2_0==35||(LA2_0>=38 && LA2_0<=39)||LA2_0==42||LA2_0==44) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:95:2: ( (lv_renderings_3_0= ruleKRendering ) ) (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )*
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:95:2: ( (lv_renderings_3_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:96:1: (lv_renderings_3_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:96:1: (lv_renderings_3_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:97:3: lv_renderings_3_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRenderingLibrary165);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:113:2: (otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==13) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:113:4: otherlv_4= ',' ( (lv_renderings_5_0= ruleKRendering ) )
                    	    {
                    	    otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingLibrary178); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getKRenderingLibraryAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:117:1: ( (lv_renderings_5_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:118:1: (lv_renderings_5_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:118:1: (lv_renderings_5_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:119:3: lv_renderings_5_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRenderingLibrary199);
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
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingLibrary215); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:147:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:148:2: (iv_ruleKRendering= ruleKRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:149:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_entryRuleKRendering251);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRendering261); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:156:1: ruleKRendering returns [EObject current=null] : (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:159:28: ( (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:160:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:160:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            int alt3=12;
            switch ( input.LA(1) ) {
            case 22:
                {
                alt3=1;
                }
                break;
            case 26:
                {
                alt3=2;
                }
                break;
            case 27:
                {
                alt3=3;
                }
                break;
            case 30:
                {
                alt3=4;
                }
                break;
            case 31:
                {
                alt3=5;
                }
                break;
            case 32:
                {
                alt3=6;
                }
                break;
            case 35:
                {
                alt3=7;
                }
                break;
            case 15:
                {
                alt3=8;
                }
                break;
            case 38:
                {
                alt3=9;
                }
                break;
            case 39:
                {
                alt3=10;
                }
                break;
            case 42:
                {
                alt3=11;
                }
                break;
            case 44:
                {
                alt3=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:161:5: this_KEllipse_0= ruleKEllipse
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKEllipseParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_ruleKRendering308);
                    this_KEllipse_0=ruleKEllipse();

                    state._fsp--;

                     
                            current = this_KEllipse_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:171:5: this_KRectangle_1= ruleKRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRectangleParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_ruleKRendering335);
                    this_KRectangle_1=ruleKRectangle();

                    state._fsp--;

                     
                            current = this_KRectangle_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:181:5: this_KRoundedRectangle_2= ruleKRoundedRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRoundedRectangleParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_ruleKRendering362);
                    this_KRoundedRectangle_2=ruleKRoundedRectangle();

                    state._fsp--;

                     
                            current = this_KRoundedRectangle_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:191:5: this_KPolyline_Impl_3= ruleKPolyline_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolyline_ImplParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_ruleKRendering389);
                    this_KPolyline_Impl_3=ruleKPolyline_Impl();

                    state._fsp--;

                     
                            current = this_KPolyline_Impl_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:201:5: this_KPolygon_4= ruleKPolygon
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolygonParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_ruleKRendering416);
                    this_KPolygon_4=ruleKPolygon();

                    state._fsp--;

                     
                            current = this_KPolygon_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:211:5: this_KImage_5= ruleKImage
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKImageParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKImage_in_ruleKRendering443);
                    this_KImage_5=ruleKImage();

                    state._fsp--;

                     
                            current = this_KImage_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:221:5: this_KArc_6= ruleKArc
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKArcParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKArc_in_ruleKRendering470);
                    this_KArc_6=ruleKArc();

                    state._fsp--;

                     
                            current = this_KArc_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:231:5: this_KRenderingRef_7= ruleKRenderingRef
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRenderingRefParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_ruleKRendering497);
                    this_KRenderingRef_7=ruleKRenderingRef();

                    state._fsp--;

                     
                            current = this_KRenderingRef_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:241:5: this_KChildArea_8= ruleKChildArea
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKChildAreaParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_ruleKRendering524);
                    this_KChildArea_8=ruleKChildArea();

                    state._fsp--;

                     
                            current = this_KChildArea_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:251:5: this_KText_9= ruleKText
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKTextParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKText_in_ruleKRendering551);
                    this_KText_9=ruleKText();

                    state._fsp--;

                     
                            current = this_KText_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:261:5: this_KCustomRendering_10= ruleKCustomRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKCustomRenderingParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_ruleKRendering578);
                    this_KCustomRendering_10=ruleKCustomRendering();

                    state._fsp--;

                     
                            current = this_KCustomRendering_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:271:5: this_KSpline_11= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSplineParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_ruleKRendering605);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:287:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:288:2: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:289:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData640);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacementData650); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:296:1: ruleKPlacementData returns [EObject current=null] : (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KDecoratorPlacementData_0 = null;

        EObject this_KGridPlacementData_1 = null;

        EObject this_KStackPlacementData_2 = null;

        EObject this_KDirectPlacementData_3 = null;

        EObject this_KPolylinePlacementData_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:299:28: ( (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:300:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:300:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            int alt4=5;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt4=1;
                }
                break;
            case 50:
                {
                alt4=2;
                }
                break;
            case 55:
                {
                alt4=3;
                }
                break;
            case 60:
                {
                alt4=4;
                }
                break;
            case 63:
                {
                alt4=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:301:5: this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData697);
                    this_KDecoratorPlacementData_0=ruleKDecoratorPlacementData();

                    state._fsp--;

                     
                            current = this_KDecoratorPlacementData_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:311:5: this_KGridPlacementData_1= ruleKGridPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData724);
                    this_KGridPlacementData_1=ruleKGridPlacementData();

                    state._fsp--;

                     
                            current = this_KGridPlacementData_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:321:5: this_KStackPlacementData_2= ruleKStackPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKStackPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData751);
                    this_KStackPlacementData_2=ruleKStackPlacementData();

                    state._fsp--;

                     
                            current = this_KStackPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:331:5: this_KDirectPlacementData_3= ruleKDirectPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDirectPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData778);
                    this_KDirectPlacementData_3=ruleKDirectPlacementData();

                    state._fsp--;

                     
                            current = this_KDirectPlacementData_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:341:5: this_KPolylinePlacementData_4= ruleKPolylinePlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPolylinePlacementDataParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData805);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:357:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:358:2: (iv_ruleKStyle= ruleKStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:359:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_entryRuleKStyle840);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle850); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:366:1: ruleKStyle returns [EObject current=null] : (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:369:28: ( (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:370:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:370:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KVerticalAlignment_5= ruleKVerticalAlignment | this_KHorizontalAlignment_6= ruleKHorizontalAlignment )
            int alt5=7;
            switch ( input.LA(1) ) {
            case 76:
                {
                alt5=1;
                }
                break;
            case 81:
                {
                alt5=2;
                }
                break;
            case 82:
                {
                alt5=3;
                }
                break;
            case 83:
            case 85:
                {
                alt5=4;
                }
                break;
            case 86:
                {
                alt5=5;
                }
                break;
            case 87:
                {
                alt5=6;
                }
                break;
            case 88:
                {
                alt5=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:371:5: this_KForegroundColor_0= ruleKForegroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundColorParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_ruleKStyle897);
                    this_KForegroundColor_0=ruleKForegroundColor();

                    state._fsp--;

                     
                            current = this_KForegroundColor_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:381:5: this_KBackgroundColor_1= ruleKBackgroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKBackgroundColorParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_ruleKStyle924);
                    this_KBackgroundColor_1=ruleKBackgroundColor();

                    state._fsp--;

                     
                            current = this_KBackgroundColor_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:391:5: this_KLineWidth_2= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle951);
                    this_KLineWidth_2=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:401:5: this_KVisibility_3= ruleKVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVisibilityParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_ruleKStyle978);
                    this_KVisibility_3=ruleKVisibility();

                    state._fsp--;

                     
                            current = this_KVisibility_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:411:5: this_KLineStyle_4= ruleKLineStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_ruleKStyle1005);
                    this_KLineStyle_4=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:421:5: this_KVerticalAlignment_5= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1032);
                    this_KVerticalAlignment_5=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:431:5: this_KHorizontalAlignment_6= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1059);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:447:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:448:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:449:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_entryRuleKPlacement1094);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacement1104); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:456:1: ruleKPlacement returns [EObject current=null] : (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;

        EObject this_KStackPlacement_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:459:28: ( (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:460:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:460:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==89) ) {
                alt6=1;
            }
            else if ( (LA6_0==90) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:461:5: this_KGridPlacement_0= ruleKGridPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement1151);
                    this_KGridPlacement_0=ruleKGridPlacement();

                    state._fsp--;

                     
                            current = this_KGridPlacement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:471:5: this_KStackPlacement_1= ruleKStackPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKStackPlacementParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_ruleKPlacement1178);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:487:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:488:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:489:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition1213);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition1223); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:496:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:499:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:500:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:500:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==71) ) {
                alt7=1;
            }
            else if ( (LA7_0==73) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:501:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition1270);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:511:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition1297);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:527:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:528:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:529:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition1332);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition1342); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:536:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:539:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:540:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:540:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==74) ) {
                alt8=1;
            }
            else if ( (LA8_0==75) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:541:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition1389);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:551:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition1416);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:567:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:568:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:569:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1451);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef1461); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:576:1: ruleKRenderingRef returns [EObject current=null] : (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:579:28: ( (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:580:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:580:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:580:3: otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            otherlv_0=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef1498); 

                	newLeafNode(otherlv_0, grammarAccess.getKRenderingRefAccess().getKRenderingRefKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1510); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:588:1: (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==16) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:588:3: otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')'
                    {
                    otherlv_2=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRenderingRef1523); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRenderingRefAccess().getReferencesKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1535); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftParenthesisKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:596:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:597:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:597:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:598:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1558);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:611:2: (otherlv_5= ',' ( ( ruleEString ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==13) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:611:4: otherlv_5= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_5=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingRef1571); 

                    	        	newLeafNode(otherlv_5, grammarAccess.getKRenderingRefAccess().getCommaKeyword_2_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:615:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:616:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:616:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:617:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1594);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef1608); 

                        	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getRightParenthesisKeyword_2_4());
                        

                    }
                    break;

            }

            otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRenderingRef1622); 

                	newLeafNode(otherlv_8, grammarAccess.getKRenderingRefAccess().getRenderingKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:638:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:639:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:639:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:640:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1645);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:653:2: (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:653:4: otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) )
                    {
                    otherlv_10=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRenderingRef1658); 

                        	newLeafNode(otherlv_10, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:657:1: ( (lv_placementData_11_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:658:1: (lv_placementData_11_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:658:1: (lv_placementData_11_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:659:3: lv_placementData_11_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1679);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:675:4: (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==21) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:675:6: otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRenderingRef1694); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRenderingRefAccess().getStylesKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1706); 

                        	newLeafNode(otherlv_13, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:683:1: ( (lv_styles_14_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:684:1: (lv_styles_14_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:684:1: (lv_styles_14_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:685:3: lv_styles_14_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1727);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:701:2: (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==13) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:701:4: otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) )
                    	    {
                    	    otherlv_15=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingRef1740); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getKRenderingRefAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:705:1: ( (lv_styles_16_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:706:1: (lv_styles_16_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:706:1: (lv_styles_16_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:707:3: lv_styles_16_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1761);
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
                    	    break loop12;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1775); 

                        	newLeafNode(otherlv_17, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1789); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:739:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:740:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:741:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse1825);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse1835); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:748:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:751:28: ( ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:752:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:752:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:752:2: () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:752:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:753:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKEllipse1881); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getKEllipseKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse1893); 

                	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:766:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==21) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:766:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKEllipse1906); 

                        	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKEllipse1918); 

                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:774:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:775:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:775:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:776:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse1939);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:792:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==13) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:792:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse1952); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:796:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:797:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:797:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:798:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse1973);
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
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:814:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==20) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:814:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKEllipse1990); 

                        	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKEllipse2002); 

                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:822:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:823:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:823:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:824:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse2023);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:840:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==24) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:840:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKEllipse2038); 

                        	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKEllipse2050); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:848:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:849:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:849:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:850:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse2071);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:866:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==25) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:866:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKEllipse2086); 

                        	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKEllipse2098); 

                        	newLeafNode(otherlv_15, grammarAccess.getKEllipseAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:874:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:875:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:875:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:876:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2119);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:892:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==13) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:892:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2132); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:896:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:897:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:897:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:898:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2153);
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
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2169); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:926:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:927:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:928:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle2205);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle2215); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:935:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:938:28: ( ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:939:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:939:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:939:2: () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:939:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:940:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKRectangle2261); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getKRectangleKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2273); 

                	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:953:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==21) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:953:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRectangle2286); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRectangle2298); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:961:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:962:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:962:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:963:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2319);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:979:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==13) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:979:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2332); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:983:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:984:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:984:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:985:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2353);
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
                    	    break loop20;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1001:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==20) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1001:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRectangle2370); 

                        	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRectangle2382); 

                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1009:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1010:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1010:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1011:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle2403);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1027:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==24) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1027:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKRectangle2418); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRectangle2430); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1035:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1036:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1036:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1037:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle2451);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1053:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==25) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1053:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKRectangle2466); 

                        	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRectangle2478); 

                        	newLeafNode(otherlv_15, grammarAccess.getKRectangleAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1061:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1062:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1062:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1063:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2499);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1079:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==13) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1079:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2512); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1083:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1084:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1084:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1085:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2533);
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
                    	    break loop24;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2549); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1113:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1114:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1115:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2585);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle2595); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1122:1: ruleKRoundedRectangle returns [EObject current=null] : (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1125:28: ( (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1126:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1126:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1126:3: otherlv_0= 'KRoundedRectangle' otherlv_1= '{' ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) ) (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )? (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )? (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )? otherlv_26= '}'
            {
            otherlv_0=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKRoundedRectangle2632); 

                	newLeafNode(otherlv_0, grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle2644); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1134:1: ( (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) ) | (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==28) ) {
                alt26=1;
            }
            else if ( (LA26_0==29) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1134:2: (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1134:2: (otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1134:4: otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) )
                    {
                    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKRoundedRectangle2658); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRoundedRectangleAccess().getCornerWidthKeyword_2_0_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1138:1: ( (lv_cornerWidth_3_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1139:1: (lv_cornerWidth_3_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1139:1: (lv_cornerWidth_3_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1140:3: lv_cornerWidth_3_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_0_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2679);
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

                    otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKRoundedRectangle2691); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRoundedRectangleAccess().getCornerHeightKeyword_2_0_2());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1160:1: ( (lv_cornerHeight_5_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1161:1: (lv_cornerHeight_5_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1161:1: (lv_cornerHeight_5_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1162:3: lv_cornerHeight_5_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_2_0_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2712);
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1179:6: (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1179:6: (otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1179:8: otherlv_6= 'cornerHeight' ( (lv_cornerHeight_7_0= ruleEFloat ) ) otherlv_8= 'cornerWidth' ( (lv_cornerWidth_9_0= ruleEFloat ) )
                    {
                    otherlv_6=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKRoundedRectangle2732); 

                        	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getCornerHeightKeyword_2_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1183:1: ( (lv_cornerHeight_7_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1184:1: (lv_cornerHeight_7_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1184:1: (lv_cornerHeight_7_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1185:3: lv_cornerHeight_7_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_2_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2753);
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

                    otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKRoundedRectangle2765); 

                        	newLeafNode(otherlv_8, grammarAccess.getKRoundedRectangleAccess().getCornerWidthKeyword_2_1_2());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1205:1: ( (lv_cornerWidth_9_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1206:1: (lv_cornerWidth_9_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1206:1: (lv_cornerWidth_9_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1207:3: lv_cornerWidth_9_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_1_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2786);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1223:4: (otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==21) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1223:6: otherlv_10= 'styles' otherlv_11= ':' ( (lv_styles_12_0= ruleKStyle ) ) (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )*
                    {
                    otherlv_10=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedRectangle2801); 

                        	newLeafNode(otherlv_10, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_3_0());
                        
                    otherlv_11=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2813); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1231:1: ( (lv_styles_12_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1232:1: (lv_styles_12_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1232:1: (lv_styles_12_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1233:3: lv_styles_12_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2834);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1249:2: (otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==13) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1249:4: otherlv_13= ',' ( (lv_styles_14_0= ruleKStyle ) )
                    	    {
                    	    otherlv_13=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2847); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1253:1: ( (lv_styles_14_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1254:1: (lv_styles_14_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1254:1: (lv_styles_14_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1255:3: lv_styles_14_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2868);
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
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1271:6: (otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==20) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1271:8: otherlv_15= 'placementData' otherlv_16= ':' ( (lv_placementData_17_0= ruleKPlacementData ) )
                    {
                    otherlv_15=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedRectangle2885); 

                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_16=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2897); 

                        	newLeafNode(otherlv_16, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1279:1: ( (lv_placementData_17_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1280:1: (lv_placementData_17_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1280:1: (lv_placementData_17_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1281:3: lv_placementData_17_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2918);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1297:4: (otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==24) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1297:6: otherlv_18= 'childPlacement' otherlv_19= ':' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                    {
                    otherlv_18=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKRoundedRectangle2933); 

                        	newLeafNode(otherlv_18, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_19=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2945); 

                        	newLeafNode(otherlv_19, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1305:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1306:1: (lv_childPlacement_20_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1306:1: (lv_childPlacement_20_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1307:3: lv_childPlacement_20_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle2966);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1323:4: (otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==25) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1323:6: otherlv_21= 'children' otherlv_22= ':' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    {
                    otherlv_21=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKRoundedRectangle2981); 

                        	newLeafNode(otherlv_21, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_6_0());
                        
                    otherlv_22=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2993); 

                        	newLeafNode(otherlv_22, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1331:1: ( (lv_children_23_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1332:1: (lv_children_23_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1332:1: (lv_children_23_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1333:3: lv_children_23_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3014);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1349:2: (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==13) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1349:4: otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) )
                    	    {
                    	    otherlv_24=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle3027); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1353:1: ( (lv_children_25_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1354:1: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1354:1: (lv_children_25_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1355:3: lv_children_25_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3048);
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
                    	    break loop31;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_26=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3064); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1383:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1384:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1385:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3100);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl3110); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1392:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1395:28: ( ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1396:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1396:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1396:2: () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1396:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1397:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKPolyline_Impl3156); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getKPolylineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3168); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1410:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==21) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1410:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolyline_Impl3181); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolyline_Impl3193); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1418:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1419:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1419:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1420:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3214);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1436:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==13) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1436:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3227); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1440:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1441:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1441:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1442:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3248);
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
                    	    break loop33;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1458:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==20) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1458:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolyline_Impl3265); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolyline_Impl3277); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1466:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1467:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1467:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1468:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3298);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1484:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==24) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1484:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKPolyline_Impl3313); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolyline_Impl3325); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1492:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1493:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1493:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1494:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3346);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1510:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==25) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1510:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKPolyline_Impl3361); 

                        	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolyline_Impl3373); 

                        	newLeafNode(otherlv_15, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1518:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1519:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1519:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1520:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3394);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1536:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==13) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1536:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3407); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1540:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1541:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1541:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1542:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3428);
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
                    	    break loop37;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3444); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1570:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1571:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1572:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon3480);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon3490); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1579:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1582:28: ( ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1583:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1583:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1583:2: () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1583:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1584:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKPolygon3536); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getKPolygonKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon3548); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1597:1: (otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )* )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==21) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1597:3: otherlv_3= 'styles' otherlv_4= ':' ( (lv_styles_5_0= ruleKStyle ) ) (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    {
                    otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolygon3561); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getStylesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolygon3573); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getColonKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1605:1: ( (lv_styles_5_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1606:1: (lv_styles_5_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1606:1: (lv_styles_5_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1607:3: lv_styles_5_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon3594);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1623:2: (otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) ) )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==13) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1623:4: otherlv_6= ',' ( (lv_styles_7_0= ruleKStyle ) )
                    	    {
                    	    otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon3607); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1627:1: ( (lv_styles_7_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1628:1: (lv_styles_7_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1628:1: (lv_styles_7_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1629:3: lv_styles_7_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon3628);
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
                    	    break loop39;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1645:6: (otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==20) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1645:8: otherlv_8= 'placementData' otherlv_9= ':' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolygon3645); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_4_0());
                        
                    otherlv_9=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolygon3657); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getColonKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1653:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1654:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1654:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1655:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon3678);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1671:4: (otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==24) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1671:6: otherlv_11= 'childPlacement' otherlv_12= ':' ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    {
                    otherlv_11=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKPolygon3693); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolygon3705); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getColonKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1679:1: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1680:1: (lv_childPlacement_13_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1680:1: (lv_childPlacement_13_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1681:3: lv_childPlacement_13_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon3726);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1697:4: (otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )* )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==25) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1697:6: otherlv_14= 'children' otherlv_15= ':' ( (lv_children_16_0= ruleKRendering ) ) (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    {
                    otherlv_14=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKPolygon3741); 

                        	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getChildrenKeyword_6_0());
                        
                    otherlv_15=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolygon3753); 

                        	newLeafNode(otherlv_15, grammarAccess.getKPolygonAccess().getColonKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1705:1: ( (lv_children_16_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1706:1: (lv_children_16_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1706:1: (lv_children_16_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1707:3: lv_children_16_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon3774);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:2: (otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==13) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:4: otherlv_17= ',' ( (lv_children_18_0= ruleKRendering ) )
                    	    {
                    	    otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon3787); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1727:1: ( (lv_children_18_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1728:1: (lv_children_18_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1728:1: (lv_children_18_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1729:3: lv_children_18_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon3808);
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
                    	    break loop43;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon3824); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1757:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1758:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1759:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage3860);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage3870); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1766:1: ruleKImage returns [EObject current=null] : (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1769:28: ( (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1770:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1770:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1770:3: otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKImage3907); 

                	newLeafNode(otherlv_0, grammarAccess.getKImageAccess().getKImageKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage3919); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKImage3931); 

                	newLeafNode(otherlv_2, grammarAccess.getKImageAccess().getBundleNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1782:1: ( (lv_bundleName_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1783:1: (lv_bundleName_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1783:1: (lv_bundleName_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1784:3: lv_bundleName_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage3952);
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

            otherlv_4=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKImage3964); 

                	newLeafNode(otherlv_4, grammarAccess.getKImageAccess().getImagePathKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1804:1: ( (lv_imagePath_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1805:1: (lv_imagePath_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1805:1: (lv_imagePath_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1806:3: lv_imagePath_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage3985);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1822:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==16) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1822:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKImage3998); 

                        	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4010); 

                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1830:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1831:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1831:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1832:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKImageRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4033);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1845:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==13) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1845:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4046); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1849:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1850:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1850:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1851:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKImageRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4069);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage4083); 

                        	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1868:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==20) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1868:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKImage4098); 

                        	newLeafNode(otherlv_12, grammarAccess.getKImageAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1872:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1873:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1873:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1874:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage4119);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1890:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==21) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1890:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKImage4134); 

                        	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4146); 

                        	newLeafNode(otherlv_15, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1898:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1899:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1899:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1900:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4167);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1916:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==13) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1916:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4180); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKImageAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1920:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1921:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1921:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1922:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4201);
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
                    	    break loop48;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4215); 

                        	newLeafNode(otherlv_19, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1942:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==25) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1942:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKImage4230); 

                        	newLeafNode(otherlv_20, grammarAccess.getKImageAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4242); 

                        	newLeafNode(otherlv_21, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1950:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1951:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1951:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1952:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4263);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1968:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==13) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1968:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4276); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKImageAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1972:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1973:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1973:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1974:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4297);
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
                    	    break loop50;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4311); 

                        	newLeafNode(otherlv_25, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1994:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==24) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1994:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKImage4326); 

                        	newLeafNode(otherlv_26, grammarAccess.getKImageAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1998:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1999:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1999:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2000:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage4347);
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

            otherlv_28=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4361); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2028:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2029:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2030:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc4397);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc4407); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2037:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2040:28: ( ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2041:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2041:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2041:2: () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2041:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2042:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKArc4453); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getKArcKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc4465); 

                	newLeafNode(otherlv_2, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2055:1: (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==36) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2055:3: otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKArc4478); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getStartAngleKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2059:1: ( (lv_startAngle_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2060:1: (lv_startAngle_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2060:1: (lv_startAngle_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2061:3: lv_startAngle_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4499);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2077:4: (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==37) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2077:6: otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKArc4514); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getArcAngleKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2081:1: ( (lv_arcAngle_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2082:1: (lv_arcAngle_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2082:1: (lv_arcAngle_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2083:3: lv_arcAngle_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4535);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2099:4: (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==16) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2099:6: otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKArc4550); 

                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getReferencesKeyword_5_0());
                        
                    otherlv_8=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc4562); 

                        	newLeafNode(otherlv_8, grammarAccess.getKArcAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2107:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2108:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2108:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2109:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKArcRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc4585);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2122:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==13) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2122:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4598); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getKArcAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2126:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2127:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2127:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2128:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKArcRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc4621);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop55;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc4635); 

                        	newLeafNode(otherlv_12, grammarAccess.getKArcAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2145:3: (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==20) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2145:5: otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) )
                    {
                    otherlv_13=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKArc4650); 

                        	newLeafNode(otherlv_13, grammarAccess.getKArcAccess().getPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2149:1: ( (lv_placementData_14_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2150:1: (lv_placementData_14_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2150:1: (lv_placementData_14_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2151:3: lv_placementData_14_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc4671);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2167:4: (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==21) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2167:6: otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}'
                    {
                    otherlv_15=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKArc4686); 

                        	newLeafNode(otherlv_15, grammarAccess.getKArcAccess().getStylesKeyword_7_0());
                        
                    otherlv_16=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc4698); 

                        	newLeafNode(otherlv_16, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2175:1: ( (lv_styles_17_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2176:1: (lv_styles_17_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2176:1: (lv_styles_17_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2177:3: lv_styles_17_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc4719);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2193:2: (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==13) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2193:4: otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) )
                    	    {
                    	    otherlv_18=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4732); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2197:1: ( (lv_styles_19_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2198:1: (lv_styles_19_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2198:1: (lv_styles_19_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2199:3: lv_styles_19_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc4753);
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
                    	    break loop58;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc4767); 

                        	newLeafNode(otherlv_20, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2219:3: (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==25) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2219:5: otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}'
                    {
                    otherlv_21=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKArc4782); 

                        	newLeafNode(otherlv_21, grammarAccess.getKArcAccess().getChildrenKeyword_8_0());
                        
                    otherlv_22=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc4794); 

                        	newLeafNode(otherlv_22, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2227:1: ( (lv_children_23_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2228:1: (lv_children_23_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2228:1: (lv_children_23_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2229:3: lv_children_23_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc4815);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2245:2: (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==13) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2245:4: otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) )
                    	    {
                    	    otherlv_24=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4828); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getKArcAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2249:1: ( (lv_children_25_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2250:1: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2250:1: (lv_children_25_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2251:3: lv_children_25_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc4849);
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
                    	    break loop60;
                        }
                    } while (true);

                    otherlv_26=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc4863); 

                        	newLeafNode(otherlv_26, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2271:3: (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==24) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2271:5: otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    {
                    otherlv_27=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKArc4878); 

                        	newLeafNode(otherlv_27, grammarAccess.getKArcAccess().getChildPlacementKeyword_9_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2275:1: ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2276:1: (lv_childPlacement_28_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2276:1: (lv_childPlacement_28_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2277:3: lv_childPlacement_28_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc4899);
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

            otherlv_29=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc4913); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2305:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2306:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2307:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea4949);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea4959); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2314:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2317:28: ( ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2318:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2318:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2318:2: () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2318:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2319:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKChildArea5005); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getKChildAreaKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea5017); 

                	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2332:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==16) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2332:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKChildArea5030); 

                        	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea5042); 

                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2340:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2341:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2341:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2342:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea5065);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2355:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==13) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2355:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKChildArea5078); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2359:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2360:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2360:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2361:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea5101);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop63;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea5115); 

                        	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2378:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==20) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2378:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKChildArea5130); 

                        	newLeafNode(otherlv_9, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2382:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2383:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2383:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2384:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea5151);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2400:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==21) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2400:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKChildArea5166); 

                        	newLeafNode(otherlv_11, grammarAccess.getKChildAreaAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea5178); 

                        	newLeafNode(otherlv_12, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2408:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2409:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2409:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2410:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5199);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2426:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==13) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2426:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKChildArea5212); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKChildAreaAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2430:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2431:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2431:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2432:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5233);
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
                    	    break loop66;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea5247); 

                        	newLeafNode(otherlv_16, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea5261); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2464:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2465:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2466:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText5297);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText5307); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2473:1: ruleKText returns [EObject current=null] : ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2476:28: ( ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2477:1: ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2477:1: ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2477:2: ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2477:2: ( (lv_clip_0_0= 'clip' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2478:1: (lv_clip_0_0= 'clip' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2478:1: (lv_clip_0_0= 'clip' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2479:3: lv_clip_0_0= 'clip'
            {
            lv_clip_0_0=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKText5350); 

                    newLeafNode(lv_clip_0_0, grammarAccess.getKTextAccess().getClipClipKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKTextRule());
            	        }
                   		setWithLastConsumed(current, "clip", true, "clip");
            	    

            }


            }

            otherlv_1=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKText5375); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getKTextKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5387); 

                	newLeafNode(otherlv_2, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2500:1: (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==41) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2500:3: otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKText5400); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getTextKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2504:1: ( (lv_text_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2505:1: (lv_text_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2505:1: (lv_text_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2506:3: lv_text_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText5421);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2522:4: (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==16) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2522:6: otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')'
                    {
                    otherlv_5=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKText5436); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getReferencesKeyword_4_0());
                        
                    otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5448); 

                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getLeftParenthesisKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2530:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2531:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2531:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2532:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKTextRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText5471);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2545:2: (otherlv_8= ',' ( ( ruleEString ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==13) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2545:4: otherlv_8= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_8=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5484); 

                    	        	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2549:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2550:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2550:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2551:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKTextRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText5507);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText5521); 

                        	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getRightParenthesisKeyword_4_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2568:3: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==20) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2568:5: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                    {
                    otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKText5536); 

                        	newLeafNode(otherlv_11, grammarAccess.getKTextAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2572:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2573:1: (lv_placementData_12_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2573:1: (lv_placementData_12_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2574:3: lv_placementData_12_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText5557);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2590:4: (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==21) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2590:6: otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}'
                    {
                    otherlv_13=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKText5572); 

                        	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getStylesKeyword_6_0());
                        
                    otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5584); 

                        	newLeafNode(otherlv_14, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2598:1: ( (lv_styles_15_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2599:1: (lv_styles_15_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2599:1: (lv_styles_15_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2600:3: lv_styles_15_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5605);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2616:2: (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )*
                    loop72:
                    do {
                        int alt72=2;
                        int LA72_0 = input.LA(1);

                        if ( (LA72_0==13) ) {
                            alt72=1;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2616:4: otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) )
                    	    {
                    	    otherlv_16=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5618); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKTextAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2620:1: ( (lv_styles_17_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2621:1: (lv_styles_17_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2621:1: (lv_styles_17_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2622:3: lv_styles_17_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5639);
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
                    	    break loop72;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText5653); 

                        	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2642:3: (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==25) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2642:5: otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}'
                    {
                    otherlv_19=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKText5668); 

                        	newLeafNode(otherlv_19, grammarAccess.getKTextAccess().getChildrenKeyword_7_0());
                        
                    otherlv_20=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5680); 

                        	newLeafNode(otherlv_20, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2650:1: ( (lv_children_21_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2651:1: (lv_children_21_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2651:1: (lv_children_21_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2652:3: lv_children_21_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5701);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2668:2: (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==13) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2668:4: otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) )
                    	    {
                    	    otherlv_22=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5714); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getKTextAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2672:1: ( (lv_children_23_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2673:1: (lv_children_23_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2673:1: (lv_children_23_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2674:3: lv_children_23_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5735);
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
                    	    break loop74;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText5749); 

                        	newLeafNode(otherlv_24, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2694:3: (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==24) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2694:5: otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    {
                    otherlv_25=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKText5764); 

                        	newLeafNode(otherlv_25, grammarAccess.getKTextAccess().getChildPlacementKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2698:1: ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2699:1: (lv_childPlacement_26_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2699:1: (lv_childPlacement_26_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2700:3: lv_childPlacement_26_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText5785);
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

            otherlv_27=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText5799); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2728:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2729:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2730:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering5835);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering5845); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2737:1: ruleKCustomRendering returns [EObject current=null] : (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2740:28: ( (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2741:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2741:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2741:3: otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKCustomRendering5882); 

                	newLeafNode(otherlv_0, grammarAccess.getKCustomRenderingAccess().getKCustomRenderingKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering5894); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKCustomRendering5906); 

                	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2753:1: ( (lv_className_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2754:1: (lv_className_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2754:1: (lv_className_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2755:3: lv_className_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering5927);
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

            otherlv_4=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKCustomRendering5939); 

                	newLeafNode(otherlv_4, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2775:1: ( (lv_bundleName_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2776:1: (lv_bundleName_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2776:1: (lv_bundleName_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2777:3: lv_bundleName_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering5960);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2793:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==16) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2793:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKCustomRendering5973); 

                        	newLeafNode(otherlv_6, grammarAccess.getKCustomRenderingAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering5985); 

                        	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2801:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2802:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2802:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2803:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6008);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2816:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==13) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2816:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6021); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2820:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2821:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2821:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2822:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6044);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop77;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering6058); 

                        	newLeafNode(otherlv_11, grammarAccess.getKCustomRenderingAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2839:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==20) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2839:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKCustomRendering6073); 

                        	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2843:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2844:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2844:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2845:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6094);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2861:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==21) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2861:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKCustomRendering6109); 

                        	newLeafNode(otherlv_14, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6121); 

                        	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2869:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2870:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2870:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2871:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6142);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2887:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==13) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2887:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6155); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2891:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2892:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2892:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2893:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6176);
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
                    	    break loop80;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6190); 

                        	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2913:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==25) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2913:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKCustomRendering6205); 

                        	newLeafNode(otherlv_20, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6217); 

                        	newLeafNode(otherlv_21, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2921:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2922:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2922:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2923:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6238);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2939:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==13) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2939:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6251); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2943:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2944:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2944:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2945:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6272);
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
                    	    break loop82;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6286); 

                        	newLeafNode(otherlv_25, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2965:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==24) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2965:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKCustomRendering6301); 

                        	newLeafNode(otherlv_26, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2969:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2970:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2970:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2971:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering6322);
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

            otherlv_28=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6336); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2999:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3000:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3001:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline6372);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline6382); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3008:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3011:28: ( ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3012:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3012:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3012:2: () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3012:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3013:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKSpline6428); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getKSplineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6440); 

                	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3026:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==16) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3026:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKSpline6453); 

                        	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6465); 

                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3034:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3035:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3035:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3036:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline6488);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3049:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==13) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3049:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6501); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3053:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3054:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3054:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3055:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline6524);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop85;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline6538); 

                        	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3072:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==20) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3072:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKSpline6553); 

                        	newLeafNode(otherlv_9, grammarAccess.getKSplineAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3076:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3077:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3077:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3078:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline6574);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3094:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==21) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3094:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKSpline6589); 

                        	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6601); 

                        	newLeafNode(otherlv_12, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3102:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3103:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3103:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3104:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6622);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3120:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop88:
                    do {
                        int alt88=2;
                        int LA88_0 = input.LA(1);

                        if ( (LA88_0==13) ) {
                            alt88=1;
                        }


                        switch (alt88) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3120:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6635); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKSplineAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3124:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3125:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3125:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3126:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6656);
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
                    	    break loop88;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6670); 

                        	newLeafNode(otherlv_16, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3146:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==25) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3146:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKSpline6685); 

                        	newLeafNode(otherlv_17, grammarAccess.getKSplineAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6697); 

                        	newLeafNode(otherlv_18, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3154:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3155:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3155:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3156:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6718);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3172:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==13) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3172:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6731); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKSplineAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3176:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3177:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3177:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3178:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6752);
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
                    	    break loop90;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6766); 

                        	newLeafNode(otherlv_22, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3198:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==24) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3198:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKSpline6781); 

                        	newLeafNode(otherlv_23, grammarAccess.getKSplineAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3202:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3203:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3203:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3204:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline6802);
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

            otherlv_25=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6816); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3232:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3233:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3234:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6852);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6862); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3241:1: ruleKDecoratorPlacementData returns [EObject current=null] : ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3244:28: ( ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3245:1: ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3245:1: ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3245:2: ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3245:2: ( (lv_relative_0_0= 'relative' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3246:1: (lv_relative_0_0= 'relative' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3246:1: (lv_relative_0_0= 'relative' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3247:3: lv_relative_0_0= 'relative'
            {
            lv_relative_0_0=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKDecoratorPlacementData6905); 

                    newLeafNode(lv_relative_0_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
            	        }
                   		setWithLastConsumed(current, "relative", true, "relative");
            	    

            }


            }

            otherlv_1=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKDecoratorPlacementData6930); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getKDecoratorPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData6942); 

                	newLeafNode(otherlv_2, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKDecoratorPlacementData6954); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3272:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3273:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3273:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3274:3: lv_location_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getLocationEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6975);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3290:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==48) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3290:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKDecoratorPlacementData6988); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3294:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3295:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3295:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3296:3: lv_xOffset_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7009);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3312:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==49) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3312:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKDecoratorPlacementData7024); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3316:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3317:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3317:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3318:3: lv_yOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7045);
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

            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDecoratorPlacementData7059); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3346:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3347:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3348:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7095);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData7105); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3355:1: ruleKGridPlacementData returns [EObject current=null] : (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3358:28: ( (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3359:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3359:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3359:3: otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKGridPlacementData7142); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData7154); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKGridPlacementData7166); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3371:1: ( (lv_widthHint_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3372:1: (lv_widthHint_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3372:1: (lv_widthHint_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3373:3: lv_widthHint_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7187);
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

            otherlv_4=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKGridPlacementData7199); 

                	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3393:1: ( (lv_heightHint_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3394:1: (lv_heightHint_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3394:1: (lv_heightHint_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3395:3: lv_heightHint_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7220);
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

            otherlv_6=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKGridPlacementData7232); 

                	newLeafNode(otherlv_6, grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3415:1: ( (lv_horizontalIndent_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3416:1: (lv_horizontalIndent_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3416:1: (lv_horizontalIndent_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3417:3: lv_horizontalIndent_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7253);
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

            otherlv_8=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKGridPlacementData7265); 

                	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getVerticalIndentKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3437:1: ( (lv_verticalIndent_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3438:1: (lv_verticalIndent_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3438:1: (lv_verticalIndent_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3439:3: lv_verticalIndent_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getVerticalIndentEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7286);
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

            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKGridPlacementData7298); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3467:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3468:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3469:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData7334);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData7344); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3476:1: ruleKStackPlacementData returns [EObject current=null] : (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3479:28: ( (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3480:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3480:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3480:3: otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKStackPlacementData7381); 

                	newLeafNode(otherlv_0, grammarAccess.getKStackPlacementDataAccess().getKStackPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData7393); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKStackPlacementData7405); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3492:1: ( (lv_insetRight_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3493:1: (lv_insetRight_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3493:1: (lv_insetRight_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3494:3: lv_insetRight_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7426);
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

            otherlv_4=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKStackPlacementData7438); 

                	newLeafNode(otherlv_4, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3514:1: ( (lv_insetBottom_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3515:1: (lv_insetBottom_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3515:1: (lv_insetBottom_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3516:3: lv_insetBottom_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7459);
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

            otherlv_6=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKStackPlacementData7471); 

                	newLeafNode(otherlv_6, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3536:1: ( (lv_insetLeft_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3537:1: (lv_insetLeft_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3537:1: (lv_insetLeft_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3538:3: lv_insetLeft_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7492);
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

            otherlv_8=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKStackPlacementData7504); 

                	newLeafNode(otherlv_8, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3558:1: ( (lv_insetTop_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3559:1: (lv_insetTop_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3559:1: (lv_insetTop_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3560:3: lv_insetTop_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7525);
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

            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKStackPlacementData7537); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3588:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3589:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3590:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7573);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData7583); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3597:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3600:28: ( (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3601:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3601:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3601:3: otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKDirectPlacementData7620); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getKDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData7632); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKDirectPlacementData7644); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3613:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3614:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3614:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3615:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7665);
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

            otherlv_4=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKDirectPlacementData7677); 

                	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3635:1: ( (lv_bottomRight_5_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3636:1: (lv_bottomRight_5_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3636:1: (lv_bottomRight_5_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3637:3: lv_bottomRight_5_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7698);
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

            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDirectPlacementData7710); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3665:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3666:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3667:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData7746);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData7756); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3674:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* ) ;
    public final EObject ruleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_points_2_0 = null;

        EObject lv_points_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3677:28: ( (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3678:1: (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3678:1: (otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )* )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3678:3: otherlv_0= 'points' otherlv_1= ':' ( (lv_points_2_0= ruleKPosition ) ) (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )*
            {
            otherlv_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKPolylinePlacementData7793); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_0());
                
            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKPolylinePlacementData7805); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3686:1: ( (lv_points_2_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3687:1: (lv_points_2_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3687:1: (lv_points_2_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3688:3: lv_points_2_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7826);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3704:2: (otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) ) )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==13) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3704:4: otherlv_3= ',' ( (lv_points_4_0= ruleKPosition ) )
            	    {
            	    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolylinePlacementData7839); 

            	        	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_3_0());
            	        
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3708:1: ( (lv_points_4_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3709:1: (lv_points_4_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3709:1: (lv_points_4_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3710:3: lv_points_4_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7860);
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
            	    break loop95;
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
    // $ANTLR end "ruleKPolylinePlacementData"


    // $ANTLR start "entryRuleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3734:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3735:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3736:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat7899);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat7910); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3743:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3746:28: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3747:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3747:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3747:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3747:2: (kw= '-' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==64) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3748:2: kw= '-'
                    {
                    kw=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleEFloat7949); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3753:3: (this_INT_1= RULE_INT )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==RULE_INT) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3753:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat7967); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleEFloat7987); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 
                
            this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat8002); 

            		current.merge(this_INT_3);
                
             
                newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3773:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( ((LA100_0>=66 && LA100_0<=67)) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3773:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3773:2: (kw= 'E' | kw= 'e' )
                    int alt98=2;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==66) ) {
                        alt98=1;
                    }
                    else if ( (LA98_0==67) ) {
                        alt98=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 98, 0, input);

                        throw nvae;
                    }
                    switch (alt98) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3774:2: kw= 'E'
                            {
                            kw=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleEFloat8022); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3781:2: kw= 'e'
                            {
                            kw=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleEFloat8041); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3786:2: (kw= '-' )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==64) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3787:2: kw= '-'
                            {
                            kw=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleEFloat8056); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat8073); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3809:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3810:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3811:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition8122);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition8132); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3818:1: ruleKPosition returns [EObject current=null] : (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3821:28: ( (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3822:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3822:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3822:3: otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKPosition8169); 

                	newLeafNode(otherlv_0, grammarAccess.getKPositionAccess().getKPositionKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPosition8181); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKPosition8193); 

                	newLeafNode(otherlv_2, grammarAccess.getKPositionAccess().getXKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3834:1: ( (lv_x_3_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3835:1: (lv_x_3_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3835:1: (lv_x_3_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3836:3: lv_x_3_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition8214);
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

            otherlv_4=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKPosition8226); 

                	newLeafNode(otherlv_4, grammarAccess.getKPositionAccess().getYKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3856:1: ( (lv_y_5_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3857:1: (lv_y_5_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3857:1: (lv_y_5_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3858:3: lv_y_5_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition8247);
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

            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPosition8259); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3886:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3887:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3888:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8295);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition8305); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3895:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3898:28: ( ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3899:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3899:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3899:2: () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3899:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3900:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKLeftPosition8351); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getKLeftPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLeftPosition8363); 

                	newLeafNode(otherlv_2, grammarAccess.getKLeftPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3913:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==72) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3913:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKLeftPosition8376); 

                        	newLeafNode(otherlv_3, grammarAccess.getKLeftPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3917:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3918:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3918:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3919:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8397);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3935:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==45) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3935:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKLeftPosition8412); 

                        	newLeafNode(otherlv_5, grammarAccess.getKLeftPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3939:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3940:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3940:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3941:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8433);
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

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKLeftPosition8447); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3969:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3970:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3971:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8483);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition8493); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3978:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3981:28: ( ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3982:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3982:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3982:2: () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3982:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3983:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKRightPosition8539); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getKRightPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRightPosition8551); 

                	newLeafNode(otherlv_2, grammarAccess.getKRightPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3996:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==72) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3996:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKRightPosition8564); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRightPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4000:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4001:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4001:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4002:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8585);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4018:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==45) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4018:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKRightPosition8600); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRightPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4022:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4023:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4023:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4024:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8621);
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

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRightPosition8635); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4052:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4053:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4054:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8671);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition8681); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4061:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4064:28: ( ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4065:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4065:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4065:2: () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4065:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4066:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKTopPosition8727); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getKTopPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKTopPosition8739); 

                	newLeafNode(otherlv_2, grammarAccess.getKTopPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4079:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==72) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4079:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKTopPosition8752); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTopPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4083:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4084:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4084:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4085:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8773);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4101:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==45) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4101:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKTopPosition8788); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTopPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4105:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4106:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4106:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4107:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8809);
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

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKTopPosition8823); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4135:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4136:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4137:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8859);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition8869); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4144:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4147:28: ( ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4148:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4148:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4148:2: () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4148:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4149:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKBottomPosition8915); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getKBottomPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBottomPosition8927); 

                	newLeafNode(otherlv_2, grammarAccess.getKBottomPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4162:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==72) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4162:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKBottomPosition8940); 

                        	newLeafNode(otherlv_3, grammarAccess.getKBottomPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4166:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4167:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4167:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4168:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8961);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4184:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==45) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4184:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKBottomPosition8976); 

                        	newLeafNode(otherlv_5, grammarAccess.getKBottomPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4188:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4189:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4189:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4190:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8997);
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

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKBottomPosition9011); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4218:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4219:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4220:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor9047);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor9057); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4227:1: ruleKForegroundColor returns [EObject current=null] : ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4230:28: ( ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4231:1: ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4231:1: ( () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4231:2: () otherlv_1= 'KForegroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4231:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4232:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundColorAccess().getKForegroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKForegroundColor9103); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getKForegroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKForegroundColor9115); 

                	newLeafNode(otherlv_2, grammarAccess.getKForegroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4245:1: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==77) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4246:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4246:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4247:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKForegroundColor9133); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKForegroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundColorRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4260:3: (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==78) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4260:5: otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) )
                    {
                    otherlv_4=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleKForegroundColor9160); 

                        	newLeafNode(otherlv_4, grammarAccess.getKForegroundColorAccess().getRedKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4264:1: ( (lv_red_5_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4265:1: (lv_red_5_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4265:1: (lv_red_5_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4266:3: lv_red_5_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9181);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4282:4: (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==79) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4282:6: otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) )
                    {
                    otherlv_6=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleKForegroundColor9196); 

                        	newLeafNode(otherlv_6, grammarAccess.getKForegroundColorAccess().getGreenKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4286:1: ( (lv_green_7_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4287:1: (lv_green_7_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4287:1: (lv_green_7_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4288:3: lv_green_7_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9217);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4304:4: (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==80) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4304:6: otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) )
                    {
                    otherlv_8=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleKForegroundColor9232); 

                        	newLeafNode(otherlv_8, grammarAccess.getKForegroundColorAccess().getBlueKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4308:1: ( (lv_blue_9_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4309:1: (lv_blue_9_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4309:1: (lv_blue_9_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4310:3: lv_blue_9_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9253);
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

            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKForegroundColor9267); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4338:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4339:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4340:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor9303);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor9313); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4347:1: ruleKBackgroundColor returns [EObject current=null] : ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4350:28: ( ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4351:1: ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4351:1: ( () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4351:2: () otherlv_1= 'KBackgroundColor' otherlv_2= '{' ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )? (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )? (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )? otherlv_10= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4351:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4352:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundColorAccess().getKBackgroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleKBackgroundColor9359); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getKBackgroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBackgroundColor9371); 

                	newLeafNode(otherlv_2, grammarAccess.getKBackgroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4365:1: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==77) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4366:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4366:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4367:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKBackgroundColor9389); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKBackgroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundColorRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4380:3: (otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==78) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4380:5: otherlv_4= 'red' ( (lv_red_5_0= ruleEInt ) )
                    {
                    otherlv_4=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleKBackgroundColor9416); 

                        	newLeafNode(otherlv_4, grammarAccess.getKBackgroundColorAccess().getRedKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4384:1: ( (lv_red_5_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4385:1: (lv_red_5_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4385:1: (lv_red_5_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4386:3: lv_red_5_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9437);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4402:4: (otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==79) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4402:6: otherlv_6= 'green' ( (lv_green_7_0= ruleEInt ) )
                    {
                    otherlv_6=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleKBackgroundColor9452); 

                        	newLeafNode(otherlv_6, grammarAccess.getKBackgroundColorAccess().getGreenKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4406:1: ( (lv_green_7_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4407:1: (lv_green_7_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4407:1: (lv_green_7_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4408:3: lv_green_7_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9473);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4424:4: (otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) ) )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==80) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4424:6: otherlv_8= 'blue' ( (lv_blue_9_0= ruleEInt ) )
                    {
                    otherlv_8=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleKBackgroundColor9488); 

                        	newLeafNode(otherlv_8, grammarAccess.getKBackgroundColorAccess().getBlueKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4428:1: ( (lv_blue_9_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4429:1: (lv_blue_9_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4429:1: (lv_blue_9_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4430:3: lv_blue_9_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9509);
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

            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKBackgroundColor9523); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4458:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4459:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4460:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9559);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth9569); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4467:1: ruleKLineWidth returns [EObject current=null] : (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_lineWidth_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4470:28: ( (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4471:1: (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4471:1: (otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4471:3: otherlv_0= 'KLineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            {
            otherlv_0=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKLineWidth9606); 

                	newLeafNode(otherlv_0, grammarAccess.getKLineWidthAccess().getKLineWidthKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4475:1: ( (lv_lineWidth_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4476:1: (lv_lineWidth_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4476:1: (lv_lineWidth_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4477:3: lv_lineWidth_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth9627);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4493:2: ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==77) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4494:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4494:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4495:3: lv_propagateToChildren_2_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKLineWidth9645); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4516:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4517:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4518:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility9695);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility9705); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4525:1: ruleKVisibility returns [EObject current=null] : (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundVisibility_0 = null;

        EObject this_KBackgroundVisibility_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4528:28: ( (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4529:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4529:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==83) ) {
                alt118=1;
            }
            else if ( (LA118_0==85) ) {
                alt118=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }
            switch (alt118) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4530:5: this_KForegroundVisibility_0= ruleKForegroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKForegroundVisibilityParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility9752);
                    this_KForegroundVisibility_0=ruleKForegroundVisibility();

                    state._fsp--;

                     
                            current = this_KForegroundVisibility_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4540:5: this_KBackgroundVisibility_1= ruleKBackgroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKBackgroundVisibilityParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility9779);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4556:1: entryRuleKForegroundVisibility returns [EObject current=null] : iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF ;
    public final EObject entryRuleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4557:2: (iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4558:2: iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKForegroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility9814);
            iv_ruleKForegroundVisibility=ruleKForegroundVisibility();

            state._fsp--;

             current =iv_ruleKForegroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundVisibility9824); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4565:1: ruleKForegroundVisibility returns [EObject current=null] : ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_visible_2_0=null;
        Token lv_propagateToChildren_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4568:28: ( ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4569:1: ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4569:1: ( () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4569:2: () otherlv_1= 'KForegroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4569:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4570:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKForegroundVisibility9870); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4579:1: ( (lv_visible_2_0= 'visible' ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==84) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4580:1: (lv_visible_2_0= 'visible' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4580:1: (lv_visible_2_0= 'visible' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4581:3: lv_visible_2_0= 'visible'
                    {
                    lv_visible_2_0=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKForegroundVisibility9888); 

                            newLeafNode(lv_visible_2_0, grammarAccess.getKForegroundVisibilityAccess().getVisibleVisibleKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKForegroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "visible", true, "visible");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4594:3: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==77) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4595:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4595:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4596:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKForegroundVisibility9920); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4617:1: entryRuleKBackgroundVisibility returns [EObject current=null] : iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF ;
    public final EObject entryRuleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4618:2: (iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4619:2: iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility9970);
            iv_ruleKBackgroundVisibility=ruleKBackgroundVisibility();

            state._fsp--;

             current =iv_ruleKBackgroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundVisibility9980); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4626:1: ruleKBackgroundVisibility returns [EObject current=null] : ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_visible_2_0=null;
        Token lv_propagateToChildren_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4629:28: ( ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4630:1: ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4630:1: ( () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4630:2: () otherlv_1= 'KBackgroundVisibility' ( (lv_visible_2_0= 'visible' ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4630:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4631:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKBackgroundVisibility10026); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4640:1: ( (lv_visible_2_0= 'visible' ) )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==84) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4641:1: (lv_visible_2_0= 'visible' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4641:1: (lv_visible_2_0= 'visible' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4642:3: lv_visible_2_0= 'visible'
                    {
                    lv_visible_2_0=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKBackgroundVisibility10044); 

                            newLeafNode(lv_visible_2_0, grammarAccess.getKBackgroundVisibilityAccess().getVisibleVisibleKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKBackgroundVisibilityRule());
                    	        }
                           		setWithLastConsumed(current, "visible", true, "visible");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4655:3: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==77) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4656:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4656:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4657:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKBackgroundVisibility10076); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4678:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4679:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4680:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle10126);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle10136); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4687:1: ruleKLineStyle returns [EObject current=null] : ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_lineStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4690:28: ( ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4691:1: ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4691:1: ( () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4691:2: () otherlv_1= 'KLineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) )? ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4691:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4692:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineStyleAccess().getKLineStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKLineStyle10182); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getKLineStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4701:1: ( (lv_lineStyle_2_0= ruleLineStyle ) )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( ((LA123_0>=98 && LA123_0<=102)) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4702:1: (lv_lineStyle_2_0= ruleLineStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4702:1: (lv_lineStyle_2_0= ruleLineStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4703:3: lv_lineStyle_2_0= ruleLineStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle10203);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4719:3: ( (lv_propagateToChildren_3_0= 'propagateToChildren' ) )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==77) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4720:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4720:1: (lv_propagateToChildren_3_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4721:3: lv_propagateToChildren_3_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKLineStyle10222); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4742:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4743:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4744:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10272);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment10282); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4751:1: ruleKVerticalAlignment returns [EObject current=null] : (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        Enumerator lv_verticalAlignment_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4754:28: ( (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4755:1: (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4755:1: (otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4755:3: otherlv_0= 'KVerticalAlignment' ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            {
            otherlv_0=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleKVerticalAlignment10319); 

                	newLeafNode(otherlv_0, grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4759:1: ( (lv_verticalAlignment_1_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4760:1: (lv_verticalAlignment_1_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4760:1: (lv_verticalAlignment_1_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4761:3: lv_verticalAlignment_1_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10340);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4777:2: ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==77) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4778:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4778:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4779:3: lv_propagateToChildren_2_0= 'propagateToChildren'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKVerticalAlignment10358); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4800:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4801:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4802:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10408);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment10418); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4809:1: ruleKHorizontalAlignment returns [EObject current=null] : (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        Enumerator lv_horizontalAlignment_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4812:28: ( (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4813:1: (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4813:1: (otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4813:3: otherlv_0= 'KHorizontalAlignment' ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )
            {
            otherlv_0=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleKHorizontalAlignment10455); 

                	newLeafNode(otherlv_0, grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4817:1: ( (lv_horizontalAlignment_1_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4818:1: (lv_horizontalAlignment_1_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4818:1: (lv_horizontalAlignment_1_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4819:3: lv_horizontalAlignment_1_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10476);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4835:2: ( (lv_propagateToChildren_2_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4836:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4836:1: (lv_propagateToChildren_2_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4837:3: lv_propagateToChildren_2_0= 'propagateToChildren'
            {
            lv_propagateToChildren_2_0=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKHorizontalAlignment10494); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4858:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4859:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4860:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement10543);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement10553); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4867:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_numColumns_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4870:28: ( ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4871:1: ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4871:1: ( () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4871:2: () otherlv_1= 'KGridPlacement' ( (lv_numColumns_2_0= ruleEInt ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4871:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4872:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,89,FollowSets000.FOLLOW_89_in_ruleKGridPlacement10599); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getKGridPlacementKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4881:1: ( (lv_numColumns_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4882:1: (lv_numColumns_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4882:1: (lv_numColumns_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4883:3: lv_numColumns_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement10620);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4907:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4908:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4909:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement10656);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement10666); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4916:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'KStackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4919:28: ( ( () otherlv_1= 'KStackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4920:1: ( () otherlv_1= 'KStackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4920:1: ( () otherlv_1= 'KStackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4920:2: () otherlv_1= 'KStackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4920:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4921:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKStackPlacement10712); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4938:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4939:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4940:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt10749);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt10760); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4947:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4950:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4951:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4951:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4951:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4951:2: (kw= '-' )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==64) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4952:2: kw= '-'
                    {
                    kw=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleEInt10799); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt10816); 

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


    // $ANTLR start "entryRuleKInsets"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4974:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4975:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4976:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets10863);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets10873); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4983:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4986:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4987:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4987:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4987:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4987:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4988:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleKInsets10919); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets10931); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5001:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==92) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5001:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleKInsets10944); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5005:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5006:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5006:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5007:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets10965);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5023:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==93) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5023:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleKInsets10980); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5027:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5028:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5028:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5029:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11001);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5045:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==94) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5045:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleKInsets11016); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5049:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5050:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5050:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5051:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11037);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5067:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==95) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5067:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleKInsets11052); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5071:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5072:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5072:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5073:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11073);
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

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKInsets11087); 

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


    // $ANTLR start "entryRuleKPoint"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5103:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5104:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5105:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint11125);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint11135); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5112:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_x_3_0 = null;

        AntlrDatatypeRuleToken lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5115:28: ( ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5116:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5116:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5116:2: () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5116:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5117:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleKPoint11181); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5126:1: (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5126:3: otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) )
            {
            otherlv_2=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKPoint11194); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getXKeyword_2_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5130:1: ( (lv_x_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5131:1: (lv_x_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5131:1: (lv_x_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5132:3: lv_x_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11215);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5148:3: (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5148:5: otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) )
            {
            otherlv_4=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKPoint11229); 

                	newLeafNode(otherlv_4, grammarAccess.getKPointAccess().getYKeyword_3_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5152:1: ( (lv_y_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5153:1: (lv_y_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5153:1: (lv_y_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5154:3: lv_y_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11250);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5178:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5179:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5180:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry11287);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry11297); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5187:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5190:28: ( ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5191:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5191:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5191:2: ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5191:2: ( (lv_key_0_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5192:1: (lv_key_0_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5192:1: (lv_key_0_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5193:3: lv_key_0_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry11343);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5209:2: (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==97) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5209:4: otherlv_1= '=' ( (lv_value_2_0= ruleEString ) )
                    {
                    otherlv_1=(Token)match(input,97,FollowSets000.FOLLOW_97_in_rulePersistentEntry11356); 

                        	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5213:1: ( (lv_value_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5214:1: (lv_value_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5214:1: (lv_value_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5215:3: lv_value_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry11377);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5239:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5240:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5241:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString11416);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString11427); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5248:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5251:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5252:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5252:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==RULE_STRING) ) {
                alt132=1;
            }
            else if ( (LA132_0==RULE_ID) ) {
                alt132=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 132, 0, input);

                throw nvae;
            }
            switch (alt132) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5252:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString11467); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5260:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString11493); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5275:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5277:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5278:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5278:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt133=5;
            switch ( input.LA(1) ) {
            case 98:
                {
                alt133=1;
                }
                break;
            case 99:
                {
                alt133=2;
                }
                break;
            case 100:
                {
                alt133=3;
                }
                break;
            case 101:
                {
                alt133=4;
                }
                break;
            case 102:
                {
                alt133=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 133, 0, input);

                throw nvae;
            }

            switch (alt133) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5278:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5278:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5278:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleLineStyle11552); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5284:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5284:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5284:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleLineStyle11569); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5290:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5290:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5290:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,100,FollowSets000.FOLLOW_100_in_ruleLineStyle11586); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5296:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5296:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5296:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,101,FollowSets000.FOLLOW_101_in_ruleLineStyle11603); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5302:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5302:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5302:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,102,FollowSets000.FOLLOW_102_in_ruleLineStyle11620); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5312:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5314:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5315:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5315:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt134=3;
            switch ( input.LA(1) ) {
            case 103:
                {
                alt134=1;
                }
                break;
            case 104:
                {
                alt134=2;
                }
                break;
            case 105:
                {
                alt134=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 134, 0, input);

                throw nvae;
            }

            switch (alt134) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5315:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5315:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5315:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,103,FollowSets000.FOLLOW_103_in_ruleVerticalAlignment11665); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5321:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5321:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5321:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,104,FollowSets000.FOLLOW_104_in_ruleVerticalAlignment11682); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5327:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5327:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5327:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,105,FollowSets000.FOLLOW_105_in_ruleVerticalAlignment11699); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5337:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5339:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5340:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5340:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt135=3;
            switch ( input.LA(1) ) {
            case 106:
                {
                alt135=1;
                }
                break;
            case 104:
                {
                alt135=2;
                }
                break;
            case 107:
                {
                alt135=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 135, 0, input);

                throw nvae;
            }

            switch (alt135) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5340:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5340:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5340:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,106,FollowSets000.FOLLOW_106_in_ruleHorizontalAlignment11744); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5346:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5346:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5346:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,104,FollowSets000.FOLLOW_104_in_ruleHorizontalAlignment11761); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5352:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5352:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5352:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,107,FollowSets000.FOLLOW_107_in_ruleHorizontalAlignment11778); 

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
        public static final BitSet FOLLOW_ruleKRenderingLibrary_in_entryRuleKRenderingLibrary75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingLibrary85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_ruleKRenderingLibrary131 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary143 = new BitSet(new long[]{0x000014C9CC40C000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary165 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingLibrary178 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary199 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingLibrary215 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_entryRuleKRendering251 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRendering261 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_ruleKRendering308 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_ruleKRendering335 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_ruleKRendering362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_ruleKRendering389 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_ruleKRendering416 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_ruleKRendering443 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_ruleKRendering470 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_ruleKRendering497 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_ruleKRendering524 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_ruleKRendering551 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_ruleKRendering578 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_ruleKRendering605 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData640 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacementData650 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData697 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData724 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData751 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData778 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData805 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle840 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle850 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_ruleKStyle897 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_ruleKStyle924 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle951 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle978 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle1005 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1032 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1059 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement1094 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement1104 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement1151 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_ruleKPlacement1178 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition1213 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition1223 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition1270 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition1297 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition1332 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition1342 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition1389 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition1416 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1451 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef1461 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef1498 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1510 = new BitSet(new long[]{0x0000000000090000L});
        public static final BitSet FOLLOW_16_in_ruleKRenderingRef1523 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1535 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1558 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingRef1571 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1594 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef1608 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_19_in_ruleKRenderingRef1622 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1645 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRenderingRef1658 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1679 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRenderingRef1694 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1706 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1727 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingRef1740 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1761 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1775 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1789 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse1825 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse1835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKEllipse1881 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse1893 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_21_in_ruleKEllipse1906 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKEllipse1918 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse1939 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_13_in_ruleKEllipse1952 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse1973 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_20_in_ruleKEllipse1990 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKEllipse2002 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse2023 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_24_in_ruleKEllipse2038 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKEllipse2050 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse2071 = new BitSet(new long[]{0x0000000002004000L});
        public static final BitSet FOLLOW_25_in_ruleKEllipse2086 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKEllipse2098 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2119 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2132 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2153 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2169 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle2205 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle2215 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_ruleKRectangle2261 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2273 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_21_in_ruleKRectangle2286 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRectangle2298 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2319 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2332 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2353 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_20_in_ruleKRectangle2370 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRectangle2382 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle2403 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_24_in_ruleKRectangle2418 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRectangle2430 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle2451 = new BitSet(new long[]{0x0000000002004000L});
        public static final BitSet FOLLOW_25_in_ruleKRectangle2466 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRectangle2478 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2499 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2512 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2533 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2549 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2585 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle2595 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleKRoundedRectangle2632 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle2644 = new BitSet(new long[]{0x0000000030000000L});
        public static final BitSet FOLLOW_28_in_ruleKRoundedRectangle2658 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2679 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_29_in_ruleKRoundedRectangle2691 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2712 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_29_in_ruleKRoundedRectangle2732 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2753 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleKRoundedRectangle2765 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2786 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedRectangle2801 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2813 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2834 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2847 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2868 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedRectangle2885 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2897 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2918 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_24_in_ruleKRoundedRectangle2933 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2945 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle2966 = new BitSet(new long[]{0x0000000002004000L});
        public static final BitSet FOLLOW_25_in_ruleKRoundedRectangle2981 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2993 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3014 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle3027 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3048 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3064 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3100 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl3110 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_ruleKPolyline_Impl3156 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3168 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_21_in_ruleKPolyline_Impl3181 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolyline_Impl3193 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3214 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3227 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3248 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_20_in_ruleKPolyline_Impl3265 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolyline_Impl3277 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3298 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_24_in_ruleKPolyline_Impl3313 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolyline_Impl3325 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3346 = new BitSet(new long[]{0x0000000002004000L});
        public static final BitSet FOLLOW_25_in_ruleKPolyline_Impl3361 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolyline_Impl3373 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3394 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3407 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3428 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3444 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon3480 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon3490 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_31_in_ruleKPolygon3536 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon3548 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_21_in_ruleKPolygon3561 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolygon3573 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon3594 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_13_in_ruleKPolygon3607 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon3628 = new BitSet(new long[]{0x0000000003106000L});
        public static final BitSet FOLLOW_20_in_ruleKPolygon3645 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolygon3657 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon3678 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_24_in_ruleKPolygon3693 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolygon3705 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon3726 = new BitSet(new long[]{0x0000000002004000L});
        public static final BitSet FOLLOW_25_in_ruleKPolygon3741 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolygon3753 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon3774 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKPolygon3787 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon3808 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon3824 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage3860 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage3870 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_32_in_ruleKImage3907 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage3919 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_33_in_ruleKImage3931 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage3952 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_34_in_ruleKImage3964 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage3985 = new BitSet(new long[]{0x0000000003314000L});
        public static final BitSet FOLLOW_16_in_ruleKImage3998 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4010 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4033 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_13_in_ruleKImage4046 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4069 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_18_in_ruleKImage4083 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_20_in_ruleKImage4098 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage4119 = new BitSet(new long[]{0x0000000003204000L});
        public static final BitSet FOLLOW_21_in_ruleKImage4134 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage4146 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4167 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKImage4180 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4201 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4215 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_25_in_ruleKImage4230 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage4242 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4263 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKImage4276 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4297 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4311 = new BitSet(new long[]{0x0000000001004000L});
        public static final BitSet FOLLOW_24_in_ruleKImage4326 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage4347 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4361 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc4397 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc4407 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_35_in_ruleKArc4453 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc4465 = new BitSet(new long[]{0x0000003003314000L});
        public static final BitSet FOLLOW_36_in_ruleKArc4478 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4499 = new BitSet(new long[]{0x0000002003314000L});
        public static final BitSet FOLLOW_37_in_ruleKArc4514 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4535 = new BitSet(new long[]{0x0000000003314000L});
        public static final BitSet FOLLOW_16_in_ruleKArc4550 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKArc4562 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc4585 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_13_in_ruleKArc4598 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc4621 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_18_in_ruleKArc4635 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_20_in_ruleKArc4650 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc4671 = new BitSet(new long[]{0x0000000003204000L});
        public static final BitSet FOLLOW_21_in_ruleKArc4686 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc4698 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc4719 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKArc4732 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc4753 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKArc4767 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_25_in_ruleKArc4782 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc4794 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc4815 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKArc4828 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc4849 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKArc4863 = new BitSet(new long[]{0x0000000001004000L});
        public static final BitSet FOLLOW_24_in_ruleKArc4878 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc4899 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKArc4913 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea4949 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea4959 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleKChildArea5005 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea5017 = new BitSet(new long[]{0x0000000000314000L});
        public static final BitSet FOLLOW_16_in_ruleKChildArea5030 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKChildArea5042 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea5065 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_13_in_ruleKChildArea5078 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea5101 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea5115 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKChildArea5130 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea5151 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKChildArea5166 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea5178 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5199 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKChildArea5212 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5233 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea5247 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea5261 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText5297 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText5307 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_39_in_ruleKText5350 = new BitSet(new long[]{0x0000010000000000L});
        public static final BitSet FOLLOW_40_in_ruleKText5375 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText5387 = new BitSet(new long[]{0x0000020003314000L});
        public static final BitSet FOLLOW_41_in_ruleKText5400 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText5421 = new BitSet(new long[]{0x0000000003314000L});
        public static final BitSet FOLLOW_16_in_ruleKText5436 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKText5448 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText5471 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_13_in_ruleKText5484 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText5507 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_18_in_ruleKText5521 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_20_in_ruleKText5536 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText5557 = new BitSet(new long[]{0x0000000003204000L});
        public static final BitSet FOLLOW_21_in_ruleKText5572 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText5584 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5605 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKText5618 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5639 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKText5653 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_25_in_ruleKText5668 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText5680 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5701 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKText5714 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5735 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKText5749 = new BitSet(new long[]{0x0000000001004000L});
        public static final BitSet FOLLOW_24_in_ruleKText5764 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText5785 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKText5799 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering5835 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering5845 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_42_in_ruleKCustomRendering5882 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering5894 = new BitSet(new long[]{0x0000080000000000L});
        public static final BitSet FOLLOW_43_in_ruleKCustomRendering5906 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering5927 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_33_in_ruleKCustomRendering5939 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering5960 = new BitSet(new long[]{0x0000000003314000L});
        public static final BitSet FOLLOW_16_in_ruleKCustomRendering5973 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering5985 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6008 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6021 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6044 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering6058 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_20_in_ruleKCustomRendering6073 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6094 = new BitSet(new long[]{0x0000000003204000L});
        public static final BitSet FOLLOW_21_in_ruleKCustomRendering6109 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6121 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6142 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6155 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6176 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6190 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_25_in_ruleKCustomRendering6205 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6217 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6238 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6251 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6272 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6286 = new BitSet(new long[]{0x0000000001004000L});
        public static final BitSet FOLLOW_24_in_ruleKCustomRendering6301 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering6322 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline6372 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline6382 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_44_in_ruleKSpline6428 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6440 = new BitSet(new long[]{0x0000000003314000L});
        public static final BitSet FOLLOW_16_in_ruleKSpline6453 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6465 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline6488 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_13_in_ruleKSpline6501 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline6524 = new BitSet(new long[]{0x0000000000042000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline6538 = new BitSet(new long[]{0x0000000003304000L});
        public static final BitSet FOLLOW_20_in_ruleKSpline6553 = new BitSet(new long[]{0x9084200000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline6574 = new BitSet(new long[]{0x0000000003204000L});
        public static final BitSet FOLLOW_21_in_ruleKSpline6589 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6601 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6622 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKSpline6635 = new BitSet(new long[]{0x0000000000000000L,0x0000000001EE1000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6656 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6670 = new BitSet(new long[]{0x0000000003004000L});
        public static final BitSet FOLLOW_25_in_ruleKSpline6685 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6697 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6718 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKSpline6731 = new BitSet(new long[]{0x000014C9CC408000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6752 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6766 = new BitSet(new long[]{0x0000000001004000L});
        public static final BitSet FOLLOW_24_in_ruleKSpline6781 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline6802 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6816 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6852 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6862 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_45_in_ruleKDecoratorPlacementData6905 = new BitSet(new long[]{0x0000400000000000L});
        public static final BitSet FOLLOW_46_in_ruleKDecoratorPlacementData6930 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData6942 = new BitSet(new long[]{0x0000800000000000L});
        public static final BitSet FOLLOW_47_in_ruleKDecoratorPlacementData6954 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6975 = new BitSet(new long[]{0x0003000000004000L});
        public static final BitSet FOLLOW_48_in_ruleKDecoratorPlacementData6988 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7009 = new BitSet(new long[]{0x0002000000004000L});
        public static final BitSet FOLLOW_49_in_ruleKDecoratorPlacementData7024 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7045 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDecoratorPlacementData7059 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7095 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData7105 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_50_in_ruleKGridPlacementData7142 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData7154 = new BitSet(new long[]{0x0008000000000000L});
        public static final BitSet FOLLOW_51_in_ruleKGridPlacementData7166 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7187 = new BitSet(new long[]{0x0010000000000000L});
        public static final BitSet FOLLOW_52_in_ruleKGridPlacementData7199 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7220 = new BitSet(new long[]{0x0020000000000000L});
        public static final BitSet FOLLOW_53_in_ruleKGridPlacementData7232 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7253 = new BitSet(new long[]{0x0040000000000000L});
        public static final BitSet FOLLOW_54_in_ruleKGridPlacementData7265 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7286 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKGridPlacementData7298 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData7334 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData7344 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_55_in_ruleKStackPlacementData7381 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData7393 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_56_in_ruleKStackPlacementData7405 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7426 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKStackPlacementData7438 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7459 = new BitSet(new long[]{0x0400000000000000L});
        public static final BitSet FOLLOW_58_in_ruleKStackPlacementData7471 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7492 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_59_in_ruleKStackPlacementData7504 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7525 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKStackPlacementData7537 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7573 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData7583 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_60_in_ruleKDirectPlacementData7620 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData7632 = new BitSet(new long[]{0x2000000000000000L});
        public static final BitSet FOLLOW_61_in_ruleKDirectPlacementData7644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7665 = new BitSet(new long[]{0x4000000000000000L});
        public static final BitSet FOLLOW_62_in_ruleKDirectPlacementData7677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7698 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDirectPlacementData7710 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData7746 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData7756 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKPolylinePlacementData7793 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_23_in_ruleKPolylinePlacementData7805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7826 = new BitSet(new long[]{0x0000000000002002L});
        public static final BitSet FOLLOW_13_in_ruleKPolylinePlacementData7839 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7860 = new BitSet(new long[]{0x0000000000002002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat7899 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat7910 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_64_in_ruleEFloat7949 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat7967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleEFloat7987 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat8002 = new BitSet(new long[]{0x0000000000000002L,0x000000000000000CL});
        public static final BitSet FOLLOW_66_in_ruleEFloat8022 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_67_in_ruleEFloat8041 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleEFloat8056 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat8073 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition8122 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition8132 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_68_in_ruleKPosition8169 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPosition8181 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
        public static final BitSet FOLLOW_69_in_ruleKPosition8193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000280L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition8214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_70_in_ruleKPosition8226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000C00L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition8247 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPosition8259 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8295 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition8305 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleKLeftPosition8351 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLeftPosition8363 = new BitSet(new long[]{0x0000200000004000L,0x0000000000000100L});
        public static final BitSet FOLLOW_72_in_ruleKLeftPosition8376 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8397 = new BitSet(new long[]{0x0000200000004000L});
        public static final BitSet FOLLOW_45_in_ruleKLeftPosition8412 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8433 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKLeftPosition8447 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8483 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition8493 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_73_in_ruleKRightPosition8539 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRightPosition8551 = new BitSet(new long[]{0x0000200000004000L,0x0000000000000100L});
        public static final BitSet FOLLOW_72_in_ruleKRightPosition8564 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8585 = new BitSet(new long[]{0x0000200000004000L});
        public static final BitSet FOLLOW_45_in_ruleKRightPosition8600 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8621 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRightPosition8635 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8671 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition8681 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_74_in_ruleKTopPosition8727 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKTopPosition8739 = new BitSet(new long[]{0x0000200000004000L,0x0000000000000100L});
        public static final BitSet FOLLOW_72_in_ruleKTopPosition8752 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8773 = new BitSet(new long[]{0x0000200000004000L});
        public static final BitSet FOLLOW_45_in_ruleKTopPosition8788 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8809 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKTopPosition8823 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8859 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition8869 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleKBottomPosition8915 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBottomPosition8927 = new BitSet(new long[]{0x0000200000004000L,0x0000000000000100L});
        public static final BitSet FOLLOW_72_in_ruleKBottomPosition8940 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8961 = new BitSet(new long[]{0x0000200000004000L});
        public static final BitSet FOLLOW_45_in_ruleKBottomPosition8976 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8997 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKBottomPosition9011 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor9047 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor9057 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKForegroundColor9103 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKForegroundColor9115 = new BitSet(new long[]{0x0000000000004000L,0x000000000001E000L});
        public static final BitSet FOLLOW_77_in_ruleKForegroundColor9133 = new BitSet(new long[]{0x0000000000004000L,0x000000000001C000L});
        public static final BitSet FOLLOW_78_in_ruleKForegroundColor9160 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9181 = new BitSet(new long[]{0x0000000000004000L,0x0000000000018000L});
        public static final BitSet FOLLOW_79_in_ruleKForegroundColor9196 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9217 = new BitSet(new long[]{0x0000000000004000L,0x0000000000010000L});
        public static final BitSet FOLLOW_80_in_ruleKForegroundColor9232 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9253 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKForegroundColor9267 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor9303 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor9313 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_81_in_ruleKBackgroundColor9359 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBackgroundColor9371 = new BitSet(new long[]{0x0000000000004000L,0x000000000001E000L});
        public static final BitSet FOLLOW_77_in_ruleKBackgroundColor9389 = new BitSet(new long[]{0x0000000000004000L,0x000000000001C000L});
        public static final BitSet FOLLOW_78_in_ruleKBackgroundColor9416 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9437 = new BitSet(new long[]{0x0000000000004000L,0x0000000000018000L});
        public static final BitSet FOLLOW_79_in_ruleKBackgroundColor9452 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9473 = new BitSet(new long[]{0x0000000000004000L,0x0000000000010000L});
        public static final BitSet FOLLOW_80_in_ruleKBackgroundColor9488 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9509 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKBackgroundColor9523 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9559 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth9569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleKLineWidth9606 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth9627 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleKLineWidth9645 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility9695 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility9705 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility9752 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility9779 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility9814 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundVisibility9824 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_83_in_ruleKForegroundVisibility9870 = new BitSet(new long[]{0x0000000000000002L,0x0000000000102000L});
        public static final BitSet FOLLOW_84_in_ruleKForegroundVisibility9888 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleKForegroundVisibility9920 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility9970 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundVisibility9980 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_85_in_ruleKBackgroundVisibility10026 = new BitSet(new long[]{0x0000000000000002L,0x0000000000102000L});
        public static final BitSet FOLLOW_84_in_ruleKBackgroundVisibility10044 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleKBackgroundVisibility10076 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle10126 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle10136 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_86_in_ruleKLineStyle10182 = new BitSet(new long[]{0x0000000000000002L,0x0000007C00002000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle10203 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleKLineStyle10222 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10272 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment10282 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleKVerticalAlignment10319 = new BitSet(new long[]{0x0000000000000000L,0x0000038000000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10340 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleKVerticalAlignment10358 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10408 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment10418 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_88_in_ruleKHorizontalAlignment10455 = new BitSet(new long[]{0x0000000000000000L,0x00000D0000000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10476 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleKHorizontalAlignment10494 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement10543 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement10553 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_89_in_ruleKGridPlacement10599 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000001L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement10620 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement10656 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement10666 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_90_in_ruleKStackPlacement10712 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt10749 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt10760 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_64_in_ruleEInt10799 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt10816 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets10863 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets10873 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_91_in_ruleKInsets10919 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets10931 = new BitSet(new long[]{0x0000000000004000L,0x00000000F0000000L});
        public static final BitSet FOLLOW_92_in_ruleKInsets10944 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets10965 = new BitSet(new long[]{0x0000000000004000L,0x00000000E0000000L});
        public static final BitSet FOLLOW_93_in_ruleKInsets10980 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11001 = new BitSet(new long[]{0x0000000000004000L,0x00000000C0000000L});
        public static final BitSet FOLLOW_94_in_ruleKInsets11016 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11037 = new BitSet(new long[]{0x0000000000004000L,0x0000000080000000L});
        public static final BitSet FOLLOW_95_in_ruleKInsets11052 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11073 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKInsets11087 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint11125 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint11135 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleKPoint11181 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
        public static final BitSet FOLLOW_69_in_ruleKPoint11194 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_70_in_ruleKPoint11229 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000003L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11250 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry11287 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry11297 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry11343 = new BitSet(new long[]{0x0000000000000002L,0x0000000200000000L});
        public static final BitSet FOLLOW_97_in_rulePersistentEntry11356 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry11377 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString11416 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString11427 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString11467 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString11493 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleLineStyle11552 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_99_in_ruleLineStyle11569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_100_in_ruleLineStyle11586 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_101_in_ruleLineStyle11603 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_102_in_ruleLineStyle11620 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_103_in_ruleVerticalAlignment11665 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_104_in_ruleVerticalAlignment11682 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_105_in_ruleVerticalAlignment11699 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_106_in_ruleHorizontalAlignment11744 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_104_in_ruleHorizontalAlignment11761 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_107_in_ruleHorizontalAlignment11778 = new BitSet(new long[]{0x0000000000000002L});
    }


}