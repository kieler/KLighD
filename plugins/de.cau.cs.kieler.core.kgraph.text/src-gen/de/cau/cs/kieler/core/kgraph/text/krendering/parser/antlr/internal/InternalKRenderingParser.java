package de.cau.cs.kieler.core.kgraph.text.krendering.parser.antlr.internal; 

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
import de.cau.cs.kieler.core.kgraph.text.krendering.services.KRenderingGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKRenderingParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'RenderingLibrary'", "'{'", "','", "'}'", "'RenderingRef'", "'placementData'", "'styles'", "'Ellipse'", "':'", "'childPlacement'", "'children'", "'Rectangle'", "'RoundedRectangle'", "'Polyline'", "'RoundedBendsPolyline'", "'Polygon'", "'Image'", "'-'", "'Arc'", "'ChildArea'", "'Text'", "'clip'", "'mapProperties'", "'CustomRendering'", "'className'", "'bundleName'", "'Spline'", "'DecoratorPlacementData'", "'relative'", "'location'", "'xOffset'", "'yOffset'", "'width'", "'height'", "'GridPlacementData'", "'widthHint'", "'heightHint'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'StackPlacementData'", "'DirectPlacementData'", "'topLeft'", "'bottomRight'", "'PolylinePlacementData'", "'points'", "'detailedPlacementData'", "'/'", "'left'", "'right'", "'top'", "'bottom'", "'foregroundColor'", "'!'", "'backgroundColor'", "'lineWidth'", "'foregroundVisibility'", "'backgroundVisibility'", "'lineStyle'", "'rotation'", "'bold'", "'italic'", "'font'", "'fontSize'", "'verticalAlignment'", "'horizontalAlignment'", "'gridPlacement'", "'stackPlacement'", "'.'", "'E'", "'e'", "'true'", "'false'", "'KInsets'", "'KPoint'", "'x'", "'y'", "'='", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
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
    public static final int T__59=59;
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
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g"; }



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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:68:1: entryRuleKRenderingLibrary returns [EObject current=null] : iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF ;
    public final EObject entryRuleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingLibrary = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:69:2: (iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:70:2: iv_ruleKRenderingLibrary= ruleKRenderingLibrary EOF
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:77:1: ruleKRenderingLibrary returns [EObject current=null] : ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:80:28: ( ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:81:2: () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:81:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:82:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingLibraryAccess().getKRenderingLibraryAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleKRenderingLibrary131); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingLibraryAccess().getRenderingLibraryKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingLibrary143); 

                	newLeafNode(otherlv_2, grammarAccess.getKRenderingLibraryAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:95:1: ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15||LA3_0==18||(LA3_0>=22 && LA3_0<=27)||(LA3_0>=29 && LA3_0<=31)||LA3_0==34||LA3_0==37) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:95:2: ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )*
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:95:2: ( (lv_renderings_3_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:96:1: (lv_renderings_3_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:96:1: (lv_renderings_3_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:97:3: lv_renderings_3_0= ruleKRendering
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:113:2: ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==13||LA2_0==15||LA2_0==18||(LA2_0>=22 && LA2_0<=27)||(LA2_0>=29 && LA2_0<=31)||LA2_0==34||LA2_0==37) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:113:3: (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:113:3: (otherlv_4= ',' )?
                    	    int alt1=2;
                    	    int LA1_0 = input.LA(1);

                    	    if ( (LA1_0==13) ) {
                    	        alt1=1;
                    	    }
                    	    switch (alt1) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:113:5: otherlv_4= ','
                    	            {
                    	            otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingLibrary179); 

                    	                	newLeafNode(otherlv_4, grammarAccess.getKRenderingLibraryAccess().getCommaKeyword_3_1_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:117:3: ( (lv_renderings_5_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:118:1: (lv_renderings_5_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:118:1: (lv_renderings_5_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:119:3: lv_renderings_5_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRenderingLibrary202);
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
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingLibrary218); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:147:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:148:2: (iv_ruleKRendering= ruleKRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:149:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_entryRuleKRendering254);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRendering264); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:156:1: ruleKRendering returns [EObject current=null] : (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline | this_KRoundedBendsPolyline_12= ruleKRoundedBendsPolyline ) ;
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

        EObject this_KRoundedBendsPolyline_12 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:159:28: ( (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline | this_KRoundedBendsPolyline_12= ruleKRoundedBendsPolyline ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:160:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline | this_KRoundedBendsPolyline_12= ruleKRoundedBendsPolyline )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:160:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline | this_KRoundedBendsPolyline_12= ruleKRoundedBendsPolyline )
            int alt4=13;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt4=1;
                }
                break;
            case 22:
                {
                alt4=2;
                }
                break;
            case 23:
                {
                alt4=3;
                }
                break;
            case 24:
                {
                alt4=4;
                }
                break;
            case 26:
                {
                alt4=5;
                }
                break;
            case 27:
                {
                alt4=6;
                }
                break;
            case 29:
                {
                alt4=7;
                }
                break;
            case 15:
                {
                alt4=8;
                }
                break;
            case 30:
                {
                alt4=9;
                }
                break;
            case 31:
                {
                alt4=10;
                }
                break;
            case 34:
                {
                alt4=11;
                }
                break;
            case 37:
                {
                alt4=12;
                }
                break;
            case 25:
                {
                alt4=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:161:5: this_KEllipse_0= ruleKEllipse
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKEllipseParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_ruleKRendering311);
                    this_KEllipse_0=ruleKEllipse();

                    state._fsp--;

                     
                            current = this_KEllipse_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:171:5: this_KRectangle_1= ruleKRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRectangleParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_ruleKRendering338);
                    this_KRectangle_1=ruleKRectangle();

                    state._fsp--;

                     
                            current = this_KRectangle_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:181:5: this_KRoundedRectangle_2= ruleKRoundedRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRoundedRectangleParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_ruleKRendering365);
                    this_KRoundedRectangle_2=ruleKRoundedRectangle();

                    state._fsp--;

                     
                            current = this_KRoundedRectangle_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:191:5: this_KPolyline_Impl_3= ruleKPolyline_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolyline_ImplParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_ruleKRendering392);
                    this_KPolyline_Impl_3=ruleKPolyline_Impl();

                    state._fsp--;

                     
                            current = this_KPolyline_Impl_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:201:5: this_KPolygon_4= ruleKPolygon
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolygonParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_ruleKRendering419);
                    this_KPolygon_4=ruleKPolygon();

                    state._fsp--;

                     
                            current = this_KPolygon_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:211:5: this_KImage_5= ruleKImage
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKImageParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKImage_in_ruleKRendering446);
                    this_KImage_5=ruleKImage();

                    state._fsp--;

                     
                            current = this_KImage_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:221:5: this_KArc_6= ruleKArc
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKArcParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKArc_in_ruleKRendering473);
                    this_KArc_6=ruleKArc();

                    state._fsp--;

                     
                            current = this_KArc_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:231:5: this_KRenderingRef_7= ruleKRenderingRef
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRenderingRefParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_ruleKRendering500);
                    this_KRenderingRef_7=ruleKRenderingRef();

                    state._fsp--;

                     
                            current = this_KRenderingRef_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:241:5: this_KChildArea_8= ruleKChildArea
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKChildAreaParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_ruleKRendering527);
                    this_KChildArea_8=ruleKChildArea();

                    state._fsp--;

                     
                            current = this_KChildArea_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:251:5: this_KText_9= ruleKText
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKTextParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKText_in_ruleKRendering554);
                    this_KText_9=ruleKText();

                    state._fsp--;

                     
                            current = this_KText_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:261:5: this_KCustomRendering_10= ruleKCustomRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKCustomRenderingParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_ruleKRendering581);
                    this_KCustomRendering_10=ruleKCustomRendering();

                    state._fsp--;

                     
                            current = this_KCustomRendering_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:271:5: this_KSpline_11= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSplineParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_ruleKRendering608);
                    this_KSpline_11=ruleKSpline();

                    state._fsp--;

                     
                            current = this_KSpline_11; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 13 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:281:5: this_KRoundedBendsPolyline_12= ruleKRoundedBendsPolyline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRoundedBendsPolylineParserRuleCall_12()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRoundedBendsPolyline_in_ruleKRendering635);
                    this_KRoundedBendsPolyline_12=ruleKRoundedBendsPolyline();

                    state._fsp--;

                     
                            current = this_KRoundedBendsPolyline_12; 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:297:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:298:2: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:299:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData670);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacementData680); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:306:1: ruleKPlacementData returns [EObject current=null] : (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KDecoratorPlacementData_0 = null;

        EObject this_KGridPlacementData_1 = null;

        EObject this_KStackPlacementData_2 = null;

        EObject this_KDirectPlacementData_3 = null;

        EObject this_KPolylinePlacementData_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:309:28: ( (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:310:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:310:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            int alt5=5;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt5=1;
                }
                break;
            case 45:
                {
                alt5=2;
                }
                break;
            case 52:
                {
                alt5=3;
                }
                break;
            case 53:
                {
                alt5=4;
                }
                break;
            case 56:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:311:5: this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData727);
                    this_KDecoratorPlacementData_0=ruleKDecoratorPlacementData();

                    state._fsp--;

                     
                            current = this_KDecoratorPlacementData_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:321:5: this_KGridPlacementData_1= ruleKGridPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData754);
                    this_KGridPlacementData_1=ruleKGridPlacementData();

                    state._fsp--;

                     
                            current = this_KGridPlacementData_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:331:5: this_KStackPlacementData_2= ruleKStackPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKStackPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData781);
                    this_KStackPlacementData_2=ruleKStackPlacementData();

                    state._fsp--;

                     
                            current = this_KStackPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:341:5: this_KDirectPlacementData_3= ruleKDirectPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDirectPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData808);
                    this_KDirectPlacementData_3=ruleKDirectPlacementData();

                    state._fsp--;

                     
                            current = this_KDirectPlacementData_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:351:5: this_KPolylinePlacementData_4= ruleKPolylinePlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPolylinePlacementDataParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData835);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:367:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:368:2: (iv_ruleKStyle= ruleKStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:369:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_entryRuleKStyle870);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle880); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:376:1: ruleKStyle returns [EObject current=null] : (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KRotation_5= ruleKRotation | this_KFontBold_6= ruleKFontBold | this_KFontItalic_7= ruleKFontItalic | this_KFontName_8= ruleKFontName | this_KFontSize_9= ruleKFontSize | this_KVerticalAlignment_10= ruleKVerticalAlignment | this_KHorizontalAlignment_11= ruleKHorizontalAlignment ) ;
    public final EObject ruleKStyle() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundColor_0 = null;

        EObject this_KBackgroundColor_1 = null;

        EObject this_KLineWidth_2 = null;

        EObject this_KVisibility_3 = null;

        EObject this_KLineStyle_4 = null;

        EObject this_KRotation_5 = null;

        EObject this_KFontBold_6 = null;

        EObject this_KFontItalic_7 = null;

        EObject this_KFontName_8 = null;

        EObject this_KFontSize_9 = null;

        EObject this_KVerticalAlignment_10 = null;

        EObject this_KHorizontalAlignment_11 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:379:28: ( (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KRotation_5= ruleKRotation | this_KFontBold_6= ruleKFontBold | this_KFontItalic_7= ruleKFontItalic | this_KFontName_8= ruleKFontName | this_KFontSize_9= ruleKFontSize | this_KVerticalAlignment_10= ruleKVerticalAlignment | this_KHorizontalAlignment_11= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:380:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KRotation_5= ruleKRotation | this_KFontBold_6= ruleKFontBold | this_KFontItalic_7= ruleKFontItalic | this_KFontName_8= ruleKFontName | this_KFontSize_9= ruleKFontSize | this_KVerticalAlignment_10= ruleKVerticalAlignment | this_KHorizontalAlignment_11= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:380:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KRotation_5= ruleKRotation | this_KFontBold_6= ruleKFontBold | this_KFontItalic_7= ruleKFontItalic | this_KFontName_8= ruleKFontName | this_KFontSize_9= ruleKFontSize | this_KVerticalAlignment_10= ruleKVerticalAlignment | this_KHorizontalAlignment_11= ruleKHorizontalAlignment )
            int alt6=12;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt6=1;
                }
                break;
            case 66:
                {
                alt6=2;
                }
                break;
            case 67:
                {
                alt6=3;
                }
                break;
            case 68:
            case 69:
                {
                alt6=4;
                }
                break;
            case 70:
                {
                alt6=5;
                }
                break;
            case 71:
                {
                alt6=6;
                }
                break;
            case 72:
                {
                alt6=7;
                }
                break;
            case 73:
                {
                alt6=8;
                }
                break;
            case 74:
                {
                alt6=9;
                }
                break;
            case 75:
                {
                alt6=10;
                }
                break;
            case 76:
                {
                alt6=11;
                }
                break;
            case 77:
                {
                alt6=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:381:5: this_KForegroundColor_0= ruleKForegroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundColorParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_ruleKStyle927);
                    this_KForegroundColor_0=ruleKForegroundColor();

                    state._fsp--;

                     
                            current = this_KForegroundColor_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:391:5: this_KBackgroundColor_1= ruleKBackgroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKBackgroundColorParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_ruleKStyle954);
                    this_KBackgroundColor_1=ruleKBackgroundColor();

                    state._fsp--;

                     
                            current = this_KBackgroundColor_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:401:5: this_KLineWidth_2= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle981);
                    this_KLineWidth_2=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:411:5: this_KVisibility_3= ruleKVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVisibilityParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_ruleKStyle1008);
                    this_KVisibility_3=ruleKVisibility();

                    state._fsp--;

                     
                            current = this_KVisibility_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:421:5: this_KLineStyle_4= ruleKLineStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_ruleKStyle1035);
                    this_KLineStyle_4=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:431:5: this_KRotation_5= ruleKRotation
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKRotationParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRotation_in_ruleKStyle1062);
                    this_KRotation_5=ruleKRotation();

                    state._fsp--;

                     
                            current = this_KRotation_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:441:5: this_KFontBold_6= ruleKFontBold
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontBoldParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_ruleKStyle1089);
                    this_KFontBold_6=ruleKFontBold();

                    state._fsp--;

                     
                            current = this_KFontBold_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:451:5: this_KFontItalic_7= ruleKFontItalic
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontItalicParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_ruleKStyle1116);
                    this_KFontItalic_7=ruleKFontItalic();

                    state._fsp--;

                     
                            current = this_KFontItalic_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:461:5: this_KFontName_8= ruleKFontName
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontNameParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_ruleKStyle1143);
                    this_KFontName_8=ruleKFontName();

                    state._fsp--;

                     
                            current = this_KFontName_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:471:5: this_KFontSize_9= ruleKFontSize
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontSizeParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_ruleKStyle1170);
                    this_KFontSize_9=ruleKFontSize();

                    state._fsp--;

                     
                            current = this_KFontSize_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:481:5: this_KVerticalAlignment_10= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1197);
                    this_KVerticalAlignment_10=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:491:5: this_KHorizontalAlignment_11= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1224);
                    this_KHorizontalAlignment_11=ruleKHorizontalAlignment();

                    state._fsp--;

                     
                            current = this_KHorizontalAlignment_11; 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:507:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:508:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:509:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_entryRuleKPlacement1259);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacement1269); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:516:1: ruleKPlacement returns [EObject current=null] : (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;

        EObject this_KStackPlacement_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:519:28: ( (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:520:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:520:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==78) ) {
                alt7=1;
            }
            else if ( (LA7_0==79) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:521:5: this_KGridPlacement_0= ruleKGridPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement1316);
                    this_KGridPlacement_0=ruleKGridPlacement();

                    state._fsp--;

                     
                            current = this_KGridPlacement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:531:5: this_KStackPlacement_1= ruleKStackPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKStackPlacementParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_ruleKPlacement1343);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:547:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:548:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:549:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition1378);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition1388); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:556:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:559:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:560:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:560:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==60) ) {
                alt8=1;
            }
            else if ( (LA8_0==61) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:561:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition1435);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:571:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition1462);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:587:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:588:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:589:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition1497);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition1507); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:596:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:599:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:600:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:600:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==62) ) {
                alt9=1;
            }
            else if ( (LA9_0==63) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:601:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition1554);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:611:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition1581);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:627:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:628:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:629:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1616);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef1626); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:636:1: ruleKRenderingRef returns [EObject current=null] : ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:639:28: ( ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:2: () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:641:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingRefAccess().getKRenderingRefAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef1672); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getRenderingRefKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:650:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:651:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:651:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:652:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1695);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:665:2: (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==12) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:665:4: otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1708); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:669:1: (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==16) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:669:3: otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) )
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRenderingRef1721); 

                                	newLeafNode(otherlv_4, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:673:1: ( (lv_placementData_5_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:674:1: (lv_placementData_5_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:674:1: (lv_placementData_5_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:675:3: lv_placementData_5_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1742);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:691:4: (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==17) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:691:6: otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}'
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1757); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRenderingRefAccess().getStylesKeyword_3_2_0());
                                
                            otherlv_7=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1769); 

                                	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_2_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:699:1: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:700:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:700:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:701:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1790);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:717:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop12:
                            do {
                                int alt12=2;
                                int LA12_0 = input.LA(1);

                                if ( (LA12_0==13||LA12_0==64||(LA12_0>=66 && LA12_0<=77)) ) {
                                    alt12=1;
                                }


                                switch (alt12) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:717:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:717:3: (otherlv_9= ',' )?
                            	    int alt11=2;
                            	    int LA11_0 = input.LA(1);

                            	    if ( (LA11_0==13) ) {
                            	        alt11=1;
                            	    }
                            	    switch (alt11) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:717:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingRef1804); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKRenderingRefAccess().getCommaKeyword_3_2_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:721:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:722:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:722:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:723:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_2_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1827);
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
                            	    break loop12;
                                }
                            } while (true);

                            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1841); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_3_2_4());
                                

                            }
                            break;

                    }

                    otherlv_12=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1855); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:755:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:756:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:757:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse1893);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse1903); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:764:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:767:28: ( ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:768:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:768:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:768:2: () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:768:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:769:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse1949); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getEllipseKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:778:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==12) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:778:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse1962); 

                        	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:782:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==17) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:782:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse1975); 

                                	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:786:1: (otherlv_4= ':' )?
                            int alt15=2;
                            int LA15_0 = input.LA(1);

                            if ( (LA15_0==19) ) {
                                alt15=1;
                            }
                            switch (alt15) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:786:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse1988); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:790:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:791:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:791:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:792:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse2011);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:808:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop17:
                            do {
                                int alt17=2;
                                int LA17_0 = input.LA(1);

                                if ( (LA17_0==13||LA17_0==64||(LA17_0>=66 && LA17_0<=77)) ) {
                                    alt17=1;
                                }


                                switch (alt17) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:808:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:808:3: (otherlv_6= ',' )?
                            	    int alt16=2;
                            	    int LA16_0 = input.LA(1);

                            	    if ( (LA16_0==13) ) {
                            	        alt16=1;
                            	    }
                            	    switch (alt16) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:808:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2025); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:812:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:813:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:813:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:814:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse2048);
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
                            	    break loop17;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:830:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==16) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:830:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse2065); 

                                	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:834:1: (otherlv_9= ':' )?
                            int alt19=2;
                            int LA19_0 = input.LA(1);

                            if ( (LA19_0==19) ) {
                                alt19=1;
                            }
                            switch (alt19) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:834:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse2078); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:838:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:839:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:839:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:840:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse2101);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:856:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==20) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:856:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKEllipse2116); 

                                	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:860:1: (otherlv_12= ':' )?
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0==19) ) {
                                alt21=1;
                            }
                            switch (alt21) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:860:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse2129); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:864:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:865:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:865:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:866:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse2152);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:882:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==21) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:882:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKEllipse2167); 

                                	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:886:1: (otherlv_15= ':' )?
                            int alt23=2;
                            int LA23_0 = input.LA(1);

                            if ( (LA23_0==19) ) {
                                alt23=1;
                            }
                            switch (alt23) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:886:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse2180); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKEllipseAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:890:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:891:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:891:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:892:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2203);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:908:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop25:
                            do {
                                int alt25=2;
                                int LA25_0 = input.LA(1);

                                if ( (LA25_0==13||LA25_0==15||LA25_0==18||(LA25_0>=22 && LA25_0<=27)||(LA25_0>=29 && LA25_0<=31)||LA25_0==34||LA25_0==37) ) {
                                    alt25=1;
                                }


                                switch (alt25) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:908:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:908:3: (otherlv_17= ',' )?
                            	    int alt24=2;
                            	    int LA24_0 = input.LA(1);

                            	    if ( (LA24_0==13) ) {
                            	        alt24=1;
                            	    }
                            	    switch (alt24) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:908:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2217); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:912:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:913:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:913:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:914:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2240);
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
                            	    break loop25;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2256); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:942:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:943:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:944:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle2294);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle2304); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:951:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:954:28: ( ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:955:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:955:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:955:2: () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:955:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:956:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKRectangle2350); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:965:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==12) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:965:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2363); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:969:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==17) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:969:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2376); 

                                	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:973:1: (otherlv_4= ':' )?
                            int alt28=2;
                            int LA28_0 = input.LA(1);

                            if ( (LA28_0==19) ) {
                                alt28=1;
                            }
                            switch (alt28) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:973:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2389); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:977:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:978:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:978:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:979:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2412);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:995:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop30:
                            do {
                                int alt30=2;
                                int LA30_0 = input.LA(1);

                                if ( (LA30_0==13||LA30_0==64||(LA30_0>=66 && LA30_0<=77)) ) {
                                    alt30=1;
                                }


                                switch (alt30) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:995:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:995:3: (otherlv_6= ',' )?
                            	    int alt29=2;
                            	    int LA29_0 = input.LA(1);

                            	    if ( (LA29_0==13) ) {
                            	        alt29=1;
                            	    }
                            	    switch (alt29) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:995:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2426); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:999:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1000:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1000:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1001:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2449);
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
                            	    break loop30;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1017:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==16) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1017:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle2466); 

                                	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1021:1: (otherlv_9= ':' )?
                            int alt32=2;
                            int LA32_0 = input.LA(1);

                            if ( (LA32_0==19) ) {
                                alt32=1;
                            }
                            switch (alt32) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1021:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2479); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1025:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1026:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1026:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1027:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle2502);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1043:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==20) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1043:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRectangle2517); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1047:1: (otherlv_12= ':' )?
                            int alt34=2;
                            int LA34_0 = input.LA(1);

                            if ( (LA34_0==19) ) {
                                alt34=1;
                            }
                            switch (alt34) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1047:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2530); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1051:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1052:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1052:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1053:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle2553);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1069:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==21) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1069:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRectangle2568); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1073:1: (otherlv_15= ':' )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0==19) ) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1073:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2581); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRectangleAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1077:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1078:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1078:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1079:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2604);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1095:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop38:
                            do {
                                int alt38=2;
                                int LA38_0 = input.LA(1);

                                if ( (LA38_0==13||LA38_0==15||LA38_0==18||(LA38_0>=22 && LA38_0<=27)||(LA38_0>=29 && LA38_0<=31)||LA38_0==34||LA38_0==37) ) {
                                    alt38=1;
                                }


                                switch (alt38) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1095:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1095:3: (otherlv_17= ',' )?
                            	    int alt37=2;
                            	    int LA37_0 = input.LA(1);

                            	    if ( (LA37_0==13) ) {
                            	        alt37=1;
                            	    }
                            	    switch (alt37) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1095:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2618); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1099:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1100:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1100:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1101:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2641);
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
                            	    break loop38;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2657); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1129:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1130:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1131:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2695);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle2705); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1138:1: ruleKRoundedRectangle returns [EObject current=null] : ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1141:28: ( ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1142:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1142:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1142:2: () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1142:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1143:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2751); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getRoundedRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1152:1: ( (lv_cornerWidth_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1153:1: (lv_cornerWidth_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1153:1: (lv_cornerWidth_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1154:3: lv_cornerWidth_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2772);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1170:2: (otherlv_3= ',' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==13) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1170:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2785); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1174:3: ( (lv_cornerHeight_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1175:1: (lv_cornerHeight_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1175:1: (lv_cornerHeight_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1176:3: lv_cornerHeight_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2808);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1192:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==12) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1192:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle2821); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1196:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==17) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1196:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2834); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1200:1: (otherlv_7= ':' )?
                            int alt42=2;
                            int LA42_0 = input.LA(1);

                            if ( (LA42_0==19) ) {
                                alt42=1;
                            }
                            switch (alt42) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1200:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle2847); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1204:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1205:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1205:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1206:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2870);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1222:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop44:
                            do {
                                int alt44=2;
                                int LA44_0 = input.LA(1);

                                if ( (LA44_0==13||LA44_0==64||(LA44_0>=66 && LA44_0<=77)) ) {
                                    alt44=1;
                                }


                                switch (alt44) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1222:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1222:3: (otherlv_9= ',' )?
                            	    int alt43=2;
                            	    int LA43_0 = input.LA(1);

                            	    if ( (LA43_0==13) ) {
                            	        alt43=1;
                            	    }
                            	    switch (alt43) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1222:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2884); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1226:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1227:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1227:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1228:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2907);
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
                            	    break loop44;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1244:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==16) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1244:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle2924); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1248:1: (otherlv_12= ':' )?
                            int alt46=2;
                            int LA46_0 = input.LA(1);

                            if ( (LA46_0==19) ) {
                                alt46=1;
                            }
                            switch (alt46) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1248:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle2937); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1252:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1253:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1253:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1254:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2960);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1270:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==20) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1270:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedRectangle2975); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_5_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1274:1: (otherlv_15= ':' )?
                            int alt48=2;
                            int LA48_0 = input.LA(1);

                            if ( (LA48_0==19) ) {
                                alt48=1;
                            }
                            switch (alt48) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1274:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle2988); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1278:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1279:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1279:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1280:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle3011);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1296:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==21) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1296:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedRectangle3026); 

                                	newLeafNode(otherlv_17, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1300:1: (otherlv_18= ':' )?
                            int alt50=2;
                            int LA50_0 = input.LA(1);

                            if ( (LA50_0==19) ) {
                                alt50=1;
                            }
                            switch (alt50) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1300:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle3039); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1304:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1305:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1305:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1306:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3062);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1322:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop52:
                            do {
                                int alt52=2;
                                int LA52_0 = input.LA(1);

                                if ( (LA52_0==13||LA52_0==15||LA52_0==18||(LA52_0>=22 && LA52_0<=27)||(LA52_0>=29 && LA52_0<=31)||LA52_0==34||LA52_0==37) ) {
                                    alt52=1;
                                }


                                switch (alt52) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1322:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1322:3: (otherlv_20= ',' )?
                            	    int alt51=2;
                            	    int LA51_0 = input.LA(1);

                            	    if ( (LA51_0==13) ) {
                            	        alt51=1;
                            	    }
                            	    switch (alt51) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1322:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle3076); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1326:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1327:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1327:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1328:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3099);
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
                            	    break loop52;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3115); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1356:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1357:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1358:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3153);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl3163); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1365:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1368:28: ( ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1369:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1369:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1369:2: () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1369:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1370:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKPolyline_Impl3209); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1379:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==12) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1379:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3222); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1383:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==17) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1383:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3235); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1387:1: (otherlv_4= ':' )?
                            int alt55=2;
                            int LA55_0 = input.LA(1);

                            if ( (LA55_0==19) ) {
                                alt55=1;
                            }
                            switch (alt55) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1387:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3248); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1391:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1392:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1392:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1393:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3271);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1409:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==13||LA57_0==64||(LA57_0>=66 && LA57_0<=77)) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1409:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1409:3: (otherlv_6= ',' )?
                            	    int alt56=2;
                            	    int LA56_0 = input.LA(1);

                            	    if ( (LA56_0==13) ) {
                            	        alt56=1;
                            	    }
                            	    switch (alt56) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1409:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3285); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1413:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1414:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1414:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1415:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3308);
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
                            	    break loop57;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1431:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==16) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1431:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl3325); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1435:1: (otherlv_9= ':' )?
                            int alt59=2;
                            int LA59_0 = input.LA(1);

                            if ( (LA59_0==19) ) {
                                alt59=1;
                            }
                            switch (alt59) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1435:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3338); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1439:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1440:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1440:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1441:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3361);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1457:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==20) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1457:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolyline_Impl3376); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1461:1: (otherlv_12= ':' )?
                            int alt61=2;
                            int LA61_0 = input.LA(1);

                            if ( (LA61_0==19) ) {
                                alt61=1;
                            }
                            switch (alt61) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1461:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3389); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1465:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1466:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1466:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1467:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3412);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1483:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==21) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1483:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolyline_Impl3427); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1487:1: (otherlv_15= ':' )?
                            int alt63=2;
                            int LA63_0 = input.LA(1);

                            if ( (LA63_0==19) ) {
                                alt63=1;
                            }
                            switch (alt63) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1487:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3440); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1491:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1492:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1492:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1493:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3463);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1509:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop65:
                            do {
                                int alt65=2;
                                int LA65_0 = input.LA(1);

                                if ( (LA65_0==13||LA65_0==15||LA65_0==18||(LA65_0>=22 && LA65_0<=27)||(LA65_0>=29 && LA65_0<=31)||LA65_0==34||LA65_0==37) ) {
                                    alt65=1;
                                }


                                switch (alt65) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1509:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1509:3: (otherlv_17= ',' )?
                            	    int alt64=2;
                            	    int LA64_0 = input.LA(1);

                            	    if ( (LA64_0==13) ) {
                            	        alt64=1;
                            	    }
                            	    switch (alt64) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1509:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3477); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1513:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1514:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1514:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1515:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3500);
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
                            	    break loop65;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3516); 

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


    // $ANTLR start "entryRuleKRoundedBendsPolyline"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1543:1: entryRuleKRoundedBendsPolyline returns [EObject current=null] : iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF ;
    public final EObject entryRuleKRoundedBendsPolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedBendsPolyline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1544:2: (iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1545:2: iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF
            {
             newCompositeNode(grammarAccess.getKRoundedBendsPolylineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedBendsPolyline_in_entryRuleKRoundedBendsPolyline3554);
            iv_ruleKRoundedBendsPolyline=ruleKRoundedBendsPolyline();

            state._fsp--;

             current =iv_ruleKRoundedBendsPolyline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedBendsPolyline3564); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1552:1: ruleKRoundedBendsPolyline returns [EObject current=null] : ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}' )? ) ;
    public final EObject ruleKRoundedBendsPolyline() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        AntlrDatatypeRuleToken lv_bendRadius_2_0 = null;

        EObject lv_styles_6_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_placementData_11_0 = null;

        EObject lv_childPlacement_14_0 = null;

        EObject lv_children_17_0 = null;

        EObject lv_children_19_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1555:28: ( ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1556:1: ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1556:1: ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1556:2: () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1556:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1557:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedBendsPolylineAccess().getKRoundedBendsPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKRoundedBendsPolyline3610); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedBendsPolylineAccess().getRoundedBendsPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1566:1: ( (lv_bendRadius_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1567:1: (lv_bendRadius_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1567:1: (lv_bendRadius_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1568:3: lv_bendRadius_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getBendRadiusEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedBendsPolyline3631);
            lv_bendRadius_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
            	        }
                   		set(
                   			current, 
                   			"bendRadius",
                    		lv_bendRadius_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1584:2: (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}' )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==12) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1584:4: otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedBendsPolyline3644); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRoundedBendsPolylineAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1588:1: (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==17) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1588:3: otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            {
                            otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3657); 

                                	newLeafNode(otherlv_4, grammarAccess.getKRoundedBendsPolylineAccess().getStylesKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1592:1: (otherlv_5= ':' )?
                            int alt68=2;
                            int LA68_0 = input.LA(1);

                            if ( (LA68_0==19) ) {
                                alt68=1;
                            }
                            switch (alt68) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1592:3: otherlv_5= ':'
                                    {
                                    otherlv_5=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedBendsPolyline3670); 

                                        	newLeafNode(otherlv_5, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1596:3: ( (lv_styles_6_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1597:1: (lv_styles_6_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1597:1: (lv_styles_6_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1598:3: lv_styles_6_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getStylesKStyleParserRuleCall_3_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3693);
                            lv_styles_6_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_6_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1614:2: ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            loop70:
                            do {
                                int alt70=2;
                                int LA70_0 = input.LA(1);

                                if ( (LA70_0==13||LA70_0==64||(LA70_0>=66 && LA70_0<=77)) ) {
                                    alt70=1;
                                }


                                switch (alt70) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1614:3: (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1614:3: (otherlv_7= ',' )?
                            	    int alt69=2;
                            	    int LA69_0 = input.LA(1);

                            	    if ( (LA69_0==13) ) {
                            	        alt69=1;
                            	    }
                            	    switch (alt69) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1614:5: otherlv_7= ','
                            	            {
                            	            otherlv_7=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedBendsPolyline3707); 

                            	                	newLeafNode(otherlv_7, grammarAccess.getKRoundedBendsPolylineAccess().getCommaKeyword_3_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1618:3: ( (lv_styles_8_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1619:1: (lv_styles_8_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1619:1: (lv_styles_8_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1620:3: lv_styles_8_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getStylesKStyleParserRuleCall_3_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3730);
                            	    lv_styles_8_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_8_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop70;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1636:6: (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==16) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1636:8: otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedBendsPolyline3747); 

                                	newLeafNode(otherlv_9, grammarAccess.getKRoundedBendsPolylineAccess().getPlacementDataKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1640:1: (otherlv_10= ':' )?
                            int alt72=2;
                            int LA72_0 = input.LA(1);

                            if ( (LA72_0==19) ) {
                                alt72=1;
                            }
                            switch (alt72) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1640:3: otherlv_10= ':'
                                    {
                                    otherlv_10=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedBendsPolyline3760); 

                                        	newLeafNode(otherlv_10, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1644:3: ( (lv_placementData_11_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1645:1: (lv_placementData_11_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1645:1: (lv_placementData_11_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1646:3: lv_placementData_11_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getPlacementDataKPlacementDataParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedBendsPolyline3783);
                            lv_placementData_11_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1662:4: (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==20) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1662:6: otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) )
                            {
                            otherlv_12=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedBendsPolyline3798); 

                                	newLeafNode(otherlv_12, grammarAccess.getKRoundedBendsPolylineAccess().getChildPlacementKeyword_3_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1666:1: (otherlv_13= ':' )?
                            int alt74=2;
                            int LA74_0 = input.LA(1);

                            if ( (LA74_0==19) ) {
                                alt74=1;
                            }
                            switch (alt74) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1666:3: otherlv_13= ':'
                                    {
                                    otherlv_13=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedBendsPolyline3811); 

                                        	newLeafNode(otherlv_13, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1670:3: ( (lv_childPlacement_14_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1671:1: (lv_childPlacement_14_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1671:1: (lv_childPlacement_14_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1672:3: lv_childPlacement_14_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getChildPlacementKPlacementParserRuleCall_3_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedBendsPolyline3834);
                            lv_childPlacement_14_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"childPlacement",
                                    		lv_childPlacement_14_0, 
                                    		"KPlacement");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1688:4: (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==21) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1688:6: otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )*
                            {
                            otherlv_15=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedBendsPolyline3849); 

                                	newLeafNode(otherlv_15, grammarAccess.getKRoundedBendsPolylineAccess().getChildrenKeyword_3_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1692:1: (otherlv_16= ':' )?
                            int alt76=2;
                            int LA76_0 = input.LA(1);

                            if ( (LA76_0==19) ) {
                                alt76=1;
                            }
                            switch (alt76) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1692:3: otherlv_16= ':'
                                    {
                                    otherlv_16=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedBendsPolyline3862); 

                                        	newLeafNode(otherlv_16, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1696:3: ( (lv_children_17_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1697:1: (lv_children_17_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1697:1: (lv_children_17_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1698:3: lv_children_17_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getChildrenKRenderingParserRuleCall_3_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3885);
                            lv_children_17_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_17_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1714:2: ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )*
                            loop78:
                            do {
                                int alt78=2;
                                int LA78_0 = input.LA(1);

                                if ( (LA78_0==13||LA78_0==15||LA78_0==18||(LA78_0>=22 && LA78_0<=27)||(LA78_0>=29 && LA78_0<=31)||LA78_0==34||LA78_0==37) ) {
                                    alt78=1;
                                }


                                switch (alt78) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1714:3: (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1714:3: (otherlv_18= ',' )?
                            	    int alt77=2;
                            	    int LA77_0 = input.LA(1);

                            	    if ( (LA77_0==13) ) {
                            	        alt77=1;
                            	    }
                            	    switch (alt77) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1714:5: otherlv_18= ','
                            	            {
                            	            otherlv_18=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedBendsPolyline3899); 

                            	                	newLeafNode(otherlv_18, grammarAccess.getKRoundedBendsPolylineAccess().getCommaKeyword_3_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1718:3: ( (lv_children_19_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1719:1: (lv_children_19_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1719:1: (lv_children_19_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1720:3: lv_children_19_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getChildrenKRenderingParserRuleCall_3_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3922);
                            	    lv_children_19_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"children",
                            	            		lv_children_19_0, 
                            	            		"KRendering");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop78;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedBendsPolyline3938); 

                        	newLeafNode(otherlv_20, grammarAccess.getKRoundedBendsPolylineAccess().getRightCurlyBracketKeyword_3_5());
                        

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


    // $ANTLR start "entryRuleKPolygon"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1748:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1749:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1750:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon3976);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon3986); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1757:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1760:28: ( ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1761:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1761:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1761:2: () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1761:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1762:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKPolygon4032); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getPolygonKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1771:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==12) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1771:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon4045); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1775:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==17) ) {
                        alt84=1;
                    }
                    switch (alt84) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1775:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4058); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1779:1: (otherlv_4= ':' )?
                            int alt81=2;
                            int LA81_0 = input.LA(1);

                            if ( (LA81_0==19) ) {
                                alt81=1;
                            }
                            switch (alt81) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1779:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon4071); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1783:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1784:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1784:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1785:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4094);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1801:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop83:
                            do {
                                int alt83=2;
                                int LA83_0 = input.LA(1);

                                if ( (LA83_0==13||LA83_0==64||(LA83_0>=66 && LA83_0<=77)) ) {
                                    alt83=1;
                                }


                                switch (alt83) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1801:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1801:3: (otherlv_6= ',' )?
                            	    int alt82=2;
                            	    int LA82_0 = input.LA(1);

                            	    if ( (LA82_0==13) ) {
                            	        alt82=1;
                            	    }
                            	    switch (alt82) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1801:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon4108); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1805:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1806:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1806:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1807:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4131);
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
                            	    break loop83;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1823:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==16) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1823:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon4148); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1827:1: (otherlv_9= ':' )?
                            int alt85=2;
                            int LA85_0 = input.LA(1);

                            if ( (LA85_0==19) ) {
                                alt85=1;
                            }
                            switch (alt85) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1827:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon4161); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1831:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1832:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1832:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1833:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon4184);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1849:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==20) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1849:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolygon4199); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1853:1: (otherlv_12= ':' )?
                            int alt87=2;
                            int LA87_0 = input.LA(1);

                            if ( (LA87_0==19) ) {
                                alt87=1;
                            }
                            switch (alt87) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1853:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon4212); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1857:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1858:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1858:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1859:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon4235);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1875:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt92=2;
                    int LA92_0 = input.LA(1);

                    if ( (LA92_0==21) ) {
                        alt92=1;
                    }
                    switch (alt92) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1875:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolygon4250); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1879:1: (otherlv_15= ':' )?
                            int alt89=2;
                            int LA89_0 = input.LA(1);

                            if ( (LA89_0==19) ) {
                                alt89=1;
                            }
                            switch (alt89) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1879:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon4263); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKPolygonAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1883:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1884:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1884:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1885:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4286);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1901:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop91:
                            do {
                                int alt91=2;
                                int LA91_0 = input.LA(1);

                                if ( (LA91_0==13||LA91_0==15||LA91_0==18||(LA91_0>=22 && LA91_0<=27)||(LA91_0>=29 && LA91_0<=31)||LA91_0==34||LA91_0==37) ) {
                                    alt91=1;
                                }


                                switch (alt91) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1901:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1901:3: (otherlv_17= ',' )?
                            	    int alt90=2;
                            	    int LA90_0 = input.LA(1);

                            	    if ( (LA90_0==13) ) {
                            	        alt90=1;
                            	    }
                            	    switch (alt90) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1901:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon4300); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1905:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1906:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1906:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1907:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4323);
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
                            	    break loop91;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon4339); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1935:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1936:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1937:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage4377);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage4387); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1944:1: ruleKImage returns [EObject current=null] : ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1947:28: ( ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:2: () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1949:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKImageAccess().getKImageAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKImage4433); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getImageKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1958:1: ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( ((LA94_0>=RULE_STRING && LA94_0<=RULE_ID)) ) {
                alt94=1;
            }
            else if ( (LA94_0==28) ) {
                alt94=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }
            switch (alt94) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1958:2: ( (lv_bundleName_2_0= ruleEString ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1958:2: ( (lv_bundleName_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1959:1: (lv_bundleName_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1959:1: (lv_bundleName_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1960:3: lv_bundleName_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4455);
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1977:7: otherlv_3= '-'
                    {
                    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKImage4473); 

                        	newLeafNode(otherlv_3, grammarAccess.getKImageAccess().getHyphenMinusKeyword_2_1());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1981:2: ( (lv_imagePath_4_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1982:1: (lv_imagePath_4_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1982:1: (lv_imagePath_4_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1983:3: lv_imagePath_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4495);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1999:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==12) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1999:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4508); 

                        	newLeafNode(otherlv_5, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2003:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt98=2;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==17) ) {
                        alt98=1;
                    }
                    switch (alt98) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2003:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4521); 

                                	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getStylesKeyword_4_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2007:1: (otherlv_7= ':' )?
                            int alt95=2;
                            int LA95_0 = input.LA(1);

                            if ( (LA95_0==19) ) {
                                alt95=1;
                            }
                            switch (alt95) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2007:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKImage4534); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getColonKeyword_4_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2011:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2012:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2012:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2013:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4557);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2029:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop97:
                            do {
                                int alt97=2;
                                int LA97_0 = input.LA(1);

                                if ( (LA97_0==13||LA97_0==64||(LA97_0>=66 && LA97_0<=77)) ) {
                                    alt97=1;
                                }


                                switch (alt97) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2029:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2029:3: (otherlv_9= ',' )?
                            	    int alt96=2;
                            	    int LA96_0 = input.LA(1);

                            	    if ( (LA96_0==13) ) {
                            	        alt96=1;
                            	    }
                            	    switch (alt96) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2029:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4571); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_4_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2033:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2034:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2034:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2035:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4594);
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
                            	    break loop97;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2051:6: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==16) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2051:8: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKImage4611); 

                                	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getPlacementDataKeyword_4_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2055:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2056:1: (lv_placementData_12_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2056:1: (lv_placementData_12_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2057:3: lv_placementData_12_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage4632);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2073:4: (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )?
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==21) ) {
                        alt102=1;
                    }
                    switch (alt102) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2073:6: otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}'
                            {
                            otherlv_13=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKImage4647); 

                                	newLeafNode(otherlv_13, grammarAccess.getKImageAccess().getChildrenKeyword_4_3_0());
                                
                            otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4659); 

                                	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2081:1: ( (lv_children_15_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2082:1: (lv_children_15_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2082:1: (lv_children_15_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2083:3: lv_children_15_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4680);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2099:2: ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )*
                            loop101:
                            do {
                                int alt101=2;
                                int LA101_0 = input.LA(1);

                                if ( (LA101_0==13||LA101_0==15||LA101_0==18||(LA101_0>=22 && LA101_0<=27)||(LA101_0>=29 && LA101_0<=31)||LA101_0==34||LA101_0==37) ) {
                                    alt101=1;
                                }


                                switch (alt101) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2099:3: (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2099:3: (otherlv_16= ',' )?
                            	    int alt100=2;
                            	    int LA100_0 = input.LA(1);

                            	    if ( (LA100_0==13) ) {
                            	        alt100=1;
                            	    }
                            	    switch (alt100) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2099:5: otherlv_16= ','
                            	            {
                            	            otherlv_16=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4694); 

                            	                	newLeafNode(otherlv_16, grammarAccess.getKImageAccess().getCommaKeyword_4_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2103:3: ( (lv_children_17_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2104:1: (lv_children_17_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2104:1: (lv_children_17_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2105:3: lv_children_17_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4717);
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
                            	    break loop101;
                                }
                            } while (true);

                            otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4731); 

                                	newLeafNode(otherlv_18, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_4_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2125:3: (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
                    int alt103=2;
                    int LA103_0 = input.LA(1);

                    if ( (LA103_0==20) ) {
                        alt103=1;
                    }
                    switch (alt103) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2125:5: otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            {
                            otherlv_19=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKImage4746); 

                                	newLeafNode(otherlv_19, grammarAccess.getKImageAccess().getChildPlacementKeyword_4_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2129:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2130:1: (lv_childPlacement_20_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2130:1: (lv_childPlacement_20_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2131:3: lv_childPlacement_20_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_4_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage4767);
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

                    otherlv_21=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4781); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2159:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2160:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2161:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc4819);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc4829); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2168:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2171:28: ( ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2172:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2172:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2172:2: () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2172:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2173:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKArc4875); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getArcKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2182:1: ( (lv_startAngle_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2183:1: (lv_startAngle_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2183:1: (lv_startAngle_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2184:3: lv_startAngle_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4896);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2200:2: (otherlv_3= ',' )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==13) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2200:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4909); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2204:3: ( (lv_arcAngle_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2205:1: (lv_arcAngle_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2205:1: (lv_arcAngle_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2206:3: lv_arcAngle_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4932);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2222:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==12) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2222:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc4945); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2226:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt109=2;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==17) ) {
                        alt109=1;
                    }
                    switch (alt109) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2226:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc4958); 

                                	newLeafNode(otherlv_6, grammarAccess.getKArcAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2230:1: (otherlv_7= ':' )?
                            int alt106=2;
                            int LA106_0 = input.LA(1);

                            if ( (LA106_0==19) ) {
                                alt106=1;
                            }
                            switch (alt106) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2230:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKArc4971); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2234:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2235:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2235:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2236:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc4994);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2252:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop108:
                            do {
                                int alt108=2;
                                int LA108_0 = input.LA(1);

                                if ( (LA108_0==13||LA108_0==64||(LA108_0>=66 && LA108_0<=77)) ) {
                                    alt108=1;
                                }


                                switch (alt108) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2252:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2252:3: (otherlv_9= ',' )?
                            	    int alt107=2;
                            	    int LA107_0 = input.LA(1);

                            	    if ( (LA107_0==13) ) {
                            	        alt107=1;
                            	    }
                            	    switch (alt107) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2252:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc5008); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKArcAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2256:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2257:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2257:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2258:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc5031);
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
                            	    break loop108;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2274:6: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==16) ) {
                        alt110=1;
                    }
                    switch (alt110) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2274:8: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKArc5048); 

                                	newLeafNode(otherlv_11, grammarAccess.getKArcAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2278:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2279:1: (lv_placementData_12_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2279:1: (lv_placementData_12_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2280:3: lv_placementData_12_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc5069);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2296:4: (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )?
                    int alt113=2;
                    int LA113_0 = input.LA(1);

                    if ( (LA113_0==21) ) {
                        alt113=1;
                    }
                    switch (alt113) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2296:6: otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}'
                            {
                            otherlv_13=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKArc5084); 

                                	newLeafNode(otherlv_13, grammarAccess.getKArcAccess().getChildrenKeyword_5_3_0());
                                
                            otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc5096); 

                                	newLeafNode(otherlv_14, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2304:1: ( (lv_children_15_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2305:1: (lv_children_15_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2305:1: (lv_children_15_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2306:3: lv_children_15_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5117);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2322:2: ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )*
                            loop112:
                            do {
                                int alt112=2;
                                int LA112_0 = input.LA(1);

                                if ( (LA112_0==13||LA112_0==15||LA112_0==18||(LA112_0>=22 && LA112_0<=27)||(LA112_0>=29 && LA112_0<=31)||LA112_0==34||LA112_0==37) ) {
                                    alt112=1;
                                }


                                switch (alt112) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2322:3: (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2322:3: (otherlv_16= ',' )?
                            	    int alt111=2;
                            	    int LA111_0 = input.LA(1);

                            	    if ( (LA111_0==13) ) {
                            	        alt111=1;
                            	    }
                            	    switch (alt111) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2322:5: otherlv_16= ','
                            	            {
                            	            otherlv_16=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc5131); 

                            	                	newLeafNode(otherlv_16, grammarAccess.getKArcAccess().getCommaKeyword_5_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2326:3: ( (lv_children_17_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2327:1: (lv_children_17_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2327:1: (lv_children_17_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2328:3: lv_children_17_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5154);
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
                            	    break loop112;
                                }
                            } while (true);

                            otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5168); 

                                	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_5_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2348:3: (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
                    int alt114=2;
                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==20) ) {
                        alt114=1;
                    }
                    switch (alt114) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2348:5: otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            {
                            otherlv_19=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKArc5183); 

                                	newLeafNode(otherlv_19, grammarAccess.getKArcAccess().getChildPlacementKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2352:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2353:1: (lv_childPlacement_20_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2353:1: (lv_childPlacement_20_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2354:3: lv_childPlacement_20_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_5_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc5204);
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

                    otherlv_21=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5218); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2382:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2383:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2384:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea5256);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea5266); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2391:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2394:28: ( ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2395:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2395:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2395:2: () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2395:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2396:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKChildArea5312); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getChildAreaKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2405:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==12) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2405:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea5325); 

                        	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2409:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==17) ) {
                        alt119=1;
                    }
                    switch (alt119) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2409:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea5338); 

                                	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2413:1: (otherlv_4= ':' )?
                            int alt116=2;
                            int LA116_0 = input.LA(1);

                            if ( (LA116_0==19) ) {
                                alt116=1;
                            }
                            switch (alt116) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2413:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKChildArea5351); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2417:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2418:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2418:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2419:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5374);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2435:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop118:
                            do {
                                int alt118=2;
                                int LA118_0 = input.LA(1);

                                if ( (LA118_0==13||LA118_0==64||(LA118_0>=66 && LA118_0<=77)) ) {
                                    alt118=1;
                                }


                                switch (alt118) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2435:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2435:3: (otherlv_6= ',' )?
                            	    int alt117=2;
                            	    int LA117_0 = input.LA(1);

                            	    if ( (LA117_0==13) ) {
                            	        alt117=1;
                            	    }
                            	    switch (alt117) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2435:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKChildArea5388); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2439:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2440:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2440:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2441:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5411);
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
                            	    break loop118;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2457:6: (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )?
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==16) ) {
                        alt120=1;
                    }
                    switch (alt120) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2457:8: otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKChildArea5428); 

                                	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2461:1: ( (lv_placementData_9_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2462:1: (lv_placementData_9_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2462:1: (lv_placementData_9_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2463:3: lv_placementData_9_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea5449);
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

                    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea5463); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2491:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2492:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2493:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText5501);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText5511); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2500:1: ruleKText returns [EObject current=null] : ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? ) ;
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
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        AntlrDatatypeRuleToken lv_text_2_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_styles_9_0 = null;

        EObject lv_placementData_11_0 = null;

        EObject lv_children_14_0 = null;

        EObject lv_children_16_0 = null;

        EObject lv_childPlacement_19_0 = null;

        EObject lv_persistentEntries_22_0 = null;

        EObject lv_persistentEntries_24_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2503:28: ( ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2504:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2504:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2504:2: () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2504:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2505:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTextAccess().getKTextAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKText5557); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getTextKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2514:1: ( (lv_text_2_0= ruleEString ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( ((LA122_0>=RULE_STRING && LA122_0<=RULE_ID)) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2515:1: (lv_text_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2515:1: (lv_text_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2516:3: lv_text_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText5578);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2532:3: (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==12) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2532:5: otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5592); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2536:1: ( (lv_clip_4_0= 'clip' ) )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==32) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2537:1: (lv_clip_4_0= 'clip' )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2537:1: (lv_clip_4_0= 'clip' )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2538:3: lv_clip_4_0= 'clip'
                            {
                            lv_clip_4_0=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKText5610); 

                                    newLeafNode(lv_clip_4_0, grammarAccess.getKTextAccess().getClipClipKeyword_3_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getKTextRule());
                            	        }
                                   		setWithLastConsumed(current, "clip", true, "clip");
                            	    

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2551:3: (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )?
                    int alt127=2;
                    int LA127_0 = input.LA(1);

                    if ( (LA127_0==17) ) {
                        alt127=1;
                    }
                    switch (alt127) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2551:5: otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            {
                            otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5637); 

                                	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getStylesKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2555:1: (otherlv_6= ':' )?
                            int alt124=2;
                            int LA124_0 = input.LA(1);

                            if ( (LA124_0==19) ) {
                                alt124=1;
                            }
                            switch (alt124) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2555:3: otherlv_6= ':'
                                    {
                                    otherlv_6=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKText5650); 

                                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2559:3: ( (lv_styles_7_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2560:1: (lv_styles_7_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2560:1: (lv_styles_7_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2561:3: lv_styles_7_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5673);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2577:2: ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            loop126:
                            do {
                                int alt126=2;
                                int LA126_0 = input.LA(1);

                                if ( (LA126_0==13||LA126_0==64||(LA126_0>=66 && LA126_0<=77)) ) {
                                    alt126=1;
                                }


                                switch (alt126) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2577:3: (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2577:3: (otherlv_8= ',' )?
                            	    int alt125=2;
                            	    int LA125_0 = input.LA(1);

                            	    if ( (LA125_0==13) ) {
                            	        alt125=1;
                            	    }
                            	    switch (alt125) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2577:5: otherlv_8= ','
                            	            {
                            	            otherlv_8=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5687); 

                            	                	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_3_2_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2581:3: ( (lv_styles_9_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2582:1: (lv_styles_9_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2582:1: (lv_styles_9_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2583:3: lv_styles_9_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5710);
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
                            	    break loop126;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2599:6: (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt128=2;
                    int LA128_0 = input.LA(1);

                    if ( (LA128_0==16) ) {
                        alt128=1;
                    }
                    switch (alt128) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2599:8: otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_10=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKText5727); 

                                	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getPlacementDataKeyword_3_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2603:1: ( (lv_placementData_11_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2604:1: (lv_placementData_11_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2604:1: (lv_placementData_11_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2605:3: lv_placementData_11_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_3_3_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText5748);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2621:4: (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )?
                    int alt131=2;
                    int LA131_0 = input.LA(1);

                    if ( (LA131_0==21) ) {
                        alt131=1;
                    }
                    switch (alt131) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2621:6: otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}'
                            {
                            otherlv_12=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKText5763); 

                                	newLeafNode(otherlv_12, grammarAccess.getKTextAccess().getChildrenKeyword_3_4_0());
                                
                            otherlv_13=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5775); 

                                	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_4_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2629:1: ( (lv_children_14_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2630:1: (lv_children_14_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2630:1: (lv_children_14_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2631:3: lv_children_14_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5796);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2647:2: ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )*
                            loop130:
                            do {
                                int alt130=2;
                                int LA130_0 = input.LA(1);

                                if ( (LA130_0==13||LA130_0==15||LA130_0==18||(LA130_0>=22 && LA130_0<=27)||(LA130_0>=29 && LA130_0<=31)||LA130_0==34||LA130_0==37) ) {
                                    alt130=1;
                                }


                                switch (alt130) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2647:3: (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2647:3: (otherlv_15= ',' )?
                            	    int alt129=2;
                            	    int LA129_0 = input.LA(1);

                            	    if ( (LA129_0==13) ) {
                            	        alt129=1;
                            	    }
                            	    switch (alt129) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2647:5: otherlv_15= ','
                            	            {
                            	            otherlv_15=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5810); 

                            	                	newLeafNode(otherlv_15, grammarAccess.getKTextAccess().getCommaKeyword_3_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2651:3: ( (lv_children_16_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2652:1: (lv_children_16_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2652:1: (lv_children_16_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2653:3: lv_children_16_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5833);
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
                            	    break loop130;
                                }
                            } while (true);

                            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText5847); 

                                	newLeafNode(otherlv_17, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_4_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2673:3: (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )?
                    int alt132=2;
                    int LA132_0 = input.LA(1);

                    if ( (LA132_0==20) ) {
                        alt132=1;
                    }
                    switch (alt132) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2673:5: otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            {
                            otherlv_18=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKText5862); 

                                	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getChildPlacementKeyword_3_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2677:1: ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2678:1: (lv_childPlacement_19_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2678:1: (lv_childPlacement_19_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2679:3: lv_childPlacement_19_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_3_5_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText5883);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2695:4: (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )?
                    int alt136=2;
                    int LA136_0 = input.LA(1);

                    if ( (LA136_0==33) ) {
                        alt136=1;
                    }
                    switch (alt136) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2695:6: otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )*
                            {
                            otherlv_20=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKText5898); 

                                	newLeafNode(otherlv_20, grammarAccess.getKTextAccess().getMapPropertiesKeyword_3_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2699:1: (otherlv_21= ':' )?
                            int alt133=2;
                            int LA133_0 = input.LA(1);

                            if ( (LA133_0==19) ) {
                                alt133=1;
                            }
                            switch (alt133) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2699:3: otherlv_21= ':'
                                    {
                                    otherlv_21=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKText5911); 

                                        	newLeafNode(otherlv_21, grammarAccess.getKTextAccess().getColonKeyword_3_6_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2703:3: ( (lv_persistentEntries_22_0= rulePersistentEntry ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2704:1: (lv_persistentEntries_22_0= rulePersistentEntry )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2704:1: (lv_persistentEntries_22_0= rulePersistentEntry )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2705:3: lv_persistentEntries_22_0= rulePersistentEntry
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_6_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKText5934);
                            lv_persistentEntries_22_0=rulePersistentEntry();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"persistentEntries",
                                    		lv_persistentEntries_22_0, 
                                    		"PersistentEntry");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2721:2: ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )*
                            loop135:
                            do {
                                int alt135=2;
                                int LA135_0 = input.LA(1);

                                if ( ((LA135_0>=RULE_STRING && LA135_0<=RULE_ID)||LA135_0==13) ) {
                                    alt135=1;
                                }


                                switch (alt135) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2721:3: (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2721:3: (otherlv_23= ',' )?
                            	    int alt134=2;
                            	    int LA134_0 = input.LA(1);

                            	    if ( (LA134_0==13) ) {
                            	        alt134=1;
                            	    }
                            	    switch (alt134) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2721:5: otherlv_23= ','
                            	            {
                            	            otherlv_23=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5948); 

                            	                	newLeafNode(otherlv_23, grammarAccess.getKTextAccess().getCommaKeyword_3_6_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2725:3: ( (lv_persistentEntries_24_0= rulePersistentEntry ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2726:1: (lv_persistentEntries_24_0= rulePersistentEntry )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2726:1: (lv_persistentEntries_24_0= rulePersistentEntry )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2727:3: lv_persistentEntries_24_0= rulePersistentEntry
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_6_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKText5971);
                            	    lv_persistentEntries_24_0=rulePersistentEntry();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"persistentEntries",
                            	            		lv_persistentEntries_24_0, 
                            	            		"PersistentEntry");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop135;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_25=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText5987); 

                        	newLeafNode(otherlv_25, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_7());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2755:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2756:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2757:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6025);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering6035); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2764:1: ruleKCustomRendering returns [EObject current=null] : ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2767:28: ( ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2768:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2768:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2768:2: () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2768:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2769:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKCustomRenderingAccess().getKCustomRenderingAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKCustomRendering6081); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getCustomRenderingKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2778:1: (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==12) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2778:3: otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6094); 

                        	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKCustomRendering6106); 

                        	newLeafNode(otherlv_3, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2786:1: ( (lv_className_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2787:1: (lv_className_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2787:1: (lv_className_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2788:3: lv_className_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6127);
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

                    otherlv_5=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKCustomRendering6139); 

                        	newLeafNode(otherlv_5, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_2_3());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2808:1: ( (lv_bundleName_6_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2809:1: (lv_bundleName_6_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2809:1: (lv_bundleName_6_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2810:3: lv_bundleName_6_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_2_4_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6160);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2826:2: (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )?
                    int alt141=2;
                    int LA141_0 = input.LA(1);

                    if ( (LA141_0==17) ) {
                        alt141=1;
                    }
                    switch (alt141) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2826:4: otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            {
                            otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6173); 

                                	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_2_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2830:1: (otherlv_8= ':' )?
                            int alt138=2;
                            int LA138_0 = input.LA(1);

                            if ( (LA138_0==19) ) {
                                alt138=1;
                            }
                            switch (alt138) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2830:3: otherlv_8= ':'
                                    {
                                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKCustomRendering6186); 

                                        	newLeafNode(otherlv_8, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2834:3: ( (lv_styles_9_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2835:1: (lv_styles_9_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2835:1: (lv_styles_9_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2836:3: lv_styles_9_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6209);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2852:2: ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            loop140:
                            do {
                                int alt140=2;
                                int LA140_0 = input.LA(1);

                                if ( (LA140_0==13||LA140_0==64||(LA140_0>=66 && LA140_0<=77)) ) {
                                    alt140=1;
                                }


                                switch (alt140) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2852:3: (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2852:3: (otherlv_10= ',' )?
                            	    int alt139=2;
                            	    int LA139_0 = input.LA(1);

                            	    if ( (LA139_0==13) ) {
                            	        alt139=1;
                            	    }
                            	    switch (alt139) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2852:5: otherlv_10= ','
                            	            {
                            	            otherlv_10=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6223); 

                            	                	newLeafNode(otherlv_10, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2856:3: ( (lv_styles_11_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2857:1: (lv_styles_11_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2857:1: (lv_styles_11_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2858:3: lv_styles_11_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6246);
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
                            	    break loop140;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2874:6: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt142=2;
                    int LA142_0 = input.LA(1);

                    if ( (LA142_0==16) ) {
                        alt142=1;
                    }
                    switch (alt142) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2874:8: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKCustomRendering6263); 

                                	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_2_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2878:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2879:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2879:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2880:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_2_6_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6284);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2896:4: (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )?
                    int alt145=2;
                    int LA145_0 = input.LA(1);

                    if ( (LA145_0==21) ) {
                        alt145=1;
                    }
                    switch (alt145) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2896:6: otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}'
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKCustomRendering6299); 

                                	newLeafNode(otherlv_14, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_2_7_0());
                                
                            otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6311); 

                                	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_7_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2904:1: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2905:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2905:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2906:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_7_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6332);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2922:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop144:
                            do {
                                int alt144=2;
                                int LA144_0 = input.LA(1);

                                if ( (LA144_0==13||LA144_0==15||LA144_0==18||(LA144_0>=22 && LA144_0<=27)||(LA144_0>=29 && LA144_0<=31)||LA144_0==34||LA144_0==37) ) {
                                    alt144=1;
                                }


                                switch (alt144) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2922:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2922:3: (otherlv_17= ',' )?
                            	    int alt143=2;
                            	    int LA143_0 = input.LA(1);

                            	    if ( (LA143_0==13) ) {
                            	        alt143=1;
                            	    }
                            	    switch (alt143) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2922:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6346); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_7_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2926:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2927:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2927:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2928:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_7_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6369);
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
                            	    break loop144;
                                }
                            } while (true);

                            otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6383); 

                                	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_2_7_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2948:3: (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )?
                    int alt146=2;
                    int LA146_0 = input.LA(1);

                    if ( (LA146_0==20) ) {
                        alt146=1;
                    }
                    switch (alt146) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2948:5: otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) )
                            {
                            otherlv_20=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKCustomRendering6398); 

                                	newLeafNode(otherlv_20, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_2_8_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2952:1: ( (lv_childPlacement_21_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2953:1: (lv_childPlacement_21_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2953:1: (lv_childPlacement_21_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2954:3: lv_childPlacement_21_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_2_8_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering6419);
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

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6433); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2982:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2983:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2984:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline6471);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline6481); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2991:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2994:28: ( ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2995:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2995:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2995:2: () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2995:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2996:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKSpline6527); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getSplineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3005:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==12) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3005:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6540); 

                        	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3009:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt151=2;
                    int LA151_0 = input.LA(1);

                    if ( (LA151_0==17) ) {
                        alt151=1;
                    }
                    switch (alt151) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3009:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6553); 

                                	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3013:1: (otherlv_4= ':' )?
                            int alt148=2;
                            int LA148_0 = input.LA(1);

                            if ( (LA148_0==19) ) {
                                alt148=1;
                            }
                            switch (alt148) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3013:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKSpline6566); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3017:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3018:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3018:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3019:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6589);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3035:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop150:
                            do {
                                int alt150=2;
                                int LA150_0 = input.LA(1);

                                if ( (LA150_0==13||LA150_0==64||(LA150_0>=66 && LA150_0<=77)) ) {
                                    alt150=1;
                                }


                                switch (alt150) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3035:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3035:3: (otherlv_6= ',' )?
                            	    int alt149=2;
                            	    int LA149_0 = input.LA(1);

                            	    if ( (LA149_0==13) ) {
                            	        alt149=1;
                            	    }
                            	    switch (alt149) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3035:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6603); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3039:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3040:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3040:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3041:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6626);
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
                            	    break loop150;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3057:6: (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )?
                    int alt152=2;
                    int LA152_0 = input.LA(1);

                    if ( (LA152_0==16) ) {
                        alt152=1;
                    }
                    switch (alt152) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3057:8: otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKSpline6643); 

                                	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3061:1: ( (lv_placementData_9_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3062:1: (lv_placementData_9_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3062:1: (lv_placementData_9_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3063:3: lv_placementData_9_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline6664);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3079:4: (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )?
                    int alt155=2;
                    int LA155_0 = input.LA(1);

                    if ( (LA155_0==21) ) {
                        alt155=1;
                    }
                    switch (alt155) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3079:6: otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}'
                            {
                            otherlv_10=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKSpline6679); 

                                	newLeafNode(otherlv_10, grammarAccess.getKSplineAccess().getChildrenKeyword_2_3_0());
                                
                            otherlv_11=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6691); 

                                	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3087:1: ( (lv_children_12_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3088:1: (lv_children_12_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3088:1: (lv_children_12_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3089:3: lv_children_12_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6712);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3105:2: ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )*
                            loop154:
                            do {
                                int alt154=2;
                                int LA154_0 = input.LA(1);

                                if ( (LA154_0==13||LA154_0==15||LA154_0==18||(LA154_0>=22 && LA154_0<=27)||(LA154_0>=29 && LA154_0<=31)||LA154_0==34||LA154_0==37) ) {
                                    alt154=1;
                                }


                                switch (alt154) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3105:3: (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3105:3: (otherlv_13= ',' )?
                            	    int alt153=2;
                            	    int LA153_0 = input.LA(1);

                            	    if ( (LA153_0==13) ) {
                            	        alt153=1;
                            	    }
                            	    switch (alt153) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3105:5: otherlv_13= ','
                            	            {
                            	            otherlv_13=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6726); 

                            	                	newLeafNode(otherlv_13, grammarAccess.getKSplineAccess().getCommaKeyword_2_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3109:3: ( (lv_children_14_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3110:1: (lv_children_14_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3110:1: (lv_children_14_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3111:3: lv_children_14_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6749);
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
                            	    break loop154;
                                }
                            } while (true);

                            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6763); 

                                	newLeafNode(otherlv_15, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_2_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3131:3: (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )?
                    int alt156=2;
                    int LA156_0 = input.LA(1);

                    if ( (LA156_0==20) ) {
                        alt156=1;
                    }
                    switch (alt156) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3131:5: otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            {
                            otherlv_16=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKSpline6778); 

                                	newLeafNode(otherlv_16, grammarAccess.getKSplineAccess().getChildPlacementKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3135:1: ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3136:1: (lv_childPlacement_17_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3136:1: (lv_childPlacement_17_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3137:3: lv_childPlacement_17_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_2_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline6799);
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

                    otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6813); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3165:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3166:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3167:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6851);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6861); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3174:1: ruleKDecoratorPlacementData returns [EObject current=null] : (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3177:28: ( (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3178:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3178:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3178:3: otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}'
            {
            otherlv_0=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKDecoratorPlacementData6898); 

                	newLeafNode(otherlv_0, grammarAccess.getKDecoratorPlacementDataAccess().getDecoratorPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData6910); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3186:1: ( (lv_relative_2_0= 'relative' ) )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==39) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3187:1: (lv_relative_2_0= 'relative' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3187:1: (lv_relative_2_0= 'relative' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3188:3: lv_relative_2_0= 'relative'
                    {
                    lv_relative_2_0=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKDecoratorPlacementData6928); 

                            newLeafNode(lv_relative_2_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		setWithLastConsumed(current, "relative", true, "relative");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKDecoratorPlacementData6954); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3205:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3206:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3206:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3207:3: lv_location_4_0= ruleEFloat
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3223:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==41) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3223:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKDecoratorPlacementData6988); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3227:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3228:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3228:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3229:3: lv_xOffset_6_0= ruleEFloat
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3245:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==42) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3245:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKDecoratorPlacementData7024); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3249:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3250:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3250:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3251:3: lv_yOffset_8_0= ruleEFloat
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3267:4: (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==43) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3267:6: otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKDecoratorPlacementData7060); 

                        	newLeafNode(otherlv_9, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3271:1: ( (lv_width_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3272:1: (lv_width_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3272:1: (lv_width_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3273:3: lv_width_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7081);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3289:4: (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==44) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3289:6: otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKDecoratorPlacementData7096); 

                        	newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3293:1: ( (lv_height_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3294:1: (lv_height_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3294:1: (lv_height_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3295:3: lv_height_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7117);
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

            otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDecoratorPlacementData7131); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3323:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3324:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3325:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7167);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData7177); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3332:1: ruleKGridPlacementData returns [EObject current=null] : ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' ) ;
    public final EObject ruleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        AntlrDatatypeRuleToken lv_widthHint_4_0 = null;

        AntlrDatatypeRuleToken lv_heightHint_6_0 = null;

        AntlrDatatypeRuleToken lv_insetRight_8_0 = null;

        AntlrDatatypeRuleToken lv_insetBottom_10_0 = null;

        AntlrDatatypeRuleToken lv_insetLeft_12_0 = null;

        AntlrDatatypeRuleToken lv_insetTop_14_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3335:28: ( ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3336:1: ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3336:1: ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3336:2: () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3336:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3337:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKGridPlacementData7223); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getGridPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData7235); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3350:1: (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )?
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==46) ) {
                alt163=1;
            }
            switch (alt163) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3350:3: otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKGridPlacementData7248); 

                        	newLeafNode(otherlv_3, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3354:1: ( (lv_widthHint_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3355:1: (lv_widthHint_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3355:1: (lv_widthHint_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3356:3: lv_widthHint_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7269);
                    lv_widthHint_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"widthHint",
                            		lv_widthHint_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3372:4: (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )?
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==47) ) {
                alt164=1;
            }
            switch (alt164) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3372:6: otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKGridPlacementData7284); 

                        	newLeafNode(otherlv_5, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3376:1: ( (lv_heightHint_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3377:1: (lv_heightHint_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3377:1: (lv_heightHint_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3378:3: lv_heightHint_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7305);
                    lv_heightHint_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"heightHint",
                            		lv_heightHint_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3394:4: (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==48) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3394:6: otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKGridPlacementData7320); 

                        	newLeafNode(otherlv_7, grammarAccess.getKGridPlacementDataAccess().getInsetRightKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3398:1: ( (lv_insetRight_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3399:1: (lv_insetRight_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3399:1: (lv_insetRight_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3400:3: lv_insetRight_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetRightEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7341);
                    lv_insetRight_8_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetRight",
                            		lv_insetRight_8_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3416:4: (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )?
            int alt166=2;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==49) ) {
                alt166=1;
            }
            switch (alt166) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3416:6: otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKGridPlacementData7356); 

                        	newLeafNode(otherlv_9, grammarAccess.getKGridPlacementDataAccess().getInsetBottomKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3420:1: ( (lv_insetBottom_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3421:1: (lv_insetBottom_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3421:1: (lv_insetBottom_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3422:3: lv_insetBottom_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetBottomEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7377);
                    lv_insetBottom_10_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetBottom",
                            		lv_insetBottom_10_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3438:4: (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )?
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==50) ) {
                alt167=1;
            }
            switch (alt167) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3438:6: otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKGridPlacementData7392); 

                        	newLeafNode(otherlv_11, grammarAccess.getKGridPlacementDataAccess().getInsetLeftKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3442:1: ( (lv_insetLeft_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3443:1: (lv_insetLeft_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3443:1: (lv_insetLeft_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3444:3: lv_insetLeft_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7413);
                    lv_insetLeft_12_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetLeft",
                            		lv_insetLeft_12_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3460:4: (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )?
            int alt168=2;
            int LA168_0 = input.LA(1);

            if ( (LA168_0==51) ) {
                alt168=1;
            }
            switch (alt168) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3460:6: otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) )
                    {
                    otherlv_13=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKGridPlacementData7428); 

                        	newLeafNode(otherlv_13, grammarAccess.getKGridPlacementDataAccess().getInsetTopKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3464:1: ( (lv_insetTop_14_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3465:1: (lv_insetTop_14_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3465:1: (lv_insetTop_14_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3466:3: lv_insetTop_14_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetTopEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7449);
                    lv_insetTop_14_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetTop",
                            		lv_insetTop_14_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKGridPlacementData7463); 

                	newLeafNode(otherlv_15, grammarAccess.getKGridPlacementDataAccess().getRightCurlyBracketKeyword_9());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3494:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3495:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3496:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData7499);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData7509); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3503:1: ruleKStackPlacementData returns [EObject current=null] : ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
    public final EObject ruleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        AntlrDatatypeRuleToken lv_insetRight_4_0 = null;

        AntlrDatatypeRuleToken lv_insetBottom_6_0 = null;

        AntlrDatatypeRuleToken lv_insetLeft_8_0 = null;

        AntlrDatatypeRuleToken lv_insetTop_10_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3506:28: ( ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3507:1: ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3507:1: ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3507:2: () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3507:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3508:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementDataAccess().getKStackPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKStackPlacementData7555); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getStackPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData7567); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3521:1: (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==48) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3521:3: otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKStackPlacementData7580); 

                        	newLeafNode(otherlv_3, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3525:1: ( (lv_insetRight_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3526:1: (lv_insetRight_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3526:1: (lv_insetRight_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3527:3: lv_insetRight_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7601);
                    lv_insetRight_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetRight",
                            		lv_insetRight_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3543:4: (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )?
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==49) ) {
                alt170=1;
            }
            switch (alt170) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3543:6: otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKStackPlacementData7616); 

                        	newLeafNode(otherlv_5, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3547:1: ( (lv_insetBottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3548:1: (lv_insetBottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3548:1: (lv_insetBottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3549:3: lv_insetBottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7637);
                    lv_insetBottom_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetBottom",
                            		lv_insetBottom_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3565:4: (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )?
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==50) ) {
                alt171=1;
            }
            switch (alt171) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3565:6: otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKStackPlacementData7652); 

                        	newLeafNode(otherlv_7, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3569:1: ( (lv_insetLeft_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3570:1: (lv_insetLeft_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3570:1: (lv_insetLeft_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3571:3: lv_insetLeft_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7673);
                    lv_insetLeft_8_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetLeft",
                            		lv_insetLeft_8_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3587:4: (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )?
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( (LA172_0==51) ) {
                alt172=1;
            }
            switch (alt172) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3587:6: otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKStackPlacementData7688); 

                        	newLeafNode(otherlv_9, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3591:1: ( (lv_insetTop_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3592:1: (lv_insetTop_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3592:1: (lv_insetTop_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3593:3: lv_insetTop_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7709);
                    lv_insetTop_10_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKStackPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"insetTop",
                            		lv_insetTop_10_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKStackPlacementData7723); 

                	newLeafNode(otherlv_11, grammarAccess.getKStackPlacementDataAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3621:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3622:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3623:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7759);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData7769); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3630:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3633:28: ( (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3634:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3634:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3634:3: otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKDirectPlacementData7806); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData7818); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKDirectPlacementData7830); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3646:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3647:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3647:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3648:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7851);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3664:2: (otherlv_4= ',' )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==13) ) {
                alt173=1;
            }
            switch (alt173) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3664:4: otherlv_4= ','
                    {
                    otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKDirectPlacementData7864); 

                        	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getCommaKeyword_4());
                        

                    }
                    break;

            }

            otherlv_5=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKDirectPlacementData7878); 

                	newLeafNode(otherlv_5, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3672:1: ( (lv_bottomRight_6_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3673:1: (lv_bottomRight_6_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3673:1: (lv_bottomRight_6_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3674:3: lv_bottomRight_6_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7899);
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

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDirectPlacementData7911); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3702:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3703:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3704:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData7947);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData7957); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3711:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3714:28: ( (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3715:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3715:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3715:3: otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKPolylinePlacementData7994); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getPolylinePlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData8006); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKPolylinePlacementData8018); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3727:1: (otherlv_3= ':' )?
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==19) ) {
                alt174=1;
            }
            switch (alt174) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3727:3: otherlv_3= ':'
                    {
                    otherlv_3=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolylinePlacementData8031); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3731:3: ( (lv_points_4_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3732:1: (lv_points_4_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3732:1: (lv_points_4_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3733:3: lv_points_4_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8054);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3749:2: ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )*
            loop176:
            do {
                int alt176=2;
                int LA176_0 = input.LA(1);

                if ( (LA176_0==13||(LA176_0>=60 && LA176_0<=61)) ) {
                    alt176=1;
                }


                switch (alt176) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3749:3: (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3749:3: (otherlv_5= ',' )?
            	    int alt175=2;
            	    int LA175_0 = input.LA(1);

            	    if ( (LA175_0==13) ) {
            	        alt175=1;
            	    }
            	    switch (alt175) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3749:5: otherlv_5= ','
            	            {
            	            otherlv_5=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolylinePlacementData8068); 

            	                	newLeafNode(otherlv_5, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_5_0());
            	                

            	            }
            	            break;

            	    }

            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3753:3: ( (lv_points_6_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3754:1: (lv_points_6_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3754:1: (lv_points_6_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3755:3: lv_points_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8091);
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
            	    break loop176;
                }
            } while (true);

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3771:4: (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )?
            int alt177=2;
            int LA177_0 = input.LA(1);

            if ( (LA177_0==58) ) {
                alt177=1;
            }
            switch (alt177) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3771:6: otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) )
                    {
                    otherlv_7=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKPolylinePlacementData8106); 

                        	newLeafNode(otherlv_7, grammarAccess.getKPolylinePlacementDataAccess().getDetailedPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3775:1: ( (lv_detailPlacementData_8_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3776:1: (lv_detailPlacementData_8_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3776:1: (lv_detailPlacementData_8_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3777:3: lv_detailPlacementData_8_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getDetailPlacementDataKPlacementDataParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData8127);
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

            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolylinePlacementData8141); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3805:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3806:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3807:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition8177);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition8187); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3814:1: ruleKPosition returns [EObject current=null] : ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_x_0_0 = null;

        EObject lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3817:28: ( ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3818:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3818:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3818:2: ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3818:2: ( (lv_x_0_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3819:1: (lv_x_0_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3819:1: (lv_x_0_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3820:3: lv_x_0_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition8233);
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

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKPosition8245); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getSolidusKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3840:1: ( (lv_y_2_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3841:1: (lv_y_2_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3841:1: (lv_y_2_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3842:3: lv_y_2_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition8266);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3866:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3867:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3868:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8302);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition8312); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3875:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3878:28: ( ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3879:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3879:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3879:2: () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3879:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3880:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKLeftPosition8358); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getLeftKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3889:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3890:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3890:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3891:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8379);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3907:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3908:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3908:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3909:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8400);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3933:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3934:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3935:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8436);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition8446); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3942:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3945:28: ( ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3946:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3946:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3946:2: () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3946:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3947:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKRightPosition8492); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getRightKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3956:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3957:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3957:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3958:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8513);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3974:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3975:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3975:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3976:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8534);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4000:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4001:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4002:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8570);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition8580); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4009:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4012:28: ( ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4013:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4013:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4013:2: () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4013:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4014:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKTopPosition8626); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getTopKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4023:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4024:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4024:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4025:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8647);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4041:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4042:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4042:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4043:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8668);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4067:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4068:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4069:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8704);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition8714); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4076:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4079:28: ( ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4080:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4080:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4080:2: () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4080:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4081:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKBottomPosition8760); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getBottomKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4090:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4091:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4091:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4092:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8781);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4108:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4109:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4109:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4110:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8802);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4134:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4135:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4136:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor8838);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor8848); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4143:1: ruleKForegroundColor returns [EObject current=null] : ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKForegroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4146:28: ( ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4147:1: ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4147:1: ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4147:2: () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4147:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4148:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundColorAccess().getKForegroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKForegroundColor8894); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getForegroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4157:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4158:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4158:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4159:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor8915);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4175:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4176:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4176:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4177:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor8936);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4193:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4194:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4194:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4195:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor8957);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4211:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==65) ) {
                alt178=1;
            }
            switch (alt178) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4212:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4212:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4213:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKForegroundColor8975); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4234:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4235:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4236:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor9025);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor9035); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4243:1: ruleKBackgroundColor returns [EObject current=null] : ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4246:28: ( ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4247:1: ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4247:1: ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4247:2: () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4247:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4248:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundColorAccess().getKBackgroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKBackgroundColor9081); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getBackgroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4257:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4258:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4258:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4259:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9102);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4275:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4276:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4276:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4277:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9123);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4293:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4294:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4294:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4295:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9144);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4311:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==65) ) {
                alt179=1;
            }
            switch (alt179) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4312:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4312:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4313:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKBackgroundColor9162); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4334:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4335:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4336:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9212);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth9222); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4343:1: ruleKLineWidth returns [EObject current=null] : (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_lineWidth_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4346:28: ( (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4347:1: (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4347:1: (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4347:3: otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKLineWidth9259); 

                	newLeafNode(otherlv_0, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4351:1: ( (lv_lineWidth_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4352:1: (lv_lineWidth_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4352:1: (lv_lineWidth_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4353:3: lv_lineWidth_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth9280);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4369:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==65) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4370:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4370:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4371:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKLineWidth9298); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4392:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4393:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4394:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility9348);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility9358); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4401:1: ruleKVisibility returns [EObject current=null] : (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundVisibility_0 = null;

        EObject this_KBackgroundVisibility_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4404:28: ( (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4405:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4405:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==68) ) {
                alt181=1;
            }
            else if ( (LA181_0==69) ) {
                alt181=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 181, 0, input);

                throw nvae;
            }
            switch (alt181) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4406:5: this_KForegroundVisibility_0= ruleKForegroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKForegroundVisibilityParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility9405);
                    this_KForegroundVisibility_0=ruleKForegroundVisibility();

                    state._fsp--;

                     
                            current = this_KForegroundVisibility_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4416:5: this_KBackgroundVisibility_1= ruleKBackgroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKBackgroundVisibilityParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility9432);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4432:1: entryRuleKForegroundVisibility returns [EObject current=null] : iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF ;
    public final EObject entryRuleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4433:2: (iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4434:2: iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKForegroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility9467);
            iv_ruleKForegroundVisibility=ruleKForegroundVisibility();

            state._fsp--;

             current =iv_ruleKForegroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundVisibility9477); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4441:1: ruleKForegroundVisibility returns [EObject current=null] : ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4444:28: ( ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4445:1: ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4445:1: ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4445:2: () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4445:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4446:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKForegroundVisibility9523); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundVisibilityAccess().getForegroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4455:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4456:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4456:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4457:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility9544);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4473:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==65) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4474:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4474:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4475:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKForegroundVisibility9562); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4496:1: entryRuleKBackgroundVisibility returns [EObject current=null] : iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF ;
    public final EObject entryRuleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4497:2: (iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4498:2: iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility9612);
            iv_ruleKBackgroundVisibility=ruleKBackgroundVisibility();

            state._fsp--;

             current =iv_ruleKBackgroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundVisibility9622); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4505:1: ruleKBackgroundVisibility returns [EObject current=null] : ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4508:28: ( ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4509:1: ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4509:1: ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4509:2: () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4509:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4510:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKBackgroundVisibility9668); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundVisibilityAccess().getBackgroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4519:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4520:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4520:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4521:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility9689);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4537:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==65) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4538:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4538:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4539:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKBackgroundVisibility9707); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4560:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4561:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4562:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9757);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle9767); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4569:1: ruleKLineStyle returns [EObject current=null] : ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_lineStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4572:28: ( ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4573:1: ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4573:1: ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4573:2: () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4573:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4574:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineStyleAccess().getKLineStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKLineStyle9813); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4583:1: ( (lv_lineStyle_2_0= ruleLineStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4584:1: (lv_lineStyle_2_0= ruleLineStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4584:1: (lv_lineStyle_2_0= ruleLineStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4585:3: lv_lineStyle_2_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle9834);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4601:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==65) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4602:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4602:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4603:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKLineStyle9852); 

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


    // $ANTLR start "entryRuleKRotation"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4624:1: entryRuleKRotation returns [EObject current=null] : iv_ruleKRotation= ruleKRotation EOF ;
    public final EObject entryRuleKRotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRotation = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4625:2: (iv_ruleKRotation= ruleKRotation EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4626:2: iv_ruleKRotation= ruleKRotation EOF
            {
             newCompositeNode(grammarAccess.getKRotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRotation_in_entryRuleKRotation9902);
            iv_ruleKRotation=ruleKRotation();

            state._fsp--;

             current =iv_ruleKRotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRotation9912); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4633:1: ruleKRotation returns [EObject current=null] : ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKRotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_rotation_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4636:28: ( ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4637:1: ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4637:1: ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4637:2: () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4637:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4638:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRotationAccess().getKRotationAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKRotation9958); 

                	newLeafNode(otherlv_1, grammarAccess.getKRotationAccess().getRotationKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4647:1: ( (lv_rotation_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4648:1: (lv_rotation_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4648:1: (lv_rotation_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4649:3: lv_rotation_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRotationAccess().getRotationEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRotation9979);
            lv_rotation_2_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKRotationRule());
            	        }
                   		set(
                   			current, 
                   			"rotation",
                    		lv_rotation_2_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4665:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==65) ) {
                alt185=1;
            }
            switch (alt185) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4666:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4666:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4667:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKRotation9997); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKRotationAccess().getPropagateToChildrenExclamationMarkKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRotationRule());
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
    // $ANTLR end "ruleKRotation"


    // $ANTLR start "entryRuleKFontBold"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4688:1: entryRuleKFontBold returns [EObject current=null] : iv_ruleKFontBold= ruleKFontBold EOF ;
    public final EObject entryRuleKFontBold() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontBold = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4689:2: (iv_ruleKFontBold= ruleKFontBold EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4690:2: iv_ruleKFontBold= ruleKFontBold EOF
            {
             newCompositeNode(grammarAccess.getKFontBoldRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_entryRuleKFontBold10047);
            iv_ruleKFontBold=ruleKFontBold();

            state._fsp--;

             current =iv_ruleKFontBold; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontBold10057); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4697:1: ruleKFontBold returns [EObject current=null] : ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontBold() throws RecognitionException {
        EObject current = null;

        Token lv_bold_1_0=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4700:28: ( ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4701:1: ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4701:1: ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4701:2: () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4701:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4702:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontBoldAccess().getKFontBoldAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4707:2: ( (lv_bold_1_0= 'bold' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4708:1: (lv_bold_1_0= 'bold' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4708:1: (lv_bold_1_0= 'bold' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4709:3: lv_bold_1_0= 'bold'
            {
            lv_bold_1_0=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKFontBold10109); 

                    newLeafNode(lv_bold_1_0, grammarAccess.getKFontBoldAccess().getBoldBoldKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKFontBoldRule());
            	        }
                   		setWithLastConsumed(current, "bold", true, "bold");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4722:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==65) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4723:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4723:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4724:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontBold10140); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4745:1: entryRuleKFontItalic returns [EObject current=null] : iv_ruleKFontItalic= ruleKFontItalic EOF ;
    public final EObject entryRuleKFontItalic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontItalic = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4746:2: (iv_ruleKFontItalic= ruleKFontItalic EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4747:2: iv_ruleKFontItalic= ruleKFontItalic EOF
            {
             newCompositeNode(grammarAccess.getKFontItalicRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic10190);
            iv_ruleKFontItalic=ruleKFontItalic();

            state._fsp--;

             current =iv_ruleKFontItalic; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontItalic10200); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4754:1: ruleKFontItalic returns [EObject current=null] : ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontItalic() throws RecognitionException {
        EObject current = null;

        Token lv_italic_1_0=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4757:28: ( ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4758:1: ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4758:1: ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4758:2: () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4758:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4759:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontItalicAccess().getKFontItalicAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4764:2: ( (lv_italic_1_0= 'italic' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4765:1: (lv_italic_1_0= 'italic' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4765:1: (lv_italic_1_0= 'italic' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4766:3: lv_italic_1_0= 'italic'
            {
            lv_italic_1_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKFontItalic10252); 

                    newLeafNode(lv_italic_1_0, grammarAccess.getKFontItalicAccess().getItalicItalicKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKFontItalicRule());
            	        }
                   		setWithLastConsumed(current, "italic", true, "italic");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4779:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==65) ) {
                alt187=1;
            }
            switch (alt187) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4780:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4780:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4781:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontItalic10283); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4802:1: entryRuleKFontName returns [EObject current=null] : iv_ruleKFontName= ruleKFontName EOF ;
    public final EObject entryRuleKFontName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontName = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4803:2: (iv_ruleKFontName= ruleKFontName EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4804:2: iv_ruleKFontName= ruleKFontName EOF
            {
             newCompositeNode(grammarAccess.getKFontNameRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_entryRuleKFontName10333);
            iv_ruleKFontName=ruleKFontName();

            state._fsp--;

             current =iv_ruleKFontName; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontName10343); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4811:1: ruleKFontName returns [EObject current=null] : (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontName() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4814:28: ( (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4815:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4815:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4815:3: otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKFontName10380); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontNameAccess().getFontKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4819:1: ( (lv_name_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4820:1: (lv_name_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4820:1: (lv_name_1_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4821:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKFontNameAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKFontName10401);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4837:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==65) ) {
                alt188=1;
            }
            switch (alt188) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4838:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4838:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4839:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontName10419); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4860:1: entryRuleKFontSize returns [EObject current=null] : iv_ruleKFontSize= ruleKFontSize EOF ;
    public final EObject entryRuleKFontSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontSize = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4861:2: (iv_ruleKFontSize= ruleKFontSize EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4862:2: iv_ruleKFontSize= ruleKFontSize EOF
            {
             newCompositeNode(grammarAccess.getKFontSizeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_entryRuleKFontSize10469);
            iv_ruleKFontSize=ruleKFontSize();

            state._fsp--;

             current =iv_ruleKFontSize; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontSize10479); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4869:1: ruleKFontSize returns [EObject current=null] : (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontSize() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_size_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4872:28: ( (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4873:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4873:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4873:3: otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKFontSize10516); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontSizeAccess().getFontSizeKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4877:1: ( (lv_size_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4878:1: (lv_size_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4878:1: (lv_size_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4879:3: lv_size_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKFontSizeAccess().getSizeEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKFontSize10537);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4895:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==65) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4896:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4896:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4897:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontSize10555); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4918:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4919:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4920:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10605);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment10615); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4927:1: ruleKVerticalAlignment returns [EObject current=null] : ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_verticalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4930:28: ( ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4931:1: ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4931:1: ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4931:2: () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4931:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4932:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKVerticalAlignment10661); 

                	newLeafNode(otherlv_1, grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4941:1: ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4942:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4942:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4943:3: lv_verticalAlignment_2_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10682);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4959:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==65) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4960:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4960:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4961:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKVerticalAlignment10700); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4982:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4983:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4984:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10750);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment10760); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4991:1: ruleKHorizontalAlignment returns [EObject current=null] : ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_horizontalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4994:28: ( ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4995:1: ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4995:1: ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4995:2: () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4995:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4996:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKHorizontalAlignment10806); 

                	newLeafNode(otherlv_1, grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5005:1: ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5006:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5006:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5007:3: lv_horizontalAlignment_2_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10827);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5023:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==65) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5024:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5024:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5025:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKHorizontalAlignment10845); 

                            newLeafNode(lv_propagateToChildren_3_0, grammarAccess.getKHorizontalAlignmentAccess().getPropagateToChildrenExclamationMarkKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
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
    // $ANTLR end "ruleKHorizontalAlignment"


    // $ANTLR start "entryRuleKGridPlacement"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5046:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5047:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5048:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement10895);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement10905); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5055:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_numColumns_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5058:28: ( ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5059:1: ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5059:1: ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5059:2: () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5059:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5060:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleKGridPlacement10951); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getGridPlacementKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5069:1: ( (lv_numColumns_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5070:1: (lv_numColumns_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5070:1: (lv_numColumns_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5071:3: lv_numColumns_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement10972);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5095:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5096:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5097:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11008);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement11018); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5104:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'stackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5107:28: ( ( () otherlv_1= 'stackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5108:1: ( () otherlv_1= 'stackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5108:1: ( () otherlv_1= 'stackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5108:2: () otherlv_1= 'stackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5108:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5109:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleKStackPlacement11064); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5126:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5127:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5128:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat11101);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat11112); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5135:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5138:28: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5139:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5139:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5139:2: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5139:2: (kw= '-' )?
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==28) ) {
                alt192=1;
            }
            switch (alt192) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5140:2: kw= '-'
                    {
                    kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleEFloat11151); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11168); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5152:1: (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==80) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5153:2: kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    {
                    kw=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleEFloat11187); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2_0()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11202); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_2_1()); 
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5165:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    int alt195=2;
                    int LA195_0 = input.LA(1);

                    if ( ((LA195_0>=81 && LA195_0<=82)) ) {
                        alt195=1;
                    }
                    switch (alt195) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5165:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5165:2: (kw= 'E' | kw= 'e' )
                            int alt193=2;
                            int LA193_0 = input.LA(1);

                            if ( (LA193_0==81) ) {
                                alt193=1;
                            }
                            else if ( (LA193_0==82) ) {
                                alt193=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 193, 0, input);

                                throw nvae;
                            }
                            switch (alt193) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5166:2: kw= 'E'
                                    {
                                    kw=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleEFloat11222); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_0()); 
                                        

                                    }
                                    break;
                                case 2 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5173:2: kw= 'e'
                                    {
                                    kw=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleEFloat11241); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_1()); 
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5178:2: (kw= '-' )?
                            int alt194=2;
                            int LA194_0 = input.LA(1);

                            if ( (LA194_0==28) ) {
                                alt194=1;
                            }
                            switch (alt194) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5179:2: kw= '-'
                                    {
                                    kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleEFloat11256); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_2_2_1()); 
                                        

                                    }
                                    break;

                            }

                            this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11273); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5199:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5200:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5201:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             newCompositeNode(grammarAccess.getEBooleanRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean11323);
            iv_ruleEBoolean=ruleEBoolean();

            state._fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean11334); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5208:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5211:28: ( (kw= 'true' | kw= 'false' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5212:1: (kw= 'true' | kw= 'false' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5212:1: (kw= 'true' | kw= 'false' )
            int alt197=2;
            int LA197_0 = input.LA(1);

            if ( (LA197_0==83) ) {
                alt197=1;
            }
            else if ( (LA197_0==84) ) {
                alt197=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 197, 0, input);

                throw nvae;
            }
            switch (alt197) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5213:2: kw= 'true'
                    {
                    kw=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleEBoolean11372); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEBooleanAccess().getTrueKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5220:2: kw= 'false'
                    {
                    kw=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleEBoolean11391); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5233:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5234:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5235:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt11432);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt11443); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5242:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5245:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5246:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5246:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5246:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5246:2: (kw= '-' )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==28) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5247:2: kw= '-'
                    {
                    kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleEInt11482); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt11499); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5269:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5270:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5271:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets11546);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets11556); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5278:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5281:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5282:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5282:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5282:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5282:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5283:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKInsets11602); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets11614); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5296:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==62) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5296:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKInsets11627); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5300:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5301:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5301:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5302:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11648);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5318:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt200=2;
            int LA200_0 = input.LA(1);

            if ( (LA200_0==63) ) {
                alt200=1;
            }
            switch (alt200) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5318:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKInsets11663); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5322:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5323:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5323:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5324:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11684);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5340:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==60) ) {
                alt201=1;
            }
            switch (alt201) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5340:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKInsets11699); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5344:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5345:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5345:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5346:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11720);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5362:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt202=2;
            int LA202_0 = input.LA(1);

            if ( (LA202_0==61) ) {
                alt202=1;
            }
            switch (alt202) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5362:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKInsets11735); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5366:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5367:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5367:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5368:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11756);
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

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKInsets11770); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5398:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5399:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5400:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint11808);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint11818); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5407:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_x_3_0 = null;

        AntlrDatatypeRuleToken lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5410:28: ( ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5411:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5411:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5411:2: () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5411:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5412:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKPoint11864); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5421:1: (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5421:3: otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) )
            {
            otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleKPoint11877); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getXKeyword_2_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5425:1: ( (lv_x_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5426:1: (lv_x_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5426:1: (lv_x_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5427:3: lv_x_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11898);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5443:3: (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5443:5: otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) )
            {
            otherlv_4=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleKPoint11912); 

                	newLeafNode(otherlv_4, grammarAccess.getKPointAccess().getYKeyword_3_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5447:1: ( (lv_y_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5448:1: (lv_y_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5448:1: (lv_y_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5449:3: lv_y_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11933);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5473:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5474:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5475:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry11970);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry11980); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5482:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5485:28: ( ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5486:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5486:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5486:2: ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5486:2: ( (lv_key_0_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5487:1: (lv_key_0_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5487:1: (lv_key_0_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5488:3: lv_key_0_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry12026);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5504:2: (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            int alt203=2;
            int LA203_0 = input.LA(1);

            if ( (LA203_0==89) ) {
                alt203=1;
            }
            switch (alt203) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5504:4: otherlv_1= '=' ( (lv_value_2_0= ruleEString ) )
                    {
                    otherlv_1=(Token)match(input,89,FollowSets000.FOLLOW_89_in_rulePersistentEntry12039); 

                        	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5508:1: ( (lv_value_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5509:1: (lv_value_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5509:1: (lv_value_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5510:3: lv_value_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry12060);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5534:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5535:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5536:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString12099);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString12110); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5543:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5546:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5547:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5547:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt204=2;
            int LA204_0 = input.LA(1);

            if ( (LA204_0==RULE_STRING) ) {
                alt204=1;
            }
            else if ( (LA204_0==RULE_ID) ) {
                alt204=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 204, 0, input);

                throw nvae;
            }
            switch (alt204) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5547:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString12150); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5555:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString12176); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5570:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5572:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5573:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5573:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt205=5;
            switch ( input.LA(1) ) {
            case 90:
                {
                alt205=1;
                }
                break;
            case 91:
                {
                alt205=2;
                }
                break;
            case 92:
                {
                alt205=3;
                }
                break;
            case 93:
                {
                alt205=4;
                }
                break;
            case 94:
                {
                alt205=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 205, 0, input);

                throw nvae;
            }

            switch (alt205) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5573:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5573:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5573:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleLineStyle12235); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5579:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5579:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5579:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleLineStyle12252); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5585:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5585:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5585:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleLineStyle12269); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5591:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5591:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5591:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleLineStyle12286); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5597:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5597:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5597:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleLineStyle12303); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5607:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5609:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5610:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5610:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt206=3;
            switch ( input.LA(1) ) {
            case 95:
                {
                alt206=1;
                }
                break;
            case 96:
                {
                alt206=2;
                }
                break;
            case 97:
                {
                alt206=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 206, 0, input);

                throw nvae;
            }

            switch (alt206) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5610:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5610:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5610:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleVerticalAlignment12348); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5616:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5616:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5616:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleVerticalAlignment12365); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5622:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5622:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5622:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleVerticalAlignment12382); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5632:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5634:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5635:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5635:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt207=3;
            switch ( input.LA(1) ) {
            case 98:
                {
                alt207=1;
                }
                break;
            case 96:
                {
                alt207=2;
                }
                break;
            case 99:
                {
                alt207=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 207, 0, input);

                throw nvae;
            }

            switch (alt207) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5635:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5635:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5635:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleHorizontalAlignment12427); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5641:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5641:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5641:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleHorizontalAlignment12444); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5647:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5647:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5647:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleHorizontalAlignment12461); 

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
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary143 = new BitSet(new long[]{0x00000024EFC4C000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary165 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingLibrary179 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary202 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingLibrary218 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_entryRuleKRendering254 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRendering264 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_ruleKRendering311 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_ruleKRendering338 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_ruleKRendering365 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_ruleKRendering392 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_ruleKRendering419 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_ruleKRendering446 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_ruleKRendering473 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_ruleKRendering500 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_ruleKRendering527 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_ruleKRendering554 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_ruleKRendering581 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_ruleKRendering608 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedBendsPolyline_in_ruleKRendering635 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData670 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacementData680 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData727 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData754 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData781 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle870 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_ruleKStyle927 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_ruleKStyle954 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle981 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle1008 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle1035 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRotation_in_ruleKStyle1062 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_ruleKStyle1089 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_ruleKStyle1116 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_ruleKStyle1143 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_ruleKStyle1170 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1197 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1224 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement1259 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement1269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement1316 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_ruleKPlacement1343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition1378 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition1388 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition1435 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition1462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition1497 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition1507 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition1554 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition1581 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1616 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef1626 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef1672 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1695 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1708 = new BitSet(new long[]{0x0000000000034000L});
        public static final BitSet FOLLOW_16_in_ruleKRenderingRef1721 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1742 = new BitSet(new long[]{0x0000000000024000L});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1757 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1769 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1790 = new BitSet(new long[]{0x0000000000006000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRenderingRef1804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1827 = new BitSet(new long[]{0x0000000000006000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1841 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1855 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse1893 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse1903 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse1949 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse1962 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse1975 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKEllipse1988 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse2011 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse2048 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKEllipse2065 = new BitSet(new long[]{0x0130204000080000L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse2078 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse2101 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKEllipse2116 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse2129 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse2152 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKEllipse2167 = new BitSet(new long[]{0x00000024EFCC8000L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse2180 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2203 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2217 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2240 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2256 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle2294 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle2304 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKRectangle2350 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2363 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2376 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2389 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2412 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2449 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKRectangle2466 = new BitSet(new long[]{0x0130204000080000L});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2479 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle2502 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRectangle2517 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2530 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle2553 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRectangle2568 = new BitSet(new long[]{0x00000024EFCC8000L});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2581 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2604 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2618 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2641 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2657 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2695 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle2705 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2751 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2772 = new BitSet(new long[]{0x0000000010002010L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2785 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2808 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle2821 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2834 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle2847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2870 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2907 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle2924 = new BitSet(new long[]{0x0130204000080000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle2937 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2960 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedRectangle2975 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle2988 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle3011 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedRectangle3026 = new BitSet(new long[]{0x00000024EFCC8000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle3039 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3062 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle3076 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3099 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3115 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3153 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl3163 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_ruleKPolyline_Impl3209 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3222 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3235 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3271 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3308 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl3325 = new BitSet(new long[]{0x0130204000080000L});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3338 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3361 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolyline_Impl3376 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3389 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3412 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolyline_Impl3427 = new BitSet(new long[]{0x00000024EFCC8000L});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3440 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3463 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3477 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3500 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3516 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedBendsPolyline_in_entryRuleKRoundedBendsPolyline3554 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedBendsPolyline3564 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_ruleKRoundedBendsPolyline3610 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedBendsPolyline3631 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedBendsPolyline3644 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3657 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKRoundedBendsPolyline3670 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3693 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRoundedBendsPolyline3707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3730 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKRoundedBendsPolyline3747 = new BitSet(new long[]{0x0130204000080000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedBendsPolyline3760 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedBendsPolyline3783 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedBendsPolyline3798 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedBendsPolyline3811 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedBendsPolyline3834 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedBendsPolyline3849 = new BitSet(new long[]{0x00000024EFCC8000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedBendsPolyline3862 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3885 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedBendsPolyline3899 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3922 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedBendsPolyline3938 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon3976 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon3986 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_ruleKPolygon4032 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon4045 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4058 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKPolygon4071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4094 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKPolygon4108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4131 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKPolygon4148 = new BitSet(new long[]{0x0130204000080000L});
        public static final BitSet FOLLOW_19_in_ruleKPolygon4161 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon4184 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolygon4199 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_19_in_ruleKPolygon4212 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon4235 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolygon4250 = new BitSet(new long[]{0x00000024EFCC8000L});
        public static final BitSet FOLLOW_19_in_ruleKPolygon4263 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4286 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolygon4300 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4323 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon4339 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage4377 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage4387 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleKImage4433 = new BitSet(new long[]{0x0000000010000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4455 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_28_in_ruleKImage4473 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4495 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKImage4508 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4521 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKImage4534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4557 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKImage4571 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4594 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKImage4611 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage4632 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKImage4647 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage4659 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4680 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKImage4694 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4717 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4731 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKImage4746 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage4767 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4781 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc4819 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc4829 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleKArc4875 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4896 = new BitSet(new long[]{0x0000000010002010L});
        public static final BitSet FOLLOW_13_in_ruleKArc4909 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4932 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKArc4945 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKArc4958 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKArc4971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc4994 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKArc5008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc5031 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKArc5048 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc5069 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKArc5084 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc5096 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5117 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKArc5131 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5154 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5168 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKArc5183 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc5204 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5218 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea5256 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea5266 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_ruleKChildArea5312 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea5325 = new BitSet(new long[]{0x0000000000034000L});
        public static final BitSet FOLLOW_17_in_ruleKChildArea5338 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKChildArea5351 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5374 = new BitSet(new long[]{0x0000000000016000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKChildArea5388 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5411 = new BitSet(new long[]{0x0000000000016000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKChildArea5428 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea5449 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea5463 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText5501 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText5511 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_31_in_ruleKText5557 = new BitSet(new long[]{0x0000000000001062L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText5578 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKText5592 = new BitSet(new long[]{0x0000000300334000L});
        public static final BitSet FOLLOW_32_in_ruleKText5610 = new BitSet(new long[]{0x0000000200334000L});
        public static final BitSet FOLLOW_17_in_ruleKText5637 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKText5650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5673 = new BitSet(new long[]{0x0000000200316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKText5687 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5710 = new BitSet(new long[]{0x0000000200316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKText5727 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText5748 = new BitSet(new long[]{0x0000000200304000L});
        public static final BitSet FOLLOW_21_in_ruleKText5763 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText5775 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5796 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKText5810 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5833 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKText5847 = new BitSet(new long[]{0x0000000200104000L});
        public static final BitSet FOLLOW_20_in_ruleKText5862 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText5883 = new BitSet(new long[]{0x0000000200004000L});
        public static final BitSet FOLLOW_33_in_ruleKText5898 = new BitSet(new long[]{0x0000000000080060L});
        public static final BitSet FOLLOW_19_in_ruleKText5911 = new BitSet(new long[]{0x0000000000080060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKText5934 = new BitSet(new long[]{0x0000000000086060L});
        public static final BitSet FOLLOW_13_in_ruleKText5948 = new BitSet(new long[]{0x0000000000080060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKText5971 = new BitSet(new long[]{0x0000000000086060L});
        public static final BitSet FOLLOW_14_in_ruleKText5987 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6025 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering6035 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_ruleKCustomRendering6081 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6094 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_35_in_ruleKCustomRendering6106 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6127 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_36_in_ruleKCustomRendering6139 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6160 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6173 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKCustomRendering6186 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6209 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6246 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKCustomRendering6263 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6284 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKCustomRendering6299 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6311 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6332 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6346 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6369 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6383 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKCustomRendering6398 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering6419 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6433 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline6471 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline6481 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_37_in_ruleKSpline6527 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6540 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6553 = new BitSet(new long[]{0x0000000000080000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_19_in_ruleKSpline6566 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6589 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKSpline6603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6626 = new BitSet(new long[]{0x0000000000316000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_16_in_ruleKSpline6643 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline6664 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKSpline6679 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6691 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6712 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_13_in_ruleKSpline6726 = new BitSet(new long[]{0x00000024EFC48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6749 = new BitSet(new long[]{0x00000024EFC4E000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6763 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKSpline6778 = new BitSet(new long[]{0x0000000000080000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline6799 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6813 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6851 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6861 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleKDecoratorPlacementData6898 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData6910 = new BitSet(new long[]{0x0000018000000000L});
        public static final BitSet FOLLOW_39_in_ruleKDecoratorPlacementData6928 = new BitSet(new long[]{0x0000010000000000L});
        public static final BitSet FOLLOW_40_in_ruleKDecoratorPlacementData6954 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6975 = new BitSet(new long[]{0x00001E0000004000L});
        public static final BitSet FOLLOW_41_in_ruleKDecoratorPlacementData6988 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7009 = new BitSet(new long[]{0x00001C0000004000L});
        public static final BitSet FOLLOW_42_in_ruleKDecoratorPlacementData7024 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7045 = new BitSet(new long[]{0x0000180000004000L});
        public static final BitSet FOLLOW_43_in_ruleKDecoratorPlacementData7060 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7081 = new BitSet(new long[]{0x0000100000004000L});
        public static final BitSet FOLLOW_44_in_ruleKDecoratorPlacementData7096 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7117 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDecoratorPlacementData7131 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7167 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData7177 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_45_in_ruleKGridPlacementData7223 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData7235 = new BitSet(new long[]{0x000FC00000004000L});
        public static final BitSet FOLLOW_46_in_ruleKGridPlacementData7248 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7269 = new BitSet(new long[]{0x000F800000004000L});
        public static final BitSet FOLLOW_47_in_ruleKGridPlacementData7284 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7305 = new BitSet(new long[]{0x000F000000004000L});
        public static final BitSet FOLLOW_48_in_ruleKGridPlacementData7320 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7341 = new BitSet(new long[]{0x000E000000004000L});
        public static final BitSet FOLLOW_49_in_ruleKGridPlacementData7356 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7377 = new BitSet(new long[]{0x000C000000004000L});
        public static final BitSet FOLLOW_50_in_ruleKGridPlacementData7392 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7413 = new BitSet(new long[]{0x0008000000004000L});
        public static final BitSet FOLLOW_51_in_ruleKGridPlacementData7428 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7449 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKGridPlacementData7463 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData7499 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData7509 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_52_in_ruleKStackPlacementData7555 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData7567 = new BitSet(new long[]{0x000F000000004000L});
        public static final BitSet FOLLOW_48_in_ruleKStackPlacementData7580 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7601 = new BitSet(new long[]{0x000E000000004000L});
        public static final BitSet FOLLOW_49_in_ruleKStackPlacementData7616 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7637 = new BitSet(new long[]{0x000C000000004000L});
        public static final BitSet FOLLOW_50_in_ruleKStackPlacementData7652 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7673 = new BitSet(new long[]{0x0008000000004000L});
        public static final BitSet FOLLOW_51_in_ruleKStackPlacementData7688 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7709 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKStackPlacementData7723 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7759 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData7769 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleKDirectPlacementData7806 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData7818 = new BitSet(new long[]{0x0040000000000000L});
        public static final BitSet FOLLOW_54_in_ruleKDirectPlacementData7830 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7851 = new BitSet(new long[]{0x0080000000002000L});
        public static final BitSet FOLLOW_13_in_ruleKDirectPlacementData7864 = new BitSet(new long[]{0x0080000000000000L});
        public static final BitSet FOLLOW_55_in_ruleKDirectPlacementData7878 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7899 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDirectPlacementData7911 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData7947 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData7957 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_56_in_ruleKPolylinePlacementData7994 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData8006 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKPolylinePlacementData8018 = new BitSet(new long[]{0x3000000000080000L});
        public static final BitSet FOLLOW_19_in_ruleKPolylinePlacementData8031 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8054 = new BitSet(new long[]{0x3400000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKPolylinePlacementData8068 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8091 = new BitSet(new long[]{0x3400000000006000L});
        public static final BitSet FOLLOW_58_in_ruleKPolylinePlacementData8106 = new BitSet(new long[]{0x0130204000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData8127 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPolylinePlacementData8141 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition8177 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition8187 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition8233 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_59_in_ruleKPosition8245 = new BitSet(new long[]{0xC000000000000000L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition8266 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8302 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition8312 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_60_in_ruleKLeftPosition8358 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8379 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8400 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8436 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition8446 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_61_in_ruleKRightPosition8492 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8513 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8534 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8570 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition8580 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_62_in_ruleKTopPosition8626 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8647 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8668 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8704 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition8714 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKBottomPosition8760 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8781 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8802 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor8838 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor8848 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_64_in_ruleKForegroundColor8894 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor8915 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor8936 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor8957 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKForegroundColor8975 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor9025 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor9035 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_66_in_ruleKBackgroundColor9081 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9102 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9123 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9144 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKBackgroundColor9162 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9212 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth9222 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_67_in_ruleKLineWidth9259 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth9280 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKLineWidth9298 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility9348 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility9358 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility9405 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility9432 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility9467 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundVisibility9477 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_68_in_ruleKForegroundVisibility9523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility9544 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKForegroundVisibility9562 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility9612 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundVisibility9622 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_69_in_ruleKBackgroundVisibility9668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility9689 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKBackgroundVisibility9707 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9757 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle9767 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_70_in_ruleKLineStyle9813 = new BitSet(new long[]{0x0000000000000000L,0x000000007C000000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle9834 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKLineStyle9852 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRotation_in_entryRuleKRotation9902 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRotation9912 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleKRotation9958 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRotation9979 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKRotation9997 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_entryRuleKFontBold10047 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontBold10057 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_72_in_ruleKFontBold10109 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontBold10140 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic10190 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontItalic10200 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_73_in_ruleKFontItalic10252 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontItalic10283 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_entryRuleKFontName10333 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontName10343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_74_in_ruleKFontName10380 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKFontName10401 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontName10419 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_entryRuleKFontSize10469 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontSize10479 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleKFontSize10516 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKFontSize10537 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontSize10555 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10605 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment10615 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKVerticalAlignment10661 = new BitSet(new long[]{0x0000000000000000L,0x0000000380000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10682 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKVerticalAlignment10700 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10750 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment10760 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleKHorizontalAlignment10806 = new BitSet(new long[]{0x0000000000000000L,0x0000000D00000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10827 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKHorizontalAlignment10845 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement10895 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement10905 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_78_in_ruleKGridPlacement10951 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement10972 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11008 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement11018 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_79_in_ruleKStackPlacement11064 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat11101 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat11112 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_ruleEFloat11151 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11168 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
        public static final BitSet FOLLOW_80_in_ruleEFloat11187 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11202 = new BitSet(new long[]{0x0000000000000002L,0x0000000000060000L});
        public static final BitSet FOLLOW_81_in_ruleEFloat11222 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_82_in_ruleEFloat11241 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_28_in_ruleEFloat11256 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11273 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean11323 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean11334 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_83_in_ruleEBoolean11372 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_84_in_ruleEBoolean11391 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt11432 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt11443 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_ruleEInt11482 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt11499 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets11546 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets11556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_85_in_ruleKInsets11602 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets11614 = new BitSet(new long[]{0xF000000000004000L});
        public static final BitSet FOLLOW_62_in_ruleKInsets11627 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11648 = new BitSet(new long[]{0xB000000000004000L});
        public static final BitSet FOLLOW_63_in_ruleKInsets11663 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11684 = new BitSet(new long[]{0x3000000000004000L});
        public static final BitSet FOLLOW_60_in_ruleKInsets11699 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11720 = new BitSet(new long[]{0x2000000000004000L});
        public static final BitSet FOLLOW_61_in_ruleKInsets11735 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11756 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKInsets11770 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint11808 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint11818 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_86_in_ruleKPoint11864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
        public static final BitSet FOLLOW_87_in_ruleKPoint11877 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11898 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_88_in_ruleKPoint11912 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11933 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry11970 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry11980 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry12026 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
        public static final BitSet FOLLOW_89_in_rulePersistentEntry12039 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry12060 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString12099 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString12110 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString12150 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString12176 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_90_in_ruleLineStyle12235 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_91_in_ruleLineStyle12252 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_92_in_ruleLineStyle12269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_93_in_ruleLineStyle12286 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_94_in_ruleLineStyle12303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleVerticalAlignment12348 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleVerticalAlignment12365 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_97_in_ruleVerticalAlignment12382 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleHorizontalAlignment12427 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleHorizontalAlignment12444 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_99_in_ruleHorizontalAlignment12461 = new BitSet(new long[]{0x0000000000000002L});
    }


}